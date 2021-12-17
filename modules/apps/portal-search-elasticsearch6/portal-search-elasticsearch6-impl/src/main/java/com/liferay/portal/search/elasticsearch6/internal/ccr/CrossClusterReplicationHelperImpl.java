/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.elasticsearch6.internal.ccr;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.search.ccr.CrossClusterReplicationHelper;
import com.liferay.portal.search.configuration.CrossClusterReplicationConfigurationWrapper;
import com.liferay.portal.search.configuration.ElasticsearchConnectionConfigurationWrapper;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchConnectionManager;

import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.security.KeyStore;

import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsRequest;
import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.client.CcrClient;
import org.elasticsearch.client.ClusterClient;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.ccr.FollowInfoRequest;
import org.elasticsearch.client.ccr.FollowInfoResponse;
import org.elasticsearch.client.ccr.PauseFollowRequest;
import org.elasticsearch.client.ccr.PutFollowRequest;
import org.elasticsearch.client.ccr.UnfollowRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @author Bryan Engler
 */
@Component(immediate = true, service = CrossClusterReplicationHelper.class)
public class CrossClusterReplicationHelperImpl
	implements CrossClusterReplicationHelper {

	@Override
	public void addRemoteCluster(
		String remoteClusterAlias, String remoteClusterSeedNodeTransportAddress,
		String localClusterConnectionId) {

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Adding remote cluster ", remoteClusterAlias,
					" for connection ", localClusterConnectionId));
		}

		try {
			_updateSettings(
				localClusterConnectionId, remoteClusterAlias,
				remoteClusterSeedNodeTransportAddress);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to add the remote cluster ", remoteClusterAlias,
						" for connection ", localClusterConnectionId),
					exception);
			}
		}
	}

	@Override
	public void deleteRemoteCluster(
		String remoteClusterAlias, String localClusterConnectionId) {

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Removing remote cluster ", remoteClusterAlias,
					" for connection ", localClusterConnectionId));
		}

		try {
			_updateSettings(localClusterConnectionId, remoteClusterAlias, null);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to remove the remote cluster ",
						remoteClusterAlias, " for connection ",
						localClusterConnectionId),
					exception);
			}
		}
	}

	@Override
	public void follow(String indexName) {
		if (!elasticsearchConnectionManager.
				isCrossClusterReplicationEnabled()) {

			if (_log.isInfoEnabled()) {
				_log.info(
					"Not following index " + indexName +
						" because cross-cluster replication is not enabled");
			}

			return;
		}

		for (String localClusterConnectionId :
				elasticsearchConnectionManager.getLocalClusterConnectionIds()) {

			follow(
				crossClusterReplicationConfigurationWrapper.
					getRemoteClusterAlias(),
				indexName, localClusterConnectionId);
		}
	}

	@Override
	public void follow(
		String remoteClusterAlias, String indexName,
		String localClusterConnectionId) {

		if (_isFollowingActive(localClusterConnectionId, indexName)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"The ", indexName,
						" index is already being followed for connection ",
						localClusterConnectionId));
			}

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Executing follow request for the ", indexName,
					" index with connection ", localClusterConnectionId));
		}

		try {
			_putFollow(remoteClusterAlias, indexName, localClusterConnectionId);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to follow the ", indexName, " index in the ",
						remoteClusterAlias, " cluster for connection ",
						localClusterConnectionId),
					exception);
			}
		}
	}

	@Override
	public void unfollow(String indexName) {
		if (!elasticsearchConnectionManager.
				isCrossClusterReplicationEnabled()) {

			if (_log.isInfoEnabled()) {
				_log.info(
					"Not unfollowing index " + indexName +
						" because cross-cluster replication is not enabled");
			}

			return;
		}

		for (String localClusterConnectionId :
				elasticsearchConnectionManager.getLocalClusterConnectionIds()) {

			unfollow(indexName, localClusterConnectionId);
		}
	}

	@Override
	public void unfollow(String indexName, String localClusterConnectionId) {
		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Executing unfollow requests for the ", indexName,
					" index with connection ", localClusterConnectionId));
		}

		try {
			_pauseFollow(indexName, localClusterConnectionId);

			_closeIndex(indexName, localClusterConnectionId);

			_unfollow(indexName, localClusterConnectionId);

			_deleteIndex(indexName, localClusterConnectionId);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to unfollow the ", indexName,
						" index for connection ", localClusterConnectionId),
					exception);
			}
		}
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile CrossClusterReplicationConfigurationWrapper
		crossClusterReplicationConfigurationWrapper;

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile ElasticsearchConnectionConfigurationWrapper
		elasticsearchConnectionConfigurationWrapper;

	@Reference
	protected ElasticsearchConnectionManager elasticsearchConnectionManager;

	private void _closeIndex(String indexName, String connectionId)
		throws Exception {

		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient(
			connectionId);

		IndicesClient indices = restHighLevelClient.indices();

		CloseIndexRequest closeIndexRequest = new CloseIndexRequest(indexName);

		indices.close(closeIndexRequest, RequestOptions.DEFAULT);
	}

	private void _configureSecurity(
		RestClientBuilder restClientBuilder, String connectionId) {

		restClientBuilder.setHttpClientConfigCallback(
			httpClientBuilder -> {
				if (elasticsearchConnectionConfigurationWrapper.
						isAuthenticationEnabled(connectionId)) {

					httpClientBuilder.setDefaultCredentialsProvider(
						_createCredentialsProvider(connectionId));
				}

				if (elasticsearchConnectionConfigurationWrapper.
						isTransportSSLEnabled(connectionId)) {

					httpClientBuilder.setSSLContext(
						_createSSLContext(connectionId));
				}

				return httpClientBuilder;
			});
	}

	private CredentialsProvider _createCredentialsProvider(
		String connectionId) {

		CredentialsProvider credentialsProvider =
			new BasicCredentialsProvider();

		credentialsProvider.setCredentials(
			AuthScope.ANY,
			new UsernamePasswordCredentials(
				elasticsearchConnectionConfigurationWrapper.getUsername(
					connectionId),
				elasticsearchConnectionConfigurationWrapper.getPassword(
					connectionId)));

		return credentialsProvider;
	}

	private RestHighLevelClient _createRestHighLevelClient(
		String connectionId) {

		RestClientBuilder restClientBuilder = RestClient.builder(
			HttpHost.create(
				elasticsearchConnectionConfigurationWrapper.
					getNetworkHostAddress(connectionId)));

		_configureSecurity(restClientBuilder, connectionId);

		return new RestHighLevelClient(restClientBuilder);
	}

	private SSLContext _createSSLContext(String connectionId) {
		try {
			Path path = Paths.get(
				elasticsearchConnectionConfigurationWrapper.
					getSslTruststorePath(connectionId));

			InputStream is = Files.newInputStream(path);

			KeyStore keyStore = KeyStore.getInstance(
				elasticsearchConnectionConfigurationWrapper.
					getCertificateFormat(connectionId));
			String truststorePassword =
				elasticsearchConnectionConfigurationWrapper.
					getSslTruststorePassword(connectionId);

			keyStore.load(is, truststorePassword.toCharArray());

			SSLContextBuilder sslContextBuilder = SSLContexts.custom();

			sslContextBuilder.loadKeyMaterial(
				keyStore, truststorePassword.toCharArray());
			sslContextBuilder.loadTrustMaterial(keyStore, null);

			return sslContextBuilder.build();
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private void _deleteIndex(String indexName, String connectionId)
		throws Exception {

		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient(
			connectionId);

		IndicesClient indices = restHighLevelClient.indices();

		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(
			indexName);

		indices.delete(deleteIndexRequest, RequestOptions.DEFAULT);
	}

	private boolean _isFollowingActive(String connectionId, String indexName) {
		try {
			RestHighLevelClient restHighLevelClient =
				_createRestHighLevelClient(connectionId);

			CcrClient ccrClient = restHighLevelClient.ccr();

			FollowInfoRequest followInfoRequest = new FollowInfoRequest(
				indexName);

			FollowInfoResponse followInfoResponse = ccrClient.getFollowInfo(
				followInfoRequest, RequestOptions.DEFAULT);

			List<FollowInfoResponse.FollowerInfo> followerInfos =
				followInfoResponse.getInfos();

			FollowInfoResponse.FollowerInfo followerInfo = followerInfos.get(0);

			FollowInfoResponse.Status status = followerInfo.getStatus();

			if (status == FollowInfoResponse.Status.ACTIVE) {
				return true;
			}
		}
		catch (Exception exception) {
		}

		return false;
	}

	private void _pauseFollow(String indexName, String connectionId)
		throws Exception {

		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient(
			connectionId);

		CcrClient ccrClient = restHighLevelClient.ccr();

		PauseFollowRequest pauseFollowRequest = new PauseFollowRequest(
			indexName);

		ccrClient.pauseFollow(pauseFollowRequest, RequestOptions.DEFAULT);
	}

	private void _putFollow(
			String remoteClusterAlias, String indexName, String connectionId)
		throws Exception {

		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient(
			connectionId);

		CcrClient ccrClient = restHighLevelClient.ccr();

		PutFollowRequest putFollowRequest = new PutFollowRequest(
			remoteClusterAlias, indexName, indexName, ActiveShardCount.from(1));

		ccrClient.putFollow(putFollowRequest, RequestOptions.DEFAULT);
	}

	private void _unfollow(String indexName, String connectionId)
		throws Exception {

		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient(
			connectionId);

		CcrClient ccrClient = restHighLevelClient.ccr();

		UnfollowRequest unfollowRequest = new UnfollowRequest(indexName);

		ccrClient.unfollow(unfollowRequest, RequestOptions.DEFAULT);
	}

	private void _updateSettings(
			String connectionId, String remoteClusterAlias,
			String remoteClusterSeedNodeTransportAddress)
		throws Exception {

		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient(
			connectionId);

		ClusterClient clusterClient = restHighLevelClient.cluster();

		ClusterUpdateSettingsRequest clusterUpdateSettingsRequest =
			new ClusterUpdateSettingsRequest();

		clusterUpdateSettingsRequest.persistentSettings(
			HashMapBuilder.put(
				"cluster.remote." + remoteClusterAlias + ".seeds",
				remoteClusterSeedNodeTransportAddress
			).build());

		clusterClient.putSettings(
			clusterUpdateSettingsRequest, RequestOptions.DEFAULT);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CrossClusterReplicationHelperImpl.class);

}