/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.bom.internal.dto.v1_0.converter;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.product.url.CPFriendlyURL;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.headless.commerce.bom.dto.v1_0.Product;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=commerceProductInstance",
	service = {DTOConverter.class, ProductDTOConverter.class}
)
public class ProductDTOConverter implements DTOConverter<CPInstance, Product> {

	@Override
	public String getContentType() {
		return Product.class.getSimpleName();
	}

	public Product toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CPInstance cpInstance = _cpInstanceService.getCPInstance(
			(Long)dtoConverterContext.getId());

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		return new Product() {
			{
				id = cpInstance.getCPInstanceUuid();
				name = cpDefinition.getName(
					LocaleUtil.toLanguageId(dtoConverterContext.getLocale()));
				sku = cpInstance.getSku();
				thumbnailUrl = _cpInstanceHelper.getCPInstanceThumbnailSrc(
					cpInstance.getCPInstanceId());

				String cpDefinitionURL = cpDefinition.getURL(
					LocaleUtil.toLanguageId(dtoConverterContext.getLocale()));

				String productURLSeparator =
					_cpFriendlyURL.getProductURLSeparator(
						cpInstance.getCompanyId());

				url = productURLSeparator + cpDefinitionURL;
			}
		};
	}

	@Reference
	private CPFriendlyURL _cpFriendlyURL;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

	@Reference
	private CPInstanceService _cpInstanceService;

}