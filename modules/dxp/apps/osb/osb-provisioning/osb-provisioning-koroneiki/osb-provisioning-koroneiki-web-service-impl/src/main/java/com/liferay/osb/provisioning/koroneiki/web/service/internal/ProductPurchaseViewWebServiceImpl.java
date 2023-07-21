/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ProductPurchaseViewWebService.class
)
public class ProductPurchaseViewWebServiceImpl
	implements ProductPurchaseViewWebService {

	public ProductPurchaseView getProductPurchaseView(
			String accountKey, String productKey)
		throws Exception {

		return _productPurchaseViewResource.
			getAccountAccountKeyProductProductKeyProductPurchaseView(
				accountKey, productKey);
	}

	public List<ProductPurchaseView> search(
			String search, FilterQuery filterQuery, int page, int pageSize,
			String sortString)
		throws Exception {

		try {
			String filterString = null;

			if (filterQuery != null) {
				filterString = filterQuery.toString();
			}

			Page<ProductPurchaseView> productPurchaseViewsPage =
				_productPurchaseViewResource.getProductPurchaseViewsPage(
					search, filterString, Pagination.of(page, pageSize),
					sortString);

			if ((productPurchaseViewsPage != null) &&
				(productPurchaseViewsPage.getItems() != null)) {

				return new ArrayList<>(productPurchaseViewsPage.getItems());
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return Collections.emptyList();
	}

	public long searchCount(String search, FilterQuery filterQuery)
		throws Exception {

		try {
			String filterString = null;

			if (filterQuery != null) {
				filterString = filterQuery.toString();
			}

			Page<ProductPurchaseView> productPurchaseViewsPage =
				_productPurchaseViewResource.getProductPurchaseViewsPage(
					search, filterString, Pagination.of(1, 1),
					StringPool.BLANK);

			if (productPurchaseViewsPage != null) {
				return productPurchaseViewsPage.getTotalCount();
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return 0;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ProductPurchaseViewResource.Builder builder =
			ProductPurchaseViewResource.builder();

		_productPurchaseViewResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductPurchaseViewWebServiceImpl.class);

	private ProductPurchaseViewResource _productPurchaseViewResource;

}