/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.forecast.data.integration;

import com.liferay.commerce.data.integration.service.ScheduledTaskExecutorService;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastPeriod;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastScope;
import com.liferay.commerce.machine.learning.internal.forecast.constants.CommerceMLForecastTarget;
import com.liferay.commerce.machine.learning.internal.forecast.data.integration.process.type.CommerceAccountCommerceMLForecastProcessType;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "data.integration.service.executor.key=" + CommerceAccountCommerceMLForecastProcessType.KEY,
	service = ScheduledTaskExecutorService.class
)
public class CommerceAccountCommerceMLForecastScheduledTaskExecutorService
	extends BaseForecastScheduledTaskExecutorService {

	@Override
	public String getName() {
		return CommerceAccountCommerceMLForecastProcessType.KEY;
	}

	@Override
	protected String getPeriod() {
		return _COMMERCE_ML_FORECAST_PERIOD.getLabel();
	}

	@Override
	protected String getScope() {
		return _COMMERCE_ML_FORECAST_SCOPE.getLabel();
	}

	@Override
	protected String getTarget() {
		return _COMMERCE_ML_FORECAST_TARGET.getLabel();
	}

	private static final CommerceMLForecastPeriod _COMMERCE_ML_FORECAST_PERIOD =
		CommerceMLForecastPeriod.MONTH;

	private static final CommerceMLForecastScope _COMMERCE_ML_FORECAST_SCOPE =
		CommerceMLForecastScope.COMMERCE_ACCOUNT;

	private static final CommerceMLForecastTarget _COMMERCE_ML_FORECAST_TARGET =
		CommerceMLForecastTarget.REVENUE;

}