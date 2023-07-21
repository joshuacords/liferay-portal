/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.content.search.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Juergen Kappler
 */
@ExtendedObjectClassDefinition(
	category = "web-content",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.journal.content.search.web.internal.configuration.JournalContentSearchPortletInstanceConfiguration",
	localization = "content/Language",
	name = "journal-content-search-portlet-instance-configuration-name"
)
public interface JournalContentSearchPortletInstanceConfiguration {

	@Meta.AD(deflt = "true", name = "enable-highlighting", required = false)
	public boolean enableHighlighting();

	@Meta.AD(deflt = "true", name = "show-listed", required = false)
	public boolean showListed();

	@Meta.AD(name = "target-widget-id", required = false)
	public String targetPortletId();

}