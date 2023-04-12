package com.liferay.portal.search.internal.filter;

import com.liferay.portal.search.filter.QueryFilter;
import com.liferay.portal.search.filter.QueryFilterBuilder;
import com.liferay.portal.search.query.Query;

public class QueryFilterBuilderImpl implements QueryFilterBuilder {

	@Override
	public QueryFilter build() {
		return new QueryFilterImpl(_query);
	}

	@Override
	public void setQuery(Query query) {
		_query = query;
	}

	private Query _query;
}
