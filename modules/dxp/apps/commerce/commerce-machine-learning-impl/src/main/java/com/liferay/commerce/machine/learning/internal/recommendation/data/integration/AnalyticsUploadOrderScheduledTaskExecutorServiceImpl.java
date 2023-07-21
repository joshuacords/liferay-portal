/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.recommendation.data.integration;

import com.liferay.commerce.data.integration.service.ScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.data.integration.AnalyticsCommerceMLScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.data.integration.BatchEngineTaskItemDelegateResourceMapper;
import com.liferay.commerce.machine.learning.internal.recommendation.data.integration.process.type.AnalyticsUploadOrderProcessType;
import com.liferay.commerce.machine.learning.internal.recommendation.data.integration.process.type.AnalyticsUploadProductProcessType;
import com.liferay.headless.commerce.admin.order.constants.v1_0.OrderBatchEngineTaskItemDelegateConstants;
import com.liferay.headless.commerce.admin.order.dto.v1_0.Order;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "data.integration.service.executor.key=" + AnalyticsUploadOrderProcessType.KEY,
	service = ScheduledTaskExecutorService.class
)
public class AnalyticsUploadOrderScheduledTaskExecutorServiceImpl
	implements ScheduledTaskExecutorService {

	@Override
	public String getName() {
		return AnalyticsUploadProductProcessType.KEY;
	}

	@Override
	public void runProcess(long commerceDataIntegrationProcessId)
		throws IOException, PortalException {

		_analyticsCommerceMLScheduledTaskExecutorService.uploadResources(
			commerceDataIntegrationProcessId, _EXPORT_RESOURCE_NAMES);
	}

	private static final BatchEngineTaskItemDelegateResourceMapper[]
		_EXPORT_RESOURCE_NAMES = {
			new BatchEngineTaskItemDelegateResourceMapper(
				Order.class.getName(), null,
				OrderBatchEngineTaskItemDelegateConstants.COMMERCE_ML_ORDER)
		};

	@Reference
	private AnalyticsCommerceMLScheduledTaskExecutorService
		_analyticsCommerceMLScheduledTaskExecutorService;

}