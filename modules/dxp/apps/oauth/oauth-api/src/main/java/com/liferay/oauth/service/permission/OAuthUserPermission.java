/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.service.permission;

import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.OAuthUserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Ivica Cardic
 */
public class OAuthUserPermission {

	public static void check(
			PermissionChecker permissionChecker, long oAuthUserId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, oAuthUserId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, OAuthUser oAuthUser,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, oAuthUser, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long oAuthUserId,
			String actionId)
		throws PortalException {

		return contains(
			permissionChecker,
			OAuthUserLocalServiceUtil.getOAuthUser(oAuthUserId), actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, OAuthUser oAuthUser,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				oAuthUser.getCompanyId(), OAuthUser.class.getName(),
				oAuthUser.getOAuthUserId(), oAuthUser.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			null, OAuthUser.class.getName(), oAuthUser.getOAuthApplicationId(),
			actionId);
	}

}