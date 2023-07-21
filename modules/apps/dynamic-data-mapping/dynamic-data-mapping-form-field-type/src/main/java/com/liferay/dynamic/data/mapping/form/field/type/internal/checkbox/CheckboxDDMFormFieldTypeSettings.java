/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.checkbox;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;

/**
 * @author Marcellus Tavares
 */
@DDMForm
@DDMFormLayout(
	paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.TABBED_MODE,
	value = {
		@DDMFormLayoutPage(
			title = "%basic",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"label", "tip", "required", "showAsSwitcher"
							}
						)
					}
				)
			}
		),
		@DDMFormLayoutPage(
			title = "%advanced",
			value = {
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"name", "visibilityExpression",
								"predefinedValue", "validation",
								"fieldNamespace", "indexType", "localizable",
								"readOnly", "dataType", "type", "showLabel",
								"repeatable"
							}
						)
					}
				)
			}
		)
	}
)
public interface CheckboxDDMFormFieldTypeSettings
	extends DefaultDDMFormFieldTypeSettings {

	@DDMFormField(
		dataType = "string", label = "%predefined-value",
		properties = "showAsSwitcher=true", type = "checkbox"
	)
	@Override
	public LocalizedValue predefinedValue();

	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public boolean repeatable();

	@DDMFormField(
		dataType = "boolean", label = "%show-as-a-switcher",
		properties = "showAsSwitcher=true", type = "checkbox"
	)
	public boolean showAsSwitcher();

	@DDMFormField(visibilityExpression = "FALSE")
	@Override
	public DDMFormFieldValidation validation();

}