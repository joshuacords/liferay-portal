/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.data.set;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilter;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilterRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

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
@Component(immediate = true, service = ClayDataSetFilterRegistry.class)
public class ClayDataSetFilterRegistryImpl
	implements ClayDataSetFilterRegistry {

	@Override
	public List<ClayDataSetFilter> getClayDataSetFilters(String key) {
		List<ServiceWrapper<ClayDataSetFilter>>
			clayDataSetFilterServiceWrappers = _serviceTrackerMap.getService(
				key);

		if (clayDataSetFilterServiceWrappers == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No ClayDataSetFilter registered with key " + key);
			}

			return Collections.emptyList();
		}

		List<ClayDataSetFilter> clayDataSetFilters = new ArrayList<>();

		for (ServiceWrapper<ClayDataSetFilter> clayDataSetFilterServiceWrapper :
				clayDataSetFilterServiceWrappers) {

			clayDataSetFilters.add(
				clayDataSetFilterServiceWrapper.getService());
		}

		return clayDataSetFilters;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, ClayDataSetFilter.class,
			"commerce.data.set.display.name",
			ServiceTrackerCustomizerFactory.<ClayDataSetFilter>serviceWrapper(
				bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayDataSetFilterRegistryImpl.class);

	private ServiceTrackerMap<String, List<ServiceWrapper<ClayDataSetFilter>>>
		_serviceTrackerMap;

}