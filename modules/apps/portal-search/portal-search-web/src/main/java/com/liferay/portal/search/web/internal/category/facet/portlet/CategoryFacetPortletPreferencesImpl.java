/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.category.facet.portlet;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.web.internal.portlet.preferences.BasePortletPreferences;

import javax.portlet.PortletPreferences;

/**
 * @author Lino Alves
 */
public class CategoryFacetPortletPreferencesImpl
	extends BasePortletPreferences implements CategoryFacetPortletPreferences {

	public CategoryFacetPortletPreferencesImpl(
		PortletPreferences portletPreferences) {

		super(portletPreferences);
	}

	@Override
	public String getDisplayStyle() {
		return getString(
			CategoryFacetPortletPreferences.PREFERENCE_KEY_DISPLAY_STYLE,
			"cloud");
	}

	@Override
	public int getFrequencyThreshold() {
		return getInteger(
			CategoryFacetPortletPreferences.PREFERENCE_KEY_FREQUENCY_THRESHOLD,
			1);
	}

	@Override
	public String[] getGroupVocabularyExternalReferenceCodes() {
		String groupVocabularyExternalReferenceCodes = getString(
			CategoryFacetPortletPreferences.
				PREFERENCE_VOCABULARY_EXTERNAL_REFERENCE_CODES,
			null);

		return StringUtil.split(groupVocabularyExternalReferenceCodes);
	}

	@Override
	public int getMaxTerms() {
		return getInteger(
			CategoryFacetPortletPreferences.PREFERENCE_KEY_MAX_TERMS, 10);
	}

	@Override
	public String getOrder() {
		return getString(
			CategoryFacetPortletPreferences.PREFERENCE_KEY_ORDER, "count:desc");
	}

	@Override
	public String getParameterName() {
		return getString(
			CategoryFacetPortletPreferences.PREFERENCE_KEY_PARAMETER_NAME,
			"category");
	}

	@Override
	public boolean isFrequenciesVisible() {
		return getBoolean(
			CategoryFacetPortletPreferences.PREFERENCE_KEY_FREQUENCIES_VISIBLE,
			true);
	}

}