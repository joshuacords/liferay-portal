/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.internal.discovery;

import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.price.list.discovery.CommercePriceListDiscovery;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "commerce.price.list.discovery.key=" + CommercePricingConstants.ORDER_BY_HIERARCHY,
	service = CommercePriceListDiscovery.class
)
public class CommercePriceListHierarchyDiscoveryImpl
	implements CommercePriceListDiscovery {

	@Override
	public CommercePriceList getCommercePriceList(
			long groupId, long commerceAccountId, long commerceChannelId,
			String cPInstanceUuid, String commercePriceListType)
		throws PortalException {

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountAndChannelId(
					groupId, commercePriceListType, commerceAccountId,
					commerceChannelId);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByAccountId(
				groupId, commercePriceListType, commerceAccountId);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		long[] commerceAccountGroupIds =
			_commerceAccountHelper.getCommerceAccountGroupIds(
				commerceAccountId);

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountGroupsAndChannelId(
					groupId, commercePriceListType, commerceAccountGroupIds,
					commerceChannelId);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountGroupIds(
					groupId, commercePriceListType, commerceAccountGroupIds);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByChannelId(
				groupId, commercePriceListType, commerceChannelId);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByUnqualified(
				groupId, commercePriceListType);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		return null;
	}

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

}