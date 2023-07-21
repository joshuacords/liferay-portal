/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Bryan Engler
 */
@ExtendedObjectClassDefinition(
	category = "search", factoryInstanceLabelAttribute = "connectionId",
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	factory = true,
	id = "com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.ElasticsearchConnectionConfiguration",
	localization = "content/Language",
	name = "elasticsearch-connection-configuration-name"
)
public interface ElasticsearchConnectionConfiguration {

	@Meta.AD(
		description = "connection-id-help", name = "connection-id",
		required = false
	)
	public String connectionId();

	@Meta.AD(
		deflt = "LiferayElasticsearchCluster2",
		description = "cluster-name-help", name = "cluster-name",
		required = false
	)
	public String clusterName();

	@Meta.AD(
		deflt = "localhost:9301", description = "transport-addresses-help",
		name = "transport-addresses", required = false
	)
	public String[] transportAddresses();

	@Meta.AD(
		deflt = "http://localhost:9201",
		description = "network-host-address-help",
		name = "network-host-address", required = false
	)
	public String networkHostAddress();

	@Meta.AD(
		deflt = "true", description = "client-transport-sniff-help",
		name = "client-transport-sniff", required = false
	)
	public boolean clientTransportSniff();

	@Meta.AD(
		deflt = "false",
		description = "client-transport-ignore-cluster-name-help",
		name = "client-transport-ignore-cluster-name", required = false
	)
	public boolean clientTransportIgnoreClusterName();

	@Meta.AD(
		deflt = "", description = "client-transport-ping-timeout-help",
		name = "client-transport-ping-timeout", required = false
	)
	public String clientTransportPingTimeout();

	@Meta.AD(
		deflt = "",
		description = "client-transport-nodes-sampler-interval-help",
		name = "client-transport-nodes-sampler-interval", required = false
	)
	public String clientTransportNodesSamplerInterval();

	@Meta.AD(
		deflt = "false", description = "authentication-enabled-help",
		name = "authentication-enabled", required = false
	)
	public boolean authenticationEnabled();

	@Meta.AD(
		deflt = "elastic", description = "username-help", name = "username",
		required = false
	)
	public String username();

	@Meta.AD(
		description = "password-help", name = "password", required = false,
		type = Meta.Type.Password
	)
	public String password();

	@Meta.AD(
		deflt = "false", description = "transport-ssl-enabled-help",
		name = "transport-ssl-enabled", required = false
	)
	public boolean transportSSLEnabled();

	@Meta.AD(
		deflt = "certificate",
		description = "transport-ssl-verification-mode-help",
		name = "transport-ssl-verification-mode",
		optionValues = {"certificate", "full", "none"}, required = false
	)
	public String transportSSLVerificationMode();

	@Meta.AD(
		deflt = "pkcs12", description = "certificate-format-help",
		name = "certificate-format", required = false
	)
	public String certificateFormat();

	@Meta.AD(
		deflt = "/path/to/elastic-certificates.p12",
		description = "ssl-keystore-path-help", name = "ssl-keystore-path",
		required = false
	)
	public String sslKeystorePath();

	@Meta.AD(
		description = "ssl-keystore-password-help",
		name = "ssl-keystore-password", required = false,
		type = Meta.Type.Password
	)
	public String sslKeystorePassword();

	@Meta.AD(
		deflt = "/path/to/elastic-certificates.p12",
		description = "ssl-truststore-path-help", name = "ssl-truststore-path",
		required = false
	)
	public String sslTruststorePath();

	@Meta.AD(
		description = "ssl-truststore-password-help",
		name = "ssl-truststore-password", required = false,
		type = Meta.Type.Password
	)
	public String sslTruststorePassword();

	@Meta.AD(
		deflt = "/path/to/instance.key", description = "ssl-key-path-help",
		name = "ssl-key-path", required = false
	)
	public String sslKeyPath();

	@Meta.AD(
		deflt = "/path/to/instance.crt",
		description = "ssl-certificate-path-help",
		name = "ssl-certificate-path", required = false
	)
	public String sslCertificatePath();

	@Meta.AD(
		deflt = "/path/to/ca.crt",
		description = "ssl-certificate-authorities-paths-help",
		name = "ssl-certificate-authorities-paths", required = false
	)
	public String[] sslCertificateAuthoritiesPaths();

}