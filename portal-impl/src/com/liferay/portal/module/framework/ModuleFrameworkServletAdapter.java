/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.module.framework;

import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.registry.collections.ServiceTrackerCollections;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Miguel Pastor
 * @author Raymond Augé
 */
public class ModuleFrameworkServletAdapter extends HttpServlet {

	@Override
	protected void service(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		if (_servlets.isEmpty()) {
			PortalUtil.sendError(
				HttpServletResponse.SC_SERVICE_UNAVAILABLE,
				new ServletException("Module framework is unavailable"),
				httpServletRequest, httpServletResponse);

			return;
		}

		HttpServlet httpServlet = _servlets.get(0);

		httpServlet.service(httpServletRequest, httpServletResponse);
	}

	private final List<HttpServlet> _servlets =
		ServiceTrackerCollections.openList(
			HttpServlet.class,
			"(&(bean.id=" + HttpServlet.class.getName() +
				")(original.bean=*))");

}