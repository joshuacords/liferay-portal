/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradeCompanyId;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeCompanyId extends BaseUpgradeCompanyId {

	@Override
	protected void doUpgrade() throws Exception {
		super.doUpgrade();

		runSQL(
			"create index IX_DB81EB42 on DDMStorageLink " +
				"(uuid_[$COLUMN_LENGTH:75$], companyId)");
	}

	@Override
	protected TableUpdater[] getTableUpdaters() {
		return new TableUpdater[] {
			new TableUpdater("DDMStorageLink", "DDMStructure", "structureId"),
			new TableUpdater("DDMStructureLink", "DDMStructure", "structureId")
		};
	}

}