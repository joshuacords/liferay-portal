/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.rescore;

import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.document.Field;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.rescore.Rescore;
import com.liferay.portal.search.rescore.RescoreBuilder;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Adam Brandizzi
 */
public abstract class BaseRescoreTestCase extends BaseIndexingTestCase {

	@Test
	public void testRescore() {
		addDocuments(
			value -> DocumentCreationHelpers.singleText(_TITLE, value),
			Arrays.asList("alpha zeta", "alpha alpha", "alpha beta beta"));

		Query query = queries.string(_TITLE.concat(":alpha"));

		assertSearch(
			_TITLE, query, (List<Rescore>)null,
			Arrays.asList("alpha alpha", "alpha zeta", "alpha beta beta"));

		assertSearch(
			_TITLE, query, Arrays.asList(buildRescore(_TITLE, "beta")),
			Arrays.asList("alpha beta beta", "alpha alpha", "alpha zeta"));
	}

	@Test
	public void testRescoreQuery() {
		addDocuments(
			value -> DocumentCreationHelpers.singleText(_TITLE, value),
			Arrays.asList(
				"alpha alpha", "alpha gamma gamma", "alpha beta beta"));

		Query query = queries.string(_TITLE.concat(":alpha"));

		assertSearch(
			_TITLE, query, (Query)null,
			Arrays.asList(
				"alpha alpha", "alpha gamma gamma", "alpha beta beta"));

		Query rescoreQuery = queries.match(_TITLE, "beta");

		assertSearch(
			_TITLE, query, rescoreQuery,
			Arrays.asList(
				"alpha beta beta", "alpha alpha", "alpha gamma gamma"));
	}

	@Test
	public void testRescores() {
		addDocuments(
			value -> DocumentCreationHelpers.singleText(_TITLE, value),
			Arrays.asList(
				"alpha alpha", "alpha gamma gamma", "alpha beta beta beta"));

		Query query = queries.string(_TITLE.concat(":alpha"));

		assertSearch(
			_TITLE, query, (List<Rescore>)null,
			Arrays.asList(
				"alpha alpha", "alpha gamma gamma", "alpha beta beta beta"));

		assertSearch(
			_TITLE, query,
			Arrays.asList(
				buildRescore(_TITLE, "beta"), buildRescore(_TITLE, "gamma")),
			Arrays.asList(
				"alpha beta beta beta", "alpha gamma gamma", "alpha alpha"));
	}

	protected void assertSearch(
		String fieldName, Query query, List<Rescore> rescores,
		List<String> expectedValues) {

		assertSearch(
			indexingTestHelper -> {
				SearchSearchRequest searchSearchRequest =
					getSearchSearchRequest(query);

				searchSearchRequest.setRescores(rescores);

				List<String> actualValues = getFieldValues(
					searchSearchRequest, fieldName);

				Assert.assertEquals(
					expectedValues.toString(), actualValues.toString());
			});
	}

	protected void assertSearch(
		String fieldName, Query query, Query rescoreQuery,
		List<String> expectedValues) {

		assertSearch(
			indexingTestHelper -> {
				SearchSearchRequest searchSearchRequest =
					getSearchSearchRequest(query);

				searchSearchRequest.setRescoreQuery(rescoreQuery);

				List<String> actualValues = getFieldValues(
					searchSearchRequest, fieldName);

				Assert.assertEquals(
					expectedValues.toString(), actualValues.toString());
			});
	}

	protected Rescore buildRescore(String fieldName, String value) {
		RescoreBuilder rescoreBuilder =
			rescoreBuilderFactory.getRescoreBuilder();

		Query rescoreQuery = queries.match(fieldName, value);

		return rescoreBuilder.query(
			rescoreQuery
		).windowSize(
			100
		).build();
	}

	protected String getFieldValue(String fieldName, SearchHit searchHit) {
		Document document = searchHit.getDocument();

		Map<String, Field> fields = document.getFields();

		Field field = fields.get(fieldName);

		return (String)field.getValue();
	}

	protected List<String> getFieldValues(
		SearchSearchRequest searchSearchRequest, String fieldName) {

		SearchEngineAdapter searchEngineAdapter = getSearchEngineAdapter();

		SearchSearchResponse searchSearchResponse = searchEngineAdapter.execute(
			searchSearchRequest);

		SearchHits searchHits = searchSearchResponse.getSearchHits();

		List<SearchHit> searchHitsList = searchHits.getSearchHits();

		Stream<SearchHit> stream = searchHitsList.stream();

		return stream.map(
			searchHit -> getFieldValue(fieldName, searchHit)
		).collect(
			Collectors.toList()
		);
	}

	protected SearchSearchRequest getSearchSearchRequest(Query query) {
		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		searchSearchRequest.setIndexNames("_all");
		searchSearchRequest.setQuery(query);
		searchSearchRequest.setSize(30);

		return searchSearchRequest;
	}

	private static final String _TITLE = "title";

}