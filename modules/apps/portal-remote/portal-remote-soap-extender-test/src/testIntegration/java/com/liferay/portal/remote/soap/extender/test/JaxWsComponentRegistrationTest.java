/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.remote.soap.extender.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.net.URL;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.BundleActivator;

/**
 * @author Carlos Sierra Andrés
 */
@RunWith(Arquillian.class)
public class JaxWsComponentRegistrationTest extends BaseJaxWsTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testIsRegistered() throws Exception {
		String greeting = getGreeting(
			"http://localhost:8080/o/soap-test/greeter?wsdl");

		Assert.assertEquals("Greetings.", greeting);
	}

	@Test(expected = Exception.class)
	public void testServiceListIsUnavailable() throws Exception {
		URL url = new URL("http://localhost:8080/o/soap-test/services");

		StringUtil.read(url.openStream());
	}

	@Override
	protected BundleActivator getBundleActivator() {
		return new GreeterBundleActivator();
	}

}