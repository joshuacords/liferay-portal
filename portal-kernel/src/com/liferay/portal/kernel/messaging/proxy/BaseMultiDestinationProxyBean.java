/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public abstract class BaseMultiDestinationProxyBean {

	public void afterPropertiesSet() {
	}

	public abstract String getDestinationName(ProxyRequest proxyRequest);

	public void send(ProxyRequest proxyRequest) {
		MessageBusUtil.sendMessage(
			getDestinationName(proxyRequest), buildMessage(proxyRequest));
	}

	public void setSynchronousMessageSenderMode(
		SynchronousMessageSender.Mode mode) {

		_mode = mode;
	}

	public Object synchronousSend(ProxyRequest proxyRequest) throws Exception {
		Message message = new Message();

		message.setPayload(proxyRequest);

		SynchronousMessageSender synchronousMessageSender =
			_getSynchronousMessageSender();

		ProxyResponse proxyResponse =
			(ProxyResponse)synchronousMessageSender.send(
				getDestinationName(proxyRequest), message);

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

		return message;
	}

	private SynchronousMessageSender _getSynchronousMessageSender() {
		if (_mode == SynchronousMessageSender.Mode.DEFAULT) {
			return _defaultSynchronousMessageSender;
		}

		return _directSynchronousMessageSender;
	}

	private static volatile SynchronousMessageSender
		_defaultSynchronousMessageSender =
			ServiceProxyFactory.newServiceTrackedInstance(
				SynchronousMessageSender.class,
				BaseMultiDestinationProxyBean.class,
				"_defaultSynchronousMessageSender", "(mode=DEFAULT)", true);
	private static volatile SynchronousMessageSender
		_directSynchronousMessageSender =
			ServiceProxyFactory.newServiceTrackedInstance(
				SynchronousMessageSender.class,
				BaseMultiDestinationProxyBean.class,
				"_directSynchronousMessageSender", "(mode=DIRECT)", true);

	private SynchronousMessageSender.Mode _mode;

}