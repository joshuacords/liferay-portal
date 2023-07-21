/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.internal.upgrade.v4_1_2;

import com.liferay.calendar.internal.upgrade.v4_1_2.util.CalendarNotificationTemplateTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Nara Andrade
 */
public class UpgradeCalendarNotificationTemplate extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumnType(
				"CalendarNotificationTemplate", "notificationTypeSettings",
				"VARCHAR(200) null")) {

			alter(
				CalendarNotificationTemplateTable.class,
				new AlterColumnType(
					"notificationTypeSettings", "VARCHAR(200) null"));
		}
	}

}