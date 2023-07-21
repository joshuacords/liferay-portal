/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.search;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.search.MultisearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.MultisearchSearchResponse;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.action.search.MultiSearchAction;
import org.elasticsearch.action.search.MultiSearchRequestBuilder;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = MultisearchSearchRequestExecutor.class)
public class MultisearchSearchRequestExecutorImpl
	implements MultisearchSearchRequestExecutor {

	@Override
	public MultisearchSearchResponse execute(
		MultisearchSearchRequest multisearchSearchRequest) {

		Client client = _elasticsearchClientResolver.getClient(true);

		MultiSearchRequestBuilder multiSearchRequestBuilder =
			MultiSearchAction.INSTANCE.newRequestBuilder(client);

		List<SearchSearchRequest> searchSearchRequests =
			multisearchSearchRequest.getSearchSearchRequests();

		List<SearchRequestHolder> searchRequestHolders = new ArrayList<>(
			searchSearchRequests.size());

		searchSearchRequests.forEach(
			searchSearchRequest -> {
				SearchRequestBuilder searchRequestBuilder =
					SearchAction.INSTANCE.newRequestBuilder(client);

				_searchSearchRequestAssembler.assemble(
					searchRequestBuilder, searchSearchRequest);

				SearchRequestHolder searchRequestHolder =
					new SearchRequestHolder(
						searchSearchRequest, searchRequestBuilder);

				searchRequestHolders.add(searchRequestHolder);

				multiSearchRequestBuilder.add(searchRequestBuilder);
			});

		MultiSearchResponse multiSearchResponse =
			multiSearchRequestBuilder.get();

		Iterator<MultiSearchResponse.Item> multiSearchResponseItems =
			multiSearchResponse.iterator();

		MultisearchSearchResponse multisearchSearchResponse =
			new MultisearchSearchResponse();

		int counter = 0;

		while (multiSearchResponseItems.hasNext()) {
			MultiSearchResponse.Item multiSearchResponseItem =
				multiSearchResponseItems.next();

			SearchResponse searchResponse =
				multiSearchResponseItem.getResponse();

			SearchSearchResponse searchSearchResponse =
				new SearchSearchResponse();

			SearchRequestHolder searchRequestHolder = searchRequestHolders.get(
				counter);

			SearchSearchRequest searchSearchRequest =
				searchRequestHolder.getSearchSearchRequest();

			_searchSearchResponseAssembler.assemble(
				searchRequestHolder.getSearchRequestBuilder(), searchResponse,
				searchSearchRequest, searchSearchResponse);

			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"The search engine processed ",
						searchSearchResponse.getSearchRequestString(), " in ",
						searchSearchResponse.getExecutionTime(), " ms"));
			}

			if (searchSearchRequest.isIncludeResponseString()) {
				searchSearchResponse.setSearchResponseString(
					searchResponse.toString());
			}

			counter++;
		}

		return multisearchSearchResponse;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	@Reference(unbind = "-")
	protected void setSearchSearchRequestAssembler(
		SearchSearchRequestAssembler searchSearchRequestAssembler) {

		_searchSearchRequestAssembler = searchSearchRequestAssembler;
	}

	@Reference(unbind = "-")
	protected void setSearchSearchResponseAssembler(
		SearchSearchResponseAssembler searchSearchResponseAssembler) {

		_searchSearchResponseAssembler = searchSearchResponseAssembler;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MultisearchSearchRequestExecutorImpl.class);

	private ElasticsearchClientResolver _elasticsearchClientResolver;
	private SearchSearchRequestAssembler _searchSearchRequestAssembler;
	private SearchSearchResponseAssembler _searchSearchResponseAssembler;

	private class SearchRequestHolder {

		public SearchRequestHolder(
			SearchSearchRequest searchSearchRequest,
			SearchRequestBuilder searchRequestBuilder) {

			_searchSearchRequest = searchSearchRequest;
			_searchRequestBuilder = searchRequestBuilder;
		}

		public SearchRequestBuilder getSearchRequestBuilder() {
			return _searchRequestBuilder;
		}

		public SearchSearchRequest getSearchSearchRequest() {
			return _searchSearchRequest;
		}

		private final SearchRequestBuilder _searchRequestBuilder;
		private final SearchSearchRequest _searchSearchRequest;

	}

}