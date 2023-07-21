/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.xsl.content.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Raymond Augé
 */
@ExtendedObjectClassDefinition(category = "xsl-content")
@Meta.OCD(
	id = "com.liferay.xsl.content.web.internal.configuration.XSLContentConfiguration",
	localization = "content/Language", name = "xsl-content-configuration-name"
)
public interface XSLContentConfiguration {

	@Meta.AD(
		deflt = "@portlet_context_url@", id = "valid.url.prefixes",
		name = "valid-url-prefixes", required = false
	)
	public String validUrlPrefixes();

	@Meta.AD(
		deflt = "false", id = "xml.doctype.declaration.allowed",
		name = "xml-doctype-declaration-allowed", required = false
	)
	public boolean xmlDoctypeDeclarationAllowed();

	@Meta.AD(
		deflt = "false", id = "xml.external.general.entities.allowed",
		name = "xml-external-general-entities-allowed", required = false
	)
	public boolean xmlExternalGeneralEntitiesAllowed();

	@Meta.AD(
		deflt = "false", id = "xml.external.parameter.entities.allowed",
		name = "xml-external-parameter-entities-allowed", required = false
	)
	public boolean xmlExternalParameterEntitiesAllowed();

	@Meta.AD(
		deflt = "true", id = "xsl.secure.processing.enabled",
		name = "xsl-secure-processing-enabled", required = false
	)
	public boolean xslSecureProcessingEnabled();

}