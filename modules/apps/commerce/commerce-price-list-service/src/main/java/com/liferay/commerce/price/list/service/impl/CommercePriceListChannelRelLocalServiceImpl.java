/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.service.impl;

import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListChannelRel;
import com.liferay.commerce.price.list.service.base.CommercePriceListChannelRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Riccardo Alberti
 * @see CommercePriceListChannelRelLocalServiceBaseImpl
 */
public class CommercePriceListChannelRelLocalServiceImpl
	extends CommercePriceListChannelRelLocalServiceBaseImpl {

	@Override
	public CommercePriceListChannelRel addCommercePriceListChannelRel(
			long commercePriceListId, long commerceChannelId, int order,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		CommercePriceListChannelRel commercePriceListChannelRel =
			commercePriceListChannelRelPersistence.create(
				counterLocalService.increment());

		commercePriceListChannelRel.setCompanyId(user.getCompanyId());
		commercePriceListChannelRel.setUserId(user.getUserId());
		commercePriceListChannelRel.setUserName(user.getFullName());
		commercePriceListChannelRel.setCommerceChannelId(commerceChannelId);
		commercePriceListChannelRel.setCommercePriceListId(commercePriceListId);
		commercePriceListChannelRel.setOrder(order);
		commercePriceListChannelRel.setExpandoBridgeAttributes(serviceContext);

		// Commerce price list

		reindexCommercePriceList(commercePriceListId);

		// Cache

		commercePriceListLocalService.cleanPriceListCache(
			serviceContext.getCompanyId());

		return commercePriceListChannelRelPersistence.update(
			commercePriceListChannelRel);
	}

	@Override
	public CommercePriceListChannelRel deleteCommercePriceListChannelRel(
			CommercePriceListChannelRel commercePriceListChannelRel)
		throws PortalException {

		commercePriceListChannelRelPersistence.remove(
			commercePriceListChannelRel);

		// Commerce price list

		reindexCommercePriceList(
			commercePriceListChannelRel.getCommercePriceListId());

		// Cache

		commercePriceListLocalService.cleanPriceListCache(
			commercePriceListChannelRel.getCompanyId());

		return commercePriceListChannelRel;
	}

	@Override
	public CommercePriceListChannelRel deleteCommercePriceListChannelRel(
			long commercePriceListChannelRelId)
		throws PortalException {

		CommercePriceListChannelRel commercePriceListChannelRel =
			commercePriceListChannelRelPersistence.findByPrimaryKey(
				commercePriceListChannelRelId);

		return commercePriceListChannelRelLocalService.
			deleteCommercePriceListChannelRel(commercePriceListChannelRel);
	}

	@Override
	public void deleteCommercePriceListChannelRels(long commercePriceListId)
		throws PortalException {

		List<CommercePriceListChannelRel> commercePriceListChannelRels =
			commercePriceListChannelRelPersistence.findByCommercePriceListId(
				commercePriceListId);

		for (CommercePriceListChannelRel commercePriceListChannelRel :
				commercePriceListChannelRels) {

			commercePriceListChannelRelLocalService.
				deleteCommercePriceListChannelRel(commercePriceListChannelRel);
		}
	}

	@Override
	public CommercePriceListChannelRel fetchCommercePriceListChannelRel(
		long commerceChannelId, long commercePriceListId) {

		return commercePriceListChannelRelPersistence.fetchByC_C(
			commerceChannelId, commercePriceListId);
	}

	@Override
	public List<CommercePriceListChannelRel> getCommercePriceListChannelRels(
		long commercePriceListId) {

		return commercePriceListChannelRelPersistence.findByCommercePriceListId(
			commercePriceListId);
	}

	@Override
	public List<CommercePriceListChannelRel> getCommercePriceListChannelRels(
		long commercePriceListId, int start, int end,
		OrderByComparator<CommercePriceListChannelRel> orderByComparator) {

		return commercePriceListChannelRelPersistence.findByCommercePriceListId(
			commercePriceListId, start, end, orderByComparator);
	}

	@Override
	public List<CommercePriceListChannelRel> getCommercePriceListChannelRels(
		long commercePriceListId, String name, int start, int end) {

		return commercePriceListChannelRelFinder.findByCommercePriceListId(
			commercePriceListId, name, start, end);
	}

	@Override
	public int getCommercePriceListChannelRelsCount(long commercePriceListId) {
		return commercePriceListChannelRelPersistence.
			countByCommercePriceListId(commercePriceListId);
	}

	@Override
	public int getCommercePriceListChannelRelsCount(
		long commercePriceListId, String name) {

		return commercePriceListChannelRelFinder.countByCommercePriceListId(
			commercePriceListId, name);
	}

	protected void reindexCommercePriceList(long commercePriceListId)
		throws PortalException {

		Indexer<CommercePriceList> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CommercePriceList.class);

		indexer.reindex(CommercePriceList.class.getName(), commercePriceListId);
	}

}