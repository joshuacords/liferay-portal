/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.query;

import com.liferay.portal.search.internal.query.BooleanQueryImpl;
import com.liferay.portal.search.internal.query.CommonTermsQueryImpl;
import com.liferay.portal.search.internal.query.FuzzyQueryImpl;
import com.liferay.portal.search.internal.query.MatchAllQueryImpl;
import com.liferay.portal.search.internal.query.MoreLikeThisQueryImpl;
import com.liferay.portal.search.internal.query.TermQueryImpl;
import com.liferay.portal.search.internal.query.WildcardQueryImpl;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Collections;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Bryan Engler
 */
public class ElasticsearchQueryTranslatorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		ElasticsearchQueryTranslatorFixture
			elasticsearchQueryTranslatorFixture =
				new ElasticsearchQueryTranslatorFixture();

		_elasticsearchQueryTranslator =
			elasticsearchQueryTranslatorFixture.
				getElasticsearchQueryTranslator();
	}

	@Test
	public void testTranslateBoostCommonTermsQuery() {
		_assertBoosts(new CommonTermsQueryImpl("test", "test"));
	}

	@Test
	public void testTranslateBoostFuzzyQuery() {
		_assertBoosts(new FuzzyQueryImpl("test", "test"));
	}

	@Test
	public void testTranslateBoostMatchAllQuery() {
		_assertBoosts(new MatchAllQueryImpl());
	}

	@Test
	public void testTranslateBoostMoreLikeThisQueryStringQuery() {
		_assertBoosts(
			new MoreLikeThisQueryImpl(Collections.emptyList(), "test"));
	}

	@Test
	public void testTranslateBoostTermQuery() {
		_assertBoosts(new TermQueryImpl("test", "test"));
	}

	@Test
	public void testTranslateBoostWildcardQuery() {
		_assertBoosts(new WildcardQueryImpl("test", "test"));
	}

	private void _assertBoost(Query query) {
		query.setBoost(_BOOST);

		QueryBuilder queryBuilder = _elasticsearchQueryTranslator.translate(
			query);

		Assert.assertEquals(
			queryBuilder.toString(), String.valueOf(_BOOST),
			String.valueOf(queryBuilder.boost()));
	}

	private void _assertBoosts(Query query) {
		_assertBoost(query);
		_assertInnerBoost(query);
	}

	private void _assertInnerBoost(Query query) {
		query.setBoost(_BOOST);

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.addMustQueryClauses(query);

		QueryBuilder queryBuilder = _elasticsearchQueryTranslator.translate(
			booleanQuery);

		List<QueryBuilder> mustQueryBuilders =
			((BoolQueryBuilder)queryBuilder).must();

		QueryBuilder innerQueryBuilder = mustQueryBuilders.get(0);

		Assert.assertEquals(
			innerQueryBuilder.toString(), String.valueOf(_BOOST),
			String.valueOf(innerQueryBuilder.boost()));
	}

	private static final Float _BOOST = 1.5F;

	private ElasticsearchQueryTranslator _elasticsearchQueryTranslator;

}