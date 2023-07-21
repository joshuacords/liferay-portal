/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.remote.cors.client.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.remote.cors.configuration.PortalCORSConfiguration;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Dictionary;

import javax.ws.rs.HttpMethod;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Marta Medio
 */
@RunWith(Arquillian.class)
public class CORSConfigurationPortalTest extends BaseCORSClientTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testCORSUsingBasic() throws Exception {
		assertJsonWSUrl("/user/get-current-user", HttpMethod.OPTIONS, false);
		assertJsonWSUrl("/user/get-current-user", HttpMethod.GET, false);

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("configuration.name", "test-cors");

		properties.put("filter.mapping.url.pattern", "/api/jsonws/*");

		createFactoryConfiguration(
			PortalCORSConfiguration.class.getName(), properties);

		assertJsonWSUrl("/user/get-current-user", HttpMethod.OPTIONS, true);
		assertJsonWSUrl("/user/get-current-user", HttpMethod.GET, false);
	}

}