/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.type.facet.portlet.shared.search;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.search.facet.type.TypeFacetSearchContributor;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.web.internal.type.facet.constants.TypeFacetPortletKeys;
import com.liferay.portal.search.web.internal.type.facet.portlet.TypeFacetPortletPreferences;
import com.liferay.portal.search.web.internal.type.facet.portlet.TypeFacetPortletPreferencesImpl;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lino Alves
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + TypeFacetPortletKeys.TYPE_FACET,
	service = PortletSharedSearchContributor.class
)
public class TypeFacetPortletSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		TypeFacetPortletPreferences typeFacetPortletPreferences =
			new TypeFacetPortletPreferencesImpl(
				portletSharedSearchSettings.getPortletPreferencesOptional());

		SearchRequestBuilder searchRequestBuilder =
			portletSharedSearchSettings.getSearchRequestBuilder();

		typeFacetSearchContributor.contribute(
			searchRequestBuilder,
			siteFacetBuilder -> siteFacetBuilder.aggregationName(
				portletSharedSearchSettings.getPortletId()
			).frequencyThreshold(
				typeFacetPortletPreferences.getFrequencyThreshold()
			).selectedEntryClassNames(
				portletSharedSearchSettings.getParameterValues(
					typeFacetPortletPreferences.getParameterName())
			));

		ThemeDisplay themeDisplay =
			portletSharedSearchSettings.getThemeDisplay();

		searchRequestBuilder.entryClassNames(
			typeFacetPortletPreferences.getCurrentAssetTypesArray(
				themeDisplay.getCompanyId()));
	}

	@Reference
	protected TypeFacetSearchContributor typeFacetSearchContributor;

}