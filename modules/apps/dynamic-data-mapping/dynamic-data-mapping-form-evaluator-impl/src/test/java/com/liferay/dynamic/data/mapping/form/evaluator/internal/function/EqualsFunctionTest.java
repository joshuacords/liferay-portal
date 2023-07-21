/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Leonardo Barros
 */
public class EqualsFunctionTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testApplyFalse1() {
		EqualsFunction equalsFunction = new EqualsFunction();

		Assert.assertFalse(equalsFunction.apply("FORMS", "forms"));
	}

	@Test
	public void testApplyFalse2() {
		EqualsFunction equalsFunction = new EqualsFunction();

		Assert.assertFalse(equalsFunction.apply(null, "forms"));
	}

	@Test
	public void testApplyTrue1() {
		EqualsFunction equalsFunction = new EqualsFunction();

		Assert.assertTrue(equalsFunction.apply("1", new BigDecimal(1)));
	}

	@Test
	public void testApplyTrue2() {
		EqualsFunction equalsFunction = new EqualsFunction();

		Assert.assertTrue(equalsFunction.apply("forms", "forms"));
	}

}