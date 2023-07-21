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
public interface ElasticsearchConnectionConfigurationWrapper {

	public String getCertificateFormat(String connectionId);

	public String getClientTransportNodesSamplerInterval(String connectionId);

	public String getClientTransportPingTimeout(String connectionId);

	public String getClusterName(String connectionId);

	public String getNetworkHostAddress(String connectionId);

	public String getPassword(String connectionId);

	public String[] getSslCertificateAuthoritiesPaths(String connectionId);

	public String getSslCertificatePath(String connectionId);

	public String getSslKeyPath(String connectionId);

	public String getSslKeystorePassword(String connectionId);

	public String getSslKeystorePath(String connectionId);

	public String getSslTruststorePassword(String connectionId);

	public String getSslTruststorePath(String connectionId);

	public String[] getTransportAddresses(String connectionId);

	public String getTransportSSLVerificationMode(String connectionId);

	public String getUsername(String connectionId);

	public boolean isAuthenticationEnabled(String connectionId);

	public boolean isClientTransportIgnoreClusterName(String connectionId);

	public boolean isClientTransportSniff(String connectionId);

	public boolean isTransportSSLEnabled(String connectionId);

}