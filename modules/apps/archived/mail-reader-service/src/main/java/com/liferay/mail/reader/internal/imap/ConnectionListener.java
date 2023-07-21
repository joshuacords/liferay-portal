/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.internal.imap;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.mail.event.ConnectionEvent;

/**
 * @author Alexander Chow
 */
public class ConnectionListener implements javax.mail.event.ConnectionListener {

	public ConnectionListener(String service) {
		_service = service;
	}

	@Override
	public void closed(ConnectionEvent connectionEvent) {
		if (_log.isDebugEnabled()) {
			long uptime = (System.currentTimeMillis() - _startTime) / 1000;

			_log.debug(
				StringBundler.concat(
					"Closed ", _service, " after ", uptime, "seconds"));
		}
	}

	@Override
	public void disconnected(ConnectionEvent connectionEvent) {
		if (_log.isDebugEnabled()) {
			long uptime = (System.currentTimeMillis() - _startTime) / 1000;

			_log.debug(
				StringBundler.concat(
					"Disconnected ", _service, " after ", uptime, "seconds"));
		}
	}

	@Override
	public void opened(ConnectionEvent connectionEvent) {
		_startTime = System.currentTimeMillis();

		if (_log.isDebugEnabled()) {
			_log.debug("Opened " + _service);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConnectionListener.class);

	private final String _service;
	private long _startTime;

}