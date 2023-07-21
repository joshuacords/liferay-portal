/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.upgrade.v0_0_5;

import com.liferay.portal.kernel.upgrade.BaseUpgradeCompanyId;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeCompanyId extends BaseUpgradeCompanyId {

	@Override
	protected void doUpgrade() throws Exception {
		super.doUpgrade();

		runSQL(
			"create index IX_CC7576C7 on JournalArticleResource " +
				"(uuid_[$COLUMN_LENGTH:75$], companyId)");
	}

	@Override
	protected TableUpdater[] getTableUpdaters() {
		return new TableUpdater[] {
			new TableUpdater("JournalArticleImage", "Group_", "groupId"),
			new TableUpdater("JournalArticleResource", "Group_", "groupId")
		};
	}

}