/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;

import org.mockito.Mockito;

/**
 * @author André de Oliveira
 */
public class IndexCreator {

	public Index createIndex(IndexName indexName) {
		IndicesAdminClient indicesAdminClient = getIndicesAdminClient();

		String name = indexName.getName();

		DeleteIndexRequestBuilder deleteIndexRequestBuilder =
			indicesAdminClient.prepareDelete(name);

		deleteIndexRequestBuilder.setIndicesOptions(
			IndicesOptions.lenientExpandOpen());

		deleteIndexRequestBuilder.get();

		CreateIndexRequestBuilder createIndexRequestBuilder =
			indicesAdminClient.prepareCreate(name);

		IndexCreationHelper indexCreationHelper = getIndexCreationHelper();

		indexCreationHelper.contribute(createIndexRequestBuilder);

		Settings.Builder builder = Settings.builder();

		builder.put("index.number_of_replicas", 0);
		builder.put("index.number_of_shards", 1);

		indexCreationHelper.contributeIndexSettings(builder);

		createIndexRequestBuilder.setSettings(builder);

		createIndexRequestBuilder.get();

		indexCreationHelper.whenIndexCreated(name);

		return new Index(indexName);
	}

	protected IndexCreationHelper getIndexCreationHelper() {
		if (!_liferayMappingsAddedToIndex) {
			if (_indexCreationHelper != null) {
				return _indexCreationHelper;
			}

			return Mockito.mock(IndexCreationHelper.class);
		}

		LiferayIndexCreationHelper liferayIndexCreationHelper =
			new LiferayIndexCreationHelper(_elasticsearchClientResolver);

		if (_indexCreationHelper == null) {
			return liferayIndexCreationHelper;
		}

		return new IndexCreationHelper() {

			@Override
			public void contribute(
				CreateIndexRequestBuilder createIndexRequestBuilder) {

				_indexCreationHelper.contribute(createIndexRequestBuilder);

				liferayIndexCreationHelper.contribute(
					createIndexRequestBuilder);
			}

			@Override
			public void contributeIndexSettings(Settings.Builder builder) {
				_indexCreationHelper.contributeIndexSettings(builder);

				liferayIndexCreationHelper.contributeIndexSettings(builder);
			}

			@Override
			public void whenIndexCreated(String indexName) {
				_indexCreationHelper.whenIndexCreated(indexName);

				liferayIndexCreationHelper.whenIndexCreated(indexName);
			}

		};
	}

	protected final IndicesAdminClient getIndicesAdminClient() {
		Client client = _elasticsearchClientResolver.getClient();

		AdminClient adminClient = client.admin();

		return adminClient.indices();
	}

	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	protected void setIndexCreationHelper(
		IndexCreationHelper indexCreationHelper) {

		_indexCreationHelper = indexCreationHelper;
	}

	protected void setLiferayMappingsAddedToIndex(
		boolean liferayMappingsAddedToIndex) {

		_liferayMappingsAddedToIndex = liferayMappingsAddedToIndex;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;
	private IndexCreationHelper _indexCreationHelper;
	private boolean _liferayMappingsAddedToIndex;

}