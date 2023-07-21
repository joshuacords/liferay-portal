/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.google.pubsub.broker;

import com.liferay.osb.distributed.messaging.google.pubsub.connector.ServiceAccountCredentialsProvider;
import com.liferay.osb.distributed.messaging.google.pubsub.connector.broker.BaseMessageBroker;
import com.liferay.osb.provisioning.distributed.messaging.internal.google.pubsub.ISOpsServiceAccountCredentialsProvider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {"projectId=is-ops-dev", "publishing.topic.pattern=.*"},
	service = ISOpsPubsubMessageBroker.class
)
public class ISOpsPubsubMessageBroker extends BaseMessageBroker {

	@Override
	protected ServiceAccountCredentialsProvider
			getServiceAccountCredentialsProvider()
		throws Exception {

		return _isOpsServiceAccountCredentialsProvider;
	}

	@Reference
	private ISOpsServiceAccountCredentialsProvider
		_isOpsServiceAccountCredentialsProvider;

}