/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.json.web.service.client.internal;

import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.server.simulator.HTTPServerSimulator;
import com.liferay.petra.json.web.service.client.server.simulator.SimulatorConstants;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class JSONWebServiceClientImplDeleteTest
	extends BaseJSONWebServiceClientTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		HTTPServerSimulator.start();
	}

	@After
	public void tearDown() {
		HTTPServerSimulator.stop();
	}

	@Test(expected = JSONWebServiceInvocationException.class)
	public void testBadRequestOnDelete() throws Exception {
		JSONWebServiceClientImpl jsonWebServiceClientImpl =
			new JSONWebServiceClientImpl();

		Map<String, Object> properties = getBaseProperties();

		properties.put("protocol", "http");

		jsonWebServiceClientImpl.activate(properties);

		jsonWebServiceClientImpl.doDelete(
			"/", Collections.<String, String>emptyMap());
	}

	@Test
	public void testResponse200OnDelete() throws Exception {
		JSONWebServiceClientImpl jsonWebServiceClientImpl =
			new JSONWebServiceClientImpl();

		Map<String, Object> properties = getBaseProperties();

		properties.put(
			"headers", "headerKey1=headerValue1;Accept=application/json;");
		properties.put("protocol", "http");

		jsonWebServiceClientImpl.activate(properties);

		Map<String, String> params = new HashMap<String, String>();

		params.put(
			SimulatorConstants.HTTP_PARAMETER_RESPOND_WITH_STATUS, "200");
		params.put(
			SimulatorConstants.HTTP_PARAMETER_RETURN_PARMS_IN_JSON, "true");

		String json = jsonWebServiceClientImpl.doDelete("/testDelete/", params);

		Assert.assertTrue(
			json,
			json.contains(
				SimulatorConstants.HTTP_PARAMETER_RESPOND_WITH_STATUS));
	}

	@Test
	public void testResponse202OnDelete() throws Exception {
		JSONWebServiceClientImpl jsonWebServiceClientImpl =
			new JSONWebServiceClientImpl();

		Map<String, Object> properties = getBaseProperties();

		properties.put(
			"headers", "headerKey1=headerValue1;Accept=application/json;");
		properties.put("protocol", "http");

		jsonWebServiceClientImpl.activate(properties);

		Map<String, String> params = new HashMap<String, String>();

		params.put(
			SimulatorConstants.HTTP_PARAMETER_RESPOND_WITH_STATUS, "202");
		params.put(
			SimulatorConstants.HTTP_PARAMETER_RETURN_PARMS_IN_JSON, "true");

		String json = jsonWebServiceClientImpl.doDelete("/testDelete/", params);

		Assert.assertEquals(SimulatorConstants.RESPONSE_SUCCESS_IN_JSON, json);
	}

	@Test
	public void testResponse204OnDelete() throws Exception {
		JSONWebServiceClientImpl jsonWebServiceClientImpl =
			new JSONWebServiceClientImpl();

		Map<String, Object> properties = getBaseProperties();

		properties.put(
			"headers", "Accept=application/json;headerKey1=headerValue1");
		properties.put("protocol", "http");

		jsonWebServiceClientImpl.activate(properties);

		Map<String, String> params = new HashMap<String, String>();

		params.put(
			SimulatorConstants.HTTP_PARAMETER_RESPOND_WITH_STATUS, "204");
		params.put(
			SimulatorConstants.HTTP_PARAMETER_RETURN_PARMS_IN_JSON, "true");

		String json = jsonWebServiceClientImpl.doDelete("/testDelete/", params);

		Assert.assertNull(json);
	}

	@Test(expected = JSONWebServiceInvocationException.class)
	public void testResponse405OnDelete() throws Exception {
		JSONWebServiceClientImpl jsonWebServiceClientImpl =
			new JSONWebServiceClientImpl();

		Map<String, Object> properties = getBaseProperties();

		properties.put(
			"headers", "Accept=application/json;headerKey1=headerValue1");
		properties.put("protocol", "http");

		jsonWebServiceClientImpl.activate(properties);

		Map<String, String> params = new HashMap<String, String>();

		params.put(
			SimulatorConstants.HTTP_PARAMETER_RESPOND_WITH_STATUS, "405");
		params.put(
			SimulatorConstants.HTTP_PARAMETER_RETURN_PARMS_IN_JSON, "true");

		String json = jsonWebServiceClientImpl.doDelete("/testDelete/", params);

		Assert.assertNull(json);
	}

}