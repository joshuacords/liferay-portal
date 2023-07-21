/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.query;

import com.liferay.portal.search.elasticsearch7.internal.LiferayElasticsearchIndexingFixtureFactory;
import com.liferay.portal.search.query.MoreLikeThisQuery;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.search.test.util.query.BaseMoreLikeThisQueryTestCase;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Collections;

import org.elasticsearch.action.search.SearchPhaseExecutionException;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Wade Cao
 */
public class MoreLikeThisQueryTest extends BaseMoreLikeThisQueryTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Override
	@Test
	public void testMoreLikeThisWithoutFields() throws Exception {
		addDocuments("java eclipse", "eclipse liferay", "java liferay eclipse");

		MoreLikeThisQuery moreLikeThisQuery = queries.moreLikeThis(
			Collections.emptyList(), "java");

		try {
			assertSearch(moreLikeThisQuery, Collections.emptyList());

			Assert.fail();
		}
		catch (SearchPhaseExecutionException searchPhaseExecutionException) {
			Throwable throwable = searchPhaseExecutionException.getRootCause();

			String message = throwable.getMessage();

			Assert.assertTrue(
				message,
				message.contains(
					"[more_like_this] query cannot infer the field"));
		}
	}

	@Override
	protected IndexingFixture createIndexingFixture() throws Exception {
		return LiferayElasticsearchIndexingFixtureFactory.getInstance();
	}

}