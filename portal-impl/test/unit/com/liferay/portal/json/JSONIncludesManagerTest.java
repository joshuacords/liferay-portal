/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.json;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Igor Spasic
 */
public class JSONIncludesManagerTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());

		JSONInit.init();
	}

	@Test
	public void testExtendsOne() {
		ExtendsOne extendsOne = new ExtendsOne();

		String json = JSONFactoryUtil.looseSerialize(extendsOne);

		Assert.assertEquals("{\"ftwo\":173}", json);
	}

	@Test
	public void testExtendsTwo() {
		ExtendsTwo extendsTwo = new ExtendsTwo();

		String json = JSONFactoryUtil.looseSerialize(extendsTwo);

		Assert.assertEquals("{\"ftwo\":173}", json);
	}

	@Test
	public void testFour() {
		Four four = new Four();

		String json = JSONFactoryUtil.looseSerialize(four);

		Assert.assertTrue(json, json.contains("nuMber"));
		Assert.assertTrue(json, json.contains("vaLue"));
	}

	@Test
	public void testOne() {
		One one = new One();

		String json = JSONFactoryUtil.looseSerialize(one);

		Assert.assertEquals("{\"fone\":\"string\",\"ftwo\":173}", json);
	}

	@Test
	public void testThree() {
		Three three = new Three();

		String json = JSONFactoryUtil.looseSerialize(three);

		Assert.assertEquals("{\"flag\":true}", json);
	}

	@Test
	public void testTwo() {
		Two two = new Two();

		String json = JSONFactoryUtil.looseSerialize(two);

		Assert.assertEquals("{\"ftwo\":173}", json);
	}

}