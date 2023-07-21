/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.internal.upgrade.v1_1_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Adolfo Pérez
 */
public class UpgradeMBThread extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("MBThread", "title")) {
			runSQL("alter table MBThread add title STRING null");
		}

		try (PreparedStatement ps1 = connection.prepareStatement(
				StringBundler.concat(
					"select MBThread.threadId, MBMessage.subject from ",
					"MBThread inner join MBMessage on MBThread.rootMessageId ",
					"= MBMessage.messageId and MBThread.threadId = ",
					"MBMessage.threadId"));
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update MBThread set title = ? where threadId = ?");
			ResultSet rs = ps1.executeQuery()) {

			while (rs.next()) {
				String title = rs.getString(2);

				ps2.setString(1, title);

				long threadId = rs.getLong(1);

				ps2.setLong(2, threadId);

				ps2.addBatch();
			}

			ps2.executeBatch();
		}
	}

}