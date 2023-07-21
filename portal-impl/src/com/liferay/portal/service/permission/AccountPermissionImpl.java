/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Account;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.AccountPermission;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountPermissionImpl implements AccountPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, account, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, Account.class.getName(),
				account.getAccountId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long accountId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, accountId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, Account.class.getName(), accountId,
				actionId);
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, Account account, String actionId) {

		return permissionChecker.hasPermission(
			null, Account.class.getName(), account.getAccountId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long accountId,
			String actionId)
		throws PortalException {

		return contains(
			permissionChecker, AccountLocalServiceUtil.getAccount(accountId),
			actionId);
	}

}