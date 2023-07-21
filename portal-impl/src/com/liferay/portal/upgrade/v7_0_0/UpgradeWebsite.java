/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v7_0_0.util.WebsiteTable;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeWebsite extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(WebsiteTable.class, new AlterColumnType("typeId", "LONG"));
	}

}