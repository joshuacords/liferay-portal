/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.taglib.servlet.taglib.base;

import com.liferay.data.engine.taglib.internal.servlet.ServletContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Jeyvison Nascimento
 * @author Leonardo Barros
 * @generated
 */
public abstract class BaseDataLayoutRendererTag extends com.liferay.taglib.util.IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public java.lang.String getContainerId() {
		return _containerId;
	}

	public java.lang.Long getDataLayoutId() {
		return _dataLayoutId;
	}

	public java.lang.Long getDataRecordId() {
		return _dataRecordId;
	}

	public java.util.Map getDataRecordValues() {
		return _dataRecordValues;
	}

	public java.lang.String getNamespace() {
		return _namespace;
	}

	public void setContainerId(java.lang.String containerId) {
		_containerId = containerId;
	}

	public void setDataLayoutId(java.lang.Long dataLayoutId) {
		_dataLayoutId = dataLayoutId;
	}

	public void setDataRecordId(java.lang.Long dataRecordId) {
		_dataRecordId = dataRecordId;
	}

	public void setDataRecordValues(java.util.Map dataRecordValues) {
		_dataRecordValues = dataRecordValues;
	}

	public void setNamespace(java.lang.String namespace) {
		_namespace = namespace;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		setServletContext(ServletContextUtil.getServletContext());
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_containerId = null;
		_dataLayoutId = null;
		_dataRecordId = null;
		_dataRecordValues = null;
		_namespace = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, "containerId", _containerId);
		setNamespacedAttribute(request, "dataLayoutId", _dataLayoutId);
		setNamespacedAttribute(request, "dataRecordId", _dataRecordId);
		setNamespacedAttribute(request, "dataRecordValues", _dataRecordValues);
		setNamespacedAttribute(request, "namespace", _namespace);
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "liferay-data-engine:data-layout-renderer:";

	private static final String _PAGE =
		"/data_layout_renderer/page.jsp";

	private java.lang.String _containerId = null;
	private java.lang.Long _dataLayoutId = null;
	private java.lang.Long _dataRecordId = null;
	private java.util.Map _dataRecordValues = null;
	private java.lang.String _namespace = null;

}