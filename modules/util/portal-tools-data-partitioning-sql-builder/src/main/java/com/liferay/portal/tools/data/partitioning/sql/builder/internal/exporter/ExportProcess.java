/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.internal.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.DBExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.command.DeleteExportProcessCommand;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.command.ExportProcessCommand;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.command.InsertControlExportProcessCommand;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.command.InsertPartitionedExportProcessCommand;

import java.io.IOException;

import java.util.List;

/**
 * @author Manuel de la Peña
 */
public class ExportProcess {

	public ExportProcess(DBExporter dbExporter) {
		_dbExporter = dbExporter;
	}

	public void export(ExportContext exportContext) throws IOException {
		List<String> partitionedTableNames =
			_dbExporter.getPartitionedTableNames(exportContext);
		List<String> controlTableNames = _dbExporter.getControlTableNames(
			exportContext);

		List<Long> companyIds = exportContext.getCompanyIds();

		for (Long companyId : companyIds) {
			ExportProcessCommand deleteExportProcessCommand =
				new DeleteExportProcessCommand(
					companyId, _dbExporter, partitionedTableNames,
					exportContext);

			deleteExportProcessCommand.export();

			ExportProcessCommand insertPartitionedExportProcessCommand =
				new InsertPartitionedExportProcessCommand(
					companyId, _dbExporter, partitionedTableNames,
					exportContext);

			insertPartitionedExportProcessCommand.export();

			ExportProcessCommand insertControlExportProcessCommand =
				new InsertControlExportProcessCommand(
					companyId, _dbExporter, controlTableNames, exportContext);

			insertControlExportProcessCommand.export();
		}
	}

	private final DBExporter _dbExporter;

}