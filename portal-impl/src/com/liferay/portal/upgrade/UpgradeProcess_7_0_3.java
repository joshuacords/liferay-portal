/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.upgrade.v7_0_3.UpgradeGroup;
import com.liferay.portal.upgrade.v7_0_3.UpgradeMessageBoards;
import com.liferay.portal.upgrade.v7_0_3.UpgradeModules;
import com.liferay.portal.upgrade.v7_0_3.UpgradeOracle;
import com.liferay.portal.upgrade.v7_0_3.UpgradeOrganization;
import com.liferay.portal.upgrade.v7_0_3.UpgradeSQLServer;
import com.liferay.portal.upgrade.v7_0_3.UpgradeSybase;

/**
 * @author Adolfo Pérez
 */
public class UpgradeProcess_7_0_3 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return ReleaseInfo.RELEASE_7_0_3_BUILD_NUMBER;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(new UpgradeGroup());
		upgrade(new UpgradeMessageBoards());
		upgrade(new UpgradeModules());
		upgrade(new UpgradeOrganization());
		upgrade(new UpgradeOracle());
		upgrade(new UpgradeSQLServer());
		upgrade(new UpgradeSybase());

		clearIndexesCache();
	}

}