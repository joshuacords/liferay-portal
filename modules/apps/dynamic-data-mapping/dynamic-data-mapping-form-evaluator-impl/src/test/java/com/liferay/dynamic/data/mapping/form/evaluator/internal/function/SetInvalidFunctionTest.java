/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.dynamic.data.mapping.expression.UpdateFieldPropertyRequest;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Leonardo Barros
 */
@RunWith(MockitoJUnitRunner.class)
public class SetInvalidFunctionTest extends PowerMockito {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testApply() {
		DefaultDDMExpressionObserver defaultDDMExpressionObserver =
			new DefaultDDMExpressionObserver();

		DefaultDDMExpressionObserver spy = spy(defaultDDMExpressionObserver);

		SetInvalidFunction setInvalidFunction = new SetInvalidFunction();

		setInvalidFunction.setDDMExpressionObserver(spy);

		Boolean result = setInvalidFunction.apply(
			"contact", "Custom error message");

		ArgumentCaptor<UpdateFieldPropertyRequest> argumentCaptor =
			ArgumentCaptor.forClass(UpdateFieldPropertyRequest.class);

		Mockito.verify(
			spy, Mockito.times(1)
		).updateFieldProperty(
			argumentCaptor.capture()
		);

		UpdateFieldPropertyRequest updateFieldPropertyRequest =
			argumentCaptor.getValue();

		Map<String, Object> properties =
			updateFieldPropertyRequest.getProperties();

		Assert.assertEquals("contact", updateFieldPropertyRequest.getField());

		Assert.assertTrue("valid", properties.containsKey("valid"));
		Assert.assertEquals(
			"Custom error message",
			updateFieldPropertyRequest.getPropertyOptional(
				"errorMessage"
			).get());

		Assert.assertFalse((boolean)properties.get("valid"));

		Assert.assertTrue(result);
	}

	@Test
	public void testNullObserver() {
		SetInvalidFunction setInvalidFunction = new SetInvalidFunction();

		Boolean result = setInvalidFunction.apply("field", "errorMessage");

		Assert.assertFalse(result);
	}

}