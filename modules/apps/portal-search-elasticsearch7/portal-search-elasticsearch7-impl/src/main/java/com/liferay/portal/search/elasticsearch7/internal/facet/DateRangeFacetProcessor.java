/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.facet;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Optional;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.DateRangeAggregationBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = "class.name=com.liferay.portal.kernel.search.facet.DateRangeFacet",
	service = FacetProcessor.class
)
public class DateRangeFacetProcessor
	implements FacetProcessor<SearchRequestBuilder> {

	@Override
	public Optional<AggregationBuilder> processFacet(Facet facet) {
		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		JSONObject jsonObject = facetConfiguration.getData();

		JSONArray jsonArray = jsonObject.getJSONArray("ranges");

		if (jsonArray == null) {
			return Optional.empty();
		}

		DateRangeAggregationBuilder dateRangeAggregationBuilder =
			AggregationBuilders.dateRange(FacetUtil.getAggregationName(facet));

		dateRangeAggregationBuilder.field(facetConfiguration.getFieldName());

		String format = jsonObject.getString("format");

		dateRangeAggregationBuilder.format(format);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject rangeJSONObject = jsonArray.getJSONObject(i);

			String range = rangeJSONObject.getString("range");

			range = StringUtil.replace(
				range, CharPool.OPEN_BRACKET, StringPool.BLANK);
			range = StringUtil.replace(
				range, CharPool.CLOSE_BRACKET, StringPool.BLANK);

			String[] rangeParts = range.split(StringPool.SPACE);

			dateRangeAggregationBuilder.addRange(rangeParts[0], rangeParts[2]);
		}

		return Optional.of(dateRangeAggregationBuilder);
	}

}