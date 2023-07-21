/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.checkbox.multiple;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.PropsImpl;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Marcela Cunha
 */
public class CheckboxMultipleDDMFormFieldValueRequestParameterRetrieverTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() throws Exception {
		PropsUtil.setProps(new PropsImpl());
	}

	@Before
	public void setUp() {
		_checkboxMultipleDDMFormFieldValueRequestParameterRetriever =
			new CheckboxMultipleDDMFormFieldValueRequestParameterRetriever();

		_checkboxMultipleDDMFormFieldValueRequestParameterRetriever.
			jsonFactory = _jsonFactory;
	}

	@Test
	public void testCompletedSubmission() {
		String expectedResult = createJSONArrayString("Option 2");

		String defaultDDMFormFieldParameterValue = createJSONArrayString(
			"Option 1");

		String actualResult =
			_checkboxMultipleDDMFormFieldValueRequestParameterRetriever.get(
				createHttpServletRequest("Option 2"),
				_CHECKBOX_MULTIPLE_SUBMISSION,
				defaultDDMFormFieldParameterValue);

		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testEmptySubmission() {
		String expectedResult = "[]";

		String defaultDDMFormFieldParameterValue = createJSONArrayString(
			"Option 1");

		String actualResult =
			_checkboxMultipleDDMFormFieldValueRequestParameterRetriever.get(
				createHttpServletRequest(), _CHECKBOX_MULTIPLE_SUBMISSION,
				defaultDDMFormFieldParameterValue);

		Assert.assertEquals(expectedResult, actualResult);
	}

	protected MockHttpServletRequest createHttpServletRequest() {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setLifecycleAction(true);

		mockHttpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);

		return mockHttpServletRequest;
	}

	protected HttpServletRequest createHttpServletRequest(String... strings) {
		MockHttpServletRequest mockHttpServletRequest =
			createHttpServletRequest();

		mockHttpServletRequest.addParameter(
			_CHECKBOX_MULTIPLE_SUBMISSION, strings);

		return mockHttpServletRequest;
	}

	protected String createJSONArrayString(String... strings) {
		JSONArray jsonArray = _jsonFactory.createJSONArray();

		for (String string : strings) {
			jsonArray.put(string);
		}

		return jsonArray.toString();
	}

	private static final String _CHECKBOX_MULTIPLE_SUBMISSION =
		"checkBoxSubmissionResult";

	private CheckboxMultipleDDMFormFieldValueRequestParameterRetriever
		_checkboxMultipleDDMFormFieldValueRequestParameterRetriever;
	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

}