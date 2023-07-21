/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.db;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Adolfo Pérez
 */
public class DBInspector {

	public DBInspector(Connection connection) {
		_connection = connection;
	}

	public String getCatalog() throws SQLException {
		return _connection.getCatalog();
	}

	public String getSchema() {
		try {
			return _connection.getSchema();
		}
		catch (Throwable t) {
			if (_log.isDebugEnabled()) {
				_log.debug(t, t);
			}

			return null;
		}
	}

	public boolean hasColumn(String tableName, String columnName)
		throws Exception {

		DatabaseMetaData databaseMetaData = _connection.getMetaData();

		try (ResultSet rs = databaseMetaData.getColumns(
				getCatalog(), getSchema(), normalizeName(tableName),
				normalizeName(columnName))) {

			if (!rs.next()) {
				return false;
			}

			return true;
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return false;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #hasColumnType(String, String, String)}
	 */
	@Deprecated
	public boolean hasColumnType(
			Class<?> tableClass, String columnName, String columnType)
		throws Exception {

		Field tableNameField = tableClass.getField("TABLE_NAME");

		return hasColumnType(
			(String)tableNameField.get(null), columnName, columnType);
	}

	public boolean hasColumnType(
			String tableName, String columnName, String columnType)
		throws Exception {

		DatabaseMetaData databaseMetaData = _connection.getMetaData();

		try (ResultSet rs = databaseMetaData.getColumns(
				getCatalog(), getSchema(),
				normalizeName(tableName, databaseMetaData),
				normalizeName(columnName, databaseMetaData))) {

			if (!rs.next()) {
				return false;
			}

			int expectedColumnSize = _getColumnSize(columnType);

			int actualColumnSize = rs.getInt("COLUMN_SIZE");

			if ((expectedColumnSize != -1) &&
				(expectedColumnSize != actualColumnSize)) {

				return false;
			}

			Integer expectedColumnDataType = _getColumnDataType(columnType);

			int actualColumnDataType = rs.getInt("DATA_TYPE");

			if ((expectedColumnDataType == null) ||
				(expectedColumnDataType != actualColumnDataType)) {

				return false;
			}

			boolean expectedColumnNullable = _isColumnNullable(columnType);

			int actualColumnNullable = rs.getInt("NULLABLE");

			if ((expectedColumnNullable &&
				 (actualColumnNullable != DatabaseMetaData.columnNullable)) ||
				(!expectedColumnNullable &&
				 (actualColumnNullable != DatabaseMetaData.columnNoNulls))) {

				return false;
			}

			return true;
		}
	}

	public boolean hasIndex(String tableName, String indexName)
		throws Exception {

		DB db = DBManagerUtil.getDB();
		DatabaseMetaData databaseMetaData = _connection.getMetaData();

		try (ResultSet rs = db.getIndexResultSet(
				_connection, normalizeName(tableName, databaseMetaData))) {

			while (rs.next()) {
				if (Objects.equals(indexName, rs.getString("index_name"))) {
					return true;
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return false;
	}

	public boolean hasRows(String tableName) {
		try (PreparedStatement ps = _connection.prepareStatement(
				"select count(*) from " + tableName);
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				int count = rs.getInt(1);

				if (count > 0) {
					return true;
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return false;
	}

	public boolean hasTable(String tableName) throws Exception {
		return hasTable(tableName, false);
	}

	public boolean hasTable(String tableName, boolean caseSensitive)
		throws Exception {

		if (caseSensitive) {
			if (_hasTable(tableName)) {
				return true;
			}

			return false;
		}

		DatabaseMetaData databaseMetaData = _connection.getMetaData();

		if (_hasTable(normalizeName(tableName, databaseMetaData))) {
			return true;
		}

		return false;
	}

	public String normalizeName(String name) throws SQLException {
		return normalizeName(name, _connection.getMetaData());
	}

	public String normalizeName(String name, DatabaseMetaData databaseMetaData)
		throws SQLException {

		if (databaseMetaData.storesLowerCaseIdentifiers()) {
			return StringUtil.toLowerCase(name);
		}

		if (databaseMetaData.storesUpperCaseIdentifiers()) {
			return StringUtil.toUpperCase(name);
		}

		return name;
	}

	private Integer _getColumnDataType(String columnType) {
		Matcher matcher = _columnTypePattern.matcher(columnType);

		if (!matcher.lookingAt()) {
			return null;
		}

		DB db = DBManagerUtil.getDB();

		return db.getSQLType(matcher.group(1));
	}

	private int _getColumnSize(String columnType) throws UpgradeException {
		Matcher matcher = _columnSizePattern.matcher(columnType);

		if (!matcher.matches()) {
			return -1;
		}

		String columnSize = matcher.group(1);

		if (Validator.isNotNull(columnSize)) {
			try {
				return Integer.parseInt(columnSize);
			}
			catch (NumberFormatException numberFormatException) {
				throw new UpgradeException(
					StringBundler.concat(
						"Column type ", columnType,
						" has an invalid column size ", columnSize),
					numberFormatException);
			}
		}

		return -1;
	}

	private boolean _hasTable(String tableName) throws Exception {
		DatabaseMetaData metadata = _connection.getMetaData();

		try (ResultSet rs = metadata.getTables(
				getCatalog(), getSchema(), tableName, null)) {

			while (rs.next()) {
				return true;
			}
		}

		return false;
	}

	private boolean _isColumnNullable(String typeName) {
		typeName = typeName.trim();

		int i = typeName.indexOf("null");

		if (i == -1) {
			return false;
		}

		if ((i > 0) && !Character.isSpaceChar(typeName.charAt(i - 1))) {
			return false;
		}

		if ((i + 4) < typeName.length()) {
			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(DBInspector.class);

	private static final Pattern _columnSizePattern = Pattern.compile(
		"^\\w+(?:\\((\\d+)\\))?.*", Pattern.CASE_INSENSITIVE);
	private static final Pattern _columnTypePattern = Pattern.compile(
		"(^\\w+)", Pattern.CASE_INSENSITIVE);

	private final Connection _connection;

}