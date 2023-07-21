/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.generic.BooleanClauseFactoryImpl;

/**
 * @author Bruno Farache
 */
public class BooleanClauseFactoryUtil {

	public static BooleanClause<Query> create(Query query, String occur) {
		return getBooleanClauseFactory().create(query, occur);
	}

	public static BooleanClause<Query> create(
		String field, String value, String occur) {

		return getBooleanClauseFactory().create(field, value, occur);
	}

	public static BooleanClause<Filter> createFilter(
		Filter filter, BooleanClauseOccur booleanClauseOccur) {

		return getBooleanClauseFactory().createFilter(
			filter, booleanClauseOccur);
	}

	public static BooleanClause<Filter> createFilter(
		SearchContext searchContext, Filter filter,
		BooleanClauseOccur booleanClauseOccur) {

		return getBooleanClauseFactory().createFilter(
			filter, booleanClauseOccur);
	}

	public static BooleanClause<Filter> createFilter(
		SearchContext searchContext, String field, String value,
		BooleanClauseOccur booleanClauseOccur) {

		return getBooleanClauseFactory().createFilter(
			field, value, booleanClauseOccur);
	}

	public static BooleanClause<Filter> createFilter(
		String field, String value, BooleanClauseOccur booleanClauseOccur) {

		return getBooleanClauseFactory().createFilter(
			field, value, booleanClauseOccur);
	}

	public static BooleanClauseFactory getBooleanClauseFactory() {
		return _booleanClauseFactory;
	}

	private static final BooleanClauseFactory _booleanClauseFactory =
		new BooleanClauseFactoryImpl();

}