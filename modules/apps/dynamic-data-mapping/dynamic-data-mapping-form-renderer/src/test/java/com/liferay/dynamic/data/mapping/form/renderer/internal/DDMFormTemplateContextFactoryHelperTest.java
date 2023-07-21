/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.renderer.internal;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidationExpression;
import com.liferay.dynamic.data.mapping.model.DDMFormRule;
import com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceService;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Rafael Praxedes
 */
@RunWith(PowerMockRunner.class)
public class DDMFormTemplateContextFactoryHelperTest extends PowerMockito {

	@BeforeClass
	public static void setUpClass() throws Exception {
		setUpDDMFormTemplateContextFactoryHelper();
	}

	@Test
	public void testGetEvaluableFieldNames() throws Exception {
		DDMForm ddmForm = DDMFormTestUtil.createDDMForm();

		ddmForm.addDDMFormField(
			DDMFormTestUtil.createTextDDMFormField(
				"Field0", false, false, false));
		ddmForm.addDDMFormField(
			DDMFormTestUtil.createTextDDMFormField(
				"Field1", false, false, false));
		ddmForm.addDDMFormField(
			DDMFormTestUtil.createTextDDMFormField(
				"Field2", false, false, true));

		DDMFormField ddmFormField3 = DDMFormTestUtil.createTextDDMFormField(
			"Field3", false, false, false);

		ddmFormField3.setVisibilityExpression("equals(Field0, 'Joe')");

		ddmForm.addDDMFormField(ddmFormField3);

		DDMFormField ddmFormField4 = DDMFormTestUtil.createTextDDMFormField(
			"Field4", false, false, false);

		DDMFormFieldValidation ddmFormFieldValidation =
			new DDMFormFieldValidation();

		ddmFormFieldValidation.setDDMFormFieldValidationExpression(
			new DDMFormFieldValidationExpression() {
				{
					setValue("isEmailAddress(Field4)");
				}
			});

		ddmFormField4.setDDMFormFieldValidation(ddmFormFieldValidation);

		ddmForm.addDDMFormField(ddmFormField4);

		Set<String> expectedEvaluableFieldNames = SetUtil.fromArray(
			new String[] {"Field0", "Field2", "Field4"});

		Set<String> actualEvaluableFieldNames =
			_ddmFormTemplateContextFactoryHelper.getEvaluableDDMFormFieldNames(
				ddmForm);

		Assert.assertEquals(
			expectedEvaluableFieldNames, actualEvaluableFieldNames);
	}

	protected static void setUpDDMFormTemplateContextFactoryHelper()
		throws Exception {

		DDMDataProviderInstance ddmDataProviderInstance = mock(
			DDMDataProviderInstance.class);

		when(
			ddmDataProviderInstance.getUuid()
		).thenReturn(
			_DATA_PROVIDER_INSTANCE_UUID
		);

		DDMDataProviderInstanceService ddmDataProviderInstanceService = mock(
			DDMDataProviderInstanceService.class);

		when(
			ddmDataProviderInstanceService.getDataProviderInstance(
				Matchers.anyLong())
		).thenReturn(
			ddmDataProviderInstance
		);

		_ddmFormTemplateContextFactoryHelper =
			new DDMFormTemplateContextFactoryHelper();
	}

	protected DDMFormRule createAutoFillDDMFormRule() {
		StringBuilder sb = new StringBuilder();

		sb.append("call(");
		sb.append(StringPool.APOSTROPHE);
		sb.append(_DATA_PROVIDER_INSTANCE_UUID);
		sb.append(StringPool.APOSTROPHE);
		sb.append(", 'input=Field1', 'Field2=output')");

		return new DDMFormRule(
			"not(equals(getValue('Field1'), 'Option'))", sb.toString());
	}

	private static final String _DATA_PROVIDER_INSTANCE_UUID =
		"ea3464d6-71e2-5202-964a-f53d6cc0ee39";

	private static DDMFormTemplateContextFactoryHelper
		_ddmFormTemplateContextFactoryHelper;

}