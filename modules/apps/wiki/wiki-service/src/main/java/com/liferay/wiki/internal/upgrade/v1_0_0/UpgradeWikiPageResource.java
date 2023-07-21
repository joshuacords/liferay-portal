/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.internal.upgrade.v1_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Akos Thurzo
 */
public class UpgradeWikiPageResource extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateWikiPageResources();
	}

	protected long getGroupId(long resourcePrimKey) throws Exception {
		long groupId = 0;

		try (PreparedStatement ps = connection.prepareStatement(
				"select groupId from WikiPage where resourcePrimKey = ?")) {

			ps.setLong(1, resourcePrimKey);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					groupId = rs.getLong("groupId");
				}
			}
		}

		return groupId;
	}

	protected void updateWikiPageResources() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select resourcePrimKey from WikiPageResource");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long resourcePrimKey = rs.getLong("resourcePrimKey");

				runSQL(
					StringBundler.concat(
						"update WikiPageResource set groupId = ",
						getGroupId(resourcePrimKey),
						" where resourcePrimKey = ", resourcePrimKey));
			}
		}
	}

}