/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.messaging.internal.sender;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;
import com.liferay.portal.kernel.nio.intraband.messaging.IntrabandBridgeDestination;
import com.liferay.portal.messaging.internal.SynchronousDestination;

import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shuyang Zhou
 */
@Component(property = "mode=DIRECT", service = SynchronousMessageSender.class)
public class DirectSynchronousMessageSender
	implements SynchronousMessageSender {

	@Override
	public Object send(String destinationName, Message message)
		throws MessageBusException {

		Destination destination = _messageBus.getDestination(destinationName);

		if (destination == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Destination " + destinationName + " is not configured");
			}

			return null;
		}

		if (destination instanceof IntrabandBridgeDestination ||
			destination instanceof SynchronousDestination) {

			destination.send(message);
		}
		else {
			Set<MessageListener> messageListeners =
				destination.getMessageListeners();

			for (MessageListener messageListener : messageListeners) {
				try {
					messageListener.receive(message);
				}
				catch (MessageListenerException messageListenerException) {
					throw new MessageBusException(messageListenerException);
				}
			}
		}

		return message.getResponse();
	}

	@Override
	public Object send(String destinationName, Message message, long timeout)
		throws MessageBusException {

		if (_log.isWarnEnabled()) {
			_log.warn(
				DirectSynchronousMessageSender.class.getName() +
					" does not support timeout");
		}

		return send(destinationName, message);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DirectSynchronousMessageSender.class);

	@Reference
	private MessageBus _messageBus;

}