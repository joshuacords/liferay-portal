/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.internal.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.registry.Filter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceRegistration;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerList;
import com.liferay.registry.internal.InterfaceOne;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Raymond Augé
 * @author Carlos Sierra Andrés
 */
@RunWith(Arquillian.class)
public class ServiceTrackerCollectionsTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() {
		_registry = RegistryUtil.getRegistry();
	}

	@Test
	public void testClass() {
		try (ServiceTrackerList<InterfaceOne> serviceTrackerList =
				ServiceTrackerCollections.openList(InterfaceOne.class)) {

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());

			InterfaceOne interfaceOneA = getInstance();

			ServiceRegistration<InterfaceOne> serviceRegistrationA =
				_registry.registerService(InterfaceOne.class, interfaceOneA);

			Assert.assertNotNull(serviceRegistrationA);

			InterfaceOne interfaceOneB = getInstance();

			serviceTrackerList.add(interfaceOneB);

			Assert.assertEquals(
				serviceTrackerList.toString(), 2, serviceTrackerList.size());

			for (InterfaceOne interfaceOne : serviceTrackerList) {
				Assert.assertNotNull(interfaceOne);
			}

			serviceRegistrationA.unregister();

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());

			serviceTrackerList.remove(interfaceOneB);

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());
		}
	}

	@Test
	public void testClassFilter() throws Exception {
		Filter filter = _registry.getFilter("(a.property=G)");

		try (ServiceTrackerList<InterfaceOne> serviceTrackerList =
				ServiceTrackerCollections.openList(
					InterfaceOne.class, filter)) {

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());

			InterfaceOne interfaceOneA = getInstance();

			Map<String, Object> properties = new HashMap<>();

			properties.put("a.property", "G");

			ServiceRegistration<InterfaceOne> serviceRegistrationA =
				_registry.registerService(
					InterfaceOne.class, interfaceOneA, properties);

			Assert.assertNotNull(serviceRegistrationA);

			InterfaceOne interfaceOneB = getInstance();

			try {
				serviceTrackerList.add(interfaceOneB);

				Assert.fail();
			}
			catch (IllegalStateException illegalStateException) {
			}

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());

			for (InterfaceOne interfaceOne : serviceTrackerList) {
				Assert.assertNotNull(interfaceOne);
			}

			Collection<InterfaceOne> interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 1, interfaceOnes.size());

			serviceRegistrationA.unregister();

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 0, interfaceOnes.size());

			serviceTrackerList.remove(interfaceOneB);

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 0, interfaceOnes.size());

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());
		}
	}

	@Test
	public void testClassFilterProperties() throws Exception {
		Filter filter = _registry.getFilter("(a.property=G)");

		Map<String, Object> properties = new HashMap<>();

		properties.put("a.property", "G");

		try (ServiceTrackerList<InterfaceOne> serviceTrackerList =
				ServiceTrackerCollections.openList(
					InterfaceOne.class, filter, properties)) {

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());

			InterfaceOne interfaceOneA = getInstance();

			ServiceRegistration<InterfaceOne> serviceRegistrationA =
				_registry.registerService(InterfaceOne.class, interfaceOneA);

			Assert.assertNotNull(serviceRegistrationA);

			InterfaceOne interfaceOneB = getInstance();

			serviceTrackerList.add(interfaceOneB);

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());

			for (InterfaceOne interfaceOne : serviceTrackerList) {
				Assert.assertNotNull(interfaceOne);
			}

			Collection<InterfaceOne> interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 1, interfaceOnes.size());

			serviceRegistrationA.unregister();

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 1, interfaceOnes.size());

			serviceTrackerList.remove(interfaceOneB);

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 0, interfaceOnes.size());

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());
		}
	}

	@Test
	public void testClassFilterServiceTrackerCustomizer() throws Exception {
		Filter filter = _registry.getFilter("(a.property=G)");

		AtomicInteger counter = new AtomicInteger();

		ServiceTrackerCustomizer<InterfaceOne, InterfaceOne>
			serviceTrackerCustomizer = new MockServiceTrackerCustomizer(
				counter);

		try (ServiceTrackerList<InterfaceOne> serviceTrackerList =
				ServiceTrackerCollections.openList(
					InterfaceOne.class, filter, serviceTrackerCustomizer)) {

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());

			InterfaceOne interfaceOneA = getInstance();

			Map<String, Object> properties = new HashMap<>();

			properties.put("a.property", "G");

			ServiceRegistration<InterfaceOne> serviceRegistrationA =
				_registry.registerService(
					InterfaceOne.class, interfaceOneA, properties);

			Assert.assertNotNull(serviceRegistrationA);

			InterfaceOne interfaceOneB = getInstance();

			try {
				serviceTrackerList.add(interfaceOneB);

				Assert.fail();
			}
			catch (IllegalStateException illegalStateException) {
			}

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());

			for (InterfaceOne interfaceOne : serviceTrackerList) {
				Assert.assertNotNull(interfaceOne);
			}

			Collection<InterfaceOne> interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 1, interfaceOnes.size());

			Assert.assertEquals(1, counter.intValue());

			serviceRegistrationA.unregister();

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());
			Assert.assertEquals(2, counter.intValue());

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 0, interfaceOnes.size());

			serviceTrackerList.remove(interfaceOneB);

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 0, interfaceOnes.size());

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());
			Assert.assertEquals(2, counter.intValue());
		}
	}

	@Test
	public void testClassFilterServiceTrackerCustomizerProperties()
		throws Exception {

		Filter filter = _registry.getFilter("(a.property=G)");

		AtomicInteger counter = new AtomicInteger();

		ServiceTrackerCustomizer<InterfaceOne, InterfaceOne>
			serviceTrackerCustomizer = new MockServiceTrackerCustomizer(
				counter);

		Map<String, Object> properties = new HashMap<>();

		properties.put("a.property", "G");

		try (ServiceTrackerList<InterfaceOne> serviceTrackerList =
				ServiceTrackerCollections.openList(
					InterfaceOne.class, filter, serviceTrackerCustomizer,
					properties)) {

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());

			InterfaceOne interfaceOneA = getInstance();

			ServiceRegistration<InterfaceOne> serviceRegistrationA =
				_registry.registerService(InterfaceOne.class, interfaceOneA);

			Assert.assertNotNull(serviceRegistrationA);

			InterfaceOne interfaceOneB = getInstance();

			serviceTrackerList.add(interfaceOneB);

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());

			for (InterfaceOne interfaceOne : serviceTrackerList) {
				Assert.assertNotNull(interfaceOne);
			}

			Collection<InterfaceOne> interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 1, interfaceOnes.size());

			Assert.assertEquals(1, counter.intValue());

			serviceRegistrationA.unregister();

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());
			Assert.assertEquals(1, counter.intValue());

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 1, interfaceOnes.size());

			serviceTrackerList.remove(interfaceOneB);

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 0, interfaceOnes.size());

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());
			Assert.assertEquals(2, counter.intValue());
		}
	}

	@Test
	public void testClassProperties() throws Exception {
		Map<String, Object> properties = new HashMap<>();

		properties.put("a.property", "G");

		try (ServiceTrackerList<InterfaceOne> serviceTrackerList =
				ServiceTrackerCollections.openList(
					InterfaceOne.class, properties)) {

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());

			InterfaceOne interfaceOneA = getInstance();

			ServiceRegistration<InterfaceOne> serviceRegistrationA =
				_registry.registerService(
					InterfaceOne.class, interfaceOneA, properties);

			Assert.assertNotNull(serviceRegistrationA);

			InterfaceOne interfaceOneB = getInstance();

			serviceTrackerList.add(interfaceOneB);

			Assert.assertEquals(
				serviceTrackerList.toString(), 2, serviceTrackerList.size());

			for (InterfaceOne interfaceOne : serviceTrackerList) {
				Assert.assertNotNull(interfaceOne);
			}

			Collection<InterfaceOne> interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 2, interfaceOnes.size());

			serviceRegistrationA.unregister();

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 1, interfaceOnes.size());

			serviceTrackerList.remove(interfaceOneB);

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 0, interfaceOnes.size());

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());
		}
	}

	@Test
	public void testClassServiceTrackerCustomizer() {
		AtomicInteger counter = new AtomicInteger();

		ServiceTrackerCustomizer<InterfaceOne, InterfaceOne>
			serviceTrackerCustomizer = new MockServiceTrackerCustomizer(
				counter);

		try (ServiceTrackerList<InterfaceOne> serviceTrackerList =
				ServiceTrackerCollections.openList(
					InterfaceOne.class, serviceTrackerCustomizer)) {

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());

			InterfaceOne interfaceOneA = getInstance();

			ServiceRegistration<InterfaceOne> serviceRegistrationA =
				_registry.registerService(InterfaceOne.class, interfaceOneA);

			Assert.assertNotNull(serviceRegistrationA);

			InterfaceOne interfaceOneB = getInstance();

			serviceTrackerList.add(interfaceOneB);

			Assert.assertEquals(
				serviceTrackerList.toString(), 2, serviceTrackerList.size());

			for (InterfaceOne interfaceOne : serviceTrackerList) {
				Assert.assertNotNull(interfaceOne);
			}

			Assert.assertEquals(2, counter.intValue());

			serviceRegistrationA.unregister();

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());
			Assert.assertEquals(3, counter.intValue());

			serviceTrackerList.remove(interfaceOneB);

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());
			Assert.assertEquals(4, counter.intValue());
		}
	}

	@Test
	public void testClassServiceTrackerCustomizerCustomizerProperties1()
		throws Exception {

		AtomicInteger counter = new AtomicInteger();

		ServiceTrackerCustomizer<InterfaceOne, InterfaceOne>
			serviceTrackerCustomizer = new MockServiceTrackerCustomizer(
				counter);

		Map<String, Object> properties = new HashMap<>();

		properties.put("a.property", "G");

		try (ServiceTrackerList<InterfaceOne> serviceTrackerList =
				ServiceTrackerCollections.openList(
					InterfaceOne.class, serviceTrackerCustomizer, properties)) {

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());

			InterfaceOne interfaceOneA = getInstance();

			ServiceRegistration<InterfaceOne> serviceRegistrationA =
				_registry.registerService(
					InterfaceOne.class, interfaceOneA, properties);

			Assert.assertNotNull(serviceRegistrationA);

			InterfaceOne interfaceOneB = getInstance();

			serviceTrackerList.add(interfaceOneB);

			Assert.assertEquals(
				serviceTrackerList.toString(), 2, serviceTrackerList.size());

			for (InterfaceOne interfaceOne : serviceTrackerList) {
				Assert.assertNotNull(interfaceOne);
			}

			Collection<InterfaceOne> interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 2, interfaceOnes.size());

			Assert.assertEquals(2, counter.intValue());

			serviceRegistrationA.unregister();

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());
			Assert.assertEquals(3, counter.intValue());

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 1, interfaceOnes.size());

			serviceTrackerList.remove(interfaceOneB);

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 0, interfaceOnes.size());

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());
			Assert.assertEquals(4, counter.intValue());
		}
	}

	@Test
	public void testClassServiceTrackerCustomizerCustomizerProperties2()
		throws Exception {

		AtomicInteger counter = new AtomicInteger();

		ServiceTrackerCustomizer<InterfaceOne, InterfaceOne>
			serviceTrackerCustomizer = new MockServiceTrackerCustomizer(
				counter);

		Map<String, Object> properties = new HashMap<>();

		properties.put("a.property", "G");

		try (ServiceTrackerList<InterfaceOne> serviceTrackerList =
				ServiceTrackerCollections.openList(
					InterfaceOne.class, serviceTrackerCustomizer, properties)) {

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());

			InterfaceOne interfaceOneA = getInstance();

			ServiceRegistration<InterfaceOne> serviceRegistrationA =
				_registry.registerService(InterfaceOne.class, interfaceOneA);

			Assert.assertNotNull(serviceRegistrationA);

			InterfaceOne interfaceOneB = getInstance();

			serviceTrackerList.add(interfaceOneB);

			Assert.assertEquals(
				serviceTrackerList.toString(), 2, serviceTrackerList.size());

			for (InterfaceOne interfaceOne : serviceTrackerList) {
				Assert.assertNotNull(interfaceOne);
			}

			Collection<InterfaceOne> interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 1, interfaceOnes.size());

			Assert.assertEquals(2, counter.intValue());

			serviceRegistrationA.unregister();

			Assert.assertEquals(
				serviceTrackerList.toString(), 1, serviceTrackerList.size());
			Assert.assertEquals(3, counter.intValue());

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 1, interfaceOnes.size());

			serviceTrackerList.remove(interfaceOneB);

			interfaceOnes = _registry.getServices(
				InterfaceOne.class, "(a.property=G)");

			Assert.assertEquals(
				interfaceOnes.toString(), 0, interfaceOnes.size());

			Assert.assertEquals(
				serviceTrackerList.toString(), 0, serviceTrackerList.size());
			Assert.assertEquals(4, counter.intValue());
		}
	}

	protected InterfaceOne getInstance() {
		return new InterfaceOne() {
		};
	}

	private Registry _registry;

	private class MockServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<InterfaceOne, InterfaceOne> {

		public MockServiceTrackerCustomizer(AtomicInteger counter) {
			_counter = counter;
		}

		@Override
		public InterfaceOne addingService(
			ServiceReference<InterfaceOne> serviceReference) {

			InterfaceOne one = _registry.getService(serviceReference);

			_counter.incrementAndGet();

			return one;
		}

		@Override
		public void modifiedService(
			ServiceReference<InterfaceOne> serviceReference,
			InterfaceOne interfaceOne) {

			_counter.incrementAndGet();
		}

		@Override
		public void removedService(
			ServiceReference<InterfaceOne> serviceReference,
			InterfaceOne interfaceOne) {

			_registry.ungetService(serviceReference);

			_counter.incrementAndGet();
		}

		private final AtomicInteger _counter;

	}

}