/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.template;

import com.liferay.portal.kernel.util.ProxyFactory;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Dante Wang
 * @author Philip Jones
 */
public class TemplateManagerUtilTest {

	@BeforeClass
	public static void setUpClass() {
		_templateManager = (TemplateManager)ProxyUtil.newProxyInstance(
			TemplateManager.class.getClassLoader(),
			new Class<?>[] {TemplateManager.class},
			(proxy, method, args) -> {
				if (Objects.equals(method.getName(), "getName")) {
					return _TEST_TEMPLATE_MANAGER_NAME;
				}

				if (Objects.equals(method.getName(), "getTemplate") &&
					(args[0] == _TEMPLATE_RESOURCE)) {

					return _TEMPLATE;
				}

				return null;
			});

		RegistryUtil.setRegistry(new BasicRegistryImpl());

		Registry registry = RegistryUtil.getRegistry();

		_serviceRegistration = registry.registerService(
			TemplateManager.class, _templateManager,
			Collections.singletonMap("language.type", "test"));
	}

	@AfterClass
	public static void tearDownClass() {
		_serviceRegistration.unregister();
	}

	@Test
	public void testGetTemplate() throws TemplateException {
		Assert.assertSame(
			_TEMPLATE,
			TemplateManagerUtil.getTemplate(
				_TEST_TEMPLATE_MANAGER_NAME, _TEMPLATE_RESOURCE, false));
	}

	@Test
	public void testGetTemplateManager() {
		Assert.assertSame(
			_templateManager,
			TemplateManagerUtil.getTemplateManager(
				_TEST_TEMPLATE_MANAGER_NAME));
	}

	@Test
	public void testGetTemplateManagerName() {
		Set<String> templateManagerNames =
			TemplateManagerUtil.getTemplateManagerNames();

		Assert.assertTrue(
			templateManagerNames.toString(),
			templateManagerNames.contains(_TEST_TEMPLATE_MANAGER_NAME));
	}

	@Test
	public void testGetTemplateManagers() {
		Map<String, TemplateManager> templateManagers =
			TemplateManagerUtil.getTemplateManagers();

		Assert.assertSame(
			_templateManager,
			templateManagers.get(_TEST_TEMPLATE_MANAGER_NAME));
	}

	@Test
	public void testHasTemplateManager() {
		Assert.assertTrue(
			_TEST_TEMPLATE_MANAGER_NAME + " not found",
			TemplateManagerUtil.hasTemplateManager(
				_TEST_TEMPLATE_MANAGER_NAME));
	}

	private static final Template _TEMPLATE = ProxyFactory.newDummyInstance(
		Template.class);

	private static final TemplateResource _TEMPLATE_RESOURCE =
		ProxyFactory.newDummyInstance(TemplateResource.class);

	private static final String _TEST_TEMPLATE_MANAGER_NAME =
		"TEST_TEMPLATE_MANAGER_NAME";

	private static ServiceRegistration<TemplateManager> _serviceRegistration;
	private static TemplateManager _templateManager;

}