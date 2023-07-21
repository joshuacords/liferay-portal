/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.radio;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.json.JSONFactoryImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Rafael Praxedes
 */
@RunWith(PowerMockRunner.class)
public class RadioDDMFormFieldValueRequestParameterRetrieverTest
	extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		_radioDDMFormFieldValueRequestParameterRetriever =
			new RadioDDMFormFieldValueRequestParameterRetriever();

		field(
			RadioDDMFormFieldValueRequestParameterRetriever.class,
			"_jsonFactory"
		).set(
			_radioDDMFormFieldValueRequestParameterRetriever,
			new JSONFactoryImpl()
		);
	}

	@Test
	public void testEmptySubmitWithoutPredefinedValue() {
		String fieldValue =
			_radioDDMFormFieldValueRequestParameterRetriever.get(
				new MockHttpServletRequest(), "radio0", "[]");

		Assert.assertEquals(StringPool.BLANK, fieldValue);
	}

	private RadioDDMFormFieldValueRequestParameterRetriever
		_radioDDMFormFieldValueRequestParameterRetriever;

}