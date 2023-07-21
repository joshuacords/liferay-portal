/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.form.internal.dto.v1_0.util;

import com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderingContext;
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormTemplateContextFactory;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.headless.form.dto.v1_0.FormContext;
import com.liferay.headless.form.dto.v1_0.FormFieldContext;
import com.liferay.headless.form.dto.v1_0.FormFieldValue;
import com.liferay.headless.form.dto.v1_0.FormPageContext;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Victor Oliveira
 */
public class FormContextUtil {

	public static FormContext evaluateContext(
			DDMFormInstance ddmFormInstance,
			DDMFormRenderingContext ddmFormRenderingContext,
			DDMFormTemplateContextFactory ddmFormTemplateContextFactory,
			FormFieldValue[] formFieldValues, Locale locale)
		throws Exception {

		ddmFormRenderingContext.setDDMFormValues(
			DDMFormValuesUtil.createDDMFormValues(
				ddmFormInstance, formFieldValues, locale));
		ddmFormRenderingContext.setLocale(locale);

		DDMStructure ddmStructure = ddmFormInstance.getStructure();

		Map<String, Object> formContext = ddmFormTemplateContextFactory.create(
			ddmStructure.getDDMForm(), ddmStructure.getDDMFormLayout(),
			ddmFormRenderingContext);

		return new FormContext() {
			{
				formPageContexts = TransformUtil.transformToArray(
					_getMaps(formContext, "pages"),
					FormContextUtil::_toFormPageContext, FormPageContext.class);
				readOnly = _getBoolean(formContext, "readOnly");
				showRequiredFieldsWarning = _getBoolean(
					formContext, "showRequiredFieldsWarning");
				showSubmitButton = _getBoolean(formContext, "showSubmitButton");
			}
		};
	}

	private static Boolean _getBoolean(Map<String, Object> map, String key) {
		return (Boolean)map.getOrDefault(key, false);
	}

	private static List<Map<String, Object>> _getMaps(
		Map<String, Object> map, String key) {

		return (List<Map<String, Object>>)map.getOrDefault(
			key, new ArrayList<>());
	}

	private static String _getString(Map<String, Object> map, String key) {
		return String.valueOf(map.getOrDefault(key, ""));
	}

	private static FormFieldContext _toFormFieldContext(
		Map<String, Object> fieldContext) {

		return new FormFieldContext() {
			{
				evaluable = _getBoolean(fieldContext, "evaluable");
				name = _getString(fieldContext, "fieldName");
				readOnly = _getBoolean(fieldContext, "readOnly");
				required = _getBoolean(fieldContext, "required");
				valid = _getBoolean(fieldContext, "valid");
				value = _getString(fieldContext, "value");
				valueChanged = _getBoolean(fieldContext, "valueChanged");
				visible = _getBoolean(fieldContext, "visible");
			}
		};
	}

	private static FormPageContext _toFormPageContext(
		Map<String, Object> formPageContext) {

		return new FormPageContext() {
			{
				enabled = _getBoolean(formPageContext, "enabled");

				List<Map<String, Object>> maps = _getMaps(
					formPageContext, "rows");

				Stream<Map<String, Object>> stream = maps.stream();

				formFieldContexts = TransformUtil.transformToArray(
					stream.map(
						row -> _getMaps(row, "columns")
					).flatMap(
						List::stream
					).map(
						column -> _getMaps(column, "fields")
					).flatMap(
						List::stream
					).collect(
						Collectors.toList()
					),
					FormContextUtil::_toFormFieldContext,
					FormFieldContext.class);

				showRequiredFieldsWarning = _getBoolean(
					formPageContext, "showRequiredFieldsWarning");
			}
		};
	}

}