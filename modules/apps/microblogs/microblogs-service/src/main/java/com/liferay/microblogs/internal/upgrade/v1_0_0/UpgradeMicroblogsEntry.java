/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.microblogs.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Calvin Keum
 */
public class UpgradeMicroblogsEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateMicroBlogsEntry();
	}

	protected void updateMicroBlogsEntry() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			if (hasColumn("MicroblogsEntry", "creatorClassNameId") ||
				hasColumn("MicroblogsEntry", "creatorClassPK")) {

				return;
			}

			runSQL("alter table MicroblogsEntry add creatorClassNameId LONG");
			runSQL("alter table MicroblogsEntry add creatorClassPK LONG");
			runSQL(
				"create index IX_6AA6B164 on MicroblogsEntry (" +
					"creatorClassNameId, type_)");
			runSQL(
				"create index IX_14ACFA9 on MicroblogsEntry (" +
					"creatorClassNameId, creatorClassPK, type_)");

			runSQL(
				"update MicroblogsEntry set creatorClassNameId = " +
					PortalUtil.getClassNameId(User.class) +
						", creatorClassPK = MicroblogsEntry.userId");
		}
	}

}