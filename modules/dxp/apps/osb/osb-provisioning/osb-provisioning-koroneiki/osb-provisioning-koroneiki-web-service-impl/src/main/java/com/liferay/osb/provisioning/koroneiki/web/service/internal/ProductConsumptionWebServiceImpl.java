/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ProductConsumptionWebService.class
)
public class ProductConsumptionWebServiceImpl
	implements ProductConsumptionWebService {

	public ProductConsumption addProductConsumption(
			String agentName, String agentUID, String accountKey,
			ProductConsumption productConsumption)
		throws Exception {

		return _productConsumptionResource.
			postAccountAccountKeyProductConsumption(
				agentName, agentUID, accountKey, productConsumption);
	}

	public void deleteProductConsumption(
			String agentName, String agentUID, String productConsumptionKey)
		throws Exception {

		_productConsumptionResource.deleteProductConsumption(
			agentName, agentUID, productConsumptionKey);
	}

	public List<ProductConsumption> getProductConsumptions(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception {

		Page<ProductConsumption> productConsumptionsPage =
			_productConsumptionResource.
				getProductConsumptionByExternalLinkDomainEntityNameEntityPage(
					domain, entityName, entityId,
					Pagination.of(page, pageSize));

		if ((productConsumptionsPage != null) &&
			(productConsumptionsPage.getItems() != null)) {

			return new ArrayList<>(productConsumptionsPage.getItems());
		}

		return Collections.emptyList();
	}

	public List<ProductConsumption> search(
			FilterQuery filterQuery, int page, int pageSize, String sort)
		throws Exception {

		String filterString = null;

		if (filterQuery != null) {
			filterString = filterQuery.toString();
		}

		Page<ProductConsumption> productConsumptionsPage =
			_productConsumptionResource.getProductConsumptionsPage(
				StringPool.BLANK, filterString, Pagination.of(page, pageSize),
				sort);

		if ((productConsumptionsPage != null) &&
			(productConsumptionsPage.getItems() != null)) {

			return new ArrayList<>(productConsumptionsPage.getItems());
		}

		return Collections.emptyList();
	}

	public long searchCount(FilterQuery filterQuery) throws Exception {
		String filterString = null;

		if (filterQuery != null) {
			filterString = filterQuery.toString();
		}

		Page<ProductConsumption> productConsumptionsPage =
			_productConsumptionResource.getProductConsumptionsPage(
				StringPool.BLANK, filterString, Pagination.of(1, 1),
				StringPool.BLANK);

		if (productConsumptionsPage != null) {
			return productConsumptionsPage.getTotalCount();
		}

		return 0;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ProductConsumptionResource.Builder builder =
			ProductConsumptionResource.builder();

		_productConsumptionResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private ProductConsumptionResource _productConsumptionResource;

}