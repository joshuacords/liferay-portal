/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.machine.learning.internal.resource.v1_0;

import com.liferay.commerce.machine.learning.forecast.model.AssetCategoryCommerceMLForecast;
import com.liferay.commerce.machine.learning.forecast.service.AssetCategoryCommerceMLForecastService;
import com.liferay.headless.commerce.machine.learning.dto.v1_0.AccountCategoryForecast;
import com.liferay.headless.commerce.machine.learning.internal.constants.CommerceMLForecastConstants;
import com.liferay.headless.commerce.machine.learning.internal.dto.v1_0.converter.AccountCategoryForecastDTOConverter;
import com.liferay.headless.commerce.machine.learning.internal.dto.v1_0.converter.CommerceMLForecastCompositeResourcePrimaryKey;
import com.liferay.headless.commerce.machine.learning.internal.util.v1_0.CommerceAccountPermissionHelper;
import com.liferay.headless.commerce.machine.learning.resource.v1_0.AccountCategoryForecastResource;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Ferrari
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account-category-forecast.properties",
	scope = ServiceScope.PROTOTYPE,
	service = AccountCategoryForecastResource.class
)
public class AccountCategoryForecastResourceImpl
	extends BaseAccountCategoryForecastResourceImpl {

	@Override
	public Page<AccountCategoryForecast>
			getAccountCategoryForecastsByMonthlyRevenuePage(
				Long[] accountIds, Long[] categoryIds, Integer forecastLength,
				Date forecastStartDate, Integer historyLength,
				Pagination pagination)
		throws Exception {

		List<Long> commerceAccountIds =
			_commerceAccountPermissionHelper.filterCommerceAccountIds(
				Arrays.asList(accountIds));

		if (commerceAccountIds.isEmpty()) {
			return Page.of(Collections.emptyList());
		}

		Date startDate = forecastStartDate;

		if (startDate == null) {
			startDate = new Date();
		}

		if (historyLength == null) {
			historyLength = CommerceMLForecastConstants.HISTORY_LENGTH_DEFAULT;
		}

		if (forecastLength == null) {
			forecastLength =
				CommerceMLForecastConstants.FORECAST_LENGTH_DEFAULT;
		}

		long[] assetCategoryIds = ArrayUtil.toArray(categoryIds);

		List<AssetCategoryCommerceMLForecast> assetCategoryCommerceMLForecasts =
			_assetCategoryCommerceMLForecastService.
				getMonthlyRevenueAssetCategoryCommerceMLForecasts(
					contextCompany.getCompanyId(), assetCategoryIds,
					ArrayUtil.toLongArray(commerceAccountIds), startDate,
					historyLength, forecastLength,
					pagination.getStartPosition(), pagination.getEndPosition());

		long totalItems =
			_assetCategoryCommerceMLForecastService.
				getMonthlyRevenueAssetCategoryCommerceMLForecastsCount(
					contextCompany.getCompanyId(), assetCategoryIds,
					ArrayUtil.toLongArray(commerceAccountIds), startDate,
					historyLength, forecastLength);

		return Page.of(
			_toAccountCategoryForecasts(assetCategoryCommerceMLForecasts),
			pagination, totalItems);
	}

	private List<AccountCategoryForecast> _toAccountCategoryForecasts(
			List<AssetCategoryCommerceMLForecast>
				commerceAccountCommerceMLForecasts)
		throws Exception {

		List<AccountCategoryForecast> accountForecasts = new ArrayList<>();

		for (AssetCategoryCommerceMLForecast assetCategoryCommerceMLForecast :
				commerceAccountCommerceMLForecasts) {

			CommerceMLForecastCompositeResourcePrimaryKey
				commerceMLForecastCompositeResourcePrimaryKey =
					new CommerceMLForecastCompositeResourcePrimaryKey(
						assetCategoryCommerceMLForecast.getCompanyId(),
						assetCategoryCommerceMLForecast.getForecastId());

			accountForecasts.add(
				_accountCategoryForecastDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						commerceMLForecastCompositeResourcePrimaryKey,
						contextAcceptLanguage.getPreferredLocale())));
		}

		return accountForecasts;
	}

	@Reference
	private AccountCategoryForecastDTOConverter
		_accountCategoryForecastDTOConverter;

	@Reference
	private AssetCategoryCommerceMLForecastService
		_assetCategoryCommerceMLForecastService;

	@Reference
	private CommerceAccountPermissionHelper _commerceAccountPermissionHelper;

}