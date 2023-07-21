/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.suggest.QuerySuggester;
import com.liferay.portal.kernel.search.suggest.Suggester;
import com.liferay.portal.kernel.search.suggest.SuggesterResults;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public abstract class BaseIndexSearcher
	implements IndexSearcher, QuerySuggester {

	public void setQuerySuggester(QuerySuggester querySuggester) {
		_querySuggester = querySuggester;
	}

	@Override
	public String spellCheckKeywords(SearchContext searchContext)
		throws SearchException {

		if (_querySuggester == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No query suggester configured");
			}

			return StringPool.BLANK;
		}

		return _querySuggester.spellCheckKeywords(searchContext);
	}

	@Override
	public Map<String, List<String>> spellCheckKeywords(
			SearchContext searchContext, int max)
		throws SearchException {

		if (_querySuggester == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No query suggester configured");
			}

			return Collections.emptyMap();
		}

		return _querySuggester.spellCheckKeywords(searchContext, max);
	}

	@Override
	public SuggesterResults suggest(
			SearchContext searchContext, Suggester suggester)
		throws SearchException {

		if (_querySuggester == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No query suggester configured");
			}

			return new SuggesterResults();
		}

		return _querySuggester.suggest(searchContext, suggester);
	}

	@Override
	public String[] suggestKeywordQueries(SearchContext searchContext, int max)
		throws SearchException {

		if (_querySuggester == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No query suggester configured");
			}

			return StringPool.EMPTY_ARRAY;
		}

		return _querySuggester.suggestKeywordQueries(searchContext, max);
	}

	protected void populateUID(Document document, QueryConfig queryConfig) {
		Field uidField = document.getField(Field.UID);

		if (uidField != null) {
			return;
		}

		if (Validator.isNull(queryConfig.getAlternateUidFieldName())) {
			return;
		}

		String uidValue = document.get(queryConfig.getAlternateUidFieldName());

		if (Validator.isNotNull(uidValue)) {
			uidField = new Field(Field.UID, uidValue);

			document.add(uidField);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseIndexSearcher.class);

	private QuerySuggester _querySuggester;

}