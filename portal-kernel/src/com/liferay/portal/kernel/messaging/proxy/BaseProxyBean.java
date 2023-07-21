/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Micha Kiener
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public abstract class BaseProxyBean {

	public void send(ProxyRequest proxyRequest) {
		_messageBus.sendMessage(_destinationName, buildMessage(proxyRequest));
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	public void setSynchronousDestinationName(
		String synchronousDestinationName) {

		_synchronousDestinationName = synchronousDestinationName;
	}

	public void setSynchronousMessageSenderMode(
		SynchronousMessageSender.Mode synchronousMessageSenderMode) {

		_synchronousMessageSenderMode = synchronousMessageSenderMode;
	}

	public Object synchronousSend(ProxyRequest proxyRequest) throws Exception {
		if (!MessageBusUtil.hasMessageListener(_destinationName)) {
			return proxyRequest.execute(this);
		}

		SynchronousMessageSender synchronousMessageSender =
			_getSynchronousMessageSender();

		ProxyResponse proxyResponse =
			(ProxyResponse)synchronousMessageSender.send(
				_synchronousDestinationName, buildMessage(proxyRequest));

		if (proxyResponse == null) {
			return proxyRequest.execute(this);
		}
		else if (proxyResponse.hasError()) {
			throw proxyResponse.getException();
		}

		return proxyResponse.getResult();
	}

	protected Message buildMessage(ProxyRequest proxyRequest) {
		Message message = new Message();

		message.setPayload(proxyRequest);

		MessageValuesThreadLocal.populateMessageFromThreadLocals(message);

		if (proxyRequest.isLocal()) {
			message.put(MessagingProxy.LOCAL_MESSAGE, Boolean.TRUE);
		}

		return message;
	}

	private SynchronousMessageSender _getSynchronousMessageSender() {
		if (_synchronousMessageSenderMode ==
				SynchronousMessageSender.Mode.DEFAULT) {

			return _defaultSynchronousMessageSender;
		}

		return _directSynchronousMessageSender;
	}

	private static volatile SynchronousMessageSender
		_defaultSynchronousMessageSender =
			ServiceProxyFactory.newServiceTrackedInstance(
				SynchronousMessageSender.class, BaseProxyBean.class,
				"_defaultSynchronousMessageSender", "(mode=DEFAULT)", true);
	private static volatile SynchronousMessageSender
		_directSynchronousMessageSender =
			ServiceProxyFactory.newServiceTrackedInstance(
				SynchronousMessageSender.class, BaseProxyBean.class,
				"_directSynchronousMessageSender", "(mode=DIRECT)", true);
	private static volatile MessageBus _messageBus =
		ServiceProxyFactory.newServiceTrackedInstance(
			MessageBus.class, BaseProxyBean.class, "_messageBus", true);

	private String _destinationName;
	private String _synchronousDestinationName;
	private SynchronousMessageSender.Mode _synchronousMessageSenderMode;

}