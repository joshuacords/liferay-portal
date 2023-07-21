/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.permission;

import com.liferay.osb.koroneiki.root.permission.ModelPermission;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, service = {AccountPermission.class, ModelPermission.class}
)
public class AccountPermissionImpl
	implements AccountPermission, ModelPermission {

	public static final String RESOURCE_NAME_ACCOUNTS =
		"com.liferay.osb.koroneiki.taproot.accounts";

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
	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME_ACCOUNTS, 0, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				account.getCompanyId(), Account.class.getName(),
				account.getAccountId(), account.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, Account.class.getName(), account.getAccountId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long accountId,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker, _accountLocalService.getAccount(accountId),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] accountIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(accountIds)) {
			return false;
		}

		for (long accountId : accountIds) {
			if (!contains(permissionChecker, accountId, actionId)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		return permissionChecker.hasPermission(
			0, RESOURCE_NAME_ACCOUNTS, RESOURCE_NAME_ACCOUNTS, actionId);
	}

	@Override
	public String getClassName() {
		return Account.class.getName();
	}

	@Reference
	private AccountLocalService _accountLocalService;

}