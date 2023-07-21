/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model.dao;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.model.ReleaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Adolfo Pérez
 */
public class ReleaseDAO {

	public void addRelease(Connection connection, String bundleSymbolicName)
		throws SQLException {

		if (hasRelease(connection, bundleSymbolicName)) {
			return;
		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		StringBundler sb = new StringBundler(4);

		sb.append("insert into Release_ (mvccVersion, releaseId, createDate, ");
		sb.append("modifiedDate, servletContextName, schemaVersion, ");
		sb.append("buildNumber, buildDate, verified, state_, testString) ");
		sb.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try (PreparedStatement ps = connection.prepareStatement(
				sb.toString())) {

			ps.setLong(1, 0);
			ps.setLong(2, increment());
			ps.setTimestamp(3, timestamp);
			ps.setTimestamp(4, timestamp);
			ps.setString(5, bundleSymbolicName);
			ps.setString(6, "0.0.1");
			ps.setInt(7, 001);
			ps.setTimestamp(8, timestamp);
			ps.setBoolean(9, false);
			ps.setInt(10, 0);
			ps.setString(11, ReleaseConstants.TEST_STRING);

			ps.execute();
		}
	}

	protected boolean hasRelease(
			Connection connection, String bundleSymbolicName)
		throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(
				"select * from Release_ where servletContextName = ?")) {

			ps.setString(1, bundleSymbolicName);

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}

	protected long increment() {
		DB db = DBManagerUtil.getDB();

		return db.increment();
	}

}