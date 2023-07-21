/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.item.selector.taglib.internal.display.context;

import com.liferay.document.library.display.context.DLUIItemKeys;
import com.liferay.document.library.portlet.toolbar.contributor.DLPortletToolbarContributor;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.item.selector.taglib.internal.document.library.portlet.toolbar.contributor.DLPortletToolbarContributorRegistryUtil;
import com.liferay.item.selector.taglib.servlet.taglib.RepositoryEntryBrowserTag;
import com.liferay.item.selector.taglib.servlet.taglib.util.RepositoryEntryBrowserTagUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.servlet.taglib.ui.URLMenuItem;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alejandro Tardín
 */
public class ItemSelectorRepositoryEntryManagementToolbarDisplayContext {

	public ItemSelectorRepositoryEntryManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest) {

		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_httpServletRequest = httpServletRequest;

		_currentURLObj = PortletURLUtil.getCurrent(
			_liferayPortletRequest, _liferayPortletResponse);

		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			liferayPortletRequest);
	}

	public CreationMenu getCreationMenu() {
		DLPortletToolbarContributor dlPortletToolbarContributor =
			DLPortletToolbarContributorRegistryUtil.
				getDLPortletToolbarContributor();

		List<Menu> menus = dlPortletToolbarContributor.getPortletTitleMenus(
			_liferayPortletRequest, _liferayPortletResponse);

		if (menus.isEmpty()) {
			return null;
		}

		CreationMenu creationMenu = new CreationMenu();

		creationMenu.setItemsIconAlignment("left");

		Set<String> allowedCreationMenuUIItemKeys =
			_getAllowedCreationMenuUIItemKeys();

		for (Menu menu : menus) {
			List<URLMenuItem> urlMenuItems =
				(List<URLMenuItem>)(List<?>)menu.getMenuItems();

			for (URLMenuItem urlMenuItem : urlMenuItems) {
				if (Objects.equals(
						urlMenuItem.getKey(), DLUIItemKeys.ADD_FOLDER) ||
					Objects.equals(urlMenuItem.getKey(), DLUIItemKeys.UPLOAD) ||
					allowedCreationMenuUIItemKeys.contains(
						urlMenuItem.getKey())) {

					creationMenu.addDropdownItem(
						dropdownItem -> {
							dropdownItem.setData(urlMenuItem.getData());
							dropdownItem.setHref(urlMenuItem.getURL());
							dropdownItem.setIcon(urlMenuItem.getIcon());
							dropdownItem.setLabel(urlMenuItem.getLabel());
							dropdownItem.setSeparator(
								urlMenuItem.hasSeparator());
						});
				}
			}
		}

		return creationMenu;
	}

	public List<DropdownItem> getFilterDropdownItems() {
		return new DropdownItemList() {
			{
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

	public String getOrderByType() {
		if (_orderByType != null) {
			return _orderByType;
		}

		_orderByType = RepositoryEntryBrowserTagUtil.getOrderByType(
			_httpServletRequest, _portalPreferences);

		return _orderByType;
	}

	public PortletURL getSearchURL() throws PortletException {
		PortletURL searchURL = PortletURLUtil.clone(
			_currentURLObj, _liferayPortletResponse);

		searchURL.setParameter("keywords", (String)null);
		searchURL.setParameter("resetCur", Boolean.TRUE.toString());

		return searchURL;
	}

	public PortletURL getSortingURL() throws PortletException {
		PortletURL sortingURL = _getCurrentSortingURL();

		sortingURL.setParameter(
			"orderByType",
			Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

		return sortingURL;
	}

	public ViewTypeItemList getViewTypes() throws PortletException {
		PortletURL displayStyleURL = PortletURLUtil.clone(
			_getPortletURL(), _liferayPortletResponse);

		return new ViewTypeItemList(displayStyleURL, _getDisplayStyle()) {
			{
				if (ArrayUtil.contains(_getDisplayStyles(), "icon")) {
					addCardViewTypeItem();
				}

				if (ArrayUtil.contains(_getDisplayStyles(), "descriptive")) {
					addListViewTypeItem();
				}

				if (ArrayUtil.contains(_getDisplayStyles(), "list")) {
					addTableViewTypeItem();
				}
			}
		};
	}

	public boolean isDisabled() {
		return false;
	}

	private Set<String> _getAllowedCreationMenuUIItemKeys() {
		Set<String> allowedCreationMenuUIItemKeys =
			(Set)_httpServletRequest.getAttribute(
				"liferay-item-selector:repository-entry-browser:" +
					"allowedCreationMenuUIItemKeys");

		if (SetUtil.isEmpty(allowedCreationMenuUIItemKeys)) {
			return Collections.emptySet();
		}

		return allowedCreationMenuUIItemKeys;
	}

	private PortletURL _getCurrentSortingURL() throws PortletException {
		PortletURL currentSortingURL = PortletURLUtil.clone(
			_getPortletURL(), _liferayPortletResponse);

		currentSortingURL.setParameter("orderByType", getOrderByType());
		currentSortingURL.setParameter("orderByCol", _getOrderByCol());

		return currentSortingURL;
	}

	private String _getDisplayStyle() {
		return GetterUtil.getString(
			_httpServletRequest.getAttribute(
				"liferay-item-selector:repository-entry-browser:displayStyle"));
	}

	private String[] _getDisplayStyles() {
		return RepositoryEntryBrowserTag.DISPLAY_STYLES;
	}

	private String _getOrderByCol() {
		if (_orderByCol != null) {
			return _orderByCol;
		}

		_orderByCol = RepositoryEntryBrowserTagUtil.getOrderByCol(
			_httpServletRequest, _portalPreferences);

		return _orderByCol;
	}

	private List<DropdownItem> _getOrderByDropdownItems() {
		return new DropdownItemList() {
			{
				Map<String, String> orderColumnsMap = HashMapBuilder.put(
					"modifiedDate", "modified-date"
				).put(
					"size", "size"
				).put(
					"title", "title"
				).build();

				for (Map.Entry<String, String> orderByColEntry :
						orderColumnsMap.entrySet()) {

					add(
						dropdownItem -> {
							String orderByCol = orderByColEntry.getKey();

							dropdownItem.setActive(
								orderByCol.equals(_getOrderByCol()));
							dropdownItem.setHref(
								_getCurrentSortingURL(), "orderByCol",
								orderByCol);

							dropdownItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest,
									orderByColEntry.getValue()));
						});
				}
			}
		};
	}

	private PortletURL _getPortletURL() {
		return (PortletURL)_httpServletRequest.getAttribute(
			"liferay-item-selector:repository-entry-browser:portletURL");
	}

	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _orderByCol;
	private String _orderByType;
	private final PortalPreferences _portalPreferences;

}