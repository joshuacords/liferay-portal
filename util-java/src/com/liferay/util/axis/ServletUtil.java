/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.axis;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.axis.AxisEngine;
import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

/**
 * @author Brian Wing Shun Chan
 */
public class ServletUtil {

	public static HttpServletRequest getRequest() {
		MessageContext messageContext = AxisEngine.getCurrentMessageContext();

		return (HttpServletRequest)messageContext.getProperty(
			HTTPConstants.MC_HTTP_SERVLETREQUEST);
	}

	public static HttpServlet getServlet() {
		MessageContext messageContext = AxisEngine.getCurrentMessageContext();

		return (HttpServlet)messageContext.getProperty(
			HTTPConstants.MC_HTTP_SERVLET);
	}

	public static ServletContext getServletContext() {
		return getServlet().getServletContext();
	}

	public static HttpSession getSession() {
		HttpServletRequest httpServletRequest = getRequest();

		return httpServletRequest.getSession();
	}

}