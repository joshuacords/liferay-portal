/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.custom.filter.portlet.shared.search;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.filter.ComplexQueryPartBuilderFactory;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.web.internal.custom.filter.constants.CustomFilterPortletKeys;
import com.liferay.portal.search.web.internal.custom.filter.portlet.CustomFilterPortletPreferences;
import com.liferay.portal.search.web.internal.custom.filter.portlet.CustomFilterPortletPreferencesImpl;
import com.liferay.portal.search.web.internal.custom.filter.portlet.CustomFilterPortletUtil;
import com.liferay.portal.search.web.internal.util.SearchOptionalUtil;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import java.util.Optional;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CustomFilterPortletKeys.CUSTOM_FILTER,
	service = PortletSharedSearchContributor.class
)
public class CustomFilterPortletSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		CustomFilterPortletPreferences customFilterPortletPreferences =
			new CustomFilterPortletPreferencesImpl(
				portletSharedSearchSettings.getPortletPreferencesOptional());

		SearchRequestBuilder searchRequestBuilder =
			portletSharedSearchSettings.getFederatedSearchRequestBuilder(
				customFilterPortletPreferences.getFederatedSearchKeyOptional());

		searchRequestBuilder.addComplexQueryPart(
			_complexQueryPartBuilderFactory.builder(
			).boost(
				getBoost(customFilterPortletPreferences)
			).disabled(
				customFilterPortletPreferences.isDisabled()
			).field(
				customFilterPortletPreferences.getFilterFieldString()
			).name(
				customFilterPortletPreferences.getQueryNameString()
			).occur(
				customFilterPortletPreferences.getOccur()
			).parent(
				customFilterPortletPreferences.getParentQueryNameString()
			).type(
				customFilterPortletPreferences.getFilterQueryType()
			).value(
				getFilterValue(
					portletSharedSearchSettings, customFilterPortletPreferences)
			).build());
	}

	protected Float getBoost(
		CustomFilterPortletPreferences customFilterPortletPreferences) {

		Optional<String> optional =
			customFilterPortletPreferences.getBoostOptional();

		return optional.map(
			GetterUtil::getFloat
		).orElse(
			null
		);
	}

	protected String getFilterValue(
		PortletSharedSearchSettings portletSharedSearchSettings,
		CustomFilterPortletPreferences customFilterPortletPreferences) {

		Optional<String> optional = getFilterValueOptional(
			customFilterPortletPreferences, portletSharedSearchSettings);

		return optional.orElse(null);
	}

	protected Optional<String> getFilterValueOptional(
		CustomFilterPortletPreferences customFilterPortletPreferences,
		PortletSharedSearchSettings portletSharedSearchSettings) {

		Optional<String> filterValueOptional =
			customFilterPortletPreferences.getFilterValueOptional();

		if (customFilterPortletPreferences.isImmutable()) {
			return filterValueOptional;
		}

		Optional<String> parameterValueOptional =
			portletSharedSearchSettings.getParameterOptional(
				CustomFilterPortletUtil.getParameterName(
					customFilterPortletPreferences));

		return Optional.ofNullable(
			SearchOptionalUtil.findFirstPresent(
				Stream.of(parameterValueOptional, filterValueOptional), null));
	}

	@Reference
	private ComplexQueryPartBuilderFactory _complexQueryPartBuilderFactory;

}