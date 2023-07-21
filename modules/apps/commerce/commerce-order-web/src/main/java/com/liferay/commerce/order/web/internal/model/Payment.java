/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.web.internal.model;

import com.liferay.commerce.frontend.model.LabelField;

/**
 * @author Alessio Antonio Rendina
 */
public class Payment {

	public Payment(
		long paymentId, LabelField type, String amount, String createDate,
		String content) {

		_paymentId = paymentId;
		_type = type;
		_amount = amount;
		_createDate = createDate;
		_content = content;
	}

	public String getAmount() {
		return _amount;
	}

	public String getContent() {
		return _content;
	}

	public String getCreateDate() {
		return _createDate;
	}

	public long getPaymentId() {
		return _paymentId;
	}

	public LabelField getType() {
		return _type;
	}

	private final String _amount;
	private final String _content;
	private final String _createDate;
	private final long _paymentId;
	private final LabelField _type;

}