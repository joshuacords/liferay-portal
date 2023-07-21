/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.renderer.internal;

import com.liferay.dynamic.data.mapping.form.evaluator.DDMFormEvaluatorFieldContextKey;
import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTemplateContextContributor;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesTracker;
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderingContext;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageConstants;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * @author Marcellus Tavares
 */
public class DDMFormFieldTemplateContextFactoryTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		setUpDDMFormTemplateContextFactoryUtil();
		setUpLanguageUtil();
	}

	@Test
	public void testNotReadOnlyTextFieldAndReadOnlyForm() {

		// Dynamic data mapping form

		DDMForm ddmForm = DDMFormTestUtil.createDDMForm();

		DDMFormField ddmFormField = DDMFormTestUtil.createTextDDMFormField(
			"Field1", false, false, false);

		boolean readOnly = false;

		ddmFormField.setReadOnly(readOnly);

		ddmForm.addDDMFormField(ddmFormField);

		// Dynamic data mapping form field evaluation

		String instanceId = StringUtil.randomString();

		Map<String, Object> changedProperties =
			HashMapBuilder.<String, Object>put(
				"readOnly", readOnly
			).put(
				"visible", true
			).build();

		Map<DDMFormEvaluatorFieldContextKey, Map<String, Object>>
			ddmFormFieldsPropertyChanges =
				HashMapBuilder.
					<DDMFormEvaluatorFieldContextKey, Map<String, Object>>put(
						new DDMFormEvaluatorFieldContextKey(
							"Field1", instanceId),
						changedProperties
					).build();

		// Dynamic data mapping form values

		List<DDMFormFieldValue> ddmFormFieldValues = new ArrayList<>();

		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"Field1", "Value 1");

		ddmFormFieldValue.setInstanceId(instanceId);

		ddmFormFieldValues.add(ddmFormFieldValue);

		DDMFormFieldTemplateContextFactory ddmFormFieldTemplateContextFactory =
			createDDMFormFieldTemplateContextFactory(
				ddmForm, ddmFormFieldsPropertyChanges, ddmFormFieldValues, true,
				getTextDDMFormFieldRenderer(),
				getTextDDMFormFieldTemplateContextContributor());

		List<Object> fields = ddmFormFieldTemplateContextFactory.create();

		Assert.assertEquals(fields.toString(), 1, fields.size());

		Map<String, Object> fieldTemplateContext =
			(Map<String, Object>)fields.get(0);

		Assert.assertTrue(MapUtil.getBoolean(fieldTemplateContext, "readOnly"));
	}

	@Test
	public void testReadOnlyTextFieldAndNotReadOnlyForm() {

		// Dynamic data mapping form

		DDMForm ddmForm = DDMFormTestUtil.createDDMForm();

		DDMFormField ddmFormField = DDMFormTestUtil.createTextDDMFormField(
			"Field1", false, false, true);

		boolean readOnly = true;

		ddmFormField.setReadOnly(readOnly);

		ddmForm.addDDMFormField(ddmFormField);

		// Dynamic data mapping form field evaluation

		String instanceId = StringUtil.randomString();

		Map<String, Object> changedProperties =
			HashMapBuilder.<String, Object>put(
				"readOnly", readOnly
			).put(
				"visible", true
			).build();

		Map<DDMFormEvaluatorFieldContextKey, Map<String, Object>>
			ddmFormFieldsPropertyChanges =
				HashMapBuilder.
					<DDMFormEvaluatorFieldContextKey, Map<String, Object>>put(
						new DDMFormEvaluatorFieldContextKey(
							"Field1", instanceId),
						changedProperties
					).build();

		// Dynamic data mapping form values

		List<DDMFormFieldValue> ddmFormFieldValues = new ArrayList<>();

		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"Field1", "Value 1");

		ddmFormFieldValue.setInstanceId(instanceId);

		ddmFormFieldValues.add(ddmFormFieldValue);

		DDMFormFieldTemplateContextFactory ddmFormFieldTemplateContextFactory =
			createDDMFormFieldTemplateContextFactory(
				ddmForm, ddmFormFieldsPropertyChanges, ddmFormFieldValues,
				false, getTextDDMFormFieldRenderer(),
				getTextDDMFormFieldTemplateContextContributor());

		List<Object> fields = ddmFormFieldTemplateContextFactory.create();

		Assert.assertEquals(fields.toString(), 1, fields.size());

		Map<String, Object> fieldTemplateContext =
			(Map<String, Object>)fields.get(0);

		Assert.assertTrue(MapUtil.getBoolean(fieldTemplateContext, "readOnly"));
	}

	@Test
	public void testTextField() {

		// Dynamic data mapping form

		DDMForm ddmForm = DDMFormTestUtil.createDDMForm();

		boolean required = true;

		DDMFormField ddmFormField = DDMFormTestUtil.createTextDDMFormField(
			"Field1", false, false, required);

		ddmFormField.setLabel(
			DDMFormValuesTestUtil.createLocalizedValue("Field 1", _LOCALE));
		ddmFormField.setReadOnly(false);
		ddmFormField.setTip(
			DDMFormValuesTestUtil.createLocalizedValue(
				"This is a tip.", _LOCALE));

		ddmFormField.setProperty("displayStyle", "singleline");

		ddmForm.addDDMFormField(ddmFormField);

		// Dynamic data mapping form field evaluation

		String instanceId = StringUtil.randomString();

		Map<String, Object> changedProperties =
			HashMapBuilder.<String, Object>put(
				"required", true
			).put(
				"valid", true
			).put(
				"visible", true
			).build();

		Map<DDMFormEvaluatorFieldContextKey, Map<String, Object>>
			ddmFormFieldsPropertyChanges =
				HashMapBuilder.
					<DDMFormEvaluatorFieldContextKey, Map<String, Object>>put(
						new DDMFormEvaluatorFieldContextKey(
							"Field1", instanceId),
						changedProperties
					).build();

		// Dynamic data mapping form values

		List<DDMFormFieldValue> ddmFormFieldValues = new ArrayList<>();

		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				"Field1", "Value 1");

		ddmFormFieldValue.setInstanceId(instanceId);

		ddmFormFieldValues.add(ddmFormFieldValue);

		DDMFormFieldTemplateContextFactory ddmFormFieldTemplateContextFactory =
			createDDMFormFieldTemplateContextFactory(
				ddmForm, ddmFormFieldsPropertyChanges, ddmFormFieldValues,
				false, getTextDDMFormFieldRenderer(),
				getTextDDMFormFieldTemplateContextContributor());

		List<Object> fields = ddmFormFieldTemplateContextFactory.create();

		Assert.assertEquals(fields.toString(), 1, fields.size());

		Map<String, Object> fieldTemplateContext =
			(Map<String, Object>)fields.get(0);

		Assert.assertEquals(
			"singleline",
			MapUtil.getString(fieldTemplateContext, "displayStyle"));
		Assert.assertEquals(
			"Field 1", MapUtil.getString(fieldTemplateContext, "label"));
		Assert.assertFalse(
			MapUtil.getBoolean(fieldTemplateContext, "readOnly"));
		Assert.assertFalse(
			MapUtil.getBoolean(fieldTemplateContext, "repeatable"));
		Assert.assertTrue(MapUtil.getBoolean(fieldTemplateContext, "required"));
		Assert.assertEquals(
			"This is a tip.", MapUtil.getString(fieldTemplateContext, "tip"));
		Assert.assertTrue(MapUtil.getBoolean(fieldTemplateContext, "valid"));
		Assert.assertEquals(
			StringPool.BLANK,
			MapUtil.getString(fieldTemplateContext, "validationErrorMessage"));
		Assert.assertEquals(
			"Value 1", MapUtil.getString(fieldTemplateContext, "value"));
		Assert.assertTrue(MapUtil.getBoolean(fieldTemplateContext, "visible"));

		String expectedName = String.format(
			_FIELD_NAME_FORMAT, "Field1", instanceId, 0, _LOCALE.toString());

		Assert.assertEquals(
			expectedName, MapUtil.getString(fieldTemplateContext, "name"));
	}

	protected DDMFormFieldTemplateContextFactory
		createDDMFormFieldTemplateContextFactory(
			DDMForm ddmForm,
			Map<DDMFormEvaluatorFieldContextKey, Map<String, Object>>
				ddmFormFieldsPropertyChanges,
			List<DDMFormFieldValue> ddmFormFieldValues, boolean ddmFormReadOnly,
			DDMFormFieldRenderer ddmFormFieldRenderer,
			DDMFormFieldTemplateContextContributor
				ddmFormFieldTemplateContextContributor) {

		DDMFormRenderingContext ddmFormRenderingContext =
			new DDMFormRenderingContext();

		ddmFormRenderingContext.setHttpServletRequest(_httpServletRequest);
		ddmFormRenderingContext.setLocale(_LOCALE);
		ddmFormRenderingContext.setPortletNamespace(_PORTLET_NAMESPACE);
		ddmFormRenderingContext.setReadOnly(ddmFormReadOnly);

		DDMFormFieldTemplateContextFactory ddmFormFieldTemplateContextFactory =
			new DDMFormFieldTemplateContextFactory(
				ddmForm.getDDMFormFieldsMap(true), ddmFormFieldsPropertyChanges,
				ddmFormFieldValues, ddmFormRenderingContext, true);

		DDMFormFieldTypeServicesTracker ddmFormFieldTypeServicesTracker =
			mockDDMFormFieldTypeServicesTracker(
				ddmFormFieldRenderer, ddmFormFieldTemplateContextContributor);

		ddmFormFieldTemplateContextFactory.setDDMFormFieldTypeServicesTracker(
			ddmFormFieldTypeServicesTracker);

		return ddmFormFieldTemplateContextFactory;
	}

	protected DDMFormFieldRenderer getTextDDMFormFieldRenderer() {
		return new BaseDDMFormFieldRenderer() {

			public String getTemplateLanguage() {
				return null;
			}

			public String getTemplateNamespace() {
				return "ddm.text";
			}

			public TemplateResource getTemplateResource() {
				return null;
			}

		};
	}

	protected DDMFormFieldTemplateContextContributor
		getTextDDMFormFieldTemplateContextContributor() {

		return new DDMFormFieldTemplateContextContributor() {

			public Map<String, Object> getParameters(
				DDMFormField ddmFormField,
				DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

				return HashMapBuilder.<String, Object>put(
					"displayStyle", ddmFormField.getProperty("displayStyle")
				).build();
			}

		};
	}

	protected DDMFormFieldTypeServicesTracker
		mockDDMFormFieldTypeServicesTracker(
			DDMFormFieldRenderer ddmFormFieldRenderer,
			DDMFormFieldTemplateContextContributor
				ddmFormFieldTemplateContextContributor) {

		DDMFormFieldTypeServicesTracker ddmFormFieldTypeServicesTracker =
			Mockito.mock(DDMFormFieldTypeServicesTracker.class);

		Mockito.when(
			ddmFormFieldTypeServicesTracker.getDDMFormFieldRenderer(
				Matchers.anyString())
		).thenReturn(
			ddmFormFieldRenderer
		);

		Mockito.when(
			ddmFormFieldTypeServicesTracker.
				getDDMFormFieldTemplateContextContributor(Matchers.anyString())
		).thenReturn(
			ddmFormFieldTemplateContextContributor
		);

		return ddmFormFieldTypeServicesTracker;
	}

	protected void setUpDDMFormTemplateContextFactoryUtil() {
		_httpServletRequest = Mockito.mock(HttpServletRequest.class);

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setPathThemeImages(StringPool.BLANK);

		Mockito.when(
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY)
		).thenReturn(
			themeDisplay
		);
	}

	protected void setUpLanguageUtil() {
		Language language = Mockito.mock(Language.class);

		whenLanguageGet(
			language, LocaleUtil.US, LanguageConstants.KEY_DIR, "ltr");

		LanguageUtil languageUtil = new LanguageUtil();

		languageUtil.setLanguage(language);
	}

	protected void whenLanguageGet(
		Language language, Locale locale, String key, String returnValue) {

		Mockito.when(
			language.get(Matchers.eq(locale), Matchers.eq(key))
		).thenReturn(
			returnValue
		);
	}

	private static final String _FIELD_NAME_FORMAT =
		"_PORTLET_NAMESPACE_ddm$$%s$%s$%d$$%s";

	private static final Locale _LOCALE = LocaleUtil.US;

	private static final String _PORTLET_NAMESPACE = "_PORTLET_NAMESPACE_";

	private HttpServletRequest _httpServletRequest;

}