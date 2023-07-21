/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.atom;

import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Tina Tian
 */
public class AtomCollectionAdapterRegistryUtilTest {

	@BeforeClass
	public static void setUpClass() {
		RegistryUtil.setRegistry(new BasicRegistryImpl());
	}

	@Before
	public void setUp() {
		_atomCollectionAdapter =
			(AtomCollectionAdapter)ProxyUtil.newProxyInstance(
				AtomCollectionAdapter.class.getClassLoader(),
				new Class<?>[] {AtomCollectionAdapter.class},
				(proxy, method, args) -> {
					if (Objects.equals(method.getName(), "getCollectionName")) {
						return _TEST_ATOM_COLLECTION_NAME;
					}

					return null;
				});

		Registry registry = RegistryUtil.getRegistry();

		_serviceRegistration = registry.registerService(
			AtomCollectionAdapter.class, _atomCollectionAdapter);
	}

	@After
	public void tearDown() {
		_serviceRegistration.unregister();
	}

	@Test
	public void testGetAtomCollectionAdapter() {
		Assert.assertSame(
			_atomCollectionAdapter,
			AtomCollectionAdapterRegistryUtil.getAtomCollectionAdapter(
				_TEST_ATOM_COLLECTION_NAME));
	}

	@Test
	public void testGetAtomCollectionAdapters() {
		List<AtomCollectionAdapter<?>> atomCollectionAdapters =
			AtomCollectionAdapterRegistryUtil.getAtomCollectionAdapters();

		Assert.assertTrue(
			_TEST_ATOM_COLLECTION_NAME + " not found in " +
				atomCollectionAdapters,
			atomCollectionAdapters.removeIf(
				atomCollectionAdapter ->
					atomCollectionAdapter == _atomCollectionAdapter));
	}

	private static final String _TEST_ATOM_COLLECTION_NAME =
		"TEST_ATOM_COLLECTION_NAME";

	private AtomCollectionAdapter _atomCollectionAdapter;
	private ServiceRegistration<AtomCollectionAdapter> _serviceRegistration;

}