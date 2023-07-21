/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.user.internal.application.list;

import com.liferay.application.list.BaseJSPPanelCategory;
import com.liferay.application.list.constants.ApplicationListWebKeys;
import com.liferay.application.list.constants.PanelCategoryKeys;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author     Adolfo Pérez
 * @see        com.liferay.product.navigation.personal.menu.PersonalMenuEntry
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class UserPanelCategory extends BaseJSPPanelCategory {

	@Override
	public String getHeaderJspPath() {
		return "/user_header.jsp";
	}

	@Override
	public String getJspPath() {
		return "/user_body.jsp";
	}

	@Override
	public String getKey() {
		return PanelCategoryKeys.USER;
	}

	@Override
	public String getLabel(Locale locale) {
		return null;
	}

	@Override
	public boolean include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		httpServletRequest.setAttribute(
			ApplicationListWebKeys.PANEL_CATEGORY, this);

		return super.include(httpServletRequest, httpServletResponse);
	}

	@Override
	public boolean includeHeader(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		httpServletRequest.setAttribute(
			ApplicationListWebKeys.PANEL_CATEGORY, this);

		return super.includeHeader(httpServletRequest, httpServletResponse);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

}