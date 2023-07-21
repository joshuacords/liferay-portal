/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.facet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;

import java.util.Optional;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
@Component(
	immediate = true, property = "class.name=DEFAULT",
	service = FacetProcessor.class
)
public class DefaultFacetProcessor
	implements FacetProcessor<SearchRequestBuilder> {

	@Override
	public Optional<AggregationBuilder> processFacet(Facet facet) {
		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms(FacetUtil.getAggregationName(facet));

		termsAggregationBuilder.field(facet.getFieldName());

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		JSONObject data = facetConfiguration.getData();

		int minDocCount = data.getInt("frequencyThreshold");

		if (minDocCount > 0) {
			termsAggregationBuilder.minDocCount(minDocCount);
		}

		int size = data.getInt("maxTerms");

		if (size > 0) {
			termsAggregationBuilder.size(size);
		}

		return Optional.of(termsAggregationBuilder);
	}

}