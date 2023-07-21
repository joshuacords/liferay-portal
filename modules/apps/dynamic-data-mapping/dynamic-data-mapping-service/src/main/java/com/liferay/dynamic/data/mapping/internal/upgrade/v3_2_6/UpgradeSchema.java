/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v3_2_6;

import com.liferay.dynamic.data.mapping.internal.upgrade.v3_2_6.util.DDMFormInstanceTable;
import com.liferay.dynamic.data.mapping.internal.upgrade.v3_2_6.util.DDMFormInstanceVersionTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

/**
 * @author Rodrigo Paulino
 */
public class UpgradeSchema extends UpgradeProcess {

	protected void alterTables() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			if (!hasColumnType(
					DDMFormInstanceTable.TABLE_NAME, "description",
					"TEXT null")) {

				alter(
					DDMFormInstanceTable.class,
					new AlterColumnType("description", "TEXT null"));
			}

			if (!hasColumnType(
					DDMFormInstanceVersionTable.TABLE_NAME, "description",
					"TEXT null")) {

				alter(
					DDMFormInstanceVersionTable.class,
					new AlterColumnType("description", "TEXT null"));
			}
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		alterTables();
	}

}