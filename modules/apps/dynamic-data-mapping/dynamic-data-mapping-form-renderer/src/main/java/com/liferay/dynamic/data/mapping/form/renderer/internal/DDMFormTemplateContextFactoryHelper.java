/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.renderer.internal;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.DDMFormRule;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Leonardo Barros
 * @author Rafael Praxedes
 */
public class DDMFormTemplateContextFactoryHelper {

	public Set<String> getEvaluableDDMFormFieldNames(DDMForm ddmForm) {
		Set<String> evaluableDDMFormFieldNames = new HashSet<>();

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(true);

		Set<String> ddmFormFieldNames = ddmFormFieldsMap.keySet();

		evaluableDDMFormFieldNames.addAll(
			getReferencedFieldNamesByDDMFormRules(
				ddmForm.getDDMFormRules(), ddmFormFieldNames));

		for (DDMFormField ddmFormField : ddmFormFieldsMap.values()) {
			if (isDDMFormFieldEvaluable(ddmFormField)) {
				evaluableDDMFormFieldNames.add(ddmFormField.getName());
			}

			evaluableDDMFormFieldNames.addAll(
				getReferencedFieldNamesByExpression(
					ddmFormField.getVisibilityExpression(), ddmFormFieldNames));
		}

		return evaluableDDMFormFieldNames;
	}

	protected Set<String> getReferencedFieldNamesByDDMFormRules(
		List<DDMFormRule> ddmFormRules, Set<String> ddmFormFieldNames) {

		Set<String> referencedFieldNames = new HashSet<>();

		for (DDMFormRule ddmFormRule : ddmFormRules) {
			referencedFieldNames.addAll(
				getReferencedFieldNamesByExpression(
					ddmFormRule.getCondition(), ddmFormFieldNames));

			for (String action : ddmFormRule.getActions()) {
				referencedFieldNames.addAll(
					getReferencedFieldNamesByExpression(
						action, ddmFormFieldNames));
			}
		}

		return referencedFieldNames;
	}

	protected Set<String> getReferencedFieldNamesByExpression(
		String expression, Set<String> ddmFormFieldNames) {

		if (Validator.isNull(expression)) {
			return Collections.emptySet();
		}

		Set<String> referencedFieldNames = new HashSet<>();

		for (String ddmFormFieldName : ddmFormFieldNames) {
			Pattern pattern = Pattern.compile(
				String.format(".*('?%s'?).*", ddmFormFieldName));

			Matcher matcher = pattern.matcher(expression);

			if (matcher.matches()) {
				referencedFieldNames.add(ddmFormFieldName);
			}
		}

		return referencedFieldNames;
	}

	protected boolean isDDMFormFieldEvaluable(DDMFormField ddmFormField) {
		if (ddmFormField.isRequired()) {
			return true;
		}

		DDMFormFieldValidation ddmFormFieldValidation =
			ddmFormField.getDDMFormFieldValidation();

		if ((ddmFormFieldValidation != null) &&
			(ddmFormFieldValidation.getDDMFormFieldValidationExpression() !=
				null)) {

			return true;
		}

		return false;
	}

}