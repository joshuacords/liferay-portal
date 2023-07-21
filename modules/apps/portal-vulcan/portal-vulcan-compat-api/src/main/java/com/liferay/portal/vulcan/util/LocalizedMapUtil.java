/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Brian Wing Shun Chan
 */
public class LocalizedMapUtil {

	public static Map<String, String> getLocalizedMap(
		boolean acceptAllLanguages, Map<Locale, String> localizedMap) {

		if (!acceptAllLanguages) {
			return null;
		}

		Map<String, String> map = new HashMap<>();

		for (Map.Entry<Locale, String> entry : localizedMap.entrySet()) {
			Locale locale = entry.getKey();

			map.put(locale.toLanguageTag(), entry.getValue());
		}

		return map;
	}

	public static Map<Locale, String> merge(
		Map<Locale, String> map, Map.Entry<Locale, String> entry) {

		if (map == null) {
			return Stream.of(
				entry
			).collect(
				Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
			);
		}

		if (entry == null) {
			return map;
		}

		if (entry.getValue() == null) {
			map.remove(entry.getKey());

			return map;
		}

		Set<Map.Entry<Locale, String>> mapEntries = map.entrySet();

		return Stream.concat(
			mapEntries.stream(), Stream.of(entry)
		).collect(
			Collectors.toMap(
				Map.Entry::getKey, Map.Entry::getValue,
				(value1, value2) -> value2)
		);
	}

	public static Map<Locale, String> patch(
		Map<Locale, String> map, Locale locale, String value) {

		if (value != null) {
			map.put(locale, value);
		}

		return map;
	}

}