/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr7.internal.query;

import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.TermQuery;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.Query;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(immediate = true, service = TermQueryTranslator.class)
public class TermQueryTranslatorImpl implements TermQueryTranslator {

	@Override
	public Query translate(TermQuery termQuery) {
		QueryTerm queryTerm = termQuery.getQueryTerm();

		Query query = new org.apache.lucene.search.TermQuery(
			new Term(queryTerm.getField(), queryTerm.getValue()));

		if (!termQuery.isDefaultBoost()) {
			return new BoostQuery(query, termQuery.getBoost());
		}

		return query;
	}

}