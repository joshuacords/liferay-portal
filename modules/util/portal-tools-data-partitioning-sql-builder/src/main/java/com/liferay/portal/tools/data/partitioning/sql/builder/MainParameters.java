/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder;

import com.beust.jcommander.Parameter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.util.PropsReader;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.validators.CompanyIdsRequiredParameterValidator;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.validators.FileRequiredParameterValidator;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.validators.RequiredParameterValidator;
import com.liferay.portal.tools.data.partitioning.sql.builder.internal.validators.WritableFileRequiredParameterValidator;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel de la Peña
 */
public class MainParameters {

	public String getCatalogName() {
		return _catalogName;
	}

	public String getCompanyIds() {
		return _companyIds;
	}

	public String getOutputDirName() {
		return _outputDirName;
	}

	public String getPropertiesFileName() {
		return _propertiesFileName;
	}

	public String getSchemaName() {
		return _schemaName;
	}

	public boolean isWriteFile() {
		return _writeFile;
	}

	public ExportContext toExportContext() throws IOException {
		if ((_catalogName == null) || _catalogName.isEmpty()) {
			return new ExportContext(
				_getCompanyIds(), _outputDirName,
				PropsReader.read(getPropertiesFileName()), _schemaName,
				_writeFile);
		}

		return new ExportContext(
			_catalogName, _getCompanyIds(), _outputDirName,
			PropsReader.read(getPropertiesFileName()), _schemaName, _writeFile);
	}

	private List<Long> _getCompanyIds() {
		List<Long> companyIds = new ArrayList<>();

		for (String companyId : _companyIds.split(",")) {
			companyIds.add(Long.parseLong(companyId));
		}

		return companyIds;
	}

	@Parameter(
		description = "Catalog name to be exported",
		names = {"-K", "--catalog-name"}
	)
	private String _catalogName;

	@Parameter(
		description = "Comma-separated list of company IDs to be exported",
		names = {"-C", "--company-ids"},
		validateWith = CompanyIdsRequiredParameterValidator.class
	)
	private String _companyIds;

	@Parameter(
		description = "Output directory", names = {"-O", "--output-dir"},
		validateWith = WritableFileRequiredParameterValidator.class
	)
	private String _outputDirName;

	@Parameter(
		description = "Properties file with database configuration",
		names = {"-P", "--properties-file"},
		validateWith = FileRequiredParameterValidator.class
	)
	private String _propertiesFileName;

	@Parameter(
		description = "Schema name to be exported",
		names = {"-S", "--schema-name"},
		validateWith = RequiredParameterValidator.class
	)
	private String _schemaName;

	@Parameter(
		description = "Whether to write tables to separate SQL files",
		names = {"-W", "--write-file"}
	)
	private boolean _writeFile;

}