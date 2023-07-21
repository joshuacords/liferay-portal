/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.checkbox;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueAccessor;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;

import java.util.Locale;
import java.util.function.IntFunction;

import org.osgi.service.component.annotations.Component;

/**
 * @author Renato Rego
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=checkbox",
	service = {
		CheckboxDDMFormFieldValueAccessor.class, DDMFormFieldValueAccessor.class
	}
)
public class CheckboxDDMFormFieldValueAccessor
	implements DDMFormFieldValueAccessor<Boolean> {

	@Override
	public IntFunction<Boolean[]> getArrayGeneratorIntFunction() {
		return Boolean[]::new;
	}

	@Override
	public Boolean getValue(
		DDMFormFieldValue ddmFormFieldValue, Locale locale) {

		Value value = ddmFormFieldValue.getValue();

		return Boolean.valueOf(value.getString(locale));
	}

	@Override
	public boolean isEmpty(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		Boolean valueBoolean = getValue(ddmFormFieldValue, locale);

		if (valueBoolean == Boolean.FALSE) {
			return true;
		}

		return false;
	}

}