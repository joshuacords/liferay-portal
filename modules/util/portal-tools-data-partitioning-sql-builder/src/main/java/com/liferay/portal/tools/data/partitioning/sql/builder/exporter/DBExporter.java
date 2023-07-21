/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;

import java.io.OutputStream;

import java.util.List;

/**
 * @author Manuel de la Peña
 */
public interface DBExporter {

	public List<String> getControlTableNames(ExportContext exportContext);

	public String getOutputFileExtension();

	public List<String> getPartitionedTableNames(ExportContext exportContext);

	public void write(
		long companyId, String tableName, OutputStream outputStream);

	public void write(String tableName, OutputStream outputStream);

	public void writeDelete(
		long companyId, String tableName, OutputStream outputStream);

}