/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.taglib.base.BaseBarTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Eudaldo Alonso
 */
public class ManagementBarTag extends BaseBarTag implements BodyTag {

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() {
		callSetAttributes();

		return EVAL_BODY_INCLUDE;
	}

	public String getSearchContainerId() {
		return _searchContainerId;
	}

	public boolean isDisabled() {
		return _disabled;
	}

	public boolean isIncludeCheckBox() {
		return _includeCheckBox;
	}

	public void setActionButtons(String actionButtons) {
		_actionButtons = actionButtons;
	}

	public void setDisabled(boolean disabled) {
		_disabled = disabled;
	}

	public void setFilters(String filters) {
		_filters = filters;
	}

	public void setIncludeCheckBox(boolean includeCheckBox) {
		_includeCheckBox = includeCheckBox;
	}

	public void setSearchContainerId(String searchContainerId) {
		_searchContainerId = searchContainerId;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_actionButtons = null;
		_disabled = false;
		_filters = null;
		_includeCheckBox = false;
		_searchContainerId = null;

		super.cleanUp();
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected int processStartTag() throws Exception {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-frontend:management-bar:actionButtons", _actionButtons);
		httpServletRequest.setAttribute(
			"liferay-frontend:management-bar:buttons", buttons);
		httpServletRequest.setAttribute(
			"liferay-frontend:management-bar:disabled", _disabled);
		httpServletRequest.setAttribute(
			"liferay-frontend:management-bar:filters", _filters);
		httpServletRequest.setAttribute(
			"liferay-frontend:management-bar:includeCheckBox",
			_includeCheckBox);
		httpServletRequest.setAttribute(
			"liferay-frontend:management-bar:searchContainerId",
			_searchContainerId);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _PAGE = "/management_bar/page.jsp";

	private String _actionButtons;
	private boolean _disabled;
	private String _filters;
	private boolean _includeCheckBox;
	private String _searchContainerId;

}