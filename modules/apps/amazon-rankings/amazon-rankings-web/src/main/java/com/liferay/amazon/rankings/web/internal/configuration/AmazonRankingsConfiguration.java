/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.amazon.rankings.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Jorge Ferrer
 */
@ExtendedObjectClassDefinition(category = "third-party")
@Meta.OCD(
	id = "com.liferay.amazon.rankings.web.internal.configuration.AmazonRankingsConfiguration",
	localization = "content/Language",
	name = "amazon-rankings-configuration-name"
)
public interface AmazonRankingsConfiguration {

	@Meta.AD(name = "amazon-access-key-id", required = false)
	public String amazonAccessKeyId();

	@Meta.AD(name = "amazon-associate-tag", required = false)
	public String amazonAssociateTag();

	@Meta.AD(name = "amazon-secret-access-key", required = false)
	public String amazonSecretAccessKey();

	@Meta.AD(
		deflt = "0066620996|0131412752|0201633612|0310241448", name = "isbns",
		required = false
	)
	public String[] isbns();

}