/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.options.web.internal.display.context;

import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.options.web.internal.portlet.action.ActionHelper;
import com.liferay.commerce.product.options.web.internal.util.CPOptionsPortletUtil;
import com.liferay.commerce.product.service.CPOptionCategoryService;
import com.liferay.commerce.product.service.CPSpecificationOptionService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrea Di Giorgi
 */
public class CPSpecificationOptionDisplayContext
	extends BaseCPOptionsDisplayContext<CPSpecificationOption> {

	public CPSpecificationOptionDisplayContext(
			ActionHelper actionHelper, HttpServletRequest httpServletRequest,
			CPOptionCategoryService cpOptionCategoryService,
			CPSpecificationOptionService cpSpecificationOptionService)
		throws PortalException {

		super(
			actionHelper, httpServletRequest,
			CPSpecificationOption.class.getSimpleName());

		setDefaultOrderByCol("label");

		_cpOptionCategoryService = cpOptionCategoryService;
		_cpSpecificationOptionService = cpSpecificationOptionService;
	}

	public List<CPOptionCategory> getCPOptionCategories()
		throws PortalException {

		BaseModelSearchResult<CPOptionCategory>
			cpOptionCategoryBaseModelSearchResult =
				_cpOptionCategoryService.searchCPOptionCategories(
					cpRequestHelper.getCompanyId(), null, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

		return cpOptionCategoryBaseModelSearchResult.getBaseModels();
	}

	public String getCPOptionCategoryTitle(
			CPSpecificationOption cpSpecificationOption)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		CPOptionCategory cpOptionCategory =
			_cpOptionCategoryService.fetchCPOptionCategory(
				cpSpecificationOption.getCPOptionCategoryId());

		if (cpOptionCategory != null) {
			return cpOptionCategory.getTitle(themeDisplay.getLocale());
		}

		return StringPool.BLANK;
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter("navigation", getNavigation());

		return portletURL;
	}

	@Override
	public SearchContainer<CPSpecificationOption> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		searchContainer.setEmptyResultsMessage(
			"no-specification-labels-were-found");

		OrderByComparator<CPSpecificationOption> orderByComparator =
			CPOptionsPortletUtil.getCPSpecificationOptionOrderByComparator(
				getOrderByCol(), getOrderByType());

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		Sort sort = CPOptionsPortletUtil.getCPSpecificationOptionSort(
			getOrderByCol(), getOrderByType());

		Boolean facetable = null;

		String navigation = getNavigation();

		if (navigation.equals("no")) {
			facetable = false;
		}
		else if (navigation.equals("yes")) {
			facetable = true;
		}

		BaseModelSearchResult<CPSpecificationOption>
			cpSpecificationOptionBaseModelSearchResult =
				_cpSpecificationOptionService.searchCPSpecificationOptions(
					cpRequestHelper.getCompanyId(), facetable, getKeywords(),
					searchContainer.getStart(), searchContainer.getEnd(), sort);

		searchContainer.setTotal(
			cpSpecificationOptionBaseModelSearchResult.getLength());
		searchContainer.setResults(
			cpSpecificationOptionBaseModelSearchResult.getBaseModels());

		return searchContainer;
	}

	protected String getNavigation() {
		return ParamUtil.getString(httpServletRequest, "navigation");
	}

	private final CPOptionCategoryService _cpOptionCategoryService;
	private final CPSpecificationOptionService _cpSpecificationOptionService;

}