/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.remote.axis.extender.client.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.remote.axis.extender.test.service.http.CalcServiceSoap;
import com.liferay.portal.remote.axis.extender.test.service.http.CalcServiceSoapService;
import com.liferay.portal.remote.axis.extender.test.service.http.CalcServiceSoapServiceLocator;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.net.URL;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Carlos Sierra Andrés
 */
@RunWith(Arquillian.class)
public class AxisExtenderTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testGreeter() throws Exception {
		URL url = new URL(
			"http://localhost:8080/o/com.liferay.portal.remote.axis.extender." +
				"test/api/axis/CalcService?wsdl");

		CalcServiceSoapService calcServiceSoapService =
			new CalcServiceSoapServiceLocator();

		CalcServiceSoap calcServiceSoap =
			calcServiceSoapService.getCalcServiceSoapPort(url);

		Assert.assertEquals(5, calcServiceSoap.sum(2, 3));
	}

}