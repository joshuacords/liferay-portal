/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.policy.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Tomas Polesovsky
 */
@ExtendedObjectClassDefinition(
	category = "multi-factor-authentication",
	factoryInstanceLabelAttribute = "name"
)
@Meta.OCD(
	factory = true,
	id = "com.liferay.multi.factor.authentication.policy.configuration.MFAPolicyConfiguration",
	localization = "content/Language", name = "mfa-policy-configuration-name"
)
@ProviderType
public interface MFAPolicyConfiguration {

	@Meta.AD(
		description = "mfa-policy-name-description", name = "mfa-policy-name",
		required = false
	)
	public String name();

	@Meta.AD(
		description = "mfa-checker-names-description",
		name = "mfa-checker-names", required = false
	)
	public String[] checkerNames();

}