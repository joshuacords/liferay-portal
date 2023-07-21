/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InetAddressUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.configuration.ElasticsearchConnectionConfigurationWrapper;
import com.liferay.portal.search.elasticsearch7.internal.settings.SettingsBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.Map;
import java.util.Set;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @author Bryan Engler
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.ElasticsearchConnectionConfiguration",
	immediate = true, property = "operation.mode=CCR",
	service = CCRElasticsearchConnection.class
)
public class CCRElasticsearchConnection extends BaseElasticsearchConnection {

	@Override
	public String getConnectionId() {
		return _connectionId;
	}

	@Override
	public OperationMode getOperationMode() {
		return OperationMode.REMOTE;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_connectionId = (String)properties.get("connectionId");

		if (!Validator.isBlank(_connectionId)) {
			reconnect();

			elasticsearchConnectionManager.addCCRElasticsearchConnection(this);
		}
	}

	protected void addTransportAddress(
			TransportClient transportClient, String transportAddress)
		throws UnknownHostException {

		String[] transportAddressParts = StringUtil.split(
			transportAddress, StringPool.COLON);

		String host = transportAddressParts[0];

		int port = GetterUtil.getInteger(transportAddressParts[1]);

		InetAddress inetAddress = InetAddressUtil.getInetAddressByName(host);

		transportClient.addTransportAddress(
			new TransportAddress(inetAddress, port));
	}

	protected void configureAuthentication(SettingsBuilder settingsBuilder) {
		String username =
			elasticsearchConnectionConfigurationWrapper.getUsername(
				_connectionId);

		String password =
			elasticsearchConnectionConfigurationWrapper.getPassword(
				_connectionId);

		String user = username + StringPool.COLON + password;

		settingsBuilder.put("xpack.security.user", user);
	}

	protected void configurePEMPaths(SettingsBuilder settingsBuilder) {
		settingsBuilder.put(
			"xpack.security.transport.ssl.certificate",
			elasticsearchConnectionConfigurationWrapper.getSslCertificatePath(
				_connectionId));
		settingsBuilder.putList(
			"xpack.security.transport.ssl.certificate_authorities",
			elasticsearchConnectionConfigurationWrapper.
				getSslCertificateAuthoritiesPaths(_connectionId));
		settingsBuilder.put(
			"xpack.security.transport.ssl.key",
			elasticsearchConnectionConfigurationWrapper.getSslKeyPath(
				_connectionId));
	}

	protected void configurePKCSPaths(SettingsBuilder settingsBuilder) {
		settingsBuilder.put(
			"xpack.security.transport.ssl.keystore.password",
			elasticsearchConnectionConfigurationWrapper.getSslKeystorePassword(
				_connectionId));
		settingsBuilder.put(
			"xpack.security.transport.ssl.keystore.path",
			elasticsearchConnectionConfigurationWrapper.getSslKeystorePath(
				_connectionId));
		settingsBuilder.put(
			"xpack.security.transport.ssl.truststore.password",
			elasticsearchConnectionConfigurationWrapper.
				getSslTruststorePassword(_connectionId));
		settingsBuilder.put(
			"xpack.security.transport.ssl.truststore.path",
			elasticsearchConnectionConfigurationWrapper.getSslTruststorePath(
				_connectionId));
	}

	protected void configureSSL(SettingsBuilder settingsBuilder) {
		settingsBuilder.put("xpack.security.transport.ssl.enabled", "true");
		settingsBuilder.put(
			"xpack.security.transport.ssl.verification_mode",
			StringUtil.toLowerCase(
				elasticsearchConnectionConfigurationWrapper.
					getTransportSSLVerificationMode(_connectionId)));

		String certificateFormat =
			elasticsearchConnectionConfigurationWrapper.getCertificateFormat(
				_connectionId);

		if (certificateFormat.equals("pkcs12")) {
			configurePKCSPaths(settingsBuilder);
		}
		else {
			configurePEMPaths(settingsBuilder);
		}
	}

	@Override
	protected Client createClient() {
		Set<String> transportAddresses = SetUtil.fromArray(
			elasticsearchConnectionConfigurationWrapper.getTransportAddresses(
				_connectionId));

		if (transportAddresses.isEmpty()) {
			throw new IllegalStateException(
				"There must be at least one transport address");
		}

		Thread thread = Thread.currentThread();

		ClassLoader contextClassLoader = thread.getContextClassLoader();

		Class<?> clazz = getClass();

		thread.setContextClassLoader(clazz.getClassLoader());

		try {
			TransportClient transportClient = createTransportClient();

			for (String transportAddress : transportAddresses) {
				try {
					addTransportAddress(transportClient, transportAddress);
				}
				catch (Exception exception) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to add transport address " +
								transportAddress,
							exception);
					}
				}
			}

			return transportClient;
		}
		finally {
			thread.setContextClassLoader(contextClassLoader);
		}
	}

	protected TransportClient createTransportClient() {
		if (elasticsearchConnectionConfigurationWrapper.isAuthenticationEnabled(
				_connectionId)) {

			configureAuthentication(settingsBuilder);
		}

		if (elasticsearchConnectionConfigurationWrapper.isTransportSSLEnabled(
				_connectionId)) {

			configureSSL(settingsBuilder);
		}

		Settings settings = settingsBuilder.build();

		if (_log.isDebugEnabled()) {
			_log.debug("Settings: " + settings.toString());
		}

		if (elasticsearchConnectionConfigurationWrapper.isAuthenticationEnabled(
				_connectionId) ||
			elasticsearchConnectionConfigurationWrapper.isTransportSSLEnabled(
				_connectionId)) {

			return new PreBuiltXPackTransportClient(settings);
		}

		return new PreBuiltTransportClient(settings);
	}

	@Deactivate
	protected void deactivate(Map<String, Object> properties) {
		close();

		elasticsearchConnectionManager.removeCCRElasticsearchConnection(this);
	}

	@Override
	protected void loadRequiredDefaultConfigurations() {
		settingsBuilder.put(
			"client.transport.ignore_cluster_name",
			elasticsearchConnectionConfigurationWrapper.
				isClientTransportIgnoreClusterName(_connectionId));
		settingsBuilder.put(
			"client.transport.nodes_sampler_interval",
			elasticsearchConnectionConfigurationWrapper.
				getClientTransportNodesSamplerInterval(_connectionId));
		settingsBuilder.put(
			"client.transport.ping_timeout",
			elasticsearchConnectionConfigurationWrapper.
				getClientTransportPingTimeout(_connectionId));
		settingsBuilder.put(
			"client.transport.sniff",
			elasticsearchConnectionConfigurationWrapper.isClientTransportSniff(
				_connectionId));
		settingsBuilder.put(
			"cluster.name",
			elasticsearchConnectionConfigurationWrapper.getClusterName(
				_connectionId));
		settingsBuilder.put(
			"request.headers.X-Found-Cluster",
			elasticsearchConnectionConfigurationWrapper.getClusterName(
				_connectionId));
	}

	protected void reconnect() {
		if (isConnected()) {
			close();
		}

		if (!isConnected()) {
			connect();
		}
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile ElasticsearchConnectionConfigurationWrapper
		elasticsearchConnectionConfigurationWrapper;

	@Reference
	protected volatile ElasticsearchConnectionManager
		elasticsearchConnectionManager;

	@Reference
	protected Props props;

	private static final Log _log = LogFactoryUtil.getLog(
		CCRElasticsearchConnection.class);

	private String _connectionId;

}