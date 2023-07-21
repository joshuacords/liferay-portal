/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.writer;

import com.liferay.petra.io.unsync.UnsyncPrintWriter;
import com.liferay.petra.string.StringUtil;

import java.io.IOException;
import java.io.OutputStream;

import java.lang.reflect.Field;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class CSVBatchEngineExportTaskItemWriter
	implements BatchEngineExportTaskItemWriter {

	public CSVBatchEngineExportTaskItemWriter(
		String delimiter, Map<String, Field> fieldMap, List<String> fieldNames,
		OutputStream outputStream) {

		if (fieldNames.isEmpty()) {
			throw new IllegalArgumentException("Field names are not set");
		}

		_delimiter = delimiter;

		_columnValuesExtractor = new ColumnValuesExtractor(
			fieldMap, fieldNames);

		_unsyncPrintWriter = new UnsyncPrintWriter(outputStream);

		_unsyncPrintWriter.println(StringUtil.merge(fieldNames, delimiter));
	}

	@Override
	public void close() throws IOException {
		_unsyncPrintWriter.close();
	}

	@Override
	public void write(Collection<?> items) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

		for (Object item : items) {
			_write(dateFormat, _columnValuesExtractor.extractValues(item));
		}
	}

	private void _write(DateFormat dateFormat, Collection<?> values) {
		_unsyncPrintWriter.println(
			StringUtil.merge(
				values,
				value -> {
					if (value instanceof Date) {
						return dateFormat.format(value);
					}

					return String.valueOf(value);
				},
				_delimiter));
	}

	private final ColumnValuesExtractor _columnValuesExtractor;
	private final String _delimiter;
	private final UnsyncPrintWriter _unsyncPrintWriter;

}