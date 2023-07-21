/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.builder.internal.context;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesTracker;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldValueAccessor;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Jeyvison Nascimento
 */
@RunWith(MockitoJUnitRunner.class)
public class DDMFormContextToDDMFormTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		setUpDDMFormContextToDDMFormValues();
	}

	@Test
	public void testGetValueFromValueAccessor() throws IOException {
		Mockito.when(
			_ddmFormFieldTypeServicesTracker.getDDMFormFieldValueAccessor(
				Matchers.anyString())
		).thenReturn(
			_ddmFormFieldValueAccessor
		);

		Mockito.when(
			_ddmFormFieldValueAccessor.getValue(Matchers.any(), Matchers.any())
		).thenReturn(
			false
		);

		_ddmFormContextToDDMForm.ddmFormFieldTypeServicesTracker =
			_ddmFormFieldTypeServicesTracker;

		Object result = _ddmFormContextToDDMForm.getValueFromValueAccessor(
			"checkbox", "false", LocaleUtil.US);

		Assert.assertFalse((boolean)result);
	}

	@Test
	public void testGetValueWithoutValueAccessor() throws IOException {
		Mockito.when(
			_ddmFormFieldTypeServicesTracker.getDDMFormFieldValueAccessor(
				Matchers.anyString())
		).thenReturn(
			null
		);

		_ddmFormContextToDDMForm.ddmFormFieldTypeServicesTracker =
			_ddmFormFieldTypeServicesTracker;

		Object result = _ddmFormContextToDDMForm.getValueFromValueAccessor(
			"checkbox", "false", LocaleUtil.US);

		Assert.assertEquals("false", result);
	}

	protected void setUpDDMFormContextToDDMFormValues() throws Exception {
		_ddmFormContextToDDMForm = new DDMFormContextToDDMForm();

		_ddmFormContextToDDMForm.jsonFactory = new JSONFactoryImpl();
	}

	private DDMFormContextToDDMForm _ddmFormContextToDDMForm;

	@Mock
	private DDMFormFieldTypeServicesTracker _ddmFormFieldTypeServicesTracker;

	@Mock
	private DDMFormFieldValueAccessor _ddmFormFieldValueAccessor;

}