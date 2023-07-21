/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.synonyms.web.internal.index;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.index.CreateIndexRequest;
import com.liferay.portal.search.engine.adapter.index.DeleteIndexRequest;
import com.liferay.portal.search.tuning.synonyms.web.internal.index.name.SynonymSetIndexName;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adam Brandizzi
 */
@Component(service = SynonymSetIndexCreator.class)
public class SynonymSetIndexCreatorImpl implements SynonymSetIndexCreator {

	@Override
	public void create(SynonymSetIndexName synonymSetIndexName) {
		CreateIndexRequest createIndexRequest = new CreateIndexRequest(
			synonymSetIndexName.getIndexName());

		createIndexRequest.setSource(readIndexSettings());

		_searchEngineAdapter.execute(createIndexRequest);
	}

	@Override
	public void delete(SynonymSetIndexName synonymSetIndexName) {
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(
			synonymSetIndexName.getIndexName());

		_searchEngineAdapter.execute(deleteIndexRequest);
	}

	protected String readIndexSettings() {
		return StringUtil.read(getClass(), INDEX_SETTINGS_RESOURCE_NAME);
	}

	protected static final String INDEX_SETTINGS_RESOURCE_NAME =
		"/META-INF/search/liferay-search-tuning-synonyms-index.json";

	@Reference
	private SearchEngineAdapter _searchEngineAdapter;

}