/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.similar.results.web.internal.builder;

import com.liferay.portal.search.similar.results.web.spi.contributor.SimilarResultsContributor;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = SimilarResultsContributorsHolder.class)
public class SimilarResultsContributorsHolderImpl
	implements SimilarResultsContributorsHolder {

	@Override
	public Stream<SimilarResultsContributor> stream() {
		return _similarResultsContributors.stream();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addSimilarResultsContributor(
		SimilarResultsContributor similarResultsContributor) {

		_similarResultsContributors.add(similarResultsContributor);
	}

	protected void removeSimilarResultsContributor(
		SimilarResultsContributor similarResultsContributor) {

		_similarResultsContributors.remove(similarResultsContributor);
	}

	private final Collection<SimilarResultsContributor>
		_similarResultsContributors = new CopyOnWriteArrayList<>();

}