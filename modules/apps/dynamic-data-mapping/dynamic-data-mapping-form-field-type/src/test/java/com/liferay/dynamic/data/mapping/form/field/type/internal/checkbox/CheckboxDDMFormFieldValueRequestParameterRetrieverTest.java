/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.checkbox;

import com.liferay.petra.string.StringPool;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Marcellus Tavares
 */
@RunWith(PowerMockRunner.class)
public class CheckboxDDMFormFieldValueRequestParameterRetrieverTest {

	@Test
	public void testGetRequestParameterValueFalse() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		String expectedParameterValue = StringPool.FALSE;

		request.addParameter("ddmFormFieldCheckbox", expectedParameterValue);

		String defaultParameterValue = StringPool.TRUE;

		String actualParameterValue =
			_checkboxDDMFormFieldValueRequestParameterRetriever.get(
				request, "ddmFormFieldCheckbox", defaultParameterValue);

		Assert.assertEquals(expectedParameterValue, actualParameterValue);
	}

	@Test
	public void testGetRequestParameterValueTrue() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		String expectedParameterValue = StringPool.TRUE;

		request.addParameter("ddmFormFieldCheckbox", expectedParameterValue);

		String defaultParameterValue = StringPool.FALSE;

		String actualParameterValue =
			_checkboxDDMFormFieldValueRequestParameterRetriever.get(
				request, "ddmFormFieldCheckbox", defaultParameterValue);

		Assert.assertEquals(expectedParameterValue, actualParameterValue);
	}

	@Test
	public void testGetValueWithNullRequestParameter() {
		MockHttpServletRequest request = new MockHttpServletRequest();

		String defaultParameterValue = StringPool.TRUE;

		String parameterValue =
			_checkboxDDMFormFieldValueRequestParameterRetriever.get(
				request, "ddmFormFieldCheckbox", defaultParameterValue);

		Assert.assertEquals(parameterValue, defaultParameterValue);
	}

	private final CheckboxDDMFormFieldValueRequestParameterRetriever
		_checkboxDDMFormFieldValueRequestParameterRetriever =
			new CheckboxDDMFormFieldValueRequestParameterRetriever();

}