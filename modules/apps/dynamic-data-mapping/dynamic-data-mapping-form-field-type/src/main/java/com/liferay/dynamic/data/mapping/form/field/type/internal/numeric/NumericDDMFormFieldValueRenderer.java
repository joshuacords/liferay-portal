/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.numeric;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueRenderer;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.util.NumericDDMFormFieldUtil;
import com.liferay.petra.string.StringPool;
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
	service = DDMFormFieldValueRenderer.class
)
public class NumericDDMFormFieldValueRenderer
	implements DDMFormFieldValueRenderer {

	@Override
	public String render(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		Number number = getNumber(ddmFormFieldValue);

		if (number != null) {
			NumberFormat numberFormat =
				NumericDDMFormFieldUtil.getDecimalFormat(locale);

			return numberFormat.format(number);
		}

		return StringPool.BLANK;
	}

	protected Number getNumber(DDMFormFieldValue ddmFormFieldValue) {
		Value value = ddmFormFieldValue.getValue();

		Locale locale = value.getDefaultLocale();

		String valueString = value.getString(locale);

		if (Validator.isNotNull(valueString)) {
			try {
				NumberFormat formatter =
					NumericDDMFormFieldUtil.getDecimalFormat(locale);

				return formatter.parse(valueString);
			}
			catch (ParseException parseException) {
			}
		}

		return null;
	}

}