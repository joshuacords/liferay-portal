/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.background.task;

import com.liferay.portal.search.elasticsearch7.internal.ElasticsearchSearchEngineFixture;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch7.internal.index.FieldMappingAssert;
import com.liferay.portal.search.elasticsearch7.internal.index.LiferayTypeMappingsConstants;
import com.liferay.portal.search.test.util.background.task.BaseReindexSingleIndexerBackgroundTaskExecutorTestCase;
import com.liferay.portal.search.test.util.search.engine.SearchEngineFixture;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author Adam Brandizzi
 */
public class ReindexSingleIndexerBackgroundTaskExecutorTest
	extends BaseReindexSingleIndexerBackgroundTaskExecutorTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Override
	protected void assertFieldType(String fieldName, String fieldType)
		throws Exception {

		ElasticsearchFixture elasticsearchFixture =
			_elasticsearchSearchEngineFixture.getElasticsearchFixture();

		FieldMappingAssert.assertType(
			fieldType, fieldName,
			LiferayTypeMappingsConstants.LIFERAY_DOCUMENT_TYPE, getIndexName(),
			elasticsearchFixture.getIndicesAdminClient());
	}

	@Override
	protected SearchEngineFixture getSearchEngineFixture() {
		_elasticsearchSearchEngineFixture =
			new ElasticsearchSearchEngineFixture(
				ReindexSingleIndexerBackgroundTaskExecutorTest.class.
					getSimpleName());

		return _elasticsearchSearchEngineFixture;
	}

	private ElasticsearchSearchEngineFixture _elasticsearchSearchEngineFixture;

}