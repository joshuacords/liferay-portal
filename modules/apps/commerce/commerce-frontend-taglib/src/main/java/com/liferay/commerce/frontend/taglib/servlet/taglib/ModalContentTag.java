/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.taglib.servlet.taglib;

import com.liferay.commerce.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Fabio Diego Mastrorilli
 */
public class ModalContentTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		request.setAttribute(
			_ATTRIBUTE_NAMESPACE + "contentCssClasses", _contentCssClasses);
		request.setAttribute(_ATTRIBUTE_NAMESPACE + "modalId", _modalId);
		request.setAttribute(
			_ATTRIBUTE_NAMESPACE + "showCancelButton", _showCancelButton);
		request.setAttribute(
			_ATTRIBUTE_NAMESPACE + "showSubmitButton", _showSubmitButton);
		request.setAttribute(
			_ATTRIBUTE_NAMESPACE + "submitButtonLabel", _submitButtonLabel);
		request.setAttribute(_ATTRIBUTE_NAMESPACE + "title", _title);

		super.doStartTag();

		return EVAL_BODY_INCLUDE;
	}

	public String getContentCssClasses() {
		return _contentCssClasses;
	}

	public String getModalId() {
		return _modalId;
	}

	public boolean getShowCancelButton() {
		return _showCancelButton;
	}

	public boolean getShowSubmitButton() {
		return _showSubmitButton;
	}

	public String getSubmitButtonLabel() {
		return _submitButtonLabel;
	}

	public String getTitle() {
		return _title;
	}

	public void setContentCssClasses(String contentCssClasses) {
		_contentCssClasses = contentCssClasses;
	}

	public void setModalId(String modalId) {
		_modalId = modalId;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setShowCancelButton(boolean showCancelButton) {
		_showCancelButton = showCancelButton;
	}

	public void setShowSubmitButton(boolean showSubmitButton) {
		_showSubmitButton = showSubmitButton;
	}

	public void setSubmitButtonLabel(String submitButtonLabel) {
		_submitButtonLabel = submitButtonLabel;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_contentCssClasses = null;
		_modalId = null;
		_showCancelButton = true;
		_showSubmitButton = true;
		_submitButtonLabel = null;
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
		"liferay-commerce:modal-content:";

	private static final String _END_PAGE = "/modal_content/end.jsp";

	private static final String _START_PAGE = "/modal_content/start.jsp";

	private String _contentCssClasses;
	private String _modalId;
	private boolean _showCancelButton = true;
	private boolean _showSubmitButton = true;
	private String _submitButtonLabel;
	private String _title;

}