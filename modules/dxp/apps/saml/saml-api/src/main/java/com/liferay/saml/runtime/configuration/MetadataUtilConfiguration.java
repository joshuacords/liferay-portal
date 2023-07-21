/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.runtime.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author     Carlos Sierra Andrés
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.saml.opensaml.integration.internal.transport.configuration.HttpClientFactoryConfiguration}
 */
@Deprecated
@ExtendedObjectClassDefinition(category = "sso")
@Meta.OCD(
	id = "com.liferay.saml.runtime.configuration.MetadataUtilConfiguration",
	localization = "content/Language", name = "metadata-util-configuration-name"
)
public interface MetadataUtilConfiguration {

	@Meta.AD(
		deflt = "60000", name = "connection-manager-timeout", required = false
	)
	public int getConnectionManagerTimeout();

	@Meta.AD(deflt = "60000", name = "so-timeout", required = false)
	public int getSoTimeout();

}