/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.util;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Pavel Savinov
 */
public class AssetEntryUsageActionMenuContributorRegistryUtil {

	public static AssetEntryUsageActionMenuContributor
		getAssetEntryUsageActionMenuContributor(String className) {

		return _assetEntryUsageActionMenuContributorRegistryUtil.
			_getAssetEntryUsageActionMenuContributor(className);
	}

	private AssetEntryUsageActionMenuContributorRegistryUtil() {
		Bundle bundle = FrameworkUtil.getBundle(
			AssetEntryUsageActionMenuContributorRegistryUtil.class);

		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundle.getBundleContext(),
			AssetEntryUsageActionMenuContributor.class, "model.class.name");
	}

	private AssetEntryUsageActionMenuContributor
		_getAssetEntryUsageActionMenuContributor(String className) {

		return _serviceTrackerMap.getService(className);
	}

	private static final AssetEntryUsageActionMenuContributorRegistryUtil
		_assetEntryUsageActionMenuContributorRegistryUtil =
			new AssetEntryUsageActionMenuContributorRegistryUtil();

	private final ServiceTrackerMap
		<String, AssetEntryUsageActionMenuContributor> _serviceTrackerMap;

}