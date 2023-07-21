/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.date;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueValidationException;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueValidator;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcela Cunha
 * @author Pedro Queiroz
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=date",
	service = DDMFormFieldValueValidator.class
)
public class DateDDMFormFieldValueValidator
	implements DDMFormFieldValueValidator {

	@Override
	public void validate(DDMFormField ddmFormField, Value value)
		throws DDMFormFieldValueValidationException {

		DDMForm ddmForm = ddmFormField.getDDMForm();

		Locale defaultLocale = ddmForm.getDefaultLocale();

		String valueString = value.getString(defaultLocale);

		validateDateValue(ddmFormField, defaultLocale, valueString);
	}

	protected void validateDateValue(
			DDMFormField ddmFormField, Locale defaultLocale, String valueString)
		throws DDMFormFieldValueValidationException {

		if (Validator.isNotNull(valueString)) {
			try {
				DateUtil.formatDate("yyyy-MM-dd", valueString, defaultLocale);
			}
			catch (ParseException parseException) {
				if (_log.isDebugEnabled()) {
					_log.debug(parseException, parseException);
				}

				throw new DDMFormFieldValueValidationException(
					String.format(
						"Invalid date input for date field \"%s\"",
						ddmFormField.getName()));
			}
		}
		else if (ddmFormField.isRequired()) {
			throw new DDMFormFieldValueValidationException(
				String.format(
					"Date input cannot be null \"%s\"",
					ddmFormField.getName()));
		}
	}

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		DateDDMFormFieldValueValidator.class);

}