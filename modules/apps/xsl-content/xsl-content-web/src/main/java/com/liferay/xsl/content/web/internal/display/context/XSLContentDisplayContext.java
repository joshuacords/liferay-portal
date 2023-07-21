/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.xsl.content.web.internal.display.context;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.xsl.content.web.internal.configuration.XSLContentConfiguration;
import com.liferay.xsl.content.web.internal.configuration.XSLContentPortletInstanceConfiguration;
import com.liferay.xsl.content.web.internal.util.XSLContentUtil;

import java.net.URL;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Juergen Kappler
 */
public class XSLContentDisplayContext {

	public XSLContentDisplayContext(
			HttpServletRequest httpServletRequest,
			XSLContentConfiguration xslContentConfiguration)
		throws ConfigurationException {

		_xslContentConfiguration = xslContentConfiguration;

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = _themeDisplay.getPortletDisplay();

		_xslContentPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				XSLContentPortletInstanceConfiguration.class);
	}

	public String getContent(ServletRequest servletRequest) throws Exception {
		if (_content != null) {
			return _content;
		}

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)servletRequest;

		String contextPath = httpServletRequest.getContextPath();

		String xmlUrl = XSLContentUtil.replaceUrlTokens(
			_themeDisplay, contextPath,
			_xslContentPortletInstanceConfiguration.xmlUrl());

		String xslUrl = XSLContentUtil.replaceUrlTokens(
			_themeDisplay, contextPath,
			_xslContentPortletInstanceConfiguration.xslUrl());

		_content = XSLContentUtil.transform(
			_xslContentConfiguration, new URL(xmlUrl), new URL(xslUrl));

		return _content;
	}

	public XSLContentPortletInstanceConfiguration
		getXSLContentPortletInstanceConfiguration() {

		return _xslContentPortletInstanceConfiguration;
	}

	private String _content;
	private final ThemeDisplay _themeDisplay;
	private final XSLContentConfiguration _xslContentConfiguration;
	private final XSLContentPortletInstanceConfiguration
		_xslContentPortletInstanceConfiguration;

}