/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.GetFieldMappingIndexRequest;
import com.liferay.portal.search.engine.adapter.index.GetFieldMappingIndexResponse;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(
	immediate = true, service = GetFieldMappingIndexRequestExecutor.class
)
public class GetFieldMappingIndexRequestExecutorImpl
	implements GetFieldMappingIndexRequestExecutor {

	@Override
	public GetFieldMappingIndexResponse execute(
		GetFieldMappingIndexRequest getFieldMappingIndexRequest) {

		GetFieldMappingsRequestBuilder getFieldMappingsRequestBuilder =
			createGetFieldMappingsRequestBuilder(getFieldMappingIndexRequest);

		GetFieldMappingsResponse getFieldMappingsResponse =
			getFieldMappingsRequestBuilder.get();

		Map
			<String,
			 Map
				 <String,
				  Map<String, GetFieldMappingsResponse.FieldMappingMetadata>>>
					mappings = getFieldMappingsResponse.mappings();

		Map<String, String> fieldMappings = new HashMap<>();

		for (String indexName : getFieldMappingIndexRequest.getIndexNames()) {
			Map
				<String,
				 Map<String, GetFieldMappingsResponse.FieldMappingMetadata>>
					map1 = mappings.get(indexName);

			Map<String, GetFieldMappingsResponse.FieldMappingMetadata> map2 =
				map1.get(getFieldMappingIndexRequest.getMappingName());

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			for (String fieldName : getFieldMappingIndexRequest.getFields()) {
				GetFieldMappingsResponse.FieldMappingMetadata
					fieldMappingMetadata = map2.get(fieldName);

				Map<String, Object> source = fieldMappingMetadata.sourceAsMap();

				jsonObject.put(fieldName, source);
			}

			fieldMappings.put(indexName, jsonObject.toString());
		}

		return new GetFieldMappingIndexResponse(fieldMappings);
	}

	protected GetFieldMappingsRequestBuilder
		createGetFieldMappingsRequestBuilder(
			GetFieldMappingIndexRequest getFieldMappingIndexRequest) {

		Client client = _elasticsearchClientResolver.getClient(true);

		AdminClient adminClient = client.admin();

		IndicesAdminClient indicesAdminClient = adminClient.indices();

		GetFieldMappingsRequestBuilder getFieldMappingsRequestBuilder =
			indicesAdminClient.prepareGetFieldMappings(
				getFieldMappingIndexRequest.getIndexNames());

		getFieldMappingsRequestBuilder.setFields(
			getFieldMappingIndexRequest.getFields());
		getFieldMappingsRequestBuilder.setTypes(
			getFieldMappingIndexRequest.getMappingName());

		return getFieldMappingsRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}