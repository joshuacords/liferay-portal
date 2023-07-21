/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.service.permission;

import com.liferay.knowledge.base.constants.KBConstants;
import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Adolfo Pérez
 * @author     Roberto Díaz
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Component(
	property = "model.class.name=com.liferay.knowledge.base.model.KBFolder",
	service = BaseModelPermissionChecker.class
)
@Deprecated
public class KBFolderPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, KBFolder kbFolder,
			String actionId)
		throws PortalException {

		_kbFolderModelResourcePermission.check(
			permissionChecker, kbFolder, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, long kbFolderId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, kbFolderId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, KBFolder.class.getName(), kbFolderId,
				actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long kbFolderId,
			String actionId)
		throws PortalException {

		_kbFolderModelResourcePermission.check(
			permissionChecker, kbFolderId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, KBFolder kbFolder,
			String actionId)
		throws PortalException {

		return _kbFolderModelResourcePermission.contains(
			permissionChecker, kbFolder, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long kbFolderId,
			String actionId)
		throws PortalException {

		if (kbFolderId == KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			if (actionId.equals(ActionKeys.VIEW)) {
				return true;
			}

			return _portletResourcePermission.contains(
				permissionChecker, groupId, actionId);
		}

		return _kbFolderModelResourcePermission.contains(
			permissionChecker, kbFolderId, actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		contains(permissionChecker, groupId, primaryKey, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.knowledge.base.model.KBFolder)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<KBFolder> modelResourcePermission) {

		_kbFolderModelResourcePermission = modelResourcePermission;
	}

	@Reference(
		target = "(resource.name=" + KBConstants.RESOURCE_NAME_ADMIN + ")",
		unbind = "-"
	)
	protected void setPortletResourcePermission(
		PortletResourcePermission portletResourcePermission) {

		_portletResourcePermission = portletResourcePermission;
	}

	private static ModelResourcePermission<KBFolder>
		_kbFolderModelResourcePermission;
	private static PortletResourcePermission _portletResourcePermission;

}