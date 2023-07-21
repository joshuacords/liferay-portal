/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.grid;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.util.LocaleUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Pedro Queiroz
 */
@RunWith(PowerMockRunner.class)
public class GridDDMFormFieldValueAccessorTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		setUpGridDDMFormFieldValueAccessor();
	}

	@Test
	public void testEmpty() {
		DDMForm ddmForm = DDMFormTestUtil.createDDMForm();

		DDMFormField ddmFormField = DDMFormTestUtil.createDDMFormField(
			"Grid", "Grid", "grid", "string", false, false, true);

		DDMFormFieldOptions ddmFormFieldOptions = new DDMFormFieldOptions();

		ddmFormFieldOptions.addOptionLabel("row1", LocaleUtil.US, "Row 1");
		ddmFormFieldOptions.addOptionLabel("row2", LocaleUtil.US, "Row 2");

		ddmFormField.setProperty("rows", ddmFormFieldOptions);

		ddmForm.addDDMFormField(ddmFormField);

		DDMFormValues ddmFormValues = DDMFormValuesTestUtil.createDDMFormValues(
			ddmForm);

		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createDDMFormFieldValue(
				"Grid", new UnlocalizedValue("{\"row1\":\"column1\"}"));

		ddmFormFieldValue.setDDMFormValues(ddmFormValues);

		Assert.assertTrue(
			_gridDDMFormFieldValueAccessor.isEmpty(
				ddmFormFieldValue, LocaleUtil.US));
	}

	@Test
	public void testGetGridValue() {
		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createDDMFormFieldValue(
				"Grid", new UnlocalizedValue("{\"RowValue\":\"ColumnValue\"}"));

		Assert.assertEquals(
			"{\"RowValue\":\"ColumnValue\"}",
			String.valueOf(
				_gridDDMFormFieldValueAccessor.getValue(
					ddmFormFieldValue, LocaleUtil.US)));
	}

	@Test
	public void testNotEmpty() {
		DDMForm ddmForm = DDMFormTestUtil.createDDMForm();

		DDMFormField ddmFormField = DDMFormTestUtil.createDDMFormField(
			"Grid", "Grid", "grid", "string", false, false, true);

		DDMFormFieldOptions ddmFormFieldOptions = new DDMFormFieldOptions();

		ddmFormFieldOptions.addOptionLabel("row1", LocaleUtil.US, "Row 1");
		ddmFormFieldOptions.addOptionLabel("row2", LocaleUtil.US, "Row 2");

		ddmFormField.setProperty("rows", ddmFormFieldOptions);

		ddmForm.addDDMFormField(ddmFormField);

		DDMFormValues ddmFormValues = DDMFormValuesTestUtil.createDDMFormValues(
			ddmForm);

		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createDDMFormFieldValue(
				"Grid",
				new UnlocalizedValue(
					"{\"row1\":\"column1\",\"row2\":\"column1\"}"));

		ddmFormFieldValue.setDDMFormValues(ddmFormValues);

		Assert.assertFalse(
			_gridDDMFormFieldValueAccessor.isEmpty(
				ddmFormFieldValue, LocaleUtil.US));
	}

	protected void setUpGridDDMFormFieldValueAccessor() throws Exception {
		_gridDDMFormFieldValueAccessor = new GridDDMFormFieldValueAccessor();

		field(
			GridDDMFormFieldValueAccessor.class, "jsonFactory"
		).set(
			_gridDDMFormFieldValueAccessor, _jsonFactory
		);
	}

	private GridDDMFormFieldValueAccessor _gridDDMFormFieldValueAccessor;
	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

}