/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upgrade;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Juan Fernández
 * @author Sergio González
 */
public abstract class BaseUpgradeAdminPortlets extends UpgradeProcess {

	protected void addResourcePermission(
			long resourcePermissionId, long companyId, String name, int scope,
			String primKey, long roleId, long actionIds)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				StringBundler.concat(
					"insert into ResourcePermission (resourcePermissionId, ",
					"companyId, name, scope, primKey, primKeyId, roleId, ",
					"actionIds, viewActionId) values (?, ?, ?, ?, ?, ?, ?, ?, ",
					"?)"))) {

			boolean viewActionId = false;

			if ((actionIds % 2) == 1) {
				viewActionId = true;
			}

			ps.setLong(1, resourcePermissionId);
			ps.setLong(2, companyId);
			ps.setString(3, name);
			ps.setInt(4, scope);
			ps.setString(5, primKey);
			ps.setLong(6, GetterUtil.getLong(primKey));
			ps.setLong(7, roleId);
			ps.setLong(8, actionIds);
			ps.setBoolean(9, viewActionId);

			ps.executeUpdate();
		}
	}

	protected long getBitwiseValue(String name, String actionId)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"select bitwiseValue from ResourceAction where name = ? and " +
					"actionId = ?")) {

			ps.setString(1, name);
			ps.setString(2, actionId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getLong("bitwiseValue");
				}

				return 0;
			}
		}
	}

	protected long getControlPanelGroupId() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select groupId from Group_ where name = '" +
					GroupConstants.CONTROL_PANEL + "'");
			ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getLong("groupId");
			}

			return 0;
		}
	}

	protected void updateAccessInControlPanelPermission(
			String portletFrom, String portletTo)
		throws Exception {

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long bitwiseValue = getBitwiseValue(
				portletFrom, ActionKeys.ACCESS_IN_CONTROL_PANEL);

			try (PreparedStatement ps = connection.prepareStatement(
					"select * from ResourcePermission where name = ?")) {

				ps.setString(1, portletFrom);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						long actionIds = rs.getLong("actionIds");

						if ((actionIds & bitwiseValue) != 0) {
							actionIds = actionIds & ~bitwiseValue;

							long resourcePermissionId = rs.getLong(
								"resourcePermissionId");

							runSQL(
								StringBundler.concat(
									"update ResourcePermission set actionIds ",
									"= ", actionIds,
									" where resourcePermissionId = ",
									resourcePermissionId));

							resourcePermissionId = increment(
								ResourcePermission.class.getName());

							long companyId = rs.getLong("companyId");
							int scope = rs.getInt("scope");
							String primKey = rs.getString("primKey");
							long roleId = rs.getLong("roleId");

							actionIds = rs.getLong("actionIds");

							actionIds |= bitwiseValue;

							addResourcePermission(
								resourcePermissionId, companyId, portletTo,
								scope, primKey, roleId, actionIds);
						}
					}
				}
			}
		}
	}

}