/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.search;

import com.liferay.portal.search.searcher.SearchResponse;

import java.util.Collections;
import java.util.List;

/**
 * @author Joshua Cords
 */
public class ObjectRelatedEntrySearchResponse {

	public ObjectRelatedEntrySearchResponse(
		List<ObjectRelatedEntrySearchResult> objectRelatedEntrySearchResults,
		SearchResponse searchResponse) {

		_objectRelatedEntrySearchResults = Collections.unmodifiableList(
			objectRelatedEntrySearchResults);
		_searchResponse = searchResponse;
	}

	public List<ObjectRelatedEntrySearchResult>
		getObjectRelatedEntrySearchResults() {

		return _objectRelatedEntrySearchResults;
	}

	public SearchResponse getSearchResponse() {
		return _searchResponse;
	}

	private final List<ObjectRelatedEntrySearchResult>
		_objectRelatedEntrySearchResults;
	private final SearchResponse _searchResponse;

}