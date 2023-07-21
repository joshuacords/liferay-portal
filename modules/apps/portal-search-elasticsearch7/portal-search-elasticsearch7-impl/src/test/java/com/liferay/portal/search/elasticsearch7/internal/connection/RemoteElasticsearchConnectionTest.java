/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.search.elasticsearch7.configuration.OperationMode;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.net.InetSocketAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.TransportAddress;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class RemoteElasticsearchConnectionTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_remoteElasticsearchConnection = new RemoteElasticsearchConnection();

		_remoteElasticsearchConnection.props = PropsTestUtil.setProps(
			new HashMap<String, Object>() {
				{
					put(
						PropsKeys.DNS_SECURITY_ADDRESS_TIMEOUT_SECONDS,
						String.valueOf(2));
					put(
						PropsKeys.DNS_SECURITY_THREAD_LIMIT,
						String.valueOf(10));
					put("configuration.override.", new Properties());
				}
			});
	}

	@Test
	public void testModifyConnected() {
		HashMap<String, Object> properties = new HashMap<>();

		properties.put("operationMode", OperationMode.REMOTE.name());

		_remoteElasticsearchConnection.activate(properties);

		Assert.assertFalse(_remoteElasticsearchConnection.isConnected());

		_remoteElasticsearchConnection.connect();

		Assert.assertTrue(_remoteElasticsearchConnection.isConnected());

		assertTransportAddress("localhost", 9300);

		properties.put("transportAddresses", "127.0.0.1:9999");

		_remoteElasticsearchConnection.modified(properties);

		Assert.assertTrue(_remoteElasticsearchConnection.isConnected());

		assertTransportAddress("127.0.0.1", 9999);
	}

	@Test
	public void testModifyConnectedWithInvalidPropertiesThenValidProperties() {
		HashMap<String, Object> properties = new HashMap<>();

		properties.put("operationMode", OperationMode.REMOTE.name());

		_remoteElasticsearchConnection.activate(properties);

		_remoteElasticsearchConnection.connect();

		properties.put(
			"additionalConfigurations",
			StringBundler.concat(
				StringPool.OPEN_CURLY_BRACE, RandomTestUtil.randomString(),
				StringPool.CLOSE_CURLY_BRACE));

		try {
			_remoteElasticsearchConnection.modified(properties);

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
		}

		Assert.assertFalse(_remoteElasticsearchConnection.isConnected());

		properties.replace("additionalConfigurations", StringPool.BLANK);

		_remoteElasticsearchConnection.modified(properties);

		Assert.assertTrue(_remoteElasticsearchConnection.isConnected());
	}

	@Test
	public void testModifyUnconnected() {
		Assert.assertFalse(_remoteElasticsearchConnection.isConnected());

		HashMap<String, Object> properties = new HashMap<>();

		properties.put("operationMode", OperationMode.REMOTE.name());

		_remoteElasticsearchConnection.modified(properties);

		Assert.assertTrue(_remoteElasticsearchConnection.isConnected());
	}

	protected void assertTransportAddress(String hostString, int port) {
		TransportClient transportClient =
			(TransportClient)_remoteElasticsearchConnection.getClient();

		List<TransportAddress> transportAddresses =
			transportClient.transportAddresses();

		Assert.assertEquals(
			transportAddresses.toString(), 1, transportAddresses.size());

		TransportAddress transportAddress = transportAddresses.get(0);

		InetSocketAddress inetSocketAddress = transportAddress.address();

		Assert.assertEquals(hostString, inetSocketAddress.getHostString());
		Assert.assertEquals(port, inetSocketAddress.getPort());
	}

	private RemoteElasticsearchConnection _remoteElasticsearchConnection;

}