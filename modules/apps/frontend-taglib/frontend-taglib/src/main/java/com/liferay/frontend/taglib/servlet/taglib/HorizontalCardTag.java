/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.internal.servlet.ServletContextUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Carlos Lancha
 */
public class HorizontalCardTag extends CardTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public Map<String, Object> getLinkData() {
		return _linkData;
	}

	public String getText() {
		return _text;
	}

	public void setColHTML(String colHTML) {
		_colHTML = colHTML;
	}

	public void setLinkData(Map<String, Object> linkData) {
		_linkData = linkData;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setText(String text) {
		_text = text;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_colHTML = null;
		_linkData = null;
		_text = null;
	}

	@Override
	protected String getPage() {
		return "/card/horizontal_card/page.jsp";
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		super.setAttributes(httpServletRequest);

		httpServletRequest.setAttribute(
			"liferay-frontend:card:colHTML", _colHTML);
		httpServletRequest.setAttribute(
			"liferay-frontend:card:linkData", _linkData);
		httpServletRequest.setAttribute("liferay-frontend:card:text", _text);
	}

	private String _colHTML;
	private Map<String, Object> _linkData;
	private String _text;

}