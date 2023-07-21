/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.util;

import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Alessio Antonio Rendina
 */
public interface CPSubscriptionType {

	public String getLabel(Locale locale);

	public String getName();

	public Date getSubscriptionNextIterationDate(
		TimeZone timeZone, int subscriptionLength,
		UnicodeProperties subscriptionTypeSettingsProperties,
		Date lastIterationDate);

	public Date getSubscriptionStartDate(
		TimeZone timeZone,
		UnicodeProperties subscriptionTypeSettingsProperties);

}