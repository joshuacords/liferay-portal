/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.service.impl;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRel;
import com.liferay.commerce.account.service.base.CommerceAccountGroupCommerceAccountRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupCommerceAccountRelServiceImpl
	extends CommerceAccountGroupCommerceAccountRelServiceBaseImpl {

	@Override
	public CommerceAccountGroupCommerceAccountRel
			addCommerceAccountGroupCommerceAccountRel(
				long commerceAccountGroupId, long commerceAccountId,
				ServiceContext serviceContext)
		throws PortalException {

		_commerceAccountGroupModelResourcePermission.check(
			getPermissionChecker(), commerceAccountGroupId, ActionKeys.UPDATE);

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(), commerceAccountId, ActionKeys.UPDATE);

		return commerceAccountGroupCommerceAccountRelLocalService.
			addCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroupId, commerceAccountId, serviceContext);
	}

	@Override
	public void deleteCommerceAccountGroupCommerceAccountRel(
			long commerceAccountGroupCommerceAccountRelId)
		throws PortalException {

		CommerceAccountGroupCommerceAccountRel
			commerceAccountGroupCommerceAccountRel =
				commerceAccountGroupCommerceAccountRelLocalService.
					getCommerceAccountGroupCommerceAccountRel(
						commerceAccountGroupCommerceAccountRelId);

		_commerceAccountGroupModelResourcePermission.check(
			getPermissionChecker(),
			commerceAccountGroupCommerceAccountRel.getCommerceAccountGroupId(),
			ActionKeys.UPDATE);

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(),
			commerceAccountGroupCommerceAccountRel.getCommerceAccountId(),
			ActionKeys.UPDATE);

		commerceAccountGroupCommerceAccountRelLocalService.
			deleteCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroupCommerceAccountRel);
	}

	@Override
	public CommerceAccountGroupCommerceAccountRel
			getCommerceAccountGroupCommerceAccountRel(
				long commerceAccountGroupId, long commerceAccountId)
		throws PortalException {

		_commerceAccountGroupModelResourcePermission.check(
			getPermissionChecker(), commerceAccountGroupId, ActionKeys.UPDATE);

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(), commerceAccountId, ActionKeys.UPDATE);

		return commerceAccountGroupCommerceAccountRelLocalService.
			getCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroupId, commerceAccountId);
	}

	@Override
	public List<CommerceAccountGroupCommerceAccountRel>
			getCommerceAccountGroupCommerceAccountRels(
				long commerceAccountGroupId, int start, int end)
		throws PortalException {

		_commerceAccountGroupModelResourcePermission.check(
			getPermissionChecker(), commerceAccountGroupId, ActionKeys.VIEW);

		return commerceAccountGroupCommerceAccountRelLocalService.
			getCommerceAccountGroupCommerceAccountRels(
				commerceAccountGroupId, start, end);
	}

	@Override
	public int getCommerceAccountGroupCommerceAccountRelsCount(
			long commerceAccountGroupId)
		throws PortalException {

		_commerceAccountGroupModelResourcePermission.check(
			getPermissionChecker(), commerceAccountGroupId, ActionKeys.UPDATE);

		return commerceAccountGroupCommerceAccountRelLocalService.
			getCommerceAccountGroupCommerceAccountRelsCount(
				commerceAccountGroupId);
	}

	private static volatile ModelResourcePermission<CommerceAccountGroup>
		_commerceAccountGroupModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceAccountGroupCommerceAccountRelServiceImpl.class,
				"_commerceAccountGroupModelResourcePermission",
				CommerceAccountGroup.class);
	private static volatile ModelResourcePermission<CommerceAccount>
		_commerceAccountModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceAccountGroupCommerceAccountRelServiceImpl.class,
				"_commerceAccountModelResourcePermission",
				CommerceAccount.class);

}