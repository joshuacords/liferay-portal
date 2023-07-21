/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.item.selector.web.internal.display.context;

import com.liferay.commerce.item.selector.web.internal.search.CommerceCountryItemSelectorChecker;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.util.CommerceUtil;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceCountryItemSelectorViewDisplayContext
	extends BaseCommerceItemSelectorViewDisplayContext<CommerceCountry> {

	public CommerceCountryItemSelectorViewDisplayContext(
		CommerceCountryService commerceCountryService,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName) {

		super(httpServletRequest, portletURL, itemSelectedEventName);

		_commerceCountryService = commerceCountryService;

		setDefaultOrderByCol("priority");
		setDefaultOrderByType("asc");
	}

	@Override
	public PortletURL getPortletURL() {
		PortletURL portletURL = super.getPortletURL();

		String checkedCommerceCountryIds = StringUtil.merge(
			getCheckedCommerceCountryIds());

		portletURL.setParameter(
			"checkedCommerceCountryIds", checkedCommerceCountryIds);

		return portletURL;
	}

	public SearchContainer<CommerceCountry> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		searchContainer = new SearchContainer<>(
			cpRequestHelper.getRenderRequest(), getPortletURL(), null, null);

		searchContainer.setEmptyResultsMessage("there-are-no-countries");

		searchContainer.setOrderByCol(getOrderByCol());

		OrderByComparator<CommerceCountry> orderByComparator =
			CommerceUtil.getCommerceCountryOrderByComparator(
				getOrderByCol(), getOrderByType());

		searchContainer.setOrderByComparator(orderByComparator);

		searchContainer.setOrderByType(getOrderByType());

		RowChecker rowChecker = new CommerceCountryItemSelectorChecker(
			cpRequestHelper.getRenderResponse(),
			getCheckedCommerceCountryIds());

		searchContainer.setRowChecker(rowChecker);

		List<CommerceCountry> results =
			_commerceCountryService.getCommerceCountries(
				themeDisplay.getCompanyId(), true, searchContainer.getStart(),
				searchContainer.getEnd(), orderByComparator);

		searchContainer.setResults(results);

		int total = _commerceCountryService.getCommerceCountriesCount(
			themeDisplay.getCompanyId());

		searchContainer.setTotal(total);

		return searchContainer;
	}

	protected long[] getCheckedCommerceCountryIds() {
		return ParamUtil.getLongValues(
			cpRequestHelper.getRenderRequest(), "checkedCommerceCountryIds");
	}

	private final CommerceCountryService _commerceCountryService;

}