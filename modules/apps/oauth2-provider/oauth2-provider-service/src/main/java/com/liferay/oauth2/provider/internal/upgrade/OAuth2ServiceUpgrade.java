/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.internal.upgrade;

import com.liferay.oauth2.provider.internal.upgrade.v1_2_0.util.OAuth2AuthorizationTable;
import com.liferay.oauth2.provider.internal.upgrade.v2_0_0.UpgradeOAuth2ApplicationScopeAliases;
import com.liferay.oauth2.provider.internal.upgrade.v2_0_0.util.OAuth2ApplicationScopeAliasesTable;
import com.liferay.oauth2.provider.internal.upgrade.v2_0_0.util.OAuth2ApplicationTable;
import com.liferay.oauth2.provider.internal.upgrade.v2_0_0.util.OAuth2ScopeGrantTable;
import com.liferay.oauth2.provider.scope.liferay.ScopeLocator;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.step.util.UpgradeStepFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(service = UpgradeStepRegistrator.class)
public class OAuth2ServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.1.0",
			new com.liferay.oauth2.provider.internal.upgrade.v1_1_0.
				UpgradeOAuth2ScopeGrant());

		registry.register(
			"1.1.0", "1.2.0",
			UpgradeStepFactory.addColumns(
				OAuth2AuthorizationTable.class,
				"remoteHostInfo VARCHAR(255) null"));

		registry.register(
			"1.2.0", "1.3.0",
			UpgradeStepFactory.addColumns(
				OAuth2ScopeGrantTable.class, "scopeAliases TEXT null"));

		registry.register(
			"1.3.0", "2.0.0",
			new UpgradeOAuth2ApplicationScopeAliases(
				_companyLocalService, _scopeLocator),
			UpgradeStepFactory.dropColumns(
				OAuth2ApplicationScopeAliasesTable.class, "scopeAliases",
				"scopeAliasesHash"));

		registry.register(
			"2.0.0", "3.0.0",
			UpgradeStepFactory.addColumns(
				OAuth2ApplicationTable.class, "clientCredentialUserId LONG"),
			UpgradeStepFactory.addColumns(
				OAuth2ApplicationTable.class,
				"clientCredentialUserName VARCHAR(75) null"),
			UpgradeStepFactory.runSql(
				"update OAuth2Application set clientCredentialUserId = " +
					"userId, clientCredentialUserName = userName"));
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ScopeLocator _scopeLocator;

}