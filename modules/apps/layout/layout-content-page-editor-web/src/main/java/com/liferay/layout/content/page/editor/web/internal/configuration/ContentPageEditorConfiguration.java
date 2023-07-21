/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Alejandro Tardín
 */
@ExtendedObjectClassDefinition(
	category = "pages", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.layout.content.page.editor.web.internal.configuration.ContentPageEditorConfiguration",
	localization = "content/Language",
	name = "content-page-editor-configuration-name"
)
public interface ContentPageEditorConfiguration {

	/**
	 * Enables comments inside the content page editor.
	 */
	@Meta.AD(
		deflt = "false", description = "comments-enabled-description",
		name = "comments-enabled", required = false
	)
	public boolean commentsEnabled();

}