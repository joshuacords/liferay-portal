/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal;

import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Bruno Basto
 */
public class SoyManagerTest {

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
	public void testGetName() {
		SoyManager soyManager = _soyTestHelper.getSoyManager();

		Assert.assertEquals(
			TemplateConstants.LANG_TYPE_SOY, soyManager.getName());
	}

	@Test
	public void testProcessMultiTemplateAllResources() throws Exception {
		Template template = _soyTestHelper.getSoyTemplate(
			"multi.soy", "simple.soy", "context.soy", "multi-context.soy");

		template.put("namespace", "soy.multiTest.simple");

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		Assert.assertEquals("Hello.", unsyncStringWriter.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProcessMultiTemplateEmptyList() throws Exception {
		_soyTestHelper.getSoyTemplate();
	}

	@Test
	public void testProcessMultiTemplateSimple() throws Exception {
		Template template = _soyTestHelper.getSoyTemplate(
			"multi.soy", "simple.soy");

		template.put("namespace", "soy.multiTest.simple");

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		Assert.assertEquals("Hello.", unsyncStringWriter.toString());
	}

	@Test
	public void testProcessMultiTemplateWithContext() throws Exception {
		Template template = _soyTestHelper.getSoyTemplate(
			"multi-context.soy", "context.soy");

		template.put("name", "Bruno Basto");
		template.put("namespace", "soy.multiTest.withContext");

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		Assert.assertEquals(
			"Hello. My name is Bruno Basto.", unsyncStringWriter.toString());
	}

	@Test(expected = TemplateException.class)
	public void testProcessMultiTemplateWithoutNamespace() throws Exception {
		Template template = _soyTestHelper.getSoyTemplate("simple.soy");

		template.processTemplate(new UnsyncStringWriter());
	}

	@Test
	public void testProcessTemplateSimple() throws Exception {
		Template template = _soyTestHelper.getSoyTemplate("simple.soy");

		template.put("namespace", "soy.test.simple");

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		Assert.assertEquals("Hello.", unsyncStringWriter.toString());
	}

	@Test
	public void testProcessTemplateWithContext() throws Exception {
		Template template = _soyTestHelper.getSoyTemplate("context.soy");

		template.put("name", "Bruno Basto");
		template.put("namespace", "soy.test.withContext");

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		template.processTemplate(unsyncStringWriter);

		Assert.assertEquals(
			"Hello. My name is Bruno Basto.", unsyncStringWriter.toString());
	}

	@Test(expected = TemplateException.class)
	public void testProcessTemplateWithoutNamespace() throws Exception {
		Template template = _soyTestHelper.getSoyTemplate("simple.soy");

		template.processTemplate(new UnsyncStringWriter());
	}

	private final SoyTestHelper _soyTestHelper = new SoyTestHelper();

}