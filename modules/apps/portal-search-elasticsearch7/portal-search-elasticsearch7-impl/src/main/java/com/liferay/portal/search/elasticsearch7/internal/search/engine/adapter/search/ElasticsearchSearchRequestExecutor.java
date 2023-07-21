/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.search;

import com.liferay.portal.search.engine.adapter.search.CountSearchRequest;
import com.liferay.portal.search.engine.adapter.search.CountSearchResponse;
import com.liferay.portal.search.engine.adapter.search.MultisearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.MultisearchSearchResponse;
import com.liferay.portal.search.engine.adapter.search.SearchRequestExecutor;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.engine.adapter.search.SuggestSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SuggestSearchResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	property = "search.engine.impl=Elasticsearch",
	service = SearchRequestExecutor.class
)
public class ElasticsearchSearchRequestExecutor
	implements SearchRequestExecutor {

	@Override
	public CountSearchResponse executeSearchRequest(
		CountSearchRequest countSearchRequest) {

		return _countSearchRequestExecutor.execute(countSearchRequest);
	}

	@Override
	public MultisearchSearchResponse executeSearchRequest(
		MultisearchSearchRequest multisearchSearchRequest) {

		return _multisearchSearchRequestExecutor.execute(
			multisearchSearchRequest);
	}

	@Override
	public SearchSearchResponse executeSearchRequest(
		SearchSearchRequest searchSearchRequest) {

		return _searchSearchRequestExecutor.execute(searchSearchRequest);
	}

	@Override
	public SuggestSearchResponse executeSearchRequest(
		SuggestSearchRequest suggestSearchRequest) {

		return _suggestSearchRequestExecutor.execute(suggestSearchRequest);
	}

	@Reference(unbind = "-")
	protected void setCountSearchRequestExecutor(
		CountSearchRequestExecutor countSearchRequestExecutor) {

		_countSearchRequestExecutor = countSearchRequestExecutor;
	}

	@Reference(unbind = "-")
	protected void setMultisearchSearchRequestExecutor(
		MultisearchSearchRequestExecutor multisearchSearchRequestExecutor) {

		_multisearchSearchRequestExecutor = multisearchSearchRequestExecutor;
	}

	@Reference(unbind = "-")
	protected void setSearchSearchRequestExecutor(
		SearchSearchRequestExecutor searchSearchRequestExecutor) {

		_searchSearchRequestExecutor = searchSearchRequestExecutor;
	}

	@Reference(unbind = "-")
	protected void setSuggestSearchRequestExecutor(
		SuggestSearchRequestExecutor suggestSearchRequestExecutor) {

		_suggestSearchRequestExecutor = suggestSearchRequestExecutor;
	}

	private CountSearchRequestExecutor _countSearchRequestExecutor;
	private MultisearchSearchRequestExecutor _multisearchSearchRequestExecutor;
	private SearchSearchRequestExecutor _searchSearchRequestExecutor;
	private SuggestSearchRequestExecutor _suggestSearchRequestExecutor;

}