/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.util.count;

import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.search.test.util.document.BaseDocumentTestCase;

import org.junit.Test;

/**
 * @author Wade Cao
 */
public abstract class BaseDocumentCountTestCase extends BaseDocumentTestCase {

	@Test
	public void testAllWordsInAllDocuments() throws Exception {
		assertCount("sixth fifth fourth third second first", 6);
	}

	@Test
	public void testOneWordInAllDocuments() throws Exception {
		assertCount("Smith", 6);
	}

	@Test
	public void testOneWordPerDocument() throws Exception {
		assertCount("first", 1);

		assertCount("second", 1);

		assertCount("third", 1);

		assertCount("fourth", 1);

		assertCount("fifth", 1);

		assertCount("sixth", 1);
	}

	protected void assertCount(String keywords, int expectedCount)
		throws Exception {

		assertSearch(
			indexingTestHelper -> {
				indexingTestHelper.setQuery(getQuery(keywords));

				indexingTestHelper.search();

				indexingTestHelper.assertResultCount(expectedCount);
			});
	}

	protected Query getQuery(String keywords) {
		BooleanQueryImpl booleanQueryImpl = new BooleanQueryImpl();

		booleanQueryImpl.add(
			new MatchQuery("firstName", keywords), BooleanClauseOccur.SHOULD);
		booleanQueryImpl.add(
			new MatchQuery("lastName", keywords), BooleanClauseOccur.SHOULD);

		return booleanQueryImpl;
	}

}