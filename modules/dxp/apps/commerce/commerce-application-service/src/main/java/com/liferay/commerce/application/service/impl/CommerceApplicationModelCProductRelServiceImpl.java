/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.application.service.impl;

import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.model.CommerceApplicationModelCProductRel;
import com.liferay.commerce.application.service.base.CommerceApplicationModelCProductRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceApplicationModelCProductRelServiceImpl
	extends CommerceApplicationModelCProductRelServiceBaseImpl {

	@Override
	public CommerceApplicationModelCProductRel
			addCommerceApplicationModelCProductRel(
				long userId, long commerceApplicationModelId, long cProductId)
		throws PortalException {

		_commerceApplicationModelModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationModelId,
			ActionKeys.UPDATE);

		return commerceApplicationModelCProductRelLocalService.
			addCommerceApplicationModelCProductRel(
				userId, commerceApplicationModelId, cProductId);
	}

	@Override
	public void deleteCommerceApplicationModelCProductRel(
			long commerceApplicationModelCProductRelId)
		throws PortalException {

		commerceApplicationModelCProductRelLocalService.
			deleteCommerceApplicationModelCProductRel(
				commerceApplicationModelCProductRelId);
	}

	@Override
	public List<CommerceApplicationModelCProductRel>
			getCommerceApplicationModelCProductRels(
				long commerceApplicationModelId, int start, int end)
		throws PortalException {

		_commerceApplicationModelModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationModelId,
			ActionKeys.VIEW);

		return commerceApplicationModelCProductRelLocalService.
			getCommerceApplicationModelCProductRels(
				commerceApplicationModelId, start, end);
	}

	@Override
	public int getCommerceApplicationModelCProductRelsCount(
			long commerceApplicationModelId)
		throws PortalException {

		_commerceApplicationModelModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationModelId,
			ActionKeys.VIEW);

		return commerceApplicationModelCProductRelLocalService.
			getCommerceApplicationModelCProductRelsCount(
				commerceApplicationModelId);
	}

	private static volatile ModelResourcePermission<CommerceApplicationModel>
		_commerceApplicationModelModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceApplicationModelCProductRelServiceImpl.class,
				"_commerceApplicationModelModelResourcePermission",
				CommerceApplicationModel.class);

}