/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr7.internal;

import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.search.BaseSearchEngineConfigurator;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchEngineConfigurator;
import com.liferay.portal.kernel.search.SearchEngineHelper;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = "search.engine.impl=Solr",
	service = SearchEngineConfigurator.class
)
public class SolrSearchEngineConfigurator extends BaseSearchEngineConfigurator {

	@Override
	public void destroy() {
		super.destroy();
	}

	@Activate
	protected void activate() {
		setSearchEngines(_searchEngines);
	}

	@Override
	protected String getDefaultSearchEngineId() {
		return SearchEngineHelper.SYSTEM_ENGINE_ID;
	}

	@Override
	protected IndexSearcher getIndexSearcher() {
		return _indexSearcher;
	}

	@Override
	protected IndexWriter getIndexWriter() {
		return _indexWriter;
	}

	@Override
	protected ClassLoader getOperatingClassloader() {
		Class<?> clazz = getClass();

		return clazz.getClassLoader();
	}

	@Override
	protected SearchEngineHelper getSearchEngineHelper() {
		return searchEngineHelper;
	}

	@Reference(target = "(!(search.engine.impl=*))", unbind = "-")
	protected void setIndexSearcher(IndexSearcher indexSearcher) {
		_indexSearcher = indexSearcher;
	}

	@Reference(target = "(!(search.engine.impl=*))", unbind = "-")
	protected void setIndexWriter(IndexWriter indexWriter) {
		_indexWriter = indexWriter;
	}

	@Reference(
		target = "(&(search.engine.id=SYSTEM_ENGINE)(search.engine.impl=Solr))"
	)
	protected void setSearchEngine(
		SearchEngine searchEngine, Map<String, Object> properties) {

		String searchEngineId = MapUtil.getString(
			properties, "search.engine.id");

		_searchEngines.put(searchEngineId, searchEngine);
	}

	protected void unsetSearchEngine(
		SearchEngine searchEngine, Map<String, Object> properties) {

		String searchEngineId = MapUtil.getString(
			properties, "search.engine.id");

		if (Validator.isNull(searchEngineId)) {
			return;
		}

		_searchEngines.remove(searchEngineId);
	}

	@Reference
	protected SearchEngineHelper searchEngineHelper;

	@Reference
	private DestinationFactory _destinationFactory;

	private IndexSearcher _indexSearcher;
	private IndexWriter _indexWriter;

	@Reference
	private MessageBus _messageBus;

	private final Map<String, SearchEngine> _searchEngines =
		new ConcurrentHashMap<>();

}