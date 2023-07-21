/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.select;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueValidationException;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueValidator;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=select",
	service = DDMFormFieldValueValidator.class
)
public class SelectDDMFormFieldValueValidator
	implements DDMFormFieldValueValidator {

	@Override
	public void validate(DDMFormField ddmFormField, Value value)
		throws DDMFormFieldValueValidationException {

		String dataSourceType = GetterUtil.getString(
			ddmFormField.getProperty("dataSourceType"), "manual");

		if (Objects.equals(dataSourceType, "manual")) {
			try {
				validateDDMFormFieldOptions(ddmFormField, value);
			}
			catch (DDMFormFieldValueValidationException
						ddmFormFieldValueValidationException) {

				throw ddmFormFieldValueValidationException;
			}
			catch (Exception exception) {
				if (_log.isDebugEnabled()) {
					_log.debug(exception, exception);
				}

				throw new DDMFormFieldValueValidationException(exception);
			}
		}
	}

	protected void validateDDMFormFieldOptions(
			DDMFormField ddmFormField, Value value)
		throws Exception {

		DDMFormFieldOptions ddmFormFieldOptions =
			ddmFormField.getDDMFormFieldOptions();

		if (ddmFormFieldOptions == null) {
			throw new DDMFormFieldValueValidationException(
				String.format(
					"Options must be set for select field \"%s\"",
					ddmFormField.getName()));
		}

		Set<String> optionValues = ddmFormFieldOptions.getOptionsValues();

		if (optionValues.isEmpty()) {
			throw new DDMFormFieldValueValidationException(
				"Options must contain at least one alternative");
		}

		Map<Locale, String> selectedValues = value.getValues();

		for (String selectedValue : selectedValues.values()) {
			validateSelectedValue(ddmFormField, optionValues, selectedValue);
		}
	}

	protected void validateSelectedValue(
			DDMFormField ddmFormField, Set<String> optionValues,
			String selectedValue)
		throws Exception {

		JSONArray jsonArray = jsonFactory.createJSONArray(selectedValue);

		for (int i = 0; i < jsonArray.length(); i++) {
			if (Validator.isNull(jsonArray.getString(i)) &&
				!ddmFormField.isRequired()) {

				continue;
			}

			if (!optionValues.contains(jsonArray.getString(i))) {
				throw new DDMFormFieldValueValidationException(
					String.format(
						"The selected option \"%s\" is not a valid alternative",
						jsonArray.getString(i)));
			}
		}
	}

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		SelectDDMFormFieldValueValidator.class);

}