/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.configuration.CrossClusterReplicationConfigurationWrapper;
import com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch7.configuration.RESTClientLoggerLevel;
import com.liferay.portal.search.elasticsearch7.internal.index.IndexFactory;

import java.net.InetAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration",
	immediate = true,
	service = {
		ElasticsearchClientResolver.class, ElasticsearchConnectionManager.class
	}
)
public class ElasticsearchConnectionManager
	implements ElasticsearchClientResolver {

	public void activate(OperationMode operationMode) {
		validate(operationMode);

		_operationMode = operationMode;
	}

	public void connect() {
		ElasticsearchConnection elasticsearchConnection =
			getElasticsearchConnection(false);

		elasticsearchConnection.connect();
	}

	public AdminClient getAdminClient() {
		Client client = getClient();

		return client.admin();
	}

	public ElasticsearchConnection getCCRElasticsearchConnection(
		String connectionId) {

		ElasticsearchConnection elasticsearchConnection =
			_ccrElasticsearchConnections.get(connectionId);

		if (_log.isInfoEnabled()) {
			if (elasticsearchConnection != null) {
				_log.info("Returning connection with ID: " + connectionId);
			}
			else {
				_log.info(
					"Connection not found. Returning null for ID: " +
						connectionId);
			}
		}

		return elasticsearchConnection;
	}

	@Override
	public Client getClient() {
		return getClient(false);
	}

	@Override
	public Client getClient(boolean preferLocalCluster) {
		return getClient(null, preferLocalCluster);
	}

	@Override
	public Client getClient(String connectionId, boolean preferLocalCluster) {
		ElasticsearchConnection elasticsearchConnection =
			getElasticsearchConnection(connectionId, preferLocalCluster);

		if (elasticsearchConnection == null) {
			throw new ElasticsearchConnectionNotInitializedException();
		}

		return elasticsearchConnection.getClient();
	}

	public ElasticsearchConnection getElasticsearchConnection() {
		return getElasticsearchConnection(false);
	}

	public ElasticsearchConnection getElasticsearchConnection(
		boolean preferLocalCluster) {

		return getElasticsearchConnection(null, preferLocalCluster);
	}

	public ElasticsearchConnection getElasticsearchConnection(
		String localClusterConnectionId, boolean preferLocalCluster) {

		if (_log.isInfoEnabled()) {
			_log.info(
				"Connection requested for ID: " + localClusterConnectionId);
		}

		if (_operationMode == null) {
			return null;
		}

		if (_operationMode == OperationMode.EMBEDDED) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Returning connection with ID: " +
						EmbeddedElasticsearchConnection.CONNECTION_ID);
			}

			return _elasticsearchConnections.get(
				EmbeddedElasticsearchConnection.CONNECTION_ID);
		}

		if (preferLocalCluster && isCrossClusterReplicationEnabled()) {
			if (localClusterConnectionId == null) {
				localClusterConnectionId = getLocalClusterConnectionId();
			}

			if (localClusterConnectionId != null) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Getting local cluster connection with ID: " +
							localClusterConnectionId);
				}

				return getCCRElasticsearchConnection(localClusterConnectionId);
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Returning connection with ID: " +
					RemoteElasticsearchConnection.CONNECTION_ID);
		}

		return _elasticsearchConnections.get(
			RemoteElasticsearchConnection.CONNECTION_ID);
	}

	public String getLocalClusterConnectionId() {
		ClusterNode localClusterNode = _clusterExecutor.getLocalClusterNode();

		if (localClusterNode == null) {
			List<String> localClusterConnectionIds =
				getLocalClusterConnectionIds();

			return localClusterConnectionIds.get(0);
		}

		InetAddress portalInetAddress = localClusterNode.getPortalInetAddress();

		if (portalInetAddress == null) {
			return null;
		}

		String localClusterNodeHostName =
			portalInetAddress.getHostName() + StringPool.COLON +
				localClusterNode.getPortalPort();

		String[] localClusterConnectionConfigurations =
			crossClusterReplicationConfigurationWrapper.
				getCCRLocalClusterConnectionConfigurations();

		for (String localClusterConnectionConfiguration :
				localClusterConnectionConfigurations) {

			List<String> localClusterConnectionConfigurationParts =
				StringUtil.split(localClusterConnectionConfiguration);

			String hostName = localClusterConnectionConfigurationParts.get(0);

			if (hostName.equals(localClusterNodeHostName)) {
				return localClusterConnectionConfigurationParts.get(1);
			}
		}

		return null;
	}

	public List<String> getLocalClusterConnectionIds() {
		List<String> connectionIds = new ArrayList<>();

		String[] localClusterConnectionConfigurations =
			crossClusterReplicationConfigurationWrapper.
				getCCRLocalClusterConnectionConfigurations();

		for (String localClusterConnectionConfiguration :
				localClusterConnectionConfigurations) {

			List<String> localClusterConnectionConfigurationParts =
				StringUtil.split(localClusterConnectionConfiguration);

			connectionIds.add(localClusterConnectionConfigurationParts.get(1));
		}

		return connectionIds;
	}

	public RESTClientLoggerLevel getRESTClientLoggerLevel() {
		return _elasticsearchConfiguration.restClientLoggerLevel();
	}

	public boolean isCrossClusterReplicationEnabled() {
		if (crossClusterReplicationConfigurationWrapper == null) {
			return false;
		}

		if (ArrayUtil.isEmpty(
				crossClusterReplicationConfigurationWrapper.
					getCCRLocalClusterConnectionConfigurations())) {

			return false;
		}

		return crossClusterReplicationConfigurationWrapper.isCCREnabled();
	}

	public synchronized void registerCompanyId(long companyId) {
		_companyIds.put(companyId, companyId);
	}

	@Reference(
		cardinality = ReferenceCardinality.MANDATORY,
		target = "(operation.mode=EMBEDDED)",
		unbind = "unsetElasticsearchConnection"
	)
	public void setEmbeddedElasticsearchConnection(
		ElasticsearchConnection elasticsearchConnection) {

		_elasticsearchConnections.put(
			EmbeddedElasticsearchConnection.CONNECTION_ID,
			elasticsearchConnection);
	}

	@Reference(
		cardinality = ReferenceCardinality.MANDATORY,
		target = "(operation.mode=REMOTE)",
		unbind = "unsetElasticsearchConnection"
	)
	public void setRemoteElasticsearchConnection(
		ElasticsearchConnection elasticsearchConnection) {

		_elasticsearchConnections.put(
			RemoteElasticsearchConnection.CONNECTION_ID,
			elasticsearchConnection);
	}

	public synchronized void unregisterCompanyId(long companyId) {
		_companyIds.remove(companyId);
	}

	public void unsetElasticsearchConnection(
		ElasticsearchConnection elasticsearchConnection) {

		_elasticsearchConnections.remove(
			elasticsearchConnection.getConnectionId());

		elasticsearchConnection.close();
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);

		activate(translate(_elasticsearchConfiguration.operationMode()));
	}

	protected void addCCRElasticsearchConnection(
		CCRElasticsearchConnection ccrElasticsearchConnection) {

		if (!Validator.isBlank(ccrElasticsearchConnection.getConnectionId())) {
			_ccrElasticsearchConnections.put(
				ccrElasticsearchConnection.getConnectionId(),
				ccrElasticsearchConnection);
		}
	}

	@Modified
	protected synchronized void modified(Map<String, Object> properties) {
		_elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);

		modify(translate(_elasticsearchConfiguration.operationMode()));
	}

	protected synchronized void modify(OperationMode operationMode) {
		if (Objects.equals(operationMode, _operationMode)) {
			return;
		}

		validate(operationMode);

		ElasticsearchConnection newElasticsearchConnection =
			_elasticsearchConnections.get(operationMode.toString());

		newElasticsearchConnection.connect();

		if (_operationMode != null) {
			ElasticsearchConnection oldElasticsearchConnection =
				_elasticsearchConnections.get(_operationMode.toString());

			try {
				oldElasticsearchConnection.close();
			}
			catch (Exception exception) {
				_log.error(
					"Unable to close " + oldElasticsearchConnection, exception);
			}
		}

		_operationMode = operationMode;

		for (Long companyId : _companyIds.values()) {
			try {
				indexFactory.createIndices(getAdminClient(), companyId);
			}
			catch (Exception exception) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to reinitialize index for company " + companyId,
						exception);
				}
			}
		}
	}

	protected void removeCCRElasticsearchConnection(
		CCRElasticsearchConnection ccrElasticsearchConnection) {

		_ccrElasticsearchConnections.remove(
			ccrElasticsearchConnection.getConnectionId());
	}

	@Reference(unbind = "-")
	protected void setClusterExecutor(ClusterExecutor clusterExecutor) {
		_clusterExecutor = clusterExecutor;
	}

	protected OperationMode translate(
		com.liferay.portal.search.elasticsearch7.configuration.OperationMode
			operationMode) {

		return OperationMode.valueOf(operationMode.name());
	}

	protected void validate(OperationMode operationMode) {
		if (!_elasticsearchConnections.containsKey(operationMode.toString())) {
			throw new MissingOperationModeException(operationMode);
		}
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile CrossClusterReplicationConfigurationWrapper
		crossClusterReplicationConfigurationWrapper;

	@Reference(unbind = "-")
	protected IndexFactory indexFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		ElasticsearchConnectionManager.class);

	private final Map<String, CCRElasticsearchConnection>
		_ccrElasticsearchConnections = new HashMap<>();
	private ClusterExecutor _clusterExecutor;
	private final Map<Long, Long> _companyIds = new HashMap<>();
	private volatile ElasticsearchConfiguration _elasticsearchConfiguration;
	private final Map<String, ElasticsearchConnection>
		_elasticsearchConnections = new HashMap<>();
	private OperationMode _operationMode;

}