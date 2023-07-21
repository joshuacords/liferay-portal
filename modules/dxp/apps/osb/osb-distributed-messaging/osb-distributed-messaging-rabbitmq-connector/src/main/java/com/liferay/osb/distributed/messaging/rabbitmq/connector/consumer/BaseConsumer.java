/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.rabbitmq.connector.consumer;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.Connection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.message.AttributeTranslator;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.messaging.ExclusiveConsumerCheckMessageListener;
import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;
import com.liferay.osgi.util.StringPlus;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseConsumer implements Consumer {

	public void checkConsumer() {
		try {
			if (channel.isOpen() && Validator.isNotNull(_consumerTag)) {
				return;
			}

			if (!channel.isOpen()) {
				Connection connection = getConnection();

				channel = connection.createChannel(prefetchCount);
			}

			if (exclusive && (channel.consumerCount(queue) > 0)) {
				return;
			}

			_consumerTag = channel.basicConsume(
				queue, false, StringPool.BLANK, false, exclusive, null, this);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	public Channel getChannel() {
		return channel;
	}

	public void handleCancel(String consumerTag) throws IOException {
		if (_log.isInfoEnabled()) {
			_log.info("Consumer cancelled for " + consumerTag + " on " + queue);
		}
	}

	public void handleCancelOk(String consumerTag) {
		if (_log.isInfoEnabled()) {
			_log.info("Consumer cancelled for " + consumerTag + " on " + queue);
		}
	}

	public void handleConsumeOk(String consumerTag) {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Consumer registered for " + consumerTag + " on " + queue);
		}
	}

	@Override
	public void handleDelivery(
		String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
		byte[] body) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Received message " + envelope.getDeliveryTag() +
					" with routing key " + envelope.getRoutingKey());
		}

		String payload = null;

		try {
			payload = new String(body, "UTF-8");
		}
		catch (UnsupportedEncodingException unsupportedEncodingException) {
			_log.error(
				unsupportedEncodingException, unsupportedEncodingException);

			basicAck(envelope);

			return;
		}

		try {
			messageRouter.route(
				envelope.getRoutingKey(),
				new Message(
					envelope.getRoutingKey(),
					AttributeTranslator.toMap(properties), payload));

			basicAck(envelope);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			basicReject(envelope);
		}
	}

	public void handleRecoverOk(String consumerTag) {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Recovered connection for " + consumerTag + " on " + queue);
		}
	}

	public void handleShutdownSignal(
		String consumerTag, ShutdownSignalException shutdownSignalException) {

		_log.error(
			"Shutdown signal for " + consumerTag + " on " + queue +
				shutdownSignalException);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		exchange = GetterUtil.getString(properties.get("exchange"));
		exclusive = GetterUtil.getBoolean(properties.get("exclusive"));
		prefetchCount = GetterUtil.getInteger(
			properties.get("prefetch.count"), 20);
		queue = GetterUtil.getString(properties.get("queue"));
		routingKeys = StringPlus.asList(properties.get("routing.key"));

		Connection connection = getConnection();

		channel = connection.createChannel(prefetchCount);

		channel.queueDeclare(queue, true, false, false, null);

		for (String routingKey : routingKeys) {
			channel.queueBind(queue, exchange, routingKey);
		}

		if (exclusive) {
			checkConsumer();

			exclusiveConsumerCheckMessageListener.register(this);
		}
		else {
			_consumerTag = channel.basicConsume(queue, false, this);
		}
	}

	protected void basicAck(Envelope envelope) {
		try {
			channel.basicAck(envelope.getDeliveryTag(), false);
		}
		catch (IOException ioException) {
			_log.error(ioException, ioException);
		}
	}

	protected void basicReject(Envelope envelope) {
		try {
			channel.basicReject(envelope.getDeliveryTag(), false);
		}
		catch (IOException ioException) {
			_log.error(ioException, ioException);
		}
	}

	@Deactivate
	protected void deactivate() throws Exception {
		if (channel != null) {
			channel.close();
		}

		if (exclusive) {
			exclusiveConsumerCheckMessageListener.unregister(this);
		}
	}

	protected abstract Connection getConnection();

	protected Channel channel;
	protected String exchange;
	protected boolean exclusive;

	@Reference
	protected ExclusiveConsumerCheckMessageListener
		exclusiveConsumerCheckMessageListener;

	@Reference
	protected MessageRouter messageRouter;

	protected int prefetchCount;
	protected String queue;
	protected List<String> routingKeys;

	private static final Log _log = LogFactoryUtil.getLog(BaseConsumer.class);

	private String _consumerTag;

}