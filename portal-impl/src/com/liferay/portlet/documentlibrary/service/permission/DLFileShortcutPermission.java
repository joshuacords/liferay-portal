/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.service.permission;

import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class DLFileShortcutPermission {

	public static void check(
			PermissionChecker permissionChecker, DLFileShortcut dlFileShortcut,
			String actionId)
		throws PortalException {

		_dlFileShortcutModelResourcePermission.check(
			permissionChecker, dlFileShortcut, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, FileShortcut fileShortcut,
			String actionId)
		throws PortalException {

		_fileShortcutModelResourcePermission.check(
			permissionChecker, fileShortcut, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long fileShortcutId,
			String actionId)
		throws PortalException {

		_dlFileShortcutModelResourcePermission.check(
			permissionChecker, fileShortcutId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DLFileShortcut dlFileShortcut,
			String actionId)
		throws PortalException {

		return _dlFileShortcutModelResourcePermission.contains(
			permissionChecker, dlFileShortcut, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, FileShortcut fileShortcut,
			String actionId)
		throws PortalException {

		return _fileShortcutModelResourcePermission.contains(
			permissionChecker, fileShortcut, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long fileShortcutId,
			String actionId)
		throws PortalException {

		return _dlFileShortcutModelResourcePermission.contains(
			permissionChecker, fileShortcutId, actionId);
	}

	private static volatile ModelResourcePermission<DLFileShortcut>
		_dlFileShortcutModelResourcePermission =
			ServiceProxyFactory.newServiceTrackedInstance(
				ModelResourcePermission.class, DLFileShortcutPermission.class,
				"_dlFileShortcutModelResourcePermission",
				"(model.class.name=" + DLFileShortcut.class.getName() + ")",
				true);
	private static volatile ModelResourcePermission<FileShortcut>
		_fileShortcutModelResourcePermission =
			ServiceProxyFactory.newServiceTrackedInstance(
				ModelResourcePermission.class, DLFileShortcutPermission.class,
				"_fileShortcutModelResourcePermission",
				"(model.class.name=" + FileShortcut.class.getName() + ")",
				true);

}