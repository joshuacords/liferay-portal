/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.service.permission;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author     Brian Wing Shun Chan
 * @author     Charles May
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
@OSGiBeanProperties(
	property = "model.class.name=com.liferay.document.library.kernel.model.DLFileEntry"
)
public class DLFileEntryPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, DLFileEntry dlFileEntry,
			String actionId)
		throws PortalException {

		_dlFileEntryModelResourcePermission.check(
			permissionChecker, dlFileEntry, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, FileEntry fileEntry,
			String actionId)
		throws PortalException {

		_fileEntryModelResourcePermission.check(
			permissionChecker, fileEntry, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long fileEntryId,
			String actionId)
		throws PortalException {

		_fileEntryModelResourcePermission.check(
			permissionChecker, fileEntryId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DLFileEntry dlFileEntry,
			String actionId)
		throws PortalException {

		return _dlFileEntryModelResourcePermission.contains(
			permissionChecker, dlFileEntry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, FileEntry fileEntry,
			String actionId)
		throws PortalException {

		return _fileEntryModelResourcePermission.contains(
			permissionChecker, fileEntry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long fileEntryId,
			String actionId)
		throws PortalException {

		return _fileEntryModelResourcePermission.contains(
			permissionChecker, fileEntryId, actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		_fileEntryModelResourcePermission.check(
			permissionChecker, primaryKey, actionId);
	}

	private static volatile ModelResourcePermission<DLFileEntry>
		_dlFileEntryModelResourcePermission =
			ServiceProxyFactory.newServiceTrackedInstance(
				ModelResourcePermission.class, DLFileEntryPermission.class,
				"_dlFileEntryModelResourcePermission",
				"(model.class.name=" + DLFileEntry.class.getName() + ")", true);
	private static volatile ModelResourcePermission<FileEntry>
		_fileEntryModelResourcePermission =
			ServiceProxyFactory.newServiceTrackedInstance(
				ModelResourcePermission.class, DLFileEntryPermission.class,
				"_fileEntryModelResourcePermission",
				"(model.class.name=" + FileEntry.class.getName() + ")", true);

}