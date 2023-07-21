/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch6.internal.connection.OperationMode;
import com.liferay.portal.search.elasticsearch6.internal.index.CompanyIdIndexNameBuilder;
import com.liferay.portal.search.elasticsearch6.internal.index.CompanyIndexFactory;
import com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.ElasticsearchEngineAdapterFixture;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.search.test.util.search.engine.SearchEngineFixture;

/**
 * @author Adam Brandizzi
 */
public class ElasticsearchSearchEngineFixture implements SearchEngineFixture {

	public ElasticsearchSearchEngineFixture(String tmpDir) {
		_tmpDir = tmpDir;
	}

	public ElasticsearchConnectionManager getElasticsearchConnectionManager() {
		return _elasticsearchConnectionManager;
	}

	public ElasticsearchFixture getElasticsearchFixture() {
		return _elasticsearchFixture;
	}

	public ElasticsearchSearchEngine getElasticsearchSearchEngine() {
		return _elasticsearchSearchEngine;
	}

	@Override
	public IndexNameBuilder getIndexNameBuilder() {
		return _indexNameBuilder;
	}

	@Override
	public SearchEngine getSearchEngine() {
		return _elasticsearchSearchEngine;
	}

	@Override
	public void setUp() throws Exception {
		setUpElasticsearchFixture();

		setUpIndexNameBuilder();

		setUpElasticsearchConnnectionManager();

		setUpSearchEngineAdapter();

		setUpElasticsearchSearchEngine();
	}

	@Override
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	protected CompanyIndexFactory createCompanyIndexFactory() {
		return new CompanyIndexFactory() {
			{
				indexNameBuilder = _indexNameBuilder;
				jsonFactory = new JSONFactoryImpl();
			}
		};
	}

	protected void setUpElasticsearchConnnectionManager() {
		_elasticsearchConnectionManager = new ElasticsearchConnectionManager();

		_elasticsearchConnectionManager.setEmbeddedElasticsearchConnection(
			_elasticsearchFixture.getEmbeddedElasticsearchConnection());

		_elasticsearchConnectionManager.activate(OperationMode.EMBEDDED);
	}

	protected void setUpElasticsearchFixture() throws Exception {
		_elasticsearchFixture = new ElasticsearchFixture(_tmpDir);

		_elasticsearchFixture.setUp();
	}

	protected void setUpElasticsearchSearchEngine() {
		_elasticsearchSearchEngine = new ElasticsearchSearchEngine() {
			{
				setElasticsearchConnectionManager(
					_elasticsearchConnectionManager);
				setIndexFactory(createCompanyIndexFactory());
				setIndexNameBuilder(String::valueOf);
				setSearchEngineAdapter(_searchEngineAdapter);
			}
		};
	}

	protected void setUpIndexNameBuilder() {
		_indexNameBuilder = new CompanyIdIndexNameBuilder() {
			{
				setIndexNamePrefix(null);
			}
		};
	}

	protected void setUpSearchEngineAdapter() {
		ElasticsearchEngineAdapterFixture elasticsearchEngineAdapterFixture =
			new ElasticsearchEngineAdapterFixture() {
				{
					setElasticsearchClientResolver(_elasticsearchFixture);
				}
			};

		elasticsearchEngineAdapterFixture.setUp();

		_searchEngineAdapter =
			elasticsearchEngineAdapterFixture.getSearchEngineAdapter();
	}

	private ElasticsearchConnectionManager _elasticsearchConnectionManager;
	private ElasticsearchFixture _elasticsearchFixture;
	private ElasticsearchSearchEngine _elasticsearchSearchEngine;
	private IndexNameBuilder _indexNameBuilder;
	private SearchEngineAdapter _searchEngineAdapter;
	private final String _tmpDir;

}