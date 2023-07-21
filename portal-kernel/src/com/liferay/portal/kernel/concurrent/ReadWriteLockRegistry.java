/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * Registry for {@link ReadWriteLock} objects with {@link ReadWriteLockKey} as
 * keys. The behavior of acquiring and releasing locks is provided by a {@link
 * ConcurrentHashMap}. This class is completely thread safe and ensures that
 * only one {@link ReadWriteLock} exists per key.
 * </p>
 *
 * @author     Shuyang Zhou
 * @see        ReadWriteLock
 * @see        ReadWriteLockKey
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ReadWriteLockRegistry {

	public Lock acquireLock(ReadWriteLockKey<?> readWriteLockKey) {
		ReadWriteLock readWriteLock = _readWriteLockMap.get(readWriteLockKey);

		if (readWriteLock == null) {
			ReadWriteLock newReadWriteLock = new ReentrantReadWriteLock();

			readWriteLock = _readWriteLockMap.putIfAbsent(
				readWriteLockKey, newReadWriteLock);

			if (readWriteLock == null) {
				readWriteLock = newReadWriteLock;
			}
		}

		if (readWriteLockKey.isWriteLock()) {
			return readWriteLock.writeLock();
		}

		return readWriteLock.readLock();
	}

	public void releaseLock(ReadWriteLockKey<?> readWriteLockKey) {
		if (readWriteLockKey.isWriteLock()) {
			_readWriteLockMap.remove(readWriteLockKey);
		}
	}

	private final ConcurrentMap<ReadWriteLockKey<?>, ReadWriteLock>
		_readWriteLockMap = new ConcurrentHashMap<>();

}