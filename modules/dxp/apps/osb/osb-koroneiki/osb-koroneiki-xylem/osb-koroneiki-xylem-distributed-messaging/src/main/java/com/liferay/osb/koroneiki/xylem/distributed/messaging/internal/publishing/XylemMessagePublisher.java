/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.publishing;

import com.liferay.osb.distributed.messaging.publishing.BaseMessagePublisher;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker.LegacyMessageBroker;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker.XylemMessageBroker;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = MessagePublisher.class)
public class XylemMessagePublisher extends BaseMessagePublisher {

	@Reference(unbind = "-")
	protected void setLegacyMessageBroker(
		LegacyMessageBroker legacyMessageBroker,
		Map<String, Object> properties) {

		addMessageBroker(legacyMessageBroker, properties);
	}

	@Reference(unbind = "-")
	protected void setXylemMessageBroker(
		XylemMessageBroker xylemMessageBroker, Map<String, Object> properties) {

		addMessageBroker(xylemMessageBroker, properties);
	}

}