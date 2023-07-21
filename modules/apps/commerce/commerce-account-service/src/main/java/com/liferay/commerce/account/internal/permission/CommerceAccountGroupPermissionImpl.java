/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.internal.permission;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.permission.CommerceAccountGroupPermission;
import com.liferay.commerce.account.service.CommerceAccountGroupLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceAccountGroupPermission.class)
public class CommerceAccountGroupPermissionImpl
	implements CommerceAccountGroupPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceAccountGroup commerceAccountGroup, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceAccountGroup, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceAccountGroup.class.getName(),
				commerceAccountGroup.getCommerceAccountGroupId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceAccountGroupId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceAccountGroupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceAccountGroup.class.getName(),
				commerceAccountGroupId, actionId);
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker,
		CommerceAccountGroup commerceAccountGroup, String actionId) {

		if (contains(
				permissionChecker,
				commerceAccountGroup.getCommerceAccountGroupId(), actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long commerceAccountGroupId,
		String actionId) {

		CommerceAccountGroup commerceAccountGroup =
			_commerceAccountGroupLocalService.fetchCommerceAccountGroup(
				commerceAccountGroupId);

		if (commerceAccountGroup == null) {
			return false;
		}

		return _contains(permissionChecker, commerceAccountGroup, actionId);
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long[] commerceAccountGroupIds,
		String actionId) {

		if (ArrayUtil.isEmpty(commerceAccountGroupIds)) {
			return false;
		}

		for (long commerceAccountGroupId : commerceAccountGroupIds) {
			if (!contains(
					permissionChecker, commerceAccountGroupId, actionId)) {

				return false;
			}
		}

		return true;
	}

	private boolean _contains(
		PermissionChecker permissionChecker,
		CommerceAccountGroup commerceAccountGroup, String actionId) {

		if (permissionChecker.isCompanyAdmin(
				commerceAccountGroup.getCompanyId()) ||
			permissionChecker.isOmniadmin()) {

			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				permissionChecker.getCompanyId(),
				CommerceAccountGroup.class.getName(),
				commerceAccountGroup.getCommerceAccountGroupId(),
				permissionChecker.getUserId(), actionId) &&
			(commerceAccountGroup.getUserId() ==
				permissionChecker.getUserId())) {

			return true;
		}

		return permissionChecker.hasPermission(
			null, CommerceAccountGroup.class.getName(),
			commerceAccountGroup.getCommerceAccountGroupId(), actionId);
	}

	@Reference
	private CommerceAccountGroupLocalService _commerceAccountGroupLocalService;

}