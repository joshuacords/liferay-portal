/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.upgrade.v0_0_8;

import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Preston Crary
 * @author Alberto Chaparro
 */
public class UpgradeArticleExpirationDate extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateArticleExpirationDate();
	}

	protected void updateArticleExpirationDate() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			JournalServiceConfiguration journalServiceConfiguration =
				ConfigurationProviderUtil.getCompanyConfiguration(
					JournalServiceConfiguration.class,
					CompanyThreadLocal.getCompanyId());

			if (!journalServiceConfiguration.
					expireAllArticleVersionsEnabled()) {

				return;
			}

			StringBundler sb = new StringBundler(15);

			sb.append("select JournalArticle.* from JournalArticle left join ");
			sb.append("JournalArticle tempJournalArticle on ");
			sb.append("(JournalArticle.groupId = tempJournalArticle.groupId) ");
			sb.append("and (JournalArticle.articleId = ");
			sb.append("tempJournalArticle.articleId) and ");
			sb.append("(JournalArticle.version < tempJournalArticle.version) ");
			sb.append("and (JournalArticle.status = ");
			sb.append("tempJournalArticle.status) where ");
			sb.append("(JournalArticle.classNameId = ");
			sb.append(JournalArticleConstants.CLASSNAME_ID_DEFAULT);
			sb.append(") and (tempJournalArticle.version is null) and ");
			sb.append("(JournalArticle.expirationDate is not null) and ");
			sb.append("(JournalArticle.status = ");
			sb.append(WorkflowConstants.STATUS_APPROVED);
			sb.append(")");

			try (PreparedStatement ps = connection.prepareStatement(
					sb.toString());
				ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					long groupId = rs.getLong("groupId");
					String articleId = rs.getString("articleId");
					Timestamp expirationDate = rs.getTimestamp(
						"expirationDate");
					int status = rs.getInt("status");

					updateExpirationDate(
						groupId, articleId, expirationDate, status);
				}
			}
		}
	}

	protected void updateExpirationDate(
			long groupId, String articleId, Timestamp expirationDate,
			int status)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update JournalArticle set expirationDate = ? where groupId " +
					"= ? and articleId = ? and status = ?")) {

			ps.setTimestamp(1, expirationDate);
			ps.setLong(2, groupId);
			ps.setString(3, articleId);
			ps.setInt(4, status);

			ps.executeUpdate();
		}
	}

}