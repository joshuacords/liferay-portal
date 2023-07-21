/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib.base;

import com.liferay.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.frontend.taglib.servlet.taglib.BarTag;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.jsp.PageContext;

/**
 * @author Roberto Díaz
 */
public abstract class BaseBarTag extends IncludeTag implements BarTag {

	@Override
	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		buttons = null;
	}

	protected String buttons;

}