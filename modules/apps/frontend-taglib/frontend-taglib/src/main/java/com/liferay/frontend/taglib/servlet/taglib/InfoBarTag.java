/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.taglib.base.BaseBarTag;

import javax.servlet.jsp.JspException;

/**
 * @author Roberto Díaz
 */
public class InfoBarTag extends BaseBarTag {

	@Override
	public int doEndTag() throws JspException {
		request.setAttribute("liferay-frontend:info-bar:buttons", buttons);
		request.setAttribute("liferay-frontend:info-bar:fixed", _fixed);

		return super.doEndTag();
	}

	public boolean isFixed() {
		return _fixed;
	}

	public void setFixed(boolean fixed) {
		_fixed = fixed;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_fixed = false;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	private static final String _END_PAGE = "/info_bar/end.jsp";

	private static final String _START_PAGE = "/info_bar/start.jsp";

	private boolean _fixed;

}