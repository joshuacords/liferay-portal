/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.db;

import com.liferay.portal.kernel.util.LoggingTimer;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

/**
 * @author Hugo Huijser
 * @author Brian Wing Shun Chan
 */
public abstract class BaseDBProcess implements DBProcess {

	public BaseDBProcess() {
	}

	@Override
	public void runSQL(Connection connection, String template)
		throws IOException, SQLException {

		DB db = DBManagerUtil.getDB();

		db.runSQL(connection, template);
	}

	@Override
	public void runSQL(DBTypeToSQLMap dbTypeToSQLMap)
		throws IOException, SQLException {

		DB db = DBManagerUtil.getDB();

		if (connection == null) {
			db.runSQL(dbTypeToSQLMap);
		}
		else {
			db.runSQL(connection, dbTypeToSQLMap);
		}
	}

	@Override
	public void runSQL(String template) throws IOException, SQLException {
		DB db = DBManagerUtil.getDB();

		if (connection == null) {
			db.runSQL(template);
		}
		else {
			db.runSQL(connection, template);
		}
	}

	@Override
	public void runSQL(String[] templates) throws IOException, SQLException {
		DB db = DBManagerUtil.getDB();

		if (connection == null) {
			db.runSQL(templates);
		}
		else {
			db.runSQL(connection, templates);
		}
	}

	@Override
	public void runSQLTemplate(String path)
		throws IOException, NamingException, SQLException {

		try (LoggingTimer loggingTimer = new LoggingTimer(path)) {
			DB db = DBManagerUtil.getDB();

			db.runSQLTemplate(path);
		}
	}

	@Override
	public void runSQLTemplate(String path, boolean failOnError)
		throws IOException, NamingException, SQLException {

		try (LoggingTimer loggingTimer = new LoggingTimer(path)) {
			DB db = DBManagerUtil.getDB();

			db.runSQLTemplate(path, failOnError);
		}
	}

	@Override
	public void runSQLTemplateString(
			String template, boolean evaluate, boolean failOnError)
		throws IOException, NamingException, SQLException {

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			DB db = DBManagerUtil.getDB();

			if (connection == null) {
				db.runSQLTemplateString(template, evaluate, failOnError);
			}
			else {
				db.runSQLTemplateString(
					connection, template, evaluate, failOnError);
			}
		}
	}

	protected boolean doHasTable(String tableName) throws Exception {
		DBInspector dbInspector = new DBInspector(connection);

		return dbInspector.hasTable(tableName, true);
	}

	protected boolean hasColumn(String tableName, String columnName)
		throws Exception {

		DBInspector dbInspector = new DBInspector(connection);

		return dbInspector.hasColumn(tableName, columnName);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #hasColumnType(String, String, String)}
	 */
	@Deprecated
	protected boolean hasColumnType(
			Class<?> tableClass, String columnName, String columnType)
		throws Exception {

		DBInspector dbInspector = new DBInspector(connection);

		return dbInspector.hasColumnType(tableClass, columnName, columnType);
	}

	protected boolean hasColumnType(
			String tableName, String columnName, String columnType)
		throws Exception {

		DBInspector dbInspector = new DBInspector(connection);

		return dbInspector.hasColumnType(tableName, columnName, columnType);
	}

	protected boolean hasIndex(String tableName, String indexName)
		throws Exception {

		DBInspector dbInspector = new DBInspector(connection);

		return dbInspector.hasIndex(tableName, indexName);
	}

	protected boolean hasRows(Connection connection, String tableName) {
		DBInspector dbInspector = new DBInspector(connection);

		return dbInspector.hasRows(tableName);
	}

	protected boolean hasRows(String tableName) throws Exception {
		return hasRows(connection, tableName);
	}

	protected boolean hasTable(String tableName) throws Exception {
		DBInspector dbInspector = new DBInspector(connection);

		return dbInspector.hasTable(tableName);
	}

	protected Connection connection;

}