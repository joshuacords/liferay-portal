/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.subscription.type.web.internal;

import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.subscription.type.web.internal.constants.CPSubscriptionTypeConstants;
import com.liferay.commerce.product.util.CPSubscriptionType;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.subscription.type.name=" + CPConstants.MONTHLY_SUBSCRIPTION_TYPE,
		"commerce.product.subscription.type.order:Integer=30"
	},
	service = CPSubscriptionType.class
)
public class MonthlyCPSubscriptionTypeImpl implements CPSubscriptionType {

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "month");
	}

	@Override
	public String getName() {
		return CPConstants.MONTHLY_SUBSCRIPTION_TYPE;
	}

	@Override
	public Date getSubscriptionNextIterationDate(
		TimeZone timeZone, int subscriptionLength,
		UnicodeProperties subscriptionTypeSettingsProperties,
		Date lastIterationDate) {

		Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone);

		if (lastIterationDate == null) {
			lastIterationDate = getSubscriptionStartDate(
				timeZone, subscriptionTypeSettingsProperties);
		}

		calendar.setTime(lastIterationDate);

		calendar.add(Calendar.MONTH, subscriptionLength);

		int dayOfMonthActualMaximum = calendar.getActualMaximum(
			Calendar.DAY_OF_MONTH);

		int monthlyMode = GetterUtil.getInteger(
			subscriptionTypeSettingsProperties.get("monthlyMode"));

		if (monthlyMode ==
				CPSubscriptionTypeConstants.MODE_EXACT_DAY_OF_MONTH) {

			int monthDay = GetterUtil.getInteger(
				subscriptionTypeSettingsProperties.get("monthDay"));

			if (monthDay > dayOfMonthActualMaximum) {
				monthDay = dayOfMonthActualMaximum;
			}

			if (monthDay > 0) {
				calendar.set(Calendar.DAY_OF_MONTH, monthDay);
			}
		}

		return calendar.getTime();
	}

	@Override
	public Date getSubscriptionStartDate(
		TimeZone timeZone,
		UnicodeProperties subscriptionTypeSettingsProperties) {

		Date now = new Date();

		if ((subscriptionTypeSettingsProperties == null) ||
			subscriptionTypeSettingsProperties.isEmpty()) {

			return now;
		}

		Calendar calendar = CalendarFactoryUtil.getCalendar(
			now.getTime(), timeZone);

		int dayOfMonthActualMaximum = calendar.getActualMaximum(
			Calendar.DAY_OF_MONTH);

		int today = calendar.get(Calendar.DAY_OF_MONTH);

		int monthlyMode = GetterUtil.getInteger(
			subscriptionTypeSettingsProperties.get("monthlyMode"));

		if (monthlyMode ==
				CPSubscriptionTypeConstants.MODE_EXACT_DAY_OF_MONTH) {

			int monthDay = GetterUtil.getInteger(
				subscriptionTypeSettingsProperties.get("monthDay"));

			if (monthDay > dayOfMonthActualMaximum) {
				monthDay = dayOfMonthActualMaximum;
			}

			if (monthDay < today) {
				return now;
			}

			calendar.set(Calendar.DAY_OF_MONTH, monthDay);
		}
		else if (monthlyMode ==
					CPSubscriptionTypeConstants.MODE_LAST_DAY_OF_MONTH) {

			calendar.set(Calendar.DAY_OF_MONTH, dayOfMonthActualMaximum);
		}

		return calendar.getTime();
	}

}