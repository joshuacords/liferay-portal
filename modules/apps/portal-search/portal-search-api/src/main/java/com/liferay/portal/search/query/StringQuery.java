/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.query;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides support for parsing raw, human readable query syntax. No
 * transformation is made on user input.
 *
 * <p>
 * The actual query syntax and any further processing are dependent on your
 * search engine's implementation details. Consult your search provider's
 * documentation for more information.
 * </p>
 *
 * @author Bruno Farache
 */
@ProviderType
public interface StringQuery extends Query {

	public Boolean getAllowLeadingWildcard();

	public String getAnalyzer();

	public Boolean getAnalyzeWildcard();

	public Boolean getAutoGenerateSynonymsPhraseQuery();

	public String getDefaultField();

	public Operator getDefaultOperator();

	public Boolean getEnablePositionIncrements();

	public Float getFuzziness();

	public Integer getFuzzyMaxExpansions();

	public Integer getFuzzyPrefixLength();

	public Boolean getFuzzyTranspositions();

	public Boolean getLenient();

	public Integer getMaxDeterminedStates();

	public Integer getPhraseSlop();

	public String getQuery();

	public String getQuoteAnalyzer();

	public String getQuoteFieldSuffix();

	public String getRewrite();

	public String getTimeZone();

	public void setAllowLeadingWildcard(Boolean allowLeadingWildcard);

	public void setAnalyzer(String analyzer);

	public void setAnalyzeWildcard(Boolean analyzeWildcard);

	public void setAutoGenerateSynonymsPhraseQuery(
		Boolean autoGenerateSynonymsPhraseQuery);

	public void setDefaultField(String defaultField);

	public void setDefaultOperator(Operator defaultOperator);

	public void setEnablePositionIncrements(Boolean enablePositionIncrements);

	public void setFuzziness(Float fuzziness);

	public void setFuzzyMaxExpansions(Integer fuzzyMaxExpansions);

	public void setFuzzyPrefixLength(Integer fuzzyPrefixLength);

	public void setFuzzyTranspositions(Boolean fuzzyTranspositions);

	public void setLenient(Boolean lenient);

	public void setMaxDeterminedStates(Integer maxDeterminedStates);

	public void setPhraseSlop(Integer phraseSlop);

	public void setQuoteAnalyzer(String quoteAnalyzer);

	public void setQuoteFieldSuffix(String quoteFieldSuffix);

	public void setRewrite(String rewrite);

	public void setTimeZone(String timeZone);

}