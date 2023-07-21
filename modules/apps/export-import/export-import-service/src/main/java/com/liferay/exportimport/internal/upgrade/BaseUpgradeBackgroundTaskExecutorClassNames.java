/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.upgrade;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

/**
 * @author Jonathan McCann
 */
public abstract class BaseUpgradeBackgroundTaskExecutorClassNames
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		String[][] renameTaskExecutorClassNamesArray =
			getRenameTaskExecutorClassNames();

		for (String[] renameTaskExecutorClassName :
				renameTaskExecutorClassNamesArray) {

			try (LoggingTimer loggingTimer = new LoggingTimer(
					renameTaskExecutorClassName[0])) {

				StringBundler sb = new StringBundler(5);

				sb.append(
					"update BackgroundTask set taskExecutorClassName = '");
				sb.append(renameTaskExecutorClassName[1]);
				sb.append("' where taskExecutorClassName = '");
				sb.append(renameTaskExecutorClassName[0]);
				sb.append("'");

				runSQL(sb.toString());
			}
		}
	}

	protected abstract String[][] getRenameTaskExecutorClassNames();

}