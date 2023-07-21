/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.web.internal.upgrade;

import com.liferay.portal.configuration.upgrade.PrefsPropsToConfigurationUpgradeHelper;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.users.admin.constants.UsersAdminPortletKeys;
import com.liferay.users.admin.web.internal.upgrade.v1_0_0.UpgradeFileUploadsConfiguration;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Drew Brokke
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class UsersAdminWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		Release release = _releaseLocalService.fetchRelease(
			"com.liferay.users.admin.service");

		if (release != null) {
			release.setServletContextName("com.liferay.users.admin.web");

			_releaseLocalService.updateRelease(release);
		}

		registry.register("0.0.0", "1.0.1", new DummyUpgradeStep());

		registry.register(
			"0.0.1", "1.0.0",
			new UpgradeFileUploadsConfiguration(
				_prefsPropsToConfigurationUpgradeHelper));

		registry.register(
			"1.0.0", "1.0.1",
			new BaseUpgradePortletId() {

				@Override
				protected String[][] getRenamePortletIdsArray() {
					return new String[][] {
						{"2", UsersAdminPortletKeys.MY_ACCOUNT}
					};
				}

			});
	}

	@Reference
	private PrefsPropsToConfigurationUpgradeHelper
		_prefsPropsToConfigurationUpgradeHelper;

	@Reference
	private ReleaseLocalService _releaseLocalService;

}