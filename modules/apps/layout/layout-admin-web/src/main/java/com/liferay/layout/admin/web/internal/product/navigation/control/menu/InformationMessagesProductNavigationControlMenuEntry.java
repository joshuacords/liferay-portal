/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.product.navigation.control.menu;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.product.navigation.control.menu.BaseJSPProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;
import com.liferay.sites.kernel.util.SitesUtil;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(
	immediate = true,
	property = {
		"product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.TOOLS,
		"product.navigation.control.menu.entry.order:Integer=300"
	},
	service = ProductNavigationControlMenuEntry.class
)
public class InformationMessagesProductNavigationControlMenuEntry
	extends BaseJSPProductNavigationControlMenuEntry
	implements ProductNavigationControlMenuEntry {

	public static final String INFORMATION_MESSAGES_LINKED_LAYOUT =
		"INFORMATION_MESSAGES_LINKED_LAYOUT";

	public static final String INFORMATION_MESSAGES_MODIFIED_LAYOUT =
		"INFORMATION_MESSAGES_MODIFIED_LAYOUT";

	@Override
	public String getIconJspPath() {
		return "/control/menu/information_messages.jsp";
	}

	public boolean hasUpdateLayoutPermission(ThemeDisplay themeDisplay)
		throws PortalException {

		if (LayoutPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), themeDisplay.getLayout(),
				ActionKeys.UPDATE)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean includeIcon(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		try {
			httpServletRequest.setAttribute(
				INFORMATION_MESSAGES_LINKED_LAYOUT,
				isLinkedLayout(themeDisplay));
			httpServletRequest.setAttribute(
				INFORMATION_MESSAGES_MODIFIED_LAYOUT,
				isModifiedLayout(themeDisplay));
		}
		catch (PortalException portalException) {
			_log.error(portalException, portalException);
		}

		return super.includeIcon(httpServletRequest, httpServletResponse);
	}

	@Override
	public boolean isShow(HttpServletRequest httpServletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout.isTypeControlPanel()) {
			return false;
		}

		if (!isLinkedLayout(themeDisplay) && !isModifiedLayout(themeDisplay)) {
			return false;
		}

		return super.isShow(httpServletRequest);
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.layout.admin.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	protected boolean isLinkedLayout(ThemeDisplay themeDisplay)
		throws PortalException {

		Layout layout = themeDisplay.getLayout();

		Group group = layout.getGroup();

		if (!SitesUtil.isLayoutUpdateable(layout) ||
			(layout.isLayoutPrototypeLinkActive() &&
			 !group.hasStagingGroup())) {

			if (!LayoutPermissionUtil.containsWithoutViewableGroup(
					themeDisplay.getPermissionChecker(), layout, false,
					ActionKeys.UPDATE)) {

				return false;
			}

			return true;
		}

		return false;
	}

	protected boolean isModifiedLayout(ThemeDisplay themeDisplay)
		throws PortalException {

		Layout layout = themeDisplay.getLayout();

		LayoutSet layoutSet = layout.getLayoutSet();

		if (!layoutSet.isLayoutSetPrototypeLinkActive() ||
			!SitesUtil.isLayoutModifiedSinceLastMerge(layout)) {

			return false;
		}

		if (!hasUpdateLayoutPermission(themeDisplay)) {
			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		InformationMessagesProductNavigationControlMenuEntry.class);

}