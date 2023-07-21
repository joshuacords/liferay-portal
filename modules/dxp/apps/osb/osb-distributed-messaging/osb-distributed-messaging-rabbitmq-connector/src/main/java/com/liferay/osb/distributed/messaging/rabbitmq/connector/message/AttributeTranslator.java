/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.rabbitmq.connector.message;

import com.liferay.osb.distributed.messaging.AttributeKeys;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.MessageProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class AttributeTranslator {

	public static Map<String, Object> toMap(AMQP.BasicProperties properties) {
		Map<String, Object> map = new HashMap<>();

		map.put(AttributeKeys.APP_ID, properties.getAppId());
		map.put(AttributeKeys.CLUSTER_ID, properties.getClusterId());
		map.put(
			AttributeKeys.CONTENT_ENCODING, properties.getContentEncoding());
		map.put(AttributeKeys.CONTENT_TYPE, properties.getContentType());
		map.put(AttributeKeys.CORRELATION_ID, properties.getCorrelationId());
		map.put(AttributeKeys.DELIVERY_MODE, properties.getDeliveryMode());
		map.put(AttributeKeys.EXPIRATION, properties.getExpiration());
		map.put(AttributeKeys.HEADERS, properties.getHeaders());
		map.put(AttributeKeys.MESSAGE_ID, properties.getMessageId());
		map.put(AttributeKeys.PRIORITY, properties.getPriority());
		map.put(AttributeKeys.REPLY_TO, properties.getReplyTo());
		map.put(AttributeKeys.TIMESTAMP, properties.getTimestamp());
		map.put(AttributeKeys.TYPE, properties.getType());
		map.put(AttributeKeys.USER_ID, properties.getUserId());

		return map;
	}

	public static AMQP.BasicProperties toProperties(Map<String, Object> map) {
		if ((map == null) || map.isEmpty()) {
			return MessageProperties.PERSISTENT_TEXT_PLAIN;
		}

		AMQP.BasicProperties.Builder propertiesBuilder =
			new AMQP.BasicProperties.Builder();

		propertiesBuilder.appId((String)map.get(AttributeKeys.APP_ID));
		propertiesBuilder.clusterId((String)map.get(AttributeKeys.CLUSTER_ID));
		propertiesBuilder.contentEncoding(
			(String)map.get(AttributeKeys.CONTENT_ENCODING));

		if (map.containsKey(AttributeKeys.CONTENT_TYPE)) {
			propertiesBuilder.contentType(
				(String)map.get(AttributeKeys.CONTENT_TYPE));
		}
		else {
			propertiesBuilder.contentType(
				MessageProperties.PERSISTENT_TEXT_PLAIN.getContentType());
		}

		propertiesBuilder.correlationId(
			(String)map.get(AttributeKeys.CORRELATION_ID));

		if (map.containsKey(AttributeKeys.DELIVERY_MODE)) {
			propertiesBuilder.deliveryMode(
				(Integer)map.get(AttributeKeys.DELIVERY_MODE));
		}
		else {
			propertiesBuilder.deliveryMode(
				MessageProperties.PERSISTENT_TEXT_PLAIN.getDeliveryMode());
		}

		propertiesBuilder.expiration((String)map.get(AttributeKeys.EXPIRATION));
		propertiesBuilder.headers(
			(Map<String, Object>)map.get(AttributeKeys.HEADERS));
		propertiesBuilder.messageId((String)map.get(AttributeKeys.MESSAGE_ID));

		if (map.containsKey(AttributeKeys.PRIORITY)) {
			propertiesBuilder.priority(
				(Integer)map.get(AttributeKeys.PRIORITY));
		}
		else {
			propertiesBuilder.priority(
				MessageProperties.PERSISTENT_TEXT_PLAIN.getPriority());
		}

		propertiesBuilder.replyTo((String)map.get(AttributeKeys.REPLY_TO));
		propertiesBuilder.timestamp((Date)map.get(AttributeKeys.TIMESTAMP));
		propertiesBuilder.type((String)map.get(AttributeKeys.TYPE));
		propertiesBuilder.userId((String)map.get(AttributeKeys.USER_ID));

		return propertiesBuilder.build();
	}

}