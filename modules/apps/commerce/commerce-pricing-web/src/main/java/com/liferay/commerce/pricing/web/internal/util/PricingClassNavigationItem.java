/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.web.internal.util;

import com.liferay.commerce.pricing.constants.CommercePricingPortletConstants;
import com.liferay.commerce.pricing.constants.CommercePricingPortletKeys;
import com.liferay.commerce.pricing.util.PricingNavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
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
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.pricing.navigation.item.key=" + CommercePricingPortletKeys.COMMERCE_PRICING_CLASSES,
		"commerce.pricing.navigation.item.order:Integer=10"
	},
	service = PricingNavigationItem.class
)
public class PricingClassNavigationItem implements PricingNavigationItem {

	@Override
	public NavigationItem getNavigationItem(PortletRequest portletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		boolean manageCatalogPermission = _portletPermission.contains(
			themeDisplay.getPermissionChecker(),
			CommercePricingPortletKeys.COMMERCE_PRICING_CLASSES,
			ActionKeys.VIEW);

		if (!manageCatalogPermission) {
			return null;
		}

		NavigationItem navigationItem = new NavigationItem();

		String portletId = _portal.getPortletId(portletRequest);

		navigationItem.setActive(
			portletId.equals(
				CommercePricingPortletKeys.COMMERCE_PRICING_CLASSES));

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			portletRequest, CommercePricingPortletKeys.COMMERCE_PRICING_CLASSES,
			PortletRequest.ACTION_PHASE);

		navigationItem.setHref(portletURL.toString());

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", themeDisplay.getLocale(), getClass());

		navigationItem.setLabel(
			LanguageUtil.get(
				resourceBundle,
				CommercePricingPortletConstants.
					NAVIGATION_ITEM_PRICING_CLASSES));

		return navigationItem;
	}

	@Reference
	private Portal _portal;

	@Reference
	private PortletPermission _portletPermission;

}