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

package com.liferay.osb.provisioning.distributed.messaging.internal.publishing;

import com.liferay.osb.distributed.messaging.publishing.BaseMessagePublisher;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.osb.provisioning.distributed.messaging.internal.google.pubsub.broker.ISOpsPubsubMessageBroker;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = MessagePublisher.class)
public class ProvisioningMessagePublisher extends BaseMessagePublisher {

	@Reference(unbind = "-")
	protected void setISOpsPubsubMessageBroker(
		ISOpsPubsubMessageBroker isOpsPubsubMessageBroker,
		Map<String, Object> properties) {

		addMessageBroker(isOpsPubsubMessageBroker, properties);
	}

}