/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal;

import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.search.constants.SearchContextAttributes;
import com.liferay.portal.search.elasticsearch6.constants.ElasticsearchSearchContextAttributes;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.internal.legacy.searcher.SearchRequestBuilderFactoryImpl;
import com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.test.util.indexing.DocumentFixture;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Michael C. Han
 */
public class ElasticsearchIndexSearcherTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_documentFixture.setUp();

		SearchRequestBuilderFactory searchRequestBuilderFactory =
			new SearchRequestBuilderFactoryImpl();

		_elasticsearchIndexSearcher = createElasticsearchIndexSearcher(
			searchRequestBuilderFactory);
		_searchRequestBuilderFactory = searchRequestBuilderFactory;
	}

	@After
	public void tearDown() {
		_documentFixture.tearDown();
	}

	@Test
	public void testSearchContextAttributes() throws SearchException {
		SearchContext searchContext = new SearchContext();

		searchContext.setAttribute(
			ElasticsearchSearchContextAttributes.
				ATTRIBUTE_KEY_SEARCH_REQUEST_PREFERENCE,
			"testValue");
		searchContext.setAttribute(
			SearchContextAttributes.ATTRIBUTE_KEY_BASIC_FACET_SELECTION,
			Boolean.TRUE);
		searchContext.setAttribute(
			SearchContextAttributes.ATTRIBUTE_KEY_LUCENE_SYNTAX, Boolean.TRUE);

		SearchSearchRequest searchSearchRequest =
			_elasticsearchIndexSearcher.createSearchSearchRequest(
				_searchRequestBuilderFactory.builder(
					searchContext
				).build(),
				searchContext, Mockito.mock(Query.class), 0, 0);

		Assert.assertTrue(searchSearchRequest.isBasicFacetSelection());
		Assert.assertTrue(searchSearchRequest.isLuceneSyntax());

		Assert.assertEquals("testValue", searchSearchRequest.getPreference());
	}

	protected static ElasticsearchIndexSearcher
		createElasticsearchIndexSearcher(
			SearchRequestBuilderFactory searchRequestBuilderFactory) {

		return new ElasticsearchIndexSearcher() {
			{
				setIndexNameBuilder(String::valueOf);
				setSearchRequestBuilderFactory(searchRequestBuilderFactory);
			}
		};
	}

	private final DocumentFixture _documentFixture = new DocumentFixture();
	private ElasticsearchIndexSearcher _elasticsearchIndexSearcher;
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

}