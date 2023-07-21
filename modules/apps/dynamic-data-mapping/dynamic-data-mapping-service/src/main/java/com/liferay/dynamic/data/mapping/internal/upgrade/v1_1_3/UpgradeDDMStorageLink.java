/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_3;

import com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_3.util.DDMStorageLinkTable;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Pedro Queiroz
 */
public class UpgradeDDMStorageLink extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("DDMStorageLink", "structureVersionId")) {
			alter(
				DDMStorageLinkTable.class,
				new AlterTableAddColumn("structureVersionId LONG"));
		}

		try (PreparedStatement ps1 = connection.prepareStatement(
				StringBundler.concat(
					"select distinct DDMStorageLink.structureId, ",
					"TEMP_TABLE.structureVersionId from DDMStorageLink inner ",
					"join (select structureId, max(structureVersionId) as ",
					"structureVersionId from DDMStructureVersion group by ",
					"DDMStructureVersion.structureId) TEMP_TABLE on ",
					"DDMStorageLink.structureId = TEMP_TABLE.structureId"));
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMStorageLink set structureVersionId = ? where " +
						"structureId = ?");
			ResultSet rs = ps1.executeQuery()) {

			while (rs.next()) {
				long ddmStructureVersionId = rs.getLong("structureVersionId");

				if (ddmStructureVersionId > 0) {
					ps2.setLong(1, ddmStructureVersionId);
					ps2.setLong(2, rs.getLong("structureId"));

					ps2.addBatch();
				}
			}

			ps2.executeBatch();
		}
	}

}