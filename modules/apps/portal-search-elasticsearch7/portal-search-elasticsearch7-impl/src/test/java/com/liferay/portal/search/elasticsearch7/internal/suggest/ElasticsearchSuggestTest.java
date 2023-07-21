/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.suggest;

import com.liferay.portal.search.elasticsearch7.internal.LiferayElasticsearchIndexingFixtureFactory;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;
import com.liferay.portal.search.test.util.suggest.BaseSuggestTestCase;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.elasticsearch.action.search.SearchPhaseExecutionException;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author André de Oliveira
 */
public class ElasticsearchSuggestTest extends BaseSuggestTestCase {

	@ClassRule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Override
	@Test
	public void testMultipleWords() throws Exception {
		indexSuccessfulQuery("indexed this phrase");

		assertSuggest(
			"[indexef phrase, index phrasd]", "indexef   this   phrasd", 2);
	}

	@Override
	@Test
	public void testNothingToSuggest() throws Exception {
		indexSuccessfulQuery("creating the keywordSearch mapping");

		assertSuggest("[]", "nothign");
	}

	@Override
	@Test
	public void testNull() throws Exception {
		expectedException.expect(SearchPhaseExecutionException.class);
		expectedException.expectMessage("all shards failed");

		indexSuccessfulQuery("creating the keywordSearch mapping");

		assertSuggest("[]", null);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Override
	protected IndexingFixture createIndexingFixture() {
		return LiferayElasticsearchIndexingFixtureFactory.getInstance();
	}

}