/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.searcher;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Joshua Cords
 */
public class SearchRequestImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testSetFromAndSize() {
		SearchContext searchContext = new SearchContext();

		SearchRequestImpl searchRequestImpl = new SearchRequestImpl(
			searchContext);

		searchRequestImpl.setFrom(10);
		searchRequestImpl.setSize(20);

		_assertWindow(10, 20, searchContext, searchRequestImpl);

		searchRequestImpl.setFrom(30);

		_assertWindow(30, 20, searchContext, searchRequestImpl);
	}

	@Test
	public void testSetSearchContextStartAndEnd() {
		SearchContext searchContext = new SearchContext();

		SearchRequestImpl searchRequestImpl = new SearchRequestImpl(
			searchContext);

		searchRequestImpl.setFrom(0);
		searchRequestImpl.setSize(8);

		searchContext.setEnd(22);
		searchContext.setStart(10);

		_assertWindow(10, 12, searchContext, searchRequestImpl);

		searchContext.setEnd(QueryUtil.ALL_POS);
		searchContext.setStart(QueryUtil.ALL_POS);

		Assert.assertNull(searchRequestImpl.getFrom());
		Assert.assertNull(searchRequestImpl.getSize());
	}

	@Test
	public void testSetSizeAndFrom() {
		SearchContext searchContext = new SearchContext();

		SearchRequestImpl searchRequestImpl = new SearchRequestImpl(
			searchContext);

		searchRequestImpl.setSize(20);
		searchRequestImpl.setFrom(10);

		_assertWindow(10, 20, searchContext, searchRequestImpl);

		searchRequestImpl.setSize(0);

		_assertWindow(10, 0, searchContext, searchRequestImpl);
	}

	private void _assertWindow(
		int from, int size, SearchContext searchContext,
		SearchRequestImpl searchRequestImpl) {

		Assert.assertEquals(Integer.valueOf(from), searchRequestImpl.getFrom());
		Assert.assertEquals(Integer.valueOf(size), searchRequestImpl.getSize());
		Assert.assertEquals(from, searchContext.getStart());
		Assert.assertEquals(from + size, searchContext.getEnd());
	}

}