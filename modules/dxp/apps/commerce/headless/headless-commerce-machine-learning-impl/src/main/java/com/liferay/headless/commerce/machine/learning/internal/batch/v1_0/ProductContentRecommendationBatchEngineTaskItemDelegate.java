/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.machine.learning.internal.batch.v1_0;

import com.liferay.batch.engine.BaseBatchEngineTaskItemDelegate;
import com.liferay.batch.engine.BatchEngineTaskItemDelegate;
import com.liferay.batch.engine.pagination.Page;
import com.liferay.batch.engine.pagination.Pagination;
import com.liferay.commerce.machine.learning.recommendation.model.ProductContentCommerceMLRecommendation;
import com.liferay.commerce.machine.learning.recommendation.service.ProductContentCommerceMLRecommendationService;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.ProductContentRecommendation;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = BatchEngineTaskItemDelegate.class)
public class ProductContentRecommendationBatchEngineTaskItemDelegate
	extends BaseBatchEngineTaskItemDelegate<ProductContentRecommendation> {

	@Override
	public void createItem(
			ProductContentRecommendation item,
			Map<String, Serializable> parameters)
		throws Exception {

		ProductContentCommerceMLRecommendation
			productContentCommerceMLRecommendation =
				_productContentCommerceMLRecommendationService.create();

		productContentCommerceMLRecommendation.setCompanyId(
			contextCompany.getCompanyId());
		productContentCommerceMLRecommendation.setCreateDate(
			item.getCreateDate());
		productContentCommerceMLRecommendation.setEntryClassPK(
			item.getProductId());
		productContentCommerceMLRecommendation.setJobId(item.getJobId());
		productContentCommerceMLRecommendation.setRank(item.getRank());
		productContentCommerceMLRecommendation.setRecommendedEntryClassPK(
			item.getRecommendedProductId());
		productContentCommerceMLRecommendation.setScore(item.getScore());

		_productContentCommerceMLRecommendationService.
			addProductContentCommerceMLRecommendation(
				productContentCommerceMLRecommendation);
	}

	@Override
	public Page<ProductContentRecommendation> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		return null;
	}

	@Reference
	private ProductContentCommerceMLRecommendationService
		_productContentCommerceMLRecommendationService;

}