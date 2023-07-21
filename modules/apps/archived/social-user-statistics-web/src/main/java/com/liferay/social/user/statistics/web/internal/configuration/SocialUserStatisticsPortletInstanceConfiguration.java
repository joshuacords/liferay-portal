/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.user.statistics.web.internal.configuration;

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
	id = "com.liferay.social.user.statistics.web.internal.configuration.SocialUserStatisticsPortletInstanceConfiguration",
	localization = "content/Language",
	name = "social-user-statistics-portlet-instance-configuration-name"
)
public interface SocialUserStatisticsPortletInstanceConfiguration {

	@Meta.AD(
		deflt = "user.achievements", name = "display-activity-counter-name",
		required = false
	)
	public String[] displayActivityCounterName();

	@Meta.AD(
		deflt = "true", name = "display-additional-activity-counters",
		required = false
	)
	public boolean displayAdditionalActivityCounters();

	@Meta.AD(deflt = "true", name = "rank-by-contribution", required = false)
	public boolean rankByContribution();

	@Meta.AD(deflt = "true", name = "rank-by-participation", required = false)
	public boolean rankByParticipation();

	@Meta.AD(deflt = "true", name = "show-header-text", required = false)
	public boolean showHeaderText();

	@Meta.AD(deflt = "true", name = "show-totals", required = false)
	public boolean showTotals();

}