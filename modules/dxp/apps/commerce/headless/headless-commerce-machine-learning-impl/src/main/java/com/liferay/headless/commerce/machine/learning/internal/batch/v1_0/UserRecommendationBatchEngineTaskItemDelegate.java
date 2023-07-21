/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.machine.learning.internal.batch.v1_0;

import com.liferay.batch.engine.BaseBatchEngineTaskItemDelegate;
import com.liferay.batch.engine.BatchEngineTaskItemDelegate;
import com.liferay.batch.engine.pagination.Page;
import com.liferay.batch.engine.pagination.Pagination;
import com.liferay.commerce.machine.learning.recommendation.model.UserCommerceMLRecommendation;
import com.liferay.commerce.machine.learning.recommendation.service.UserCommerceMLRecommendationService;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.UserRecommendation;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = BatchEngineTaskItemDelegate.class)
public class UserRecommendationBatchEngineTaskItemDelegate
	extends BaseBatchEngineTaskItemDelegate<UserRecommendation> {

	@Override
	public void createItem(
			UserRecommendation item, Map<String, Serializable> parameters)
		throws Exception {

		UserCommerceMLRecommendation userCommerceMLRecommendation =
			_userCommerceMLRecommendationService.create();

		userCommerceMLRecommendation.setAssetCategoryIds(
			ArrayUtil.toArray(item.getAssetCategoryIds()));
		userCommerceMLRecommendation.setCompanyId(
			contextCompany.getCompanyId());
		userCommerceMLRecommendation.setCreateDate(item.getCreateDate());
		userCommerceMLRecommendation.setEntryClassPK(item.getProductId());
		userCommerceMLRecommendation.setJobId(item.getJobId());
		userCommerceMLRecommendation.setRecommendedEntryClassPK(
			item.getRecommendedProductId());
		userCommerceMLRecommendation.setScore(item.getScore());

		_userCommerceMLRecommendationService.addUserCommerceMLRecommendation(
			userCommerceMLRecommendation);
	}

	@Override
	public Page<UserRecommendation> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		return null;
	}

	@Reference
	private UserCommerceMLRecommendationService
		_userCommerceMLRecommendationService;

}