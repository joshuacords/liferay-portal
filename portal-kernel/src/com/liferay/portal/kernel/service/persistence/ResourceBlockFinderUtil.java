/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ResourceBlockFinderUtil {

	public static
		com.liferay.portal.kernel.security.permission.ResourceBlockIdsBag
			findByC_G_N_R(
				long companyId, long groupId, String name, long[] roleIds) {

		return getFinder().findByC_G_N_R(companyId, groupId, name, roleIds);
	}

	public static ResourceBlockFinder getFinder() {
		if (_finder == null) {
			_finder = (ResourceBlockFinder)PortalBeanLocatorUtil.locate(
				ResourceBlockFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(ResourceBlockFinder finder) {
		_finder = finder;
	}

	private static ResourceBlockFinder _finder;

}