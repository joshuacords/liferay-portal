/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.item.selector.web.internal.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.item.selector.criterion.SiteItemSelectorCriterion;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public abstract class BaseSitesItemSelectorViewDisplayContext
	implements SitesItemSelectorViewDisplayContext {

	public BaseSitesItemSelectorViewDisplayContext(
		HttpServletRequest httpServletRequest,
		SiteItemSelectorCriterion siteItemSelectorCriterion,
		String itemSelectedEventName, PortletURL portletURL) {

		request = httpServletRequest;
		_siteItemSelectorCriterion = siteItemSelectorCriterion;
		_itemSelectedEventName = itemSelectedEventName;
		this.portletURL = portletURL;
	}

	@Override
	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		_displayStyle = ParamUtil.getString(request, "displayStyle", "icon");

		return _displayStyle;
	}

	@Override
	public String getGroupName(Group group) throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		return group.getDescriptiveName(themeDisplay.getLocale());
	}

	@Override
	public String getItemSelectedEventName() {
		return _itemSelectedEventName;
	}

	@Override
	public PortletRequest getPortletRequest() {
		return (PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
	}

	@Override
	public PortletResponse getPortletResponse() {
		return (PortletResponse)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);
	}

	@Override
	public PortletURL getPortletURL() throws PortletException {
		return PortletURLUtil.clone(
			portletURL,
			PortalUtil.getLiferayPortletResponse(getPortletResponse()));
	}

	@Override
	public SiteItemSelectorCriterion getSiteItemSelectorCriterion() {
		return _siteItemSelectorCriterion;
	}

	@Override
	public boolean isShowChildSitesLink() {
		return false;
	}

	@Override
	public boolean isShowSearch() {
		return true;
	}

	@Override
	public boolean isShowSortFilter() {
		return false;
	}

	protected final PortletURL portletURL;
	protected final HttpServletRequest request;

	private String _displayStyle;
	private final String _itemSelectedEventName;
	private final SiteItemSelectorCriterion _siteItemSelectorCriterion;

}