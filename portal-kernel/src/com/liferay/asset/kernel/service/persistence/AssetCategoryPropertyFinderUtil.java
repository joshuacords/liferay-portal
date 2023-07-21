/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 * @deprecated
 * @generated
 */
@Deprecated
public class AssetCategoryPropertyFinderUtil {

	public static int countByG_K(long groupId, String key) {
		return getFinder().countByG_K(groupId, key);
	}

	public static java.util.List
		<com.liferay.asset.kernel.model.AssetCategoryProperty> findByG_K(
			long groupId, String key) {

		return getFinder().findByG_K(groupId, key);
	}

	public static java.util.List
		<com.liferay.asset.kernel.model.AssetCategoryProperty> findByG_K(
			long groupId, String key, int start, int end) {

		return getFinder().findByG_K(groupId, key, start, end);
	}

	public static AssetCategoryPropertyFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetCategoryPropertyFinder)PortalBeanLocatorUtil.locate(
				AssetCategoryPropertyFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(AssetCategoryPropertyFinder finder) {
		_finder = finder;
	}

	private static AssetCategoryPropertyFinder _finder;

}