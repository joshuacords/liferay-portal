/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.search.FilterQuery;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface ProductWebService {

	public Product addProduct(
			String agentName, String agentUID, Product product)
		throws Exception;

	public void deleteProduct(
			String agentName, String agentUID, String productKey)
		throws Exception;

	public Product fetchProduct(String productKey) throws Exception;

	public Product fetchProductByName(String name) throws Exception;

	public Product getProduct(String productKey) throws Exception;

	public List<Product> getProducts(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception;

	public List<Product> search(
			String search, FilterQuery filterQuery, int page, int pageSize,
			String sortString)
		throws Exception;

	public long searchCount(String search, FilterQuery filterQuery)
		throws Exception;

	public Product updateProduct(
			String agentName, String agentUID, String productKey,
			Product product)
		throws Exception;

}