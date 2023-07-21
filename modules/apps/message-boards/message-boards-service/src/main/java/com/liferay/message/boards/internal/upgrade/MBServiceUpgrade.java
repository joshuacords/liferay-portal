/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.internal.upgrade;

import com.liferay.message.boards.internal.upgrade.v1_0_0.UpgradeClassNames;
import com.liferay.message.boards.internal.upgrade.v1_0_1.UpgradeUnsupportedGuestPermissions;
import com.liferay.message.boards.internal.upgrade.v1_1_0.UpgradeMBThread;
import com.liferay.message.boards.internal.upgrade.v2_0_0.util.MBBanTable;
import com.liferay.message.boards.internal.upgrade.v2_0_0.util.MBCategoryTable;
import com.liferay.message.boards.internal.upgrade.v2_0_0.util.MBDiscussionTable;
import com.liferay.message.boards.internal.upgrade.v2_0_0.util.MBMailingListTable;
import com.liferay.message.boards.internal.upgrade.v2_0_0.util.MBMessageTable;
import com.liferay.message.boards.internal.upgrade.v2_0_0.util.MBStatsUserTable;
import com.liferay.message.boards.internal.upgrade.v2_0_0.util.MBThreadFlagTable;
import com.liferay.message.boards.internal.upgrade.v2_0_0.util.MBThreadTable;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class MBServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.1", "1.0.0", new UpgradeClassNames());

		registry.register(
			"1.0.0", "1.0.1",
			new UpgradeUnsupportedGuestPermissions(
				_resourceActionLocalService, _resourcePermissionLocalService,
				_roleLocalService));

		registry.register(
			"com.liferay.message.boards.service", "1.0.1", "1.1.0",
			new UpgradeMBThread());

		registry.register(
			"1.1.0", "2.0.0",
			new BaseUpgradeSQLServerDatetime(
				new Class<?>[] {
					MBBanTable.class, MBCategoryTable.class,
					MBDiscussionTable.class, MBMailingListTable.class,
					MBMessageTable.class, MBStatsUserTable.class,
					MBThreadFlagTable.class, MBThreadTable.class
				}));
	}

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}