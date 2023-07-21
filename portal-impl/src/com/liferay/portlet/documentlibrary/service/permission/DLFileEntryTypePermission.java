/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.service.permission;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author     Alexander Chow
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class DLFileEntryTypePermission {

	public static void check(
			PermissionChecker permissionChecker, DLFileEntryType fileEntryType,
			String actionId)
		throws PortalException {

		_dlFileEntryTypeModelResourcePermission.check(
			permissionChecker, fileEntryType, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long fileEntryTypeId,
			String actionId)
		throws PortalException {

		_dlFileEntryTypeModelResourcePermission.check(
			permissionChecker, fileEntryTypeId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DLFileEntryType fileEntryType,
			String actionId)
		throws PortalException {

		return _dlFileEntryTypeModelResourcePermission.contains(
			permissionChecker, fileEntryType, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long fileEntryTypeId,
			String actionId)
		throws PortalException {

		return _dlFileEntryTypeModelResourcePermission.contains(
			permissionChecker, fileEntryTypeId, actionId);
	}

	private static volatile ModelResourcePermission<DLFileEntryType>
		_dlFileEntryTypeModelResourcePermission =
			ServiceProxyFactory.newServiceTrackedInstance(
				ModelResourcePermission.class, DLFileEntryTypePermission.class,
				"_dlFileEntryTypeModelResourcePermission",
				"(model.class.name=" + DLFileEntryType.class.getName() + ")",
				true);

}