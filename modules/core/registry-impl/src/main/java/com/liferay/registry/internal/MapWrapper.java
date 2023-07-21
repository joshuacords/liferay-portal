/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.internal;

import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class MapWrapper extends Dictionary<String, Object> {

	public MapWrapper(Map<String, Object> map) {
		_map = map;
	}

	@Override
	public Enumeration<Object> elements() {
		if (_map == null) {
			return Collections.enumeration(Collections.emptyList());
		}

		return Collections.enumeration(_map.values());
	}

	@Override
	public Object get(Object key) {
		if (_map == null) {
			return null;
		}

		return _map.get(key);
	}

	@Override
	public boolean isEmpty() {
		if (_map == null) {
			return true;
		}

		return _map.isEmpty();
	}

	@Override
	public Enumeration<String> keys() {
		if (_map == null) {
			return Collections.enumeration(Collections.<String>emptyList());
		}

		return Collections.enumeration(_map.keySet());
	}

	@Override
	public Object put(String key, Object value) {
		if (_map == null) {
			return null;
		}

		return _map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		if (_map == null) {
			return null;
		}

		return _map.remove(key);
	}

	@Override
	public int size() {
		if (_map == null) {
			return 0;
		}

		return _map.size();
	}

	private Map<String, Object> _map;

}