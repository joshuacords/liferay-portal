/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.connection;

import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.InputStream;

import java.net.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.cluster.node.info.NodeInfo;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequestBuilder;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.common.transport.BoundTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.http.HttpInfo;

import org.hamcrest.CoreMatchers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.MockitoAnnotations;

/**
 * @author André de Oliveira
 */
public class EmbeddedElasticsearchConnectionHttpTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		_clusterName = RandomTestUtil.randomString();

		Map<String, Object> properties = new HashMap<String, Object>() {
			{
				put("clusterName", _clusterName);
				put("networkHost", "_site_");
			}
		};

		_elasticsearchFixture = new ElasticsearchFixture(
			EmbeddedElasticsearchConnectionHttpTest.class.getSimpleName(),
			properties);

		_elasticsearchFixture.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Test
	public void testHttpLocallyAvailableRegardlessOfNetworkHost()
		throws Exception {

		String status = toString(new URL("http://localhost:" + getHttpPort()));

		Assert.assertThat(
			status,
			CoreMatchers.containsString(
				"\"cluster_name\" : \"" + _clusterName));
	}

	protected int getHttpPort() {
		AdminClient adminClient = _elasticsearchFixture.getAdminClient();

		ClusterAdminClient clusterAdminClient = adminClient.cluster();

		NodesInfoRequestBuilder nodesInfoRequestBuilder =
			clusterAdminClient.prepareNodesInfo();

		NodesInfoResponse nodesInfoResponse = nodesInfoRequestBuilder.get();

		List<NodeInfo> nodeInfos = nodesInfoResponse.getNodes();

		NodeInfo nodeInfo = nodeInfos.get(0);

		HttpInfo httpInfo = nodeInfo.getHttp();

		BoundTransportAddress boundTransportAddress = httpInfo.address();

		TransportAddress transportAddress =
			boundTransportAddress.publishAddress();

		return transportAddress.getPort();
	}

	protected String toString(URL url) throws Exception {
		try (InputStream inputStream = url.openStream()) {
			return StringUtil.read(inputStream);
		}
	}

	private String _clusterName;
	private ElasticsearchFixture _elasticsearchFixture;

}