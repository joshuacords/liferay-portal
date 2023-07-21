/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr8.internal.query;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.DisMaxQuery;
import com.liferay.portal.kernel.search.generic.FuzzyQuery;
import com.liferay.portal.kernel.search.generic.MatchAllQuery;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.search.generic.MoreLikeThisQuery;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.NestedQuery;
import com.liferay.portal.kernel.search.generic.StringQuery;
import com.liferay.portal.kernel.search.query.QueryTranslator;
import com.liferay.portal.kernel.search.query.QueryVisitor;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(
	immediate = true, property = "search.engine.impl=Solr",
	service = {LuceneQueryConverter.class, QueryTranslator.class}
)
public class SolrQueryTranslator
	implements LuceneQueryConverter, QueryTranslator<String>,
			   QueryVisitor<org.apache.lucene.search.Query> {

	@Override
	public org.apache.lucene.search.Query convert(Query query) {
		return query.accept(this);
	}

	@Override
	public String translate(Query query, SearchContext searchContext) {
		org.apache.lucene.search.Query luceneQuery = query.accept(this);

		if (luceneQuery != null) {
			return luceneQuery.toString();
		}

		return null;
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		BooleanQuery booleanQuery) {

		return _booleanQueryTranslator.translate(booleanQuery, this);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(DisMaxQuery disMaxQuery) {
		return _disMaxQueryTranslator.translate(disMaxQuery, this);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(FuzzyQuery fuzzyQuery) {
		return _fuzzyQueryTranslator.translate(fuzzyQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		MatchAllQuery matchAllQuery) {

		return _matchAllQueryTranslator.translate(matchAllQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(MatchQuery matchQuery) {
		return _matchQueryTranslator.translate(matchQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		MoreLikeThisQuery moreLikeThisQuery) {

		return _moreLikeThisQueryTranslator.translate(moreLikeThisQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		MultiMatchQuery multiMatchQuery) {

		return _multiMatchQueryTranslator.translate(multiMatchQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(NestedQuery nestedQuery) {
		return _nestedQueryTranslator.translate(nestedQuery, this);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(StringQuery stringQuery) {
		return _stringQueryTranslator.translate(stringQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(TermQuery termQuery) {
		return _termQueryTranslator.translate(termQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		TermRangeQuery termRangeQuery) {

		return _termRangeQueryTranslator.translate(termRangeQuery);
	}

	@Override
	public org.apache.lucene.search.Query visitQuery(
		WildcardQuery wildcardQuery) {

		return _wildcardQueryTranslator.translate(wildcardQuery);
	}

	@Reference(unbind = "-")
	protected void setBooleanQueryTranslator(
		BooleanQueryTranslator booleanQueryTranslator) {

		_booleanQueryTranslator = booleanQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setDisMaxQueryTranslator(
		DisMaxQueryTranslator disMaxQueryTranslator) {

		_disMaxQueryTranslator = disMaxQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setFuzzyQueryTranslator(
		FuzzyQueryTranslator fuzzyQueryTranslator) {

		_fuzzyQueryTranslator = fuzzyQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setMatchAllQueryTranslator(
		MatchAllQueryTranslator matchAllQueryTranslator) {

		_matchAllQueryTranslator = matchAllQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setMatchQueryTranslator(
		MatchQueryTranslator matchQueryTranslator) {

		_matchQueryTranslator = matchQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setMoreLikeThisQueryTranslator(
		MoreLikeThisQueryTranslator moreLikeThisQueryTranslator) {

		_moreLikeThisQueryTranslator = moreLikeThisQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setMultiMatchQueryTranslator(
		MultiMatchQueryTranslator multiMatchQueryTranslator) {

		_multiMatchQueryTranslator = multiMatchQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setNestedQueryTranslator(
		NestedQueryTranslator nestedQueryTranslator) {

		_nestedQueryTranslator = nestedQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setStringQueryTranslator(
		StringQueryTranslator stringQueryTranslator) {

		_stringQueryTranslator = stringQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setTermQueryTranslator(
		TermQueryTranslator termQueryTranslator) {

		_termQueryTranslator = termQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setTermRangeQueryTranslator(
		TermRangeQueryTranslator termRangeQueryTranslator) {

		_termRangeQueryTranslator = termRangeQueryTranslator;
	}

	@Reference(unbind = "-")
	protected void setWildcardQueryTranslator(
		WildcardQueryTranslator wildcardQueryTranslator) {

		_wildcardQueryTranslator = wildcardQueryTranslator;
	}

	private BooleanQueryTranslator _booleanQueryTranslator;
	private DisMaxQueryTranslator _disMaxQueryTranslator;
	private FuzzyQueryTranslator _fuzzyQueryTranslator;
	private MatchAllQueryTranslator _matchAllQueryTranslator;
	private MatchQueryTranslator _matchQueryTranslator;
	private MoreLikeThisQueryTranslator _moreLikeThisQueryTranslator;
	private MultiMatchQueryTranslator _multiMatchQueryTranslator;
	private NestedQueryTranslator _nestedQueryTranslator;
	private StringQueryTranslator _stringQueryTranslator;
	private TermQueryTranslator _termQueryTranslator;
	private TermRangeQueryTranslator _termRangeQueryTranslator;
	private WildcardQueryTranslator _wildcardQueryTranslator;

}