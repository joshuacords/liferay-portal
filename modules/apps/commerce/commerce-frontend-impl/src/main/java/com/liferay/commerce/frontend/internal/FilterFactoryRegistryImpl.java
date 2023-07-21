/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal;

import com.liferay.commerce.frontend.FilterFactory;
import com.liferay.commerce.frontend.FilterFactoryRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = FilterFactoryRegistry.class)
public class FilterFactoryRegistryImpl implements FilterFactoryRegistry {

	@Override
	public List<FilterFactory> getFilterFactories() {
		List<FilterFactory> filterFactories = new ArrayList<>();

		List<ServiceWrapper<FilterFactory>> filterFactoryServiceWrappers =
			ListUtil.fromCollection(_serviceTrackerMap.values());

		for (ServiceWrapper<FilterFactory> filterFactoryServiceWrapper :
				filterFactoryServiceWrappers) {

			filterFactories.add(filterFactoryServiceWrapper.getService());
		}

		return Collections.unmodifiableList(filterFactories);
	}

	@Override
	public FilterFactory getFilterFactory(String key) {
		ServiceWrapper<FilterFactory> filterFactoryServiceWrapper =
			_serviceTrackerMap.getService(key);

		if (filterFactoryServiceWrapper == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No FilterFactory registered with key " + key);
			}

			return new DefaultFilterFactoryImpl();
		}

		return filterFactoryServiceWrapper.getService();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, FilterFactory.class, "commerce.data.provider.key",
			ServiceTrackerCustomizerFactory.<FilterFactory>serviceWrapper(
				bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FilterFactoryRegistryImpl.class);

	private ServiceTrackerMap<String, ServiceWrapper<FilterFactory>>
		_serviceTrackerMap;

}