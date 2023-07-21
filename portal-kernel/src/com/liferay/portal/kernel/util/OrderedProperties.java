/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderedProperties extends Properties {

	public OrderedProperties() {
		_names = new Vector<>();
	}

	@Override
	public Enumeration<String> propertyNames() {
		return _names.elements();
	}

	@Override
	public Object put(Object key, Object value) {
		if (_names.contains(key)) {
			_names.remove(key);
		}

		_names.add((String)key);

		return super.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		_names.remove(key);

		return super.remove(key);
	}

	private final Vector<String> _names;

}