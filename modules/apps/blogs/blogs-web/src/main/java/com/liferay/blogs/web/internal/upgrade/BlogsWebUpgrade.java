/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.internal.upgrade;

import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.web.internal.upgrade.v1_0_0.UpgradePortletPreferences;
import com.liferay.blogs.web.internal.upgrade.v1_0_0.UpgradePortletSettings;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.upgrade.BaseUpgradeStagingGroupTypeSettings;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class BlogsWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.2.1", new DummyUpgradeStep());

		registry.register(
			"0.0.1", "1.0.0", new UpgradePortletPreferences(),
			new UpgradePortletSettings(_settingsFactory));

		registry.register(
			"1.0.0", "1.1.0",
			new com.liferay.blogs.web.internal.upgrade.v1_1_0.
				UpgradePortletPreferences());

		registry.register(
			"1.1.0", "1.2.0",
			new com.liferay.blogs.web.internal.upgrade.v1_2_0.
				UpgradePortletPreferences());

		registry.register(
			"1.2.0", "1.2.1",
			new BaseUpgradeStagingGroupTypeSettings(
				_companyLocalService, _groupLocalService,
				BlogsPortletKeys.BLOGS, BlogsPortletKeys.BLOGS_ADMIN));
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	@Reference
	private SettingsFactory _settingsFactory;

}