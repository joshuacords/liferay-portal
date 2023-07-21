/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.shindig.servlet;

import com.google.inject.Injector;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shindig.common.servlet.GuiceServletContextListener;

/**
 * @author Igor Spasic
 */
public class AuthenticationServletFilter
	extends org.apache.shindig.auth.AuthenticationServletFilter {

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		if (injector == null) {
			HttpServletRequest httpServletRequest =
				(HttpServletRequest)servletRequest;

			HttpSession session = httpServletRequest.getSession();

			_init(session.getServletContext());
		}

		super.doFilter(servletRequest, servletResponse, filterChain);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		// LPS-23577 and LPS-41715

		injector = null;
	}

	private void _init(ServletContext servletContext) throws ServletException {
		injector = (Injector)servletContext.getAttribute(
			GuiceServletContextListener.INJECTOR_ATTRIBUTE);

		if (injector == null) {
			injector = (Injector)servletContext.getAttribute(
				GuiceServletContextListener.INJECTOR_NAME);

			if (injector == null) {
				throw new UnavailableException(
					"Guice injector is not available. Please register " +
						GuiceServletContextListener.class.getName() + ".");
			}
		}

		injector.injectMembers(this);
	}

}