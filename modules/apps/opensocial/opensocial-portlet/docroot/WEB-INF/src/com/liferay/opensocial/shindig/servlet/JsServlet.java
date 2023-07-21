/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.shindig.servlet;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dennis Ju
 */
public class JsServlet extends org.apache.shindig.gadgets.servlet.JsServlet {

	@Override
	protected void doGet(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		String requestURI = httpServletRequest.getRequestURI();

		if (!requestURI.equals("/combo")) {
			super.doGet(httpServletRequest, httpServletResponse);

			return;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(
			httpServletRequest.getAttribute(
				JavaConstants.JAVAX_SERVLET_INCLUDE_REQUEST_URI));
		sb.append(CharPool.QUESTION);
		sb.append(
			httpServletRequest.getAttribute(
				JavaConstants.JAVAX_SERVLET_INCLUDE_QUERY_STRING));

		String urlString = PortalUtil.getAbsoluteURL(
			httpServletRequest, sb.toString());

		URL url = new URL(urlString);

		URLConnection urlConnection = url.openConnection();

		ServletResponseUtil.write(
			httpServletResponse, urlConnection.getInputStream());
	}

}