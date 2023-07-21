/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.web.internal.upgrade;

import com.liferay.oauth.web.internal.upgrade.v1_0_0.UpgradePortletId;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.release.BaseUpgradeWebModuleRelease;

import org.osgi.service.component.annotations.Component;

/**
 * @author Carlos Sierra Andrés
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class OAuthWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		try {
			BaseUpgradeWebModuleRelease baseUpgradeWebModuleRelease =
				new BaseUpgradeWebModuleRelease() {

					@Override
					protected String getBundleSymbolicName() {
						return "com.liferay.oauth.web";
					}

					@Override
					protected String[] getPortletIds() {
						return new String[] {
							"1_WAR_oauthportlet", "2_WAR_oauthportlet",
							"3_WAR_oauthportlet"
						};
					}

				};

			baseUpgradeWebModuleRelease.upgrade();
		}
		catch (UpgradeException upgradeException) {
			throw new RuntimeException(upgradeException);
		}

		registry.register(
			"com.liferay.oauth.web", "0.0.0", "1.0.0", new DummyUpgradeStep());

		registry.register(
			"com.liferay.oauth.web", "0.0.1", "1.0.0", new UpgradePortletId());
	}

}