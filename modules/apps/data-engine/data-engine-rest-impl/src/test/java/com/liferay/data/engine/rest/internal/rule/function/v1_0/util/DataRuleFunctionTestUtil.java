/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.rule.function.v1_0.util;

import com.liferay.data.engine.rest.dto.v1_0.DataDefinitionField;
import com.liferay.data.engine.rest.dto.v1_0.DataRecord;
import com.liferay.data.engine.rest.internal.dto.v1_0.util.DataDefinitionFieldUtil;
import com.liferay.data.engine.rule.function.DataRuleFunction;
import com.liferay.data.engine.rule.function.DataRuleFunctionResult;
import com.liferay.portal.kernel.test.util.RandomTestUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcelo Mello
 */
public class DataRuleFunctionTestUtil {

	public static DataRuleFunctionResult validateDataRuleFunction(
		DataRecord dataRecord, DataRuleFunction dataRuleFunction,
		String fieldType) {

		return validateDataRuleFunction(
			new HashMap<>(), dataRecord, dataRuleFunction, fieldType);
	}

	public static DataRuleFunctionResult validateDataRuleFunction(
		Map<String, Object> dataDefinitionRuleParameters, DataRecord dataRecord,
		DataRuleFunction dataRuleFunction, String fieldType) {

		DataDefinitionField dataDefinitionField = _randomDataDefinitionField(
			fieldType);

		Map<String, Object> dataRecordValues = dataRecord.getDataRecordValues();

		return dataRuleFunction.validate(
			dataDefinitionRuleParameters,
			DataDefinitionFieldUtil.toSPIDataDefinitionField(
				dataDefinitionField),
			dataRecordValues.get(dataDefinitionField.getName()));
	}

	private static DataDefinitionField _randomDataDefinitionField(
		String fieldType) {

		DataDefinitionField dataDefinitionField = new DataDefinitionField() {
			{
				id = RandomTestUtil.randomLong();
				indexable = false;
				label = new HashMap();
				localizable = false;
				name = "fieldName";
				repeatable = false;
				tip = new HashMap();
			}
		};

		dataDefinitionField.setFieldType(fieldType);

		return dataDefinitionField;
	}

}