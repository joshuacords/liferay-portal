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
 * @author Jenny Chen
 */
@Component(
	immediate = true,
	property = {
		"exchange=is_entity_exchange", "exclusive=true",
		"queue=is_osb_provisioning_entity_queue",
		"routing.key=koroneiki.account.contactrole.unassigned",
		"routing.key=koroneiki.entitlement.create",
		"routing.key=koroneiki.entitlement.delete",
		"routing.key=koroneiki.product.delete"
	},
	service = EntityLegacyConsumer.class
)
public class EntityLegacyConsumer extends BaseConsumer {

	@Override
	protected Connection getConnection() {
		return _legacyConnection;
	}

	@Reference
	private LegacyConnection _legacyConnection;

}