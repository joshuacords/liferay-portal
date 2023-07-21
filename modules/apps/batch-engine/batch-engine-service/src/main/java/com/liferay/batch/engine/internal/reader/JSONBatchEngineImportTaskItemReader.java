/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.reader;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class JSONBatchEngineImportTaskItemReader
	implements BatchEngineImportTaskItemReader {

	public JSONBatchEngineImportTaskItemReader(InputStream inputStream)
		throws IOException {

		_inputStream = inputStream;

		_jsonParser = _jsonFactory.createParser(_inputStream);

		_jsonParser.nextToken();
	}

	@Override
	public void close() throws IOException {
		_inputStream.close();
		_jsonParser.close();
	}

	@Override
	public Map<String, Object> read() throws Exception {
		if (_jsonParser.nextToken() == JsonToken.START_OBJECT) {
			return _objectMapper.readValue(
				_jsonParser,
				new TypeReference<Map<String, Object>>() {
				});
		}

		return null;
	}

	private static final JsonFactory _jsonFactory = new JsonFactory();
	private static final ObjectMapper _objectMapper = new ObjectMapper();

	private final InputStream _inputStream;
	private final JsonParser _jsonParser;

}