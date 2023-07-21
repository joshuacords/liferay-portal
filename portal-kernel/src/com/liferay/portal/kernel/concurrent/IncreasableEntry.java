/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.concurrent;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.util.Objects;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public abstract class IncreasableEntry<K, V> {

	public IncreasableEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IncreasableEntry<?, ?>)) {
			return false;
		}

		IncreasableEntry<K, V> increasableEntry = (IncreasableEntry<K, V>)obj;

		if (Objects.equals(key, increasableEntry.key) &&
			Objects.equals(value, increasableEntry.value)) {

			return true;
		}

		return false;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, key);

		return HashUtil.hash(hash, value);
	}

	public abstract IncreasableEntry<K, V> increase(V deltaValue);

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{key=");
		sb.append(key);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	protected final K key;
	protected final V value;

}