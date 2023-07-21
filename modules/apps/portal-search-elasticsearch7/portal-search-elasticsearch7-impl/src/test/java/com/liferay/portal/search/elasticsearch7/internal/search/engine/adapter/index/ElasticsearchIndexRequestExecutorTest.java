/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.search.engine.adapter.index.GetFieldMappingIndexRequest;
import com.liferay.portal.search.engine.adapter.index.GetMappingIndexRequest;
import com.liferay.portal.search.engine.adapter.index.PutMappingIndexRequest;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Dylan Rebelak
 */
public class ElasticsearchIndexRequestExecutorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		_elasticsearchIndexRequestExecutor =
			new ElasticsearchIndexRequestExecutor() {
				{
					setGetFieldMappingIndexRequestExecutor(
						_getFieldMappingIndexRequestExecutor);
					setGetMappingIndexRequestExecutor(
						_getMappingIndexRequestExecutor);
					setPutMappingIndexRequestExecutor(
						_putMappingIndexRequestExecutor);
				}
			};
	}

	@Test
	public void testExecuteGetFieldMappingIndexRequest() {
		GetFieldMappingIndexRequest getFieldMappingIndexRequest =
			new GetFieldMappingIndexRequest(null, null, null);

		_elasticsearchIndexRequestExecutor.executeIndexRequest(
			getFieldMappingIndexRequest);

		Mockito.verify(
			_getFieldMappingIndexRequestExecutor
		).execute(
			getFieldMappingIndexRequest
		);
	}

	@Test
	public void testExecuteGetMappingIndexRequest() {
		GetMappingIndexRequest getMappingIndexRequest =
			new GetMappingIndexRequest(null, null);

		_elasticsearchIndexRequestExecutor.executeIndexRequest(
			getMappingIndexRequest);

		Mockito.verify(
			_getMappingIndexRequestExecutor
		).execute(
			getMappingIndexRequest
		);
	}

	@Test
	public void testExecutePutMappingIndexRequest() {
		PutMappingIndexRequest putMappingIndexRequest =
			new PutMappingIndexRequest(null, null, null);

		_elasticsearchIndexRequestExecutor.executeIndexRequest(
			putMappingIndexRequest);

		Mockito.verify(
			_putMappingIndexRequestExecutor
		).execute(
			putMappingIndexRequest
		);
	}

	private ElasticsearchIndexRequestExecutor
		_elasticsearchIndexRequestExecutor;

	@Mock
	private GetFieldMappingIndexRequestExecutor
		_getFieldMappingIndexRequestExecutor;

	@Mock
	private GetMappingIndexRequestExecutor _getMappingIndexRequestExecutor;

	@Mock
	private PutMappingIndexRequestExecutor _putMappingIndexRequestExecutor;

}