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

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Leonardo Barros
 */
public class SetPropertyFunctionTest extends PowerMockito {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testApply() {
		SetPropertyFunction<Boolean> setPropertyFunction =
			new SetMultipleFunction();

		DefaultDDMExpressionObserver defaultDDMExpressionObserver =
			new DefaultDDMExpressionObserver();

		DefaultDDMExpressionObserver spy = spy(defaultDDMExpressionObserver);

		setPropertyFunction.setDDMExpressionObserver(spy);

		Boolean result = setPropertyFunction.apply("field", true);

		ArgumentCaptor<UpdateFieldPropertyRequest> argumentCaptor =
			ArgumentCaptor.forClass(UpdateFieldPropertyRequest.class);

		Mockito.verify(
			spy, Mockito.times(1)
		).updateFieldProperty(
			argumentCaptor.capture()
		);

		Assert.assertTrue(result);

		UpdateFieldPropertyRequest updateFieldPropertyRequest =
			argumentCaptor.getValue();

		Map<String, Object> properties =
			updateFieldPropertyRequest.getProperties();

		Assert.assertEquals("field", updateFieldPropertyRequest.getField());

		Assert.assertTrue(properties.containsKey("multiple"));
		Assert.assertTrue((boolean)properties.get("multiple"));
	}

	@Test
	public void testNullObserver() {
		SetPropertyFunction<Boolean> setPropertyFunction =
			new SetEnabledFunction();

		Assert.assertFalse(setPropertyFunction.apply("field", true));
	}

}