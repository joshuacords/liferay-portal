/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.cache;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.io.Serializable;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tina Tian
 */
public class PortalCacheManagerProvider {

	public static PortalCacheManager<? extends Serializable, ?>
		getPortalCacheManager(String portalCacheManagerName) {

		return _dynamicPortalCacheManagers.computeIfAbsent(
			portalCacheManagerName,
			key -> new DynamicPortalCacheManager<>(key));
	}

	public static Collection<PortalCacheManager<? extends Serializable, ?>>
		getPortalCacheManagers() {

		return Collections.unmodifiableCollection(
			_dynamicPortalCacheManagers.values());
	}

	private static final Map
		<String, DynamicPortalCacheManager<? extends Serializable, ?>>
			_dynamicPortalCacheManagers = new ConcurrentHashMap<>();
	private static final ServiceTracker
		<PortalCacheManager<? extends Serializable, ?>,
		 DynamicPortalCacheManager<? extends Serializable, ?>> _serviceTracker;

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			(Class<PortalCacheManager<? extends Serializable, ?>>)
				(Class<?>)PortalCacheManager.class,
			new PortalCacheProviderServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	private static class PortalCacheProviderServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<PortalCacheManager<? extends Serializable, ?>,
			 DynamicPortalCacheManager<? extends Serializable, ?>> {

		@Override
		public DynamicPortalCacheManager<? extends Serializable, ?>
			addingService(
				ServiceReference<PortalCacheManager<? extends Serializable, ?>>
					serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			PortalCacheManager<? extends Serializable, ?> portalCacheManager =
				registry.getService(serviceReference);

			DynamicPortalCacheManager<? extends Serializable, ?>
				dynamicPortalCacheManager =
					_dynamicPortalCacheManagers.computeIfAbsent(
						portalCacheManager.getPortalCacheManagerName(),
						key -> new DynamicPortalCacheManager<>(key));

			dynamicPortalCacheManager.setPortalCacheManager(portalCacheManager);

			return dynamicPortalCacheManager;
		}

		@Override
		public void modifiedService(
			ServiceReference<PortalCacheManager<? extends Serializable, ?>>
				serviceReference,
			DynamicPortalCacheManager<? extends Serializable, ?>
				dynamicPortalCacheManager) {
		}

		@Override
		public void removedService(
			ServiceReference<PortalCacheManager<? extends Serializable, ?>>
				serviceReference,
			DynamicPortalCacheManager<? extends Serializable, ?>
				dynamicPortalCacheManager) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			dynamicPortalCacheManager.setPortalCacheManager(null);
		}

	}

}