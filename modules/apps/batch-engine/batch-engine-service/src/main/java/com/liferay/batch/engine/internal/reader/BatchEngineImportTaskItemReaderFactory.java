/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.reader;

import com.liferay.batch.engine.BatchEngineTaskContentType;

import java.io.IOException;
import java.io.InputStream;

import java.util.zip.ZipInputStream;

/**
 * @author Shuyang Zhou
 * @author Ivica Cardic
 */
public class BatchEngineImportTaskItemReaderFactory {

	public BatchEngineImportTaskItemReaderFactory(
		String csvFileColumnDelimiter) {

		_csvFileColumnDelimiter = csvFileColumnDelimiter;
	}

	public BatchEngineImportTaskItemReader create(
			BatchEngineTaskContentType batchEngineTaskContentType,
			InputStream inputStream)
		throws Exception {

		inputStream = _getZipInputStream(inputStream);

		if (batchEngineTaskContentType == BatchEngineTaskContentType.CSV) {
			return new CSVBatchEngineImportTaskItemReader(
				_csvFileColumnDelimiter, inputStream);
		}

		if (batchEngineTaskContentType == BatchEngineTaskContentType.JSON) {
			return new JSONBatchEngineImportTaskItemReader(inputStream);
		}

		if (batchEngineTaskContentType == BatchEngineTaskContentType.JSONL) {
			return new JSONLBatchEngineImportTaskItemReader(inputStream);
		}

		if ((batchEngineTaskContentType == BatchEngineTaskContentType.XLS) ||
			(batchEngineTaskContentType == BatchEngineTaskContentType.XLSX)) {

			return new XLSBatchEngineImportTaskItemReader(inputStream);
		}

		throw new IllegalArgumentException(
			"Unknown batch engine task content type " +
				batchEngineTaskContentType);
	}

	private InputStream _getZipInputStream(InputStream inputStream)
		throws IOException {

		ZipInputStream zipInputStream = new ZipInputStream(inputStream);

		zipInputStream.getNextEntry();

		return zipInputStream;
	}

	private final String _csvFileColumnDelimiter;

}