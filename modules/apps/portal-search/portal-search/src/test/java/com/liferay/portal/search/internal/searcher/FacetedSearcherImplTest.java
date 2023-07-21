/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.searcher;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.ExpandoQueryContributor;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcher;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.asset.SearchableAssetClassNamesProvider;
import com.liferay.portal.search.constants.SearchContextAttributes;
import com.liferay.portal.search.internal.indexer.PreFilterContributorHelper;
import com.liferay.portal.search.internal.test.util.DocumentFixture;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author André de Oliveira
 */
public class FacetedSearcherImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() throws Exception {
		Registry registry = new BasicRegistryImpl();

		RegistryUtil.setRegistry(registry);
	}

	@AfterClass
	public static void tearDownClass() {
		RegistryUtil.setRegistry(null);
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		_documentFixture = new DocumentFixture();

		_documentFixture.setUp();

		facetedSearcher = createFacetedSearcher();
	}

	@After
	public void tearDown() throws Exception {
		_documentFixture.tearDown();
	}

	@Test
	public void testEmptySearchDisabledBlank() throws Exception {
		SearchContext searchContext = new SearchContext();

		searchContext.setKeywords(StringPool.BLANK);

		assertSearchSkipped(searchContext);
	}

	@Test
	public void testEmptySearchDisabledByDefault() throws Exception {
		SearchContext searchContext = new SearchContext();

		assertSearchSkipped(searchContext);
	}

	@Test
	public void testEmptySearchDisabledSpaces() throws Exception {
		SearchContext searchContext = new SearchContext();

		searchContext.setKeywords(StringPool.FOUR_SPACES);

		assertSearchSkipped(searchContext);
	}

	@Test
	public void testEmptySearchEnabled() throws Exception {
		SearchContext searchContext = new SearchContext();

		searchContext.setAttribute(
			SearchContextAttributes.ATTRIBUTE_KEY_EMPTY_SEARCH, Boolean.TRUE);
		searchContext.setEntryClassNames(
			new String[] {RandomTestUtil.randomString()});

		Hits hits = facetedSearcher.search(searchContext);

		Assert.assertNull(hits);

		Mockito.verify(
			indexSearcherHelper
		).search(
			Mockito.same(searchContext), Mockito.any()
		);
	}

	protected void assertSearchSkipped(SearchContext searchContext)
		throws SearchException {

		Hits hits = facetedSearcher.search(searchContext);

		Assert.assertEquals(hits.toString(), 0, hits.getLength());

		Mockito.verifyZeroInteractions(indexSearcherHelper);
	}

	protected FacetedSearcherImpl createFacetedSearcher() {
		return new FacetedSearcherImpl(
			expandoQueryContributor, indexerRegistry, indexSearcherHelper,
			preFilterContributorHelper, searchableAssetClassNamesProvider);
	}

	@Mock
	protected ExpandoQueryContributor expandoQueryContributor;

	protected FacetedSearcher facetedSearcher;

	@Mock
	protected IndexerRegistry indexerRegistry;

	@Mock
	protected IndexSearcherHelper indexSearcherHelper;

	@Mock
	protected PreFilterContributorHelper preFilterContributorHelper;

	@Mock
	protected SearchableAssetClassNamesProvider
		searchableAssetClassNamesProvider;

	private DocumentFixture _documentFixture;

}