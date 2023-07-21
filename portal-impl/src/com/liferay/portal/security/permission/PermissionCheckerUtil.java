/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 */
public class PermissionCheckerUtil {

	public static void setThreadValues(User user) {
		if (user == null) {
			PrincipalThreadLocal.setName(null);
			PermissionThreadLocal.setPermissionChecker(null);

			return;
		}

		PrincipalThreadLocal.setName(String.valueOf(user.getUserId()));

		try {
			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			if (permissionChecker == null) {
				Class<?> clazz = Class.forName(PropsValues.PERMISSIONS_CHECKER);

				permissionChecker = (PermissionChecker)clazz.newInstance();
			}

			permissionChecker.init(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PermissionCheckerUtil.class);

}