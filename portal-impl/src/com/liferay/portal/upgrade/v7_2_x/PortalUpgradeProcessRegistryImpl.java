/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_2_x;

import com.liferay.portal.kernel.upgrade.DummyUpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.version.Version;
import com.liferay.portal.upgrade.util.PortalUpgradeProcessRegistry;

import java.util.TreeMap;

/**
 * @author JosĂ© Ă�ngel JimĂ©nez
 */
public class PortalUpgradeProcessRegistryImpl
	implements PortalUpgradeProcessRegistry {

	@Override
	public void registerUpgradeProcesses(
		TreeMap<Version, UpgradeProcess> upgradeProcesses) {

		upgradeProcesses.put(
			new Version(3, 0, 0), new UpgradeSQLServerDatetime());

		upgradeProcesses.put(new Version(4, 0, 0), new UpgradeSchema());

		upgradeProcesses.put(new Version(5, 0, 0), new UpgradeBadColumnNames());

		upgradeProcesses.put(new Version(5, 0, 1), new UpgradePersonalMenu());

		upgradeProcesses.put(new Version(5, 0, 2), new UpgradeCountry());

		upgradeProcesses.put(new Version(5, 0, 3), new UpgradeModules());

		upgradeProcesses.put(new Version(5, 0, 4), new UpgradeLayout());

		upgradeProcesses.put(new Version(5, 0, 5), new UpgradeThemeId());

		upgradeProcesses.put(new Version(5, 1, 0), new UpgradeMVCCVersion());

		upgradeProcesses.put(new Version(5, 1, 1), new DummyUpgradeProcess());

		upgradeProcesses.put(
			new Version(5, 1, 2), new UpgradeLayoutDescription());

		upgradeProcesses.put(new Version(5, 1, 3), new UpgradeLayoutTitle());

		upgradeProcesses.put(new Version(5, 1, 4), new UpgradeAssetCategory());
	}

}