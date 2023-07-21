/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.dto.v1_0.converter;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.commerce.discount.service.CommerceDiscountCommerceAccountGroupRelService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.DiscountAccountGroup;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel",
	service = {DiscountAccountGroupDTOConverter.class, DTOConverter.class}
)
public class DiscountAccountGroupDTOConverter
	implements DTOConverter
		<CommerceDiscountCommerceAccountGroupRel, DiscountAccountGroup> {

	@Override
	public String getContentType() {
		return DiscountAccountGroup.class.getSimpleName();
	}

	public DiscountAccountGroup toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				_commerceDiscountCommerceAccountGroupRelService.
					getCommerceDiscountCommerceAccountGroupRel(
						(Long)dtoConverterContext.getId());

		CommerceAccountGroup commerceAccountGroup =
			commerceDiscountCommerceAccountGroupRel.getCommerceAccountGroup();
		CommerceDiscount commerceDiscount =
			commerceDiscountCommerceAccountGroupRel.getCommerceDiscount();

		return new DiscountAccountGroup() {
			{
				accountGroupExternalReferenceCode =
					commerceAccountGroup.getExternalReferenceCode();
				accountGroupId =
					commerceAccountGroup.getCommerceAccountGroupId();
				discountExternalReferenceCode =
					commerceDiscount.getExternalReferenceCode();
				discountId = commerceDiscount.getCommerceDiscountId();
				id =
					commerceDiscountCommerceAccountGroupRel.
						getCommerceDiscountCommerceAccountGroupRelId();
			}
		};
	}

	@Reference
	private CommerceDiscountCommerceAccountGroupRelService
		_commerceDiscountCommerceAccountGroupRelService;

}