/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.settings.web.internal.display.context;

import com.liferay.analytics.settings.configuration.AnalyticsConfiguration;
import com.liferay.analytics.settings.web.internal.constants.AnalyticsSettingsWebKeys;
import com.liferay.analytics.settings.web.internal.search.UserGroupChecker;
import com.liferay.analytics.settings.web.internal.search.UserGroupSearch;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.UserGroupNameComparator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author André Miranda
 */
public class UserGroupDisplayContext {

	public UserGroupDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_analyticsConfiguration =
			(AnalyticsConfiguration)renderRequest.getAttribute(
				AnalyticsSettingsWebKeys.ANALYTICS_CONFIGURATION);
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			_renderRequest, "orderByType", "asc");

		return _orderByType;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName",
			"/analytics_settings/edit_synced_contacts_groups");

		return portletURL;
	}

	public UserGroupSearch getUserGroupSearch() {
		UserGroupSearch userGroupSearch = new UserGroupSearch(
			_renderRequest, getPortletURL());

		userGroupSearch.setOrderByCol(_getOrderByCol());
		userGroupSearch.setOrderByType(getOrderByType());

		List<UserGroup> userGroups = UserGroupServiceUtil.search(
			_getCompanyId(), _getKeywords(), _getUserGroupParams(),
			userGroupSearch.getStart(), userGroupSearch.getEnd(),
			new UserGroupNameComparator(_isOrderByAscending()));

		userGroupSearch.setResults(userGroups);

		userGroupSearch.setRowChecker(
			new UserGroupChecker(
				_renderResponse,
				SetUtil.fromArray(
					_analyticsConfiguration.syncedUserGroupIds())));

		int total = UserGroupServiceUtil.searchCount(
			_getCompanyId(), _getKeywords(), _getUserGroupParams());

		userGroupSearch.setTotal(total);

		return userGroupSearch;
	}

	private long _getCompanyId() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return themeDisplay.getCompanyId();
	}

	private String _getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_renderRequest, "keywords");

		return _keywords;
	}

	private String _getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_renderRequest, "orderByCol", "user-group-name");

		return _orderByCol;
	}

	private LinkedHashMap<String, Object> _getUserGroupParams() {
		return LinkedHashMapBuilder.<String, Object>put(
			"active", Boolean.TRUE
		).build();
	}

	private boolean _isOrderByAscending() {
		if (Objects.equals(getOrderByType(), "asc")) {
			return true;
		}

		return false;
	}

	private final AnalyticsConfiguration _analyticsConfiguration;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}