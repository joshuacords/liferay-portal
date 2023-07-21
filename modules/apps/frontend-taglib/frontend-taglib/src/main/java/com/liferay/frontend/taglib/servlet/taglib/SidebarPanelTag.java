/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Carlos Lancha
 */
public class SidebarPanelTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		setNamespacedAttribute(
			request, "searchContainerId", _searchContainerId);
		setNamespacedAttribute(request, "resourceURL", _resourceURL);

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		super.doStartTag();

		return EVAL_BODY_INCLUDE;
	}

	public String getResourceURL() {
		return _resourceURL;
	}

	public String getSearchContainerId() {
		return _searchContainerId;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setResourceURL(String resourceURL) {
		_resourceURL = resourceURL;
	}

	public void setSearchContainerId(String searchContainerId) {
		_searchContainerId = searchContainerId;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_resourceURL = null;
		_searchContainerId = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	private static final String _ATTRIBUTE_NAMESPACE =
		"liferay-frontend:sidebar-panel:";

	private static final String _END_PAGE = "/sidebar_panel/end.jsp";

	private static final String _START_PAGE = "/sidebar_panel/start.jsp";

	private String _resourceURL;
	private String _searchContainerId;

}