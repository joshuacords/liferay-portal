/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notifications.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.UserNotificationFeedEntry;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergio González
 */
public class NotificationsManagementToolbarDisplayContext {

	public NotificationsManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest, PortletURL currentURLObj) {

		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_httpServletRequest = httpServletRequest;
		_currentURLObj = currentURLObj;
	}

	public List<DropdownItem> getActionDropdownItems() {
		return new DropdownItemList() {
			{
				if (!_isActionRequired()) {
					add(
						dropdownItem -> {
							dropdownItem.putData(
								"action", "markNotificationsAsRead");
							dropdownItem.setIcon("envelope-open");
							dropdownItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest, "mark-as-read"));
							dropdownItem.setQuickAction(true);
						});
					add(
						dropdownItem -> {
							dropdownItem.putData(
								"action", "markNotificationsAsUnread");
							dropdownItem.setIcon("envelope-closed");
							dropdownItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest, "mark-as-unread"));
							dropdownItem.setQuickAction(true);
						});
				}

				add(
					dropdownItem -> {
						dropdownItem.putData("action", "deleteNotifications");
						dropdownItem.setIcon("times-circle");
						dropdownItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "delete"));
						dropdownItem.setQuickAction(true);
					});
			}
		};
	}

	public List<String> getAvailableActions(
		UserNotificationEvent userNotificationEvent,
		UserNotificationFeedEntry userNotificationFeedEntry) {

		List<String> availableActions = new ArrayList<>();

		if ((userNotificationFeedEntry == null) ||
			!userNotificationFeedEntry.isApplicable()) {

			return availableActions;
		}

		if (!userNotificationFeedEntry.isActionable()) {
			availableActions.add("deleteNotifications");
		}

		if (!userNotificationEvent.isActionRequired()) {
			if (!userNotificationEvent.isArchived()) {
				availableActions.add("markNotificationsAsRead");
			}
			else {
				availableActions.add("markNotificationsAsUnread");
			}
		}

		return availableActions;
	}

	public String getClearResultsURL() {
		PortletURL clearResultsURL = _liferayPortletResponse.createRenderURL();

		clearResultsURL.setParameter(
			"actionRequired", String.valueOf(_isActionRequired()));

		return clearResultsURL.toString();
	}

	public List<DropdownItem> getFilterDropdownItems() {
		return new DropdownItemList() {
			{
				if (!_isActionRequired()) {
					addGroup(
						dropdownGroupItem -> {
							dropdownGroupItem.setDropdownItems(
								_getFilterNavigationDropdownItems());
							dropdownGroupItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest,
									"filter-by-navigation"));
						});
				}

				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							_getOrderByDropdownItems());
						dropdownGroupItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "order-by"));
					});
			}
		};
	}

	public List<LabelItem> getFilterLabelItems() {
		return new LabelItemList() {
			{
				String navigation = _getNavigation();

				if (navigation.equals("read") || navigation.equals("unread")) {
					add(
						labelItem -> {
							PortletURL removeLabelURL = PortletURLUtil.clone(
								_currentURLObj, _liferayPortletResponse);

							removeLabelURL.setParameter(
								"navigation", (String)null);

							labelItem.putData(
								"removeLabelURL", removeLabelURL.toString());

							labelItem.setCloseable(true);
							labelItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest, navigation));
						});
				}
			}
		};
	}

	public String getOrderByType() {
		return ParamUtil.getString(_httpServletRequest, "orderByType", "desc");
	}

	public PortletURL getSortingURL() throws PortletException {
		PortletURL sortingURL = PortletURLUtil.clone(
			_currentURLObj, _liferayPortletResponse);

		sortingURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, "0");
		sortingURL.setParameter("orderByCol", "date");
		sortingURL.setParameter(
			"orderByType",
			Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

		return sortingURL;
	}

	private List<DropdownItem> _getFilterNavigationDropdownItems() {
		String navigation = _getNavigation();

		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setActive(navigation.equals("all"));
						dropdownItem.setHref(
							PortletURLUtil.clone(
								_currentURLObj, _liferayPortletResponse),
							SearchContainer.DEFAULT_CUR_PARAM, "0",
							"navigation", "all");
						dropdownItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "all"));
					});
				add(
					dropdownItem -> {
						dropdownItem.setActive(navigation.equals("unread"));
						dropdownItem.setHref(
							PortletURLUtil.clone(
								_currentURLObj, _liferayPortletResponse),
							SearchContainer.DEFAULT_CUR_PARAM, "0",
							"navigation", "unread");
						dropdownItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "unread"));
					});
				add(
					dropdownItem -> {
						dropdownItem.setActive(navigation.equals("read"));
						dropdownItem.setHref(
							PortletURLUtil.clone(
								_currentURLObj, _liferayPortletResponse),
							SearchContainer.DEFAULT_CUR_PARAM, "0",
							"navigation", "read");
						dropdownItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "read"));
					});
			}
		};
	}

	private String _getNavigation() {
		return ParamUtil.getString(_httpServletRequest, "navigation", "all");
	}

	private List<DropdownItem> _getOrderByDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setActive(true);
						dropdownItem.setHref(getSortingURL());
						dropdownItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "date"));
					});
			}
		};
	}

	private boolean _isActionRequired() {
		return ParamUtil.getBoolean(_httpServletRequest, "actionRequired");
	}

	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;

}