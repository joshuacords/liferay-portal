/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.renderer;

import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Marcellus Tavares
 */
public class DDMFormRenderingContext {

	public DDMFormRenderingContext() {
		setReturnFullContext(true);
	}

	public void addProperty(String key, Object value) {
		_properties.put(key, value);
	}

	public String getContainerId() {
		return _containerId;
	}

	public DDMFormValues getDDMFormValues() {
		return _ddmFormValues;
	}

	public long getGroupId() {
		return _groupId;
	}

	public HttpServletRequest getHttpServletRequest() {
		return _httpServletRequest;
	}

	public HttpServletResponse getHttpServletResponse() {
		return _httpServletResponse;
	}

	public Locale getLocale() {
		return _locale;
	}

	public String getPortletNamespace() {
		return _portletNamespace;
	}

	public <T> T getProperty(String key) {
		return (T)_properties.get(key);
	}

	public String getSubmitLabel() {
		return _submitLabel;
	}

	public boolean isReadOnly() {
		return _readOnly;
	}

	public boolean isReturnFullContext() {
		return MapUtil.getBoolean(_properties, "returnFullContext");
	}

	public boolean isSharedURL() {
		return MapUtil.getBoolean(_properties, "sharedURL");
	}

	public boolean isShowRequiredFieldsWarning() {
		return _showRequiredFieldsWarning;
	}

	public boolean isShowSubmitButton() {
		return _showSubmitButton;
	}

	public boolean isViewMode() {
		return MapUtil.getBoolean(_properties, "viewMode");
	}

	public void setContainerId(String containerId) {
		_containerId = containerId;
	}

	public void setDDMFormValues(DDMFormValues ddmFormValues) {
		_ddmFormValues = ddmFormValues;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		_httpServletRequest = httpServletRequest;
	}

	public void setHttpServletResponse(
		HttpServletResponse httpServletResponse) {

		_httpServletResponse = httpServletResponse;
	}

	public void setLocale(Locale locale) {
		_locale = locale;
	}

	public void setPortletNamespace(String portletNamespace) {
		_portletNamespace = portletNamespace;
	}

	public void setReadOnly(boolean readOnly) {
		_readOnly = readOnly;
	}

	public void setReturnFullContext(boolean fullContext) {
		_properties.put("returnFullContext", fullContext);
	}

	public void setSharedURL(boolean sharedURL) {
		_properties.put("sharedURL", sharedURL);
	}

	public void setShowRequiredFieldsWarning(
		boolean showRequiredFieldsWarning) {

		_showRequiredFieldsWarning = showRequiredFieldsWarning;
	}

	public void setShowSubmitButton(boolean showSubmitButton) {
		_showSubmitButton = showSubmitButton;
	}

	public void setSubmitLabel(String submitLabel) {
		_submitLabel = submitLabel;
	}

	public void setViewMode(boolean viewMode) {
		_properties.put("viewMode", viewMode);
	}

	private String _containerId;
	private DDMFormValues _ddmFormValues;
	private long _groupId;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private Locale _locale;
	private String _portletNamespace;
	private final Map<String, Object> _properties = new HashMap<>();
	private boolean _readOnly;
	private boolean _showRequiredFieldsWarning = true;
	private boolean _showSubmitButton = true;
	private String _submitLabel;

}