package com.liferay.portal.search.internal.query;

import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.search.query.LegacyQuery;
import com.liferay.portal.kernel.search.query.QueryVisitor;

public class LegacyQueryImpl implements LegacyQuery {

	public LegacyQueryImpl(com.liferay.portal.search.query.Query modernQuery) {
		this.modernQuery = modernQuery;
	}

	com.liferay.portal.search.query.Query modernQuery;

	@Override
	public <T> T accept(QueryVisitor<T> queryVisitor) {
		Class<?> clazz = queryVisitor.getClass();
		if (clazz.getSimpleName().equals("com..liferay.portal.search.elasticsearch7.internal.legacy.query.ElasticsearchQueryTranslator")) {
			return ((ElasticsearchQueryTranslator)queryVisitor).visitQuery(this);
		}

		return null;
	}

	@Override
	public float getBoost() {
		return 0;
	}

	@Override
	public Filter getPostFilter() {
		return null;
	}

	@Override
	public BooleanFilter getPreBooleanFilter() {
		return null;
	}

	@Override
	public QueryConfig getQueryConfig() {
		return null;
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public boolean isDefaultBoost() {
		return false;
	}

	@Override
	public void setBoost(float boost) {

	}

	@Override
	public void setPostFilter(Filter filter) {

	}

	@Override
	public void setPreBooleanFilter(BooleanFilter preBooleanFilter) {

	}

	@Override
	public void setQueryConfig(QueryConfig queryConfig) {

	}
}
