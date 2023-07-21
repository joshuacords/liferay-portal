/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shipment.web.internal.servlet.taglib.ui;

import com.liferay.commerce.model.CommerceShipment;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	property = {
		"screen.navigation.category.order:Integer=10",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class CommerceShipmentShippingSummaryScreenNavigationEntry
	implements ScreenNavigationCategory,
			   ScreenNavigationEntry<CommerceShipment> {

	@Override
	public String getCategoryKey() {
		return CommerceShipmentScreenNavigationConstants.
			CATEGORY_KEY_SHIPPING_SUMMARY;
	}

	@Override
	public String getEntryKey() {
		return CommerceShipmentScreenNavigationConstants.
			CATEGORY_KEY_SHIPPING_SUMMARY;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getEntryKey());
	}

	@Override
	public String getScreenNavigationKey() {
		return CommerceShipmentScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_COMMERCE_SHIPMENT_GENERAL;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/shipment/shipping_summary.jsp");
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.shipment.web)"
	)
	private ServletContext _servletContext;

}