/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.opensearch2.internal.facet;

import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;

import java.util.List;
import java.util.Map;

/**
 * @author Joshua Cords
 */
public class SelectedTermsFacetCollector implements FacetCollector {

	public SelectedTermsFacetCollector(
		FacetCollector facetCollector, Map<String, Integer> frequencies) {

		_facetCollector = facetCollector;

		List<TermCollector> termCollectors = facetCollector.getTermCollectors();

		_termCollectorHolder = new TermCollectorHolder(termCollectors.size());

		for (TermCollector termCollector : termCollectors) {
			_termCollectorHolder.add(
				termCollector.getTerm(),
				frequencies.getOrDefault(
					termCollector.getTerm(), termCollector.getFrequency()));
		}
	}

	@Override
	public String getFieldName() {
		return _facetCollector.getFieldName();
	}

	@Override
	public TermCollector getTermCollector(String term) {
		return _termCollectorHolder.getTermCollector(term);
	}

	@Override
	public List<TermCollector> getTermCollectors() {
		return _termCollectorHolder.getTermCollectors();
	}

	private final FacetCollector _facetCollector;
	private final TermCollectorHolder _termCollectorHolder;

}