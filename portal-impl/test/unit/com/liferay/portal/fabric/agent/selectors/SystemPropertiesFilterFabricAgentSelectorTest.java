/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.agent.selectors;

import com.liferay.petra.process.ProcessCallable;
import com.liferay.portal.fabric.agent.FabricAgent;
import com.liferay.portal.fabric.status.AdvancedOperatingSystemMXBean;
import com.liferay.portal.fabric.status.FabricStatus;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.lang.management.RuntimeMXBean;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class SystemPropertiesFilterFabricAgentSelectorTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@Test
	public void testSelect() {
		FabricAgentSelector fabricAgentSelector =
			new TestSystemPropertiesFilterFabricAgentSelector("test");

		FabricAgent fabricAgent1 = createFabricAgent(
			Collections.<String, String>singletonMap("test", null));
		FabricAgent fabricAgent2 = createFabricAgent(
			Collections.<String, String>emptyMap());

		Collection<FabricAgent> fabricAgents = fabricAgentSelector.select(
			new ArrayList<FabricAgent>(
				Arrays.asList(fabricAgent1, fabricAgent2)),
			null);

		Assert.assertEquals(fabricAgents.toString(), 1, fabricAgents.size());

		Iterator<FabricAgent> iterator = fabricAgents.iterator();

		Assert.assertSame(fabricAgent1, iterator.next());
	}

	protected FabricAgent createFabricAgent(
		Map<String, String> systemProperties) {

		return (FabricAgent)Proxy.newProxyInstance(
			FabricAgent.class.getClassLoader(),
			new Class<?>[] {FabricAgent.class},
			new FabricAgentInvocationHandler(systemProperties));
	}

	protected static class FabricAgentInvocationHandler
		implements InvocationHandler {

		public FabricAgentInvocationHandler(
			Map<String, String> systemProperties) {

			_systemProperties = systemProperties;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) {
			String methodName = method.getName();

			if (methodName.equals("getFabricStatus")) {
				return Proxy.newProxyInstance(
					FabricStatus.class.getClassLoader(),
					new Class<?>[] {FabricStatus.class},
					new FabricStatusInvocationHandler(_systemProperties));
			}

			if (methodName.equals("toString")) {
				return String.valueOf(_systemProperties);
			}

			throw new UnsupportedOperationException();
		}

		private final Map<String, String> _systemProperties;

	}

	protected static class FabricStatusInvocationHandler
		implements InvocationHandler {

		public FabricStatusInvocationHandler(
			Map<String, String> systemProperties) {

			_systemProperties = systemProperties;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) {
			String methodName = method.getName();

			if (methodName.equals("getRuntimeMXBean")) {
				return Proxy.newProxyInstance(
					AdvancedOperatingSystemMXBean.class.getClassLoader(),
					new Class<?>[] {RuntimeMXBean.class},
					new RuntimeMXBeanInvocationHandler(_systemProperties));
			}

			if (methodName.equals("toString")) {
				return String.valueOf(_systemProperties);
			}

			throw new UnsupportedOperationException();
		}

		private final Map<String, String> _systemProperties;

	}

	protected static class RuntimeMXBeanInvocationHandler
		implements InvocationHandler {

		public RuntimeMXBeanInvocationHandler(
			Map<String, String> systemProperties) {

			_systemProperties = systemProperties;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) {
			String methodName = method.getName();

			if (methodName.equals("getSystemProperties")) {
				return _systemProperties;
			}

			if (methodName.equals("toString")) {
				return String.valueOf(_systemProperties);
			}

			throw new UnsupportedOperationException();
		}

		private final Map<String, String> _systemProperties;

	}

	protected static class TestSystemPropertiesFilterFabricAgentSelector
		extends SystemPropertiesFilterFabricAgentSelector {

		public TestSystemPropertiesFilterFabricAgentSelector(String key) {
			_key = key;
		}

		@Override
		protected boolean accept(
			Map<String, String> systemProperties,
			ProcessCallable<?> processCallable) {

			return systemProperties.containsKey(_key);
		}

		private final String _key;

	}

}