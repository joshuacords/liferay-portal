/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.concurrent;

import java.util.Objects;

/**
 * <p>
 * Represents a key that is used by ReadWriteLockRegistry. T must also be
 * immutable and properly implement the equals and hashCode methods.
 * </p>
 *
 * @author     Shuyang Zhou
 * @see        ReadWriteLockRegistry
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ReadWriteLockKey<T> {

	public ReadWriteLockKey(T key, boolean writeLock) {
		_key = key;
		_writeLock = writeLock;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReadWriteLockKey<?>)) {
			return false;
		}

		ReadWriteLockKey<T> readWriteLockKey = (ReadWriteLockKey<T>)obj;

		if (Objects.equals(_key, readWriteLockKey._key)) {
			return true;
		}

		return false;
	}

	public T getKey() {
		return _key;
	}

	@Override
	public int hashCode() {
		return _key.hashCode();
	}

	public boolean isWriteLock() {
		return _writeLock;
	}

	private final T _key;
	private final boolean _writeLock;

}