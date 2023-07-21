/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.talend.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author guywandji
 * @author Alessio Antonio Rendina
 */
@ExtendedObjectClassDefinition(
	category = "data-integration",
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.data.integration.talend.internal.configuration.CommerceDataIntegrationProcessConfiguration",
	localization = "content/Language",
	name = "commerce-data-integration-process-configuration-name"
)
public interface CommerceDataIntegrationProcessConfiguration {

	@Meta.AD(
		deflt = ".zip,.rar,.jar,.properties", name = "file-extensions",
		required = false
	)
	public String[] imageExtensions();

	@Meta.AD(deflt = "50242880", name = "file-max-size", required = false)
	public long imageMaxSize();

}