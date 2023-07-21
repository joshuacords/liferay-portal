/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.synonyms.web.internal.synchronizer;

import com.liferay.portal.search.tuning.synonyms.web.internal.filter.SynonymSetFilterReader;
import com.liferay.portal.search.tuning.synonyms.web.internal.filter.name.SynonymSetFilterNameHolder;
import com.liferay.portal.search.tuning.synonyms.web.internal.index.SynonymSet;
import com.liferay.portal.search.tuning.synonyms.web.internal.index.SynonymSetIndexWriter;
import com.liferay.portal.search.tuning.synonyms.web.internal.index.name.SynonymSetIndexName;
import com.liferay.portal.search.tuning.synonyms.web.internal.index.name.SynonymSetIndexNameBuilder;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adam Brandizzi
 */
@Component(service = FilterToIndexSynchronizer.class)
public class FilterToIndexSynchronizerImpl
	implements FilterToIndexSynchronizer {

	@Override
	public void copyToIndex(
		String companyIndexName, SynonymSetIndexName synonymSetIndexName) {

		for (String synonyms : getSynonymsFromFilters(companyIndexName)) {
			addSynonymSetToIndex(synonymSetIndexName, synonyms);
		}
	}

	protected void addSynonymSetToIndex(
		SynonymSetIndexName synonymSetIndexName, String synonyms) {

		SynonymSet.SynonymSetBuilder synonymSetBuilder =
			new SynonymSet.SynonymSetBuilder();

		synonymSetBuilder.synonyms(synonyms);

		_synonymSetIndexWriter.create(
			synonymSetIndexName, synonymSetBuilder.build());
	}

	protected String[] getSynonymsFromFilters(String companyIndexName) {
		LinkedHashSet<String> synonyms = Stream.of(
			_synonymSetFilterNameHolder.getFilterNames()
		).map(
			filterName -> _synonymSetFilterReader.getSynonymSets(
				companyIndexName, filterName)
		).flatMap(
			Stream::of
		).collect(
			Collectors.toCollection(LinkedHashSet::new)
		);

		return synonyms.toArray(new String[0]);
	}

	@Reference
	private SynonymSetFilterNameHolder _synonymSetFilterNameHolder;

	@Reference
	private SynonymSetFilterReader _synonymSetFilterReader;

	@Reference
	private SynonymSetIndexNameBuilder _synonymSetIndexNameBuilder;

	@Reference
	private SynonymSetIndexWriter _synonymSetIndexWriter;

}