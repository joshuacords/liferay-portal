/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.cluster;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch7.internal.connection.EmbeddedElasticsearchConnection;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.elasticsearch.cluster.service.ClusterService;
import org.elasticsearch.cluster.service.MasterService;
import org.elasticsearch.common.inject.Injector;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.node.Node;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author William Newbury
 */
public class ClusterSettingsTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_testCluster.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_testCluster.tearDown();
	}

	@Test
	public void testClusterSettings() throws Exception {
		ElasticsearchFixture elasticsearchFixture = _testCluster.getNode(0);

		EmbeddedElasticsearchConnection embeddedElasticsearchConnection =
			elasticsearchFixture.getEmbeddedElasticsearchConnection();

		Node node = embeddedElasticsearchConnection.getNode();

		Injector injector = node.injector();

		ClusterService clusterService = injector.getInstance(
			ClusterService.class);

		MasterService masterService = clusterService.getMasterService();

		TimeValue slowTaskLoggingThreshold = ReflectionTestUtil.getFieldValue(
			masterService, "slowTaskLoggingThreshold");

		Assert.assertEquals("10s", slowTaskLoggingThreshold.toString());
	}

	private final TestCluster _testCluster = new TestCluster(1, this);

}