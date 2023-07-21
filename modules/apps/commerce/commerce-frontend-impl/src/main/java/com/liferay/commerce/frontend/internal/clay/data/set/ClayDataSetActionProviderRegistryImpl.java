/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.data.set;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetActionProvider;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetActionProviderRegistry;
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
@Component(immediate = true, service = ClayDataSetActionProviderRegistry.class)
public class ClayDataSetActionProviderRegistryImpl
	implements ClayDataSetActionProviderRegistry {

	@Override
	public List<ClayDataSetActionProvider> getClayDataSetActionProviders(
		String key) {

		List<ServiceWrapper<ClayDataSetActionProvider>>
			clayDataSetActionProviderServiceWrappers =
				_serviceTrackerMap.getService(key);

		if (clayDataSetActionProviderServiceWrappers == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No ClayDataSetActionProvider registered with key " + key);
			}

			return Collections.emptyList();
		}

		List<ClayDataSetActionProvider> clayDataSetActionProviders =
			new ArrayList<>();

		for (ServiceWrapper<ClayDataSetActionProvider>
				tableActionProviderServiceWrapper :
					clayDataSetActionProviderServiceWrappers) {

			clayDataSetActionProviders.add(
				tableActionProviderServiceWrapper.getService());
		}

		return clayDataSetActionProviders;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, ClayDataSetActionProvider.class,
			"commerce.data.provider.key",
			ServiceTrackerCustomizerFactory.
				<ClayDataSetActionProvider>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayDataSetActionProviderRegistryImpl.class);

	private ServiceTrackerMap
		<String, List<ServiceWrapper<ClayDataSetActionProvider>>>
			_serviceTrackerMap;

}