/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.cluster;

import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch6.internal.connection.Index;
import com.liferay.portal.search.elasticsearch6.internal.connection.IndexCreator;
import com.liferay.portal.search.elasticsearch6.internal.connection.IndexName;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * @author André de Oliveira
 */
public class Cluster2InstancesTest {

	@ClassRule
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
	public void test2Nodes1PrimaryShard() throws Exception {
		ElasticsearchFixture elasticsearchFixture0 = _testCluster.getNode(0);

		createIndex(elasticsearchFixture0);

		ClusterAssert.assert1PrimaryShardAnd2Nodes(elasticsearchFixture0);

		ElasticsearchFixture elasticsearchFixture1 = _testCluster.getNode(1);

		createIndex(elasticsearchFixture1);

		ClusterAssert.assert1PrimaryShardAnd2Nodes(elasticsearchFixture1);
	}

	@Test
	public void testExpandAndShrink() throws Exception {
		ElasticsearchFixture elasticsearchFixture0 = _testCluster.getNode(0);

		Index index0 = createIndex(elasticsearchFixture0);

		ElasticsearchFixture elasticsearchFixture1 = _testCluster.getNode(1);

		Index index1 = createIndex(elasticsearchFixture1);

		updateNumberOfReplicas(1, index1, elasticsearchFixture1);

		ClusterAssert.assert1ReplicaShard(elasticsearchFixture0);
		ClusterAssert.assert1ReplicaShard(elasticsearchFixture1);

		updateNumberOfReplicas(0, index0, elasticsearchFixture0);

		ClusterAssert.assert1PrimaryShardAnd2Nodes(elasticsearchFixture0);
		ClusterAssert.assert1PrimaryShardAnd2Nodes(elasticsearchFixture1);
	}

	@Rule
	public TestName testName = new TestName();

	protected Index createIndex(ElasticsearchFixture elasticsearchFixture) {
		IndexCreator indexCreator = new IndexCreator() {
			{
				setElasticsearchClientResolver(elasticsearchFixture);
			}
		};

		return indexCreator.createIndex(
			new IndexName(testName.getMethodName()));
	}

	protected void updateNumberOfReplicas(
		int numberOfReplicas, Index index,
		ElasticsearchFixture elasticsearchFixture) {

		ReplicasManager replicasManager = new ReplicasManagerImpl(
			elasticsearchFixture.getIndicesAdminClient());

		replicasManager.updateNumberOfReplicas(
			numberOfReplicas, index.getName());
	}

	private final TestCluster _testCluster = new TestCluster(2, this);

}