/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.engine.adapter.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Dylan Rebelak
 */
public class MultisearchSearchRequest
	implements SearchRequest<MultisearchSearchResponse> {

	@Override
	public MultisearchSearchResponse accept(
		SearchRequestExecutor searchRequestExecutor) {

		return searchRequestExecutor.executeSearchRequest(this);
	}

	public void addSearchSearchRequest(
		SearchSearchRequest searchSearchRequest) {

		_searchSearchRequests.add(searchSearchRequest);
	}

	public void addSearchSearchRequests(
		Collection<SearchSearchRequest> searchSearchRequests) {

		_searchSearchRequests.addAll(searchSearchRequests);
	}

	public List<SearchSearchRequest> getSearchSearchRequests() {
		return _searchSearchRequests;
	}

	private final List<SearchSearchRequest> _searchSearchRequests =
		new ArrayList<>();

}