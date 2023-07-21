/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder;

import com.beust.jcommander.JCommander;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.DataPartitioningExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.DataPartitioningExporterFactory;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;

/**
 * @author Manuel de la Peña
 */
public class Main {

	public static void main(String[] arguments) throws Exception {
		MainParameters mainParameters = _validate(arguments);

		DataPartitioningExporter dataPartitioningExporter =
			DataPartitioningExporterFactory.getDataPartitioningExporter();

		ExportContext exportContext = mainParameters.toExportContext();

		dataPartitioningExporter.export(exportContext);
	}

	private static MainParameters _validate(String[] arguments)
		throws Exception {

		MainParameters mainParameters = new MainParameters();

		if ((arguments == null) || (arguments.length == 0)) {
			throw new IllegalArgumentException("Arguments are null");
		}

		new JCommander(mainParameters, arguments);

		return mainParameters;
	}

}