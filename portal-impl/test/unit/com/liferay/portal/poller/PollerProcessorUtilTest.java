/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.poller;

import com.liferay.portal.kernel.poller.PollerProcessor;
import com.liferay.portal.kernel.resiliency.spi.SPIRegistryUtil;
import com.liferay.portal.kernel.util.ProxyFactory;
import com.liferay.portal.resiliency.spi.SPIRegistryImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.util.Collections;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Leon Chi
 */
public class PollerProcessorUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGetPollerProcessor() {
		PollerProcessor pollerProcessor = ProxyFactory.newDummyInstance(
			PollerProcessor.class);

		SPIRegistryUtil spiRegistryUtil = new SPIRegistryUtil();

		spiRegistryUtil.setSPIRegistry(new SPIRegistryImpl());

		RegistryUtil.setRegistry(new BasicRegistryImpl());

		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<PollerProcessor> serviceRegistration =
			registry.registerService(
				PollerProcessor.class, pollerProcessor,
				Collections.singletonMap(
					"javax.portlet.name", _TEST_PORTLET_ID));

		try {
			Assert.assertSame(
				pollerProcessor,
				PollerProcessorUtil.getPollerProcessor(_TEST_PORTLET_ID));
		}
		finally {
			serviceRegistration.unregister();
		}
	}

	private static final String _TEST_PORTLET_ID = "TEST_PORTLET_ID";

}