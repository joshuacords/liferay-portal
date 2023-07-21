/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.item.selector.web.internal.display.context;

import com.liferay.commerce.product.item.selector.web.internal.util.CPItemSelectorViewUtil;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.OrderByComparator;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPOptionItemSelectorViewDisplayContext
	extends BaseCPItemSelectorViewDisplayContext<CPOption> {

	public CPOptionItemSelectorViewDisplayContext(
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName, CPOptionService cpOptionService) {

		super(
			httpServletRequest, portletURL, itemSelectedEventName,
			"CPOptionItemSelectorView");

		_cpOptionService = cpOptionService;
	}

	@Override
	public SearchContainer<CPOption> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		searchContainer.setEmptyResultsMessage("no-options-were-found");

		OrderByComparator<CPOption> orderByComparator =
			CPItemSelectorViewUtil.getCPOptionOrderByComparator(
				getOrderByCol(), getOrderByType());

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		Sort sort = CPItemSelectorViewUtil.getCPOptionSort(
			getOrderByCol(), getOrderByType());

		BaseModelSearchResult<CPOption> cpOptionBaseModelSearchResult =
			_cpOptionService.searchCPOptions(
				cpRequestHelper.getCompanyId(), getKeywords(),
				searchContainer.getStart(), searchContainer.getEnd(), sort);

		searchContainer.setTotal(cpOptionBaseModelSearchResult.getLength());
		searchContainer.setResults(
			cpOptionBaseModelSearchResult.getBaseModels());

		return searchContainer;
	}

	private final CPOptionService _cpOptionService;

}