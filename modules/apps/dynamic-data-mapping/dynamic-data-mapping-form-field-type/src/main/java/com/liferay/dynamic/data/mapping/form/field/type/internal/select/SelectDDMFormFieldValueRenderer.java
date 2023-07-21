/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.select;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.util.Locale;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Renato Rego
 */
@Component(
	immediate = true, property = "ddm.form.field.type.name=select",
	service = DDMFormFieldValueRenderer.class
)
public class SelectDDMFormFieldValueRenderer
	implements DDMFormFieldValueRenderer {

	@Override
	public String render(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		JSONArray optionsValuesJSONArray =
			selectDDMFormFieldValueAccessor.getValue(ddmFormFieldValue, locale);

		if (optionsValuesJSONArray.length() == 0) {
			return StringPool.BLANK;
		}

		DDMFormFieldOptions ddmFormFieldOptions = getDDMFormFieldOptions(
			ddmFormFieldValue);

		StringBundler sb = new StringBundler(
			(optionsValuesJSONArray.length() * 2) - 1);

		for (int i = 0; i < optionsValuesJSONArray.length(); i++) {
			String optionValue = optionsValuesJSONArray.getString(i);

			if (isManualDataSourceType(ddmFormFieldValue.getDDMFormField())) {
				LocalizedValue optionLabel =
					ddmFormFieldOptions.getOptionLabels(optionValue);

				if (optionLabel != null) {
					sb.append(HtmlUtil.escape(optionLabel.getString(locale)));
				}
				else {
					sb.append(optionValue);
				}
			}
			else {
				sb.append(optionValue);
			}

			sb.append(StringPool.COMMA_AND_SPACE);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	protected DDMFormFieldOptions getDDMFormFieldOptions(
		DDMFormFieldValue ddmFormFieldValue) {

		DDMFormField ddmFormField = ddmFormFieldValue.getDDMFormField();

		return ddmFormField.getDDMFormFieldOptions();
	}

	protected boolean isManualDataSourceType(DDMFormField ddmFormField) {
		String dataSourceType = GetterUtil.getString(
			ddmFormField.getProperty("dataSourceType"), "manual");

		if (Objects.equals(dataSourceType, "manual")) {
			return true;
		}

		return false;
	}

	@Reference
	protected SelectDDMFormFieldValueAccessor selectDDMFormFieldValueAccessor;

}