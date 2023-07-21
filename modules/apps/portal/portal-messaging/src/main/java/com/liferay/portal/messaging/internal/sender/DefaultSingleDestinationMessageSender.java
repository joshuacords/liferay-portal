/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.messaging.internal.sender;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class DefaultSingleDestinationMessageSender
	implements SingleDestinationMessageSender {

	@Override
	public void send(Message message) {
		_messageBus.sendMessage(_destinationName, message);
	}

	@Override
	public void send(Object payload) {
		Message message = new Message();

		message.setPayload(payload);

		send(message);
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	public void setMessageBus(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	private String _destinationName;
	private MessageBus _messageBus;

}