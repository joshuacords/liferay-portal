/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.search;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;

import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = SearchSearchRequestExecutor.class)
public class SearchSearchRequestExecutorImpl
	implements SearchSearchRequestExecutor {

	@Override
	public SearchSearchResponse execute(
		SearchSearchRequest searchSearchRequest) {

		SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(
			_elasticsearchClientResolver.getClient(
				searchSearchRequest.isPreferLocalCluster()),
			SearchAction.INSTANCE);

		_searchSearchRequestAssembler.assemble(
			searchRequestBuilder, searchSearchRequest);

		if (_log.isTraceEnabled()) {
			String prettyPrintedRequestString = _getPrettyPrintedRequestString(
				searchRequestBuilder);

			_log.trace("Search query: " + prettyPrintedRequestString);
		}

		SearchResponse searchResponse = searchRequestBuilder.get();

		SearchSearchResponse searchSearchResponse = new SearchSearchResponse();

		_searchSearchResponseAssembler.assemble(
			searchRequestBuilder, searchResponse, searchSearchRequest,
			searchSearchResponse);

		if (_log.isDebugEnabled()) {
			_log.debug(
				StringBundler.concat(
					"The search engine processed ",
					searchSearchResponse.getSearchRequestString(), " in ",
					searchSearchResponse.getExecutionTime(), " ms"));
		}

		return searchSearchResponse;
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

	private String _getPrettyPrintedRequestString(
		SearchRequestBuilder searchRequestBuilder) {

		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.setPrettyPrinting();

		Gson gson = gsonBuilder.create();

		JsonParser jsonParser = new JsonParser();

		try {
			return gson.toJson(
				jsonParser.parse(searchRequestBuilder.toString()));
		}
		catch (Exception exception) {
			return exception.getMessage();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SearchSearchRequestExecutorImpl.class);

	private ElasticsearchClientResolver _elasticsearchClientResolver;
	private SearchSearchRequestAssembler _searchSearchRequestAssembler;
	private SearchSearchResponseAssembler _searchSearchResponseAssembler;

}