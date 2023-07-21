/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class IconVerticalCardTag extends VerticalCardTag {

	public String getIcon() {
		return _icon;
	}

	public void setIcon(String icon) {
		_icon = icon;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_icon = null;
	}

	@Override
	protected String getPage() {
		return "/card/icon_vertical_card/page.jsp";
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return true;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		super.setAttributes(httpServletRequest);

		httpServletRequest.setAttribute("liferay-frontend:card:icon", _icon);
	}

	private String _icon;

}