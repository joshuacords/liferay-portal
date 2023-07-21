/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker;

import com.liferay.osb.distributed.messaging.rabbitmq.connector.Connection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.broker.BaseMessageBroker;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.XylemConnection;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = {"exchange=", "publishing.topic.pattern=.*"},
	service = XylemMessageBroker.class
)
public class XylemMessageBroker extends BaseMessageBroker {

	@Override
	protected Connection getConnection() {
		return _xylemConnection;
	}

	@Reference
	private XylemConnection _xylemConnection;

}