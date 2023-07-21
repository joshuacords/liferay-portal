/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.form.navigator.internal.configuration;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alejandro Tardín
 */
public class RetrieverWhenNeitherACategoryNorAContextAreSpecifiedInTheConfigTest
	extends BaseFormNavigatorEntryConfigurationRetrieverTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Override
	public void setUp() throws Exception {
		super.setUp();

		createConfiguration(
			"form1",
			new String[] {"formNavigatorEntryKey1,formNavigatorEntryKey2"});
	}

	@Test
	public void testContainsValuesForTheEmptyCategoryAndAnyContext() {
		List<String> formNavigatorEntryKeys =
			formNavigatorEntryConfigurationRetriever.getFormNavigatorEntryKeys(
				"form1", "", "add"
			).get();

		Assert.assertEquals(
			formNavigatorEntryKeys.toString(), 2,
			formNavigatorEntryKeys.size());

		Iterator<String> iterator = formNavigatorEntryKeys.iterator();

		Assert.assertEquals("formNavigatorEntryKey1", iterator.next());
		Assert.assertEquals("formNavigatorEntryKey2", iterator.next());
	}

	@Test
	public void testContainsValuesForTheEmptyCategoryAndNoContext() {
		List<String> formNavigatorEntryKeys =
			formNavigatorEntryConfigurationRetriever.getFormNavigatorEntryKeys(
				"form1", "", null
			).get();

		Assert.assertEquals(
			formNavigatorEntryKeys.toString(), 2,
			formNavigatorEntryKeys.size());

		Iterator<String> iterator = formNavigatorEntryKeys.iterator();

		Assert.assertEquals("formNavigatorEntryKey1", iterator.next());
		Assert.assertEquals("formNavigatorEntryKey2", iterator.next());
	}

	@Test
	public void testDoesNotContainValuesForANonemptyCategory() {
		Optional<List<String>> formNavigatorEntryKeys =
			formNavigatorEntryConfigurationRetriever.getFormNavigatorEntryKeys(
				"form1", "general", null);

		Assert.assertFalse(formNavigatorEntryKeys.isPresent());
	}

}