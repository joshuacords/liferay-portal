/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.content.web.internal.frontend;

import com.liferay.commerce.frontend.DefaultFilterImpl;

/**
 * @author Alessio Antonio Rendina
 */
public class OrderFilterImpl extends DefaultFilterImpl {

	public long getAccountId() {
		return _accountId;
	}

	public long getCommerceOrderId() {
		return _commerceOrderId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public void setCommerceOrderId(long commerceOrderId) {
		_commerceOrderId = commerceOrderId;
	}

	private long _accountId;
	private long _commerceOrderId;

}