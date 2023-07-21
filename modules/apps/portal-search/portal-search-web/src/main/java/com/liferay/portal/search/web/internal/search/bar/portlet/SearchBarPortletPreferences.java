/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.search.bar.portlet;

import com.liferay.portal.search.web.internal.display.context.SearchScopePreference;

import java.util.Optional;

/**
 * @author André de Oliveira
 */
public interface SearchBarPortletPreferences {

	public static final String PREFERENCE_KEY_DESTINATION = "destination";

	public static final String PREFERENCE_KEY_FEDERATED_SEARCH_KEY =
		"federatedSearchKey";

	public static final String PREFERENCE_KEY_INVISIBLE = "invisible";

	public static final String PREFERENCE_KEY_KEYWORDS_PARAMETER_NAME =
		"keywordsParameterName";

	public static final String PREFERENCE_KEY_SCOPE_PARAMETER_NAME =
		"scopeParameterName";

	public static final String PREFERENCE_KEY_SEARCH_SCOPE = "searchScope";

	public static final String PREFERENCE_KEY_SHOW_STAGED_RESULTS =
		"showStagedResults";

	public static final String PREFERENCE_KEY_USE_ADVANCED_SEARCH_SYNTAX =
		"useAdvancedSearchSyntax";

	public Optional<String> getDestination();

	public String getDestinationString();

	public Optional<String> getFederatedSearchKeyOptional();

	public String getFederatedSearchKeyString();

	public String getKeywordsParameterName();

	public String getScopeParameterName();

	public SearchScopePreference getSearchScopePreference();

	public String getSearchScopePreferenceString();

	public boolean isInvisible();

	public boolean isShowStagedResults();

	public boolean isUseAdvancedSearchSyntax();

}