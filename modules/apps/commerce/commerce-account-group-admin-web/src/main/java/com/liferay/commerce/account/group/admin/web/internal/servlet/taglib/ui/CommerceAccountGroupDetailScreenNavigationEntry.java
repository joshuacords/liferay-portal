/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.group.admin.web.internal.servlet.taglib.ui;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"screen.navigation.category.order:Integer=10",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class CommerceAccountGroupDetailScreenNavigationEntry
	implements ScreenNavigationCategory,
			   ScreenNavigationEntry<CommerceAccountGroup> {

	@Override
	public String getCategoryKey() {
		return CommerceAccountGroupScreenNavigationConstants.
			CATEGORY_KEY_COMMERCE_ACCOUNT_GROUP_DETAIL;
	}

	@Override
	public String getEntryKey() {
		return CommerceAccountGroupScreenNavigationConstants.
			ENTRY_KEY_COMMERCE_ACCOUNT_GROUP_DETAIL;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle,
			CommerceAccountGroupScreenNavigationConstants.
				ENTRY_KEY_COMMERCE_ACCOUNT_GROUP_DETAIL);
	}

	@Override
	public String getScreenNavigationKey() {
		return CommerceAccountGroupScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_COMMERCE_ACCOUNT_GROUP_GENERAL;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/account_group/detail.jsp");
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.account.group.admin.web)"
	)
	private ServletContext _servletContext;

}