/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.cache.thread.local;

import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class ThreadLocalCache<T> {

	public ThreadLocalCache(Object id, Lifecycle lifecycle) {
		_id = id;
		_lifecycle = lifecycle;
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #ThreadLocalCache(Object, Lifecycle)}
	 */
	@Deprecated
	public ThreadLocalCache(Serializable name, Lifecycle lifecycle) {
		this((Object)name, lifecycle);
	}

	public T get(String key) {
		if (_cache == null) {
			return null;
		}

		return _cache.get(key);
	}

	public Object getId() {
		return _id;
	}

	public Lifecycle getLifecycle() {
		return _lifecycle;
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link #getId()}
	 */
	@Deprecated
	public Serializable getName() {
		return _id.toString();
	}

	public void put(String key, T obj) {
		if (_cache == null) {
			_cache = new HashMap<>();
		}

		_cache.put(key, obj);
	}

	public void remove(String key) {
		if (_cache != null) {
			_cache.remove(key);
		}
	}

	public void removeAll() {
		if (_cache != null) {
			_cache.clear();
		}
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{cache=");
		sb.append(_cache.toString());
		sb.append(", id=");
		sb.append(_id);
		sb.append(", lifecycle=");
		sb.append(_lifecycle);
		sb.append("}");

		return sb.toString();
	}

	private Map<String, T> _cache;
	private final Object _id;
	private final Lifecycle _lifecycle;

}