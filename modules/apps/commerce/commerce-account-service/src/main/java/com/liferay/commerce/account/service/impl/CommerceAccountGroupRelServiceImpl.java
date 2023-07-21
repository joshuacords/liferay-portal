/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.service.impl;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.model.CommerceAccountGroupRel;
import com.liferay.commerce.account.service.base.CommerceAccountGroupRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupRelServiceImpl
	extends CommerceAccountGroupRelServiceBaseImpl {

	@Override
	public CommerceAccountGroupRel addCommerceAccountGroupRel(
			String className, long classPK, long commerceAccountGroupId,
			ServiceContext serviceContext)
		throws PortalException {

		_commerceAccountGroupModelResourcePermission.check(
			getPermissionChecker(), commerceAccountGroupId, ActionKeys.UPDATE);

		return commerceAccountGroupRelLocalService.addCommerceAccountGroupRel(
			className, classPK, commerceAccountGroupId, serviceContext);
	}

	@Override
	public void deleteCommerceAccountGroupRel(long commerceAccountGroupRelId)
		throws PortalException {

		commerceAccountGroupRelLocalService.deleteCommerceAccountGroupRel(
			commerceAccountGroupRelId);
	}

	@Override
	public void deleteCommerceAccountGroupRels(String className, long classPK) {
		commerceAccountGroupRelLocalService.deleteCommerceAccountGroupRels(
			className, classPK);
	}

	@Override
	public CommerceAccountGroupRel getCommerceAccountGroupRel(
			long commerceAccountGroupRelId)
		throws PortalException {

		return commerceAccountGroupRelLocalService.getCommerceAccountGroupRel(
			commerceAccountGroupRelId);
	}

	@Override
	public List<CommerceAccountGroupRel> getCommerceAccountGroupRels(
			long commerceAccountGroupId, int start, int end,
			OrderByComparator<CommerceAccountGroupRel> orderByComparator)
		throws PortalException {

		_commerceAccountGroupModelResourcePermission.check(
			getPermissionChecker(), commerceAccountGroupId, ActionKeys.VIEW);

		return commerceAccountGroupRelLocalService.getCommerceAccountGroupRels(
			commerceAccountGroupId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceAccountGroupRel> getCommerceAccountGroupRels(
			String className, long classPK, int start, int end,
			OrderByComparator<CommerceAccountGroupRel> orderByComparator)
		throws PortalException {

		return commerceAccountGroupRelLocalService.getCommerceAccountGroupRels(
			className, classPK, start, end, orderByComparator);
	}

	@Override
	public int getCommerceAccountGroupRelsCount(long commerceAccountGroupId)
		throws PortalException {

		_commerceAccountGroupModelResourcePermission.check(
			getPermissionChecker(), commerceAccountGroupId, ActionKeys.VIEW);

		return commerceAccountGroupRelLocalService.
			getCommerceAccountGroupRelsCount(commerceAccountGroupId);
	}

	@Override
	public int getCommerceAccountGroupRelsCount(String className, long classPK)
		throws PortalException {

		return commerceAccountGroupRelLocalService.
			getCommerceAccountGroupRelsCount(className, classPK);
	}

	private static volatile ModelResourcePermission<CommerceAccountGroup>
		_commerceAccountGroupModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceAccountGroupRelServiceImpl.class,
				"_commerceAccountGroupModelResourcePermission",
				CommerceAccountGroup.class);

}