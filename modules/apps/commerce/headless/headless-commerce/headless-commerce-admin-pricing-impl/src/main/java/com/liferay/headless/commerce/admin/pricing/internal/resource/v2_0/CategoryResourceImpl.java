/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.headless.commerce.admin.pricing.dto.v2_0.Category;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.DiscountCategory;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceModifierCategory;
import com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.CategoryDTOConverter;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.CategoryResource;
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
	properties = "OSGI-INF/liferay/rest/v2_0/category.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {CategoryResource.class, NestedFieldSupport.class}
)
public class CategoryResourceImpl
	extends BaseCategoryResourceImpl implements NestedFieldSupport {

	@NestedField(parentClass = DiscountCategory.class, value = "category")
	@Override
	public Category getDiscountIdCategoryPage(
			@NestedFieldId(value = "categoryId") @NotNull Long id)
		throws Exception {

		return _categoryDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				id, contextAcceptLanguage.getPreferredLocale()));
	}

	@NestedField(parentClass = PriceModifierCategory.class, value = "category")
	@Override
	public Category getPriceModifierIdCategory(
			@NestedFieldId(value = "categoryId") @NotNull Long id)
		throws Exception {

		return _categoryDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				id, contextAcceptLanguage.getPreferredLocale()));
	}

	@Reference
	private CategoryDTOConverter _categoryDTOConverter;

}