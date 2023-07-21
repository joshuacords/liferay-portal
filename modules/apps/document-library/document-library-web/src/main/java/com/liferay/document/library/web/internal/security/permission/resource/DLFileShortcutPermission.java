/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.security.permission.resource;

import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class DLFileShortcutPermission {

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

		_fileShortcutModelResourcePermission.check(
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

		return _fileShortcutModelResourcePermission.contains(
			permissionChecker, fileShortcutId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.document.library.kernel.model.DLFileShortcut)",
		unbind = "-"
	)
	protected void setDLFileShortcutModelResourcePermission(
		ModelResourcePermission<DLFileShortcut> modelResourcePermission) {

		_dlFileShortcutModelResourcePermission = modelResourcePermission;
	}

	@Reference(
		target = "(model.class.name=com.liferay.portal.kernel.repository.model.FileShortcut)",
		unbind = "-"
	)
	protected void setFileShortcutModelResourcePermission(
		ModelResourcePermission<FileShortcut> modelResourcePermission) {

		_fileShortcutModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<DLFileShortcut>
		_dlFileShortcutModelResourcePermission;
	private static ModelResourcePermission<FileShortcut>
		_fileShortcutModelResourcePermission;

}