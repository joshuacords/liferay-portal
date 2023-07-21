/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.order.internal.messaging;

import com.liferay.commerce.constants.CommerceDestinationNames;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.product.type.virtual.order.util.CommerceVirtualOrderItemChecker;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "destination.name=" + CommerceDestinationNames.PAYMENT_STATUS,
	service = MessageListener.class
)
public class CommercePaymentStatusMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		int paymentStatus = message.getInteger("paymentStatus");

		if (paymentStatus != CommerceOrderConstants.PAYMENT_STATUS_PAID) {
			return;
		}

		long commerceOrderId = message.getLong("commerceOrderId");

		_commerceVirtualOrderItemChecker.checkCommerceVirtualOrderItems(
			commerceOrderId);
	}

	@Reference
	private CommerceVirtualOrderItemChecker _commerceVirtualOrderItemChecker;

}