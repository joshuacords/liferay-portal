/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upgrade;

import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Map;

/**
 * @author Eduardo García
 */
public abstract class RenameUpgradePortalPreferences extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Map<String, String> preferenceNamesMap = getPreferenceNamesMap();

		for (Map.Entry<String, String> entry : preferenceNamesMap.entrySet()) {
			updatePreferences(
				"PortalPreferences", "portalPreferencesId", entry.getKey(),
				entry.getValue());
		}
	}

	protected abstract Map<String, String> getPreferenceNamesMap();

	protected void updatePreferences(
			String tableName, String primaryKeyColumnName, String oldValue,
			String newValue)
		throws Exception {

		try (LoggingTimer loggingTimer = new LoggingTimer(tableName)) {
			StringBundler sb = new StringBundler(9);

			sb.append("update ");
			sb.append(tableName);
			sb.append(" set preferences = replace(preferences, '");
			sb.append(oldValue);
			sb.append("', '");
			sb.append(newValue);
			sb.append("') where preferences like '%");
			sb.append(oldValue);
			sb.append("%'");

			try {
				runSQL(sb.toString());
			}
			catch (Exception exception) {
				sb = new StringBundler(7);

				sb.append("select ");
				sb.append(primaryKeyColumnName);
				sb.append(", preferences from ");
				sb.append(tableName);
				sb.append(" where preferences like '%");
				sb.append(oldValue);
				sb.append("%'");

				try (PreparedStatement ps = connection.prepareStatement(
						sb.toString());
					ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						long primaryKey = rs.getLong(primaryKeyColumnName);
						String preferences = rs.getString("preferences");

						updatePreferences(
							tableName, primaryKeyColumnName, oldValue, newValue,
							primaryKey, preferences);
					}
				}
			}
		}
	}

	protected void updatePreferences(
			String tableName, String primaryKeyColumnName, String oldValue,
			String newValue, long primaryKey, String preferences)
		throws Exception {

		preferences = StringUtil.replace(preferences, oldValue, newValue);

		StringBundler sb = new StringBundler(5);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set preferences = ? where ");
		sb.append(primaryKeyColumnName);
		sb.append(" = ?");

		try (PreparedStatement ps = connection.prepareStatement(
				sb.toString())) {

			ps.setString(1, preferences);
			ps.setLong(2, primaryKey);

			ps.executeUpdate();
		}
	}

}