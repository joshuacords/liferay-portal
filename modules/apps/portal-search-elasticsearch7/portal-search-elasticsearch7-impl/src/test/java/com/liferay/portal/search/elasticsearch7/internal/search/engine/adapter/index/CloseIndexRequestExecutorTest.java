/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.engine.adapter.index.CloseIndexRequest;
import com.liferay.portal.search.engine.adapter.index.IndicesOptions;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.PropsImpl;

import org.elasticsearch.action.admin.indices.close.CloseIndexRequestBuilder;
import org.elasticsearch.common.unit.TimeValue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class CloseIndexRequestExecutorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		PropsUtil.setProps(new PropsImpl());

		_elasticsearchFixture = new ElasticsearchFixture(
			CreateIndexRequestExecutorTest.class.getSimpleName());

		_elasticsearchFixture.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Test
	public void testIndexRequestTranslation() {
		CloseIndexRequest closeIndexRequest = new CloseIndexRequest(
			_INDEX_NAME);

		IndicesOptions indicesOptions = new IndicesOptions();

		indicesOptions.setIgnoreUnavailable(true);

		closeIndexRequest.setIndicesOptions(indicesOptions);

		closeIndexRequest.setTimeout(100);

		CloseIndexRequestExecutorImpl closeIndexRequestExecutorImpl =
			new CloseIndexRequestExecutorImpl() {
				{
					setElasticsearchClientResolver(_elasticsearchFixture);
					setIndicesOptionsTranslator(
						new IndicesOptionsTranslatorImpl());
				}
			};

		CloseIndexRequestBuilder closeIndexRequestBuilder =
			closeIndexRequestExecutorImpl.createCloseIndexRequestBuilder(
				closeIndexRequest);

		org.elasticsearch.action.admin.indices.close.CloseIndexRequest
			elastichsearchCloseIndexRequest =
				closeIndexRequestBuilder.request();

		Assert.assertArrayEquals(
			closeIndexRequest.getIndexNames(),
			elastichsearchCloseIndexRequest.indices());

		IndicesOptionsTranslator indicesOptionsTranslator =
			new IndicesOptionsTranslatorImpl();

		Assert.assertEquals(
			indicesOptionsTranslator.translate(
				closeIndexRequest.getIndicesOptions()),
			elastichsearchCloseIndexRequest.indicesOptions());

		Assert.assertEquals(
			TimeValue.timeValueMillis(closeIndexRequest.getTimeout()),
			elastichsearchCloseIndexRequest.masterNodeTimeout());

		Assert.assertEquals(
			TimeValue.timeValueMillis(closeIndexRequest.getTimeout()),
			elastichsearchCloseIndexRequest.timeout());
	}

	private static final String _INDEX_NAME = "test_request_index";

	private ElasticsearchFixture _elasticsearchFixture;

}