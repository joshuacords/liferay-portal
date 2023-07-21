/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.PutMappingIndexRequest;
import com.liferay.portal.search.engine.adapter.index.PutMappingIndexResponse;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.xcontent.XContentType;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(immediate = true, service = PutMappingIndexRequestExecutor.class)
public class PutMappingIndexRequestExecutorImpl
	implements PutMappingIndexRequestExecutor {

	@Override
	public PutMappingIndexResponse execute(
		PutMappingIndexRequest putMappingIndexRequest) {

		PutMappingRequestBuilder putMappingRequestBuilder =
			createPutMappingRequestBuilder(putMappingIndexRequest);

		AcknowledgedResponse acknowledgedResponse =
			putMappingRequestBuilder.get();

		return new PutMappingIndexResponse(
			acknowledgedResponse.isAcknowledged());
	}

	protected PutMappingRequestBuilder createPutMappingRequestBuilder(
		PutMappingIndexRequest putMappingIndexRequest) {

		Client client = _elasticsearchClientResolver.getClient(false);

		AdminClient adminClient = client.admin();

		IndicesAdminClient indicesAdminClient = adminClient.indices();

		PutMappingRequestBuilder putMappingRequestBuilder =
			indicesAdminClient.preparePutMapping(
				putMappingIndexRequest.getIndexNames());

		putMappingRequestBuilder.setSource(
			putMappingIndexRequest.getMapping(), XContentType.JSON);
		putMappingRequestBuilder.setType(
			putMappingIndexRequest.getMappingName());

		return putMappingRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}