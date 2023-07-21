/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.xsl.content.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Juergen Kappler
 */
@ExtendedObjectClassDefinition(
	category = "xsl-content",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.xsl.content.web.internal.configuration.XSLContentPortletInstanceConfiguration",
	localization = "content/Language",
	name = "xsl-content-portlet-instance-configuration-name"
)
public interface XSLContentPortletInstanceConfiguration {

	@Meta.AD(
		deflt = "@portlet_context_url@/example.xml", name = "xml-url",
		required = false
	)
	public String xmlUrl();

	@Meta.AD(
		deflt = "@portlet_context_url@/example.xsl", name = "xsl-url",
		required = false
	)
	public String xslUrl();

}