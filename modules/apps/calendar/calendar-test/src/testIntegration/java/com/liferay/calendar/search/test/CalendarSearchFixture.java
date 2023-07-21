/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.search.test;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.test.util.HitsAssert;

import java.util.Locale;
import java.util.Objects;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
public class CalendarSearchFixture {

	public CalendarSearchFixture(IndexerRegistry indexerRegistry) {
		this(indexerRegistry, null);
	}

	public CalendarSearchFixture(
		IndexerRegistry indexerRegistry,
		SearchRequestBuilderFactory searchRequestBuilderFactory) {

		_indexerRegistry = indexerRegistry;
		_searchRequestBuilderFactory = searchRequestBuilderFactory;
	}

	public SearchContext getSearchContext(String keywords, Locale locale) {
		SearchContext searchContext = new SearchContext();

		try {
			searchContext.setCompanyId(TestPropsValues.getCompanyId());
			searchContext.setUserId(getUserId());
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}

		searchContext.setGroupIds(new long[] {_group.getGroupId()});
		searchContext.setKeywords(keywords);
		searchContext.setLocale(Objects.requireNonNull(locale));

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setSelectedFieldNames(StringPool.STAR);

		return searchContext;
	}

	public Hits search(SearchContext searchContext) {
		try {
			return _indexer.search(searchContext);
		}
		catch (SearchException searchException) {
			throw new RuntimeException(searchException);
		}
	}

	public Document searchOnlyOne(String keywords, Locale locale) {
		return HitsAssert.assertOnlyOne(
			search(getSearchContext(keywords, locale)));
	}

	public SearchResponse searchOnlyOneSearchResponse(
		String keywords, Locale locale) {

		SearchContext searchContext = getSearchContext(keywords, locale);

		_searchRequestBuilderFactory.builder(
			searchContext
		).fetchSource(
			true
		).build();

		search(searchContext);

		SearchResponse searchResponse =
			(SearchResponse)searchContext.getAttribute("search.response");

		HitsAssert.assertOnlyOne(searchResponse.getSearchHits());

		return searchResponse;
	}

	public void setGroup(Group group) {
		_group = group;
	}

	public void setIndexerClass(Class<?> clazz) {
		_indexer = _indexerRegistry.getIndexer(clazz);
	}

	public void setUser(User user) {
		_user = user;
	}

	protected long getUserId() throws PortalException {
		if (_user != null) {
			return _user.getUserId();
		}

		return TestPropsValues.getUserId();
	}

	private Group _group;
	private Indexer<?> _indexer;
	private final IndexerRegistry _indexerRegistry;
	private final SearchRequestBuilderFactory _searchRequestBuilderFactory;
	private User _user;

}