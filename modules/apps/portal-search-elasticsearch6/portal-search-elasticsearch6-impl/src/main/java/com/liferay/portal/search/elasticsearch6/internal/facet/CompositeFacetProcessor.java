/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.facet;

import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	service = {CompositeFacetProcessor.class, FacetProcessor.class}
)
public class CompositeFacetProcessor
	implements FacetProcessor<SearchRequestBuilder> {

	@Override
	public Optional<AggregationBuilder> processFacet(Facet facet) {
		Class<?> clazz = facet.getClass();

		FacetProcessor<SearchRequestBuilder> facetProcessor =
			_facetProcessors.get(clazz.getName());

		if (facetProcessor == null) {
			facetProcessor = defaultFacetProcessor;
		}

		return facetProcessor.processFacet(facet);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(&(class.name=*)(!(class.name=DEFAULT)))"
	)
	protected void setFacetProcessor(
		FacetProcessor<SearchRequestBuilder> facetProcessor,
		Map<String, Object> properties) {

		String className = MapUtil.getString(properties, "class.name");

		_facetProcessors.put(className, facetProcessor);
	}

	protected void unsetFacetProcessor(
		FacetProcessor<SearchRequestBuilder> facetProcessor,
		Map<String, Object> properties) {

		String className = MapUtil.getString(properties, "class.name");

		_facetProcessors.remove(className);
	}

	@Reference(
		cardinality = ReferenceCardinality.MANDATORY,
		target = "(class.name=DEFAULT)"
	)
	protected FacetProcessor<SearchRequestBuilder> defaultFacetProcessor;

	private final Map<String, FacetProcessor<SearchRequestBuilder>>
		_facetProcessors = new HashMap<>();

}