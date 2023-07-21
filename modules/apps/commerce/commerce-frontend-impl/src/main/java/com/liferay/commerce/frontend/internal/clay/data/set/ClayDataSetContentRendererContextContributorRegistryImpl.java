/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.data.set;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetContentRendererContextContributor;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetContentRendererContextContributorRegistry;
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
	service = ClayDataSetContentRendererContextContributorRegistry.class
)
public class ClayDataSetContentRendererContextContributorRegistryImpl
	implements ClayDataSetContentRendererContextContributorRegistry {

	@Override
	public List<ClayDataSetContentRendererContextContributor>
		getClayDataSetContentRendererContextContributors(String key) {

		List<ServiceWrapper<ClayDataSetContentRendererContextContributor>>
			clayDataSetContentRendererContextContributorServiceWrappers =
				_serviceTrackerMap.getService(key);

		if (clayDataSetContentRendererContextContributorServiceWrappers ==
				null) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					"No ClayDataSetContentRendererContextContributor " +
						"registered with key " + key);
			}

			return Collections.emptyList();
		}

		List<ClayDataSetContentRendererContextContributor>
			clayDataSetContentRendererContextContributors = new ArrayList<>();

		for (ServiceWrapper<ClayDataSetContentRendererContextContributor>
				clayDataSetContentRendererContextContributorServiceWrapper :
					clayDataSetContentRendererContextContributorServiceWrappers) {

			clayDataSetContentRendererContextContributors.add(
				clayDataSetContentRendererContextContributorServiceWrapper.
					getService());
		}

		return clayDataSetContentRendererContextContributors;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, ClayDataSetContentRendererContextContributor.class,
			"commerce.data.set.content.renderer.name",
			ServiceTrackerCustomizerFactory.
				<ClayDataSetContentRendererContextContributor>serviceWrapper(
					bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayDataSetContentRendererContextContributorRegistryImpl.class);

	private ServiceTrackerMap
		<String,
		 List<ServiceWrapper<ClayDataSetContentRendererContextContributor>>>
			_serviceTrackerMap;

}