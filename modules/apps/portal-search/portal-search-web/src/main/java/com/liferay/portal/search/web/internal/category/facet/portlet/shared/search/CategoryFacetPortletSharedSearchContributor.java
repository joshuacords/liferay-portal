/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.category.facet.portlet.shared.search;

import com.liferay.portal.search.facet.category.CategoryFacetSearchContributor;
import com.liferay.portal.search.web.internal.category.facet.constants.CategoryFacetPortletKeys;
import com.liferay.portal.search.web.internal.category.facet.portlet.CategoryFacetPortletPreferences;
import com.liferay.portal.search.web.internal.category.facet.portlet.CategoryFacetPortletPreferencesImpl;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lino Alves
 */
@Component(
	property = "javax.portlet.name=" + CategoryFacetPortletKeys.CATEGORY_FACET,
	service = PortletSharedSearchContributor.class
)
public class CategoryFacetPortletSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		CategoryFacetPortletPreferences categoryFacetPortletPreferences =
			new CategoryFacetPortletPreferencesImpl(
				portletSharedSearchSettings.getPortletPreferences());

		categoryFacetSearchContributor.contribute(
			portletSharedSearchSettings.getSearchRequestBuilder(),
			categoryFacetBuilder -> categoryFacetBuilder.aggregationName(
				portletSharedSearchSettings.getPortletId()
			).frequencyThreshold(
				categoryFacetPortletPreferences.getFrequencyThreshold()
			).maxTerms(
				categoryFacetPortletPreferences.getMaxTerms()
			).selectedGroupCategoryExternalReferenceCodes(
				portletSharedSearchSettings.getParameterValues(
					categoryFacetPortletPreferences.getParameterName())
			).groupVocabularyExternalReferenceCodes(
				categoryFacetPortletPreferences.
					getGroupVocabularyExternalReferenceCodes()
			));
	}

	@Reference
	protected CategoryFacetSearchContributor categoryFacetSearchContributor;

}