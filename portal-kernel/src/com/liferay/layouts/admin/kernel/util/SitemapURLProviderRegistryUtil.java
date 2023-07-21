/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layouts.admin.kernel.util;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;
import com.liferay.registry.collections.ServiceRegistrationMap;
import com.liferay.registry.collections.ServiceRegistrationMapImpl;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author     Eduardo García
 * @deprecated As of Mueller (7.2.x), replaced by {@link
 *             com.liferay.layout.admin.kernel.util.SitemapURLProviderRegistryUtil}
 */
@Deprecated
public class SitemapURLProviderRegistryUtil {

	public static SitemapURLProvider getSitemapURLProvider(String className) {
		return _sitemapURLProvidersServiceTrackerMap.getService(className);
	}

	public static List<SitemapURLProvider> getSitemapURLProviders() {
		Set<String> classNames = _sitemapURLProvidersServiceTrackerMap.keySet();

		List<SitemapURLProvider> sitemapURLProviders = new ArrayList<>(
			classNames.size());

		for (String className : classNames) {
			sitemapURLProviders.add(
				_sitemapURLProvidersServiceTrackerMap.getService(className));
		}

		return sitemapURLProviders;
	}

	public static void register(SitemapURLProvider sitemapURLProvider) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<SitemapURLProvider> serviceRegistration =
			registry.registerService(
				SitemapURLProvider.class, sitemapURLProvider);

		_serviceRegistrations.put(sitemapURLProvider, serviceRegistration);
	}

	public static void unregister(
		List<SitemapURLProvider> sitemapURLProviders) {

		for (SitemapURLProvider sitemapURLProvider : sitemapURLProviders) {
			unregister(sitemapURLProvider);
		}
	}

	public static void unregister(SitemapURLProvider sitemapURLProvider) {
		ServiceRegistration<SitemapURLProvider> serviceRegistration =
			_serviceRegistrations.remove(sitemapURLProvider);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	private static final ServiceRegistrationMap<SitemapURLProvider>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();

	private static final ServiceTrackerMap<String, SitemapURLProvider>
		_sitemapURLProvidersServiceTrackerMap =
			ServiceTrackerCollections.openSingleValueMap(
				SitemapURLProvider.class, null,
				(serviceReference, emitter) -> {
					Registry registry = RegistryUtil.getRegistry();

					SitemapURLProvider sitemapURLProvider = registry.getService(
						serviceReference);

					emitter.emit(sitemapURLProvider.getClassName());
				});

}