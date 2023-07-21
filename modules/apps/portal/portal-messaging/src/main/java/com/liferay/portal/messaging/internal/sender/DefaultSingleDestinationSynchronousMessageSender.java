/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.messaging.internal.sender;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationSynchronousMessageSender;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class DefaultSingleDestinationSynchronousMessageSender
	implements SingleDestinationSynchronousMessageSender {

	@Override
	public Object send(Message message) throws MessageBusException {
		return _synchronousMessageSender.send(_destinationName, message);
	}

	@Override
	public Object send(Message message, long timeout)
		throws MessageBusException {

		return _synchronousMessageSender.send(
			_destinationName, message, timeout);
	}

	@Override
	public Object send(Object payload) throws MessageBusException {
		Message message = new Message();

		message.setPayload(payload);

		return send(message);
	}

	@Override
	public Object send(Object payload, long timeout)
		throws MessageBusException {

		Message message = new Message();

		message.setPayload(payload);

		return send(message, timeout);
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	public void setSynchronousMessageSender(
		SynchronousMessageSender synchronousMessageSender) {

		_synchronousMessageSender = synchronousMessageSender;
	}

	private String _destinationName;
	private SynchronousMessageSender _synchronousMessageSender;

}