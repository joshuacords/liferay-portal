/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.configuration.admin.internal.util.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.configuration.admin.definition.ConfigurationDDMFormDeclaration;
import com.liferay.osgi.util.service.OSGiServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.lang.reflect.Method;

import java.util.Dictionary;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Pei-Jung Lan
 */
@RunWith(Arquillian.class)
public class ConfigurationDDMFormDeclarationUtilTest {

	@Before
	public void setUp() throws Exception {
		_bundle = FrameworkUtil.getBundle(
			ConfigurationDDMFormDeclarationUtilTest.class);

		_bundleContext = _bundle.getBundleContext();

		_configuration = OSGiServiceUtil.callService(
			_bundleContext, ConfigurationAdmin.class,
			configurationAdmin -> configurationAdmin.createFactoryConfiguration(
				"test.pid", StringPool.QUESTION));

		ConfigurationDDMFormDeclaration configurationDDMFormDeclaration =
			() -> TestConfigurationForm.class;

		_serviceRegistration = registerConfigurationDDMFormDeclaration(
			configurationDDMFormDeclaration, _configuration.getPid());

		Bundle bundle = null;

		for (Bundle installedBundle : _bundleContext.getBundles()) {
			if (Objects.equals(
					installedBundle.getSymbolicName(),
					"com.liferay.configuration.admin.web")) {

				bundle = installedBundle;

				break;
			}
		}

		if (bundle == null) {
			throw new IllegalStateException(
				"com.liferay.configuration.admin.web bundle not found");
		}

		Class<?> clazz = bundle.loadClass(
			"com.liferay.configuration.admin.web.internal.util." +
				"ConfigurationDDMFormDeclarationUtil");

		_method = clazz.getDeclaredMethod(
			"getConfigurationDDMFormClass", String.class);
	}

	@After
	public void tearDown() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	@Test
	public void testGetConfigurationFormClassFromPid() throws Exception {
		Assert.assertEquals(
			TestConfigurationForm.class,
			_method.invoke(null, _configuration.getPid()));
	}

	protected ServiceRegistration<ConfigurationDDMFormDeclaration>
		registerConfigurationDDMFormDeclaration(
			ConfigurationDDMFormDeclaration configurationDDMFormDeclaration,
			String configurationPid) {

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("configurationPid", configurationPid);

		return _bundleContext.registerService(
			ConfigurationDDMFormDeclaration.class,
			configurationDDMFormDeclaration, properties);
	}

	private Bundle _bundle;
	private BundleContext _bundleContext;
	private Configuration _configuration;
	private Method _method;
	private ServiceRegistration _serviceRegistration;

	private class TestConfigurationForm {
	}

}