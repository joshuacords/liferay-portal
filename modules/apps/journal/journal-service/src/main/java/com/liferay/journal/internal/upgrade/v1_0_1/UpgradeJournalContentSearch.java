/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.upgrade.v1_0_1;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jonathan McCann
 */
public class UpgradeJournalContentSearch extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradePortletId();
	}

	protected void upgradePortletId() throws Exception {
		try (PreparedStatement ps1 = connection.prepareStatement(
				"select * from JournalContentSearch where portletId like " +
					"'56%'");
			PreparedStatement ps2 = connection.prepareStatement(
				"select contentSearchId from JournalContentSearch where " +
					"groupId = ? AND privateLayout = ? AND layoutId = ? AND " +
						"portletId = ? AND articleId = ?");
			PreparedStatement ps3 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update JournalContentSearch set portletId = ? where " +
						"contentSearchId = ?");
			ResultSet rs = ps1.executeQuery()) {

			while (rs.next()) {
				long contentSearchId = rs.getLong("contentSearchId");
				long groupId = rs.getLong("groupId");
				boolean privateLayout = rs.getBoolean("privateLayout");
				long layoutId = rs.getLong("layoutId");
				String portletId = rs.getString("portletId");
				String articleId = rs.getString("articleId");

				String newPortletId = StringUtil.replaceFirst(
					portletId, _OLD_ROOT_PORTLET_ID, _NEW_ROOT_PORTLET_ID);

				ps2.setLong(1, groupId);

				ps2.setBoolean(2, privateLayout);

				ps2.setLong(3, layoutId);

				ps2.setString(4, newPortletId);

				ps2.setString(5, articleId);

				try (ResultSet rs2 = ps2.executeQuery()) {
					if (rs2.next()) {
						runSQL(
							"delete from JournalContentSearch where " +
								"contentSearchId = " + contentSearchId);
					}
					else {
						ps3.setString(1, newPortletId);

						ps3.setLong(2, contentSearchId);

						ps3.addBatch();
					}
				}
			}

			ps3.executeBatch();
		}
	}

	private static final String _NEW_ROOT_PORTLET_ID =
		"com_liferay_journal_content_web_portlet_JournalContentPortlet";

	private static final String _OLD_ROOT_PORTLET_ID = "56";

}