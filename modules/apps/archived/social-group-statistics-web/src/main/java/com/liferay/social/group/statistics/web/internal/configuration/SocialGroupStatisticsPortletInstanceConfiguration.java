/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.group.statistics.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Iván Zaera
 */
@ExtendedObjectClassDefinition(
	category = "user-activity",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.social.group.statistics.web.internal.configuration.SocialGroupStatisticsPortletInstanceConfiguration",
	localization = "content/Language",
	name = "social-group-statistics-portlet-instance-configuration-name"
)
public interface SocialGroupStatisticsPortletInstanceConfiguration {

	@Meta.AD(name = "chart-type", required = false)
	public String[] chartType();

	@Meta.AD(name = "chart-width", required = false)
	public String[] chartWidth();

	@Meta.AD(name = "data-range", required = false)
	public String[] dataRange();

	@Meta.AD(name = "display-activity-counter-name", required = false)
	public String[] displayActivityCounterName();

}