/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.google.pubsub;

import com.liferay.osb.distributed.messaging.google.pubsub.connector.BaseServiceAccountCredentialsProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"clientEmailAddress=", "clientId=", "privateKeyId=",
		"privateKeyPkcs8.key="
	},
	service = ISOpsServiceAccountCredentialsProvider.class
)
public class ISOpsServiceAccountCredentialsProvider
	extends BaseServiceAccountCredentialsProvider {
}