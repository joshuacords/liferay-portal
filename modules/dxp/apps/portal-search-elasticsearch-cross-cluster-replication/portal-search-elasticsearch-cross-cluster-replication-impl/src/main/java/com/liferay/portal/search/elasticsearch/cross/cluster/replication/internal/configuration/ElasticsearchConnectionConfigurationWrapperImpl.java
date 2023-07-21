/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.configuration.ElasticsearchConnectionConfigurationWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bryan Engler
 */
@Component(
	immediate = true,
	service = ElasticsearchConnectionConfigurationWrapper.class
)
public class ElasticsearchConnectionConfigurationWrapperImpl
	implements ElasticsearchConnectionConfigurationWrapper {

	@Override
	public String getCertificateFormat(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.certificateFormat();
	}

	@Override
	public String getClientTransportNodesSamplerInterval(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.
			clientTransportNodesSamplerInterval();
	}

	@Override
	public String getClientTransportPingTimeout(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.
			clientTransportPingTimeout();
	}

	@Override
	public String getClusterName(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.clusterName();
	}

	@Override
	public String getNetworkHostAddress(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.networkHostAddress();
	}

	@Override
	public String getPassword(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.password();
	}

	@Override
	public String[] getSslCertificateAuthoritiesPaths(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.
			sslCertificateAuthoritiesPaths();
	}

	@Override
	public String getSslCertificatePath(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.sslCertificatePath();
	}

	@Override
	public String getSslKeyPath(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.sslKeyPath();
	}

	@Override
	public String getSslKeystorePassword(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.sslKeystorePassword();
	}

	@Override
	public String getSslKeystorePath(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.sslKeystorePath();
	}

	@Override
	public String getSslTruststorePassword(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.sslTruststorePassword();
	}

	@Override
	public String getSslTruststorePath(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.sslTruststorePath();
	}

	@Override
	public String[] getTransportAddresses(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.transportAddresses();
	}

	@Override
	public String getTransportSSLVerificationMode(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.
			transportSSLVerificationMode();
	}

	@Override
	public String getUsername(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.username();
	}

	@Override
	public boolean isAuthenticationEnabled(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.authenticationEnabled();
	}

	@Override
	public boolean isClientTransportIgnoreClusterName(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.
			clientTransportIgnoreClusterName();
	}

	@Override
	public boolean isClientTransportSniff(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.clientTransportSniff();
	}

	@Override
	public boolean isTransportSSLEnabled(String connectionId) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				_elasticsearchConnectionConfigurations.get(connectionId);

		return elasticsearchConnectionConfiguration.transportSSLEnabled();
	}

	protected void addElasticsearchConnectionConfiguration(
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration) {

		if (!Validator.isBlank(
				elasticsearchConnectionConfiguration.connectionId())) {

			_elasticsearchConnectionConfigurations.put(
				elasticsearchConnectionConfiguration.connectionId(),
				elasticsearchConnectionConfiguration);
		}
	}

	protected void removeElasticsearchConnectionConfiguration(
		String connectionId) {

		_elasticsearchConnectionConfigurations.remove(connectionId);
	}

	private final Map<String, ElasticsearchConnectionConfiguration>
		_elasticsearchConnectionConfigurations = new HashMap<>();

}