/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.forecast.data.integration;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLocalService;
import com.liferay.commerce.data.integration.service.ScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.data.integration.CommerceMLScheduledTaskExecutorService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
public abstract class BaseForecastScheduledTaskExecutorService
	implements ScheduledTaskExecutorService {

	@Override
	public void runProcess(long commerceDataIntegrationProcessId)
		throws IOException, PortalException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			commerceDataIntegrationProcessLocalService.
				getCommerceDataIntegrationProcess(
					commerceDataIntegrationProcessId);

		commerceMLScheduledTaskExecutorService.executeScheduledTask(
			commerceDataIntegrationProcess.getUserId(),
			commerceDataIntegrationProcess.
				getCommerceDataIntegrationProcessId(),
			getContextProperties(commerceDataIntegrationProcess));
	}

	protected Map<String, String> getContextProperties(
		CommerceDataIntegrationProcess commerceDataIntegrationProcess) {

		Map<String, String> contextProperties = new HashMap<>();

		UnicodeProperties typeSettingsProperties =
			commerceDataIntegrationProcess.getTypeSettingsProperties();

		contextProperties.put(
			"COMMERCE_ML_FORECAST_PERIOD",
			typeSettingsProperties.getProperty(
				COMMERCE_ML_FORECAST_PERIOD, getPeriod()));

		contextProperties.put("COMMERCE_ML_FORECAST_SCOPE", getScope());

		contextProperties.put(
			"COMMERCE_ML_FORECAST_TARGET",
			typeSettingsProperties.getProperty(
				COMMERCE_ML_FORECAST_TARGET, getTarget()));

		contextProperties.put("COMMERCE_ML_PROCESS_TYPE", getName());

		return contextProperties;
	}

	protected abstract String getPeriod();

	protected abstract String getScope();

	protected abstract String getTarget();

	protected static final String COMMERCE_ML_FORECAST_PERIOD =
		"commerce.ml.forecast.period";

	protected static final String COMMERCE_ML_FORECAST_TARGET =
		"commerce.ml.forecast.target";

	@Reference
	protected CommerceDataIntegrationProcessLocalService
		commerceDataIntegrationProcessLocalService;

	@Reference
	protected CommerceMLScheduledTaskExecutorService
		commerceMLScheduledTaskExecutorService;

}