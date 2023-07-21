/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.internal.upgrade;

import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.release.BaseUpgradeServiceModuleRelease;
import com.liferay.saml.persistence.internal.upgrade.v1_0_0.UpgradeSamlSpAuthRequest;
import com.liferay.saml.persistence.internal.upgrade.v1_0_0.UpgradeSamlSpMessage;
import com.liferay.saml.persistence.internal.upgrade.v2_0_0.UpgradeSamlIdpSpConnection;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class SamlServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		try {
			BaseUpgradeServiceModuleRelease upgradeServiceModuleRelease =
				new BaseUpgradeServiceModuleRelease() {

					@Override
					protected String getNamespace() {
						return "Saml";
					}

					@Override
					protected String getNewBundleSymbolicName() {
						return "com.liferay.saml.persistence.service";
					}

					@Override
					protected String getOldBundleSymbolicName() {
						return "saml-portlet";
					}

				};

			upgradeServiceModuleRelease.upgrade();
		}
		catch (UpgradeException upgradeException) {
			throw new RuntimeException(upgradeException);
		}

		registry.register("0.0.1", "1.0.0", new DummyUpgradeStep());

		registry.register(
			"1.0.0", "1.1.0",
			new com.liferay.saml.persistence.internal.upgrade.v1_0_0.
				UpgradeSamlIdpSpSession(),
			new UpgradeSamlSpAuthRequest(), new UpgradeSamlSpMessage(),
			new com.liferay.saml.persistence.internal.upgrade.v1_0_0.
				UpgradeSamlSpSession());

		registry.register(
			"1.1.0", "1.1.1",
			new com.liferay.saml.persistence.internal.upgrade.v1_1_0.
				UpgradeSamlSpSession());

		registry.register(
			"1.1.1", "1.1.2",
			new com.liferay.saml.persistence.internal.upgrade.v1_1_1.
				UpgradeSamlSpSession());

		registry.register(
			"1.1.2", "1.1.3",
			new com.liferay.saml.persistence.internal.upgrade.v1_1_2.
				UpgradeSamlSpIdpConnection());

		registry.register(
			"1.1.3", "1.1.4",
			new com.liferay.saml.persistence.internal.upgrade.v1_1_3.
				UpgradeClassNames());

		registry.register(
			"1.1.4", "2.0.0",
			new com.liferay.saml.persistence.internal.upgrade.v1_1_4.
				UpgradeSamlSpSession(),
			new com.liferay.saml.persistence.internal.upgrade.v1_1_4.
				UpgradeSamlSpSessionData(_configurationAdmin));

		registry.register("2.0.0", "2.1.0", new UpgradeSamlIdpSpConnection());
	}

	@Reference
	private ConfigurationAdmin _configurationAdmin;

}