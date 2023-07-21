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
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.product.navigation.control.menu.BaseJSPProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;

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
		"product.navigation.control.menu.entry.order:Integer=400"
	},
	service = {
		CustomizationSettingsProductNavigationControlMenuEntry.class,
		ProductNavigationControlMenuEntry.class
	}
)
public class CustomizationSettingsProductNavigationControlMenuEntry
	extends BaseJSPProductNavigationControlMenuEntry
	implements ProductNavigationControlMenuEntry {

	public static final String CUSTOMIZATION_SETTINGS_LAYOUT_UPDATE_PERMISSION =
		"CUSTOMIZATION_SETTINGS_LAYOUT_UPDATE_PERMISSION";

	@Override
	public String getIconJspPath() {
		return "/control/menu/customization_settings.jsp";
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
				CUSTOMIZATION_SETTINGS_LAYOUT_UPDATE_PERMISSION,
				hasUpdateLayoutPermission(themeDisplay));
		}
		catch (PortalException portalException) {
			_log.error(portalException, portalException);
		}

		return super.includeIcon(httpServletRequest, httpServletResponse);
	}

	@Override
	public boolean isShow(HttpServletRequest httpServletRequest)
		throws PortalException {

		Boolean show = (Boolean)httpServletRequest.getAttribute(_SHOW);

		if (show != null) {
			return show;
		}

		show = _isShow(httpServletRequest);

		httpServletRequest.setAttribute(_SHOW, show);

		return show;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.layout.admin.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	protected boolean isCustomizableLayout(ThemeDisplay themeDisplay)
		throws PortalException {

		Layout layout = themeDisplay.getLayout();

		Group group = layout.getGroup();

		if (group.isLayoutPrototype() || group.isLayoutSetPrototype() ||
			group.isStagingGroup() || group.isUserGroup()) {

			return false;
		}

		LayoutTypePortlet layoutTypePortlet =
			themeDisplay.getLayoutTypePortlet();

		if (!layout.isTypePortlet() || (layoutTypePortlet == null)) {
			return false;
		}

		if (layout.isCustomizable() &&
			hasUpdateLayoutPermission(themeDisplay)) {

			return true;
		}

		if (!layoutTypePortlet.isCustomizable()) {
			return false;
		}

		if (!LayoutPermissionUtil.containsWithoutViewableGroup(
				themeDisplay.getPermissionChecker(), layout, false,
				ActionKeys.CUSTOMIZE)) {

			return false;
		}

		return true;
	}

	private boolean _isShow(HttpServletRequest httpServletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout.isTypeControlPanel()) {
			return false;
		}

		if (!isCustomizableLayout(themeDisplay)) {
			return false;
		}

		return super.isShow(httpServletRequest);
	}

	private static final String _SHOW =
		CustomizationSettingsProductNavigationControlMenuEntry.class + "#_SHOW";

	private static final Log _log = LogFactoryUtil.getLog(
		CustomizationSettingsProductNavigationControlMenuEntry.class);

}