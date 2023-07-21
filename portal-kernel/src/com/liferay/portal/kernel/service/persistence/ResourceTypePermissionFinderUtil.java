/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 * @deprecated
 * @generated
 */
@Deprecated
public class ResourceTypePermissionFinderUtil {

	public static java.util.List
		<com.liferay.portal.kernel.model.ResourceTypePermission>
			findByEitherScopeC_G_N(long companyId, long groupId, String name) {

		return getFinder().findByEitherScopeC_G_N(companyId, groupId, name);
	}

	public static java.util.List
		<com.liferay.portal.kernel.model.ResourceTypePermission>
			findByGroupScopeC_N_R(long companyId, String name, long roleId) {

		return getFinder().findByGroupScopeC_N_R(companyId, name, roleId);
	}

	public static ResourceTypePermissionFinder getFinder() {
		if (_finder == null) {
			_finder =
				(ResourceTypePermissionFinder)PortalBeanLocatorUtil.locate(
					ResourceTypePermissionFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(ResourceTypePermissionFinder finder) {
		_finder = finder;
	}

	private static ResourceTypePermissionFinder _finder;

}