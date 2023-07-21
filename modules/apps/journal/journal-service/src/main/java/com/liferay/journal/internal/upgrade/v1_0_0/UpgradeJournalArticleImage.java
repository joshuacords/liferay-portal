/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.upgrade.v1_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Inácio Nery
 */
public class UpgradeJournalArticleImage extends UpgradeProcess {

	protected void deleteOrphanJournalArticleImages() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("delete from JournalArticleImage where not exists");
		sb.append("(select 1 from Image where");
		sb.append("(JournalArticleImage.articleImageId = Image.imageId))");

		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(sb.toString())) {

			ps.executeUpdate();
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		deleteOrphanJournalArticleImages();

		updateJournalArticleImagesInstanceId();

		updateJournalArticleImagesName();
	}

	protected void updateJournalArticleImagesInstanceId() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select articleId, elName from JournalArticleImage where " +
					"(elInstanceId = '' or elInstanceId is null) group by " +
						"articleId, elName");
			ResultSet rs = ps1.executeQuery()) {

			try (PreparedStatement ps2 =
					AutoBatchPreparedStatementUtil.autoBatch(
						connection.prepareStatement(
							"update JournalArticleImage set elInstanceId = ? " +
								"where articleId = ? and elName = ?"))) {

				while (rs.next()) {
					String articleId = rs.getString(1);
					String elName = rs.getString(2);

					ps2.setString(1, StringUtil.randomString(4));
					ps2.setString(2, articleId);
					ps2.setString(3, elName);

					ps2.addBatch();
				}

				ps2.executeBatch();
			}
		}
	}

	protected void updateJournalArticleImagesName() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select articleImageId, elName from JournalArticleImage");
			ResultSet rs = ps1.executeQuery()) {

			try (PreparedStatement ps2 =
					AutoBatchPreparedStatementUtil.autoBatch(
						connection.prepareStatement(
							"update JournalArticleImage set elName = ? where " +
								"articleImageId = ?"))) {

				while (rs.next()) {
					String elName = rs.getString(2);

					int lastIndexOf = elName.lastIndexOf(StringPool.UNDERLINE);

					if (lastIndexOf < 1) {
						continue;
					}

					String index = elName.substring(lastIndexOf + 1);

					if (!Validator.isNumber(index)) {
						continue;
					}

					ps2.setString(1, elName.substring(0, lastIndexOf));
					ps2.setLong(2, rs.getLong(1));

					ps2.addBatch();
				}

				ps2.executeBatch();
			}
		}
	}

}