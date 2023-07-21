/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.dependency;

import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Filter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class FilterServiceDependencyVerifierTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		RegistryUtil.setRegistry(null);
		RegistryUtil.setRegistry(new BasicRegistryImpl());
	}

	@Test
	public void testVerifyImplementations() {
		Registry registry = RegistryUtil.getRegistry();

		registry.registerService(TestInstance1.class, new TestInstance1());

		Map<String, Object> properties = new HashMap<>();

		properties.put("type", "test");

		registry.registerService(
			TestInterface2.class, new TestInstance2(), properties);

		ServiceReference<?> serviceReference = registry.getServiceReference(
			TestInstance1.class);

		Filter filter1 = registry.getFilter(
			"(objectClass=com.liferay.registry.dependency." +
				"FilterServiceDependencyVerifierTest$TestInstance1)");

		FilterServiceDependencyVerifier filterServiceDependencyVerifier1 =
			new FilterServiceDependencyVerifier(filter1);

		Assert.assertTrue(
			filterServiceDependencyVerifier1.verify(serviceReference));

		Filter filter2 = registry.getFilter(
			"(&(objectClass=" + TestInterface2.class.getName() +
				")(type=test))");

		FilterServiceDependencyVerifier filterServiceDependencyVerifier2 =
			new FilterServiceDependencyVerifier(filter2);

		ServiceReference<?> serviceReference2 = registry.getServiceReference(
			TestInterface2.class);

		Assert.assertFalse(
			filterServiceDependencyVerifier2.verify(serviceReference));
		Assert.assertTrue(
			filterServiceDependencyVerifier2.verify(serviceReference2));
	}

	private static class TestInstance1 implements TestInterface1 {
	}

	private static class TestInstance2 implements TestInterface2 {
	}

	private interface TestInterface1 {
	}

	private interface TestInterface2 {
	}

}