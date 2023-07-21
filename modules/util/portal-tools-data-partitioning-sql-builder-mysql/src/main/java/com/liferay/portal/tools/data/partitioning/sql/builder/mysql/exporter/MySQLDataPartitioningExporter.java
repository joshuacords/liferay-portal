/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.mysql.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.BaseDataPartitioningExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.InsertSQLBuilder;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.serializer.DefaultFieldSerializer;

import java.text.SimpleDateFormat;

/**
 * @author Manuel de la Peña
 */
public class MySQLDataPartitioningExporter
	extends BaseDataPartitioningExporter {

	public MySQLDataPartitioningExporter() {
		super(
			new InsertSQLBuilder(
				new DefaultFieldSerializer(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))));
	}

	@Override
	public String getControlTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder();

		sb.append("select c1.");
		sb.append(getTableNameFieldName());
		sb.append(" from information_schema.columns c1 where ");
		sb.append("c1.table_schema = '");
		sb.append(exportContext.getSchemaName());
		sb.append("' and c1.");
		sb.append(getTableNameFieldName());
		sb.append(" not in (");
		sb.append(getPartitionedTableNamesSQL(exportContext));
		sb.append(") group by c1.");
		sb.append(getTableNameFieldName());
		sb.append(" order by c1.");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public int getFetchSize() {
		return Integer.MIN_VALUE;
	}

	@Override
	public String getPartitionedTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder();

		sb.append("select c2.");
		sb.append(getTableNameFieldName());
		sb.append(" from information_schema.columns c2 where ");
		sb.append("c2.table_schema = '");
		sb.append(exportContext.getSchemaName());
		sb.append("' and c2.column_name = 'companyId' group by c2.");
		sb.append(getTableNameFieldName());
		sb.append(" order by c2.");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public String getTableNameFieldName() {
		return "table_name";
	}

}