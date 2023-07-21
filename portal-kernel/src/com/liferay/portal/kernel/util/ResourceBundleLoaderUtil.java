/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.registry.ServiceReference;
import com.liferay.registry.collections.ServiceReferenceMapper;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

/**
 * @author Carlos Sierra Andrés
 */
public class ResourceBundleLoaderUtil {

	public static ResourceBundleLoader getPortalResourceBundleLoader() {
		return _portalResourceBundleLoader;
	}

	public static ResourceBundleLoader
		getResourceBundleLoaderByBundleSymbolicName(String bundleSymbolicName) {

		return _bundleSymbolicNameServiceTrackerMap.getService(
			bundleSymbolicName);
	}

	public static ResourceBundleLoader
		getResourceBundleLoaderByServletContextName(String servletContextName) {

		return _servletContextNameServiceTrackerMap.getService(
			servletContextName);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static ResourceBundleLoader
		getResourceBundleLoaderByServletContextNameAndBaseName(
			String servletContextName, String baseName) {

		return ServiceTrackerHolder.
			_servletContextNameAndBaseNameServiceTrackerMap.getService(
				baseName + "#" + servletContextName);
	}

	public static void setPortalResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_portalResourceBundleLoader = resourceBundleLoader;
	}

	private ResourceBundleLoaderUtil() {
	}

	private static final ServiceTrackerMap<String, ResourceBundleLoader>
		_bundleSymbolicNameServiceTrackerMap;
	private static ResourceBundleLoader _portalResourceBundleLoader;
	private static final ServiceTrackerMap<String, ResourceBundleLoader>
		_servletContextNameServiceTrackerMap;

	static {
		_bundleSymbolicNameServiceTrackerMap =
			ServiceTrackerCollections.openSingleValueMap(
				ResourceBundleLoader.class, "bundle.symbolic.name");
		_servletContextNameServiceTrackerMap =
			ServiceTrackerCollections.openSingleValueMap(
				ResourceBundleLoader.class, "servlet.context.name");
	}

	private static class ServiceTrackerHolder {

		private static final ServiceTrackerMap<String, ResourceBundleLoader>
			_servletContextNameAndBaseNameServiceTrackerMap;

		static {
			_servletContextNameAndBaseNameServiceTrackerMap =
				ServiceTrackerCollections.openSingleValueMap(
					ResourceBundleLoader.class,
					"(&(resource.bundle.base.name=*)(servlet.context.name=*))",
					new ServiceReferenceMapper<String, ResourceBundleLoader>() {

						@Override
						public void map(
							ServiceReference<ResourceBundleLoader>
								serviceReference,
							Emitter<String> emitter) {

							Object baseName = serviceReference.getProperty(
								"resource.bundle.base.name");
							Object servletContextName =
								serviceReference.getProperty(
									"servlet.context.name");

							emitter.emit(baseName + "#" + servletContextName);
						}

					});
		}

	}

}