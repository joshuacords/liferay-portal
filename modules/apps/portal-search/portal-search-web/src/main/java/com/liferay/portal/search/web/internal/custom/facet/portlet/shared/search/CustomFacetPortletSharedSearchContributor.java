/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.custom.facet.portlet.shared.search;

import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.facet.custom.CustomFacetSearchContributor;
import com.liferay.portal.search.facet.nested.NestedFacetSearchContributor;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.web.internal.custom.facet.constants.CustomFacetPortletKeys;
import com.liferay.portal.search.web.internal.custom.facet.portlet.CustomFacetPortletPreferences;
import com.liferay.portal.search.web.internal.custom.facet.portlet.CustomFacetPortletPreferencesImpl;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wade Cao
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CustomFacetPortletKeys.CUSTOM_FACET,
	service = PortletSharedSearchContributor.class
)
public class CustomFacetPortletSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		CustomFacetPortletPreferences customFacetPortletPreferences =
			new CustomFacetPortletPreferencesImpl(
				portletSharedSearchSettings.getPortletPreferencesOptional());

		Optional<String> fieldToAggregateOptional =
			customFacetPortletPreferences.getAggregationFieldOptional();

		if (!fieldToAggregateOptional.isPresent()) {
			return;
		}

		SearchRequestBuilder searchRequestBuilder =
			portletSharedSearchSettings.getFederatedSearchRequestBuilder(
				customFacetPortletPreferences.getFederatedSearchKeyOptional());

		String fieldToAggregate = fieldToAggregateOptional.get();

		if (!ddmIndexer.isLegacyDDMIndexFieldsEnabled() &&
			fieldToAggregate.startsWith(DDMIndexer.DDM_FIELD_PREFIX)) {

			contributeWithNestedFacet(
				fieldToAggregate, searchRequestBuilder,
				portletSharedSearchSettings, customFacetPortletPreferences);
		}
		else {
			contributeWithCustomFacet(
				fieldToAggregate, searchRequestBuilder,
				portletSharedSearchSettings, customFacetPortletPreferences);
		}
	}

	protected void contributeWithCustomFacet(
		String fieldToAggregate, SearchRequestBuilder searchRequestBuilder,
		PortletSharedSearchSettings portletSharedSearchSettings,
		CustomFacetPortletPreferences customFacetPortletPreferences) {

		customFacetSearchContributor.contribute(
			searchRequestBuilder,
			customFacetBuilder -> customFacetBuilder.aggregationName(
				portletSharedSearchSettings.getPortletId()
			).fieldToAggregate(
				fieldToAggregate
			).frequencyThreshold(
				customFacetPortletPreferences.getFrequencyThreshold()
			).maxTerms(
				customFacetPortletPreferences.getMaxTerms()
			).selectedValues(
				portletSharedSearchSettings.getParameterValues(
					getParameterName(customFacetPortletPreferences))
			));
	}

	protected void contributeWithNestedFacet(
		String fieldToAggregate, SearchRequestBuilder searchRequestBuilder,
		PortletSharedSearchSettings portletSharedSearchSettings,
		CustomFacetPortletPreferences customFacetPortletPreferences) {

		String[] ddmStructureParts = StringUtil.split(
			fieldToAggregate, DDMIndexer.DDM_FIELD_SEPARATOR);

		String nestedFieldToAggregate = ddmIndexer.getValueFieldName(
			ddmStructureParts[1], _getSuffixLocale(ddmStructureParts[3]));

		nestedFacetSearchContributor.contribute(
			searchRequestBuilder,
			nestedFacetBuilder -> nestedFacetBuilder.aggregationName(
				portletSharedSearchSettings.getPortletId()
			).fieldToAggregate(
				StringBundler.concat(
					DDMIndexer.DDM_FIELD_ARRAY, StringPool.PERIOD,
					nestedFieldToAggregate)
			).filterField(
				StringBundler.concat(
					DDMIndexer.DDM_FIELD_ARRAY, StringPool.PERIOD,
					DDMIndexer.DDM_FIELD_NAME)
			).filterValue(
				fieldToAggregate
			).frequencyThreshold(
				customFacetPortletPreferences.getFrequencyThreshold()
			).maxTerms(
				customFacetPortletPreferences.getMaxTerms()
			).path(
				DDMIndexer.DDM_FIELD_ARRAY
			).selectedValues(
				portletSharedSearchSettings.getParameterValues(
					getParameterName(customFacetPortletPreferences))
			));
	}

	protected String getParameterName(
		CustomFacetPortletPreferences customFacetPortletPreferences) {

		Optional<String> optional = Stream.of(
			customFacetPortletPreferences.getParameterNameOptional(),
			customFacetPortletPreferences.getAggregationFieldOptional()
		).filter(
			Optional::isPresent
		).map(
			Optional::get
		).findFirst();

		return optional.orElse("customfield");
	}

	@Reference
	protected CustomFacetSearchContributor customFacetSearchContributor;

	@Reference
	protected DDMIndexer ddmIndexer;

	@Reference
	protected NestedFacetSearchContributor nestedFacetSearchContributor;

	private Locale _getSuffixLocale(String string) {
		for (Locale availableLocale : LanguageUtil.getAvailableLocales()) {
			String availableLanguageId = LanguageUtil.getLanguageId(
				availableLocale);

			if (string.endsWith(availableLanguageId)) {
				return availableLocale;
			}
		}

		return null;
	}

}