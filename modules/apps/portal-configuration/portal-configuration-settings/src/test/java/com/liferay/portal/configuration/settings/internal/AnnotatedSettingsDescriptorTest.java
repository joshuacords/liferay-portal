/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.settings.internal;

import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
public class AnnotatedSettingsDescriptorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGetAllKeys() {
		Set<String> allKeys = _annotatedSettingsDescriptor.getAllKeys();

		Collection<String> expectedAllKeys = Arrays.asList(
			"boolean", "long", "string", "stringArray1", "stringArray2",
			"unrenamedProperty");

		Assert.assertEquals(
			allKeys.toString(), expectedAllKeys.size(), allKeys.size());
		Assert.assertTrue(allKeys.containsAll(expectedAllKeys));
	}

	@Test
	public void testGetMultiValuedKeys() {
		Set<String> multiValuedKeys =
			_annotatedSettingsDescriptor.getMultiValuedKeys();

		Collection<String> expectedMultiValuedKeys = Arrays.asList(
			"stringArray1", "stringArray2");

		Assert.assertEquals(
			multiValuedKeys.toString(), expectedMultiValuedKeys.size(),
			multiValuedKeys.size());
		Assert.assertTrue(multiValuedKeys.containsAll(expectedMultiValuedKeys));
	}

	@Settings.Config(settingsIds = {"settingsId.1", "settingsId.2"})
	public class MockSettings {

		public boolean getBoolean() {
			return false;
		}

		@Settings.Property(ignore = true)
		public String getIgnoredProperty() {
			return "";
		}

		public long getLong() {
			return 0;
		}

		@Settings.Property(name = "unrenamedProperty")
		public String getRenamedProperty() {
			return "";
		}

		public String getString() {
			return "";
		}

		public String[] getStringArray1() {
			return new String[0];
		}

		public String[] getStringArray2() {
			return new String[0];
		}

	}

	private final AnnotatedSettingsDescriptor _annotatedSettingsDescriptor =
		new AnnotatedSettingsDescriptor(MockSettings.class);

}