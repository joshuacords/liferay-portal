/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.index;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.index.CreateIndexRequest;
import com.liferay.portal.search.engine.adapter.index.DeleteIndexRequest;
import com.liferay.portal.search.tuning.rankings.web.internal.index.name.RankingIndexName;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wade Cao
 * @author Adam Brandizzi
 */
@Component(service = RankingIndexCreator.class)
public class RankingIndexCreatorImpl implements RankingIndexCreator {

	@Override
	public void create(RankingIndexName rankingIndexName) {
		String mappingSource = StringUtil.read(
			getClass(), _INDEX_SETTINGS_RESOURCE_NAME);

		CreateIndexRequest createIndexRequest = new CreateIndexRequest(
			rankingIndexName.getIndexName());

		createIndexRequest.setSource(mappingSource);

		_searchEngineAdapter.execute(createIndexRequest);
	}

	@Override
	public void delete(RankingIndexName rankingIndexName) {
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(
			rankingIndexName.getIndexName());

		_searchEngineAdapter.execute(deleteIndexRequest);
	}

	private static final String _INDEX_SETTINGS_RESOURCE_NAME =
		"/META-INF/search/liferay-search-tuning-rankings-index.json";

	@Reference
	private SearchEngineAdapter _searchEngineAdapter;

}