/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.util.v1_0;

import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.service.CPDefinitionSpecificationOptionValueService;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSpecification;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class ProductSpecificationUtil {

	public static CPDefinitionSpecificationOptionValue
			addCPDefinitionSpecificationOptionValue(
				CPDefinitionSpecificationOptionValueService
					cpDefinitionSpecificationOptionValueService,
				CPSpecificationOptionService cpSpecificationOptionService,
				long cpDefinitionId, ProductSpecification productSpecification,
				ServiceContext serviceContext)
		throws Exception {

		return cpDefinitionSpecificationOptionValueService.
			addCPDefinitionSpecificationOptionValue(
				cpDefinitionId,
				getCPSpecificationOptionId(
					cpSpecificationOptionService, productSpecification,
					serviceContext.getCompanyId(), serviceContext),
				getCPOptionCategoryId(productSpecification),
				LanguageUtils.getLocalizedMap(productSpecification.getValue()),
				GetterUtil.get(productSpecification.getPriority(), 0D),
				serviceContext);
	}

	public static long getCPOptionCategoryId(
		ProductSpecification productSpecification) {

		if (productSpecification.getOptionCategoryId() == null) {
			return 0;
		}

		return productSpecification.getOptionCategoryId();
	}

	public static long getCPSpecificationOptionId(
			CPSpecificationOptionService cpSpecificationOptionService,
			ProductSpecification productSpecification, long companyId,
			ServiceContext serviceContext)
		throws PortalException {

		CPSpecificationOption cpSpecificationOption =
			cpSpecificationOptionService.fetchCPSpecificationOption(
				companyId,
				StringUtil.toLowerCase(
					productSpecification.getSpecificationKey()));

		if (cpSpecificationOption == null) {
			cpSpecificationOption =
				cpSpecificationOptionService.addCPSpecificationOption(
					getCPOptionCategoryId(productSpecification),
					LanguageUtils.getLocalizedMap(
						productSpecification.getValue()),
					LanguageUtils.getLocalizedMap(
						productSpecification.getValue()),
					false,
					StringUtil.toLowerCase(
						productSpecification.getSpecificationKey()),
					serviceContext);
		}

		return cpSpecificationOption.getCPSpecificationOptionId();
	}

	public static CPDefinitionSpecificationOptionValue
			updateCPDefinitionSpecificationOptionValue(
				CPDefinitionSpecificationOptionValueService
					cpDefinitionSpecificationOptionValueService,
				CPDefinitionSpecificationOptionValue
					cpDefinitionSpecificationOptionValue,
				ProductSpecification productSpecification,
				ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionSpecificationOptionValueService.
			updateCPDefinitionSpecificationOptionValue(
				cpDefinitionSpecificationOptionValue.
					getCPDefinitionSpecificationOptionValueId(),
				getCPOptionCategoryId(productSpecification),
				LanguageUtils.getLocalizedMap(productSpecification.getValue()),
				GetterUtil.get(
					productSpecification.getPriority(),
					cpDefinitionSpecificationOptionValue.getPriority()),
				serviceContext);
	}

}