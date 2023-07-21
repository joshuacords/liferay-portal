/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.google.docs.internal.upgrade;

import com.liferay.document.library.google.docs.internal.upgrade.v1_0_0.UpgradeFileEntryTypeName;
import com.liferay.document.library.google.docs.internal.upgrade.v1_0_0.UpgradePortletPreferences;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class GoogleDocsUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"0.0.0", "1.0.0",
			new UpgradeFileEntryTypeName(_dlFileEntryTypeLocalService));

		registry.register(
			"1.0.0", "1.0.1",
			new UpgradePortletPreferences(_configurationProvider, _prefsProps));
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private DLFileEntryTypeLocalService _dlFileEntryTypeLocalService;

	@Reference
	private PrefsProps _prefsProps;

}