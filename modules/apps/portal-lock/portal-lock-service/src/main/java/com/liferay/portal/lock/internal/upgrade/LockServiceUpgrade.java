/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.lock.internal.upgrade;

import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.lock.internal.upgrade.v1_0_0.UpgradeLock;
import com.liferay.portal.lock.internal.upgrade.v2_0_0.util.LockTable;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Miguel Pastor
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class LockServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.1", "1.0.0", new UpgradeLock());

		registry.register(
			"1.0.0", "2.0.0",
			new BaseUpgradeSQLServerDatetime(new Class<?>[] {LockTable.class}));

		registry.register(
			"2.0.0", "2.0.1",
			new com.liferay.portal.lock.internal.upgrade.v2_0_1.UpgradeLock());
	}

}