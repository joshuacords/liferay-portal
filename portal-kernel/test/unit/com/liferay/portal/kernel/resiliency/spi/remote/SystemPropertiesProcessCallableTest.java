/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi.remote;

import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class SystemPropertiesProcessCallableTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Test
	public void testSystemPropertiesProcessCallable() {
		Properties oldProperties = System.getProperties();

		Properties newProperties = new Properties();

		System.setProperties(newProperties);

		Map<String, String> propertiesMap = HashMapBuilder.put(
			"key1", "value1"
		).put(
			"key2", "value2"
		).put(
			"key3", "value3"
		).build();

		SystemPropertiesProcessCallable systemPropertiesProcessCallable =
			new SystemPropertiesProcessCallable(propertiesMap);

		systemPropertiesProcessCallable.call();

		Assert.assertEquals(3, newProperties.size());

		Assert.assertEquals("value1", newProperties.getProperty("key1"));
		Assert.assertEquals("value2", newProperties.getProperty("key2"));
		Assert.assertEquals("value3", newProperties.getProperty("key3"));

		System.setProperties(oldProperties);
	}

}