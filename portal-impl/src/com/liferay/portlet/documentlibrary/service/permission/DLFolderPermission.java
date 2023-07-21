/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.service.permission;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionHelper;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
@OSGiBeanProperties(
	property = "model.class.name=com.liferay.document.library.kernel.model.DLFolder"
)
public class DLFolderPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, DLFolder dlFolder,
			String actionId)
		throws PortalException {

		_dlFolderModelResourcePermission.check(
			permissionChecker, dlFolder, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, Folder folder, String actionId)
		throws PortalException {

		_folderModelResourcePermission.check(
			permissionChecker, folder, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, long folderId,
			String actionId)
		throws PortalException {

		ModelResourcePermissionHelper.check(
			_folderModelResourcePermission, permissionChecker, groupId,
			folderId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DLFolder dlFolder,
			String actionId)
		throws PortalException {

		return _dlFolderModelResourcePermission.contains(
			permissionChecker, dlFolder, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Folder folder, String actionId)
		throws PortalException {

		return _folderModelResourcePermission.contains(
			permissionChecker, folder, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long folderId,
			String actionId)
		throws PortalException {

		return ModelResourcePermissionHelper.contains(
			_folderModelResourcePermission, permissionChecker, groupId,
			folderId, actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		ModelResourcePermissionHelper.check(
			_folderModelResourcePermission, permissionChecker, groupId,
			primaryKey, actionId);
	}

	private static volatile ModelResourcePermission<DLFolder>
		_dlFolderModelResourcePermission =
			ServiceProxyFactory.newServiceTrackedInstance(
				ModelResourcePermission.class, DLFolderPermission.class,
				"_dlFolderModelResourcePermission",
				"(model.class.name=" + DLFolder.class.getName() + ")", true);
	private static volatile ModelResourcePermission<Folder>
		_folderModelResourcePermission =
			ServiceProxyFactory.newServiceTrackedInstance(
				ModelResourcePermission.class, DLFolderPermission.class,
				"_folderModelResourcePermission",
				"(model.class.name=" + Folder.class.getName() + ")", true);

}