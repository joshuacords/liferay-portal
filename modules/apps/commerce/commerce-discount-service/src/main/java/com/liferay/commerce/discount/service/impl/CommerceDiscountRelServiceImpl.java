/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.service.impl;

import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountRel;
import com.liferay.commerce.discount.service.base.CommerceDiscountRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceDiscountRelServiceImpl
	extends CommerceDiscountRelServiceBaseImpl {

	@Override
	public CommerceDiscountRel addCommerceDiscountRel(
			long commerceDiscountId, String className, long classPK,
			ServiceContext serviceContext)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.UPDATE);

		return commerceDiscountRelLocalService.addCommerceDiscountRel(
			commerceDiscountId, className, classPK, serviceContext);
	}

	@Override
	public void deleteCommerceDiscountRel(long commerceDiscountRelId)
		throws PortalException {

		CommerceDiscountRel commerceDiscountRel =
			commerceDiscountRelLocalService.getCommerceDiscountRel(
				commerceDiscountRelId);

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountRel.getCommerceDiscountId(),
			ActionKeys.UPDATE);

		commerceDiscountRelLocalService.deleteCommerceDiscountRel(
			commerceDiscountRel);
	}

	@Override
	public CommerceDiscountRel fetchCommerceDiscountRel(
			String className, long classPK)
		throws PortalException {

		CommerceDiscountRel commerceDiscountRel =
			commerceDiscountRelLocalService.fetchCommerceDiscountRel(
				className, classPK);

		if (commerceDiscountRel != null) {
			_commerceDiscountResourcePermission.check(
				getPermissionChecker(),
				commerceDiscountRel.getCommerceDiscountId(), ActionKeys.UPDATE);
		}

		return commerceDiscountRel;
	}

	@Override
	public List<CommerceDiscountRel> getCategoriesByCommerceDiscountId(
		long commerceDiscountId, String name, int start, int end) {

		return commerceDiscountRelFinder.findCategoriesByCommerceDiscountId(
			commerceDiscountId, name, start, end, true);
	}

	@Override
	public int getCategoriesByCommerceDiscountIdCount(
		long commerceDiscountId, String name) {

		return commerceDiscountRelFinder.countCategoriesByCommerceDiscountId(
			commerceDiscountId, name, true);
	}

	@Override
	public long[] getClassPKs(long commerceDiscountId, String className)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.UPDATE);

		return commerceDiscountRelLocalService.getClassPKs(
			commerceDiscountId, className);
	}

	@Override
	public CommerceDiscountRel getCommerceDiscountRel(
			long commerceDiscountRelId)
		throws PortalException {

		CommerceDiscountRel commerceDiscountRel =
			commerceDiscountRelLocalService.getCommerceDiscountRel(
				commerceDiscountRelId);

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountRel.getCommerceDiscountId(),
			ActionKeys.UPDATE);

		return commerceDiscountRel;
	}

	@Override
	public List<CommerceDiscountRel> getCommerceDiscountRels(
			long commerceDiscountId, String className)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.UPDATE);

		return commerceDiscountRelLocalService.getCommerceDiscountRels(
			commerceDiscountId, className);
	}

	@Override
	public List<CommerceDiscountRel> getCommerceDiscountRels(
			long commerceDiscountId, String className, int start, int end,
			OrderByComparator<CommerceDiscountRel> orderByComparator)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.UPDATE);

		return commerceDiscountRelLocalService.getCommerceDiscountRels(
			commerceDiscountId, className, start, end, orderByComparator);
	}

	@Override
	public int getCommerceDiscountRelsCount(
			long commerceDiscountId, String className)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.UPDATE);

		return commerceDiscountRelLocalService.getCommerceDiscountRelsCount(
			commerceDiscountId, className);
	}

	@Override
	public List<CommerceDiscountRel>
		getCommercePricingClassesByCommerceDiscountId(
			long commerceDiscountId, String title, int start, int end) {

		return commerceDiscountRelFinder.findPricingClassesByCommerceDiscountId(
			commerceDiscountId, title, start, end, true);
	}

	@Override
	public int getCommercePricingClassesByCommerceDiscountIdCount(
		long commerceDiscountId, String title) {

		return commerceDiscountRelFinder.
			countPricingClassesByCommerceDiscountId(
				commerceDiscountId, title, true);
	}

	@Override
	public List<CommerceDiscountRel> getCPDefinitionsByCommerceDiscountId(
		long commerceDiscountId, String name, String languageId, int start,
		int end) {

		return commerceDiscountRelFinder.findCPDefinitionsByCommerceDiscountId(
			commerceDiscountId, name, languageId, start, end, true);
	}

	@Override
	public int getCPDefinitionsByCommerceDiscountIdCount(
		long commerceDiscountId, String name, String languageId) {

		return commerceDiscountRelFinder.countCPDefinitionsByCommerceDiscountId(
			commerceDiscountId, name, languageId, true);
	}

	private static volatile ModelResourcePermission<CommerceDiscount>
		_commerceDiscountResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceDiscountCommerceAccountGroupRelServiceImpl.class,
				"_commerceDiscountResourcePermission", CommerceDiscount.class);

}