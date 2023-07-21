/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.rabbitmq.consumer;

import com.liferay.osb.distributed.messaging.rabbitmq.connector.Connection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.consumer.BaseConsumer;
import com.liferay.osb.provisioning.distributed.messaging.internal.rabbitmq.LegacyConnection;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = {
		"exchange=is_dossiera_v1", "exclusive=true",
		"queue=is_osb_provisioning_dossiera_queue",
		"routing.key=dossiera.provisioning.create"
	},
	service = DossieraLegacyConsumer.class
)
public class DossieraLegacyConsumer extends BaseConsumer {

	@Override
	protected Connection getConnection() {
		return _legacyConnection;
	}

	@Reference
	private LegacyConnection _legacyConnection;

}