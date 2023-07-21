/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.highlight;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.highlight.Highlight;
import com.liferay.portal.search.highlight.HighlightField;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public abstract class BaseHighlighterTestCase extends BaseIndexingTestCase {

	@Test
	public void testHighlight() {
		String fieldName = Field.TITLE;

		addDocuments(
			value -> DocumentCreationHelpers.singleText(fieldName, value),
			Arrays.asList(
				"alpha", "alpha beta", "alpha beta alpha",
				"alpha beta gamma alpha eta theta alpha zeta eta alpha iota",
				"alpha beta gamma delta epsilon zeta eta theta iota alpha"));

		Query query = queries.string(fieldName.concat(":alpha"));

		Highlight highlight = highlights.builder(
		).addFieldConfig(
			highlights.fieldConfigBuilder(
			).field(
				fieldName
			).build()
		).fragmentSize(
			20
		).postTags(
			"[/H]"
		).preTags(
			"[H]"
		).build();

		assertSearch(
			fieldName, query, highlight,
			Arrays.asList(
				"[H]alpha[/H]", "[H]alpha[/H] beta",
				"[H]alpha[/H] beta [H]alpha[/H]",
				"[H]alpha[/H] beta gamma [H]alpha[/H]",
				"[H]alpha[/H] beta gamma delta", "eta [H]alpha[/H] iota",
				"eta theta [H]alpha[/H] zeta",
				"zeta eta theta iota [H]alpha[/H]"));
	}

	protected void assertSearch(
		String fieldName, Query query, Highlight highlight,
		List<String> expectedValues) {

		assertSearch(
			indexingTestHelper -> {
				SearchSearchRequest searchSearchRequest =
					new SearchSearchRequest();

				searchSearchRequest.setIndexNames("_all");
				searchSearchRequest.setQuery(query);
				searchSearchRequest.setSize(30);

				searchSearchRequest.setHighlight(highlight);

				SearchEngineAdapter searchEngineAdapter =
					getSearchEngineAdapter();

				SearchSearchResponse searchSearchResponse =
					searchEngineAdapter.execute(searchSearchRequest);

				SearchHits searchHits = searchSearchResponse.getSearchHits();

				List<SearchHit> searchHitsList = searchHits.getSearchHits();

				List<String> actualValues = new ArrayList<>();

				Stream<SearchHit> stream = searchHitsList.stream();

				stream.map(
					searchHit -> getFragments(fieldName, searchHit)
				).forEach(
					actualValues::addAll
				);

				Collections.sort(actualValues);

				Collections.sort(expectedValues);

				Assert.assertEquals(
					"Highlighted texts ->" + actualValues,
					expectedValues.toString(), actualValues.toString());
			});
	}

	protected List<String> getFragments(String fieldName, SearchHit searchHit) {
		Map<String, HighlightField> highlightFields =
			searchHit.getHighlightFieldsMap();

		HighlightField highlightField = highlightFields.get(fieldName);

		return highlightField.getFragments();
	}

}