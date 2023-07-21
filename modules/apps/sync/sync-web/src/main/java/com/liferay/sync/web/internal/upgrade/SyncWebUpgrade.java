/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.web.internal.upgrade;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.sync.constants.SyncAdminPortletKeys;
import com.liferay.sync.constants.SyncPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(
	immediate = true,
	service = {SyncWebUpgrade.class, UpgradeStepRegistrator.class}
)
public class SyncWebUpgrade implements UpgradeStepRegistrator {

	@Override
	@SuppressWarnings("deprecation")
	public void register(Registry registry) {
		registry.register(
			"0.0.0", "1.0.0",
			new BaseUpgradePortletId() {

				@Override
				protected String[][] getRenamePortletIdsArray() {
					return new String[][] {
						{
							SyncAdminPortletKeys.SYNC_ADMIN_PORTLET,
							SyncPortletKeys.SYNC_ADMIN_PORTLET
						},
						{
							SyncAdminPortletKeys.SYNC_DEVICES_PORTLET,
							SyncPortletKeys.SYNC_DEVICES_PORTLET
						}
					};
				}

			});
	}

}