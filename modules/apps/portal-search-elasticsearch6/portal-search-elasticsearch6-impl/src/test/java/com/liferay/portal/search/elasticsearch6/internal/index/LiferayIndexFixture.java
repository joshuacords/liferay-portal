/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.index;

import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch6.internal.connection.Index;
import com.liferay.portal.search.elasticsearch6.internal.connection.IndexCreator;
import com.liferay.portal.search.elasticsearch6.internal.connection.IndexName;

import java.util.Map;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;

/**
 * @author André de Oliveira
 */
public class LiferayIndexFixture {

	public LiferayIndexFixture(String subdirName, IndexName indexName) {
		_indexName = indexName;

		_elasticsearchFixture = new ElasticsearchFixture(subdirName);
	}

	public void assertAnalyzer(String field, String analyzer) throws Exception {
		FieldMappingAssert.assertAnalyzer(
			analyzer, field, LiferayTypeMappingsConstants.LIFERAY_DOCUMENT_TYPE,
			_index.getName(), _elasticsearchFixture.getIndicesAdminClient());
	}

	public void assertType(String field, String type) throws Exception {
		FieldMappingAssert.assertType(
			type, field, LiferayTypeMappingsConstants.LIFERAY_DOCUMENT_TYPE,
			_index.getName(), _elasticsearchFixture.getIndicesAdminClient());
	}

	public Client getClient() {
		return _elasticsearchFixture.getClient();
	}

	public ElasticsearchFixture getElasticsearchFixture() {
		return _elasticsearchFixture;
	}

	public Index getIndex() {
		return _index;
	}

	public void index(Map<String, Object> map) {
		IndexRequestBuilder indexRequestBuilder = getIndexRequestBuilder();

		indexRequestBuilder.setSource(map);

		indexRequestBuilder.get();
	}

	public void setUp() throws Exception {
		_elasticsearchFixture.setUp();

		_index = createIndex();
	}

	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	protected Index createIndex() {
		IndexCreator indexCreator = new IndexCreator() {
			{
				setElasticsearchClientResolver(_elasticsearchFixture);
				setLiferayMappingsAddedToIndex(true);
			}
		};

		return indexCreator.createIndex(_indexName);
	}

	protected IndexRequestBuilder getIndexRequestBuilder() {
		Client client = _elasticsearchFixture.getClient();

		return client.prepareIndex(
			_index.getName(),
			LiferayTypeMappingsConstants.LIFERAY_DOCUMENT_TYPE);
	}

	private final ElasticsearchFixture _elasticsearchFixture;
	private Index _index;
	private final IndexName _indexName;

}