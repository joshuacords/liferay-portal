/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.user.groups.admin.item.selector.web.internal.display.context;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.usergroupsadmin.search.UserGroupDisplayTerms;
import com.liferay.portlet.usergroupsadmin.search.UserGroupSearch;
import com.liferay.user.groups.admin.item.selector.web.internal.search.UserGroupItemSelectorChecker;
import com.liferay.users.admin.kernel.util.UsersAdmin;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class UserGroupItemSelectorViewDisplayContext {

	public UserGroupItemSelectorViewDisplayContext(
		UserGroupLocalService userGroupLocalService, UsersAdmin usersAdmin,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName) {

		_userGroupLocalService = userGroupLocalService;
		_usersAdmin = usersAdmin;
		_httpServletRequest = httpServletRequest;
		_portletURL = portletURL;
		_itemSelectedEventName = itemSelectedEventName;

		_renderRequest = (RenderRequest)httpServletRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
		_renderResponse = (RenderResponse)httpServletRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);
	}

	public String getItemSelectedEventName() {
		return _itemSelectedEventName;
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			_renderRequest, SearchContainer.DEFAULT_ORDER_BY_COL_PARAM, "name");
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			_renderRequest, SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM, "asc");
	}

	public PortletURL getPortletURL() {
		return _portletURL;
	}

	public SearchContainer<UserGroup> getSearchContainer()
		throws PortalException {

		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new UserGroupSearch(_renderRequest, getPortletURL());

		OrderByComparator<UserGroup> orderByComparator =
			_usersAdmin.getUserGroupOrderByComparator(
				getOrderByCol(), getOrderByType());

		RowChecker rowChecker = new UserGroupItemSelectorChecker(
			_renderResponse, getCheckedUserGroupIds());

		_searchContainer.setOrderByCol(getOrderByCol());
		_searchContainer.setOrderByComparator(orderByComparator);
		_searchContainer.setOrderByType(getOrderByType());
		_searchContainer.setRowChecker(rowChecker);

		long companyId = CompanyThreadLocal.getCompanyId();

		UserGroupDisplayTerms searchTerms =
			(UserGroupDisplayTerms)_searchContainer.getSearchTerms();

		String keywords = searchTerms.getKeywords();

		int total = _userGroupLocalService.searchCount(
			companyId, keywords, null);

		_searchContainer.setTotal(total);

		List<UserGroup> results = _userGroupLocalService.search(
			companyId, keywords, null, _searchContainer.getStart(),
			_searchContainer.getEnd(), orderByComparator);

		_searchContainer.setResults(results);

		return _searchContainer;
	}

	protected long[] getCheckedUserGroupIds() {
		return ParamUtil.getLongValues(_renderRequest, "checkedUserGroupIds");
	}

	private final HttpServletRequest _httpServletRequest;
	private final String _itemSelectedEventName;
	private final PortletURL _portletURL;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private SearchContainer<UserGroup> _searchContainer;
	private final UserGroupLocalService _userGroupLocalService;
	private final UsersAdmin _usersAdmin;

}