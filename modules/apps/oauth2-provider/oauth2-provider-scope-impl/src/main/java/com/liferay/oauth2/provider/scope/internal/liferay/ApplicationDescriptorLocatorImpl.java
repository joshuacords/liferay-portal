/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.scope.internal.liferay;

import com.liferay.oauth2.provider.scope.internal.constants.OAuth2ProviderScopeConstants;
import com.liferay.oauth2.provider.scope.liferay.ApplicationDescriptorLocator;
import com.liferay.oauth2.provider.scope.spi.application.descriptor.ApplicationDescriptor;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @author Tomas Polesovsky
 */
@Component(service = ApplicationDescriptorLocator.class)
public class ApplicationDescriptorLocatorImpl
	implements ApplicationDescriptorLocator {

	@Override
	public ApplicationDescriptor getApplicationDescriptor(
		String applicationName) {

		ApplicationDescriptor applicationDescriptor =
			_serviceTrackerMap.getService(applicationName);

		if (applicationDescriptor == null) {
			return _defaultApplicationDescriptor;
		}

		return applicationDescriptor;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, ApplicationDescriptor.class,
			OAuth2ProviderScopeConstants.OSGI_JAXRS_NAME);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL, target = "(default=true)"
	)
	private ApplicationDescriptor _defaultApplicationDescriptor;

	private ServiceTrackerMap<String, ApplicationDescriptor> _serviceTrackerMap;

}