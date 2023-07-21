/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.bom.internal.resource.v1_0;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.headless.commerce.bom.dto.v1_0.Product;
import com.liferay.headless.commerce.bom.internal.dto.v1_0.converter.ProductDTOConverter;
import com.liferay.headless.commerce.bom.resource.v1_0.ProductResource;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductResource.class
)
public class ProductResourceImpl extends BaseProductResourceImpl {

	@Override
	public Page<Product> getProductsPage(String q) throws Exception {
		BaseModelSearchResult<CPInstance> cpInstanceBaseModelSearchResult =
			_cpInstanceService.searchCPInstances(
				contextCompany.getCompanyId(), q,
				WorkflowConstants.STATUS_APPROVED, 0, 20, null);

		return Page.of(
			_toProducts(cpInstanceBaseModelSearchResult.getBaseModels()), null,
			cpInstanceBaseModelSearchResult.getLength());
	}

	private List<Product> _toProducts(List<CPInstance> cpInstances)
		throws Exception {

		List<Product> products = new ArrayList<>();

		for (CPInstance cpInstance : cpInstances) {
			products.add(
				_productDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						cpInstance.getCPInstanceId(),
						contextAcceptLanguage.getPreferredLocale())));
		}

		return products;
	}

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private ProductDTOConverter _productDTOConverter;

}