/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ratings.kernel.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
public class RatingsEntryFinderUtil {

	public static java.util.Map
		<java.io.Serializable, com.liferay.ratings.kernel.model.RatingsEntry>
			fetchByPrimaryKeys(
				java.util.Set<java.io.Serializable> primaryKeys) {

		return getFinder().fetchByPrimaryKeys(primaryKeys);
	}

	public static java.util.List<com.liferay.ratings.kernel.model.RatingsEntry>
		findByU_C_C(
			long userId, long classNameId, java.util.List<Long> classPKs) {

		return getFinder().findByU_C_C(userId, classNameId, classPKs);
	}

	public static RatingsEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (RatingsEntryFinder)PortalBeanLocatorUtil.locate(
				RatingsEntryFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(RatingsEntryFinder finder) {
		_finder = finder;
	}

	private static RatingsEntryFinder _finder;

}