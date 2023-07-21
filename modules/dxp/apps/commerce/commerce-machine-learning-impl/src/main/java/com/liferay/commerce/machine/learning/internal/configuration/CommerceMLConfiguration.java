/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Riccardo Ferrari
 */
@ExtendedObjectClassDefinition(
	category = "data-integration",
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.machine.learning.internal.configuration.CommerceMLConfiguration",
	localization = "content/Language", name = "commerce-ml-configuration-name"
)
public interface CommerceMLConfiguration {

	@Meta.AD(
		deflt = "http://localhost:8080", name = "commerce-ml-base-url",
		required = false
	)
	public String commerceMLBaseURL();

}