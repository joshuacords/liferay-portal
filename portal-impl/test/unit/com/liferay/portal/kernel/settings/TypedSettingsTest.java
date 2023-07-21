/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.settings;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.LocalizationImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
public class TypedSettingsTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	public TypedSettingsTest() {
		LocalizationUtil localizationUtil = new LocalizationUtil();

		localizationUtil.setLocalization(new LocalizationImpl());

		ModifiableSettings modifiableSettings = new MemorySettings();

		modifiableSettings.setValue(_KEY, "valueDefault");
		modifiableSettings.setValue(_KEY + "_en_GB", "value_en_GB");
		modifiableSettings.setValue(_KEY + "_en_US", "value_en_US");
		modifiableSettings.setValue(_KEY + "_es_ES", "value_es_ES");

		_typedSettings = new TypedSettings(
			modifiableSettings, _availableLocales);
	}

	@Test
	public void testGetLocalizedValue() {
		LocalizedValuesMap localizedValuesMap =
			_typedSettings.getLocalizedValuesMap(_KEY);

		Assert.assertEquals(
			"value_en_GB", localizedValuesMap.get(LocaleUtil.UK));
		Assert.assertEquals(
			"value_en_US", localizedValuesMap.get(LocaleUtil.US));
		Assert.assertEquals(
			"value_es_ES", localizedValuesMap.get(LocaleUtil.SPAIN));
		Assert.assertEquals(
			"valueDefault", localizedValuesMap.get(LocaleUtil.BRAZIL));
	}

	private static final String _KEY = "key";

	private static final List<Locale> _availableLocales = Arrays.asList(
		LocaleUtil.SPAIN, LocaleUtil.UK, LocaleUtil.US);

	private final TypedSettings _typedSettings;

}