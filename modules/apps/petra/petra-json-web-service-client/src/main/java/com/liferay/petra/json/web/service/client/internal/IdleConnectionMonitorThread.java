/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.json.web.service.client.internal;

import java.util.concurrent.TimeUnit;

import org.apache.http.nio.conn.NHttpClientConnectionManager;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class IdleConnectionMonitorThread extends Thread {

	public IdleConnectionMonitorThread(
		NHttpClientConnectionManager nHttpClientConnectionManager) {

		_nHttpClientConnectionManager = nHttpClientConnectionManager;
	}

	@Override
	public void run() {
		try {
			while (!_shutdown) {
				synchronized (this) {
					wait(5000);

					_nHttpClientConnectionManager.closeExpiredConnections();

					_nHttpClientConnectionManager.closeIdleConnections(
						30, TimeUnit.SECONDS);
				}
			}
		}
		catch (InterruptedException interruptedException) {
		}
	}

	public void shutdown() {
		_shutdown = true;

		synchronized (this) {
			notifyAll();
		}
	}

	private final NHttpClientConnectionManager _nHttpClientConnectionManager;
	private volatile boolean _shutdown;

}