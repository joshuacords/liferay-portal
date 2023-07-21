/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alejandro Tardín
 */
public class HtmlVerticalCardTag extends VerticalCardTag {

	public String getHtml() {
		return _html;
	}

	public void setHtml(String html) {
		_html = html;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_html = null;
	}

	@Override
	protected String getPage() {
		return "/card/html_vertical_card/page.jsp";
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return true;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		super.setAttributes(httpServletRequest);

		httpServletRequest.setAttribute("liferay-frontend:card:html", _html);
	}

	private String _html;

}