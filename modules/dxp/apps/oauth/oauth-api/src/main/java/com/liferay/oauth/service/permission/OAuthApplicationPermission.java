/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.service.permission;

import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.service.OAuthApplicationLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Ivica Cardic
 */
public class OAuthApplicationPermission {

	public static void check(
			PermissionChecker permissionChecker, long oAuthApplicationId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, oAuthApplicationId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker,
			OAuthApplication oAuthApplication, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, oAuthApplication, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long oAuthApplicationId,
			String actionId)
		throws PortalException {

		return contains(
			permissionChecker,
			OAuthApplicationLocalServiceUtil.getOAuthApplication(
				oAuthApplicationId),
			actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, OAuthApplication oAuthApplication,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				oAuthApplication.getCompanyId(),
				OAuthApplication.class.getName(),
				oAuthApplication.getOAuthApplicationId(),
				oAuthApplication.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			null, OAuthApplication.class.getName(),
			oAuthApplication.getOAuthApplicationId(), actionId);
	}

}