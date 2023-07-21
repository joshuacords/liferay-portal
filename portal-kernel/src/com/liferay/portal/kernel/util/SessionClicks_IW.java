/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

/**
 * @author Brian Wing Shun Chan
 */
public class SessionClicks_IW {
	public static SessionClicks_IW getInstance() {
		return _instance;
	}

	public java.lang.String get(
		javax.servlet.http.HttpServletRequest httpServletRequest,
		java.lang.String key, java.lang.String defaultValue) {
		return SessionClicks.get(httpServletRequest, key, defaultValue);
	}

	public java.lang.String get(
		javax.servlet.http.HttpServletRequest httpServletRequest,
		java.lang.String namespace, java.lang.String key,
		java.lang.String defaultValue) {
		return SessionClicks.get(httpServletRequest, namespace, key,
			defaultValue);
	}

	public java.lang.String get(javax.servlet.http.HttpSession session,
		java.lang.String key, java.lang.String defaultValue) {
		return SessionClicks.get(session, key, defaultValue);
	}

	public java.lang.String get(javax.servlet.http.HttpSession session,
		java.lang.String namespace, java.lang.String key,
		java.lang.String defaultValue) {
		return SessionClicks.get(session, namespace, key, defaultValue);
	}

	public void put(javax.servlet.http.HttpServletRequest httpServletRequest,
		java.lang.String key, java.lang.String value) {
		SessionClicks.put(httpServletRequest, key, value);
	}

	public void put(javax.servlet.http.HttpServletRequest httpServletRequest,
		java.lang.String namespace, java.lang.String key, java.lang.String value) {
		SessionClicks.put(httpServletRequest, namespace, key, value);
	}

	public void put(javax.servlet.http.HttpSession session,
		java.lang.String key, java.lang.String value) {
		SessionClicks.put(session, key, value);
	}

	public void put(javax.servlet.http.HttpSession session,
		java.lang.String namespace, java.lang.String key, java.lang.String value) {
		SessionClicks.put(session, namespace, key, value);
	}

	private SessionClicks_IW() {
	}

	private static SessionClicks_IW _instance = new SessionClicks_IW();
}