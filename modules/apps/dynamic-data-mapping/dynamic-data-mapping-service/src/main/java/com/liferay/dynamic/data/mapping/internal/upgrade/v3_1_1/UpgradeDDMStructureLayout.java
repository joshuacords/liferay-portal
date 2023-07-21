/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v3_1_1;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Leonardo Barros
 */
public class UpgradeDDMStructureLayout extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeSchema();

		populateFields();
	}

	protected void populateFields() throws Exception {
		long classNameId = PortalUtil.getClassNameId(DDMStructure.class);

		try (PreparedStatement ps1 = connection.prepareStatement(
				"select structureLayoutId from DDMStructureLayout where " +
					"structureLayoutKey is null or structureLayoutKey = ''");
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					StringBundler.concat(
						"update DDMStructureLayout set classNameId = ?, ",
						"structureLayoutKey = ? where structureLayoutId = ",
						"?"))) {

			try (ResultSet rs = ps1.executeQuery()) {
				while (rs.next()) {
					ps2.setLong(1, classNameId);
					ps2.setString(2, String.valueOf(increment()));
					ps2.setLong(3, rs.getLong(1));

					ps2.addBatch();
				}

				ps2.executeBatch();
			}
		}
	}

	protected void upgradeSchema() throws Exception {
		if (!hasColumn("DDMStructureLayout", "classNameId") &&
			!hasColumn("DDMStructureLayout", "structureLayoutKey")) {

			runSQL("alter table DDMStructureLayout add classNameId LONG");
			runSQL(
				"alter table DDMStructureLayout add structureLayoutKey " +
					"VARCHAR(75) null");
		}
	}

}