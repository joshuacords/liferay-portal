/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Carlos Sierra Andrés
 */
public class PortletResourceStaticURLGenerator {

	public List<String> generate(
		Portlet portlet, PortletResourceAccessor... portletResourceAccessors) {

		List<String> urls = new ArrayList<>();

		for (PortletResourceAccessor portletResourceAccessor :
				portletResourceAccessors) {

			String contextPath = null;

			if (portletResourceAccessor.isPortalResource()) {
				contextPath = PortalUtil.getPathContext();
			}
			else {
				contextPath =
					PortalUtil.getPathProxy() + portlet.getContextPath();
			}

			List<String> portletResources = portletResourceAccessor.get(
				portlet);

			for (String portletResource : portletResources) {
				if (!HttpUtil.hasProtocol(portletResource)) {
					Portlet rootPortlet = portlet.getRootPortlet();

					portletResource = PortalUtil.getStaticResourceURL(
						_httpServletRequest, contextPath + portletResource,
						rootPortlet.getTimestamp());
				}

				if (!portletResource.contains(Http.PROTOCOL_DELIMITER)) {
					String cdnBaseURL = _themeDisplay.getCDNBaseURL();

					portletResource = cdnBaseURL.concat(portletResource);
				}

				if (!_visitedURLs.contains(portletResource)) {
					urls.add(portletResource);

					_visitedURLs.add(portletResource);
				}
			}
		}

		return urls;
	}

	public void setRequest(HttpServletRequest httpServletRequest) {
		_httpServletRequest = httpServletRequest;
		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public void setVisitedURLs(Set<String> visitedURLs) {
		_visitedURLs = visitedURLs;
	}

	private HttpServletRequest _httpServletRequest;
	private ThemeDisplay _themeDisplay;
	private Set<String> _visitedURLs;

}