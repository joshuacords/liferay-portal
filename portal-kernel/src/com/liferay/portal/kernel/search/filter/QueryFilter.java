/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.filter;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.search.Query;

/**
 * @author Michael C. Han
 */
public class QueryFilter extends BaseFilter {

	public QueryFilter(Query query) {
		_query = query;
	}

	@Override
	public <T> T accept(FilterVisitor<T> filterVisitor) {
		return filterVisitor.visit(this);
	}

	public Query getQuery() {
		return _query;
	}

	@Override
	public int getSortOrder() {
		return 30;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{(query=");
		sb.append(_query);
		sb.append("), ");
		sb.append(super.toString());
		sb.append("}");

		return sb.toString();
	}

	private final Query _query;

}