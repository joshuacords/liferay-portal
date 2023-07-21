/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.configuration;

import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchFixture;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Map;
import java.util.Properties;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class ElasticsearchConfigurationTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testConfigurationsFromBuildTestXmlAntFile() throws Exception {
		Map<String, Object> configurationProperties =
			loadConfigurationProperties(
				"ElasticsearchConfigurationTest-build-test-xml.cfg");

		Class<? extends ElasticsearchConfigurationTest> clazz = getClass();

		ElasticsearchFixture elasticsearchFixture = new ElasticsearchFixture(
			clazz.getSimpleName(), configurationProperties);

		try {
			elasticsearchFixture.createNode();
		}
		finally {
			elasticsearchFixture.destroyNode();
		}
	}

	protected Map<String, Object> loadConfigurationProperties(String fileName)
		throws Exception {

		Properties properties = new Properties();

		Class<?> clazz = getClass();

		properties.load(clazz.getResourceAsStream(fileName));

		return PropertiesUtil.toMap(properties);
	}

}