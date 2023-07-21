/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.lar;

import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerRegistryUtil;
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
 * @author Leon Chi
 */
public class StagedModelDataHandlerRegistryUtilTest {

	@BeforeClass
	public static void setUpClass() {
		RegistryUtil.setRegistry(new BasicRegistryImpl());
	}

	@Before
	public void setUp() {
		Registry registry = RegistryUtil.getRegistry();

		_stagedModelDataHandler =
			(StagedModelDataHandler<?>)ProxyUtil.newProxyInstance(
				StagedModelDataHandlerRegistryUtilTest.class.getClassLoader(),
				new Class<?>[] {StagedModelDataHandler.class},
				(proxy, method, args) -> {
					if (Objects.equals(method.getName(), "getClassNames")) {
						return new String[] {_CLASS_NAME};
					}

					return null;
				});

		_serviceRegistration = registry.registerService(
			StagedModelDataHandler.class, _stagedModelDataHandler);
	}

	@After
	public void tearDown() {
		_serviceRegistration.unregister();
	}

	@Test
	public void testGetStagedModelDataHandler() {
		Assert.assertSame(
			_stagedModelDataHandler,
			StagedModelDataHandlerRegistryUtil.getStagedModelDataHandler(
				_CLASS_NAME));
	}

	@Test
	public void testGetStagedModelDataHandlers() {
		List<StagedModelDataHandler<?>> stagedModelDataHandlers =
			StagedModelDataHandlerRegistryUtil.getStagedModelDataHandlers();

		Assert.assertTrue(
			_CLASS_NAME + " not found in " + stagedModelDataHandlers,
			stagedModelDataHandlers.removeIf(
				stagedModelDataHandler ->
					_stagedModelDataHandler == stagedModelDataHandler));
	}

	private static final String _CLASS_NAME = "TestStagedModelDataHandler";

	private ServiceRegistration<StagedModelDataHandler> _serviceRegistration;
	private StagedModelDataHandler<?> _stagedModelDataHandler;

}