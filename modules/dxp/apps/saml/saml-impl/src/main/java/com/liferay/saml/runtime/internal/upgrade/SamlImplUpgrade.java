/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.runtime.internal.upgrade;

import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.saml.runtime.configuration.SamlProviderConfigurationHelper;
import com.liferay.saml.runtime.internal.upgrade.v1_0_0.UpgradeSamlConfigurationPreferences;
import com.liferay.saml.runtime.internal.upgrade.v1_0_0.UpgradeSamlIdpSsoSessionMaxAgeProperty;
import com.liferay.saml.runtime.internal.upgrade.v1_0_0.UpgradeSamlKeyStoreProperties;
import com.liferay.saml.runtime.internal.upgrade.v1_0_0.UpgradeSamlProviderConfigurationPreferences;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stian Sigvartsen
 */
@Component(
	immediate = true,
	service = {SamlImplUpgrade.class, UpgradeStepRegistrator.class}
)
public class SamlImplUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.saml.impl", "0.0.0", "0.0.1",
			new UpgradeSamlConfigurationPreferences(
				_configurationAdmin, _props),
			new UpgradeSamlKeyStoreProperties(_configurationAdmin, _prefsProps),
			new UpgradeSamlProviderConfigurationPreferences(
				_companyLocalService, _prefsProps, _props,
				_samlProviderConfigurationHelper));

		registry.register(
			"com.liferay.saml.impl", "0.0.1", "1.0.0",
			new UpgradeSamlIdpSsoSessionMaxAgeProperty(
				_configurationAdmin, _props));
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ConfigurationAdmin _configurationAdmin;

	@Reference
	private PrefsProps _prefsProps;

	@Reference
	private Props _props;

	@Reference
	private SamlProviderConfigurationHelper _samlProviderConfigurationHelper;

}