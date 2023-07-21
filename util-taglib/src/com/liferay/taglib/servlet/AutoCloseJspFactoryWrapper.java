/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspEngineInfo;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

/**
 * @author Matthew Tambara
 */
public class AutoCloseJspFactoryWrapper extends JspFactory {

	public AutoCloseJspFactoryWrapper(JspFactory jspFactory) {
		_jspFactory = jspFactory;
	}

	@Override
	public JspEngineInfo getEngineInfo() {
		return _jspFactory.getEngineInfo();
	}

	@Override
	public JspApplicationContext getJspApplicationContext(
		ServletContext servletContext) {

		return _jspFactory.getJspApplicationContext(servletContext);
	}

	@Override
	public PageContext getPageContext(
		Servlet servlet, ServletRequest servletRequest,
		ServletResponse servletResponse, String errorPageURL,
		boolean needsSession, int buffer, boolean autoflush) {

		PageContext pageContext = _jspFactory.getPageContext(
			servlet, servletRequest, servletResponse, errorPageURL,
			needsSession, buffer, autoflush);

		pageContext.setAttribute(
			AutoClosePageContextRegistry.AUTO_CLOSEABLE, Boolean.TRUE);

		return pageContext;
	}

	@Override
	public void releasePageContext(PageContext pageContext) {
		AutoClosePageContextRegistry.runAutoCloseRunnables(pageContext);

		_jspFactory.releasePageContext(pageContext);
	}

	private final JspFactory _jspFactory;

}