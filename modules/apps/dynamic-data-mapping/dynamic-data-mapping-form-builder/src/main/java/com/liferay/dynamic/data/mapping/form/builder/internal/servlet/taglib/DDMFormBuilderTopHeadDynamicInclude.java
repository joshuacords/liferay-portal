/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.servlet.taglib;

import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(immediate = true, service = DynamicInclude.class)
public class DDMFormBuilderTopHeadDynamicInclude extends BaseDynamicInclude {

	@Activate
	public void activate() {
		_postfix = _portal.getPathProxy();

		if (_postfix.isEmpty()) {
			_postfix = _servletContext.getContextPath();
		}
		else {
			_postfix = _postfix.concat(_servletContext.getContextPath());
		}
	}

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String key)
		throws IOException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PrintWriter printWriter = httpServletResponse.getWriter();

		String cdnBaseURL = themeDisplay.getCDNBaseURL();

		String staticResourceURL = _portal.getStaticResourceURL(
			httpServletRequest,
			cdnBaseURL.concat(
				_postfix
			).concat(
				"/css/main.css"
			));

		String content = "<link href=\"".concat(staticResourceURL);

		printWriter.println(
			content.concat("\" rel=\"stylesheet\" type = \"text/css\" />"));
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register(
			"com.liferay.data.engine.taglib#/data_layout_builder/start.jsp" +
				"#pre");
		dynamicIncludeRegistry.register(
			"com.liferay.dynamic.data.mapping.form.web#" +
				"EditElementSetInstanceMVCRenderCommand#render");
		dynamicIncludeRegistry.register(
			"com.liferay.dynamic.data.mapping.form.web#" +
				"EditFormInstanceMVCRenderCommand#render");
	}

	@Reference
	private Portal _portal;

	private String _postfix;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.dynamic.data.mapping.form.builder)"
	)
	private ServletContext _servletContext;

}