/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.internal.security.permission.resource;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.permission.CommerceAccountPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.commerce.account.model.CommerceAccount",
	service = ModelResourcePermission.class
)
public class CommerceAccountModelResourcePermission
	implements ModelResourcePermission<CommerceAccount> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceAccount commerceAccount, String actionId)
		throws PortalException {

		commerceAccountPermission.check(
			permissionChecker, commerceAccount, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceAccountId,
			String actionId)
		throws PortalException {

		commerceAccountPermission.check(
			permissionChecker, commerceAccountId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceAccount commerceAccount, String actionId)
		throws PortalException {

		return commerceAccountPermission.contains(
			permissionChecker, commerceAccount, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceAccountId,
			String actionId)
		throws PortalException {

		return commerceAccountPermission.contains(
			permissionChecker, commerceAccountId, actionId);
	}

	@Override
	public String getModelName() {
		return CommerceAccount.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	protected CommerceAccountPermission commerceAccountPermission;

}