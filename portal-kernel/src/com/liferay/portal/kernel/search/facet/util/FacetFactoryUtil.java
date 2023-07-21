/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.facet.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author     Raymond Augé
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class FacetFactoryUtil {

	public static Facet create(
			SearchContext searchContext, FacetConfiguration facetConfiguration)
		throws Exception {

		String className = facetConfiguration.getClassName();

		FacetFactory facetFactory = _facetFactoryUtil._facetFactories.get(
			className);

		if (facetFactory == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find facet factory for class " + className);
			}

			return null;
		}

		Facet facet = facetFactory.newInstance(searchContext);

		facet.setFacetConfiguration(facetConfiguration);

		return facet;
	}

	public FacetFactoryUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			FacetFactory.class, new FacetFactoryServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FacetFactoryUtil.class);

	private static final FacetFactoryUtil _facetFactoryUtil =
		new FacetFactoryUtil();

	private final Map<String, FacetFactory> _facetFactories =
		new ConcurrentHashMap<>();
	private final ServiceTracker<FacetFactory, FacetFactory> _serviceTracker;

	private class FacetFactoryServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<FacetFactory, FacetFactory> {

		@Override
		public FacetFactory addingService(
			ServiceReference<FacetFactory> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			FacetFactory facetFactory = registry.getService(serviceReference);

			_facetFactories.put(facetFactory.getFacetClassName(), facetFactory);

			return facetFactory;
		}

		@Override
		public void modifiedService(
			ServiceReference<FacetFactory> serviceReference,
			FacetFactory facetFactory) {
		}

		@Override
		public void removedService(
			ServiceReference<FacetFactory> serviceReference,
			FacetFactory facetFactory) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_facetFactories.remove(facetFactory.getFacetClassName());
		}

	}

}