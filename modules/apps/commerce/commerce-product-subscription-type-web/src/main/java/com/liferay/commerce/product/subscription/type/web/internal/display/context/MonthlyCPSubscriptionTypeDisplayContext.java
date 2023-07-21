/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.subscription.type.web.internal.display.context;

import com.liferay.commerce.product.subscription.type.web.internal.constants.CPSubscriptionTypeConstants;
import com.liferay.commerce.util.CommerceSubscriptionTypeUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Alessio Antonio Rendina
 */
public class MonthlyCPSubscriptionTypeDisplayContext {

	public MonthlyCPSubscriptionTypeDisplayContext(
		Object object, boolean payment) {

		_object = object;
		_payment = payment;
	}

	public int getMonthDay() {
		UnicodeProperties subscriptionTypeSettingsProperties =
			CommerceSubscriptionTypeUtil.getSubscriptionTypeSettingsProperties(
				_object, _payment);

		if ((subscriptionTypeSettingsProperties == null) ||
			subscriptionTypeSettingsProperties.isEmpty()) {

			return 1;
		}

		if (isPayment()) {
			return GetterUtil.getInteger(
				subscriptionTypeSettingsProperties.get("monthDay"));
		}

		return GetterUtil.getInteger(
			subscriptionTypeSettingsProperties.get("deliveryMonthDay"));
	}

	public int getSelectedMonthlyMode() {
		UnicodeProperties subscriptionTypeSettingsProperties =
			CommerceSubscriptionTypeUtil.getSubscriptionTypeSettingsProperties(
				_object, _payment);

		if (subscriptionTypeSettingsProperties == null) {
			return CPSubscriptionTypeConstants.MODE_ORDER_DATE;
		}

		if (isPayment()) {
			return GetterUtil.getInteger(
				subscriptionTypeSettingsProperties.get("monthlyMode"));
		}

		return GetterUtil.getInteger(
			subscriptionTypeSettingsProperties.get("deliveryMonthlyMode"));
	}

	public boolean isPayment() {
		return _payment;
	}

	private final Object _object;
	private final boolean _payment;

}