/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upgrade;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBInspector;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Preston Crary
 */
public abstract class BaseUpgradeDBColumnSize extends UpgradeProcess {

	public BaseUpgradeDBColumnSize(
		DBType dbType, String oldColumnType, int size) {

		_dbType = dbType;
		_oldColumnType = oldColumnType;
		_size = size;
	}

	@Override
	protected void doUpgrade() throws Exception {
		DB db = DBManagerUtil.getDB();

		if (db.getDBType() == _dbType) {
			_upgradeTables();
		}
	}

	protected abstract void upgradeColumn(String tableName, String columnName)
		throws Exception;

	private void _upgradeTables() throws Exception {
		DatabaseMetaData databaseMetaData = connection.getMetaData();

		DBInspector dbInspector = new DBInspector(connection);

		String catalog = dbInspector.getCatalog();
		String schema = dbInspector.getSchema();

		try (LoggingTimer loggingTimer = new LoggingTimer();
			ResultSet tableRS = databaseMetaData.getTables(
				catalog, schema, null, new String[] {"TABLE"})) {

			while (tableRS.next()) {
				String tableName = dbInspector.normalizeName(
					tableRS.getString("TABLE_NAME"));

				Set<String> invalidColumnNames = new HashSet<>();

				try (ResultSet primaryKeyRS = databaseMetaData.getPrimaryKeys(
						catalog, schema, tableName)) {

					while (primaryKeyRS.next()) {
						String primaryKeyName = StringUtil.toUpperCase(
							primaryKeyRS.getString("COLUMN_NAME"));

						invalidColumnNames.add(primaryKeyName);
					}
				}

				DB db = DBManagerUtil.getDB();

				try (ResultSet indexRS = db.getIndexResultSet(
						connection, tableName)) {

					while (indexRS.next()) {
						invalidColumnNames.add(
							StringUtil.toUpperCase(
								indexRS.getString("COLUMN_NAME")));
					}
				}

				try (ResultSet columnRS = databaseMetaData.getColumns(
						catalog, schema, tableName, null)) {

					while (columnRS.next()) {
						int size = columnRS.getInt("COLUMN_SIZE");

						if ((size == _size) &&
							StringUtil.equalsIgnoreCase(
								_oldColumnType,
								columnRS.getString("TYPE_NAME"))) {

							String columnName = columnRS.getString(
								"COLUMN_NAME");

							if (invalidColumnNames.contains(
									StringUtil.toUpperCase(columnName))) {

								continue;
							}

							try {
								upgradeColumn(tableName, columnName);
							}
							catch (SQLException sqlException) {
								if (_log.isWarnEnabled()) {
									_log.warn(
										StringBundler.concat(
											"Unable to alter length of column ",
											columnName, " for table ",
											tableName),
										sqlException);
								}
							}
						}
					}
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseUpgradeDBColumnSize.class);

	private final DBType _dbType;
	private final String _oldColumnType;
	private final int _size;

}