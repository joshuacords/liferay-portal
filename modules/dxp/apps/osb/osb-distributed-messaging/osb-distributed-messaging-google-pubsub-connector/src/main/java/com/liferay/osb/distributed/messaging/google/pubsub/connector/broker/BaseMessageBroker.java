/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.distributed.messaging.google.pubsub.connector.broker;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.google.pubsub.connector.ServiceAccountCredentialsProvider;
import com.liferay.osb.distributed.messaging.publishing.broker.MessageBroker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Amos Fong
 */
public abstract class BaseMessageBroker implements MessageBroker {

	@Override
	public synchronized void publish(String topic, Message message)
		throws Exception {

		Publisher publisher = _publisherMap.get(topic);

		if (publisher == null) {
			publisher = Publisher.newBuilder(
				TopicName.ofProjectTopicName(_projectId, topic)
			).setCredentialsProvider(
				getCredentialsProvider()
			).build();

			_publisherMap.put(topic, publisher);
		}

		ByteString byteString = ByteString.copyFromUtf8(
			(String)message.getPayload());

		PubsubMessage pubsubMessage = PubsubMessage.newBuilder(
		).putAllAttributes(
			message.getStringAttributes()
		).setData(
			byteString
		).build();

		ApiFuture<String> apiFuture = publisher.publish(pubsubMessage);

		ApiFutures.addCallback(
			apiFuture,
			new ApiFutureCallback<String>() {

				public void onFailure(Throwable t) {
					_log.error("Failed to publish message", t);
				}

				public void onSuccess(String messageId) {
					if (_log.isDebugEnabled()) {
						_log.debug("Published message: " + messageId);
					}
				}

			},
			MoreExecutors.directExecutor());
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_projectId = GetterUtil.getString(properties.get("projectId"));
	}

	@Deactivate
	protected void deactivate() {
		try {
			for (Publisher publisher : _publisherMap.values()) {
				publisher.shutdown();

				publisher.awaitTermination(1, TimeUnit.MINUTES);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	protected CredentialsProvider getCredentialsProvider() throws Exception {
		ServiceAccountCredentialsProvider serviceAccountCredentialsProvider =
			getServiceAccountCredentialsProvider();

		return serviceAccountCredentialsProvider.getCredentialsProvider();
	}

	protected abstract ServiceAccountCredentialsProvider
			getServiceAccountCredentialsProvider()
		throws Exception;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageBroker.class);

	private String _projectId;
	private final Map<String, Publisher> _publisherMap = new HashMap<>();

}