/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.grid;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueAccessor;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pedro Queiroz
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=grid",
	service = {
		DDMFormFieldValueAccessor.class, GridDDMFormFieldValueAccessor.class
	}
)
public class GridDDMFormFieldValueAccessor
	implements DDMFormFieldValueAccessor<JSONObject> {

	@Override
	public IntFunction<JSONObject[]> getArrayGeneratorIntFunction() {
		return JSONObject[]::new;
	}

	@Override
	public JSONObject getValue(
		DDMFormFieldValue ddmFormFieldValue, Locale locale) {

		Value value = ddmFormFieldValue.getValue();

		return createJSONObject(value.getString(locale));
	}

	@Override
	public boolean isEmpty(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		JSONObject jsonObject = getValue(ddmFormFieldValue, locale);

		Set<String> keys = getUniqueKeys(jsonObject);

		Set<String> rowValues = getDDMFormFieldRowValues(
			ddmFormFieldValue.getDDMFormField());

		Stream<String> stream = rowValues.stream();

		return stream.anyMatch(rowValue -> !keys.contains(rowValue));
	}

	protected JSONObject createJSONObject(String json) {
		try {
			return jsonFactory.createJSONObject(json);
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to parse JSON object", jsonException);
			}

			return jsonFactory.createJSONObject();
		}
	}

	protected Set<String> getDDMFormFieldRowValues(DDMFormField ddmFormField) {
		DDMFormFieldOptions ddmFormFieldOptions =
			(DDMFormFieldOptions)ddmFormField.getProperty("rows");

		return ddmFormFieldOptions.getOptionsValues();
	}

	protected Set<String> getUniqueKeys(JSONObject jsonObject) {
		Set<String> uniqueKeys = new HashSet<>();

		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()) {
			uniqueKeys.add(keys.next());
		}

		return uniqueKeys;
	}

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		GridDDMFormFieldValueAccessor.class);

}