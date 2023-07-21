/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_1_x;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v7_1_x.util.UserGroupTable;

/**
 * @author Jonathan McCann
 */
public class UpgradeUserGroup extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumnType("UserGroup", "name", "VARCHAR(255) null")) {
			alter(
				UserGroupTable.class,
				new AlterColumnType("name", "VARCHAR(255) null"));
		}
	}

}