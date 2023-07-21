/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.suggestions.portlet;

import com.liferay.portal.search.web.internal.util.PortletPreferencesHelper;

import java.util.Optional;

import javax.portlet.PortletPreferences;

/**
 * @author Adam Brandizzi
 */
public class SuggestionsPortletPreferencesImpl
	implements SuggestionsPortletPreferences {

	public SuggestionsPortletPreferencesImpl(
		Optional<PortletPreferences> portletPreferencesOptional) {

		_portletPreferencesHelper = new PortletPreferencesHelper(
			portletPreferencesOptional);
	}

	@Override
	public int getQueryIndexingThreshold() {
		return _portletPreferencesHelper.getInteger(
			PREFERENCE_KEY_QUERY_INDEXING_THRESHOLD, 50);
	}

	@Override
	public int getRelatedQueriesSuggestionsDisplayThreshold() {
		return _portletPreferencesHelper.getInteger(
			PREFERENCE_KEY_RELATED_QUERIES_SUGGESTIONS_DISPLAY_THRESHOLD, 50);
	}

	@Override
	public int getRelatedQueriesSuggestionsMax() {
		return _portletPreferencesHelper.getInteger(
			PREFERENCE_KEY_RELATED_QUERIES_SUGGESTIONS_MAX, 10);
	}

	@Override
	public int getSpellCheckSuggestionDisplayThreshold() {
		return _portletPreferencesHelper.getInteger(
			PREFERENCE_KEY_SPELL_CHECK_SUGGESTION_DISPLAY_THRESHOLD, 50);
	}

	@Override
	public boolean isQueryIndexingEnabled() {
		return _portletPreferencesHelper.getBoolean(
			PREFERENCE_KEY_QUERY_INDEXING_ENABLED, false);
	}

	@Override
	public boolean isRelatedQueriesSuggestionsEnabled() {
		return _portletPreferencesHelper.getBoolean(
			PREFERENCE_KEY_RELATED_QUERIES_SUGGESTIONS_ENABLED, false);
	}

	@Override
	public boolean isSpellCheckSuggestionEnabled() {
		return _portletPreferencesHelper.getBoolean(
			PREFERENCE_KEY_SPELL_CHECK_SUGGESTION_ENABLED, false);
	}

	private final PortletPreferencesHelper _portletPreferencesHelper;

}