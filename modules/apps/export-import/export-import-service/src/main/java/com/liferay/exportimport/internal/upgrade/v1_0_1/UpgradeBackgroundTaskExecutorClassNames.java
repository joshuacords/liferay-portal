/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.upgrade.v1_0_1;

import com.liferay.exportimport.internal.upgrade.BaseUpgradeBackgroundTaskExecutorClassNames;
import com.liferay.exportimport.kernel.background.task.BackgroundTaskExecutorNames;

/**
 * @author Jonathan McCann
 */
public class UpgradeBackgroundTaskExecutorClassNames
	extends BaseUpgradeBackgroundTaskExecutorClassNames {

	@Override
	protected String[][] getRenameTaskExecutorClassNames() {
		return new String[][] {
			{
				"com.liferay.dynamic.data.mapping.background.task." +
					"DDMStructureIndexerBackgroundTaskExecutor",
				BackgroundTaskExecutorNames.
					DDM_STRUCTURE_INDEXER_BACKGROUND_TASK_EXECUTOR
			}
		};
	}

}