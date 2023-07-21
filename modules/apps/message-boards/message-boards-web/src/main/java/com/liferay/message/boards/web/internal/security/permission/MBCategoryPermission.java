/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.security.permission;

import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(immediate = true, service = {})
public class MBCategoryPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long categoryId,
			String actionId)
		throws PortalException {

		if (categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
			PortletResourcePermission portletResourcePermission =
				_categoryModelResourcePermission.getPortletResourcePermission();

			return portletResourcePermission.contains(
				permissionChecker, groupId, actionId);
		}

		return _categoryModelResourcePermission.contains(
			permissionChecker, categoryId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long categoryId,
			String actionId)
		throws PortalException {

		return _categoryModelResourcePermission.contains(
			permissionChecker, categoryId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, MBCategory category,
			String actionId)
		throws PortalException {

		return _categoryModelResourcePermission.contains(
			permissionChecker, category, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.message.boards.model.MBCategory)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<MBCategory> modelResourcePermission) {

		_categoryModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<MBCategory>
		_categoryModelResourcePermission;

}