/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.item.selector.web.internal.display.context;

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.item.selector.web.internal.display.context.util.CommerceAccountItemSelectorRequestHelper;
import com.liferay.commerce.account.item.selector.web.internal.search.CommerceAccountGroupAccountItemSelectorChecker;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupCommerceAccountRelLocalService;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupAccountItemSelectorViewDisplayContext {

	public CommerceAccountGroupAccountItemSelectorViewDisplayContext(
		CommerceAccountGroupCommerceAccountRelLocalService
			commerceAccountGroupCommerceAccountRelLocalService,
		CommerceAccountGroupService commerceAccountGroupService,
		CommerceAccountService commerceAccountService,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName) {

		_commerceAccountGroupCommerceAccountRelLocalService =
			commerceAccountGroupCommerceAccountRelLocalService;
		_commerceAccountGroupService = commerceAccountGroupService;
		_commerceAccountService = commerceAccountService;
		_portletURL = portletURL;
		_itemSelectedEventName = itemSelectedEventName;

		_commerceAccountItemSelectorRequestHelper =
			new CommerceAccountItemSelectorRequestHelper(httpServletRequest);

		_portletURL.setParameter(
			"commerceAccountGroupId",
			ParamUtil.getString(
				_commerceAccountItemSelectorRequestHelper.getRenderRequest(),
				"commerceAccountGroupId"));
	}

	public String getItemSelectedEventName() {
		return _itemSelectedEventName;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			_commerceAccountItemSelectorRequestHelper.getRenderRequest(),
			SearchContainer.DEFAULT_ORDER_BY_COL_PARAM, "createDate_sortable");
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			_commerceAccountItemSelectorRequestHelper.getRenderRequest(),
			SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM, "desc");
	}

	public PortletURL getPortletURL() {
		return _portletURL;
	}

	public SearchContainer<CommerceAccount> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_commerceAccountItemSelectorRequestHelper.
				getLiferayPortletRequest(),
			getPortletURL(), null, null);

		_searchContainer.setEmptyResultsMessage("there-are-no-accounts");

		_searchContainer.setOrderByCol(getOrderByCol());
		_searchContainer.setOrderByType(getOrderByType());

		RowChecker rowChecker =
			new CommerceAccountGroupAccountItemSelectorChecker(
				_commerceAccountItemSelectorRequestHelper.getRenderResponse(),
				getCommerceAccountGroup(),
				_commerceAccountGroupCommerceAccountRelLocalService);

		_searchContainer.setRowChecker(rowChecker);

		List<CommerceAccount> results =
			_commerceAccountService.getUserCommerceAccounts(
				_commerceAccountItemSelectorRequestHelper.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2C_B2B, getKeywords(),
				_searchContainer.getStart(), _searchContainer.getEnd());

		_searchContainer.setResults(results);

		int total = _commerceAccountService.getUserCommerceAccountsCount(
			_commerceAccountItemSelectorRequestHelper.getUserId(),
			CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
			CommerceAccountConstants.SITE_TYPE_B2C_B2B, getKeywords());

		_searchContainer.setTotal(total);

		return _searchContainer;
	}

	protected CommerceAccountGroup getCommerceAccountGroup()
		throws PortalException {

		long commerceAccountGroupId = ParamUtil.getLong(
			_commerceAccountItemSelectorRequestHelper.getRenderRequest(),
			"commerceAccountGroupId");

		if (commerceAccountGroupId > 0) {
			return _commerceAccountGroupService.getCommerceAccountGroup(
				commerceAccountGroupId);
		}

		return null;
	}

	protected long getCommerceAccountGroupId() throws PortalException {
		CommerceAccountGroup commerceAccountGroup = getCommerceAccountGroup();

		if (commerceAccountGroup == null) {
			return 0;
		}

		return commerceAccountGroup.getCommerceAccountGroupId();
	}

	protected String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(
			_commerceAccountItemSelectorRequestHelper.getRenderRequest(),
			"keywords");

		return _keywords;
	}

	private final CommerceAccountGroupCommerceAccountRelLocalService
		_commerceAccountGroupCommerceAccountRelLocalService;
	private final CommerceAccountGroupService _commerceAccountGroupService;
	private final CommerceAccountItemSelectorRequestHelper
		_commerceAccountItemSelectorRequestHelper;
	private final CommerceAccountService _commerceAccountService;
	private final String _itemSelectedEventName;
	private String _keywords;
	private final PortletURL _portletURL;
	private SearchContainer<CommerceAccount> _searchContainer;

}