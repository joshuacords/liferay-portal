/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.google.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Stian Sigvartsen
 */
@ExtendedObjectClassDefinition(category = "sso")
@Meta.OCD(
	id = "com.liferay.portal.security.sso.google.configuration.GoogleAuthorizationConfiguration",
	localization = "content/Language",
	name = "google-authorization-configuration-name"
)
public interface GoogleAuthorizationConfiguration {

	@Meta.AD(
		deflt = "false", description = "enabled-help", name = "enabled",
		required = false
	)
	public boolean enabled();

	@Meta.AD(
		deflt = "", description = "client-id-help", name = "client-id",
		required = false
	)
	public String clientId();

	@Meta.AD(
		deflt = "", description = "client-secret-help", name = "client-secret",
		required = false
	)
	public String clientSecret();

}