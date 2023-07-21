/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_1_x;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortletKeys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Roberto Díaz
 */
public class UpgradeAnnouncementsPortletPreferences extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				StringBundler.concat(
					"select companyId, preferences from PortletPreferences ",
					"where portletId = '", _PORTLET_ID, "' AND ownerType = ",
					PortletKeys.PREFS_OWNER_TYPE_COMPANY));
			PreparedStatement ps2 = connection.prepareStatement(
				StringBundler.concat(
					"select portletPreferencesId, preferences from ",
					"PortletPreferences where companyId = ? AND portletId = ? ",
					"AND ownerType = ?"));
			PreparedStatement ps3 = AutoBatchPreparedStatementUtil.autoBatch(
				connection.prepareStatement(
					"update PortletPreferences set preferences = ? where " +
						"portletPreferencesId = ?"));
			ResultSet rs1 = ps1.executeQuery()) {

			while (rs1.next()) {
				String preferences = rs1.getString("preferences");

				if (preferences.equals(PortletConstants.DEFAULT_PREFERENCES)) {
					continue;
				}

				long companyId = rs1.getLong("companyId");

				ps2.setLong(1, companyId);

				ps2.setString(2, _PORTLET_ID);
				ps2.setInt(3, PortletKeys.PREFS_OWNER_TYPE_LAYOUT);

				try (ResultSet rs2 = ps2.executeQuery()) {
					while (rs2.next()) {
						String preferences2 = rs2.getString("preferences");

						if (preferences2.equals(
								PortletConstants.DEFAULT_PREFERENCES)) {

							ps3.setString(1, preferences);
							ps3.setLong(2, rs2.getLong("portletPreferencesId"));

							ps3.addBatch();
						}
					}

					ps3.executeBatch();
				}
			}
		}
	}

	private static final String _PORTLET_ID =
		"com_liferay_announcements_web_portlet_AnnouncementsPortlet";

}