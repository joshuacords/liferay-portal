/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.checkbox;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldTypeSettingsTestCase;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Marcellus Tavares
 */
@RunWith(PowerMockRunner.class)
public class CheckboxDDMFormFieldTemplateContextContributorTest
	extends BaseDDMFormFieldTypeSettingsTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testGetNotDefinedPredefinedValue() {
		DDMFormField ddmFormField = new DDMFormField("field", "checkbox");

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setLocale(LocaleUtil.US);

		Map<String, Object> parameters =
			_checkboxDDMFormFieldTemplateContextContributor.getParameters(
				ddmFormField, ddmFormFieldRenderingContext);

		boolean predefinedValue = (boolean)parameters.get("predefinedValue");

		Assert.assertFalse(predefinedValue);
	}

	@Test
	public void testGetPredefinedValueFalse() {
		DDMFormField ddmFormField = new DDMFormField("field", "checkbox");

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setLocale(LocaleUtil.US);

		LocalizedValue predefinedValue = new LocalizedValue(LocaleUtil.US);

		predefinedValue.addString(LocaleUtil.US, StringPool.FALSE);

		ddmFormField.setProperty("predefinedValue", predefinedValue);

		Map<String, Object> parameters =
			_checkboxDDMFormFieldTemplateContextContributor.getParameters(
				ddmFormField, ddmFormFieldRenderingContext);

		boolean actualPredefinedValue = (boolean)parameters.get(
			"predefinedValue");

		Assert.assertFalse(actualPredefinedValue);
	}

	@Test
	public void testGetPredefinedValueTrue() {
		DDMFormField ddmFormField = new DDMFormField("field", "checkbox");

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setLocale(LocaleUtil.US);

		LocalizedValue predefinedValue = new LocalizedValue(LocaleUtil.US);

		predefinedValue.addString(LocaleUtil.US, StringPool.TRUE);

		ddmFormField.setProperty("predefinedValue", predefinedValue);

		Map<String, Object> parameters =
			_checkboxDDMFormFieldTemplateContextContributor.getParameters(
				ddmFormField, ddmFormFieldRenderingContext);

		boolean actualPredefinedValue = (boolean)parameters.get(
			"predefinedValue");

		Assert.assertTrue(actualPredefinedValue);
	}

	private final CheckboxDDMFormFieldTemplateContextContributor
		_checkboxDDMFormFieldTemplateContextContributor =
			new CheckboxDDMFormFieldTemplateContextContributor();

}