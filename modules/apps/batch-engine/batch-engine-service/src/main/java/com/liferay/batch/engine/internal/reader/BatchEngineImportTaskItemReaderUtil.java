/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.reader;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class BatchEngineImportTaskItemReaderUtil {

	public static <T> T convertValue(
			Class<T> itemClass, Map<String, Object> fieldNameValueMap)
		throws ReflectiveOperationException {

		T item = itemClass.newInstance();

		for (Map.Entry<String, Object> entry : fieldNameValueMap.entrySet()) {
			String name = entry.getKey();

			Field field = null;

			try {
				field = itemClass.getDeclaredField(name);
			}
			catch (NoSuchFieldException noSuchFieldException) {
				field = itemClass.getDeclaredField(StringPool.UNDERLINE + name);
			}

			field.setAccessible(true);

			field.set(
				item,
				_objectMapper.convertValue(entry.getValue(), field.getType()));
		}

		return item;
	}

	public static void handleMapField(
		String fieldName, Map<String, Object> fieldNameValueMap,
		int lastDelimiterIndex, String value) {

		String key = fieldName.substring(lastDelimiterIndex + 1);

		fieldName = fieldName.substring(0, lastDelimiterIndex);

		Map<String, String> valueMap =
			(Map<String, String>)fieldNameValueMap.get(fieldName);

		if (valueMap == null) {
			valueMap = new HashMap<>();

			fieldNameValueMap.put(fieldName, valueMap);
		}

		valueMap.put(key, value);
	}

	public static Map<String, Object> mapFieldNames(
		Map<String, ? extends Serializable> fieldNameMappingMap,
		Map<String, Object> fieldNameValueMap) {

		if ((fieldNameMappingMap == null) || fieldNameMappingMap.isEmpty()) {
			return fieldNameValueMap;
		}

		Map<String, Object> targetFieldNameValueMap = new HashMap<>();

		for (Map.Entry<String, Object> entry : fieldNameValueMap.entrySet()) {
			String targetFieldName = (String)fieldNameMappingMap.get(
				entry.getKey());

			if (Validator.isNotNull(targetFieldName)) {
				targetFieldNameValueMap.put(targetFieldName, entry.getValue());
			}
		}

		return targetFieldNameValueMap;
	}

	private static final ObjectMapper _objectMapper = new ObjectMapper();

}