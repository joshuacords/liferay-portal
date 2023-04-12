package com.liferay.portal.search.internal.filter;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.filter.FilterVisitor;
import com.liferay.portal.search.filter.QueryFilter;

public class QueryFilterImpl implements QueryFilter {

	public QueryFilterImpl(Query query) {
		_query = query;
	}

	@Override
	public <T> T accept(FilterVisitor<T> filterVisitor) {
		return filterVisitor.visit(this);
	}

	@Override
	public String getExecutionOption() {
		return null;
	}

	@Override
	public Query getQuery() {
		return _query;
	}

	@Override
	public int getSortOrder() {
		return 30;
	}

	@Override
	public Boolean isCached() {
		return true;
	}

	@Override
	public void setCached(Boolean cached) {
	}

	@Override
	public void setExecutionOption(String executionOption) {
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{(query=", _query, "), ", super.toString(), "}");
	}

	private final Query _query;
}
