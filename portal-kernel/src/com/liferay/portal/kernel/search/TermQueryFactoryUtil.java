/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

/**
 * @author     Brian Wing Shun Chan
 * @author     Michael C. Han
 * @author     Raymond Augé
 * @deprecated As of Wilberforce (7.0.x), , replaced by {@link
 *             com.liferay.portal.kernel.search.generic.TermQueryImpl}
 */
@Deprecated
public class TermQueryFactoryUtil {

	public static TermQuery create(
		SearchContext searchContext, String field, long value) {

		TermQueryFactory termQueryFactory = getTermQueryFactory(searchContext);

		return termQueryFactory.create(field, value);
	}

	public static TermQuery create(
		SearchContext searchContext, String field, String value) {

		TermQueryFactory termQueryFactory = getTermQueryFactory(searchContext);

		return termQueryFactory.create(field, value);
	}

	public static TermQueryFactory getTermQueryFactory(
		SearchContext searchContext) {

		SearchEngine searchEngine = SearchEngineHelperUtil.getSearchEngine(
			searchContext.getSearchEngineId());

		return searchEngine.getTermQueryFactory();
	}

}