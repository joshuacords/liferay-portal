/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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