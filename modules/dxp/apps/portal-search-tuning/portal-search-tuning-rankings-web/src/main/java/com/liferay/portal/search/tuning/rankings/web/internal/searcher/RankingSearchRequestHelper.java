/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.searcher;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.filter.ComplexQueryPart;
import com.liferay.portal.search.filter.ComplexQueryPartBuilderFactory;
import com.liferay.portal.search.query.IdsQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.tuning.rankings.web.internal.index.Ranking;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(service = RankingSearchRequestHelper.class)
public class RankingSearchRequestHelper {

	public void contribute(
		SearchRequestBuilder searchRequestBuilder, Ranking ranking) {

		Stream.concat(
			getPinIdsQueryParts(ranking),
			Stream.of(getBlockIdsQueryPart(ranking))
		).forEach(
			searchRequestBuilder::addComplexQueryPart
		);
	}

	protected ComplexQueryPart getBlockIdsQueryPart(Ranking ranking) {
		List<String> ids = ranking.getBlockIds();

		if (ids.isEmpty()) {
			return null;
		}

		return complexQueryPartBuilderFactory.builder(
		).additive(
			true
		).query(
			_getIdsQuery(ids)
		).occur(
			"must_not"
		).build();
	}

	protected IdsQuery getIdsQuery(Ranking.Pin pin, int size) {
		IdsQuery idsQuery = queries.ids();

		idsQuery.addIds(pin.getId());

		idsQuery.setBoost((size - pin.getPosition()) * 10000F);

		return idsQuery;
	}

	protected ComplexQueryPart getPinIdsQueryPart(Query query) {
		return complexQueryPartBuilderFactory.builder(
		).additive(
			true
		).query(
			query
		).occur(
			"should"
		).build();
	}

	protected Stream<ComplexQueryPart> getPinIdsQueryParts(Ranking ranking) {
		List<Ranking.Pin> pins = ranking.getPins();

		Stream<Ranking.Pin> stream = pins.stream();

		return stream.map(
			pin -> getIdsQuery(pin, pins.size())
		).map(
			this::getPinIdsQueryPart
		);
	}

	@Reference
	protected ComplexQueryPartBuilderFactory complexQueryPartBuilderFactory;

	@Reference
	protected Queries queries;

	private IdsQuery _getIdsQuery(Collection<String> ids) {
		if (ids.isEmpty()) {
			return null;
		}

		IdsQuery idsQuery = queries.ids();

		idsQuery.addIds(ArrayUtil.toStringArray(ids));

		return idsQuery;
	}

}