/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.helper;

import com.liferay.dynamic.data.mapping.form.evaluator.DDMFormEvaluatorFieldContextKey;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Rafael Praxedes
 */
public class DDMFormEvaluatorFormValuesHelper {

	public DDMFormEvaluatorFormValuesHelper(DDMFormValues ddmFormValues) {
		createDDMFormFieldValuesMap(ddmFormValues);
	}

	public Set<DDMFormEvaluatorFieldContextKey> getDDMFormFieldContextKeySet(
		String fieldName) {

		Map<DDMFormEvaluatorFieldContextKey, DDMFormFieldValue> map =
			_ddmFormFieldValuesMap.getOrDefault(
				fieldName, Collections.emptyMap());

		return map.keySet();
	}

	public DDMFormFieldValue getDDMFormFieldValue(
		DDMFormEvaluatorFieldContextKey fieldContextKey) {

		Map<DDMFormEvaluatorFieldContextKey, DDMFormFieldValue> map =
			_ddmFormFieldValuesMap.getOrDefault(
				fieldContextKey.getName(), Collections.emptyMap());

		return map.get(fieldContextKey);
	}

	protected void createDDMFormFieldValuesMap(DDMFormValues ddmFormValues) {
		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		for (DDMFormFieldValue ddmFormFieldValue : ddmFormFieldValues) {
			populateDDMFormFieldValues(ddmFormFieldValue);
		}
	}

	protected void populateDDMFormFieldValues(
		DDMFormFieldValue ddmFormFieldValue) {

		Map<DDMFormEvaluatorFieldContextKey, DDMFormFieldValue>
			ddmFormFieldValuesMap = _ddmFormFieldValuesMap.get(
				ddmFormFieldValue.getName());

		if (ddmFormFieldValuesMap == null) {
			ddmFormFieldValuesMap = new HashMap<>();

			_ddmFormFieldValuesMap.put(
				ddmFormFieldValue.getName(), ddmFormFieldValuesMap);
		}

		ddmFormFieldValuesMap.put(
			new DDMFormEvaluatorFieldContextKey(
				ddmFormFieldValue.getName(), ddmFormFieldValue.getInstanceId()),
			ddmFormFieldValue);

		for (DDMFormFieldValue nestedDDMFormFieldValue :
				ddmFormFieldValue.getNestedDDMFormFieldValues()) {

			populateDDMFormFieldValues(nestedDDMFormFieldValue);
		}
	}

	private final Map
		<String, Map<DDMFormEvaluatorFieldContextKey, DDMFormFieldValue>>
			_ddmFormFieldValuesMap = new HashMap<>();

}