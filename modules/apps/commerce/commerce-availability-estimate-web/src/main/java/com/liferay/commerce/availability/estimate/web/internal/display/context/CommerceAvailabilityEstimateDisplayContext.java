/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.availability.estimate.web.internal.display.context;

import com.liferay.commerce.availability.estimate.web.internal.admin.AvailabilityEstimatesCommerceAdminModule;
import com.liferay.commerce.availability.estimate.web.internal.util.CommerceAvailabilityEstimateUtil;
import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.service.CommerceAvailabilityEstimateService;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAvailabilityEstimateDisplayContext {

	public CommerceAvailabilityEstimateDisplayContext(
		CommerceAvailabilityEstimateService commerceAvailabilityEstimateService,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_commerceAvailabilityEstimateService =
			commerceAvailabilityEstimateService;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
	}

	public CommerceAvailabilityEstimate getCommerceAvailabilityEstimate()
		throws PortalException {

		if (_commerceAvailabilityEstimate != null) {
			return _commerceAvailabilityEstimate;
		}

		long commerceAvailabilityEstimateId = ParamUtil.getLong(
			_renderRequest, "commerceAvailabilityEstimateId");

		if (commerceAvailabilityEstimateId > 0) {
			_commerceAvailabilityEstimate =
				_commerceAvailabilityEstimateService.
					getCommerceAvailabilityEstimate(
						commerceAvailabilityEstimateId);
		}

		return _commerceAvailabilityEstimate;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			_renderRequest, SearchContainer.DEFAULT_ORDER_BY_COL_PARAM,
			"priority");
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			_renderRequest, SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM, "asc");
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"commerceAdminModuleKey",
			AvailabilityEstimatesCommerceAdminModule.KEY);
		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public SearchContainer<CommerceAvailabilityEstimate> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String emptyResultsMessage = "there-are-no-availability-estimates";

		_searchContainer = new SearchContainer<>(
			_renderRequest, getPortletURL(), null, emptyResultsMessage);

		String orderByCol = getOrderByCol();
		String orderByType = getOrderByType();

		OrderByComparator<CommerceAvailabilityEstimate> orderByComparator =
			CommerceAvailabilityEstimateUtil.
				getCommerceAvailabilityEstimateOrderByComparator(
					orderByCol, orderByType);

		_searchContainer.setOrderByCol(orderByCol);
		_searchContainer.setOrderByComparator(orderByComparator);
		_searchContainer.setOrderByType(orderByType);
		_searchContainer.setRowChecker(getRowChecker());

		int total =
			_commerceAvailabilityEstimateService.
				getCommerceAvailabilityEstimatesCount(
					themeDisplay.getCompanyId());

		List<CommerceAvailabilityEstimate> results =
			_commerceAvailabilityEstimateService.
				getCommerceAvailabilityEstimates(
					themeDisplay.getCompanyId(), _searchContainer.getStart(),
					_searchContainer.getEnd(), orderByComparator);

		_searchContainer.setTotal(total);
		_searchContainer.setResults(results);

		return _searchContainer;
	}

	public boolean hasManageCommerceAvailabilityEstimatesPermission() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return PortalPermissionUtil.contains(
			themeDisplay.getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_AVAILABILITY_ESTIMATES);
	}

	protected RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(_renderResponse);
		}

		return _rowChecker;
	}

	private CommerceAvailabilityEstimate _commerceAvailabilityEstimate;
	private final CommerceAvailabilityEstimateService
		_commerceAvailabilityEstimateService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private RowChecker _rowChecker;
	private SearchContainer<CommerceAvailabilityEstimate> _searchContainer;

}