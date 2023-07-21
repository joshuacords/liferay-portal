/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr8.internal.filter;

import com.liferay.portal.kernel.search.filter.TermsFilter;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermInSetQuery;
import org.apache.lucene.util.BytesRef;
import org.apache.solr.client.solrj.util.ClientUtils;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = TermsFilterTranslator.class)
public class TermsFilterTranslatorImpl implements TermsFilterTranslator {

	@Override
	public Query translate(TermsFilter termsFilter) {
		String field = termsFilter.getField();

		List<BytesRef> bytesRefs = new ArrayList<>();

		for (String value : termsFilter.getValues()) {
			Term term = new Term(field, ClientUtils.escapeQueryChars(value));

			bytesRefs.add(term.bytes());
		}

		Query query = new TermInSetQuery(field, bytesRefs);

		if (bytesRefs.size() == 1) {
			return query;
		}

		BooleanQuery.Builder builder = new BooleanQuery.Builder();

		builder.add(query, BooleanClause.Occur.SHOULD);

		return builder.build();
	}

}