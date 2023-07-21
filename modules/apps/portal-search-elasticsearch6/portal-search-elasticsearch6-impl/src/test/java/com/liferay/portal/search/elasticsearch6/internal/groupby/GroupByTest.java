/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.groupby;

import com.liferay.portal.kernel.search.GroupBy;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.search.elasticsearch6.internal.LiferayElasticsearchIndexingFixtureFactory;
import com.liferay.portal.search.groupby.GroupByRequest;
import com.liferay.portal.search.groupby.GroupByResponse;
import com.liferay.portal.search.test.util.groupby.BaseGroupByTestCase;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author André de Oliveira
 * @author Tibor Lipusz
 */
public class GroupByTest extends BaseGroupByTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testGroupByDocsSizeDefault() throws Exception {
		indexDuplicates("five", 5);

		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.define(
					searchContext -> searchContext.setGroupBy(
						new GroupBy(GROUP_FIELD)));

				indexingTestHelper.search();

				indexingTestHelper.verify(
					hits -> assertGroups(
						toMap("five", "5|3"), hits, indexingTestHelper));
			});
	}

	@Test
	public void testGroupByDocsSizeZero() throws Exception {
		indexDuplicates("five", 5);

		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.define(
					searchContext -> {
						GroupBy groupBy = new GroupBy(GROUP_FIELD);

						groupBy.setSize(0);

						searchContext.setGroupBy(groupBy);
					});

				indexingTestHelper.search();

				indexingTestHelper.verify(
					hits -> assertGroups(
						toMap("five", "5|3"), hits, indexingTestHelper));
			});
	}

	@Test
	public void testGroupByTermsSortsCountAscKeyAsc() throws Exception {
		List<String> orderedResults = new ArrayList<>();

		orderedResults.add("one|2|2");
		orderedResults.add("two|2|2");
		orderedResults.add("three|3|3");

		assertGroupByTermsSortsCountDescKeyDesc(orderedResults, false, false);
	}

	@Test
	public void testGroupByTermsSortsCountAscKeyDesc() throws Exception {
		List<String> orderedResults = new ArrayList<>();

		orderedResults.add("two|2|2");
		orderedResults.add("one|2|2");
		orderedResults.add("three|3|3");

		assertGroupByTermsSortsCountDescKeyDesc(orderedResults, false, true);
	}

	@Test
	public void testGroupByTermsSortsCountDescKeyAsc() throws Exception {
		List<String> orderedResults = new ArrayList<>();

		orderedResults.add("three|3|3");
		orderedResults.add("one|2|2");
		orderedResults.add("two|2|2");

		assertGroupByTermsSortsCountDescKeyDesc(orderedResults, true, false);
	}

	@Test
	public void testGroupByTermsSortsCountDescKeyDesc() throws Exception {
		List<String> orderedResults = new ArrayList<>();

		orderedResults.add("three|3|3");
		orderedResults.add("two|2|2");
		orderedResults.add("one|2|2");

		assertGroupByTermsSortsCountDescKeyDesc(orderedResults, true, true);
	}

	@Test
	public void testGroupByTermsSortsDefault() throws Exception {
		List<String> orderedResults = new ArrayList<>();

		orderedResults.add("three|3|3");
		orderedResults.add("one|2|2");
		orderedResults.add("two|2|2");

		indexTermsSortsDuplicates();

		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.define(
					searchContext -> {
						GroupBy groupBy = new GroupBy(GROUP_FIELD);

						searchContext.setGroupBy(groupBy);
					});

				indexingTestHelper.search();

				indexingTestHelper.verify(
					hits -> assertGroupsOrdered(
						orderedResults, hits.getGroupedHits(),
						indexingTestHelper));
			});
	}

	@Test
	public void testMultipleGroupByRequests() throws Exception {
		indexDuplicates("three", 3);
		indexDuplicates("two", 2);

		List<String> groupFieldOrderedResults = new ArrayList<>();

		groupFieldOrderedResults.add("three|3|3");
		groupFieldOrderedResults.add("two|2|2");

		List<String> sortFieldOrderedResults = new ArrayList<>();

		sortFieldOrderedResults.add("1|2|2");
		sortFieldOrderedResults.add("2|2|2");
		sortFieldOrderedResults.add("3|1|1");

		Map<String, List<String>> orderedResultsMap =
			HashMapBuilder.<String, List<String>>put(
				GROUP_FIELD, groupFieldOrderedResults
			).put(
				SORT_FIELD, sortFieldOrderedResults
			).build();

		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.defineRequest(
					searchRequestBuilder -> {
						GroupByRequest groupByRequest =
							groupByRequestFactory.getGroupByRequest(
								GROUP_FIELD);

						GroupByRequest groupByRequest2 =
							groupByRequestFactory.getGroupByRequest(SORT_FIELD);

						searchRequestBuilder.groupByRequests(
							groupByRequest, groupByRequest2);
					});

				indexingTestHelper.search();

				indexingTestHelper.verifyResponse(
					searchResponse -> {
						List<GroupByResponse> groupByResponses =
							searchResponse.getGroupByResponses();

						Assert.assertEquals(
							groupByResponses.toString(), 2,
							groupByResponses.size());

						assertMultipleGroupsOrdered(
							orderedResultsMap, groupByResponses,
							indexingTestHelper);
					});
			});
	}

	protected void assertGroupByTermsSortsCountDescKeyDesc(
			List<String> orderedResults, boolean countDesc, boolean keyDesc)
		throws Exception {

		indexTermsSortsDuplicates();

		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.defineRequest(
					searchRequestBuilder -> {
						Sort[] sorts = new Sort[2];

						sorts[0] = new Sort("_count", countDesc);
						sorts[1] = new Sort("_key", keyDesc);

						GroupByRequest groupByRequest =
							groupByRequestFactory.getGroupByRequest(
								GROUP_FIELD);

						groupByRequest.setTermsSorts(sorts);

						searchRequestBuilder.groupByRequests(groupByRequest);
					});

				indexingTestHelper.search();

				indexingTestHelper.verify(
					hits -> assertGroupsOrdered(
						orderedResults, hits.getGroupedHits(),
						indexingTestHelper));

				indexingTestHelper.verifyResponse(
					searchResponse -> {
						List<GroupByResponse> groupByResponses =
							searchResponse.getGroupByResponses();

						Assert.assertEquals(
							groupByResponses.toString(), 1,
							groupByResponses.size());

						GroupByResponse groupByResponse = groupByResponses.get(
							0);

						assertGroupsOrdered(
							orderedResults, groupByResponse.getHitsMap(),
							indexingTestHelper);
					});
			});
	}

	@Override
	protected IndexingFixture createIndexingFixture() {
		return LiferayElasticsearchIndexingFixtureFactory.getInstance();
	}

	protected void indexTermsSortsDuplicates() {
		indexDuplicates("one", 2);
		indexDuplicates("two", 2);
		indexDuplicates("three", 3);
	}

}