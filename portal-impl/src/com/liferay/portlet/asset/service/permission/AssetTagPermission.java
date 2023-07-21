/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.service.permission;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author     Eduardo Lundgren
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class AssetTagPermission {

	public static void check(
			PermissionChecker permissionChecker, AssetTag tag, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, tag, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, AssetTag.class.getName(), tag.getTagId(),
				actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long tagId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, tagId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, AssetTag.class.getName(), tagId, actionId);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, AssetTag tag, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				tag.getCompanyId(), AssetTag.class.getName(), tag.getTagId(),
				tag.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			tag.getGroupId(), AssetTag.class.getName(), tag.getTagId(),
			actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long tagId, String actionId)
		throws PortalException {

		return contains(
			permissionChecker, AssetTagLocalServiceUtil.getTag(tagId),
			actionId);
	}

}