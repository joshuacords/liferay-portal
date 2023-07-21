/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal;

import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.template.soy.constants.SoyTemplateConstants;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Bruno Basto
 */
public class SoyTemplateTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_soyTestHelper.setUp();
	}

	@After
	public void tearDown() {
		_soyTestHelper.tearDown();
	}

	@Test
	public void testClear() {
		SoyTemplate soyTemplate = _soyTestHelper.getSoyTemplate("ijdata.soy");

		soyTemplate.put("key1", "value1");
		soyTemplate.put("key2", "value2");

		soyTemplate.clear();

		Set<String> keys = soyTemplate.keySet();

		Assert.assertEquals(keys.toString(), 0, keys.size());
	}

	/**
	 * Tests if data injected with the Injected Data API is rendered.
	 */
	@Test
	public void testProcessTemplateWithInjectedData() throws Exception {
		SoyTemplate soyTemplate = _soyTestHelper.getSoyTemplate("ijdata.soy");

		soyTemplate.put("namespace", "soy.test.ijdata");

		Map<String, Object> injectedData = HashMapBuilder.<String, Object>put(
			"hasData", true
		).build();

		soyTemplate.put(SoyTemplateConstants.INJECTED_DATA, injectedData);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		soyTemplate.processTemplate(unsyncStringWriter);

		Assert.assertEquals(
			"Injected Data: true", unsyncStringWriter.toString());
	}

	@Test
	public void testPut() {
		SoyTemplate soyTemplate = _soyTestHelper.getSoyTemplate("ijdata.soy");

		soyTemplate.put("key", "value");

		Assert.assertEquals("value", soyTemplate.get("key"));
	}

	@Test
	public void testPutWithSameValue() {
		SoyTemplate soyTemplate = _soyTestHelper.getSoyTemplate("ijdata.soy");

		String value = "value";

		soyTemplate.put("key", value);
		soyTemplate.put("key", value);

		Assert.assertEquals("value", soyTemplate.get("key"));
	}

	@Test
	public void testRemove() {
		SoyTemplate soyTemplate = _soyTestHelper.getSoyTemplate("ijdata.soy");

		soyTemplate.put("key1", "value1");
		soyTemplate.put("key2", "value2");

		soyTemplate.remove("key2");

		Assert.assertTrue(soyTemplate.containsKey("key1"));
		Assert.assertEquals("value1", soyTemplate.get("key1"));

		Assert.assertFalse(soyTemplate.containsKey("key2"));
	}

	private final SoyTestHelper _soyTestHelper = new SoyTestHelper();

}