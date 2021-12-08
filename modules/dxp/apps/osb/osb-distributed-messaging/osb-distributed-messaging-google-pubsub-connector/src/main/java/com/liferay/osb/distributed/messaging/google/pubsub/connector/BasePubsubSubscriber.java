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

package com.liferay.osb.distributed.messaging.google.pubsub.connector;

import com.google.api.core.ApiService;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.rpc.NotFoundException;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.SubscriptionAdminSettings;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.Subscription;
import com.google.pubsub.v1.TopicName;

import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BasePubsubSubscriber {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) throws Exception {
		String messageFilter = GetterUtil.getString(
			properties.get("messageFilter"));
		String projectId = GetterUtil.getString(properties.get("projectId"));
		String topic = GetterUtil.getString(properties.get("topic"));

		SubscriptionAdminSettings subscriptionAdminSettings =
			SubscriptionAdminSettings.newBuilder(
			).setCredentialsProvider(
				getCredentialsProvider()
			).build();

		SubscriptionAdminClient subscriptionAdminClient =
			SubscriptionAdminClient.create(subscriptionAdminSettings);

		Class<?> clazz = getClass();

		ProjectSubscriptionName projectSubscriptionName =
			ProjectSubscriptionName.of(projectId, clazz.getName());

		try {
			subscriptionAdminClient.getSubscription(projectSubscriptionName);

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Found subscription " + projectSubscriptionName.toString());
			}
		}
		catch (NotFoundException notFoundException) {
			TopicName topicName = TopicName.ofProjectTopicName(
				projectId, topic);

			Subscription subscription = Subscription.newBuilder(
			).setAckDeadlineSeconds(
				30
			).setFilter(
				messageFilter
			).setName(
				projectSubscriptionName.toString()
			).setTopic(
				topicName.toString()
			).build();

			if (_log.isDebugEnabled()) {
				_log.debug("Creating subscription " + subscription.toString());
			}

			subscriptionAdminClient.createSubscription(subscription);
		}

		_subscriber = Subscriber.newBuilder(
			projectSubscriptionName,
			new DefaultMessageReceiver(topic, messageRouter)
		).setCredentialsProvider(
			getCredentialsProvider()
		).build();

		ApiService apiService = _subscriber.startAsync();

		apiService.awaitRunning();

		if (_log.isInfoEnabled()) {
			_log.info(
				"Listening for messages on " +
					_subscriber.getSubscriptionNameString());
		}
	}

	@Deactivate
	protected void deactivate(Map<String, Object> properties) {
		if (_subscriber != null) {
			try {
				ApiService apiService = _subscriber.stopAsync();

				apiService.awaitTerminated();

				if (_log.isInfoEnabled()) {
					_log.info(
						"Stopped listening for messages on " +
							_subscriber.getSubscriptionNameString());
				}
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
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

	@Reference
	protected MessageRouter messageRouter;

	private static final Log _log = LogFactoryUtil.getLog(
		BasePubsubSubscriber.class);

	private Subscriber _subscriber;

}