/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.IndicesExistsIndexRequest;
import com.liferay.portal.search.engine.adapter.index.IndicesExistsIndexResponse;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsAction;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = IndicesExistsIndexRequestExecutor.class)
public class IndicesExistsIndexRequestExecutorImpl
	implements IndicesExistsIndexRequestExecutor {

	@Override
	public IndicesExistsIndexResponse execute(
		IndicesExistsIndexRequest indicesExistsIndexRequest) {

		IndicesExistsRequestBuilder indicesExistsRequestBuilder =
			createIndicesExistsRequestBuilder(indicesExistsIndexRequest);

		IndicesExistsResponse indicesExistsResponse =
			indicesExistsRequestBuilder.get();

		return new IndicesExistsIndexResponse(indicesExistsResponse.isExists());
	}

	protected IndicesExistsRequestBuilder createIndicesExistsRequestBuilder(
		IndicesExistsIndexRequest indicesExistsIndexRequest) {

		IndicesExistsRequestBuilder indicesExistsRequestBuilder =
			new IndicesExistsRequestBuilder(
				_elasticsearchClientResolver.getClient(
					indicesExistsIndexRequest.isPreferLocalCluster()),
				IndicesExistsAction.INSTANCE);

		indicesExistsRequestBuilder.setIndices(
			indicesExistsIndexRequest.getIndexNames());

		return indicesExistsRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}