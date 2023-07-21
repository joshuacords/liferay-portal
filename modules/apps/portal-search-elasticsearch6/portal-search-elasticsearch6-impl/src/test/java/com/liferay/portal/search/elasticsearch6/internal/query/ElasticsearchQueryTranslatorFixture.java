/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.query;

/**
 * @author Michael C. Han
 */
public class ElasticsearchQueryTranslatorFixture {

	public ElasticsearchQueryTranslatorFixture() {
		_elasticsearchQueryTranslator = new ElasticsearchQueryTranslator() {
			{
				setBooleanQueryTranslator(new BooleanQueryTranslatorImpl());
				setBoostingQueryTranslator(new BoostingQueryTranslatorImpl());
				setCommonTermsQueryTranslator(
					new CommonTermsQueryTranslatorImpl());
				setConstantScoreQueryTranslator(
					new ConstantScoreQueryTranslatorImpl());
				setDateRangeTermQueryTranslator(
					new DateRangeTermQueryTranslatorImpl());
				setDisMaxQueryTranslator(new DisMaxQueryTranslatorImpl());
				setExistsQueryTranslator(new ExistsQueryTranslatorImpl());
				setFunctionScoreQueryTranslator(
					new FunctionScoreQueryTranslatorImpl());
				setFuzzyQueryTranslator(new FuzzyQueryTranslatorImpl());
				setGeoBoundingBoxQueryTranslator(
					new GeoBoundingBoxQueryTranslatorImpl());
				setGeoDistanceQueryTranslator(
					new GeoDistanceQueryTranslatorImpl());
				setGeoDistanceRangeQueryTranslator(
					new GeoDistanceRangeQueryTranslatorImpl());
				setGeoPolygonQueryTranslator(
					new GeoPolygonQueryTranslatorImpl());
				setGeoShapeQueryTranslator(new GeoShapeQueryTranslatorImpl());
				setIdsQueryTranslator(new IdsQueryTranslatorImpl());
				setMatchAllQueryTranslator(new MatchAllQueryTranslatorImpl());
				setMatchPhrasePrefixQueryTranslator(
					new MatchPhrasePrefixQueryTranslatorImpl());
				setMatchPhraseQueryTranslator(
					new MatchPhraseQueryTranslatorImpl());
				setMatchQueryTranslator(new MatchQueryTranslatorImpl());
				setMoreLikeThisQueryTranslator(
					new MoreLikeThisQueryTranslatorImpl());
				setMultiMatchQueryTranslator(
					new MultiMatchQueryTranslatorImpl());
				setNestedQueryTranslator(new NestedQueryTranslatorImpl());
				setPercolateQueryTranslator(new PercolateQueryTranslatorImpl());
				setPrefixQueryTranslator(new PrefixQueryTranslatorImpl());
				setRangeTermQueryTranslator(new RangeTermQueryTranslatorImpl());
				setRegexQueryTranslator(new RegexQueryTranslatorImpl());
				setScriptQueryTranslator(new ScriptQueryTranslatorImpl());
				setSimpleQueryStringQueryTranslator(
					new SimpleStringQueryTranslatorImpl());
				setStringQueryTranslator(new StringQueryTranslatorImpl());
				setTermQueryTranslator(new TermQueryTranslatorImpl());
				setTermsQueryTranslator(new TermsQueryTranslatorImpl());
				setTermsSetQueryTranslator(new TermsSetQueryTranslatorImpl());
				setWildcardQueryTranslator(new WildcardQueryTranslatorImpl());
				setWrapperQueryTranslator(new WrapperQueryTranslatorImpl());
			}
		};
	}

	public ElasticsearchQueryTranslator getElasticsearchQueryTranslator() {
		return _elasticsearchQueryTranslator;
	}

	private final ElasticsearchQueryTranslator _elasticsearchQueryTranslator;

}