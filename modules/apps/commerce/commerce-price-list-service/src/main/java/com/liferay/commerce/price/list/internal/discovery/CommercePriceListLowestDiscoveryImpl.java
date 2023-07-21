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
	property = "commerce.price.list.discovery.key=" + CommercePricingConstants.ORDER_BY_LOWEST_ENTRY,
	service = CommercePriceListDiscovery.class
)
public class CommercePriceListLowestDiscoveryImpl
	implements CommercePriceListDiscovery {

	@Override
	public CommercePriceList getCommercePriceList(
			long groupId, long commerceAccountId, long commerceChannelId,
			String cPInstanceUuid, String commercePriceListType)
		throws PortalException {

		return _commercePriceListLocalService.getCommercePriceListByLowestPrice(
			groupId, commercePriceListType, cPInstanceUuid, commerceAccountId,
			_commerceAccountHelper.getCommerceAccountGroupIds(
				commerceAccountId),
			commerceChannelId);
	}

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

}