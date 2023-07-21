/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.exception.DBProviderNotAvailableException;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Manuel de la Peña
 */
public class DataPartitioningExporterFactory {

	public static DataPartitioningExporter getDataPartitioningExporter() {
		ServiceLoader<DataPartitioningExporter> serviceLoader =
			ServiceLoader.load(DataPartitioningExporter.class);

		List<DataPartitioningExporter> dataPartitioningExporters =
			new ArrayList<>();

		for (DataPartitioningExporter dataPartitioningExporter :
				serviceLoader) {

			dataPartitioningExporters.add(dataPartitioningExporter);
		}

		_logger.info(
			dataPartitioningExporters.size() +
				" data partitioning exporters available");

		for (DataPartitioningExporter dataPartitioningExporter :
				dataPartitioningExporters) {

			_logger.info(
				"Data partitioning exporter " + dataPartitioningExporter);
		}

		if (dataPartitioningExporters.isEmpty() ||
			(dataPartitioningExporters.size() > 1)) {

			throw new DBProviderNotAvailableException(
				dataPartitioningExporters.size() +
					" data partitioning exporters available");
		}

		return dataPartitioningExporters.get(0);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		DataPartitioningExporterFactory.class);

}