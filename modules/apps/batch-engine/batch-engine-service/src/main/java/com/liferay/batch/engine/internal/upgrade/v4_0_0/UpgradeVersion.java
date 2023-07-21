/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.upgrade.v4_0_0;

import com.liferay.batch.engine.internal.upgrade.v4_0_0.util.BatchEngineExportTaskTable;
import com.liferay.batch.engine.internal.upgrade.v4_0_0.util.BatchEngineImportTaskTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Ivica Cardic
 */
public class UpgradeVersion extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn("BatchEngineExportTask", "version")) {
			alter(
				BatchEngineExportTaskTable.class,
				new AlterTableDropColumn("version"));
		}

		if (hasColumn("BatchEngineImportTask", "version")) {
			alter(
				BatchEngineImportTaskTable.class,
				new AlterTableDropColumn("version"));
		}
	}

}