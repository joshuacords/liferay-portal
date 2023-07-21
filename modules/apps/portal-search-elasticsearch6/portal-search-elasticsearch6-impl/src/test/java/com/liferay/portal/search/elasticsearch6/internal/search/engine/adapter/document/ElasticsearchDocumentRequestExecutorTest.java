/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.document;

import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.engine.adapter.document.DeleteByQueryDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.DeleteDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.IndexDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.UpdateByQueryDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.UpdateDocumentRequest;
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
public class ElasticsearchDocumentRequestExecutorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		_elasticsearchDocumentRequestExecutor =
			new ElasticsearchDocumentRequestExecutor() {
				{
					setDeleteByQueryDocumentRequestExecutor(
						_deleteByQueryDocumentRequestExecutor);
					setDeleteDocumentRequestExecutor(
						_deleteDocumentRequestExecutor);
					setIndexDocumentRequestExecutor(
						_indexDocumentRequestExecutor);
					setUpdateByQueryDocumentRequestExecutor(
						_updateByQueryDocumentRequestExecutor);
					setUpdateDocumentRequestExecutor(
						_updateDocumentRequestExecutor);
				}
			};
	}

	@Test
	public void testExecuteDeleteByQueryDocumentRequest() {
		DeleteByQueryDocumentRequest deleteByQueryDocumentRequest =
			new DeleteByQueryDocumentRequest(null);

		_elasticsearchDocumentRequestExecutor.executeDocumentRequest(
			deleteByQueryDocumentRequest);

		Mockito.verify(
			_deleteByQueryDocumentRequestExecutor
		).execute(
			deleteByQueryDocumentRequest
		);
	}

	@Test
	public void testExecuteDeleteDocumentRequest() {
		DeleteDocumentRequest deleteDocumentRequest = new DeleteDocumentRequest(
			null, null);

		_elasticsearchDocumentRequestExecutor.executeDocumentRequest(
			deleteDocumentRequest);

		Mockito.verify(
			_deleteDocumentRequestExecutor
		).execute(
			deleteDocumentRequest
		);
	}

	@Test
	public void testExecuteIndexDocumentRequest() {
		IndexDocumentRequest indexDocumentRequest = new IndexDocumentRequest(
			null, (Document)null);

		_elasticsearchDocumentRequestExecutor.executeDocumentRequest(
			indexDocumentRequest);

		Mockito.verify(
			_indexDocumentRequestExecutor
		).execute(
			indexDocumentRequest
		);
	}

	@Test
	public void testExecuteUpdateByQueryDocumentRequest() {
		UpdateByQueryDocumentRequest updateByQueryDocumentRequest =
			new UpdateByQueryDocumentRequest(null, null);

		_elasticsearchDocumentRequestExecutor.executeDocumentRequest(
			updateByQueryDocumentRequest);

		Mockito.verify(
			_updateByQueryDocumentRequestExecutor
		).execute(
			updateByQueryDocumentRequest
		);
	}

	@Test
	public void testExecuteUpdateDocumentRequest() {
		UpdateDocumentRequest updateDocumentRequest = new UpdateDocumentRequest(
			null, null, (Document)null);

		_elasticsearchDocumentRequestExecutor.executeDocumentRequest(
			updateDocumentRequest);

		Mockito.verify(
			_updateDocumentRequestExecutor
		).execute(
			updateDocumentRequest
		);
	}

	@Mock
	private DeleteByQueryDocumentRequestExecutor
		_deleteByQueryDocumentRequestExecutor;

	@Mock
	private DeleteDocumentRequestExecutor _deleteDocumentRequestExecutor;

	private ElasticsearchDocumentRequestExecutor
		_elasticsearchDocumentRequestExecutor;

	@Mock
	private IndexDocumentRequestExecutor _indexDocumentRequestExecutor;

	@Mock
	private UpdateByQueryDocumentRequestExecutor
		_updateByQueryDocumentRequestExecutor;

	@Mock
	private UpdateDocumentRequestExecutor _updateDocumentRequestExecutor;

}