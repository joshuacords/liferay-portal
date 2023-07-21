/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.similar.results.web.internal.portlet;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.similar.results.web.internal.util.PortletPreferencesHelper;

import java.util.Optional;

import javax.portlet.PortletPreferences;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
public class SimilarResultsPortletPreferencesImpl
	implements SimilarResultsPortletPreferences {

	public SimilarResultsPortletPreferencesImpl(
		Optional<PortletPreferences> optional) {

		_portletPreferencesHelper = new PortletPreferencesHelper(optional);
	}

	@Override
	public String getAnalyzer() {
		return getStringNullable(PREFERENCE_KEY_ANALYZER);
	}

	@Override
	public String getDocType() {
		return getStringNullable(PREFERENCE_KEY_DOC_TYPE);
	}

	@Override
	public String getFederatedSearchKey() {
		return _portletPreferencesHelper.getString(
			PREFERENCE_KEY_FEDERATED_SEARCH_KEY, "morelikethis");
	}

	@Override
	public String getFields() {
		return getStringNullable(PREFERENCE_KEY_FIELDS);
	}

	@Override
	public String getIndexName() {
		return getStringNullable(PREFERENCE_KEY_INDEX_NAME);
	}

	@Override
	public Integer getMaxDocFrequency() {
		return getIntegerNullable(PREFERENCE_KEY_MAX_DOC_FREQUENCY);
	}

	@Override
	public Integer getMaxItemDisplay() {
		return _portletPreferencesHelper.getInteger(
			PREFERENCE_KEY_MAX_ITEM_DISPLAY, 10);
	}

	@Override
	public Integer getMaxQueryTerms() {
		return getIntegerNullable(PREFERENCE_KEY_MAX_QUERY_TERMS);
	}

	@Override
	public Integer getMaxWordLength() {
		return getIntegerNullable(PREFERENCE_KEY_MAX_WORD_LENGTH);
	}

	@Override
	public Integer getMinDocFrequency() {
		return getIntegerNullable(PREFERENCE_KEY_MIN_DOC_FREQUENCY);
	}

	@Override
	public String getMinShouldMatch() {
		return getStringNullable(PREFERENCE_KEY_MIN_SHOULD_MATCH);
	}

	@Override
	public Integer getMinTermFrequency() {
		return getIntegerNullable(PREFERENCE_KEY_MIN_TERM_FREQUENCY);
	}

	@Override
	public Integer getMinWordLength() {
		return getIntegerNullable(PREFERENCE_KEY_MIN_WORD_LENGTH);
	}

	@Override
	public String getStopWords() {
		return getStringNullable(PREFERENCE_KEY_STOP_WORDS);
	}

	@Override
	public Float getTermBoost() {
		Optional<String> optional = _portletPreferencesHelper.getString(
			PREFERENCE_KEY_TERM_BOOST);

		return optional.map(
			GetterUtil::getFloat
		).orElse(
			null
		);
	}

	protected Integer getIntegerNullable(String key) {
		Optional<Integer> optional = _portletPreferencesHelper.getInteger(key);

		return optional.orElse(null);
	}

	protected String getStringNullable(String key) {
		return _portletPreferencesHelper.getString(key, null);
	}

	private final PortletPreferencesHelper _portletPreferencesHelper;

}