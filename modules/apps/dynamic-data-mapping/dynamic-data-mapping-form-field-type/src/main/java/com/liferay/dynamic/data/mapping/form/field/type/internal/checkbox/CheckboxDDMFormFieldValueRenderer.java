/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.checkbox;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueRenderer;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Renato Rego
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=checkbox",
	service = DDMFormFieldValueRenderer.class
)
public class CheckboxDDMFormFieldValueRenderer
	implements DDMFormFieldValueRenderer {

	@Override
	public String render(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		Boolean valueBoolean = checkboxDDMFormFieldValueAccessor.getValue(
			ddmFormFieldValue, locale);

		if (valueBoolean == Boolean.TRUE) {
			return LanguageUtil.get(locale, "yes");
		}

		return LanguageUtil.get(locale, "no");
	}

	@Reference
	protected CheckboxDDMFormFieldValueAccessor
		checkboxDDMFormFieldValueAccessor;

}