/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.connector.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Kyle Bischof
 */
@ExtendedObjectClassDefinition(generateUI = false)
@Meta.OCD(
	id = "com.liferay.osb.provisioning.zendesk.connector.configuration.ZendeskConfiguration"
)
public interface ZendeskConfiguration {

	@Meta.AD(required = false)
	public String apiToken();

	@Meta.AD(required = false)
	public String domainName();

	@Meta.AD(required = false)
	public String emailAddress();

	@Meta.AD(deflt = "is-lrsd-uat@liferay.com", required = false)
	public String errorEmailAddress();

	@Meta.AD(deflt = "90000", required = false)
	public String retryWaitTime();

}