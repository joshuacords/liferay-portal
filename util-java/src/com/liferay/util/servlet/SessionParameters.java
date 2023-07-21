/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.servlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class SessionParameters {

	public static final String KEY = SessionParameters.class.getName();

	public static final boolean USE_SESSION_PARAMETERS = GetterUtil.getBoolean(
		SystemProperties.get(SessionParameters.class.getName()), true);

	public static String get(
		HttpServletRequest httpServletRequest, String parameter) {

		return get(httpServletRequest.getSession(), parameter);
	}

	public static String get(HttpSession session, String parameter) {
		if (!USE_SESSION_PARAMETERS) {
			return parameter;
		}

		Map<String, String> parameters = _getParameters(session);

		String newParameter = parameters.get(parameter);

		if (newParameter == null) {
			newParameter =
				StringUtil.randomString() + StringPool.UNDERLINE + parameter;

			parameters.put(parameter, newParameter);
		}

		return newParameter;
	}

	public static String get(PortletRequest portletRequest, String parameter) {
		return get(portletRequest.getPortletSession(), parameter);
	}

	public static String get(PortletSession portletSession, String parameter) {
		if (!USE_SESSION_PARAMETERS) {
			return parameter;
		}

		Map<String, String> parameters = _getParameters(portletSession);

		String newParameter = parameters.get(parameter);

		if (newParameter == null) {
			newParameter =
				StringUtil.randomString() + StringPool.UNDERLINE + parameter;

			parameters.put(parameter, newParameter);
		}

		return newParameter;
	}

	private static Map<String, String> _getParameters(HttpSession session) {
		Map<String, String> parameters = null;

		try {
			parameters = (Map<String, String>)session.getAttribute(KEY);

			if (parameters == null) {
				parameters = new HashMap<>();

				session.setAttribute(KEY, parameters);
			}
		}
		catch (IllegalStateException illegalStateException) {
			parameters = new HashMap<>();
		}

		return parameters;
	}

	private static Map<String, String> _getParameters(
		PortletSession portletSession) {

		Map<String, String> parameters = null;

		try {
			parameters = (Map<String, String>)portletSession.getAttribute(KEY);

			if (parameters == null) {
				parameters = new LinkedHashMap<>();

				portletSession.setAttribute(KEY, parameters);
			}
		}
		catch (IllegalStateException illegalStateException) {
			parameters = new LinkedHashMap<>();
		}

		return parameters;
	}

}