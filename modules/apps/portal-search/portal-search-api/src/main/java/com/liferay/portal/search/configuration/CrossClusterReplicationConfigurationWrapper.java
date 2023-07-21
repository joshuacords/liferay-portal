/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.configuration;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Bryan Engler
 */
@ProviderType
public interface CrossClusterReplicationConfigurationWrapper {

	public String[] getCCRLocalClusterConnectionConfigurations();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getCertificateFormat(String)}
	 */
	@Deprecated
	public String getCertificateFormat();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getClusterName(String)}
	 */
	@Deprecated
	public String getClusterName();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getNetworkHostAddress(String)}
	 */
	@Deprecated
	public String getNetworkHostAddress();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getPassword(String)}
	 */
	@Deprecated
	public String getPassword();

	public String getRemoteClusterAlias();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getSslCertificateAuthoritiesPaths(String)}
	 */
	@Deprecated
	public String[] getSslCertificateAuthoritiesPaths();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getSslCertificatePath(String)}
	 */
	@Deprecated
	public String getSslCertificatePath();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getSslKeyPath(String)}
	 */
	@Deprecated
	public String getSslKeyPath();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getSslKeystorePassword(String)}
	 */
	@Deprecated
	public String getSslKeystorePassword();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getSslKeystorePath(String)}
	 */
	@Deprecated
	public String getSslKeystorePath();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getSslTruststorePassword(String)}
	 */
	@Deprecated
	public String getSslTruststorePassword();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getSslTruststorePath(String)}
	 */
	@Deprecated
	public String getSslTruststorePath();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getTransportAddresses(String)}
	 */
	@Deprecated
	public String[] getTransportAddresses();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getTransportSSLVerificationMode(String)}
	 */
	@Deprecated
	public String getTransportSSLVerificationMode();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#getUsername(String)}
	 */
	@Deprecated
	public String getUsername();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#isAuthenticationEnabled(String)}
	 */
	@Deprecated
	public boolean isAuthenticationEnabled();

	public boolean isCCREnabled();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#isClientTransportIgnoreClusterName(String)}
	 */
	@Deprecated
	public boolean isClientTransportIgnoreClusterName();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 * ElasticsearchConnectionConfigurationWrapper#isTransportSSLEnabled(String)}
	 */
	@Deprecated
	public boolean isTransportSSLEnabled();

}