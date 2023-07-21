/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.license.manager.web.internal.upgrade.v1_0_1;

import com.liferay.license.manager.web.internal.constants.LicenseManagerPortletKeys;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author David Zhang
 * @author Alberto Chaparro
 */
public class UpgradePortletId extends BaseUpgradePortletId {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select id_ from Portlet where portletId = '176'");
			ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				removeDuplicatePortletPreferences();
				removeDuplicateResourcePermissions();

				runSQL(
					"delete from Portlet where portletId = '" +
						LicenseManagerPortletKeys.LICENSE_MANAGER + "'");

				super.doUpgrade();
			}
		}
	}

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{"176", LicenseManagerPortletKeys.LICENSE_MANAGER}
		};
	}

	protected void removeDuplicatePortletPreferences() throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(
				"select ownerId, ownerType, plid from PortletPreferences " +
					"where portletId = '176'");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long ownerId = rs.getLong(1);
				int ownerType = rs.getInt(2);
				long plid = rs.getLong(3);

				try (PreparedStatement psDelete = connection.prepareStatement(
						"delete from PortletPreferences where ownerId = ? " +
							"and ownerType = ? and plid = ? and portletId = " +
								"?")) {

					psDelete.setLong(1, ownerId);
					psDelete.setInt(2, ownerType);
					psDelete.setLong(3, plid);
					psDelete.setString(
						4, LicenseManagerPortletKeys.LICENSE_MANAGER);

					psDelete.executeUpdate();
				}
			}
		}
	}

	protected void removeDuplicateResourcePermissions() throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(
				"select companyId, scope, primKey, roleId from " +
					"ResourcePermission where name = '176'");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long companyId = rs.getLong(1);
				int scope = rs.getInt(2);
				String primKey = rs.getString(3);
				long roleId = rs.getLong(4);

				try (PreparedStatement psDelete = connection.prepareStatement(
						"delete from ResourcePermission where companyId = ? " +
							"and name = ? and scope = ? and primkey = ? and " +
								"roleId = ?")) {

					psDelete.setLong(1, companyId);
					psDelete.setString(
						2, LicenseManagerPortletKeys.LICENSE_MANAGER);
					psDelete.setInt(3, scope);
					psDelete.setString(4, primKey);
					psDelete.setLong(5, roleId);

					psDelete.executeUpdate();
				}
			}
		}
	}

}