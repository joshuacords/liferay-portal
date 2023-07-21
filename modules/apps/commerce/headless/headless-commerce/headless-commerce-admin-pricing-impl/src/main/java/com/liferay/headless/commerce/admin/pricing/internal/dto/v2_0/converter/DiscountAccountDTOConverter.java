/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountAccountRel;
import com.liferay.commerce.discount.service.CommerceDiscountAccountRelService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.DiscountAccount;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "model.class.name=com.liferay.commerce.discount.model.CommerceDiscountAccountRel",
	service = {DiscountAccountDTOConverter.class, DTOConverter.class}
)
public class DiscountAccountDTOConverter
	implements DTOConverter<CommerceDiscountAccountRel, DiscountAccount> {

	@Override
	public String getContentType() {
		return DiscountAccount.class.getSimpleName();
	}

	public DiscountAccount toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceDiscountAccountRel commerceDiscountAccountRel =
			_commerceDiscountAccountRelService.getCommerceDiscountAccountRel(
				(Long)dtoConverterContext.getId());

		CommerceAccount commerceAccount =
			commerceDiscountAccountRel.getCommerceAccount();
		CommerceDiscount commerceDiscount =
			commerceDiscountAccountRel.getCommerceDiscount();

		return new DiscountAccount() {
			{
				accountExternalReferenceCode =
					commerceAccount.getExternalReferenceCode();
				accountId = commerceAccount.getCommerceAccountId();
				actions = dtoConverterContext.getActions();
				discountExternalReferenceCode =
					commerceDiscount.getExternalReferenceCode();
				discountId = commerceDiscount.getCommerceDiscountId();
				id =
					commerceDiscountAccountRel.
						getCommerceDiscountAccountRelId();
			}
		};
	}

	@Reference
	private CommerceDiscountAccountRelService
		_commerceDiscountAccountRelService;

}