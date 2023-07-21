/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.application.list.deploy.hot.test;

import com.liferay.application.list.deploy.hot.LegacyPortletPanelAppHotDeployListener;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.deploy.hot.DependencyManagementThreadLocal;
import com.liferay.portal.kernel.deploy.hot.HotDeployEvent;
import com.liferay.portal.kernel.deploy.hot.HotDeployListener;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockServletContext;

/**
 * @author Adolfo Pérez
 */
@RunWith(Arquillian.class)
public class LegacyPortletPanelAppHotDeployListenerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_dependencyManagementEnabled =
			DependencyManagementThreadLocal.isEnabled();

		DependencyManagementThreadLocal.setEnabled(false);

		Registry registry = RegistryUtil.getRegistry();

		Collection<ServiceReference<HotDeployListener>> serviceReferences =
			registry.getServiceReferences(
				HotDeployListener.class,
				"(component.name=" +
					LegacyPortletPanelAppHotDeployListener.class.getName() +
						")");

		Iterator<ServiceReference<HotDeployListener>> iterator =
			serviceReferences.iterator();

		_serviceReference = iterator.next();

		_hotDeployListener =
			(LegacyPortletPanelAppHotDeployListener)registry.getService(
				_serviceReference);
	}

	@After
	public void tearDown() {
		DependencyManagementThreadLocal.setEnabled(
			_dependencyManagementEnabled);

		Registry registry = RegistryUtil.getRegistry();

		registry.ungetService(_serviceReference);
	}

	@Test
	public void testLegacyPortletWithControlPanelEntry() throws Exception {
		int initialServiceRegistrationsSize =
			_hotDeployListener.getServiceRegistrationsSize();

		HotDeployEvent hotDeployEvent = getHotDeployEvent(
			"classpath:/com/liferay/application/list/deploy/hot/test" +
				"/dependencies/control-panel-entry-liferay-portlet.xml");

		_hotDeployListener.invokeDeploy(hotDeployEvent);

		Assert.assertEquals(
			initialServiceRegistrationsSize + 1,
			_hotDeployListener.getServiceRegistrationsSize());
	}

	@Test
	public void testLegacyPortletWithNoControlPanelEntry() throws Exception {
		int initialServiceRegistrationsSize =
			_hotDeployListener.getServiceRegistrationsSize();

		HotDeployEvent hotDeployEvent = getHotDeployEvent(
			"classpath:/com/liferay/application/list/deploy/hot/test" +
				"/dependencies/no-control-panel-entry-liferay-portlet.xml");

		_hotDeployListener.invokeDeploy(hotDeployEvent);

		Assert.assertEquals(
			initialServiceRegistrationsSize,
			_hotDeployListener.getServiceRegistrationsSize());
	}

	@Test
	public void testPortletWithNoXMLDescriptor() throws Exception {
		int initialServiceRegistrationsSize =
			_hotDeployListener.getServiceRegistrationsSize();

		_hotDeployListener.invokeDeploy(getHotDeployEvent(null));

		Assert.assertEquals(
			initialServiceRegistrationsSize,
			_hotDeployListener.getServiceRegistrationsSize());
	}

	protected HotDeployEvent getHotDeployEvent(String location)
		throws Exception {

		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		ResourceLoader resourceLoader = new TestResourceLoader(
			location, classLoader);

		ServletContext servletContext = new TestServletContext(
			"/", resourceLoader);

		return new HotDeployEvent(servletContext, classLoader);
	}

	private boolean _dependencyManagementEnabled;
	private LegacyPortletPanelAppHotDeployListener _hotDeployListener;
	private ServiceReference<HotDeployListener> _serviceReference;

	private static class TestResourceLoader extends DefaultResourceLoader {

		public TestResourceLoader(String location, ClassLoader classLoader) {
			super(classLoader);

			_location = location;
		}

		@Override
		public Resource getResource(String location) {
			if (_location == null) {
				return _invalidResource;
			}

			return super.getResource(_location);
		}

		private static final Resource _invalidResource = new ClassPathResource(
			"/") {

			@Override
			public boolean exists() {
				return false;
			}

		};

		private final String _location;

	}

	private static class TestServletContext extends MockServletContext {

		public TestServletContext(
			String resourceBasePath, ResourceLoader resourceLoader) {

			super(resourceBasePath, resourceLoader);
		}

		@Override
		public String getServletContextName() {
			return _SERVLET_CONTEXT_NAME;
		}

		private static final String _SERVLET_CONTEXT_NAME =
			StringUtil.randomString();

	}

}