/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Marcellus Tavares
 */
@ExtendedObjectClassDefinition(
	category = "analytics-cloud", generateUI = false,
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.analytics.settings.configuration.AnalyticsConfiguration"
)
@ProviderType
public interface AnalyticsConfiguration {

	@Meta.AD(required = false)
	public String hostsAllowed();

	@Meta.AD(required = false)
	public String liferayAnalyticsDataSourceId();

	@Meta.AD(required = false)
	public String liferayAnalyticsEndpointURL();

	@Meta.AD(required = false)
	public String liferayAnalyticsFaroBackendSecuritySignature();

	@Meta.AD(required = false)
	public String liferayAnalyticsProjectId();

	@Meta.AD(required = false)
	public String liferayAnalyticsURL();

	@Meta.AD(required = false)
	public String publicKey();

	@Meta.AD(required = false)
	public String siteReportingGrouping();

	@Meta.AD(required = false)
	public boolean syncAllContacts();

	@Meta.AD(required = false)
	public String[] syncedContactFieldNames();

	@Meta.AD(required = false)
	public String[] syncedGroupIds();

	@Meta.AD(required = false)
	public String[] syncedOrganizationIds();

	@Meta.AD(required = false)
	public String[] syncedUserFieldNames();

	@Meta.AD(required = false)
	public String[] syncedUserGroupIds();

	@Meta.AD(required = false)
	public String token();

}