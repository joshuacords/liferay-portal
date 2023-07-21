/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet.filters.urlrewrite;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author László Csontos
 */
public class UrlRewriteFilter extends BasePortalFilter {

	@Override
	public void destroy() {
		if (_urlRewriteFilter != null) {
			_urlRewriteFilter.destroy();
		}

		super.destroy();
	}

	@Override
	public void init(FilterConfig filterConfig) {
		super.init(filterConfig);

		_urlRewriteFilter =
			new org.tuckey.web.filters.urlrewrite.UrlRewriteFilter();

		try {
			_urlRewriteFilter.init(filterConfig);
		}
		catch (ServletException servletException) {
			_urlRewriteFilter = null;

			_log.error(servletException, servletException);
		}
	}

	@Override
	protected void processFilter(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws Exception {

		if (_urlRewriteFilter != null) {
			_urlRewriteFilter.doFilter(
				httpServletRequest, httpServletResponse, filterChain);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UrlRewriteFilter.class);

	private org.tuckey.web.filters.urlrewrite.UrlRewriteFilter
		_urlRewriteFilter;

}