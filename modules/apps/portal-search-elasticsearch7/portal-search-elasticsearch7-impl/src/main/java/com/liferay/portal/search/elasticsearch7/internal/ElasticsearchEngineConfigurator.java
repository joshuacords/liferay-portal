/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal;

import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchEngineConfigurator;
import com.liferay.portal.kernel.search.SearchEngineHelper;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchConnection;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchConnectionManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = "search.engine.impl=Elasticsearch",
	service = SearchEngineConfigurator.class
)
public class ElasticsearchEngineConfigurator
	extends BaseSearchEngineConfigurator {

	@Override
	public void destroy() {
		ElasticsearchConnection elasticsearchConnection =
			_elasticsearchConnectionManager.getElasticsearchConnection();

		elasticsearchConnection.close();

		super.destroy();
	}

	@Activate
	protected void activate(ComponentContext componentContext) {
		_bundleContext = componentContext.getBundleContext();

		setSearchEngines(_searchEngines);

		initialize();
	}

	@Override
	protected BundleContext getBundleContext() {
		return _bundleContext;
	}

	@Override
	protected String getDefaultSearchEngineId() {
		return SearchEngineHelper.SYSTEM_ENGINE_ID;
	}

	@Override
	protected DestinationFactory getDestinationFactory() {
		return _destinationFactory;
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
	protected MessageBus getMessageBus() {
		return _messageBus;
	}

	@Override
	protected ClassLoader getOperatingClassLoader() {
		Class<?> clazz = getClass();

		return clazz.getClassLoader();
	}

	@Override
	protected SearchEngineHelper getSearchEngineHelper() {
		return _searchEngineHelper;
	}

	@Override
	protected void initialize() {
		FutureTask<Void> futureTask = new FutureTask<Void>(
			() -> {
				_elasticsearchConnectionManager.connect();

				return null;
			});

		Thread thread = new Thread(
			futureTask, "Elasticsearch Initialization Thread");

		thread.setDaemon(true);

		thread.start();

		try {
			futureTask.get();
		}
		catch (Exception exception) {
			throw new RuntimeException(
				"Unable to initialize Elasticsearch engine", exception);
		}

		super.initialize();
	}

	@Reference(unbind = "-")
	protected void setDestinationFactory(
		DestinationFactory destinationFactory) {

		_destinationFactory = destinationFactory;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchConnectionManager(
		ElasticsearchConnectionManager elasticsearchConnectionManager) {

		_elasticsearchConnectionManager = elasticsearchConnectionManager;
	}

	@Reference(target = "(!(search.engine.impl=*))", unbind = "-")
	protected void setIndexSearcher(IndexSearcher indexSearcher) {
		_indexSearcher = indexSearcher;
	}

	@Reference(target = "(!(search.engine.impl=*))", unbind = "-")
	protected void setIndexWriter(IndexWriter indexWriter) {
		_indexWriter = indexWriter;
	}

	@Reference(unbind = "-")
	protected void setMessageBus(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	@Reference(
		target = "(&(search.engine.id=SYSTEM_ENGINE)(search.engine.impl=Elasticsearch))"
	)
	protected void setSearchEngine(
		SearchEngine searchEngine, Map<String, Object> properties) {

		String searchEngineId = MapUtil.getString(
			properties, "search.engine.id");

		_searchEngines.put(searchEngineId, searchEngine);
	}

	@Reference(unbind = "-")
	protected void setSearchEngineHelper(
		SearchEngineHelper searchEngineHelper) {

		_searchEngineHelper = searchEngineHelper;
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

	private BundleContext _bundleContext;
	private DestinationFactory _destinationFactory;
	private ElasticsearchConnectionManager _elasticsearchConnectionManager;
	private IndexSearcher _indexSearcher;
	private IndexWriter _indexWriter;
	private MessageBus _messageBus;
	private SearchEngineHelper _searchEngineHelper;
	private final Map<String, SearchEngine> _searchEngines =
		new ConcurrentHashMap<>();

}