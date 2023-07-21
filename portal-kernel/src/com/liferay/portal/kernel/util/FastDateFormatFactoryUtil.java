/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.text.Format;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class FastDateFormatFactoryUtil {

	public static Format getDate(int style, Locale locale, TimeZone timeZone) {
		return getFastDateFormatFactory().getDate(style, locale, timeZone);
	}

	public static Format getDate(Locale locale) {
		return getFastDateFormatFactory().getDate(locale);
	}

	public static Format getDate(Locale locale, TimeZone timeZone) {
		return getFastDateFormatFactory().getDate(locale, timeZone);
	}

	public static Format getDate(TimeZone timeZone) {
		return getFastDateFormatFactory().getDate(timeZone);
	}

	public static Format getDateTime(
		int dateStyle, int timeStyle, Locale locale, TimeZone timeZone) {

		return getFastDateFormatFactory().getDateTime(
			dateStyle, timeStyle, locale, timeZone);
	}

	public static Format getDateTime(Locale locale) {
		return getFastDateFormatFactory().getDateTime(locale);
	}

	public static Format getDateTime(Locale locale, TimeZone timeZone) {
		return getFastDateFormatFactory().getDateTime(locale, timeZone);
	}

	public static Format getDateTime(TimeZone timeZone) {
		return getFastDateFormatFactory().getDateTime(timeZone);
	}

	public static FastDateFormatFactory getFastDateFormatFactory() {
		return _fastDateFormatFactory;
	}

	public static Format getSimpleDateFormat(String pattern) {
		return getFastDateFormatFactory().getSimpleDateFormat(pattern);
	}

	public static Format getSimpleDateFormat(String pattern, Locale locale) {
		return getFastDateFormatFactory().getSimpleDateFormat(pattern, locale);
	}

	public static Format getSimpleDateFormat(
		String pattern, Locale locale, TimeZone timeZone) {

		return getFastDateFormatFactory().getSimpleDateFormat(
			pattern, locale, timeZone);
	}

	public static Format getSimpleDateFormat(
		String pattern, TimeZone timeZone) {

		return getFastDateFormatFactory().getSimpleDateFormat(
			pattern, timeZone);
	}

	public static Format getTime(int style, Locale locale, TimeZone timeZone) {
		return getFastDateFormatFactory().getTime(style, locale, timeZone);
	}

	public static Format getTime(Locale locale) {
		return getFastDateFormatFactory().getTime(locale);
	}

	public static Format getTime(Locale locale, TimeZone timeZone) {
		return getFastDateFormatFactory().getTime(locale, timeZone);
	}

	public static Format getTime(TimeZone timeZone) {
		return getFastDateFormatFactory().getTime(timeZone);
	}

	public void setFastDateFormatFactory(
		FastDateFormatFactory fastDateFormatFactory) {

		_fastDateFormatFactory = fastDateFormatFactory;
	}

	private static FastDateFormatFactory _fastDateFormatFactory;

}