/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal;

import com.liferay.petra.lang.CentralizedThreadLocal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class PhloemNestedFieldsContextThreadLocal {

	public static void addContextName(String name) {
		LinkedList<String> contextNames = _contextNamesThreadLocal.get();

		contextNames.add(name);
	}

	public static Object getContextValue(String name) {
		Map<String, Object> contextValuesMap = _contextValuesThreadLocal.get();

		return contextValuesMap.get(name);
	}

	public static String getLastContextName() {
		LinkedList<String> contextNames = _contextNamesThreadLocal.get();

		if (contextNames.isEmpty()) {
			return null;
		}

		return contextNames.getLast();
	}

	public static void setContextValue(String name, Object value) {
		Map<String, Object> contextValuesMap = _contextValuesThreadLocal.get();

		contextValuesMap.put(name, value);
	}

	private static final ThreadLocal<LinkedList<String>>
		_contextNamesThreadLocal = new CentralizedThreadLocal<>(
			PhloemNestedFieldsContextThreadLocal.class +
				"._contextNamesThreadLocal",
			LinkedList::new);
	private static final ThreadLocal<Map<String, Object>>
		_contextValuesThreadLocal = new CentralizedThreadLocal<>(
			PhloemNestedFieldsContextThreadLocal.class +
				"._contextValuesThreadLocal",
			HashMap::new);

}