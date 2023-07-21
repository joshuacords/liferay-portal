/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.options.web.internal.display.context;

import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.options.web.internal.portlet.action.ActionHelper;
import com.liferay.commerce.product.options.web.internal.util.CPOptionsPortletUtil;
import com.liferay.commerce.product.service.CPOptionCategoryService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.OrderByComparator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPOptionCategoryDisplayContext
	extends BaseCPOptionsDisplayContext<CPOptionCategory> {

	public CPOptionCategoryDisplayContext(
			ActionHelper actionHelper, HttpServletRequest httpServletRequest,
			CPOptionCategoryService cpOptionCategoryService)
		throws PortalException {

		super(
			actionHelper, httpServletRequest,
			CPOptionCategory.class.getSimpleName());

		setDefaultOrderByCol("priority");

		_cpOptionCategoryService = cpOptionCategoryService;
	}

	@Override
	public SearchContainer<CPOptionCategory> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		searchContainer.setEmptyResultsMessage(
			"no-specification-groups-were-found");

		OrderByComparator<CPOptionCategory> orderByComparator =
			CPOptionsPortletUtil.getCPOptionCategoryOrderByComparator(
				getOrderByCol(), getOrderByType());

		Sort sort = CPOptionsPortletUtil.getCPOptionCategorySort(
			getOrderByCol(), getOrderByType());

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		BaseModelSearchResult<CPOptionCategory>
			cpOptionCategoryBaseModelSearchResult =
				_cpOptionCategoryService.searchCPOptionCategories(
					cpRequestHelper.getCompanyId(), getKeywords(),
					searchContainer.getStart(), searchContainer.getEnd(), sort);

		searchContainer.setResults(
			cpOptionCategoryBaseModelSearchResult.getBaseModels());
		searchContainer.setTotal(
			cpOptionCategoryBaseModelSearchResult.getLength());

		return searchContainer;
	}

	private final CPOptionCategoryService _cpOptionCategoryService;

}