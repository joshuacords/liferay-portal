/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.low.level.search.options.portlet.shared.search;

import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.web.internal.low.level.search.options.constants.LowLevelSearchOptionsPortletKeys;
import com.liferay.portal.search.web.internal.low.level.search.options.portlet.preferences.LowLevelSearchOptionsPortletPreferences;
import com.liferay.portal.search.web.internal.low.level.search.options.portlet.preferences.LowLevelSearchOptionsPortletPreferencesImpl;
import com.liferay.portal.search.web.internal.util.SearchStringUtil;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import org.osgi.service.component.annotations.Component;

/**
 * @author Wade Cao
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + LowLevelSearchOptionsPortletKeys.LOW_LEVEL_SEARCH_OPTIONS,
	service = PortletSharedSearchContributor.class
)
public class LowLevelSearchOptionsPortletSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		LowLevelSearchOptionsPortletPreferences
			lowLevelSearchOptionsPortletPreferences =
				new LowLevelSearchOptionsPortletPreferencesImpl(
					portletSharedSearchSettings.
						getPortletPreferencesOptional());

		SearchRequestBuilder searchRequestBuilder =
			portletSharedSearchSettings.getFederatedSearchRequestBuilder(
				lowLevelSearchOptionsPortletPreferences.
					getFederatedSearchKeyOptional());

		searchRequestBuilder.excludeContributors(
			SearchStringUtil.splitAndUnquote(
				lowLevelSearchOptionsPortletPreferences.
					getContributorsToExcludeOptional())
		).fields(
			SearchStringUtil.splitAndUnquote(
				lowLevelSearchOptionsPortletPreferences.
					getFieldsToReturnOptional())
		).includeContributors(
			SearchStringUtil.splitAndUnquote(
				lowLevelSearchOptionsPortletPreferences.
					getContributorsToIncludeOptional())
		).indexes(
			SearchStringUtil.splitAndUnquote(
				lowLevelSearchOptionsPortletPreferences.getIndexesOptional())
		);
	}

}