/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.petra.io.unsync.UnsyncBufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class JSONLBatchEngineImportTaskItemReader
	implements BatchEngineImportTaskItemReader {

	public JSONLBatchEngineImportTaskItemReader(InputStream inputStream) {
		_inputStream = inputStream;

		_unsyncBufferedReader = new UnsyncBufferedReader(
			new InputStreamReader(_inputStream));
	}

	@Override
	public void close() throws IOException {
		_unsyncBufferedReader.close();
	}

	@Override
	public Map<String, Object> read() throws Exception {
		String line = _unsyncBufferedReader.readLine();

		if (line == null) {
			return null;
		}

		return _objectMapper.readValue(
			line,
			new TypeReference<Map<String, Object>>() {
			});
	}

	private static final ObjectMapper _objectMapper = new ObjectMapper();

	private final InputStream _inputStream;
	private final UnsyncBufferedReader _unsyncBufferedReader;

}