package com.liferay.portal.search.filter;

import com.liferay.portal.search.query.Query;

public interface QueryFilter extends Filter {
	public Query getQuery();

}
