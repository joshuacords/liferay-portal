/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.search.results.portlet.shared.search;

import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.web.internal.search.results.constants.SearchResultsPortletKeys;
import com.liferay.portal.search.web.internal.search.results.portlet.SearchResultsPortletPreferences;
import com.liferay.portal.search.web.internal.search.results.portlet.SearchResultsPortletPreferencesImpl;
import com.liferay.portal.search.web.internal.util.SearchOptionalUtil;
import com.liferay.portal.search.web.internal.util.SearchStringUtil;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + SearchResultsPortletKeys.SEARCH_RESULTS,
	service = PortletSharedSearchContributor.class
)
public class SearchResultsPortletSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		SearchResultsPortletPreferences searchResultsPortletPreferences =
			new SearchResultsPortletPreferencesImpl(
				portletSharedSearchSettings.getPortletPreferencesOptional());

		paginate(searchResultsPortletPreferences, portletSharedSearchSettings);

		SearchRequestBuilder searchRequestBuilder =
			portletSharedSearchSettings.getFederatedSearchRequestBuilder(
				searchResultsPortletPreferences.
					getFederatedSearchKeyOptional());

		if (searchResultsPortletPreferences.isHighlightEnabled()) {
			searchRequestBuilder.highlightEnabled(true);

			String[] fieldsToDisplay = SearchStringUtil.splitAndUnquote(
				searchResultsPortletPreferences.getFieldsToDisplayOptional());

			searchRequestBuilder.highlightFields(fieldsToDisplay);
		}

		searchRequestBuilder.paginationStartParameterName(
			searchResultsPortletPreferences.getPaginationStartParameterName());
	}

	protected void paginate(
		SearchResultsPortletPreferences searchResultsPortletPreferences,
		PortletSharedSearchSettings portletSharedSearchSettings) {

		String paginationStartParameterName =
			searchResultsPortletPreferences.getPaginationStartParameterName();

		portletSharedSearchSettings.setPaginationStartParameterName(
			paginationStartParameterName);

		Optional<String> paginationStartParameterValueOptional =
			portletSharedSearchSettings.getParameterOptional(
				paginationStartParameterName);

		SearchOptionalUtil.copy(
			() -> paginationStartParameterValueOptional.map(Integer::valueOf),
			portletSharedSearchSettings::setPaginationStart);

		Optional<String> paginationDeltaParameterValueOptional =
			portletSharedSearchSettings.getParameterOptional(
				searchResultsPortletPreferences.
					getPaginationDeltaParameterName());

		Optional<Integer> paginationDeltaOptional =
			paginationDeltaParameterValueOptional.map(Integer::valueOf);

		int paginationDelta = paginationDeltaOptional.orElse(
			searchResultsPortletPreferences.getPaginationDelta());

		portletSharedSearchSettings.setPaginationDelta(paginationDelta);
	}

	@Reference
	protected SearchRequestBuilderFactory searchRequestBuilderFactory;

}