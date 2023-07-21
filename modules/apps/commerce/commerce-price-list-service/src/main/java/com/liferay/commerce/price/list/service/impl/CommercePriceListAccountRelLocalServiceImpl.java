/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.service.impl;

import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListAccountRel;
import com.liferay.commerce.price.list.service.base.CommercePriceListAccountRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Ethan Bustad
 * @author Alessio Antonio Rendina
 */
public class CommercePriceListAccountRelLocalServiceImpl
	extends CommercePriceListAccountRelLocalServiceBaseImpl {

	@Override
	public CommercePriceListAccountRel addCommercePriceListAccountRel(
			long commercePriceListId, long commerceAccountId, int order,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		CommercePriceListAccountRel commercePriceListAccountRel =
			commercePriceListAccountRelPersistence.create(
				counterLocalService.increment());

		commercePriceListAccountRel.setCompanyId(user.getCompanyId());
		commercePriceListAccountRel.setUserId(user.getUserId());
		commercePriceListAccountRel.setUserName(user.getFullName());
		commercePriceListAccountRel.setCommerceAccountId(commerceAccountId);
		commercePriceListAccountRel.setCommercePriceListId(commercePriceListId);
		commercePriceListAccountRel.setOrder(order);
		commercePriceListAccountRel.setExpandoBridgeAttributes(serviceContext);

		// Commerce price list

		reindexCommercePriceList(commercePriceListId);

		// Cache

		commercePriceListLocalService.cleanPriceListCache(
			serviceContext.getCompanyId());

		return commercePriceListAccountRelPersistence.update(
			commercePriceListAccountRel);
	}

	@Override
	public CommercePriceListAccountRel deleteCommercePriceListAccountRel(
			CommercePriceListAccountRel commercePriceListAccountRel)
		throws PortalException {

		commercePriceListAccountRelPersistence.remove(
			commercePriceListAccountRel);

		// Expando

		expandoRowLocalService.deleteRows(
			commercePriceListAccountRel.getCommercePriceListAccountRelId());

		// Commerce price list

		reindexCommercePriceList(
			commercePriceListAccountRel.getCommercePriceListId());

		// Cache

		commercePriceListLocalService.cleanPriceListCache(
			commercePriceListAccountRel.getCompanyId());

		return commercePriceListAccountRel;
	}

	@Override
	public CommercePriceListAccountRel deleteCommercePriceListAccountRel(
			long commercePriceListAccountRelId)
		throws PortalException {

		CommercePriceListAccountRel commercePriceListAccountRel =
			commercePriceListAccountRelPersistence.findByPrimaryKey(
				commercePriceListAccountRelId);

		return commercePriceListAccountRelLocalService.
			deleteCommercePriceListAccountRel(commercePriceListAccountRel);
	}

	@Override
	public void deleteCommercePriceListAccountRels(long commercePriceListId)
		throws PortalException {

		List<CommercePriceListAccountRel> commercePriceListAccountRels =
			commercePriceListAccountRelPersistence.findByCommercePriceListId(
				commercePriceListId);

		for (CommercePriceListAccountRel commercePriceListAccountRel :
				commercePriceListAccountRels) {

			commercePriceListAccountRelLocalService.
				deleteCommercePriceListAccountRel(commercePriceListAccountRel);
		}
	}

	@Override
	public void deleteCommercePriceListAccountRelsByCommercePriceListId(
			long commercePriceListId)
		throws PortalException {

		List<CommercePriceListAccountRel> commercePriceListAccountRels =
			commercePriceListAccountRelPersistence.findByCommercePriceListId(
				commercePriceListId);

		for (CommercePriceListAccountRel commercePriceListAccountRel :
				commercePriceListAccountRels) {

			commercePriceListAccountRelLocalService.
				deleteCommercePriceListAccountRel(commercePriceListAccountRel);
		}
	}

	@Override
	public CommercePriceListAccountRel fetchCommercePriceListAccountRel(
		long commerceAccountId, long commercePriceListId) {

		return commercePriceListAccountRelPersistence.fetchByC_C(
			commerceAccountId, commercePriceListId);
	}

	@Override
	public List<CommercePriceListAccountRel> getCommercePriceListAccountRels(
		long commercePriceListId) {

		return commercePriceListAccountRelPersistence.findByCommercePriceListId(
			commercePriceListId);
	}

	@Override
	public List<CommercePriceListAccountRel> getCommercePriceListAccountRels(
		long commercePriceListId, int start, int end,
		OrderByComparator<CommercePriceListAccountRel> orderByComparator) {

		return commercePriceListAccountRelPersistence.findByCommercePriceListId(
			commercePriceListId, start, end, orderByComparator);
	}

	@Override
	public List<CommercePriceListAccountRel> getCommercePriceListAccountRels(
		long commercePriceListId, String name, int start, int end) {

		return commercePriceListAccountRelFinder.findByCommercePriceListId(
			commercePriceListId, name, start, end);
	}

	@Override
	public int getCommercePriceListAccountRelsCount(long commercePriceListId) {
		return commercePriceListAccountRelPersistence.
			countByCommercePriceListId(commercePriceListId);
	}

	@Override
	public int getCommercePriceListAccountRelsCount(
		long commercePriceListId, String name) {

		return commercePriceListAccountRelFinder.countByCommercePriceListId(
			commercePriceListId, name);
	}

	protected void reindexCommercePriceList(long commercePriceListId)
		throws PortalException {

		Indexer<CommercePriceList> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommercePriceList.class);

		indexer.reindex(CommercePriceList.class.getName(), commercePriceListId);
	}

}