/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.headless.commerce.admin.pricing.dto.v2_0.DiscountProductGroup;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceModifierProductGroup;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.ProductGroup;
import com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.ProductGroupDTOConverter;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.ProductGroupResource;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;

import javax.validation.constraints.NotNull;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v2_0/product-group.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, ProductGroupResource.class}
)
public class ProductGroupResourceImpl
	extends BaseProductGroupResourceImpl implements NestedFieldSupport {

	@NestedField(
		parentClass = DiscountProductGroup.class, value = "productGroup"
	)
	@Override
	public ProductGroup getDiscountIdProductGroupPage(
			@NestedFieldId(value = "productGroupId") @NotNull Long id)
		throws Exception {

		return _productGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				id, contextAcceptLanguage.getPreferredLocale()));
	}

	@NestedField(
		parentClass = PriceModifierProductGroup.class, value = "productGroup"
	)
	@Override
	public ProductGroup getPriceModifierIdProductGroup(
			@NestedFieldId(value = "productGroupId") @NotNull Long id)
		throws Exception {

		return _productGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				id, contextAcceptLanguage.getPreferredLocale()));
	}

	@Reference
	private ProductGroupDTOConverter _productGroupDTOConverter;

}