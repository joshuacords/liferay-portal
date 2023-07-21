/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PortalSessionContext;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Time;

import java.util.Collection;

import javax.servlet.http.HttpSession;

/**
 * @author Alexander Chow
 */
public class MaintenanceUtil {

	public static void appendStatus(String status) {
		if (_log.isDebugEnabled()) {
			_log.debug(status);
		}

		StringBundler sb = new StringBundler(4);

		sb.append(Time.getRFC822());
		sb.append(StringPool.SPACE);
		sb.append(HtmlUtil.escape(status));
		sb.append("<br />");

		_status = _status.concat(sb.toString());
	}

	public static void cancel() {
		HttpSession session = PortalSessionContext.get(_sessionId);

		if (session != null) {
			session.invalidate();
		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn("Session " + _sessionId + " is null");
			}
		}

		_maintaining = false;
	}

	public static String getClassName() {
		return _className;
	}

	public static String getSessionId() {
		return _sessionId;
	}

	public static String getStatus() {
		return _status;
	}

	public static boolean isMaintaining() {
		return _maintaining;
	}

	public static void maintain(String sessionId, String className) {
		_sessionId = sessionId;
		_className = className;
		_maintaining = true;
		_status = StringPool.BLANK;

		appendStatus("Executing " + _className);

		Collection<HttpSession> sessions = PortalSessionContext.values();

		for (HttpSession session : sessions) {
			if (!sessionId.equals(session.getId())) {
				try {
					session.invalidate();
				}
				catch (IllegalStateException illegalStateException) {
				}
			}
		}
	}

	private MaintenanceUtil() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MaintenanceUtil.class);

	private static volatile String _className;
	private static volatile boolean _maintaining;
	private static volatile String _sessionId;
	private static volatile String _status = StringPool.BLANK;

}