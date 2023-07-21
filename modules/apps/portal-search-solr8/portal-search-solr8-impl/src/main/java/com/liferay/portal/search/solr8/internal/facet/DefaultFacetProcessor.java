/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr8.internal.facet;

import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;

import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = "class.name=DEFAULT",
	service = FacetProcessor.class
)
public class DefaultFacetProcessor implements FacetProcessor<SolrQuery> {

	@Override
	public Map<String, JSONObject> processFacet(Facet facet) {
		String name = FacetUtil.getAggregationName(facet);

		return LinkedHashMapBuilder.<String, JSONObject>put(
			name, getFacetParameters(facet)
		).build();
	}

	protected void applyFrequencyThreshold(
		JSONObject jsonObject, JSONObject dataJSONObject) {

		int minCount = dataJSONObject.getInt("frequencyThreshold");

		if (minCount > 0) {
			jsonObject.put("mincount", minCount);
		}
	}

	protected void applyMaxTerms(
		JSONObject jsonObject, JSONObject dataJSONObject) {

		int limit = dataJSONObject.getInt("maxTerms");

		if (limit > 0) {
			jsonObject.put("limit", limit);
		}
	}

	protected void applySort(
		JSONObject jsonObject, FacetConfiguration facetConfiguration) {

		String sortParam = "count";
		String sortValue = "desc";

		String order = facetConfiguration.getOrder();

		if (order.equals("OrderValueAsc")) {
			sortParam = "index";
			sortValue = "asc";
		}

		JSONObject sortJSONObject = _jsonFactory.createJSONObject();

		sortJSONObject.put(sortParam, sortValue);

		jsonObject.put("sort", sortJSONObject);
	}

	protected JSONObject getFacetParameters(Facet facet) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put(
			"field", facet.getFieldName()
		).put(
			"type", "terms"
		);

		FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

		JSONObject dataJSONObject = facetConfiguration.getData();

		applyFrequencyThreshold(jsonObject, dataJSONObject);
		applyMaxTerms(jsonObject, dataJSONObject);

		applySort(jsonObject, facetConfiguration);

		return jsonObject;
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	private JSONFactory _jsonFactory;

}