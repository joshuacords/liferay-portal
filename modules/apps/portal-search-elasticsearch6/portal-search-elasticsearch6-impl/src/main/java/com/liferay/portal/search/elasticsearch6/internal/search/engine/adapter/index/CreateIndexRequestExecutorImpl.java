/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.index;

import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.CreateIndexRequest;
import com.liferay.portal.search.engine.adapter.index.CreateIndexResponse;

import org.elasticsearch.action.admin.indices.create.CreateIndexAction;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.common.xcontent.XContentType;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = CreateIndexRequestExecutor.class)
public class CreateIndexRequestExecutorImpl
	implements CreateIndexRequestExecutor {

	@Override
	public CreateIndexResponse execute(CreateIndexRequest createIndexRequest) {
		CreateIndexRequestBuilder createIndexRequestBuilder =
			createCreateIndexRequestBuilder(createIndexRequest);

		org.elasticsearch.action.admin.indices.create.CreateIndexResponse
			elasticsearchCreateIndexResponse = createIndexRequestBuilder.get();

		return new CreateIndexResponse(
			elasticsearchCreateIndexResponse.isAcknowledged());
	}

	protected CreateIndexRequestBuilder createCreateIndexRequestBuilder(
		CreateIndexRequest createIndexRequest) {

		CreateIndexRequestBuilder createIndexRequestBuilder =
			CreateIndexAction.INSTANCE.newRequestBuilder(
				_elasticsearchClientResolver.getClient(false));

		createIndexRequestBuilder.setIndex(createIndexRequest.getIndexName());
		createIndexRequestBuilder.setSource(
			createIndexRequest.getSource(), XContentType.JSON);

		return createIndexRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}