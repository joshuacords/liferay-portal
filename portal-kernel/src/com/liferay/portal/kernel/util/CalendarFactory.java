/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public interface CalendarFactory {

	public Calendar getCalendar();

	public Calendar getCalendar(int year, int month, int date);

	public Calendar getCalendar(
		int year, int month, int date, int hour, int minute);

	public Calendar getCalendar(
		int year, int month, int date, int hour, int minute, int second);

	public Calendar getCalendar(
		int year, int month, int date, int hour, int minute, int second,
		int millisecond);

	public Calendar getCalendar(
		int year, int month, int date, int hour, int minute, int second,
		int millisecond, TimeZone timeZone);

	public Calendar getCalendar(Locale locale);

	public Calendar getCalendar(long time);

	public Calendar getCalendar(long time, TimeZone timeZone);

	public Calendar getCalendar(TimeZone timeZone);

	public Calendar getCalendar(TimeZone timeZone, Locale locale);

}