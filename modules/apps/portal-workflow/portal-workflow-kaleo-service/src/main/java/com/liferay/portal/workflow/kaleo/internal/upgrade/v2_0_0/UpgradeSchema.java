/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.upgrade.v2_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v2_0_0.util.KaleoNotificationTable;

/**
 * @author Rafael Praxedes
 */
public class UpgradeSchema extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(
			new UpgradeMVCCVersion() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {
						"KaleoAction", "KaleoCondition", "KaleoDefinition",
						"KaleoDefinitionVersion", "KaleoInstance",
						"KaleoInstanceToken", "KaleoLog", "KaleoNode",
						"KaleoNotification", "KaleoNotificationRecipient",
						"KaleoTask", "KaleoTaskAssignment",
						"KaleoTaskAssignmentInstance", "KaleoTaskForm",
						"KaleoTaskFormInstance", "KaleoTaskInstanceToken",
						"KaleoTimer", "KaleoTimerInstanceToken",
						"KaleoTransition"
					};
				}

			});

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			if (!hasColumnType(
					KaleoNotificationTable.TABLE_NAME, "notificationTypes",
					"VARCHAR(255) null")) {

				alter(
					KaleoNotificationTable.class,
					new AlterColumnType(
						"notificationTypes", "VARCHAR(255) null"));
			}
		}
	}

}