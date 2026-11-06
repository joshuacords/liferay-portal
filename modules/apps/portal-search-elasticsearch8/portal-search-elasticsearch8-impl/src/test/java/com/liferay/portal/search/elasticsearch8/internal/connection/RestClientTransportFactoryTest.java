/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch8.internal.connection;

import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.execchain.ClientExecChain;
import org.apache.http.impl.execchain.MainClientExec;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.protocol.BasicHttpContext;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 * @author Felipe Lorenz
 */
public class RestClientTransportFactoryTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testNewRestClientTransport() {
		List<CloseableHttpClient> closeableHttpClients = new ArrayList<>();

		try (MockedStatic<HttpClientBuilder> httpClientBuilderMockedStatic =
				Mockito.mockStatic(
					HttpClientBuilder.class, Mockito.CALLS_REAL_METHODS)) {

			httpClientBuilderMockedStatic.when(
				HttpClientBuilder::create
			).thenAnswer(
				createInvocation -> {
					HttpClientBuilder httpClientBuilder = Mockito.spy(
						(HttpClientBuilder)createInvocation.callRealMethod());

					Mockito.doAnswer(
						buildInvocation -> {
							CloseableHttpClient closeableHttpClient =
								(CloseableHttpClient)
									buildInvocation.callRealMethod();

							closeableHttpClients.add(closeableHttpClient);

							return closeableHttpClient;
						}
					).when(
						httpClientBuilder
					).build();

					return httpClientBuilder;
				}
			);

			_restClientTransportFactory.newRestClientTransport();
		}

		Assert.assertEquals(
			closeableHttpClients.toString(), 1, closeableHttpClients.size());

		ConnectionKeepAliveStrategy connectionKeepAliveStrategy =
			_getConnectionKeepAliveStrategy(closeableHttpClients.get(0));

		_assertGetKeepAliveDuration(connectionKeepAliveStrategy, 180000, -1);
		_assertGetKeepAliveDuration(connectionKeepAliveStrategy, 180000, 0);
		_assertGetKeepAliveDuration(connectionKeepAliveStrategy, 180000, 600);
		_assertGetKeepAliveDuration(connectionKeepAliveStrategy, 60000, 60);
	}

	private void _assertGetKeepAliveDuration(
		ConnectionKeepAliveStrategy connectionKeepAliveStrategy,
		long expectedKeepAliveDuration, long keepAliveTimeoutHeaderValue) {

		HttpResponse httpResponse = new BasicHttpResponse(
			new BasicStatusLine(
				HttpVersion.HTTP_1_1, HttpStatus.SC_OK,
				RandomTestUtil.randomString()));

		if (keepAliveTimeoutHeaderValue > -1) {
			httpResponse.setHeader(
				HttpHeaders.KEEP_ALIVE,
				"timeout=" + keepAliveTimeoutHeaderValue);
		}

		Assert.assertEquals(
			expectedKeepAliveDuration,
			connectionKeepAliveStrategy.getKeepAliveDuration(
				httpResponse, new BasicHttpContext(null)));
	}

	private ConnectionKeepAliveStrategy _getConnectionKeepAliveStrategy(
		CloseableHttpClient closeableHttpClient) {

		ClientExecChain clientExecChain = ReflectionTestUtil.getFieldValue(
			closeableHttpClient, "execChain");

		while (true) {
			clientExecChain = ReflectionTestUtil.getFieldValue(
				clientExecChain, "requestExecutor");

			if (clientExecChain instanceof MainClientExec) {
				return ReflectionTestUtil.getFieldValue(
					clientExecChain, "keepAliveStrategy");
			}
		}
	}

	private final RestClientTransportFactory _restClientTransportFactory =
		new RestClientTransportFactory.Builder(
			new String[] {"http://" + RandomTestUtil.randomString()}
		).build();

}