/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Account;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException {

		getAccountPermission().check(permissionChecker, account, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long accountId,
			String actionId)
		throws PortalException {

		getAccountPermission().check(permissionChecker, accountId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException {

		return getAccountPermission().contains(
			permissionChecker, account, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long accountId,
			String actionId)
		throws PortalException {

		return getAccountPermission().contains(
			permissionChecker, accountId, actionId);
	}

	public static AccountPermission getAccountPermission() {
		return _accountPermission;
	}

	public void setAccountPermission(AccountPermission accountPermission) {
		_accountPermission = accountPermission;
	}

	private static AccountPermission _accountPermission;

}