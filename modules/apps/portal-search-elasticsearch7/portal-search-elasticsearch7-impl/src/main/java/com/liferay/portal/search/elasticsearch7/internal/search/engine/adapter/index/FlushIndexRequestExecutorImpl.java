/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.FlushIndexRequest;
import com.liferay.portal.search.engine.adapter.index.FlushIndexResponse;
import com.liferay.portal.search.engine.adapter.index.IndexRequestShardFailure;

import org.elasticsearch.action.ShardOperationFailedException;
import org.elasticsearch.action.admin.indices.flush.FlushAction;
import org.elasticsearch.action.admin.indices.flush.FlushRequestBuilder;
import org.elasticsearch.action.admin.indices.flush.FlushResponse;
import org.elasticsearch.rest.RestStatus;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = FlushIndexRequestExecutor.class)
public class FlushIndexRequestExecutorImpl
	implements FlushIndexRequestExecutor {

	@Override
	public FlushIndexResponse execute(FlushIndexRequest flushIndexRequest) {
		FlushRequestBuilder flushRequestBuilder = createFlushRequestBuilder(
			flushIndexRequest);

		FlushResponse flushResponse = flushRequestBuilder.get();

		FlushIndexResponse flushIndexResponse = new FlushIndexResponse();

		flushIndexResponse.setFailedShards(flushResponse.getFailedShards());
		flushIndexResponse.setSuccessfulShards(
			flushResponse.getSuccessfulShards());
		flushIndexResponse.setTotalShards(flushResponse.getTotalShards());

		RestStatus restStatus = flushResponse.getStatus();

		flushIndexResponse.setRestStatus(restStatus.getStatus());

		ShardOperationFailedException[] shardOperationFailedExceptions =
			flushResponse.getShardFailures();

		if (ArrayUtil.isNotEmpty(shardOperationFailedExceptions)) {
			for (ShardOperationFailedException shardOperationFailedException :
					shardOperationFailedExceptions) {

				IndexRequestShardFailure indexRequestShardFailure =
					_indexRequestShardFailureTranslator.translate(
						shardOperationFailedException);

				flushIndexResponse.addIndexRequestShardFailure(
					indexRequestShardFailure);
			}
		}

		return flushIndexResponse;
	}

	protected FlushRequestBuilder createFlushRequestBuilder(
		FlushIndexRequest flushIndexRequest) {

		FlushRequestBuilder flushRequestBuilder = new FlushRequestBuilder(
			_elasticsearchClientResolver.getClient(false),
			FlushAction.INSTANCE);

		flushRequestBuilder.setIndices(flushIndexRequest.getIndexNames());
		flushRequestBuilder.setForce(flushIndexRequest.isForce());
		flushRequestBuilder.setWaitIfOngoing(
			flushIndexRequest.isWaitIfOngoing());

		return flushRequestBuilder;
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