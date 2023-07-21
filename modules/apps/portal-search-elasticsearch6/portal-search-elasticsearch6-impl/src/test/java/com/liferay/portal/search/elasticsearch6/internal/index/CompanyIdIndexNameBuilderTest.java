/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.index;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchFixture;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Collections;

import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.indices.InvalidIndexNameException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class CompanyIdIndexNameBuilderTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_elasticsearchFixture = new ElasticsearchFixture(
			CompanyIdIndexNameBuilderTest.class.getSimpleName());

		_elasticsearchFixture.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Test
	public void testActivate() throws Exception {
		CompanyIdIndexNameBuilder companyIdIndexNameBuilder =
			new CompanyIdIndexNameBuilder();

		companyIdIndexNameBuilder.activate(
			Collections.singletonMap("indexNamePrefix", (Object)"UPPERCASE"));

		Assert.assertEquals(
			"uppercase0", companyIdIndexNameBuilder.getIndexName(0));
	}

	@Test
	public void testIndexNamePrefixBlank() throws Exception {
		assertIndexNamePrefix(StringPool.BLANK, StringPool.BLANK);
	}

	@Test(expected = InvalidIndexNameException.class)
	public void testIndexNamePrefixInvalidIndexName() throws Exception {
		createIndices(StringPool.STAR, 0);
	}

	@Test
	public void testIndexNamePrefixNull() throws Exception {
		assertIndexNamePrefix(null, StringPool.BLANK);
	}

	@Test
	public void testIndexNamePrefixTrim() throws Exception {
		String string = RandomTestUtil.randomString();

		assertIndexNamePrefix(
			StringPool.TAB + string + StringPool.SPACE,
			StringUtil.toLowerCase(string));
	}

	@Test
	public void testIndexNamePrefixUppercase() throws Exception {
		assertIndexNamePrefix("UPPERCASE", "uppercase");
	}

	protected void assertIndexNamePrefix(
			String indexNamePrefix, String expectedIndexNamePrefix)
		throws Exception {

		long companyId = RandomTestUtil.randomLong();

		createIndices(indexNamePrefix, companyId);

		String expectedIndexName = expectedIndexNamePrefix + companyId;

		GetIndexResponse getIndexResponse = _elasticsearchFixture.getIndex(
			expectedIndexName);

		Assert.assertArrayEquals(
			new String[] {expectedIndexName}, getIndexResponse.indices());
	}

	protected void createIndices(String indexNamePrefix, long companyId)
		throws Exception {

		final CompanyIdIndexNameBuilder companyIdIndexNameBuilder =
			new CompanyIdIndexNameBuilder();

		companyIdIndexNameBuilder.setIndexNamePrefix(indexNamePrefix);

		CompanyIndexFactory companyIndexFactory = new CompanyIndexFactory() {
			{
				indexNameBuilder = companyIdIndexNameBuilder;
				jsonFactory = new JSONFactoryImpl();
			}
		};

		AdminClient adminClient = _elasticsearchFixture.getAdminClient();

		companyIndexFactory.createIndices(adminClient, companyId);
	}

	private ElasticsearchFixture _elasticsearchFixture;

}