/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.item.selector.web.internal.display.context;

import com.liferay.item.selector.constants.ItemSelectorPortletKeys;
import com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbEntry;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Roberto Díaz
 */
public class LayoutItemSelectorViewDisplayContext {

	public LayoutItemSelectorViewDisplayContext(
		HttpServletRequest httpServletRequest,
		LayoutItemSelectorCriterion layoutItemSelectorCriterion,
		PortletURL portletURL, String itemSelectedEventName,
		boolean privateLayout) {

		_httpServletRequest = httpServletRequest;
		_layoutItemSelectorCriterion = layoutItemSelectorCriterion;
		_portletURL = portletURL;
		_itemSelectedEventName = itemSelectedEventName;
		_privateLayout = privateLayout;

		_renderResponse = (RenderResponse)httpServletRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);
	}

	public String getItemSelectedEventName() {
		return _itemSelectedEventName;
	}

	public List<BreadcrumbEntry> getPortletBreadcrumbEntries()
		throws PortalException, PortletException {

		return Arrays.asList(
			_getSitesAndAssetLibrariesBreadcrumbEntry(),
			_getHomeBreadcrumbEntry());
	}

	public boolean isCheckDisplayPage() {
		return _layoutItemSelectorCriterion.isCheckDisplayPage();
	}

	public boolean isEnableCurrentPage() {
		return _layoutItemSelectorCriterion.isEnableCurrentPage();
	}

	public boolean isFollowURLOnTitleClick() {
		return _layoutItemSelectorCriterion.isFollowURLOnTitleClick();
	}

	public boolean isMultiSelection() {
		return _layoutItemSelectorCriterion.isMultiSelection();
	}

	public boolean isPrivateLayout() {
		return _privateLayout;
	}

	public boolean isShowBreadcrumb() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		boolean showBreadcrumb = ParamUtil.getBoolean(
			_httpServletRequest, "showBreadcrumb");

		if (Objects.equals(
				ItemSelectorPortletKeys.ITEM_SELECTOR,
				portletDisplay.getPortletName()) ||
			showBreadcrumb) {

			return true;
		}

		return false;
	}

	public boolean isShowHiddenPages() {
		return _layoutItemSelectorCriterion.isShowHiddenPages();
	}

	private BreadcrumbEntry _getHomeBreadcrumbEntry() throws PortalException {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		BreadcrumbEntry breadcrumbEntry = new BreadcrumbEntry();

		breadcrumbEntry.setTitle(themeDisplay.getSiteGroupName());

		return breadcrumbEntry;
	}

	private BreadcrumbEntry _getSitesAndAssetLibrariesBreadcrumbEntry()
		throws PortletException {

		BreadcrumbEntry breadcrumbEntry = new BreadcrumbEntry();

		breadcrumbEntry.setTitle(
			LanguageUtil.get(_httpServletRequest, "sites"));

		PortletURL portletURL = PortletURLUtil.clone(
			_portletURL, PortalUtil.getLiferayPortletResponse(_renderResponse));

		portletURL.setParameter("groupType", "site");
		portletURL.setParameter("showGroupSelector", Boolean.TRUE.toString());

		breadcrumbEntry.setURL(portletURL.toString());

		return breadcrumbEntry;
	}

	private final HttpServletRequest _httpServletRequest;
	private final String _itemSelectedEventName;
	private final LayoutItemSelectorCriterion _layoutItemSelectorCriterion;
	private final PortletURL _portletURL;
	private final boolean _privateLayout;
	private final RenderResponse _renderResponse;

}