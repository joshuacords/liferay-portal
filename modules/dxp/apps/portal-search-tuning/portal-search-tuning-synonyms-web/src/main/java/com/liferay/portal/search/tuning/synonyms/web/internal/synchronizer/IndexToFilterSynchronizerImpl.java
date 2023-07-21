/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.synonyms.web.internal.synchronizer;

import com.liferay.portal.search.tuning.synonyms.web.internal.filter.SynonymSetFilterWriter;
import com.liferay.portal.search.tuning.synonyms.web.internal.filter.name.SynonymSetFilterNameHolder;
import com.liferay.portal.search.tuning.synonyms.web.internal.index.SynonymSet;
import com.liferay.portal.search.tuning.synonyms.web.internal.index.SynonymSetIndexReader;
import com.liferay.portal.search.tuning.synonyms.web.internal.index.name.SynonymSetIndexName;
import com.liferay.portal.search.tuning.synonyms.web.internal.index.name.SynonymSetIndexNameBuilder;

import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adam Brandizzi
 */
@Component(service = IndexToFilterSynchronizer.class)
public class IndexToFilterSynchronizerImpl
	implements IndexToFilterSynchronizer {

	@Override
	public void copyToFilter(
		SynonymSetIndexName synonymSetIndexName, String companyIndexName,
		boolean deletion) {

		updateFilters(
			companyIndexName, getSynonymFromIndex(synonymSetIndexName),
			deletion);
	}

	protected String[] getSynonymFromIndex(
		SynonymSetIndexName synonymSetIndexName) {

		List<SynonymSet> synonymSets = _synonymSetIndexReader.search(
			synonymSetIndexName);

		Stream<SynonymSet> stream = synonymSets.stream();

		return stream.map(
			SynonymSet::getSynonyms
		).toArray(
			String[]::new
		);
	}

	protected void updateFilters(
		String companyIndexName, String[] synonyms, boolean deletion) {

		for (String filterName : _synonymSetFilterNameHolder.getFilterNames()) {
			_synonymSetFilterWriter.updateSynonymSets(
				companyIndexName, filterName, synonyms, deletion);
		}
	}

	@Reference
	private SynonymSetFilterNameHolder _synonymSetFilterNameHolder;

	@Reference
	private SynonymSetFilterWriter _synonymSetFilterWriter;

	@Reference
	private SynonymSetIndexNameBuilder _synonymSetIndexNameBuilder;

	@Reference
	private SynonymSetIndexReader _synonymSetIndexReader;

}