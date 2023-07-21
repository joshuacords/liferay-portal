/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.internal.command;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.DBExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;

import java.io.OutputStream;

import java.util.List;

/**
 * @author Manuel de la Peña
 */
public class InsertControlExportProcessCommand
	extends BaseExportProcessCommand {

	public InsertControlExportProcessCommand(
		long companyId, DBExporter dbExporter, List<String> tableNames,
		ExportContext exportContext) {

		super(companyId, dbExporter, tableNames, exportContext);
	}

	@Override
	protected String getOutputFileName() {
		return exportContext.getSchemaName() + "-" + companyId + "-control" +
			dbExporter.getOutputFileExtension();
	}

	@Override
	protected String getOutputFileName(String tableName) {
		return exportContext.getSchemaName() + "-" + companyId + "-table-" +
			tableName + dbExporter.getOutputFileExtension();
	}

	@Override
	protected void write(String tableName, OutputStream outputStream) {
		dbExporter.write(tableName, outputStream);
	}

}