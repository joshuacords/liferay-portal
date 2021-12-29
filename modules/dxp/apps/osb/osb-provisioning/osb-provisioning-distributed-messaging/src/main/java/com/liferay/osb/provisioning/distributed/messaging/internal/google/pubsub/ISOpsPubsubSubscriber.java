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

package com.liferay.osb.provisioning.distributed.messaging.internal.google.pubsub;

import com.liferay.osb.distributed.messaging.google.pubsub.connector.BasePubsubSubscriber;
import com.liferay.osb.distributed.messaging.google.pubsub.connector.ServiceAccountCredentialsProvider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {"messageFilter=", "projectId=is-ops-dev", "topic=okta-users"},
	service = ISOpsPubsubSubscriber.class
)
public class ISOpsPubsubSubscriber extends BasePubsubSubscriber {

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