/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.checkbox;

import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Renato Rego
 */
@PrepareForTest(LanguageUtil.class)
@RunWith(PowerMockRunner.class)
public class CheckboxDDMFormFieldValueRendererTest extends PowerMockito {

	@Before
	public void setUp() {
		setUpLanguageUtil();
	}

	@Test
	public void testRender() throws Exception {
		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createDDMFormFieldValue(
				"Checkbox", new UnlocalizedValue("true"));

		CheckboxDDMFormFieldValueRenderer checkboxDDMFormFieldValueRenderer =
			createCheckboxDDMFormFieldValueRenderer();

		String expectedCheckboxRenderedValue = LanguageUtil.get(
			LocaleUtil.US, "yes");

		Assert.assertEquals(
			expectedCheckboxRenderedValue,
			checkboxDDMFormFieldValueRenderer.render(
				ddmFormFieldValue, LocaleUtil.US));

		ddmFormFieldValue.setValue(new UnlocalizedValue("false"));

		expectedCheckboxRenderedValue = LanguageUtil.get(LocaleUtil.US, "no");

		Assert.assertEquals(
			expectedCheckboxRenderedValue,
			checkboxDDMFormFieldValueRenderer.render(
				ddmFormFieldValue, LocaleUtil.US));
	}

	protected CheckboxDDMFormFieldValueRenderer
			createCheckboxDDMFormFieldValueRenderer()
		throws Exception {

		CheckboxDDMFormFieldValueRenderer checkboxDDMFormFieldValueRenderer =
			new CheckboxDDMFormFieldValueRenderer();

		checkboxDDMFormFieldValueRenderer.checkboxDDMFormFieldValueAccessor =
			new CheckboxDDMFormFieldValueAccessor();

		return checkboxDDMFormFieldValueRenderer;
	}

	protected void setUpLanguageUtil() {
		whenLanguageGet(LocaleUtil.US, "no", "No");
		whenLanguageGet(LocaleUtil.US, "yes", "Yes");

		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(language);
	}

	protected void whenLanguageGet(
		Locale locale, String key, String returnValue) {

		when(
			language.get(Matchers.eq(locale), Matchers.eq(key))
		).thenReturn(
			returnValue
		);
	}

	@Mock
	protected Language language;

}