/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.GetMappingIndexRequest;
import com.liferay.portal.search.engine.adapter.index.GetMappingIndexResponse;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.compress.CompressedXContent;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(immediate = true, service = GetMappingIndexRequestExecutor.class)
public class GetMappingIndexRequestExecutorImpl
	implements GetMappingIndexRequestExecutor {

	@Override
	public GetMappingIndexResponse execute(
		GetMappingIndexRequest getMappingIndexRequest) {

		GetMappingsRequestBuilder getMappingsRequestBuilder =
			createGetMappingsRequestBuilder(getMappingIndexRequest);

		GetMappingsResponse getMappingsResponse =
			getMappingsRequestBuilder.get();

		ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetadata>>
			mappings = getMappingsResponse.mappings();

		Map<String, String> indexMappings = new HashMap<>();

		for (String indexName : getMappingIndexRequest.getIndexNames()) {
			ImmutableOpenMap<String, MappingMetadata> indexMapping =
				mappings.get(indexName);

			MappingMetadata mappingMetadata = indexMapping.get(
				getMappingIndexRequest.getMappingName());

			CompressedXContent mappingContent = mappingMetadata.source();

			indexMappings.put(indexName, mappingContent.toString());
		}

		return new GetMappingIndexResponse(indexMappings);
	}

	protected GetMappingsRequestBuilder createGetMappingsRequestBuilder(
		GetMappingIndexRequest getMappingIndexRequest) {

		Client client = _elasticsearchClientResolver.getClient(true);

		AdminClient adminClient = client.admin();

		IndicesAdminClient indicesAdminClient = adminClient.indices();

		GetMappingsRequestBuilder getMappingsRequestBuilder =
			indicesAdminClient.prepareGetMappings(
				getMappingIndexRequest.getIndexNames());

		getMappingsRequestBuilder.setTypes(
			getMappingIndexRequest.getMappingName());

		return getMappingsRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}