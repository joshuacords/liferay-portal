/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model;

import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Alessio Antonio Rendina
 */
public class CPSubscriptionInfo {

	public CPSubscriptionInfo(
		int subscriptionLength, String subscriptionType,
		UnicodeProperties subscriptionTypeSettingsProperties,
		long maxSubscriptionCycles, int deliverySubscriptionLength,
		String deliverySubscriptionType,
		UnicodeProperties deliverySubscriptionTypeSettingsProperties,
		long deliveryMaxSubscriptionCycles) {

		_subscriptionLength = subscriptionLength;
		_subscriptionType = subscriptionType;
		_subscriptionTypeSettingsProperties =
			subscriptionTypeSettingsProperties;
		_maxSubscriptionCycles = maxSubscriptionCycles;
		_deliverySubscriptionLength = deliverySubscriptionLength;
		_deliverySubscriptionType = deliverySubscriptionType;
		_deliverySubscriptionTypeSettingsProperties =
			deliverySubscriptionTypeSettingsProperties;
		_deliveryMaxSubscriptionCycles = deliveryMaxSubscriptionCycles;
	}

	public long getDeliveryMaxSubscriptionCycles() {
		return _deliveryMaxSubscriptionCycles;
	}

	public int getDeliverySubscriptionLength() {
		return _deliverySubscriptionLength;
	}

	public String getDeliverySubscriptionType() {
		return _deliverySubscriptionType;
	}

	public UnicodeProperties getDeliverySubscriptionTypeSettingsProperties() {
		return _deliverySubscriptionTypeSettingsProperties;
	}

	public long getMaxSubscriptionCycles() {
		return _maxSubscriptionCycles;
	}

	public int getSubscriptionLength() {
		return _subscriptionLength;
	}

	public String getSubscriptionType() {
		return _subscriptionType;
	}

	public UnicodeProperties getSubscriptionTypeSettingsProperties() {
		return _subscriptionTypeSettingsProperties;
	}

	private final long _deliveryMaxSubscriptionCycles;
	private final int _deliverySubscriptionLength;
	private final String _deliverySubscriptionType;
	private final UnicodeProperties _deliverySubscriptionTypeSettingsProperties;
	private final long _maxSubscriptionCycles;
	private final int _subscriptionLength;
	private final String _subscriptionType;
	private final UnicodeProperties _subscriptionTypeSettingsProperties;

}