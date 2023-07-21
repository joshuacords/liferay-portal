/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upgrade;

import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;

/**
 * @author Adolfo Pérez
 */
public abstract class BaseUpgradeRepository extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateRepositoryPortletId();
	}

	protected abstract String[][] getRenamePortletNamesArray();

	protected void updateRepositoryPortletId() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			for (String[] renamePortletNames : getRenamePortletNamesArray()) {
				String oldPortletName = renamePortletNames[0];
				String newPortletName = renamePortletNames[1];

				try (PreparedStatement ps = connection.prepareStatement(
						"update Repository set portletId = ?, name = ? where " +
							"portletId = ?")) {

					ps.setString(1, newPortletName);
					ps.setString(2, newPortletName);
					ps.setString(3, oldPortletName);

					ps.executeUpdate();
				}
			}
		}
	}

}