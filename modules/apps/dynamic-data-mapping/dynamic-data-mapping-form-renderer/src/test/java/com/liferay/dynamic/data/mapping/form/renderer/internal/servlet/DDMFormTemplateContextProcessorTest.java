/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.renderer.internal.servlet;

import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidation;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldValidationExpression;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Carolina Barbosa
 */
@PrepareForTest(LanguageUtil.class)
@RunWith(PowerMockRunner.class)
public class DDMFormTemplateContextProcessorTest extends PowerMockito {

	@Before
	public void setUp() {
		_setUpJSONFactoryUtil();
		_setUpLanguageUtil();

		_ddmFormTemplateContextProcessor = new DDMFormTemplateContextProcessor(
			JSONUtil.put(
				"pages", JSONFactoryUtil.createJSONArray()
			).put(
				"rules", JSONFactoryUtil.createJSONArray()
			),
			LocaleUtil.toLanguageId(_defaultLocale));
	}

	@Test
	public void testGetDDMFormCustomField() {
		DDMFormField ddmFormField =
			_ddmFormTemplateContextProcessor.getDDMFormField(
				JSONUtil.put(
					"customProperty", 10.5
				).put(
					"fieldName", "Custom12345678"
				).put(
					"type", "custom_field"
				));

		Assert.assertEquals("Custom12345678", ddmFormField.getName());
		Assert.assertEquals(10.5, ddmFormField.getProperty("customProperty"));
		Assert.assertEquals("custom_field", ddmFormField.getType());
	}

	@Test
	public void testGetDDMFormMultipleSelectionField() {
		DDMFormField ddmFormField =
			_ddmFormTemplateContextProcessor.getDDMFormField(
				JSONUtil.put(
					"fieldName", "MultipleSelection12345678"
				).put(
					"inline", true
				).put(
					"options",
					JSONUtil.putAll(
						JSONUtil.put(
							"label", "Option 1"
						).put(
							"value", "OptionValue1"
						),
						JSONUtil.put(
							"label", "Option 2"
						).put(
							"value", "OptionValue2"
						))
				).put(
					"showAsSwitcher", false
				).put(
					"type", "checkbox_multiple"
				));

		DDMFormFieldOptions ddmFormFieldOptions =
			ddmFormField.getDDMFormFieldOptions();

		Set<String> optionsValues = ddmFormFieldOptions.getOptionsValues();

		Assert.assertEquals(optionsValues.toString(), 2, optionsValues.size());

		Assert.assertTrue(
			optionsValues.toString(), optionsValues.contains("OptionValue1"));
		Assert.assertTrue(
			optionsValues.toString(), optionsValues.contains("OptionValue2"));

		LocalizedValue optionValue1Labels = ddmFormFieldOptions.getOptionLabels(
			"OptionValue1");

		Assert.assertEquals(
			"Option 1", optionValue1Labels.getString(_defaultLocale));

		LocalizedValue optionValue2Labels = ddmFormFieldOptions.getOptionLabels(
			"OptionValue2");

		Assert.assertEquals(
			"Option 2", optionValue2Labels.getString(_defaultLocale));

		Assert.assertEquals(
			"MultipleSelection12345678", ddmFormField.getName());
		Assert.assertTrue((boolean)ddmFormField.getProperty("inline"));
		Assert.assertFalse((boolean)ddmFormField.getProperty("showAsSwitcher"));
		Assert.assertEquals("checkbox_multiple", ddmFormField.getType());
	}

