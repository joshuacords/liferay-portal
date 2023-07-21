/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.dto.v1_0.util;

import com.liferay.data.engine.rest.dto.v1_0.DataDefinition;
import com.liferay.data.engine.rest.dto.v1_0.DataDefinitionField;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jeyvison Nascimento
 */
public class DataRecordValuesUtil {

	public static Map<String, Object> toDataRecordValues(
			DataDefinition dataDefinition, String json)
		throws Exception {

		Map<String, Object> dataRecordValues = new HashMap<>();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

		for (DataDefinitionField dataDefinitionField :
				dataDefinition.getDataDefinitionFields()) {

			if (!jsonObject.has(dataDefinitionField.getName())) {
				continue;
			}

			dataRecordValues.put(
				dataDefinitionField.getName(),
				_toDataRecordValue(dataDefinitionField, jsonObject));
		}

		return dataRecordValues;
	}

	public static String toJSON(
		DataDefinition dataDefinition, Map<String, ?> dataRecordValues) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Map<String, DataDefinitionField> dataDefinitionFields = Stream.of(
			dataDefinition.getDataDefinitionFields()
		).collect(
			Collectors.toMap(
				dataDefinitionField -> dataDefinitionField.getName(),
				Function.identity())
		);

		for (Map.Entry<String, DataDefinitionField> entry :
				dataDefinitionFields.entrySet()) {

			if (!dataRecordValues.containsKey(entry.getKey())) {
				continue;
			}

			DataDefinitionField dataDefinitionField = entry.getValue();

			if (dataDefinitionField.getRepeatable()) {
				jsonObject.put(
					entry.getKey(),
					JSONFactoryUtil.createJSONArray(
						(Object[])dataRecordValues.get(entry.getKey())));
			}
			else {
				jsonObject.put(
					entry.getKey(), dataRecordValues.get(entry.getKey()));
			}
		}

		return jsonObject.toString();
	}

	private static Object _toDataRecordValue(
		DataDefinitionField dataDefinitionField, JSONObject jsonObject) {

		if (dataDefinitionField.getRepeatable()) {
			return JSONUtil.toObjectArray(
				jsonObject.getJSONArray(dataDefinitionField.getName()));
		}

		return jsonObject.get(dataDefinitionField.getName());
	}

}