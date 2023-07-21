/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.writer;

import com.liferay.batch.engine.BatchEngineTaskContentType;

import java.io.OutputStream;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class BatchEngineExportTaskItemWriterFactory {

	public BatchEngineExportTaskItemWriterFactory(
		String csvFileColumnDelimiter) {

		_csvFileColumnDelimiter = csvFileColumnDelimiter;
	}

	public BatchEngineExportTaskItemWriter create(
			BatchEngineTaskContentType batchEngineTaskContentType,
			List<String> fieldNames, Class<?> itemClass,
			OutputStream outputStream)
		throws Exception {

		Map<String, Field> fieldMap = ItemClassIndexUtil.index(itemClass);

		if (batchEngineTaskContentType == BatchEngineTaskContentType.CSV) {
			return new CSVBatchEngineExportTaskItemWriter(
				_csvFileColumnDelimiter, fieldMap, fieldNames, outputStream);
		}

		if (batchEngineTaskContentType == BatchEngineTaskContentType.JSON) {
			return new JSONBatchEngineExportTaskItemWriter(
				fieldMap.keySet(), fieldNames, outputStream);
		}

		if (batchEngineTaskContentType == BatchEngineTaskContentType.JSONL) {
			return new JSONLBatchEngineExportTaskItemWriter(
				fieldMap.keySet(), fieldNames, outputStream);
		}

		if ((batchEngineTaskContentType == BatchEngineTaskContentType.XLS) ||
			(batchEngineTaskContentType == BatchEngineTaskContentType.XLSX)) {

			return new XLSBatchEngineExportTaskItemWriter(
				fieldMap, fieldNames, outputStream);
		}

		throw new IllegalArgumentException(
			"Unknown batch engine task content type " +
				batchEngineTaskContentType);
	}

	private final String _csvFileColumnDelimiter;

}