/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.facebook;

import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BufferCacheServletResponse;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.servlet.filters.gzip.GZipFilter;
import com.liferay.portlet.social.util.FacebookUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class FacebookServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		try {
			String[] facebookData = FacebookUtil.getFacebookData(
				httpServletRequest);

			if ((facebookData == null) ||
				!PortalUtil.isValidResourceId(facebookData[1])) {

				PortalUtil.sendError(
					HttpServletResponse.SC_NOT_FOUND,
					new NoSuchLayoutException(), httpServletRequest,
					httpServletResponse);
			}
			else {
				String facebookCanvasPageURL = facebookData[0];
				String redirect = facebookData[1];

				httpServletRequest.setAttribute(
					GZipFilter.SKIP_FILTER, Boolean.TRUE);
				httpServletRequest.setAttribute(
					WebKeys.FACEBOOK_CANVAS_PAGE_URL, facebookCanvasPageURL);

				ServletContext servletContext = getServletContext();

				RequestDispatcher requestDispatcher =
					servletContext.getRequestDispatcher(redirect);

				BufferCacheServletResponse bufferCacheServletResponse =
					new BufferCacheServletResponse(httpServletResponse);

				requestDispatcher.forward(
					httpServletRequest, bufferCacheServletResponse);

				String fbml = bufferCacheServletResponse.getString();

				fbml = fixFbml(fbml);

				ServletResponseUtil.write(httpServletResponse, fbml);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception,
				httpServletRequest, httpServletResponse);
		}
	}

	protected String fixFbml(String fbml) {
		return StringUtil.removeSubstrings(fbml, "<nobr>", "</nobr>");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FacebookServlet.class);

}