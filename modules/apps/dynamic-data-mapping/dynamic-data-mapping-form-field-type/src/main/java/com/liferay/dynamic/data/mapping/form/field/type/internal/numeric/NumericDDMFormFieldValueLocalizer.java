/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.numeric;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueLocalizer;
import com.liferay.dynamic.data.mapping.util.NumericDDMFormFieldUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.text.DecimalFormat;
import java.text.ParseException;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rodrigo Paulino
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=numeric",
	service = DDMFormFieldValueLocalizer.class
)
public class NumericDDMFormFieldValueLocalizer
	implements DDMFormFieldValueLocalizer {

	@Override
	public String localize(String value, Locale locale) {
		try {
			DecimalFormat decimalFormat =
				NumericDDMFormFieldUtil.getDecimalFormat(locale);

			Number number = GetterUtil.getNumber(decimalFormat.parse(value));

			String formattedNumber = decimalFormat.format(number);

			if (!value.equals(formattedNumber)) {
				DecimalFormat defaultDecimalFormat =
					NumericDDMFormFieldUtil.getDecimalFormat(LocaleUtil.US);

				number = defaultDecimalFormat.parse(value);

				formattedNumber = decimalFormat.format(number);
			}

			return formattedNumber;
		}
		catch (ParseException parseException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to parse number for locale " + locale,
					parseException);
			}
		}

		return value;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		NumericDDMFormFieldValueLocalizer.class);

}