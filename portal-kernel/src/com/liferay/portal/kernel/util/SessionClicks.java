/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;

import java.util.ConcurrentModificationException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class SessionClicks {

	public static String get(
		HttpServletRequest httpServletRequest, String key,
		String defaultValue) {

		return get(httpServletRequest, _DEFAULT_NAMESPACE, key, defaultValue);
	}

	public static String get(
		HttpServletRequest httpServletRequest, String namespace, String key,
		String defaultValue) {

		try {
			PortalPreferences portalPreferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					httpServletRequest);

			return portalPreferences.getValue(namespace, key, defaultValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return null;
		}
	}

	public static String get(
		HttpSession session, String key, String defaultValue) {

		return get(session, _DEFAULT_NAMESPACE, key, defaultValue);
	}

	public static String get(
		HttpSession session, String namespace, String key,
		String defaultValue) {

		String sessionKey = namespace.concat(
			StringPool.COLON
		).concat(
			key
		);

		return GetterUtil.getString(
			session.getAttribute(sessionKey), defaultValue);
	}

	public static void put(
		HttpServletRequest httpServletRequest, String key, String value) {

		put(httpServletRequest, _DEFAULT_NAMESPACE, key, value);
	}

	public static void put(
		HttpServletRequest httpServletRequest, String namespace, String key,
		String value) {

		if (!_isValidKeyValue(key, value)) {
			return;
		}

		while (true) {
			try {
				PortalPreferences portalPreferences =
					PortletPreferencesFactoryUtil.getPortalPreferences(
						httpServletRequest);

				int size = portalPreferences.size();

				if (_isValidSize(size, key, value)) {
					portalPreferences.setValue(namespace, key, value);
				}

				break;
			}
			catch (ConcurrentModificationException
						concurrentModificationException) {
			}
			catch (Exception exception) {
				_log.error(exception, exception);

				break;
			}
		}
	}

	public static void put(HttpSession session, String key, String value) {
		put(session, _DEFAULT_NAMESPACE, key, value);
	}

	public static void put(
		HttpSession session, String namespace, String key, String value) {

		if (!_isValidKeyValue(key, value)) {
			return;
		}

		Enumeration<String> enumeration = session.getAttributeNames();

		int size = 0;

		while (enumeration.hasMoreElements()) {
			enumeration.nextElement();

			size++;
		}

		if (_isValidSize(size, key, value)) {
			String sessionKey = StringBundler.concat(
				namespace, StringPool.COLON, key);

			session.setAttribute(sessionKey, value);
		}
	}

	private static boolean _isValidKeyValue(String key, String value) {
		if ((key.length() <= _SESSION_CLICKS_MAX_SIZE_TERMS) &&
			(value.length() <= _SESSION_CLICKS_MAX_SIZE_TERMS)) {

			return true;
		}

		if (_log.isWarnEnabled()) {
			_log.warn(
				StringBundler.concat(
					"Session clicks has attempted to exceed the maximum size ",
					"allowed for keys or values with key \"", key,
					"\" and value \"", value, "\""));
		}

		return false;
	}

	private static boolean _isValidSize(int size, String key, String value) {
		if (size < _SESSION_CLICKS_MAX_ALLOWED_VALUES) {
			return true;
		}

		if (_log.isWarnEnabled()) {
			_log.warn(
				StringBundler.concat(
					"Session clicks has attempted to exceed the maximum ",
					"number of allowed values with key \"", key,
					"\" and value \"", value, "\""));
		}

		return false;
	}

	private static final String _DEFAULT_NAMESPACE =
		SessionClicks.class.getName();

	private static final int _SESSION_CLICKS_MAX_ALLOWED_VALUES =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.SESSION_CLICKS_MAX_ALLOWED_VALUES));

	private static final int _SESSION_CLICKS_MAX_SIZE_TERMS =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.SESSION_CLICKS_MAX_SIZE_TERMS));

	private static final Log _log = LogFactoryUtil.getLog(SessionClicks.class);

}