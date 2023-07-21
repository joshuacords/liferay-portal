/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.captcha.internal.upgrade;

import com.liferay.captcha.internal.upgrade.v1_0_0.UpgradeCaptchaConfiguration;
import com.liferay.captcha.internal.upgrade.v1_1_0.UpgradeCaptchaConfigurationPreferences;
import com.liferay.portal.configuration.upgrade.PrefsPropsToConfigurationUpgradeHelper;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pei-Jung Lan
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class CaptchaUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.1.0", new DummyUpgradeStep());

		registry.register(
			"0.0.1", "1.0.0",
			new UpgradeCaptchaConfiguration(
				_prefsPropsToConfigurationUpgradeHelper));

		registry.register(
			"1.0.0", "1.1.0",
			new UpgradeCaptchaConfigurationPreferences(_configurationAdmin));
	}

	@Reference
	private ConfigurationAdmin _configurationAdmin;

	@Reference
	private PrefsPropsToConfigurationUpgradeHelper
		_prefsPropsToConfigurationUpgradeHelper;

}