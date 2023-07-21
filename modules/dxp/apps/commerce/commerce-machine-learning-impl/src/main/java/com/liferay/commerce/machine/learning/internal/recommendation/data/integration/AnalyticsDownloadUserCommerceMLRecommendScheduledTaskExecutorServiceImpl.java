/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.recommendation.data.integration;

import com.liferay.commerce.data.integration.service.ScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.data.integration.AnalyticsCommerceMLScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.data.integration.BatchEngineTaskItemDelegateResourceMapper;
import com.liferay.commerce.machine.learning.internal.recommendation.data.integration.process.type.AnalyticsDownloadUserCommerceMLRecommendationProcessType;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.ProductInteractionRecommendation;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.UserRecommendation;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "data.integration.service.executor.key=" + AnalyticsDownloadUserCommerceMLRecommendationProcessType.KEY,
	service = ScheduledTaskExecutorService.class
)
public class
	AnalyticsDownloadUserCommerceMLRecommendScheduledTaskExecutorServiceImpl
		implements ScheduledTaskExecutorService {

	@Override
	public String getName() {
		return AnalyticsDownloadUserCommerceMLRecommendationProcessType.KEY;
	}

	@Override
	public void runProcess(long commerceDataIntegrationProcessId)
		throws IOException, PortalException {

		List<BatchEngineTaskItemDelegateResourceMapper> importResources =
			new ArrayList<>();

		Map<String, String> userRecommendationFieldMappings = new HashMap<>();

		userRecommendationFieldMappings.put(
			"assetCategoryIds", "assetCategoryIds");
		userRecommendationFieldMappings.put("createDate", "createDate");
		userRecommendationFieldMappings.put("entryClassPK", "productId");
		userRecommendationFieldMappings.put("jobId", "jobId");
		userRecommendationFieldMappings.put(
			"recommendedEntryClassPK", "recommendedProductId");
		userRecommendationFieldMappings.put("score", "score");

		importResources.add(
			new BatchEngineTaskItemDelegateResourceMapper(
				UserRecommendation.class.getName(),
				userRecommendationFieldMappings, null));

		Map<String, String> productInteractionFieldMappings = new HashMap<>();

		productInteractionFieldMappings.put("createDate", "createDate");
		productInteractionFieldMappings.put("entryClassPK", "productId");
		productInteractionFieldMappings.put("jobId", "jobId");
		productInteractionFieldMappings.put("rank", "rank");
		productInteractionFieldMappings.put(
			"recommendedEntryClassPK", "recommendedProductId");
		productInteractionFieldMappings.put("score", "score");

		importResources.add(
			new BatchEngineTaskItemDelegateResourceMapper(
				ProductInteractionRecommendation.class.getName(),
				productInteractionFieldMappings, null));

		_analyticsCommerceMLScheduledTaskExecutorService.downloadResources(
			commerceDataIntegrationProcessId,
			importResources.toArray(
				new BatchEngineTaskItemDelegateResourceMapper[0]));
	}

	@Reference
	private AnalyticsCommerceMLScheduledTaskExecutorService
		_analyticsCommerceMLScheduledTaskExecutorService;

}