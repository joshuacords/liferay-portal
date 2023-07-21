/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.index;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.IndexRequestShardFailure;
import com.liferay.portal.search.engine.adapter.index.RefreshIndexRequest;
import com.liferay.portal.search.engine.adapter.index.RefreshIndexResponse;

import org.elasticsearch.action.ShardOperationFailedException;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequestBuilder;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = RefreshIndexRequestExecutor.class)
public class RefreshIndexRequestExecutorImpl
	implements RefreshIndexRequestExecutor {

	@Override
	public RefreshIndexResponse execute(
		RefreshIndexRequest refreshIndexRequest) {

		RefreshRequestBuilder refreshRequestBuilder =
			createRefreshRequestBuilder(refreshIndexRequest);

		RefreshResponse refreshResponse = refreshRequestBuilder.get();

		RefreshIndexResponse refreshIndexResponse = new RefreshIndexResponse();

		refreshIndexResponse.setFailedShards(refreshResponse.getFailedShards());
		refreshIndexResponse.setSuccessfulShards(
			refreshResponse.getSuccessfulShards());
		refreshIndexResponse.setTotalShards(refreshResponse.getTotalShards());

		ShardOperationFailedException[] shardOperationFailedExceptions =
			refreshResponse.getShardFailures();

		if (ArrayUtil.isNotEmpty(shardOperationFailedExceptions)) {
			for (ShardOperationFailedException shardOperationFailedException :
					shardOperationFailedExceptions) {

				IndexRequestShardFailure indexRequestShardFailure =
					_indexRequestShardFailureTranslator.translate(
						shardOperationFailedException);

				refreshIndexResponse.addIndexRequestShardFailure(
					indexRequestShardFailure);
			}
		}

		return refreshIndexResponse;
	}

	protected RefreshRequestBuilder createRefreshRequestBuilder(
		RefreshIndexRequest refreshIndexRequest) {

		Client client = _elasticsearchClientResolver.getClient(false);

		AdminClient adminClient = client.admin();

		IndicesAdminClient indicesAdminClient = adminClient.indices();

		return indicesAdminClient.prepareRefresh(
			refreshIndexRequest.getIndexNames());
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	@Reference(unbind = "-")
	protected void setIndexRequestShardFailureTranslator(
		IndexRequestShardFailureTranslator indexRequestShardFailureTranslator) {

		_indexRequestShardFailureTranslator =
			indexRequestShardFailureTranslator;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;
	private IndexRequestShardFailureTranslator
		_indexRequestShardFailureTranslator;

}