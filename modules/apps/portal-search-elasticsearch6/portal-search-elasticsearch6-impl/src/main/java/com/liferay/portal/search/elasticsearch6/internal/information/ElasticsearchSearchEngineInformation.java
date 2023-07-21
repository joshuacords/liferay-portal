/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.information;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.configuration.CrossClusterReplicationConfigurationWrapper;
import com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch6.configuration.OperationMode;
import com.liferay.portal.search.elasticsearch6.internal.ElasticsearchSearchEngine;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchConnection;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.cluster.ClusterHealthStatusTranslator;
import com.liferay.portal.search.engine.ConnectionInformation;
import com.liferay.portal.search.engine.ConnectionInformationBuilder;
import com.liferay.portal.search.engine.ConnectionInformationBuilderFactory;
import com.liferay.portal.search.engine.NodeInformation;
import com.liferay.portal.search.engine.NodeInformationBuilder;
import com.liferay.portal.search.engine.NodeInformationBuilderFactory;
import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.engine.adapter.cluster.ClusterHealthStatus;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.Version;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequestBuilder;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.cluster.node.info.NodeInfo;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequestBuilder;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.unit.TimeValue;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @author Adam Brandizzi
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration",
	immediate = true, service = SearchEngineInformation.class
)
public class ElasticsearchSearchEngineInformation
	implements SearchEngineInformation {

	@Override
	public String getClientVersionString() {
		return Version.CURRENT.toString();
	}

	@Override
	public List<ConnectionInformation> getConnectionInformationList() {
		if (!isCrossClusterReplicationInstalled()) {
			return null;
		}

		List<ConnectionInformation> connectionInformationList =
			new LinkedList<>();

		addMainConnection(
			elasticsearchConnectionManager.getElasticsearchConnection(),
			connectionInformationList);

		String filterString = String.format(
			"(&(service.factoryPid=%s)",
			_ELASTICSEARCH_CONNECTION_CONFIGURATION_CLASS_NAME);

		if (!isOperationModeEmbedded() &&
			elasticsearchConnectionManager.isCrossClusterReplicationEnabled()) {

			addCCRConnection(
				elasticsearchConnectionManager.getElasticsearchConnection(true),
				connectionInformationList);

			String connectionId =
				elasticsearchConnectionManager.getLocalClusterConnectionId();

			if (!Validator.isBlank(connectionId)) {
				filterString = filterString.concat(
					String.format("(!(connectionId=%s))", connectionId));
			}
		}

		filterString = filterString.concat(")");

		try {
			addActiveConnections(filterString, connectionInformationList);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get active connections", exception);
			}
		}

		return connectionInformationList;
	}

	@Override
	public String getNodesString() {
		try {
			String clusterNodesString = getClusterNodesString(
				elasticsearchConnectionManager.getClient());

			if (!isOperationModeEmbedded() &&
				elasticsearchConnectionManager.
					isCrossClusterReplicationEnabled()) {

				String localClusterNodesString = getClusterNodesString(
					elasticsearchConnectionManager.getClient(true));

				if (!Validator.isBlank(localClusterNodesString)) {
					StringBundler sb = new StringBundler(11);

					sb.append("Remote Cluster");
					sb.append(StringPool.SPACE);
					sb.append(StringPool.EQUAL);
					sb.append(StringPool.SPACE);
					sb.append(clusterNodesString);
					sb.append(StringPool.COMMA_AND_SPACE);
					sb.append("Local Cluster");
					sb.append(StringPool.SPACE);
					sb.append(StringPool.EQUAL);
					sb.append(StringPool.SPACE);
					sb.append(localClusterNodesString);

					clusterNodesString = sb.toString();
				}
			}

			return clusterNodesString;
		}
		catch (Exception exception) {
			return exception.toString();
		}
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public String getStatusString() {
		StringBundler sb = new StringBundler(8);

		sb.append("Vendor: ");
		sb.append(getVendorString());
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("Client Version: ");
		sb.append(getClientVersionString());
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("Nodes: ");
		sb.append(getNodesString());

		return sb.toString();
	}

	@Override
	public String getVendorString() {
		String vendor = elasticsearchSearchEngine.getVendor();

		if (isOperationModeEmbedded()) {
			StringBundler sb = new StringBundler(5);

			sb.append(vendor);
			sb.append(StringPool.SPACE);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append("Embedded");
			sb.append(StringPool.CLOSE_PARENTHESIS);

			return sb.toString();
		}

		return vendor;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);
	}

	protected void addActiveConnections(
			String filterString,
			List<ConnectionInformation> connectionInformationList)
		throws Exception {

		Configuration[] configurations = configurationAdmin.listConfigurations(
			filterString);

		for (Configuration configuration : configurations) {
			Dictionary<String, Object> properties =
				configuration.getProperties();

			String connectionId = (String)properties.get("connectionId");

			addConnectionInformation(
				elasticsearchConnectionManager.getCCRElasticsearchConnection(
					connectionId),
				connectionInformationList, null);
		}
	}

	protected void addCCRConnection(
		ElasticsearchConnection elasticsearchConnection,
		List<ConnectionInformation> connectionInformationList) {

		addConnectionInformation(
			elasticsearchConnection, connectionInformationList, "read");
	}

	protected void addConnectionInformation(
		ElasticsearchConnection elasticsearchConnection,
		List<ConnectionInformation> connectionInformationList,
		String... labels) {

		if (elasticsearchConnection == null) {
			return;
		}

		ConnectionInformationBuilder connectionInformationBuilder =
			connectionInformationBuilderFactory.
				getConnectionInformationBuilder();

		try {
			_setClusterAndNodeInformation(
				connectionInformationBuilder,
				elasticsearchConnection.getClient());
		}
		catch (Exception exception) {
			connectionInformationBuilder.error(exception.toString());

			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get node information", exception);
			}
		}

		connectionInformationBuilder.connectionId(
			elasticsearchConnection.getConnectionId());

		try {
			_setHealthInformation(
				connectionInformationBuilder,
				elasticsearchConnection.getClient());
		}
		catch (RuntimeException runtimeException) {
			connectionInformationBuilder.health("unknown");

			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get health information", runtimeException);
			}
		}

		if (ArrayUtil.isNotEmpty(labels)) {
			connectionInformationBuilder.labels(SetUtil.fromArray(labels));
		}

		connectionInformationList.add(connectionInformationBuilder.build());
	}

	protected void addMainConnection(
		ElasticsearchConnection elasticsearchConnection,
		List<ConnectionInformation> connectionInformationList) {

		String[] labels = {"read", "write"};

		if (!isOperationModeEmbedded() &&
			elasticsearchConnectionManager.isCrossClusterReplicationEnabled()) {

			labels = new String[] {"write"};
		}

		addConnectionInformation(
			elasticsearchConnection, connectionInformationList, labels);
	}

	protected String getClusterNodesString(Client client) {
		try {
			if (client == null) {
				return StringPool.BLANK;
			}

			ConnectionInformationBuilder connectionInformationBuilder =
				connectionInformationBuilderFactory.
					getConnectionInformationBuilder();

			_setClusterAndNodeInformation(connectionInformationBuilder, client);

			ConnectionInformation connectionInformation =
				connectionInformationBuilder.build();

			String clusterName = connectionInformation.getClusterName();

			List<NodeInformation> nodeInformations =
				connectionInformation.getNodeInformationList();

			Stream<NodeInformation> stream = nodeInformations.stream();

			String nodesString = stream.map(
				nodeInfo -> {
					StringBundler sb = new StringBundler(5);

					sb.append(nodeInfo.getName());
					sb.append(StringPool.SPACE);
					sb.append(StringPool.OPEN_PARENTHESIS);
					sb.append(nodeInfo.getVersion());
					sb.append(StringPool.CLOSE_PARENTHESIS);

					return sb.toString();
				}
			).collect(
				Collectors.joining(StringPool.COMMA_AND_SPACE)
			);

			StringBundler sb = new StringBundler(6);

			sb.append(clusterName);
			sb.append(StringPool.COLON);
			sb.append(StringPool.SPACE);
			sb.append(StringPool.OPEN_BRACKET);
			sb.append(nodesString);
			sb.append(StringPool.CLOSE_BRACKET);

			return sb.toString();
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get node information", exception);
			}

			StringBundler sb = new StringBundler(6);

			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append("Error");
			sb.append(StringPool.COLON);
			sb.append(StringPool.SPACE);
			sb.append(exception.toString());
			sb.append(StringPool.CLOSE_PARENTHESIS);

			return sb.toString();
		}
	}

	protected boolean isCrossClusterReplicationInstalled() {
		if (crossClusterReplicationConfigurationWrapper == null) {
			return false;
		}

		return true;
	}

	protected boolean isOperationModeEmbedded() {
		OperationMode operationMode =
			elasticsearchConfiguration.operationMode();

		return Objects.equals(operationMode, OperationMode.EMBEDDED);
	}

	@Reference
	protected ClusterHealthStatusTranslator clusterHealthStatusTranslator;

	@Reference
	protected ConfigurationAdmin configurationAdmin;

	@Reference
	protected ConnectionInformationBuilderFactory
		connectionInformationBuilderFactory;

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile CrossClusterReplicationConfigurationWrapper
		crossClusterReplicationConfigurationWrapper;

	protected volatile ElasticsearchConfiguration elasticsearchConfiguration;

	@Reference
	protected ElasticsearchConnectionManager elasticsearchConnectionManager;

	@Reference
	protected ElasticsearchSearchEngine elasticsearchSearchEngine;

	@Reference
	protected NodeInformationBuilderFactory nodeInformationBuilderFactory;

	private void _setClusterAndNodeInformation(
		ConnectionInformationBuilder connectionInformationBuilder,
		Client client) {

		AdminClient adminClient = client.admin();

		ClusterAdminClient clusterAdminClient = adminClient.cluster();

		NodesInfoRequestBuilder nodesInfoRequestBuilder =
			clusterAdminClient.prepareNodesInfo();

		TimeValue timeout = TimeValue.timeValueMillis(10000);

		NodesInfoResponse nodesInfoResponse = nodesInfoRequestBuilder.get(
			timeout);

		ClusterName clusterName = nodesInfoResponse.getClusterName();

		connectionInformationBuilder.clusterName(clusterName.value());

		List<NodeInformation> nodeInformationList = new ArrayList<>();

		for (NodeInfo nodeInfo : nodesInfoResponse.getNodes()) {
			NodeInformationBuilder nodeInformationBuilder =
				nodeInformationBuilderFactory.getNodeInformationBuilder();

			DiscoveryNode node = nodeInfo.getNode();

			nodeInformationBuilder.name(node.getName());

			Version version = node.getVersion();

			nodeInformationBuilder.version(version.toString());

			nodeInformationList.add(nodeInformationBuilder.build());
		}

		connectionInformationBuilder.nodeInformationList(nodeInformationList);
	}

	private void _setHealthInformation(
		ConnectionInformationBuilder connectionInformationBuilder,
		Client client) {

		AdminClient adminClient = client.admin();

		ClusterAdminClient clusterAdminClient = adminClient.cluster();

		ClusterHealthRequestBuilder clusterHealthRequestBuilder =
			clusterAdminClient.prepareHealth();

		ClusterHealthResponse clusterHealthResponse =
			clusterHealthRequestBuilder.get();

		ClusterHealthStatus clusterHealthStatus =
			clusterHealthStatusTranslator.translate(
				clusterHealthResponse.getStatus());

		connectionInformationBuilder.health(clusterHealthStatus.toString());
	}

	private static final String
		_ELASTICSEARCH_CONNECTION_CONFIGURATION_CLASS_NAME =
			"com.liferay.portal.search.elasticsearch.cross.cluster." +
				"replication.internal.configuration." +
					"ElasticsearchConnectionConfiguration";

	private static final Log _log = LogFactoryUtil.getLog(
		ElasticsearchSearchEngineInformation.class);

}