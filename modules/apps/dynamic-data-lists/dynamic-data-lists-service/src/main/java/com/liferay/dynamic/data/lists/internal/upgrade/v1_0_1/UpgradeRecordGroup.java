/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.internal.upgrade.v1_0_1;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Pedro Queiroz
 */
public class UpgradeRecordGroup extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("select DDLRecordSet.groupId, DDLRecord.recordId from ");
		sb.append("DDLRecord inner join DDLRecordSet on ");
		sb.append("DDLRecord.recordSetId = DDLRecordSet.recordSetId where ");
		sb.append("DDLRecord.groupId != DDLRecordSet.groupId");

		try (PreparedStatement ps1 = connection.prepareStatement(sb.toString());
			ResultSet rs = ps1.executeQuery();
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDLRecord set groupId = ? where recordId = ?")) {

			while (rs.next()) {
				long groupId = rs.getLong("groupId");
				long recordId = rs.getLong("recordId");

				ps2.setLong(1, groupId);
				ps2.setLong(2, recordId);

				ps2.addBatch();
			}

			ps2.executeBatch();
		}
	}

}