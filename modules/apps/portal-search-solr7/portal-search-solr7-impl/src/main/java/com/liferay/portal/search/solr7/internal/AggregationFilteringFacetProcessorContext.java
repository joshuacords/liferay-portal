/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr7.internal;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.facet.Facet;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author André de Oliveira
 */
public class AggregationFilteringFacetProcessorContext
	implements FacetProcessorContext {

	public static FacetProcessorContext newInstance(
		Map<String, Facet> facets, boolean basicFacetSelection) {

		if (basicFacetSelection) {
			return new AggregationFilteringFacetProcessorContext(
				Optional.of(getAllNamesString(facets)));
		}

		return new AggregationFilteringFacetProcessorContext(Optional.empty());
	}

	@Override
	public Optional<String> getExcludeTagsStringOptional() {
		return _excludeTagsStringOptional;
	}

	protected static String getAllNamesString(Map<String, Facet> facets) {
		Collection<String> names = facets.keySet();

		Stream<String> stream = names.stream();

		return stream.collect(Collectors.joining(StringPool.COMMA));
	}

	private AggregationFilteringFacetProcessorContext(
		Optional<String> excludeTagsStringOptional) {

		_excludeTagsStringOptional = excludeTagsStringOptional;
	}

	private final Optional<String> _excludeTagsStringOptional;

}