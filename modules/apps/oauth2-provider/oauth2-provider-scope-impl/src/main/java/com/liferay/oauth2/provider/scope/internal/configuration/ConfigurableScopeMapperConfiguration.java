/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.scope.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.oauth2.provider.scope.internal.constants.OAuth2ProviderScopeConstants;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Tomas Polesovsky
 */
@ExtendedObjectClassDefinition(
	category = "oauth2",
	factoryInstanceLabelAttribute = OAuth2ProviderScopeConstants.OSGI_JAXRS_NAME
)
@Meta.OCD(
	factory = true,
	id = "com.liferay.oauth2.provider.scope.internal.configuration.ConfigurableScopeMapperConfiguration",
	localization = "content/Language",
	name = "oauth2-configurable-scopemapper-configuration-name"
)
public interface ConfigurableScopeMapperConfiguration {

	@Meta.AD(
		deflt = "Default", description = "osgi-jaxrs-name-description",
		id = OAuth2ProviderScopeConstants.OSGI_JAXRS_NAME,
		name = "osgi-jaxrs-name", required = false
	)
	public String osgiJaxRsName();

	@Meta.AD(
		deflt = "GET\\,HEAD\\,OPTIONS=everything.read,PUT\\,POST\\,PATCH\\,DELETE=everything\\,everything.write",
		description = "mapping-description", id = "mapping", name = "mapping",
		required = false
	)
	public String[] mappings();

	@Meta.AD(
		deflt = "false", description = "passthrough-description",
		id = "passthrough", name = "passthrough", required = false
	)
	public boolean passthrough();

}