/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.internal.upgrade.v1_1_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Sergio González
 */
public class UpgradeWikiNode extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateWikiNodeName();
	}

	protected void updateWikiNodeName() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select nodeId, name from WikiNode");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String name = rs.getString("name");

				if (!Validator.isNumber(name)) {
					continue;
				}

				long nodeId = rs.getLong("nodeId");

				runSQL(
					StringBundler.concat(
						"update WikiNode set name = 'Node ", name,
						"' where nodeId = ", nodeId));
			}
		}
	}

}