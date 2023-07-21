/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.instances.service.internal.initializer;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.instances.initializer.PortalInstanceInitializer;
import com.liferay.portal.instances.initializer.PortalInstanceInitializerRegistry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = PortalInstanceInitializerRegistry.class)
public class PortalInstanceInitializerRegistryImpl
	implements PortalInstanceInitializerRegistry {

	@Override
	public PortalInstanceInitializer getPortalInstanceInitializer(String key) {
		if (Validator.isNull(key)) {
			return null;
		}

		PortalInstanceInitializer portalInstanceInitializer =
			_serviceTrackerMap.getService(key);

		if (portalInstanceInitializer == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No portal instance initializer registered with key " +
						key);
			}

			return null;
		}

		return portalInstanceInitializer;
	}

	@Override
	public List<PortalInstanceInitializer> getPortalInstanceInitializers() {
		return getPortalInstanceInitializers(false);
	}

	@Override
	public List<PortalInstanceInitializer> getPortalInstanceInitializers(
		boolean activeOnly) {

		if (!activeOnly) {
			return new ArrayList<>(_serviceTrackerMap.values());
		}

		List<PortalInstanceInitializer> portalInstanceInitializers =
			new ArrayList<>();

		for (PortalInstanceInitializer portalInstanceInitializer :
				_serviceTrackerMap.values()) {

			if (portalInstanceInitializer.isActive()) {
				portalInstanceInitializers.add(portalInstanceInitializer);
			}
		}

		return portalInstanceInitializers;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, PortalInstanceInitializer.class,
			"portal.instance.initializer.key");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortalInstanceInitializerRegistryImpl.class);

	private ServiceTrackerMap<String, PortalInstanceInitializer>
		_serviceTrackerMap;

}