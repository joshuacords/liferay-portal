/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.security.permission.resource;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.Account",
	service = ModelResourcePermission.class
)
public class AccountModelResourcePermission
	implements ModelResourcePermission<Account> {

	@Override
	public void check(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException {

		_accountPermission.check(permissionChecker, account, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long accountId,
			String actionId)
		throws PortalException {

		_accountPermission.check(permissionChecker, accountId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, Account account,
			String actionId)
		throws PortalException {

		return _accountPermission.contains(
			permissionChecker, account, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long accountId,
			String actionId)
		throws PortalException {

		return _accountPermission.contains(
			permissionChecker, accountId, actionId);
	}

	@Override
	public String getModelName() {
		return Account.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private AccountPermission _accountPermission;

}