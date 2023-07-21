/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.subscription.web.internal.model;

/**
 * @author Luca Pellizzon
 */
public class SubscriptionEntry {

	public SubscriptionEntry(
		long subscriptionId, Link orderId, Link commerceAccountId,
		Label subscriptionStatus, String commerceAccountName) {

		_subscriptionId = subscriptionId;
		_orderId = orderId;
		_commerceAccountId = commerceAccountId;
		_subscriptionStatus = subscriptionStatus;
		_commerceAccountName = commerceAccountName;
	}

	public Link getCommerceAccountId() {
		return _commerceAccountId;
	}

	public String getCommerceAccountName() {
		return _commerceAccountName;
	}

	public Link getOrderId() {
		return _orderId;
	}

	public long getSubscriptionId() {
		return _subscriptionId;
	}

	public Label getSubscriptionStatus() {
		return _subscriptionStatus;
	}

	private final Link _commerceAccountId;
	private final String _commerceAccountName;
	private final Link _orderId;
	private final long _subscriptionId;
	private final Label _subscriptionStatus;

}