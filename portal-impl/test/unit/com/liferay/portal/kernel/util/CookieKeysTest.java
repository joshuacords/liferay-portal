/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.PropsImpl;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Raymond Augé
 */
public class CookieKeysTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		PropsUtil.setProps(new PropsImpl());
	}

	@Test
	public void testDomain1() throws Exception {
		String domain = CookieKeys.getDomain("www.liferay.com");

		Assert.assertEquals(".liferay.com", domain);
	}

	@Test
	public void testDomain2() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		String domain = CookieKeys.getDomain(mockHttpServletRequest);

		Assert.assertEquals(StringPool.BLANK, domain);
	}

	@Test
	public void testDomain3() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		Field field = ReflectionUtil.getDeclaredField(
			CookieKeys.class, "_SESSION_COOKIE_DOMAIN");

		Object value = field.get(null);

		try {
			field.set(null, "www.example.com");

			String domain = CookieKeys.getDomain(mockHttpServletRequest);

			Assert.assertEquals("www.example.com", domain);
		}
		finally {
			field.set(null, value);
		}
	}

	@Test
	public void testDomain4() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		Field field = ReflectionUtil.getDeclaredField(
			CookieKeys.class, "_SESSION_COOKIE_USE_FULL_HOSTNAME");

		Object value = field.get(null);

		try {
			field.set(null, Boolean.FALSE);

			String domain = CookieKeys.getDomain(mockHttpServletRequest);

			Assert.assertEquals(".liferay.com", domain);
		}
		finally {
			field.set(null, value);
		}
	}

	@Test
	public void testDomain5() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		Field field = ReflectionUtil.getDeclaredField(
			CookieKeys.class, "_SESSION_COOKIE_USE_FULL_HOSTNAME");

		Object value = field.get(null);

		try {
			field.set(null, Boolean.TRUE);

			String domain = CookieKeys.getDomain(mockHttpServletRequest);

			Assert.assertEquals(StringPool.BLANK, domain);
		}
		finally {
			field.set(null, value);
		}
	}

}