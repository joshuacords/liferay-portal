/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context;

import java.util.List;
import java.util.Properties;

/**
 * @author Manuel de la Peña
 */
public class ExportContext {

	public ExportContext(
		List<Long> companyIds, String outputDirName, Properties properties,
		String schemaName, boolean writeFile) {

		this(
			schemaName, companyIds, outputDirName, properties, schemaName,
			writeFile);
	}

	public ExportContext(
		String catalogName, List<Long> companyIds, String outputDirName,
		Properties properties, String schemaName, boolean writeFile) {

		_catalogName = catalogName;
		_companyIds = companyIds;
		_outputDirName = outputDirName;
		_properties = properties;
		_schemaName = schemaName;
		_writeFile = writeFile;
	}

	public String getCatalogName() {
		return _catalogName;
	}

	public List<Long> getCompanyIds() {
		return _companyIds;
	}

	public String getOutputDirName() {
		return _outputDirName;
	}

	public Properties getProperties() {
		return _properties;
	}

	public String getSchemaName() {
		return _schemaName;
	}

	public boolean isWriteFile() {
		return _writeFile;
	}

	private final String _catalogName;
	private final List<Long> _companyIds;
	private final String _outputDirName;
	private final Properties _properties;
	private final String _schemaName;
	private final boolean _writeFile;

}