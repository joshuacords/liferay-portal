/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.permission;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Alessio Antonio Rendina
 */
public interface CommerceAccountPermission {

	public void check(
			PermissionChecker permissionChecker,
			CommerceAccount commerceAccount, String actionId)
		throws PortalException;

	public void check(
			PermissionChecker permissionChecker, long commerceAccountId,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceAccount commerceAccount, String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long commerceAccountId,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long[] commerceAccountIds,
			String actionId)
		throws PortalException;

}