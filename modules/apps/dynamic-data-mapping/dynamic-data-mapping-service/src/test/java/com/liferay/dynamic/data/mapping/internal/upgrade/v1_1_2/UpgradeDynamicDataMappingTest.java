/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_2;

import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.json.JSONArrayImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Jeyvison Nascimento
 */
@RunWith(MockitoJUnitRunner.class)
public class UpgradeDynamicDataMappingTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testTransformRadioDDMFormFieldValues() throws Exception {
		UpgradeDynamicDataMapping.RadioDDMFormFieldValueTransformer
			radioDDMFormFieldValueTransformer =
				_getRadioDDMFormFieldValueTransformer();

		Mockito.when(
			_jsonFactory.createJSONArray(Matchers.any(String.class))
		).thenReturn(
			new JSONArrayImpl()
		);

		Mockito.when(
			_ddmFormFieldValue.getValue()
		).thenReturn(
			_value
		);

		Mockito.when(
			_value.getAvailableLocales()
		).thenReturn(
			_getAvailableLocales()
		);

		Mockito.when(
			_value.getString(Mockito.any(Locale.class))
		).thenReturn(
			"value"
		);

		radioDDMFormFieldValueTransformer.transform(_ddmFormFieldValue);

		Mockito.verify(
			_value, VerificationModeFactory.atLeastOnce()
		).addString(
			Matchers.any(Locale.class), Matchers.anyString()
		);
	}

	@Test
	public void testTransformRadioDDMFormFieldValuesWithNullValue()
		throws Exception {

		UpgradeDynamicDataMapping.RadioDDMFormFieldValueTransformer
			radioDDMFormFieldValueTransformer =
				_getRadioDDMFormFieldValueTransformer();

		Mockito.when(
			_ddmFormFieldValue.getValue()
		).thenReturn(
			_value
		);

		radioDDMFormFieldValueTransformer.transform(_ddmFormFieldValue);

		Mockito.verify(
			_value, Mockito.never()
		).getString(
			Matchers.any(Locale.class)
		);
	}

	@Test
	public void testTransformSelectDDMFormFieldValues() throws Exception {
		UpgradeDynamicDataMapping.SelectDDMFormFieldValueTransformer
			selectDDMFormFieldValueTransformer =
				_getSelectDDMFormFieldValueTransformer();

		Mockito.when(
			_jsonFactory.createJSONArray(Matchers.any(String.class))
		).thenReturn(
			new JSONArrayImpl()
		);

		Mockito.when(
			_ddmFormFieldValue.getValue()
		).thenReturn(
			_value
		);

		Mockito.when(
			_value.getAvailableLocales()
		).thenReturn(
			_getAvailableLocales()
		);

		Mockito.when(
			_value.getString(Matchers.any(Locale.class))
		).thenReturn(
			"value"
		);

		selectDDMFormFieldValueTransformer.transform(_ddmFormFieldValue);

		Mockito.verify(
			_value, VerificationModeFactory.atLeastOnce()
		).addString(
			Matchers.any(Locale.class), Matchers.anyString()
		);
	}

	@Test
	public void testTransformSelectDDMFormFieldValuesWithNullValue()
		throws Exception {

		UpgradeDynamicDataMapping.SelectDDMFormFieldValueTransformer
			selectDDMFormFieldValueTransformer =
				_getSelectDDMFormFieldValueTransformer();

		Mockito.when(
			_ddmFormFieldValue.getValue()
		).thenReturn(
			null
		);

		selectDDMFormFieldValueTransformer.transform(_ddmFormFieldValue);

		Mockito.verify(
			_value, Mockito.never()
		).getString(
			Matchers.any(Locale.class)
		);
	}

	private Set<Locale> _getAvailableLocales() {
		Locale locale = new Locale("pt-BR");

		Set<Locale> locales = new HashSet<>();

		locales.add(locale);

		return locales;
	}

	private UpgradeDynamicDataMapping.RadioDDMFormFieldValueTransformer
		_getRadioDDMFormFieldValueTransformer() {

		UpgradeDynamicDataMapping upgradeDynamicDataMapping =
			_getUpgradeDynamicDataMapping();

		return upgradeDynamicDataMapping.
			new RadioDDMFormFieldValueTransformer();
	}

	private UpgradeDynamicDataMapping.SelectDDMFormFieldValueTransformer
		_getSelectDDMFormFieldValueTransformer() {

		UpgradeDynamicDataMapping upgradeDynamicDataMapping =
			_getUpgradeDynamicDataMapping();

		return upgradeDynamicDataMapping.
			new SelectDDMFormFieldValueTransformer();
	}

	private UpgradeDynamicDataMapping _getUpgradeDynamicDataMapping() {
		return new UpgradeDynamicDataMapping(
			null, null, null, null, _jsonFactory);
	}

	@Mock
	private DDMFormFieldValue _ddmFormFieldValue;

	@Mock
	private JSONFactory _jsonFactory;

	@Mock
	private Value _value;

}