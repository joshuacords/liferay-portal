/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.search.FilterQuery;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface ProductPurchaseViewWebService {

	public ProductPurchaseView getProductPurchaseView(
			String accountKey, String productKey)
		throws Exception;

	public List<ProductPurchaseView> search(
			String search, FilterQuery filterQuery, int page, int pageSize,
			String sortString)
		throws Exception;

	public long searchCount(String search, FilterQuery filterQuery)
		throws Exception;

}