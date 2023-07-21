/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.document;

import com.liferay.portal.search.elasticsearch6.internal.connection.IndexName;
import com.liferay.portal.search.elasticsearch6.internal.query.QueryBuilderFactory;
import com.liferay.portal.search.elasticsearch6.internal.query.SearchAssert;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author André de Oliveira
 */
public class SingleFieldFixture {

	public SingleFieldFixture(Client client, IndexName indexName, String type) {
		_client = client;
		_type = type;

		_index = indexName.getName();
	}

	public void assertNoHits(String text) throws Exception {
		SearchAssert.assertNoHits(_client, _field, _createQueryBuilder(text));
	}

	public void assertSearch(String text, String... expected) throws Exception {
		SearchAssert.assertSearch(
			_client, _field, _createQueryBuilder(text), expected);
	}

	public void indexDocument(String value) {
		IndexRequestBuilder indexRequestBuilder = _client.prepareIndex(
			_index, _type);

		indexRequestBuilder.setSource(_field, value);

		indexRequestBuilder.get();
	}

	public void setField(String field) {
		_field = field;
	}

	public void setQueryBuilderFactory(
		QueryBuilderFactory queryBuilderFactory) {

		_queryBuilderFactory = queryBuilderFactory;
	}

	private QueryBuilder _createQueryBuilder(String text) {
		return _queryBuilderFactory.create(_field, text);
	}

	private final Client _client;
	private String _field;
	private final String _index;
	private QueryBuilderFactory _queryBuilderFactory;
	private final String _type;

}