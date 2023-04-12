package com.liferay.portal.search.filter;

import com.liferay.portal.search.query.Query;
import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface QueryFilterBuilder {

	public QueryFilter build();

	public void setQuery(Query query);
}
