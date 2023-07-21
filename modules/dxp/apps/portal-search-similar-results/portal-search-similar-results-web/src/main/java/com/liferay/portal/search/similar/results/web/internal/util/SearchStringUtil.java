/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.similar.results.web.internal.util;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author André de Oliveira
 */
public class SearchStringUtil {

	public static Optional<String> maybe(String s) {
		s = StringUtil.trim(s);

		if (Validator.isBlank(s)) {
			return Optional.empty();
		}

		return Optional.of(s);
	}

	public static String requireEquals(String expected, String actual) {
		if (!Objects.equals(expected, actual)) {
			throw new RuntimeException(actual + " != " + expected);
		}

		return actual;
	}

	public static String requireStartsWith(String expected, String actual) {
		if (!StringUtil.startsWith(actual, expected)) {
			throw new RuntimeException(actual + " /= " + expected);
		}

		return actual;
	}

	public static String[] splitAndUnquote(Optional<String> optional) {
		return optional.map(
			SearchStringUtil::splitAndUnquote
		).orElse(
			new String[0]
		);
	}

	public static String[] splitAndUnquote(String s) {
		return Stream.of(
			StringUtil.split(s.trim(), CharPool.COMMA)
		).map(
			String::trim
		).map(
			StringUtil::unquote
		).toArray(
			String[]::new
		);
	}

}