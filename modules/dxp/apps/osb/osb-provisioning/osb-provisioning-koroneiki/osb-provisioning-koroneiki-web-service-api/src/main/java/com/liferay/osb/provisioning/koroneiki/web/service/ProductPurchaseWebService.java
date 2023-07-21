/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.search.FilterQuery;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface ProductPurchaseWebService {

	public ProductPurchase addProductPurchase(
			String agentName, String agentUID, String accountKey,
			ProductPurchase productPurchase)
		throws Exception;

	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws Exception;

	public List<ProductPurchase> search(
			FilterQuery filterQuery, int page, int pageSize, String sortString)
		throws Exception;

	public long searchCount(FilterQuery filterQuery) throws Exception;

	public ProductPurchase updateProductPurchase(
			String agentName, String agentUID, String productPurchaseKey,
			ProductPurchase productPurchase)
		throws Exception;

}