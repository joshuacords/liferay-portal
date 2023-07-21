/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.options.web.internal.util;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.util.CPNavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.permission.PortletPermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.navigation.item.key=" + CPPortletKeys.CP_OPTION_CATEGORIES,
		"commerce.product.navigation.item.order:Integer=30"
	},
	service = CPNavigationItem.class
)
public class CPSpecificationOptionNavigationItem implements CPNavigationItem {

	public NavigationItem getNavigationItem(PortletRequest portletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		boolean manageCPOptionCategoriesPermission =
			_portletPermission.contains(
				themeDisplay.getPermissionChecker(),
				CPPortletKeys.CP_OPTION_CATEGORIES, ActionKeys.VIEW);

		boolean manageCPSpecificationOptionsPermission =
			_portletPermission.contains(
				themeDisplay.getPermissionChecker(),
				CPPortletKeys.CP_SPECIFICATION_OPTIONS, ActionKeys.VIEW);

		if (!manageCPOptionCategoriesPermission &&
			!manageCPSpecificationOptionsPermission) {

			return null;
		}

		NavigationItem navigationItem = new NavigationItem();

		String portletId = _portal.getPortletId(portletRequest);

		navigationItem.setActive(
			portletId.equals(CPPortletKeys.CP_OPTION_CATEGORIES) ||
			portletId.equals(CPPortletKeys.CP_SPECIFICATION_OPTIONS));

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			portletRequest, themeDisplay.getScopeGroup(),
			CPSpecificationOption.class.getName(),
			PortletProvider.Action.MANAGE);

		navigationItem.setHref(portletURL.toString());

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", themeDisplay.getLocale(), getClass());

		navigationItem.setLabel(
			LanguageUtil.get(resourceBundle, "specifications"));

		return navigationItem;
	}

	@Reference
	private Portal _portal;

	@Reference
	private PortletPermission _portletPermission;

}