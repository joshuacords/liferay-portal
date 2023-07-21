/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.searcher;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.search.spi.searcher.SearchRequestContributor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = SearchRequestContributorsHolder.class)
public class SearchRequestContributorsHolderImpl
	implements SearchRequestContributorsHolder {

	@Override
	public Stream<SearchRequestContributor> stream() {
		return _searchRequestContributors.stream();
	}

	@Override
	public Stream<SearchRequestContributor> stream(
		Collection<String> includeIds, Collection<String> excludeIds) {

		Collection<SearchRequestContributor> collection = include(includeIds);

		exclude(collection, excludeIds);

		return collection.stream();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, SearchRequestContributor.class,
			"search.request.contributor.id");
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addSearchRequestContributor(
		SearchRequestContributor searchRequestContributor) {

		_searchRequestContributors.add(searchRequestContributor);
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	protected void exclude(
		Collection<SearchRequestContributor> collection,
		Collection<String> ids) {

		Stream<String> stream = ids.stream();

		stream.map(
			_serviceTrackerMap::getService
		).filter(
			Objects::nonNull
		).forEach(
			collection::removeAll
		);
	}

	protected Collection<SearchRequestContributor> include(
		Collection<String> ids) {

		if ((ids == null) || ids.isEmpty()) {
			return new ArrayList<>(_searchRequestContributors);
		}

		Collection<SearchRequestContributor> collection = new ArrayList<>();

		for (String id : ids) {
			collection.addAll(_serviceTrackerMap.getService(id));
		}

		return collection;
	}

	protected void removeSearchRequestContributor(
		SearchRequestContributor searchRequestContributor) {

		_searchRequestContributors.remove(searchRequestContributor);
	}

	private final Collection<SearchRequestContributor>
		_searchRequestContributors = new CopyOnWriteArrayList<>();
	private ServiceTrackerMap<String, List<SearchRequestContributor>>
		_serviceTrackerMap;

}