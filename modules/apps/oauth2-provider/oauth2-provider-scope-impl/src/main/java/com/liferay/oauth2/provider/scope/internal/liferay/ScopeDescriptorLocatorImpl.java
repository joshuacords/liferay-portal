/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.scope.internal.liferay;

import com.liferay.oauth2.provider.scope.internal.constants.OAuth2ProviderScopeConstants;
import com.liferay.oauth2.provider.scope.liferay.ScopeDescriptorLocator;
import com.liferay.oauth2.provider.scope.liferay.ScopedServiceTrackerMap;
import com.liferay.oauth2.provider.scope.liferay.ScopedServiceTrackerMapFactory;
import com.liferay.oauth2.provider.scope.spi.scope.descriptor.ScopeDescriptor;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(service = ScopeDescriptorLocator.class)
public class ScopeDescriptorLocatorImpl implements ScopeDescriptorLocator {

	@Override
	public ScopeDescriptor getScopeDescriptor(
		long companyId, String applicationName) {

		return _scopedServiceTrackerMap.getService(companyId, applicationName);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public ScopeDescriptor getScopeDescriptor(String applicationName) {
		return _scopedServiceTrackerMap.getService(0, applicationName);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_scopedServiceTrackerMap = _scopedServiceTrackerMapFactory.create(
			bundleContext, ScopeDescriptor.class,
			OAuth2ProviderScopeConstants.OSGI_JAXRS_NAME,
			() -> _defaultScopeDescriptor);
	}

	@Deactivate
	protected void deactivate() {
		_scopedServiceTrackerMap.close();
	}

	@Reference(unbind = "-")
	protected void setScopedServiceTrackerMapFactory(
		ScopedServiceTrackerMapFactory scopedServiceTrackerMapFactory) {

		_scopedServiceTrackerMapFactory = scopedServiceTrackerMapFactory;
	}

	@Reference(target = "(default=true)")
	private ScopeDescriptor _defaultScopeDescriptor;

	private ScopedServiceTrackerMap<ScopeDescriptor> _scopedServiceTrackerMap;
	private ScopedServiceTrackerMapFactory _scopedServiceTrackerMapFactory;

}