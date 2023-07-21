/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.indexer;

import com.liferay.portal.search.permission.SearchPermissionFilterContributor;

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
@Component(
	immediate = true, service = SearchPermissionFilterContributorsHolder.class
)
public class SearchPermissionFilterContributorsHolderImpl
	implements SearchPermissionFilterContributorsHolder {

	@Override
	public Stream<SearchPermissionFilterContributor> getAll() {
		return _searchPermissionFilterContributors.stream();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addSearchPermissionFilterContributor(
		SearchPermissionFilterContributor searchPermissionFilterContributor) {

		_searchPermissionFilterContributors.add(
			searchPermissionFilterContributor);
	}

	protected void removeSearchPermissionFilterContributor(
		SearchPermissionFilterContributor searchPermissionFilterContributor) {

		_searchPermissionFilterContributors.remove(
			searchPermissionFilterContributor);
	}

	private final Collection<SearchPermissionFilterContributor>
		_searchPermissionFilterContributors = new CopyOnWriteArrayList<>();

}