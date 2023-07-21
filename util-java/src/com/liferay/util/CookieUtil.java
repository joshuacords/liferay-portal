/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author     Brian Wing Shun Chan
 * @author     Shuyang Zhou
 * @deprecated As of Newton (6.2.x), replaced by {@link
 *             com.liferay.portal.kernel.util.CookieKeys}
 */
@Deprecated
public class CookieUtil {

	public static String get(
		HttpServletRequest httpServletRequest, String name) {

		return get(httpServletRequest, name, true);
	}

	public static String get(
		HttpServletRequest httpServletRequest, String name,
		boolean toUpperCase) {

		Map<String, Cookie> cookieMap = _getCookieMap(httpServletRequest);

		if (toUpperCase) {
			name = StringUtil.toUpperCase(name);
		}

		Cookie cookie = cookieMap.get(name);

		if (cookie == null) {
			return null;
		}

		return cookie.getValue();
	}

	private static Map<String, Cookie> _getCookieMap(
		HttpServletRequest httpServletRequest) {

		Map<String, Cookie> cookieMap =
			(Map<String, Cookie>)httpServletRequest.getAttribute(
				CookieUtil.class.getName());

		if (cookieMap != null) {
			return cookieMap;
		}

		Cookie[] cookies = httpServletRequest.getCookies();

		if (cookies == null) {
			cookieMap = Collections.emptyMap();
		}
		else {
			cookieMap = new HashMap<>(cookies.length * 4 / 3);

			for (Cookie cookie : cookies) {
				String cookieName = GetterUtil.getString(cookie.getName());

				cookieName = StringUtil.toUpperCase(cookieName);

				cookieMap.put(cookieName, cookie);
			}
		}

		httpServletRequest.setAttribute(CookieUtil.class.getName(), cookieMap);

		return cookieMap;
	}

}