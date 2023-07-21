/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet;

import java.io.Serializable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

/**
 * @author     Michael C. Han
 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
 */
@Deprecated
public class SharedSessionAttributeCache implements Serializable {

	public static SharedSessionAttributeCache getInstance(HttpSession session) {
		synchronized (session) {
			SharedSessionAttributeCache cache =
				(SharedSessionAttributeCache)session.getAttribute(_SESSION_KEY);

			if (cache == null) {
				cache = new SharedSessionAttributeCache();

				session.setAttribute(_SESSION_KEY, cache);
			}

			return cache;
		}
	}

	public boolean contains(String name) {
		return _attributes.containsKey(name);
	}

	public Map<String, Object> getValues() {
		return _attributes;
	}

	public void removeAttribute(String key) {
		_attributes.remove(key);
	}

	public void setAttribute(String key, Object value) {
		_attributes.put(key, value);
	}

	private SharedSessionAttributeCache() {
		_attributes = new ConcurrentHashMap<>();
	}

	private static final String _SESSION_KEY =
		SharedSessionAttributeCache.class.getName();

	private final Map<String, Object> _attributes;

}