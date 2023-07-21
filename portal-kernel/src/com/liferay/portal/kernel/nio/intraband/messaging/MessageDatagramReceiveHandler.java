/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.messaging;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.nio.intraband.BaseAsyncDatagramReceiveHandler;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.nio.ByteBuffer;

import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class MessageDatagramReceiveHandler
	extends BaseAsyncDatagramReceiveHandler {

	public MessageDatagramReceiveHandler() {
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #MessageDatagramReceiveHandler()}
	 */
	@Deprecated
	public MessageDatagramReceiveHandler(MessageBus messageBus) {
	}

	@Override
	protected void doReceive(
			RegistrationReference registrationReference, Datagram datagram)
		throws Exception {

		ByteBuffer byteBuffer = datagram.getDataByteBuffer();

		MessageRoutingBag messageRoutingBag = MessageRoutingBag.fromByteArray(
			byteBuffer.array());

		Destination destination = _messageBus.getDestination(
			messageRoutingBag.getDestinationName());

		if (destination != null) {
			Set<MessageListener> messageListeners =
				destination.getMessageListeners();

			if (destination instanceof IntrabandBridgeDestination) {
				if (messageListeners.isEmpty()) {
					IntrabandBridgeDestination intrabandBridgeDestination =
						(IntrabandBridgeDestination)destination;

					intrabandBridgeDestination.sendMessageRoutingBag(
						messageRoutingBag);
				}
				else {
					destination.send(messageRoutingBag.getMessage());
				}
			}
			else {
				if (!messageListeners.isEmpty()) {
					for (MessageListener messageListener : messageListeners) {
						try {
							messageListener.receive(
								messageRoutingBag.getMessage());
						}
						catch (MessageListenerException
									messageListenerException) {

							throw new MessageBusException(
								messageListenerException);
						}
					}
				}
			}
		}

		if (messageRoutingBag.isSynchronizedBridge()) {
			Intraband intraband = registrationReference.getIntraband();

			intraband.sendDatagram(
				registrationReference,
				Datagram.createResponseDatagram(
					datagram, messageRoutingBag.toByteArray()));
		}
	}

	private volatile MessageBus _messageBus =
		ServiceProxyFactory.newServiceTrackedInstance(
			MessageBus.class, MessageDatagramReceiveHandler.class, this,
			"_messageBus", null, true);

}