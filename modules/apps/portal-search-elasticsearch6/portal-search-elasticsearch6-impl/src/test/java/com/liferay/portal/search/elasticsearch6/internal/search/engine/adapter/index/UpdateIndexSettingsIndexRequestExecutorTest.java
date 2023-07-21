/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.index;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.engine.adapter.index.UpdateIndexSettingsIndexRequest;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;

import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequestBuilder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class UpdateIndexSettingsIndexRequestExecutorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_elasticsearchFixture = new ElasticsearchFixture(
			UpdateIndexSettingsIndexRequestExecutorTest.class.getSimpleName());

		_elasticsearchFixture.setUp();

		_indicesOptionsTranslator = new IndicesOptionsTranslatorImpl();
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Test
	public void testIndexRequestTranslation() {
		UpdateIndexSettingsIndexRequest updateIndexSettingsIndexRequest =
			new UpdateIndexSettingsIndexRequest(_INDEX_NAME);

		StringBundler sb = new StringBundler(10);

		sb.append("{\n");
		sb.append("    \"analysis\": {\n");
		sb.append("        \"analyzer\": {\n");
		sb.append("            \"content\": {\n");
		sb.append("                \"tokenizer\": \"whitespace\",\n");
		sb.append("                \"type\": \"custom\"\n");
		sb.append("            }\n");
		sb.append("        }\n");
		sb.append("    }\n");
		sb.append("}");

		updateIndexSettingsIndexRequest.setSettings(sb.toString());

		UpdateIndexSettingsIndexRequestExecutorImpl
			updateIndexSettingsIndexRequestExecutorImpl =
				new UpdateIndexSettingsIndexRequestExecutorImpl() {
					{
						setElasticsearchClientResolver(_elasticsearchFixture);
						setIndicesOptionsTranslator(_indicesOptionsTranslator);
					}
				};

		UpdateSettingsRequestBuilder updateSettingsRequestBuilder =
			updateIndexSettingsIndexRequestExecutorImpl.
				createUpdateSettingsRequestBuilder(
					updateIndexSettingsIndexRequest);

		UpdateSettingsRequest updateSettingsRequest =
			updateSettingsRequestBuilder.request();

		String[] indices = updateSettingsRequest.indices();

		Assert.assertEquals(Arrays.toString(indices), 1, indices.length);
		Assert.assertEquals(_INDEX_NAME, indices[0]);
	}

	private static final String _INDEX_NAME = "test_request_index";

	private ElasticsearchFixture _elasticsearchFixture;
	private IndicesOptionsTranslator _indicesOptionsTranslator;

}