/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Pavel Savinov
 */
@ExtendedObjectClassDefinition(
	category = "pages", generateUI = false,
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.layout.content.page.editor.web.internal.configuration.ContentCreationContentPageEditorConfiguration",
	localization = "content/Language",
	name = "content-page-editor-configuration-name"
)
public interface ContentCreationContentPageEditorConfiguration {

	/**
	 * Enables option to create content from the editable fields selection.
	 */
	@Meta.AD(
		deflt = "false", description = "content-creation-enabled-description",
		name = "content-creation-enabled", required = false
	)
	public boolean contentCreationEnabled();

}