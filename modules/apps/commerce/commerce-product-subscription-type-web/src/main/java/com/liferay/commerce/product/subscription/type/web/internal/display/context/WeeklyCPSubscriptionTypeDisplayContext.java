/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.subscription.type.web.internal.display.context;

import com.liferay.commerce.product.subscription.type.web.internal.display.context.util.CPSubscriptionTypeRequestHelper;
import com.liferay.commerce.product.subscription.type.web.internal.display.context.util.comparator.WeeklyCPSubscriptionTypeCalendarWeekDaysComparator;
import com.liferay.commerce.util.CommerceSubscriptionTypeUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class WeeklyCPSubscriptionTypeDisplayContext {

	public WeeklyCPSubscriptionTypeDisplayContext(
		Object object, HttpServletRequest httpServletRequest, boolean payment) {

		_object = object;
		_payment = payment;

		_cpSubscriptionTypeRequestHelper = new CPSubscriptionTypeRequestHelper(
			httpServletRequest);
	}

	public List<Integer> getCalendarWeekDays() {
		List<Integer> calendarWeekDays = new ArrayList<>();

		Map<String, Integer> calendarWeekDaysDisplayNames =
			getCalendarWeekDaysDisplayNames();

		for (Map.Entry<String, Integer> entry :
				calendarWeekDaysDisplayNames.entrySet()) {

			calendarWeekDays.add(entry.getValue());
		}

		calendarWeekDays.sort(
			new WeeklyCPSubscriptionTypeCalendarWeekDaysComparator());

		return calendarWeekDays;
	}

	public int getSelectedWeekDay() {
		UnicodeProperties subscriptionTypeSettingsProperties =
			CommerceSubscriptionTypeUtil.getSubscriptionTypeSettingsProperties(
				_object, _payment);

		if ((subscriptionTypeSettingsProperties == null) ||
			subscriptionTypeSettingsProperties.isEmpty()) {

			return 0;
		}

		if (isPayment()) {
			return GetterUtil.getInteger(
				subscriptionTypeSettingsProperties.get("weekDay"));
		}

		return GetterUtil.getInteger(
			subscriptionTypeSettingsProperties.get("deliveryWeekDay"));
	}

	public String getWeekDayDisplayName(int weekDay) {
		Map<String, Integer> calendarWeekDaysDisplayNames =
			getCalendarWeekDaysDisplayNames();

		for (Map.Entry<String, Integer> entry :
				calendarWeekDaysDisplayNames.entrySet()) {

			if (entry.getValue() == weekDay) {
				return entry.getKey();
			}
		}

		return StringPool.BLANK;
	}

	public boolean isPayment() {
		return _payment;
	}

	protected Map<String, Integer> getCalendarWeekDaysDisplayNames() {
		Calendar calendar = CalendarFactoryUtil.getCalendar(
			_cpSubscriptionTypeRequestHelper.getLocale());

		return calendar.getDisplayNames(
			Calendar.DAY_OF_WEEK, Calendar.LONG,
			_cpSubscriptionTypeRequestHelper.getLocale());
	}

	private final CPSubscriptionTypeRequestHelper
		_cpSubscriptionTypeRequestHelper;
	private final Object _object;
	private final boolean _payment;

}