/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Eudaldo Alonso
 */
public class VerticalCardSmallImageTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public String getCssClass() {
		return _cssClass;
	}

	public String getSrc() {
		return _src;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setSrc(String src) {
		_src = src;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_cssClass = null;
		_src = null;
	}

	@Override
	protected String getPage() {
		return "/card/vertical_card_small_image/page.jsp";
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		super.setAttributes(httpServletRequest);

		httpServletRequest.setAttribute(
			"liferay-frontend:vertical-card-small-image:cssClass", _cssClass);
		httpServletRequest.setAttribute(
			"liferay-frontend:vertical-card-small-image:src", _src);
	}

	private String _cssClass;
	private String _src;

}