	@Test
	public void testGetDDMFormTextField() {
		DDMFormField ddmFormField =
			_ddmFormTemplateContextProcessor.getDDMFormField(
				JSONUtil.put(
					"dataType", "string"
				).put(
					"fieldName", "Text12345678"
				).put(
					"label", "Text Field"
				).put(
					"localizable", true
				).put(
					"placeholder", "Placeholder"
				).put(
					"readOnly", false
				).put(
					"repeatable", true
				).put(
					"required", false
				).put(
					"tooltip", "Tooltip"
				).put(
					"type", "text"
				).put(
					"valid", false
				).put(
					"validation",
					JSONUtil.put(
						"errorMessage", "This field must not contain Test."
					).put(
						"expression",
						JSONUtil.put(
							"name", "notContains"
						).put(
							"value",
							"NOT(contains(Text12345678, \"{parameter}\"))"
						)
					).put(
						"parameter", "Test"
					)
				).put(
					"visibilityExpression", ""
				));

		Assert.assertEquals("string", ddmFormField.getDataType());

		DDMFormFieldValidation ddmFormFieldValidation =
			new DDMFormFieldValidation();

		ddmFormFieldValidation.setDDMFormFieldValidationExpression(
			new DDMFormFieldValidationExpression() {
				{
					setName("notContains");
					setValue("NOT(contains(Text12345678, \"{parameter}\"))");
				}
			});
		ddmFormFieldValidation.setErrorMessageLocalizedValue(
			DDMFormValuesTestUtil.createLocalizedValue(
				"This field must not contain Test.", _defaultLocale));
		ddmFormFieldValidation.setParameterLocalizedValue(
			DDMFormValuesTestUtil.createLocalizedValue("Test", _defaultLocale));

		Assert.assertEquals(
			ddmFormFieldValidation, ddmFormField.getDDMFormFieldValidation());

		Assert.assertEquals(
			_getLocalizedValue("Text Field"), ddmFormField.getLabel());
		Assert.assertEquals("Text12345678", ddmFormField.getName());
		Assert.assertEquals(
			_getLocalizedValue("Placeholder"),
			ddmFormField.getProperty("placeholder"));
		Assert.assertEquals(
			_getLocalizedValue("Tooltip"), ddmFormField.getProperty("tooltip"));
		Assert.assertFalse((boolean)ddmFormField.getProperty("valid"));
		Assert.assertEquals("text", ddmFormField.getType());
		Assert.assertEquals("", ddmFormField.getVisibilityExpression());
		Assert.assertTrue(ddmFormField.isLocalizable());
		Assert.assertFalse(ddmFormField.isReadOnly());
		Assert.assertTrue(ddmFormField.isRepeatable());
		Assert.assertFalse(ddmFormField.isRequired());
	}

	@Test
	public void testGetDDMFormUploadField() {
		long folderId = RandomTestUtil.randomLong();

		String guestUploadURL = RandomTestUtil.randomString();

		DDMFormField ddmFormField =
			_ddmFormTemplateContextProcessor.getDDMFormField(
				JSONUtil.put(
					"allowGuestUsers", true
				).put(
					"fieldName", "Upload12345678"
				).put(
					"folderId", folderId
				).put(
					"guestUploadURL", guestUploadURL
				).put(
					"maximumRepetitions", 7
				).put(
					"maximumSubmissionLimitReached", false
				).put(
					"type", "document_library"
				));

		Assert.assertEquals("Upload12345678", ddmFormField.getName());
		Assert.assertTrue((boolean)ddmFormField.getProperty("allowGuestUsers"));
		Assert.assertEquals(
			folderId, GetterUtil.getLong(ddmFormField.getProperty("folderId")));
		Assert.assertEquals(
			guestUploadURL, ddmFormField.getProperty("guestUploadURL"));
		Assert.assertEquals(7, ddmFormField.getProperty("maximumRepetitions"));
		Assert.assertFalse(
			(boolean)ddmFormField.getProperty("maximumSubmissionLimitReached"));
		Assert.assertTrue((boolean)ddmFormField.getProperty("valid"));
		Assert.assertEquals("document_library", ddmFormField.getType());
		Assert.assertFalse(ddmFormField.isLocalizable());
		Assert.assertFalse(ddmFormField.isReadOnly());
		Assert.assertFalse(ddmFormField.isRepeatable());
		Assert.assertFalse(ddmFormField.isRequired());
	}

	private LocalizedValue _getLocalizedValue(String value) {
		LocalizedValue localizedValue = new LocalizedValue(_defaultLocale);

		localizedValue.addString(_defaultLocale, value);

		return localizedValue;
	}

	private void _setUpJSONFactoryUtil() {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());
	}

	private void _setUpLanguageUtil() {
		mockStatic(LanguageUtil.class);

		when(
			LanguageUtil.isAvailableLocale(Matchers.any(Locale.class))
		).thenReturn(
			Boolean.TRUE
		);
	}

	private DDMFormTemplateContextProcessor _ddmFormTemplateContextProcessor;
	private final Locale _defaultLocale = LocaleUtil.US;

}