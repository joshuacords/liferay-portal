/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.poller.comet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class CometHandlerPool {

	public void closeCometHandler(String sessionId) throws CometException {
		if (_log.isDebugEnabled()) {
			_log.debug("Close comet handler " + sessionId);
		}

		Lock writeLock = _cometHandlerPoolReadWriteLock.writeLock();

		try {
			writeLock.lock();

			CometHandler cometHandler = _cometHandlers.remove(sessionId);

			if (cometHandler != null) {
				cometHandler.destroy();
			}
		}
		finally {
			writeLock.unlock();
		}
	}

	public void closeCometHandlers() throws CometException {
		Lock writeLock = _cometHandlerPoolReadWriteLock.writeLock();

		try {
			writeLock.lock();

			Set<Map.Entry<String, CometHandler>> cometHandlers =
				_cometHandlers.entrySet();

			Iterator<Map.Entry<String, CometHandler>> itr =
				cometHandlers.iterator();

			while (itr.hasNext()) {
				Map.Entry<String, CometHandler> entry = itr.next();

				CometHandler cometHandler = entry.getValue();

				if (cometHandler != null) {
					cometHandler.destroy();
				}

				itr.remove();
			}
		}
		finally {
			writeLock.unlock();
		}
	}

	public CometHandler getCometHandler(String sessionId) {
		Lock readLock = _cometHandlerPoolReadWriteLock.readLock();

		try {
			readLock.lock();

			return _cometHandlers.get(sessionId);
		}
		finally {
			readLock.unlock();
		}
	}

	public void startCometHandler(
			CometSession cometSession, CometHandler cometHandler)
		throws CometException {

		String sessionId = cometSession.getSessionId();

		if (_log.isDebugEnabled()) {
			_log.debug("Start comet handler " + sessionId);
		}

		Lock writeLock = _cometHandlerPoolReadWriteLock.writeLock();

		try {
			writeLock.lock();

			if (_cometHandlers.containsKey(sessionId)) {
				closeCometHandler(sessionId);
			}

			_cometHandlers.put(sessionId, cometHandler);

			if (_log.isWarnEnabled()) {
				_log.warn("Initialize comet handler " + sessionId);
			}

			cometHandler.init(cometSession);
		}
		finally {
			writeLock.unlock();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CometHandlerPool.class);

	private final ReadWriteLock _cometHandlerPoolReadWriteLock =
		new ReentrantReadWriteLock();
	private final Map<String, CometHandler> _cometHandlers = new HashMap<>();

}