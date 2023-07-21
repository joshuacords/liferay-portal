/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.numeric;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueValidationException;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueValidator;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.util.NumericDDMFormFieldUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.NumberFormat;
import java.text.ParseException;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=numeric",
	service = DDMFormFieldValueValidator.class
)
public class NumericDDMFormFieldValueValidator
	implements DDMFormFieldValueValidator {

	@Override
	public void validate(DDMFormField ddmFormField, Value value)
		throws DDMFormFieldValueValidationException {

		for (Locale availableLocale : value.getAvailableLocales()) {
			String valueString = value.getString(availableLocale);

			if (Validator.isNotNull(valueString) &&
				!isNumber(valueString, availableLocale)) {

				throw new DDMFormFieldValueValidationException(
					String.format(
						"\"%s\" is not a %s", valueString,
						ddmFormField.getDataType()));
			}
		}
	}

	protected boolean isNumber(String valueString, Locale locale) {
		try {
			NumberFormat numberFormat =
				NumericDDMFormFieldUtil.getDecimalFormat(locale);

			numberFormat.parse(valueString);
		}
		catch (ParseException parseException) {
			return false;
		}

		return true;
	}

}