/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;
import com.liferay.portal.model.impl.PortletImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.xml.SAXReaderImpl;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.RegistryUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Michael Bowerman
 */
public class ResourceActionsTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		RegistryUtil.setRegistry(new BasicRegistryImpl());

		UnsecureSAXReaderUtil unsecureSAXReaderUtil =
			new UnsecureSAXReaderUtil();

		unsecureSAXReaderUtil.setSAXReader(new SAXReaderImpl());

		ResourceActionsUtil resourceActionsUtil = new ResourceActionsUtil();

		ResourceActionsImpl resourceActionsImpl = new ResourceActionsImpl();

		ReflectionTestUtil.setFieldValue(
			resourceActionsImpl, "portletLocalService",
			ProxyUtil.newProxyInstance(
				_classLoader, new Class<?>[] {PortletLocalService.class},
				(proxy, method, args) -> new PortletImpl(
					RandomTestUtil.randomLong(), (String)args[0])));

		resourceActionsImpl.afterPropertiesSet();

		resourceActionsUtil.setResourceActions(resourceActionsImpl);

		ResourceActionsUtil.read(
			null, _classLoader, _SOURCE_PATH + "default.xml");
	}

	@Test
	public void testRemovePortletResource() {
		List<String> portletNames = ResourceActionsUtil.getPortletNames();

		Assert.assertTrue(
			portletNames.toString(), portletNames.contains(_PORTLET_NAME_1));
		Assert.assertTrue(
			portletNames.toString(), portletNames.contains(_PORTLET_NAME_2));

		List<String> modelNames = ResourceActionsUtil.getModelNames();

		Assert.assertTrue(
			modelNames.toString(), modelNames.contains(_MODEL_NAME));

		ResourceActionsUtil.removePortletResource(_PORTLET_NAME_1);

		portletNames = ResourceActionsUtil.getPortletNames();

		Assert.assertFalse(
			portletNames.toString(), portletNames.contains(_PORTLET_NAME_1));
		Assert.assertTrue(
			portletNames.toString(), portletNames.contains(_PORTLET_NAME_2));

		modelNames = ResourceActionsUtil.getModelNames();

		Assert.assertTrue(
			modelNames.toString(), modelNames.contains(_MODEL_NAME));

		ResourceActionsUtil.removePortletResource(_PORTLET_NAME_2);

		portletNames = ResourceActionsUtil.getPortletNames();

		Assert.assertFalse(
			portletNames.toString(), portletNames.contains(_PORTLET_NAME_1));
		Assert.assertFalse(
			portletNames.toString(), portletNames.contains(_PORTLET_NAME_2));

		modelNames = ResourceActionsUtil.getModelNames();

		Assert.assertFalse(
			modelNames.toString(), modelNames.contains(_MODEL_NAME));
	}

	private static final String _MODEL_NAME =
		"com.liferay.test.portlet.TestModel";

	private static final String _PORTLET_NAME_1 =
		"com_liferay_test_portlet_TestPortlet1";

	private static final String _PORTLET_NAME_2 =
		"com_liferay_test_portlet_TestPortlet2";

	private static final String _SOURCE_PATH =
		"com/liferay/portal/security/permission/dependencies/";

	private static final ClassLoader _classLoader =
		ResourceActionsTest.class.getClassLoader();

}