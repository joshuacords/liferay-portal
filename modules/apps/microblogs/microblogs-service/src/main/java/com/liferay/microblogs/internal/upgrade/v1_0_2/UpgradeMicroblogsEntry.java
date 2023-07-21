/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.microblogs.internal.upgrade.v1_0_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

/**
 * @author Matthew Kong
 */
public class UpgradeMicroblogsEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		removeReceiverUserId();
		renameReceiverMicroblogsEntryId();
	}

	protected void removeReceiverUserId() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			if (!hasColumn("MicroblogsEntry", "receiverUserId")) {
				return;
			}

			runSQL("drop index IX_7ABB0AB3 on MicroblogsEntry");

			runSQL("alter table MicroblogsEntry drop column receiverUserId");
		}
	}

	protected void renameReceiverMicroblogsEntryId() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			if (!hasColumn("MicroblogsEntry", "receiverMicroblogsEntryId")) {
				return;
			}

			runSQL(
				"alter table MicroblogsEntry add parentMicroblogsEntryId LONG");

			runSQL(
				"update MicroblogsEntry set parentMicroblogsEntryId = " +
					"receiverMicroblogsEntryId");

			runSQL("drop index IX_36CA3D37 on MicroblogsEntry");

			runSQL(
				"alter table MicroblogsEntry drop column " +
					"receiverMicroblogsEntryId");

			runSQL(
				"create index IX_6BD29B9C on MicroblogsEntry (type_, " +
					"parentMicroblogsEntryId)");
		}
	}

}