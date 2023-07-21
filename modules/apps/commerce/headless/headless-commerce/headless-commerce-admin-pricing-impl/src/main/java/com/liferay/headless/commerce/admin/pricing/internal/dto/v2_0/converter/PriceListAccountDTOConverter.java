/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListAccountRel;
import com.liferay.commerce.price.list.service.CommercePriceListAccountRelService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceListAccount;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.price.list.model.CommercePriceListAccountRel",
	service = {DTOConverter.class, PriceListAccountDTOConverter.class}
)
public class PriceListAccountDTOConverter
	implements DTOConverter<CommercePriceListAccountRel, PriceListAccount> {

	@Override
	public String getContentType() {
		return PriceListAccount.class.getSimpleName();
	}

	public PriceListAccount toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommercePriceListAccountRel commercePriceListAccountRel =
			_commercePriceListAccountRelService.getCommercePriceListAccountRel(
				(Long)dtoConverterContext.getId());

		CommerceAccount commerceAccount =
			commercePriceListAccountRel.getCommerceAccount();
		CommercePriceList commercePriceList =
			commercePriceListAccountRel.getCommercePriceList();

		return new PriceListAccount() {
			{
				accountExternalReferenceCode =
					commerceAccount.getExternalReferenceCode();
				accountId = commerceAccount.getCommerceAccountId();
				actions = dtoConverterContext.getActions();
				id =
					commercePriceListAccountRel.
						getCommercePriceListAccountRelId();
				order = commercePriceListAccountRel.getOrder();
				priceListExternalReferenceCode =
					commercePriceList.getExternalReferenceCode();
				priceListId = commercePriceList.getCommercePriceListId();
			}
		};
	}

	@Reference
	private CommercePriceListAccountRelService
		_commercePriceListAccountRelService;

}