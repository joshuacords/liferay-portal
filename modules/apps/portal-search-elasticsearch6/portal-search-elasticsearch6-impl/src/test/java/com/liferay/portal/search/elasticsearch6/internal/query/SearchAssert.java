/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.query;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.search.test.util.IdempotentRetryAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import org.junit.Assert;

/**
 * @author André de Oliveira
 */
public class SearchAssert {

	public static void assertNoHits(
			Client client, String field, QueryBuilder queryBuilder)
		throws Exception {

		assertSearch(client, field, queryBuilder, new String[0]);
	}

	public static void assertSearch(
			final Client client, final String field,
			final QueryBuilder queryBuilder, final String... expectedValues)
		throws Exception {

		assertSearch(() -> search(client, queryBuilder), field, expectedValues);
	}

	public static void assertSearch(
			SearchRequestBuilder searchRequestBuilder, String field,
			String... expectedValues)
		throws Exception {

		assertSearch(() -> search(searchRequestBuilder), field, expectedValues);
	}

	protected static void assertSearch(
			Supplier<SearchHits> supplier, String field,
			String... expectedValues)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			10, TimeUnit.SECONDS,
			() -> Assert.assertEquals(
				sort(Arrays.asList(expectedValues)),
				sort(getValues(supplier.get(), field))));
	}

	protected static List<String> getValues(
		SearchHits searchHits, String field) {

		List<String> values = new ArrayList<>();

		for (SearchHit searchHit : searchHits.getHits()) {
			DocumentField documentField = searchHit.field(field);

			values.add(documentField.getValue());
		}

		return values;
	}

	protected static SearchHits search(
		Client client, QueryBuilder queryBuilder) {

		SearchRequestBuilder searchRequestBuilder = client.prepareSearch();

		searchRequestBuilder.setQuery(queryBuilder);

		return search(searchRequestBuilder);
	}

	protected static SearchHits search(
		SearchRequestBuilder searchRequestBuilder) {

		searchRequestBuilder.addStoredField(StringPool.STAR);

		SearchResponse searchResponse = searchRequestBuilder.get();

		return searchResponse.getHits();
	}

	protected static String sort(Collection<String> collection) {
		List<String> list = new ArrayList<>(collection);

		Collections.sort(list);

		return list.toString();
	}

}