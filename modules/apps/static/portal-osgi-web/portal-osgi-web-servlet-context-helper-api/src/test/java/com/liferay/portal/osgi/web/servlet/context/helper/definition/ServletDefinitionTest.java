/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.osgi.web.servlet.context.helper.definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Miguel Pastor
 */
@RunWith(PowerMockRunner.class)
public class ServletDefinitionTest {

	@Before
	public void setUp() {
		_servletDefinition = new ServletDefinition();
	}

	@Test
	public void testAddMultipleURLPatterns() {
		String urlPattern = "/o/module";

		List<String> urlPatterns = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			urlPatterns.add(urlPattern + "/" + i);
		}

		_servletDefinition.setURLPatterns(urlPatterns);

		urlPatterns = _servletDefinition.getURLPatterns();

		Assert.assertEquals(urlPatterns.toString(), 10, urlPatterns.size());

		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(urlPattern + "/" + i, urlPatterns.get(i));
		}
	}

	@Test
	public void testAddSingleURLPattern() {
		String urlPattern = "/o/module";

		_servletDefinition.addURLPattern(urlPattern);

		List<String> urlPatterns = _servletDefinition.getURLPatterns();

		Assert.assertEquals(urlPatterns.toString(), 1, urlPatterns.size());
		Assert.assertEquals(urlPattern, urlPatterns.get(0));
	}

	@Test
	public void testSetMultipleInitParameters() {
		Map<String, String> initParameters = new HashMap<>();

		for (int i = 0; i < 10; i++) {
			initParameters.put("parameter-" + i, String.valueOf(i));
		}

		_servletDefinition.setInitParameters(initParameters);

		initParameters = _servletDefinition.getInitParameters();

		Assert.assertEquals(
			initParameters.toString(), 10, initParameters.size());

		for (int i = 0; i < 10; i++) {
			String expectedValue = String.valueOf(i);
			String value = initParameters.get("parameter-" + i);

			Assert.assertEquals(expectedValue, value);
		}
	}

	@Test
	public void testSetServlet() {
		_servletDefinition.setServlet(_servlet);

		Assert.assertEquals(_servlet, _servletDefinition.getServlet());
	}

	@Test
	public void testSetServletName() {
		String servletName = "Module Servlet";

		_servletDefinition.setName(servletName);

		Assert.assertEquals(servletName, _servletDefinition.getName());
	}

	@Test
	public void testSetSingleInitParameter() {
		String key = "parameter";
		String value = "1";

		_servletDefinition.setInitParameter(key, value);

		Map<String, String> initParameters =
			_servletDefinition.getInitParameters();

		Assert.assertEquals(
			initParameters.toString(), 1, initParameters.size());
		Assert.assertEquals(value, initParameters.get(key));
	}

	@Mock
	private Servlet _servlet;

	private ServletDefinition _servletDefinition;

}