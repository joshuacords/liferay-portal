/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.dto.v1_0.converter;

import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.commerce.pricing.service.CommercePricingClassCPDefinitionRelService;
import com.liferay.commerce.pricing.service.CommercePricingClassService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductGroup;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "model.class.name=com.liferay.commerce.pricing.model.CommercePricingClass",
	service = {DTOConverter.class, ProductGroupDTOConverter.class}
)
public class ProductGroupDTOConverter
	implements DTOConverter<CommercePricingClass, ProductGroup> {

	@Override
	public String getContentType() {
		return ProductGroup.class.getSimpleName();
	}

	public ProductGroup toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommercePricingClass commercePricingClass =
			_commercePricingClassService.getCommercePricingClass(
				(Long)dtoConverterContext.getId());

		ExpandoBridge expandoBridge = commercePricingClass.getExpandoBridge();

		return new ProductGroup() {
			{
				customFields = expandoBridge.getAttributes();
				description = LanguageUtils.getLanguageIdMap(
					commercePricingClass.getDescriptionMap());
				externalReferenceCode =
					commercePricingClass.getExternalReferenceCode();
				id = commercePricingClass.getCommercePricingClassId();
				productsCount = _getProductsCount(
					commercePricingClass.getCommercePricingClassId());
				title = LanguageUtils.getLanguageIdMap(
					commercePricingClass.getTitleMap());
			}
		};
	}

	private int _getProductsCount(long commercePricingClassId)
		throws PortalException {

		return _commercePricingClassCPDefinitionRelService.
			getCommercePricingClassCPDefinitionRelsCount(
				commercePricingClassId);
	}

	@Reference
	private CommercePricingClassCPDefinitionRelService
		_commercePricingClassCPDefinitionRelService;

	@Reference
	private CommercePricingClassService _commercePricingClassService;

}