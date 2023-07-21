/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.data.set;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilterContextContributor;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilterContextContributorRegistry;
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
@Component(
	immediate = true,
	service = ClayDataSetFilterContextContributorRegistry.class
)
public class ClayDataSetFilterContextContributorRegistryImpl
	implements ClayDataSetFilterContextContributorRegistry {

	@Override
	public List<ClayDataSetFilterContextContributor>
		getClayDataSetFilterContextContributors(String key) {

		List<ServiceWrapper<ClayDataSetFilterContextContributor>>
			clayDataSetFilterContextContributorServiceWrappers =
				_serviceTrackerMap.getService(key);

		if (clayDataSetFilterContextContributorServiceWrappers == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No ClayDataSetFilterContextContributor registered with " +
						"key " + key);
			}

			return Collections.emptyList();
		}

		List<ClayDataSetFilterContextContributor>
			clayDataSetFilterContextContributors = new ArrayList<>();

		for (ServiceWrapper<ClayDataSetFilterContextContributor>
				clayDataSetFilterContextContributorServiceWrapper :
					clayDataSetFilterContextContributorServiceWrappers) {

			clayDataSetFilterContextContributors.add(
				clayDataSetFilterContextContributorServiceWrapper.getService());
		}

		return clayDataSetFilterContextContributors;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, ClayDataSetFilterContextContributor.class,
			"commerce.data.set.filter.type",
			ServiceTrackerCustomizerFactory.
				<ClayDataSetFilterContextContributor>serviceWrapper(
					bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayDataSetFilterContextContributorRegistryImpl.class);

	private ServiceTrackerMap
		<String, List<ServiceWrapper<ClayDataSetFilterContextContributor>>>
			_serviceTrackerMap;

}