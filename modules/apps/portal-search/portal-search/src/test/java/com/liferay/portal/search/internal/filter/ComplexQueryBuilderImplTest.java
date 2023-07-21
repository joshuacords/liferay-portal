/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.filter;

import com.liferay.portal.search.filter.ComplexQueryPart;
import com.liferay.portal.search.filter.ComplexQueryPartBuilderFactory;
import com.liferay.portal.search.internal.query.BooleanQueryImpl;
import com.liferay.portal.search.internal.query.QueriesImpl;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.FuzzyQuery;
import com.liferay.portal.search.query.MatchQuery;
import com.liferay.portal.search.query.NestedQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.script.Scripts;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author Wade Cao
 */
public class ComplexQueryBuilderImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFilterFuzzyQuery() {
		ComplexQueryBuilderImpl complexQueryBuilderImpl =
			new ComplexQueryBuilderImpl(_queries, _scripts);

		Query query = _getQuery(
			complexQueryBuilderImpl, "fuzzy", "it is fuzzyyyyy");

		Assert.assertTrue(query instanceof FuzzyQuery);
	}

	@Test
	public void testFilterMatchQuery() {
		ComplexQueryBuilderImpl complexQueryBuilderImpl =
			new ComplexQueryBuilderImpl(_queries, _scripts);

		Query query = _getQuery(complexQueryBuilderImpl, "match", "match-me");

		Assert.assertTrue(query instanceof MatchQuery);
	}

	@Test
	public void testFilterNestedQuery() {
		ComplexQueryBuilderImpl complexQueryBuilderImpl =
			new ComplexQueryBuilderImpl(_queries, _scripts);

		Query query = _getQuery(complexQueryBuilderImpl, "nested", "path");

		Assert.assertTrue(query instanceof NestedQuery);

		NestedQuery nestedQuery = (NestedQuery)query;

		Assert.assertTrue(nestedQuery.getQuery() instanceof BooleanQuery);
	}

	@Test
	public void testInvalidType() {
		ComplexQueryBuilderImpl complexQueryBuilderImpl =
			new ComplexQueryBuilderImpl(_queries, _scripts);

		List<Query> queries = _getQueries(
			complexQueryBuilderImpl, "whatever", "[now/d now+1d/d[");

		Assert.assertTrue(queries.isEmpty());
	}

	@Test
	public void testInvalidType2() {
		ComplexQueryBuilderImpl complexQueryBuilderImpl =
			new ComplexQueryBuilderImpl(_queries, _scripts);

		List<Query> queries = _getQueries(
			complexQueryBuilderImpl, "whatever", "]10 20]");

		Assert.assertTrue(queries.isEmpty());
	}

	private List<Query> _getQueries(
		ComplexQueryBuilderImpl complexQueryBuilderImpl, String type,
		String value) {

		BooleanQuery booleanQuery = (BooleanQuery)complexQueryBuilderImpl.root(
			new BooleanQueryImpl()
		).addParts(
			new ArrayList<ComplexQueryPart>() {

				private static final long serialVersionUID = 1L;

				{
					add(
						_complexQueryPartBuilderFactory.builder(
						).boost(
							Float.valueOf(1.0F)
						).disabled(
							false
						).field(
							"modified"
						).name(
							"myTerm"
						).occur(
							"filter"
						).parent(
							"no parent"
						).type(
							type
						).value(
							value
						).build());
				}
			}
		).build();

		return booleanQuery.getFilterQueryClauses();
	}

	private Query _getQuery(
		ComplexQueryBuilderImpl complexQueryBuilderImpl, String type,
		String value) {

		List<Query> queries = _getQueries(complexQueryBuilderImpl, type, value);

		return queries.get(0);
	}

	private final ComplexQueryPartBuilderFactory
		_complexQueryPartBuilderFactory =
			new ComplexQueryPartBuilderFactoryImpl();
	private final Queries _queries = new QueriesImpl();

	@Mock
	private Scripts _scripts;

}