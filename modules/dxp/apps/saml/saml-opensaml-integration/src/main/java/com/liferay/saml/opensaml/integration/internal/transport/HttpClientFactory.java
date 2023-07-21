/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.transport;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.saml.opensaml.integration.internal.transport.configuration.HttpClientFactoryConfiguration;

import java.io.IOException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.saml.opensaml.integration.internal.transport.configuration.HttpClientFactoryConfiguration",
	immediate = true, service = HttpClientFactory.class
)
public class HttpClientFactory {

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		_poolingClientConnectionManager =
			new PoolingHttpClientConnectionManager();

		HttpClientFactoryConfiguration httpClientFactoryConfiguration =
			ConfigurableUtil.createConfigurable(
				HttpClientFactoryConfiguration.class, properties);

		_poolingClientConnectionManager.setDefaultMaxPerRoute(
			httpClientFactoryConfiguration.defaultMaxConnectionsPerRoute());

		SocketConfig.Builder socketConfigBuilder = SocketConfig.custom();

		socketConfigBuilder.setSoTimeout(
			httpClientFactoryConfiguration.soTimeout());

		_poolingClientConnectionManager.setDefaultSocketConfig(
			socketConfigBuilder.build());

		_poolingClientConnectionManager.setMaxTotal(
			httpClientFactoryConfiguration.maxTotalConnections());

		httpClientBuilder.setConnectionManager(_poolingClientConnectionManager);

		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();

		requestConfigBuilder.setConnectTimeout(
			httpClientFactoryConfiguration.connectionManagerTimeout());

		httpClientBuilder.setDefaultRequestConfig(requestConfigBuilder.build());

		httpClientBuilder.setRetryHandler(
			new DefaultHttpRequestRetryHandler(0, false));

		_closeableHttpClient = httpClientBuilder.build();

		_httpClientServiceRegistration = bundleContext.registerService(
			HttpClient.class, _closeableHttpClient, null);
	}

	@Deactivate
	protected void deactivate() {
		if (_closeableHttpClient != null) {
			try {
				_closeableHttpClient.close();
			}
			catch (IOException ioException) {
				if (_log.isDebugEnabled()) {
					_log.debug(ioException, ioException);
				}
			}
		}

		if (_httpClientServiceRegistration != null) {
			_httpClientServiceRegistration.unregister();

			_httpClientServiceRegistration = null;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Shutting down " + getClass().getName());
		}

		if (_poolingClientConnectionManager == null) {
			return;
		}

		int retry = 0;

		while (retry < 10) {
			PoolStats poolStats =
				_poolingClientConnectionManager.getTotalStats();

			int availableConnections = poolStats.getAvailable();

			if (availableConnections <= 0) {
				break;
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						getClass().getName(), " is waiting on ",
						availableConnections, " connections"));
			}

			_poolingClientConnectionManager.closeIdleConnections(
				200, TimeUnit.MILLISECONDS);

			try {
				Thread.sleep(500);
			}
			catch (InterruptedException interruptedException) {
			}

			retry++;
		}

		_poolingClientConnectionManager.shutdown();

		_poolingClientConnectionManager = null;

		if (_log.isDebugEnabled()) {
			_log.debug(toString() + " was shut down");
		}
	}

	@Modified
	protected void modified(
		BundleContext bundleContext, Map<String, Object> properties) {

		deactivate();

		activate(bundleContext, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HttpClientFactory.class);

	private CloseableHttpClient _closeableHttpClient;
	private ServiceRegistration<HttpClient> _httpClientServiceRegistration;
	private PoolingHttpClientConnectionManager _poolingClientConnectionManager;

}