/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.internal.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Scott Lee
 * @author Alexander Chow
 */
public class AccountLock {

	public static boolean acquireLock(String key) {
		synchronized (key) {
			long nowTime = System.currentTimeMillis();

			if (_locks.containsKey(key)) {
				long timeLocked = _locks.get(key);

				long expireTime = timeLocked + _EXPIRY_TIME;

				if (nowTime < expireTime) {
					if (_log.isDebugEnabled()) {
						_log.debug("Lock has not expired for " + key);
					}

					return false;
				}
			}

			_locks.put(key, nowTime);
		}

		return true;
	}

	public static String getKey(long userId, long accountEntryId) {
		StringBundler sb = new StringBundler(3);

		sb.append(userId);
		sb.append(StringPool.UNDERLINE);
		sb.append(accountEntryId);

		return sb.toString();
	}

	public static void releaseLock(String key) {
		synchronized (key) {
			_locks.remove(key);
		}
	}

	private static final long _EXPIRY_TIME = Time.MINUTE * 15;

	private static final Log _log = LogFactoryUtil.getLog(AccountLock.class);

	private static final ConcurrentHashMap<String, Long> _locks =
		new ConcurrentHashMap<>();

}