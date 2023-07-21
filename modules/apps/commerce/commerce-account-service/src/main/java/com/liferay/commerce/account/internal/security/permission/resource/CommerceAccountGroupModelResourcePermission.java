/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.internal.security.permission.resource;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.permission.CommerceAccountGroupPermission;
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
	property = "model.class.name=com.liferay.commerce.account.model.CommerceAccountGroup",
	service = ModelResourcePermission.class
)
public class CommerceAccountGroupModelResourcePermission
	implements ModelResourcePermission<CommerceAccountGroup> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceAccountGroup commerceAccountGroup, String actionId)
		throws PortalException {

		commerceAccountGroupPermission.check(
			permissionChecker, commerceAccountGroup, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceAccountGroupId,
			String actionId)
		throws PortalException {

		commerceAccountGroupPermission.check(
			permissionChecker, commerceAccountGroupId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceAccountGroup commerceAccountGroup, String actionId)
		throws PortalException {

		return commerceAccountGroupPermission.contains(
			permissionChecker, commerceAccountGroup, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceAccountGroupId,
			String actionId)
		throws PortalException {

		return commerceAccountGroupPermission.contains(
			permissionChecker, commerceAccountGroupId, actionId);
	}

	@Override
	public String getModelName() {
		return CommerceAccountGroup.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	protected CommerceAccountGroupPermission commerceAccountGroupPermission;

}