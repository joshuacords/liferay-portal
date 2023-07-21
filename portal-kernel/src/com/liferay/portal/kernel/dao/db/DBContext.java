/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.db;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Carlos Sierra Andrés
 */
public class DBContext {

	public Connection getDataAccess() {
		try {
			return DataAccess.getConnection();
		}
		catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

	public DBManager getDBManager() {
		return DBManagerUtil.getDBManager();
	}

	public Connection getUpgradeOptimizedConnection() {
		try {
			return DataAccess.getConnection();
		}
		catch (SQLException sqlException) {
			throw new RuntimeException(sqlException);
		}
	}

}