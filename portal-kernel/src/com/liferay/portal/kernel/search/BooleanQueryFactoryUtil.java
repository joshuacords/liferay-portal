/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

/**
 * @author     Brian Wing Shun Chan
 * @author     Raymond Augé
 * @deprecated As of Wilberforce (7.0.x), , replaced by {@link
 *             com.liferay.portal.kernel.search.generic.BooleanQueryImpl}
 */
@Deprecated
public class BooleanQueryFactoryUtil {

	public static BooleanQuery create(SearchContext searchContext) {
		BooleanQueryFactory booleanQueryFactory = getBooleanQueryFactory(
			searchContext);

		return booleanQueryFactory.create();
	}

	public static BooleanQueryFactory getBooleanQueryFactory(
		SearchContext searchContext) {

		SearchEngine searchEngine = SearchEngineHelperUtil.getSearchEngine(
			searchContext.getSearchEngineId());

		return searchEngine.getBooleanQueryFactory();
	}

}