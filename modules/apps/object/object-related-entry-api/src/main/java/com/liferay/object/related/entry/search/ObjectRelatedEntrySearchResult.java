/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.search;

import com.liferay.petra.lang.HashUtil;
import com.liferay.portal.search.hits.SearchHit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Joshua Cords
 */
public class ObjectRelatedEntrySearchResult {

	public ObjectRelatedEntrySearchResult(String className, long classPK) {
		_className = className;
		_classPK = classPK;
	}

	public void addRelatedEntrySearchHit(SearchHit searchHit) {
		_relatedEntrySearchHits.add(searchHit);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ObjectRelatedEntrySearchResult)) {
			return false;
		}

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			(ObjectRelatedEntrySearchResult)object;

		if ((_classPK == objectRelatedEntrySearchResult._classPK) &&
			Objects.equals(
				_className, objectRelatedEntrySearchResult._className)) {

			return true;
		}

		return false;
	}

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public List<SearchHit> getRelatedEntrySearchHits() {
		return Collections.unmodifiableList(_relatedEntrySearchHits);
	}

	public float getScore() {
		float score = 0;

		if (_searchHit != null) {
			score = _searchHit.getScore();
		}

		for (SearchHit relatedEntrySearchHit : _relatedEntrySearchHits) {
			score = Math.max(score, relatedEntrySearchHit.getScore());
		}

		return score;
	}

	public SearchHit getSearchHit() {
		return _searchHit;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _classPK);

		return HashUtil.hash(hash, _className);
	}

	public void setSearchHit(SearchHit searchHit) {
		_searchHit = searchHit;
	}

	private final String _className;
	private final long _classPK;
	private final List<SearchHit> _relatedEntrySearchHits = new ArrayList<>();
	private SearchHit _searchHit;

}