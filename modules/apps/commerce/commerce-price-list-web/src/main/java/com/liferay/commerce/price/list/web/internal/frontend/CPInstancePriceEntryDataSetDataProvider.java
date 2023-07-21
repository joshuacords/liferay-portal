/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.web.internal.frontend;

import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceEntryService;
import com.liferay.commerce.price.list.web.internal.model.InstancePriceEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.data.provider.key=" + CommercePriceListDataSetConstants.COMMERCE_DATA_SET_KEY_INSTANCE_PRICE_ENTRIES,
	service = CommerceDataSetDataProvider.class
)
public class CPInstancePriceEntryDataSetDataProvider
	implements CommerceDataSetDataProvider<InstancePriceEntry> {

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		long cpInstanceId = ParamUtil.getLong(
			httpServletRequest, "cpInstanceId");

		return _commercePriceEntryService.getInstanceCommercePriceEntriesCount(
			cpInstanceId);
	}

	@Override
	public List<InstancePriceEntry> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		List<InstancePriceEntry> instancePriceEntries = new ArrayList<>();

		long cpInstanceId = ParamUtil.getLong(
			httpServletRequest, "cpInstanceId");

		List<CommercePriceEntry> commercePriceEntries =
			_commercePriceEntryService.getInstanceCommercePriceEntries(
				cpInstanceId, pagination.getStartPosition(),
				pagination.getEndPosition());

		for (CommercePriceEntry commercePriceEntry : commercePriceEntries) {
			CommercePriceList commercePriceList =
				commercePriceEntry.getCommercePriceList();

			CommerceMoney priceMoney = commercePriceEntry.getPriceMoney(
				commercePriceList.getCommerceCurrencyId());

			Date createDate = commercePriceEntry.getCreateDate();

			String createDateDescription = LanguageUtil.getTimeDescription(
				httpServletRequest,
				System.currentTimeMillis() - createDate.getTime(), true);

			instancePriceEntries.add(
				new InstancePriceEntry(
					commercePriceEntry.getCommercePriceEntryId(),
					commercePriceList.getName(),
					HtmlUtil.escape(
						priceMoney.format(
							_portal.getLocale(httpServletRequest))),
					LanguageUtil.format(
						httpServletRequest, "x-ago", createDateDescription,
						false)));
		}

		return instancePriceEntries;
	}

	@Reference
	private CommercePriceEntryService _commercePriceEntryService;

	@Reference
	private Portal _portal;

}