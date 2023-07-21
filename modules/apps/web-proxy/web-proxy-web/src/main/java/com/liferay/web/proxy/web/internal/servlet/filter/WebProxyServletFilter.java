/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.web.proxy.web.internal.servlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.web.proxy.web.internal.servlet.request.WebProxyServletRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Daniel Couso
 */
@Component(
	immediate = true,
	property = {
		"before-filter=Valid Host Name Filter", "dispatcher=FORWARD",
		"dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=Web Proxy Filter",
		"url-pattern=/o/web-proxy-web/pbhs/*"
	},
	service = Filter.class
)
public class WebProxyServletFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		String contentType = httpServletRequest.getHeader(
			HttpHeaders.CONTENT_TYPE);

		if ((contentType != null) &&
			contentType.startsWith(
				ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED)) {

			return true;
		}

		return false;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws Exception {

		WebProxyServletRequest webProxyServletRequest =
			new WebProxyServletRequest(httpServletRequest);

		processFilter(
			WebProxyServletFilter.class.getName(), webProxyServletRequest,
			httpServletResponse, filterChain);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WebProxyServletFilter.class);

}