/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr8.internal.filter;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.FilterVisitor;

import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = BooleanFilterTranslator.class)
public class BooleanFilterTranslatorImpl implements BooleanFilterTranslator {

	@Override
	public Query translate(
		BooleanFilter booleanFilter, FilterVisitor<Query> filterVisitor) {

		BooleanQuery.Builder builder = new BooleanQuery.Builder();

		for (BooleanClause<Filter> booleanClause :
				booleanFilter.getMustBooleanClauses()) {

			builder.add(
				translate(booleanClause, filterVisitor),
				org.apache.lucene.search.BooleanClause.Occur.MUST);
		}

		for (BooleanClause<Filter> booleanClause :
				booleanFilter.getMustNotBooleanClauses()) {

			builder.add(
				translate(booleanClause, filterVisitor),
				org.apache.lucene.search.BooleanClause.Occur.MUST_NOT);
		}

		for (BooleanClause<Filter> booleanClause :
				booleanFilter.getShouldBooleanClauses()) {

			builder.add(
				translate(booleanClause, filterVisitor),
				org.apache.lucene.search.BooleanClause.Occur.SHOULD);
		}

		return builder.build();
	}

	protected Query translate(
		BooleanClause<Filter> booleanClause,
		FilterVisitor<Query> filterVisitor) {

		Filter filter = booleanClause.getClause();

		return filter.accept(filterVisitor);
	}

}