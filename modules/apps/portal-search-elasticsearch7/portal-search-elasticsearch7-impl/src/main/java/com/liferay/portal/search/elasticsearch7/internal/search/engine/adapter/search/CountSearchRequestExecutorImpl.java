/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.search;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.search.CountSearchRequest;
import com.liferay.portal.search.engine.adapter.search.CountSearchResponse;

import org.apache.lucene.search.TotalHits;

import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHits;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = CountSearchRequestExecutor.class)
public class CountSearchRequestExecutorImpl
	implements CountSearchRequestExecutor {

	@Override
	public CountSearchResponse execute(CountSearchRequest countSearchRequest) {
		SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(
			_elasticsearchClientResolver.getClient(true),
			SearchAction.INSTANCE);

		_commonSearchRequestBuilderAssembler.assemble(
			searchRequestBuilder, countSearchRequest);

		searchRequestBuilder.setSize(0);
		searchRequestBuilder.setTrackScores(false);

		SearchResponse searchResponse = searchRequestBuilder.get();

		SearchHits searchHits = searchResponse.getHits();

		CountSearchResponse countSearchResponse = new CountSearchResponse();

		TotalHits totalHits = searchHits.getTotalHits();

		countSearchResponse.setCount(totalHits.value);

		_commonSearchResponseAssembler.assemble(
			searchRequestBuilder, searchResponse, countSearchRequest,
			countSearchResponse);

		if (_log.isDebugEnabled()) {
			_log.debug(
				StringBundler.concat(
					"The search engine processed ",
					countSearchResponse.getSearchRequestString(), " in ",
					countSearchResponse.getExecutionTime(), " ms"));
		}

		return countSearchResponse;
	}

	@Reference(unbind = "-")
	protected void setCommonSearchRequestBuilderAssembler(
		CommonSearchRequestBuilderAssembler
			commonSearchRequestBuilderAssembler) {

		_commonSearchRequestBuilderAssembler =
			commonSearchRequestBuilderAssembler;
	}

	@Reference(unbind = "-")
	protected void setCommonSearchResponseAssembler(
		CommonSearchResponseAssembler commonSearchResponseAssembler) {

		_commonSearchResponseAssembler = commonSearchResponseAssembler;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CountSearchRequestExecutorImpl.class);

	private CommonSearchRequestBuilderAssembler
		_commonSearchRequestBuilderAssembler;
	private CommonSearchResponseAssembler _commonSearchResponseAssembler;
	private ElasticsearchClientResolver _elasticsearchClientResolver;

}