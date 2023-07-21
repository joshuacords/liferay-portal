/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.asset.auto.tagger.tensorflow.internal.upgrade;

import com.liferay.document.library.asset.auto.tagger.tensorflow.internal.upgrade.v0_0_2.UpgradeTensorFlowModel;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class TensorFlowAssetAutoTagProviderUpgrade
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"0.0.0", "0.0.2",
			new UpgradeTensorFlowModel(
				_configurationAdmin, _configurationProvider, _store));
	}

	@Reference
	private ConfigurationAdmin _configurationAdmin;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference(target = "(dl.store.upgrade=true)")
	private Store _store;

}