/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.admin.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class SiteAdminManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public SiteAdminManagementToolbarDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest httpServletRequest,
			SiteAdminDisplayContext siteAdminDisplayContext)
		throws PortalException {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			siteAdminDisplayContext.getSearchContainer());

		_siteAdminDisplayContext = siteAdminDisplayContext;
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.putData("action", "deleteSites");
						dropdownItem.setIcon("times-circle");
						dropdownItem.setLabel(
							LanguageUtil.get(request, "delete"));
						dropdownItem.setQuickAction(true);
					});
			}
		};
	}

	public String getAvailableActions(Group group) throws PortalException {
		if (_hasDeleteGroupPermission(group)) {
			return "deleteSites";
		}

		return StringPool.BLANK;
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);
		clearResultsURL.setParameter("orderByCol", getOrderByCol());
		clearResultsURL.setParameter("orderByType", getOrderByType());

		return clearResultsURL.toString();
	}

	@Override
	public String getComponentId() {
		return "siteAdminWebManagementToolbar";
	}

	@Override
	public CreationMenu getCreationMenu() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!PortalPermissionUtil.contains(
				themeDisplay.getPermissionChecker(),
				ActionKeys.ADD_COMMUNITY)) {

			return null;
		}

		try {
			PortletURL addSiteURL = liferayPortletResponse.createRenderURL();

			addSiteURL.setParameter(
				"mvcRenderCommandName", "/site/select_site_initializer");
			addSiteURL.setParameter("redirect", themeDisplay.getURLCurrent());

			Group group = _siteAdminDisplayContext.getGroup();

			if ((group != null) &&
				_siteAdminDisplayContext.hasAddChildSitePermission(group)) {

				addSiteURL.setParameter(
					"parentGroupId", String.valueOf(group.getGroupId()));
			}

			return new CreationMenu() {
				{
					addPrimaryDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref(addSiteURL.toString());
							dropdownItem.setLabel(
								LanguageUtil.get(request, "add"));
						});
				}
			};
		}
		catch (Exception exception) {
		}

		return null;
	}

	@Override
	public String getDefaultEventHandler() {
		return "sitesManagementToolbarDefaultEventHandler";
	}

	@Override
	public String getInfoPanelId() {
		return "infoPanelId";
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchTagURL = getPortletURL();

		searchTagURL.setParameter("orderByCol", getOrderByCol());
		searchTagURL.setParameter("orderByType", getOrderByType());

		return searchTagURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "sites";
	}

	@Override
	public Boolean isShowCreationMenu() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (PortalPermissionUtil.contains(
				themeDisplay.getPermissionChecker(),
				ActionKeys.ADD_COMMUNITY)) {

			return true;
		}

		return false;
	}

	@Override
	protected String getDisplayStyle() {
		return _siteAdminDisplayContext.getDisplayStyle();
	}

	@Override
	protected String[] getDisplayViews() {
		return new String[] {"list", "descriptive", "icon"};
	}

	@Override
	protected String[] getNavigationKeys() {
		return new String[] {"all"};
	}

	@Override
	protected String[] getOrderByKeys() {
		return new String[] {"descriptive-name"};
	}

	private boolean _hasDeleteGroupPermission(Group group)
		throws PortalException {

		if (group.isCompany()) {
			return false;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!GroupPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), group,
				ActionKeys.DELETE)) {

			return false;
		}

		if (PortalUtil.isSystemGroup(group.getGroupKey())) {
			return false;
		}

		return true;
	}

	private final SiteAdminDisplayContext _siteAdminDisplayContext;

}