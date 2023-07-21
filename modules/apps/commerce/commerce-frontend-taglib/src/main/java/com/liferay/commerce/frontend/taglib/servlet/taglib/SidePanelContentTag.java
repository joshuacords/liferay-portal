/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.taglib.servlet.taglib;

import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.portlet.PortletURL;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Diego Mastrorilli
 */
public class SidePanelContentTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		setNamespacedAttribute(request, "showCloseButton", _showCloseButton);
		setNamespacedAttribute(request, "sidePanelId", _sidePanelId);
		setNamespacedAttribute(request, "title", _title);
		setNamespacedAttribute(
			request, "screenNavigatorKey", _screenNavigatorKey);
		setNamespacedAttribute(
			request, "screenNavigatorModelBean", _screenNavigatorModelBean);
		setNamespacedAttribute(
			request, "screenNavigatorPortletURL", _screenNavigatorPortletURL);

		super.doStartTag();

		return EVAL_BODY_INCLUDE;
	}

	public String getScreenNavigatorKey() {
		return _screenNavigatorKey;
	}

	public Object getScreenNavigatorModelBean() {
		return _screenNavigatorModelBean;
	}

	public PortletURL getScreenNavigatorPortletURL() {
		return _screenNavigatorPortletURL;
	}

	public boolean getShowCloseButton() {
		return _showCloseButton;
	}

	public String getSidePanelId() {
		return _sidePanelId;
	}

	public String getTitle() {
		return _title;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setScreenNavigatorKey(String screenNavigatorKey) {
		_screenNavigatorKey = screenNavigatorKey;
	}

	public void setScreenNavigatorModelBean(Object screenNavigatorModelBean) {
		_screenNavigatorModelBean = screenNavigatorModelBean;
	}

	public void setScreenNavigatorPortletURL(
		PortletURL screenNavigatorPortletURL) {

		_screenNavigatorPortletURL = screenNavigatorPortletURL;
	}

	public void setShowCloseButton(boolean showCloseButton) {
		_showCloseButton = showCloseButton;
	}

	public void setSidePanelId(String sidePanelId) {
		_sidePanelId = sidePanelId;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_screenNavigatorKey = null;
		_screenNavigatorModelBean = null;
		_screenNavigatorPortletURL = null;
		_showCloseButton = false;
		_sidePanelId = null;
		_title = null;
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
		"liferay-commerce:side-panel-content:";

	private static final String _END_PAGE = "/side_panel_content/end.jsp";

	private static final String _START_PAGE = "/side_panel_content/start.jsp";

	private String _screenNavigatorKey;
	private Object _screenNavigatorModelBean;
	private PortletURL _screenNavigatorPortletURL;
	private boolean _showCloseButton;
	private String _sidePanelId;
	private String _title;

}