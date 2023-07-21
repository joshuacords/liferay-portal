/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Raymond Augé
 */
public class UnmodifiableCaseInsensitiveDictionaryMap<V>
	implements Map<String, V> {

	public UnmodifiableCaseInsensitiveDictionaryMap(
		Dictionary<String, V> dictionary) {

		Map<String, V> map = new HashMap<>();

		if (dictionary != null) {
			for (Enumeration<String> enumeration = dictionary.keys();
				 enumeration.hasMoreElements();) {

				String key = enumeration.nextElement();

				map.put(key, dictionary.get(key));
			}
		}

		_map = Collections.unmodifiableMap(map);
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(Object key) {
		return _map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return _map.containsValue(value);
	}

	@Override
	public Set<Map.Entry<String, V>> entrySet() {
		return _map.entrySet();
	}

	@Override
	public V get(Object key) {
		if (!(key instanceof String)) {
			return null;
		}

		String keyString = (String)key;

		V value = _map.get(keyString);

		if (value == null) {
			value = _map.get(keyString.toLowerCase());
		}

		return value;
	}

	@Override
	public boolean isEmpty() {
		return _map.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return _map.keySet();
	}

	@Override
	public V put(String key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends String, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public V remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return _map.size();
	}

	@Override
	public Collection<V> values() {
		return _map.values();
	}

	private final Map<String, V> _map;

}