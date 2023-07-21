/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.web.form.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Peter Fellwock
 */
@ExtendedObjectClassDefinition(
	category = "forms", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.web.form.web.internal.configuration.WebFormServiceConfiguration",
	localization = "content/Language",
	name = "web-form-service-configuration-name"
)
public interface WebFormServiceConfiguration {

	@Meta.AD(deflt = ";", name = "csv-separator", required = false)
	public String csvSeparator();

	@Meta.AD(deflt = "data/web_form", name = "data-root-dir", required = false)
	public String dataRootDir();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/admin.email.from.address}",
		name = "email-from-address", required = false
	)
	public String emailFromAddress();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/admin.email.from.name}",
		name = "email-from-name", required = false
	)
	public String emailFromName();

	@Meta.AD(
		deflt = "false", name = "validation-script-enable", required = false
	)
	public boolean validationScriptEnable();

}