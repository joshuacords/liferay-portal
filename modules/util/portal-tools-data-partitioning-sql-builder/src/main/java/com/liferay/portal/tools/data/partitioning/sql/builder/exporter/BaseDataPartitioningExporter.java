/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.exporter.ExportProcess;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.exporter.SQLBuilder;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Manuel de la Peña
 */
public abstract class BaseDataPartitioningExporter
	implements DataPartitioningExporter, DBExporter, DBProvider {

	public BaseDataPartitioningExporter() {
		_sqlBuilder = new InsertSQLBuilder();
	}

	public BaseDataPartitioningExporter(SQLBuilder sqlBuilder) {
		_sqlBuilder = sqlBuilder;
	}

	@Override
	public void export(ExportContext exportContext) {
		initializeDatabase(exportContext.getProperties());

		ExportProcess exportProcess = new ExportProcess(this);

		try {
			exportProcess.export(exportContext);
		}
		catch (IOException ioException) {
			_logger.error("Unable to export", ioException);
		}
	}

	@Override
	public List<String> getControlTableNames(ExportContext exportContext) {
		return getTableNames(getControlTableNamesSQL(exportContext));
	}

	@Override
	public DataSource getDataSource() {
		return _dataSource;
	}

	@Override
	public int getFetchSize() {
		return 0;
	}

	@Override
	public String getOutputFileExtension() {
		return ".sql";
	}

	@Override
	public List<String> getPartitionedTableNames(ExportContext exportContext) {
		return getTableNames(getPartitionedTableNamesSQL(exportContext));
	}

	@Override
	public void write(
		long companyId, String tableName, OutputStream outputStream) {

		DataSource dataSource = getDataSource();

		String sql = "select * from " + tableName;

		if (companyId > 0) {
			sql += " where companyId = ?";
		}

		try (Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = buildPreparedStatement(
				connection, sql, companyId);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			int columnCount = resultSetMetaData.getColumnCount();

			while (resultSet.next()) {
				String[] fields = new String[columnCount];

				for (int i = 0; i < columnCount; i++) {
					fields[i] = _sqlBuilder.buildField(
						resultSet.getObject(i + 1));
				}

				String insertSQL = _sqlBuilder.buildInsert(tableName, fields);

				outputStream.write(insertSQL.getBytes());
			}
		}
		catch (IOException | SQLException exception) {
			_logger.error(
				"Unable to generate insert SQL statements for table " +
					tableName,
				exception);
		}
	}

	@Override
	public void write(String tableName, OutputStream outputStream) {
		write(0, tableName, outputStream);
	}

	@Override
	public void writeDelete(
		long companyId, String tableName, OutputStream outputStream) {

		DataSource dataSource = getDataSource();

		try (Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = buildPreparedStatement(
				connection,
				"select count(1) from " + tableName + " where companyId = ?",
				companyId);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next() && (resultSet.getInt(1) > 0)) {
				String deleteSQL =
					"delete from " + tableName + " where companyId = " +
						companyId + ";\n";

				outputStream.write(deleteSQL.getBytes());
			}
		}
		catch (IOException | SQLException exception) {
			_logger.error(
				"Unable to generate delete SQL statements for table " +
					tableName,
				exception);
		}
	}

	protected PreparedStatement buildPreparedStatement(
			Connection connection, String sql, long companyId)
		throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(
			sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

		preparedStatement.setFetchSize(getFetchSize());

		if (companyId > 0) {
			preparedStatement.setLong(1, companyId);
		}

		return preparedStatement;
	}

	protected abstract String getControlTableNamesSQL(
		ExportContext exportContext);

	protected abstract String getPartitionedTableNamesSQL(
		ExportContext exportContext);

	protected List<String> getTableNames(String sql) {
		List<String> tableNames = new ArrayList<>();

		DataSource dataSource = getDataSource();

		try (Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sql);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				tableNames.add(resultSet.getString(getTableNameFieldName()));
			}
		}
		catch (SQLException sqlException) {
			_logger.error(
				"Unable to get table names using SQL query: " + sql,
				sqlException);
		}

		return tableNames;
	}

	protected void initializeDatabase(Properties properties) {
		HikariConfig hikariConfig = new HikariConfig(properties);

		_dataSource = new HikariDataSource(hikariConfig);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		BaseDataPartitioningExporter.class);

	private DataSource _dataSource;
	private final SQLBuilder _sqlBuilder;

}