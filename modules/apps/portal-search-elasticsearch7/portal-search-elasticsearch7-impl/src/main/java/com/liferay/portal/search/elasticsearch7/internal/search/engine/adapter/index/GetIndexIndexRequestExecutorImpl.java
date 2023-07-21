/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;

import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.GetIndexIndexRequest;
import com.liferay.portal.search.engine.adapter.index.GetIndexIndexResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.elasticsearch.action.admin.indices.get.GetIndexAction;
import org.elasticsearch.action.admin.indices.get.GetIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.compress.CompressedXContent;
import org.elasticsearch.common.settings.Settings;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = GetIndexIndexRequestExecutor.class)
public class GetIndexIndexRequestExecutorImpl
	implements GetIndexIndexRequestExecutor {

	@Override
	public GetIndexIndexResponse execute(
		GetIndexIndexRequest getIndexIndexRequest) {

		GetIndexRequestBuilder getIndexRequestBuilder =
			createGetIndexRequestBuilder(getIndexIndexRequest);

		GetIndexResponse getIndexResponse = getIndexRequestBuilder.get();

		GetIndexIndexResponse getIndexIndexResponse =
			new GetIndexIndexResponse();

		getIndexIndexResponse.setIndexNames(getIndexResponse.getIndices());

		ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetadata>>
			indicesMappings = getIndexResponse.getMappings();

		getIndexIndexResponse.setMappings(convertMappings(indicesMappings));

		ImmutableOpenMap<String, Settings> indicesSettings =
			getIndexResponse.getSettings();

		getIndexIndexResponse.setSettings(convertSettings(indicesSettings));

		return getIndexIndexResponse;
	}

	protected Map<String, Map<String, String>> convertMappings(
		ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetadata>>
			indicesMappings) {

		Iterator
			<ObjectObjectCursor
				<String, ImmutableOpenMap<String, MappingMetadata>>> iterator =
					indicesMappings.iterator();

		Map<String, Map<String, String>> indexMappings = new HashMap<>();

		while (iterator.hasNext()) {
			ObjectObjectCursor
				<String, ImmutableOpenMap<String, MappingMetadata>>
					objectObjectCursor = iterator.next();

			ImmutableOpenMap<String, MappingMetadata> typeMappingsData =
				objectObjectCursor.value;

			Map<String, String> indiceTypeMappings = new HashMap<>();

			indexMappings.put(objectObjectCursor.key, indiceTypeMappings);

			Iterator<ObjectObjectCursor<String, MappingMetadata>>
				typeMappingsIterator = typeMappingsData.iterator();

			while (typeMappingsIterator.hasNext()) {
				ObjectObjectCursor<String, MappingMetadata>
					typeMappingsObjectObjectCursor =
						typeMappingsIterator.next();

				MappingMetadata mappingMetadata =
					typeMappingsObjectObjectCursor.value;

				CompressedXContent mappingContent = mappingMetadata.source();

				indiceTypeMappings.put(
					typeMappingsObjectObjectCursor.key,
					mappingContent.toString());
			}
		}

		return indexMappings;
	}

	protected Map<String, String> convertSettings(
		ImmutableOpenMap<String, Settings> indicesSettings) {

		Iterator<ObjectObjectCursor<String, Settings>> iterator =
			indicesSettings.iterator();

		Map<String, String> indicesSettingsMap = new HashMap<>();

		while (iterator.hasNext()) {
			ObjectObjectCursor<String, Settings> objectObjectCursor =
				iterator.next();

			Settings settings = objectObjectCursor.value;

			indicesSettingsMap.put(objectObjectCursor.key, settings.toString());
		}

		return indicesSettingsMap;
	}

	protected GetIndexRequestBuilder createGetIndexRequestBuilder(
		GetIndexIndexRequest getIndexIndexRequest) {

		GetIndexRequestBuilder getIndexRequestBuilder =
			new GetIndexRequestBuilder(
				_elasticsearchClientResolver.getClient(
					getIndexIndexRequest.getConnectionId(),
					getIndexIndexRequest.isPreferLocalCluster()),
				GetIndexAction.INSTANCE);

		getIndexRequestBuilder.setIndices(getIndexIndexRequest.getIndexNames());

		return getIndexRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}