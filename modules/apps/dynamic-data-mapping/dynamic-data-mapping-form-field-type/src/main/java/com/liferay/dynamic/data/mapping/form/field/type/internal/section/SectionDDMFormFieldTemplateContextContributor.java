/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.section;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTemplateContextContributor;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Lancha
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=section",
	service = {
		DDMFormFieldTemplateContextContributor.class,
		SectionDDMFormFieldTemplateContextContributor.class
	}
)
public class SectionDDMFormFieldTemplateContextContributor
	implements DDMFormFieldTemplateContextContributor {

	@Override
	public Map<String, Object> getParameters(
		DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		Map<String, List<Object>> nestedFieldsMap =
			(Map<String, List<Object>>)ddmFormFieldRenderingContext.getProperty(
				"nestedFields");

		if (nestedFieldsMap == null) {
			nestedFieldsMap = new HashMap<>();
		}

		return HashMapBuilder.<String, Object>put(
			"nestedFields",
			getNestedFields(
				nestedFieldsMap,
				getNestedFieldNames(
					GetterUtil.getString(
						ddmFormField.getProperty("nestedFieldNames")),
					nestedFieldsMap.keySet()))
		).put(
			"predefinedValue",
			_getPredefinedValue(
				ddmFormField, ddmFormFieldRenderingContext.getLocale())
		).put(
			"rows",
			getJSONArray(GetterUtil.getString(ddmFormField.getProperty("rows")))
		).put(
			"value",
			 GetterUtil.getString(ddmFormFieldRenderingContext.getProperty("value"))
		).build();
	}

	private String _getPredefinedValue(
		DDMFormField ddmFormField, Locale locale) {

		LocalizedValue predefinedValue = ddmFormField.getPredefinedValue();

		if (predefinedValue == null) {
			return StringPool.BLANK;
		}

		return GetterUtil.getString(predefinedValue.getString(locale));
	}
	
	protected JSONArray getJSONArray(String rows) {
		try {
			return jsonFactory.createJSONArray(rows);
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(jsonException, jsonException);
			}
		}

		return jsonFactory.createJSONArray();
	}

	protected String[] getNestedFieldNames(
		String nestedFieldNames, Set<String> defaultNestedFieldNames) {

		if (Validator.isNotNull(nestedFieldNames)) {
			return StringUtil.split(nestedFieldNames);
		}

		return defaultNestedFieldNames.toArray(new String[0]);
	}

	protected List<Object> getNestedFields(
		Map<String, List<Object>> nestedFieldsMap, String[] nestedFieldNames) {

		List<Object> nestedFields = new ArrayList<>();

		for (String nestedFieldName : nestedFieldNames) {
			nestedFields.addAll(nestedFieldsMap.get(nestedFieldName));
		}

		return nestedFields;
	}

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		SectionDDMFormFieldTemplateContextContributor.class);

}