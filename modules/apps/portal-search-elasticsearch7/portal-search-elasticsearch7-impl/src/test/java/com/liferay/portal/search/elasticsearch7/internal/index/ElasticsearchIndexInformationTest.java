/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch7.internal.util.ResourceUtil;
import com.liferay.portal.search.test.util.AssertUtils;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * @author Adam Brandizzi
 */
public class ElasticsearchIndexInformationTest {

	@ClassRule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() throws Exception {
		_elasticsearchFixture = new ElasticsearchFixture(
			ElasticsearchIndexInformationTest.class.getSimpleName());

		_elasticsearchFixture.setUp();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Before
	public void setUp() throws Exception {
		_companyIndexFactoryFixture = createCompanyIndexFactoryFixture(
			_elasticsearchFixture);

		_elasticsearchIndexInformation = createElasticsearchIndexInformation(
			_elasticsearchFixture);
	}

	@Test
	public void testGetCompanyIndexName() throws Exception {
		_companyIndexFactoryFixture.createIndices();

		long companyId = RandomTestUtil.randomLong();

		Assert.assertEquals(
			getIndexNameBuilder(companyId),
			_elasticsearchIndexInformation.getCompanyIndexName(companyId));
	}

	@Test
	public void testGetFieldMappings() throws Exception {
		_companyIndexFactoryFixture.createIndices();

		String fieldMappings = _elasticsearchIndexInformation.getFieldMappings(
			_companyIndexFactoryFixture.getIndexName());

		JSONObject expectedJSONObject = loadJSONObject(
			testName.getMethodName());
		JSONObject actualJSONObject = _jsonFactory.createJSONObject(
			fieldMappings);

		AssertUtils.assertEquals("", expectedJSONObject, actualJSONObject);
	}

	@Test
	public void testGetIndexNames() throws Exception {
		_companyIndexFactoryFixture.createIndices();

		String[] indexNames = _elasticsearchIndexInformation.getIndexNames();

		Assert.assertEquals(indexNames.toString(), 1, indexNames.length);
		Assert.assertEquals(
			_companyIndexFactoryFixture.getIndexName(), indexNames[0]);
	}

	@Rule
	public TestName testName = new TestName();

	protected static ElasticsearchIndexInformation
		createElasticsearchIndexInformation(
			ElasticsearchFixture elasticsearchFixture) {

		return new ElasticsearchIndexInformation() {
			{
				elasticsearchClientResolver = elasticsearchFixture;
				indexNameBuilder =
					ElasticsearchIndexInformationTest::getIndexNameBuilder;
			}
		};
	}

	protected static String getIndexNameBuilder(long companyId) {
		return "test-" + companyId;
	}

	protected CompanyIndexFactoryFixture createCompanyIndexFactoryFixture(
		ElasticsearchFixture elasticsearchFixture) {

		return new CompanyIndexFactoryFixture(
			elasticsearchFixture, testName.getMethodName());
	}

	protected JSONObject loadJSONObject(String suffix) throws Exception {
		String json = ResourceUtil.getResourceAsString(
			getClass(),
			"ElasticsearchIndexInformationTest-" + suffix + ".json");

		return _jsonFactory.createJSONObject(json);
	}

	private static ElasticsearchFixture _elasticsearchFixture;

	private CompanyIndexFactoryFixture _companyIndexFactoryFixture;
	private ElasticsearchIndexInformation _elasticsearchIndexInformation;
	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

}