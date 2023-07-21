/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.data.set;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayViewRegistry;
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
@Component(immediate = true, service = ClayDataSetDisplayViewRegistry.class)
public class ClayDataSetDisplayViewRegistryImpl
	implements ClayDataSetDisplayViewRegistry {

	@Override
	public List<ClayDataSetDisplayView> getClayDataSetDisplayViews(String key) {
		List<ServiceWrapper<ClayDataSetDisplayView>>
			clayDataSetDisplayViewServiceWrappers =
				_serviceTrackerMap.getService(key);

		if (clayDataSetDisplayViewServiceWrappers == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No ClayDataSetDisplayView registered with key " + key);
			}

			return Collections.emptyList();
		}

		List<ClayDataSetDisplayView> clayDataSetDisplayViews =
			new ArrayList<>();

		for (ServiceWrapper<ClayDataSetDisplayView>
				clayDataSetDisplayViewServiceWrapper :
					clayDataSetDisplayViewServiceWrappers) {

			clayDataSetDisplayViews.add(
				clayDataSetDisplayViewServiceWrapper.getService());
		}

		return clayDataSetDisplayViews;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, ClayDataSetDisplayView.class,
			"commerce.data.set.display.name",
			ServiceTrackerCustomizerFactory.
				<ClayDataSetDisplayView>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayDataSetDisplayViewRegistryImpl.class);

	private ServiceTrackerMap
		<String, List<ServiceWrapper<ClayDataSetDisplayView>>>
			_serviceTrackerMap;

}