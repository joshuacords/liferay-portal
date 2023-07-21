/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.upgrade.v3_0_0;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ImageLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Pavel Savinov
 */
public class UpgradeJournalArticleImage extends UpgradeProcess {

	public UpgradeJournalArticleImage(ImageLocalService imageLocalService) {
		_imageLocalService = imageLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable("JournalArticleImage")) {
			return;
		}

		try (LoggingTimer loggingTimer = new LoggingTimer();
			Statement statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(
				"select articleImageId from JournalArticleImage")) {

			while (rs1.next()) {
				long articleImageId = rs1.getLong(1);

				_imageLocalService.deleteImage(articleImageId);
			}
		}

		runSQL(connection, "drop table JournalArticleImage");

		if (_log.isInfoEnabled()) {
			_log.info("Deleted table JournalArticleImage");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeJournalArticleImage.class);

	private final ImageLocalService _imageLocalService;

}