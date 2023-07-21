/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.upgrade.v4_0_1;

import com.liferay.batch.engine.internal.upgrade.v4_0_1.util.BatchEngineExportTaskTable;
import com.liferay.batch.engine.internal.upgrade.v4_0_1.util.BatchEngineImportTaskTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Riccardo Ferrari
 */
public class UpgradeClassName extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumnType(
				getTableName(BatchEngineExportTaskTable.class), "className",
				"VARCHAR(75) null")) {

			alter(
				BatchEngineExportTaskTable.class,
				new AlterColumnType("className", "VARCHAR(255) null"));
		}

		if (hasColumnType(
				getTableName(BatchEngineImportTaskTable.class), "className",
				"VARCHAR(75) null")) {

			alter(
				BatchEngineImportTaskTable.class,
				new AlterColumnType("className", "VARCHAR(255) null"));
		}
	}

}