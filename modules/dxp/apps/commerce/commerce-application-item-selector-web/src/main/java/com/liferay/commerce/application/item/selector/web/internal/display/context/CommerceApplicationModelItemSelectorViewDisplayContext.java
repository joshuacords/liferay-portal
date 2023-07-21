/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.application.item.selector.web.internal.display.context;

import com.liferay.commerce.application.item.selector.web.internal.search.CommerceApplicationModelItemSelectorChecker;
import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.service.CommerceApplicationModelService;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceApplicationModelItemSelectorViewDisplayContext
	extends BaseCommerceApplicationItemSelectorViewDisplayContext
		<CommerceApplicationModel> {

	public CommerceApplicationModelItemSelectorViewDisplayContext(
		CommerceApplicationModelService commerceApplicationModelService,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName) {

		super(httpServletRequest, portletURL, itemSelectedEventName);

		_commerceApplicationModelService = commerceApplicationModelService;

		setDefaultOrderByCol("name");
		setDefaultOrderByType("asc");
	}

	@Override
	public PortletURL getPortletURL() {
		PortletURL portletURL = super.getPortletURL();

		String checkedCommerceApplicationModelIds = StringUtil.merge(
			getCheckedCommerceApplicationModelIds());

		portletURL.setParameter(
			"checkedCommerceApplicationModelIds",
			checkedCommerceApplicationModelIds);

		return portletURL;
	}

	public SearchContainer<CommerceApplicationModel> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			commerceApplicationItemSelectorRequestHelper.getRenderRequest(),
			getPortletURL(), null, null);

		searchContainer.setEmptyResultsMessage("there-are-no-models");

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByType(getOrderByType());

		RowChecker rowChecker = new CommerceApplicationModelItemSelectorChecker(
			commerceApplicationItemSelectorRequestHelper.getRenderResponse(),
			getCheckedCommerceApplicationModelIds());

		searchContainer.setRowChecker(rowChecker);

		List<CommerceApplicationModel> results =
			_commerceApplicationModelService.
				getCommerceApplicationModelsByCompanyId(
					commerceApplicationItemSelectorRequestHelper.getCompanyId(),
					searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);

		int total =
			_commerceApplicationModelService.
				getCommerceApplicationModelsCountByCompanyId(
					commerceApplicationItemSelectorRequestHelper.
						getCompanyId());

		searchContainer.setTotal(total);

		return searchContainer;
	}

	protected long[] getCheckedCommerceApplicationModelIds() {
		return ParamUtil.getLongValues(
			commerceApplicationItemSelectorRequestHelper.getRenderRequest(),
			"checkedCommerceApplicationModelIds");
	}

	private final CommerceApplicationModelService
		_commerceApplicationModelService;

}