/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.service.impl;

import com.liferay.commerce.price.list.model.CommercePriceListDiscountRel;
import com.liferay.commerce.price.list.service.base.CommercePriceListDiscountRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Riccardo Alberti
 * @see CommercePriceListDiscountRelLocalServiceBaseImpl
 */
public class CommercePriceListDiscountRelLocalServiceImpl
	extends CommercePriceListDiscountRelLocalServiceBaseImpl {

	@Override
	public CommercePriceListDiscountRel addCommercePriceListDiscountRel(
			long commercePriceListId, long commerceDiscountId, int order,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		CommercePriceListDiscountRel commercePriceListDiscountRel =
			commercePriceListDiscountRelPersistence.create(
				counterLocalService.increment());

		commercePriceListDiscountRel.setCompanyId(user.getCompanyId());
		commercePriceListDiscountRel.setUserId(user.getUserId());
		commercePriceListDiscountRel.setUserName(user.getFullName());
		commercePriceListDiscountRel.setCommerceDiscountId(commerceDiscountId);
		commercePriceListDiscountRel.setCommercePriceListId(
			commercePriceListId);
		commercePriceListDiscountRel.setOrder(order);
		commercePriceListDiscountRel.setExpandoBridgeAttributes(serviceContext);

		// Cache

		commercePriceListLocalService.cleanPriceListCache(
			serviceContext.getCompanyId());

		return commercePriceListDiscountRelPersistence.update(
			commercePriceListDiscountRel);
	}

	@Override
	public CommercePriceListDiscountRel deleteCommercePriceListDiscountRel(
			CommercePriceListDiscountRel commercePriceListDiscountRel)
		throws PortalException {

		commercePriceListDiscountRelPersistence.remove(
			commercePriceListDiscountRel);

		// Cache

		commercePriceListLocalService.cleanPriceListCache(
			commercePriceListDiscountRel.getCompanyId());

		return commercePriceListDiscountRel;
	}

	@Override
	public CommercePriceListDiscountRel deleteCommercePriceListDiscountRel(
			long commercePriceListDiscountRelId)
		throws PortalException {

		CommercePriceListDiscountRel commercePriceListDiscountRel =
			commercePriceListDiscountRelPersistence.findByPrimaryKey(
				commercePriceListDiscountRelId);

		return commercePriceListDiscountRelLocalService.
			deleteCommercePriceListDiscountRel(commercePriceListDiscountRel);
	}

	@Override
	public void deleteCommercePriceListDiscountRels(long commercePriceListId) {
		commercePriceListDiscountRelPersistence.removeByCommercePriceListId(
			commercePriceListId);
	}

	@Override
	public CommercePriceListDiscountRel fetchCommercePriceListDiscountRel(
		long commerceDiscountId, long commercePriceListId) {

		return commercePriceListDiscountRelPersistence.fetchByC_C(
			commerceDiscountId, commercePriceListId);
	}

	@Override
	public List<CommercePriceListDiscountRel> getCommercePriceListDiscountRels(
		long commercePriceListId) {

		return commercePriceListDiscountRelPersistence.
			findByCommercePriceListId(commercePriceListId);
	}

	@Override
	public List<CommercePriceListDiscountRel> getCommercePriceListDiscountRels(
		long commercePriceListId, int start, int end,
		OrderByComparator<CommercePriceListDiscountRel> orderByComparator) {

		return commercePriceListDiscountRelPersistence.
			findByCommercePriceListId(
				commercePriceListId, start, end, orderByComparator);
	}

	@Override
	public int getCommercePriceListDiscountRelsCount(long commercePriceListId) {
		return commercePriceListDiscountRelPersistence.
			countByCommercePriceListId(commercePriceListId);
	}

}