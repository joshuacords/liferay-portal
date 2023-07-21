/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.renderer.internal;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

/**
 * @author Marcellus Tavares
 */
public class DefaultDDMFormValuesFactory {

	public DefaultDDMFormValuesFactory(DDMForm ddmForm, Locale locale) {
		_ddmForm = ddmForm;
		_locale = locale;
	}

	public DDMFormValues create() {
		DDMFormValues ddmFormValues = new DDMFormValues(_ddmForm);

		ddmFormValues.addAvailableLocale(_locale);
		ddmFormValues.setDefaultLocale(_locale);

		for (DDMFormField ddmFormField : _ddmForm.getDDMFormFields()) {
			DDMFormFieldValue ddmFormFieldValue =
				createDefaultDDMFormFieldValue(ddmFormField);

			ddmFormValues.addDDMFormFieldValue(ddmFormFieldValue);
		}

		return ddmFormValues;
	}

	protected DDMFormFieldValue createDefaultDDMFormFieldValue(
		DDMFormField ddmFormField) {

		DDMFormFieldValue ddmFormFieldValue = new DDMFormFieldValue();

		ddmFormFieldValue.setName(ddmFormField.getName());

		ddmFormFieldValue.setValue(createDefaultValue(ddmFormField));

		for (DDMFormField nestedDDMFormField :
				ddmFormField.getNestedDDMFormFields()) {

			ddmFormFieldValue.addNestedDDMFormFieldValue(
				createDefaultDDMFormFieldValue(nestedDDMFormField));
		}

		return ddmFormFieldValue;
	}

	protected Value createDefaultLocalizedValue(String defaultValueString) {
		Value value = new LocalizedValue(_locale);

		value.addString(_locale, defaultValueString);

		return value;
	}

	protected Value createDefaultValue(DDMFormField ddmFormField) {
		LocalizedValue predefinedValue = ddmFormField.getPredefinedValue();

		String defaultValueString = GetterUtil.getString(
			predefinedValue.getString(_locale));

		if (ddmFormField.isLocalizable()) {
			return createDefaultLocalizedValue(defaultValueString);
		}

		return new UnlocalizedValue(defaultValueString);
	}

	private final DDMForm _ddmForm;
	private final Locale _locale;

}