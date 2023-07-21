/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.engine.adapter.index.GetFieldMappingIndexRequest;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.PropsImpl;

import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsRequestBuilder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Dylan Rebelak
 */
public class GetFieldMappingIndexRequestExecutorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		PropsUtil.setProps(new PropsImpl());

		_elasticsearchFixture = new ElasticsearchFixture(
			GetFieldMappingIndexRequestExecutorTest.class.getSimpleName());

		_elasticsearchFixture.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Test
	public void testIndexRequestTranslation() {
		GetFieldMappingIndexRequest getFieldMappingIndexRequest =
			new GetFieldMappingIndexRequest(
				new String[] {_INDEX_NAME}, _MAPPING_NAME,
				new String[] {_FIELD_NAME});

		GetFieldMappingIndexRequestExecutorImpl
			getFieldMappingIndexRequestExecutorImpl =
				new GetFieldMappingIndexRequestExecutorImpl() {
					{
						setElasticsearchClientResolver(_elasticsearchFixture);
					}
				};

		GetFieldMappingsRequestBuilder getFieldMappingsRequestBuilder =
			getFieldMappingIndexRequestExecutorImpl.
				createGetFieldMappingsRequestBuilder(
					getFieldMappingIndexRequest);

		GetFieldMappingsRequest getFieldMappingsRequest =
			getFieldMappingsRequestBuilder.request();

		Assert.assertArrayEquals(
			new String[] {_INDEX_NAME}, getFieldMappingsRequest.indices());
		Assert.assertArrayEquals(
			new String[] {_MAPPING_NAME}, getFieldMappingsRequest.types());
		Assert.assertArrayEquals(
			new String[] {_FIELD_NAME}, getFieldMappingsRequest.fields());
	}

	private static final String _FIELD_NAME = "testField";

	private static final String _INDEX_NAME = "test_request_index";

	private static final String _MAPPING_NAME = "testMapping";

	private ElasticsearchFixture _elasticsearchFixture;

}