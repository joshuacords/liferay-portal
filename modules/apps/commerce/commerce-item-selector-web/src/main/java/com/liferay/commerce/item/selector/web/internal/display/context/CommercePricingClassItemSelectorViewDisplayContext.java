/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.item.selector.web.internal.display.context;

import com.liferay.commerce.item.selector.web.internal.search.CommercePricingClassItemSelectorChecker;
import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.commerce.pricing.service.CommercePricingClassService;
import com.liferay.commerce.pricing.util.comparator.CommercePricingClassCreateDateComparator;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class CommercePricingClassItemSelectorViewDisplayContext
	extends BaseCommerceItemSelectorViewDisplayContext<CommercePricingClass> {

	public CommercePricingClassItemSelectorViewDisplayContext(
		CommercePricingClassService commercePricingClassService,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName) {

		super(httpServletRequest, portletURL, itemSelectedEventName);

		_commercePricingClassService = commercePricingClassService;

		setDefaultOrderByCol("create-date");
		setDefaultOrderByType("desc");
	}

	public SearchContainer<CommercePricingClass> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		searchContainer = new SearchContainer<>(
			cpRequestHelper.getRenderRequest(), getPortletURL(), null, null);

		searchContainer.setEmptyResultsMessage("there-are-no-product-groups");

		searchContainer.setOrderByCol(getOrderByCol());

		OrderByComparator<CommercePricingClass> orderByComparator =
			getCommercePricingClassOrderByComparator(
				getOrderByCol(), getOrderByType());

		searchContainer.setOrderByComparator(orderByComparator);

		searchContainer.setOrderByType(getOrderByType());

		RowChecker rowChecker = new CommercePricingClassItemSelectorChecker(
			cpRequestHelper.getRenderResponse(),
			getCheckedCommercePricingClassIds());

		searchContainer.setRowChecker(rowChecker);

		List<CommercePricingClass> commercePricingClasses =
			_commercePricingClassService.getCommercePricingClasses(
				themeDisplay.getCompanyId(), searchContainer.getStart(),
				searchContainer.getEnd(), orderByComparator);

		searchContainer.setResults(commercePricingClasses);

		int commercePricingClassesCount =
			_commercePricingClassService.getCommercePricingClassesCount(
				themeDisplay.getCompanyId());

		searchContainer.setTotal(commercePricingClassesCount);

		return searchContainer;
	}

	protected static OrderByComparator<CommercePricingClass>
		getCommercePricingClassOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		if (orderByCol.equals("create-date")) {
			return new CommercePricingClassCreateDateComparator(orderByAsc);
		}

		return null;
	}

	protected long[] getCheckedCommercePricingClassIds() {
		return ParamUtil.getLongValues(
			cpRequestHelper.getRenderRequest(),
			"checkedCommercePricingClassIds");
	}

	private final CommercePricingClassService _commercePricingClassService;

}