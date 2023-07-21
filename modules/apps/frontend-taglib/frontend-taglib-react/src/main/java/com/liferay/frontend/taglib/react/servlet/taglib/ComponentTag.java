/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.react.servlet.taglib;

import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolvedPackageNameUtil;
import com.liferay.frontend.taglib.react.internal.util.ReactRendererProvider;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.template.react.renderer.ComponentDescriptor;
import com.liferay.portal.template.react.renderer.ReactRenderer;
import com.liferay.taglib.util.ParamAndPropertyAncestorTagImpl;

import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * @author Chema Balsas
 */
public class ComponentTag extends ParamAndPropertyAncestorTagImpl {

	@Override
	public int doEndTag() throws JspException {
		JspWriter jspWriter = pageContext.getOut();

		Map<String, Object> data = getData();

		try {
			prepareData(data);

			ComponentDescriptor componentDescriptor = new ComponentDescriptor(
				getModule(), getComponentId(), null, isPositionInLine());

			ReactRenderer reactRenderer =
				ReactRendererProvider.getReactRenderer();

			reactRenderer.renderReact(
				componentDescriptor, data, request, jspWriter);
		}
		catch (Exception exception) {
			throw new JspException(exception);
		}
		finally {
			cleanUp();
		}

		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public String getComponentId() {
		return _componentId;
	}

	public String getModule() {
		if (_setServletContext) {
			String namespace = NPMResolvedPackageNameUtil.get(servletContext);

			return namespace.concat(
				"/"
			).concat(
				_module
			);
		}

		String namespace = NPMResolvedPackageNameUtil.get(
			pageContext.getServletContext());

		return namespace.concat(
			"/"
		).concat(
			_module
		);
	}

	@Override
	public void release() {
		super.release();

		_setServletContext = false;
	}

	public void setComponentId(String componentId) {
		_componentId = componentId;
	}

	public void setData(Map<String, Object> data) {
		_data = data;
	}

	public void setModule(String module) {
		_module = module;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);

		_setServletContext = true;
	}

	protected void cleanUp() {
		_componentId = null;
		_data = Collections.emptyMap();
		_module = null;
		_setServletContext = false;
	}

	protected Map<String, Object> getData() {
		return _data;
	}

	protected boolean isPositionInLine() {
		String fragmentId = ParamUtil.getString(request, "p_f_id");

		if (Validator.isNotNull(fragmentId)) {
			return true;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay.isIsolated() || themeDisplay.isLifecycleResource() ||
			themeDisplay.isStateExclusive()) {

			return true;
		}

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String portletId = portletDisplay.getId();

		if (Validator.isNotNull(portletId) &&
			themeDisplay.isPortletEmbedded(
				themeDisplay.getScopeGroupId(), themeDisplay.getLayout(),
				portletId)) {

			return true;
		}

		return false;
	}

	protected void prepareData(Map<String, Object> data) {
	}

	private String _componentId;
	private Map<String, Object> _data = Collections.emptyMap();
	private String _module;
	private boolean _setServletContext;

}