/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression.internal;

import com.liferay.dynamic.data.mapping.expression.CreateExpressionRequest;
import com.liferay.dynamic.data.mapping.expression.DDMExpression;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunctionFactory;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunctionTracker;
import com.liferay.dynamic.data.mapping.expression.internal.functions.PowFunction;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Leonardo Barros
 */
@RunWith(MockitoJUnitRunner.class)
public class DDMExpressionFactoryImplTest extends PowerMockito {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testCreateDDMExpression() throws Exception {
		DDMExpressionFactoryImpl ddmExpressionFactoryImpl =
			new DDMExpressionFactoryImpl();

		ddmExpressionFactoryImpl.ddmExpressionFunctionTracker =
			_ddmExpressionFunctionTracker;

		Map<String, DDMExpressionFunctionFactory> factories =
			new HashMap<String, DDMExpressionFunctionFactory>() {
				{
					put("pow", () -> new PowFunction());
				}
			};

		when(
			_ddmExpressionFunctionTracker.getDDMExpressionFunctionFactories(
				Matchers.any())
		).thenReturn(
			factories
		);

		CreateExpressionRequest.Builder builder =
			CreateExpressionRequest.Builder.newBuilder("pow(2,3)");

		DDMExpression<BigDecimal> ddmExpression =
			ddmExpressionFactoryImpl.createExpression(builder.build());

		BigDecimal actual = ddmExpression.evaluate();

		Assert.assertEquals(0, actual.compareTo(new BigDecimal(8)));
	}

	@Mock
	private DDMExpressionFunctionTracker _ddmExpressionFunctionTracker;

}