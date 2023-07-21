/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.teams.web.internal.display.context;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.portlet.SearchDisplayStyleUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.persistence.constants.UserGroupFinderConstants;
import com.liferay.portlet.usergroupsadmin.search.UserGroupDisplayTerms;
import com.liferay.portlet.usergroupsadmin.search.UserGroupSearch;
import com.liferay.site.teams.web.internal.constants.SiteTeamsPortletKeys;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;

import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class EditSiteTeamAssignmentsUserGroupsDisplayContext
	extends EditSiteTeamAssignmentsDisplayContext {

	public EditSiteTeamAssignmentsUserGroupsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		super(renderRequest, renderResponse, httpServletRequest);
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		_displayStyle = SearchDisplayStyleUtil.getDisplayStyle(
			request, SiteTeamsPortletKeys.SITE_TEAMS, "usergroup-display-style",
			"list");

		return _displayStyle;
	}

	@Override
	public PortletURL getEditTeamAssignmentsURL() {
		PortletURL portletURL = super.getEditTeamAssignmentsURL();

		String keywords = getKeywords();

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
		}

		String orderByCol = getOrderByCol();

		if (Validator.isNotNull(orderByCol)) {
			portletURL.setParameter("orderByCol", orderByCol);
		}

		String orderByType = getOrderByType();

		if (Validator.isNotNull(orderByType)) {
			portletURL.setParameter("orderByType", orderByType);
		}

		return portletURL;
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(request, "keywords");

		return _keywords;
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(request, "orderByCol", "name");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(request, "orderByType", "asc");

		return _orderByType;
	}

	public SearchContainer getUserGroupSearchContainer() {
		if (_userGroupSearchContainer != null) {
			return _userGroupSearchContainer;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		SearchContainer userGroupSearchContainer = new UserGroupSearch(
			renderRequest, getEditTeamAssignmentsURL());

		OrderByComparator<UserGroup> orderByComparator =
			UsersAdminUtil.getUserGroupOrderByComparator(
				getOrderByCol(), getOrderByType());

		userGroupSearchContainer.setOrderByCol(getOrderByCol());
		userGroupSearchContainer.setOrderByComparator(orderByComparator);
		userGroupSearchContainer.setOrderByType(getOrderByType());

		userGroupSearchContainer.setRowChecker(
			new EmptyOnClickRowChecker(renderResponse));

		UserGroupDisplayTerms searchTerms =
			(UserGroupDisplayTerms)userGroupSearchContainer.getSearchTerms();

		LinkedHashMap<String, Object> userGroupParams =
			LinkedHashMapBuilder.<String, Object>put(
				UserGroupFinderConstants.PARAM_KEY_USER_GROUPS_TEAMS,
				getTeamId()
			).build();

		int userGroupsCount = UserGroupLocalServiceUtil.searchCount(
			themeDisplay.getCompanyId(), searchTerms.getKeywords(),
			userGroupParams);

		userGroupSearchContainer.setTotal(userGroupsCount);

		List<UserGroup> userGroups = UserGroupLocalServiceUtil.search(
			themeDisplay.getCompanyId(), searchTerms.getKeywords(),
			userGroupParams, userGroupSearchContainer.getStart(),
			userGroupSearchContainer.getEnd(),
			userGroupSearchContainer.getOrderByComparator());

		userGroupSearchContainer.setResults(userGroups);

		_userGroupSearchContainer = userGroupSearchContainer;

		return _userGroupSearchContainer;
	}

	private String _displayStyle;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private SearchContainer _userGroupSearchContainer;

}