/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.osb.provisioning.rest.client.dto.v1_0.AppLicenseKey;
import com.liferay.osb.provisioning.rest.client.dto.v1_0.Type;
import com.liferay.osb.provisioning.rest.client.http.HttpInvoker;
import com.liferay.osb.provisioning.rest.client.pagination.Page;
import com.liferay.osb.provisioning.rest.client.pagination.Pagination;
import com.liferay.osb.provisioning.rest.client.resource.v1_0.AppLicenseKeyResource;
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.AppLicenseKeySerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.search.test.util.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public abstract class BaseAppLicenseKeyResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_appLicenseKeyResource.setContextCompany(testCompany);

		AppLicenseKeyResource.Builder builder = AppLicenseKeyResource.builder();

		appLicenseKeyResource = builder.authentication(
			"test@liferay.com", "test"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		AppLicenseKey appLicenseKey1 = randomAppLicenseKey();

		String json = objectMapper.writeValueAsString(appLicenseKey1);

		AppLicenseKey appLicenseKey2 = AppLicenseKeySerDes.toDTO(json);

		Assert.assertTrue(equals(appLicenseKey1, appLicenseKey2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		AppLicenseKey appLicenseKey = randomAppLicenseKey();

		String json1 = objectMapper.writeValueAsString(appLicenseKey);
		String json2 = AppLicenseKeySerDes.toJSON(appLicenseKey);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		AppLicenseKey appLicenseKey = randomAppLicenseKey();

		appLicenseKey.setDescription(regex);
		appLicenseKey.setHostName(regex);
		appLicenseKey.setIpAddresses(regex);
		appLicenseKey.setKey(regex);
		appLicenseKey.setMacAddresses(regex);
		appLicenseKey.setModifiedUserName(regex);
		appLicenseKey.setModifiedUserUuid(regex);
		appLicenseKey.setOrderId(regex);
		appLicenseKey.setOwner(regex);
		appLicenseKey.setProductId(regex);
		appLicenseKey.setProductName(regex);
		appLicenseKey.setProductVersion(regex);
		appLicenseKey.setUserName(regex);
		appLicenseKey.setUserUuid(regex);

		String json = AppLicenseKeySerDes.toJSON(appLicenseKey);

		Assert.assertFalse(json.contains(regex));

		appLicenseKey = AppLicenseKeySerDes.toDTO(json);

		Assert.assertEquals(regex, appLicenseKey.getDescription());
		Assert.assertEquals(regex, appLicenseKey.getHostName());
		Assert.assertEquals(regex, appLicenseKey.getIpAddresses());
		Assert.assertEquals(regex, appLicenseKey.getKey());
		Assert.assertEquals(regex, appLicenseKey.getMacAddresses());
		Assert.assertEquals(regex, appLicenseKey.getModifiedUserName());
		Assert.assertEquals(regex, appLicenseKey.getModifiedUserUuid());
		Assert.assertEquals(regex, appLicenseKey.getOrderId());
		Assert.assertEquals(regex, appLicenseKey.getOwner());
		Assert.assertEquals(regex, appLicenseKey.getProductId());
		Assert.assertEquals(regex, appLicenseKey.getProductName());
		Assert.assertEquals(regex, appLicenseKey.getProductVersion());
		Assert.assertEquals(regex, appLicenseKey.getUserName());
		Assert.assertEquals(regex, appLicenseKey.getUserUuid());
	}

	@Test
	public void testGetAppLicenseKeysPage() throws Exception {
		Page<AppLicenseKey> page = appLicenseKeyResource.getAppLicenseKeysPage(
			null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		AppLicenseKey appLicenseKey1 =
			testGetAppLicenseKeysPage_addAppLicenseKey(randomAppLicenseKey());

		AppLicenseKey appLicenseKey2 =
			testGetAppLicenseKeysPage_addAppLicenseKey(randomAppLicenseKey());

		page = appLicenseKeyResource.getAppLicenseKeysPage(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(appLicenseKey1, (List<AppLicenseKey>)page.getItems());
		assertContains(appLicenseKey2, (List<AppLicenseKey>)page.getItems());
		assertValid(page, testGetAppLicenseKeysPage_getExpectedActions());
	}

	protected Map<String, Map<String, String>>
			testGetAppLicenseKeysPage_getExpectedActions()
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetAppLicenseKeysPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		AppLicenseKey appLicenseKey1 = randomAppLicenseKey();

		appLicenseKey1 = testGetAppLicenseKeysPage_addAppLicenseKey(
			appLicenseKey1);

		for (EntityField entityField : entityFields) {
			Page<AppLicenseKey> page =
				appLicenseKeyResource.getAppLicenseKeysPage(
					null,
					getFilterString(entityField, "between", appLicenseKey1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(appLicenseKey1),
				(List<AppLicenseKey>)page.getItems());
		}
	}

	@Test
	public void testGetAppLicenseKeysPageWithFilterDoubleEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DOUBLE);

		if (entityFields.isEmpty()) {
			return;
		}

		AppLicenseKey appLicenseKey1 =
			testGetAppLicenseKeysPage_addAppLicenseKey(randomAppLicenseKey());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		AppLicenseKey appLicenseKey2 =
			testGetAppLicenseKeysPage_addAppLicenseKey(randomAppLicenseKey());

		for (EntityField entityField : entityFields) {
			Page<AppLicenseKey> page =
				appLicenseKeyResource.getAppLicenseKeysPage(
					null, getFilterString(entityField, "eq", appLicenseKey1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(appLicenseKey1),
				(List<AppLicenseKey>)page.getItems());
		}
	}

	@Test
	public void testGetAppLicenseKeysPageWithFilterStringEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		AppLicenseKey appLicenseKey1 =
			testGetAppLicenseKeysPage_addAppLicenseKey(randomAppLicenseKey());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		AppLicenseKey appLicenseKey2 =
			testGetAppLicenseKeysPage_addAppLicenseKey(randomAppLicenseKey());

		for (EntityField entityField : entityFields) {
			Page<AppLicenseKey> page =
				appLicenseKeyResource.getAppLicenseKeysPage(
					null, getFilterString(entityField, "eq", appLicenseKey1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(appLicenseKey1),
				(List<AppLicenseKey>)page.getItems());
		}
	}

	@Test
	public void testGetAppLicenseKeysPageWithPagination() throws Exception {
		Page<AppLicenseKey> totalPage =
			appLicenseKeyResource.getAppLicenseKeysPage(null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		AppLicenseKey appLicenseKey1 =
			testGetAppLicenseKeysPage_addAppLicenseKey(randomAppLicenseKey());

		AppLicenseKey appLicenseKey2 =
			testGetAppLicenseKeysPage_addAppLicenseKey(randomAppLicenseKey());

		AppLicenseKey appLicenseKey3 =
			testGetAppLicenseKeysPage_addAppLicenseKey(randomAppLicenseKey());

		Page<AppLicenseKey> page1 = appLicenseKeyResource.getAppLicenseKeysPage(
			null, null, Pagination.of(1, totalCount + 2), null);

		List<AppLicenseKey> appLicenseKeys1 =
			(List<AppLicenseKey>)page1.getItems();

		Assert.assertEquals(
			appLicenseKeys1.toString(), totalCount + 2, appLicenseKeys1.size());

		Page<AppLicenseKey> page2 = appLicenseKeyResource.getAppLicenseKeysPage(
			null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<AppLicenseKey> appLicenseKeys2 =
			(List<AppLicenseKey>)page2.getItems();

		Assert.assertEquals(
			appLicenseKeys2.toString(), 1, appLicenseKeys2.size());

		Page<AppLicenseKey> page3 = appLicenseKeyResource.getAppLicenseKeysPage(
			null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(appLicenseKey1, (List<AppLicenseKey>)page3.getItems());
		assertContains(appLicenseKey2, (List<AppLicenseKey>)page3.getItems());
		assertContains(appLicenseKey3, (List<AppLicenseKey>)page3.getItems());
	}

	@Test
	public void testGetAppLicenseKeysPageWithSortDateTime() throws Exception {
		testGetAppLicenseKeysPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, appLicenseKey1, appLicenseKey2) -> {
				BeanTestUtil.setProperty(
					appLicenseKey1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetAppLicenseKeysPageWithSortDouble() throws Exception {
		testGetAppLicenseKeysPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, appLicenseKey1, appLicenseKey2) -> {
				BeanTestUtil.setProperty(
					appLicenseKey1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					appLicenseKey2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetAppLicenseKeysPageWithSortInteger() throws Exception {
		testGetAppLicenseKeysPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, appLicenseKey1, appLicenseKey2) -> {
				BeanTestUtil.setProperty(
					appLicenseKey1, entityField.getName(), 0);
				BeanTestUtil.setProperty(
					appLicenseKey2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetAppLicenseKeysPageWithSortString() throws Exception {
		testGetAppLicenseKeysPageWithSort(
			EntityField.Type.STRING,
			(entityField, appLicenseKey1, appLicenseKey2) -> {
				Class<?> clazz = appLicenseKey1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						appLicenseKey1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						appLicenseKey2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						appLicenseKey1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						appLicenseKey2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						appLicenseKey1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						appLicenseKey2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetAppLicenseKeysPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, AppLicenseKey, AppLicenseKey, Exception>
					unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		AppLicenseKey appLicenseKey1 = randomAppLicenseKey();
		AppLicenseKey appLicenseKey2 = randomAppLicenseKey();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, appLicenseKey1, appLicenseKey2);
		}

		appLicenseKey1 = testGetAppLicenseKeysPage_addAppLicenseKey(
			appLicenseKey1);

		appLicenseKey2 = testGetAppLicenseKeysPage_addAppLicenseKey(
			appLicenseKey2);

		for (EntityField entityField : entityFields) {
			Page<AppLicenseKey> ascPage =
				appLicenseKeyResource.getAppLicenseKeysPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(appLicenseKey1, appLicenseKey2),
				(List<AppLicenseKey>)ascPage.getItems());

			Page<AppLicenseKey> descPage =
				appLicenseKeyResource.getAppLicenseKeysPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(appLicenseKey2, appLicenseKey1),
				(List<AppLicenseKey>)descPage.getItems());
		}
	}

	protected AppLicenseKey testGetAppLicenseKeysPage_addAppLicenseKey(
			AppLicenseKey appLicenseKey)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetAppLicenseKeysPage() throws Exception {
		GraphQLField graphQLField = new GraphQLField(
			"appLicenseKeys",
			new HashMap<String, Object>() {
				{
					put("page", 1);
					put("pageSize", 10);
				}
			},
			new GraphQLField("items", getGraphQLFields()),
			new GraphQLField("page"), new GraphQLField("totalCount"));

		JSONObject appLicenseKeysJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/appLicenseKeys");

		long totalCount = appLicenseKeysJSONObject.getLong("totalCount");

		AppLicenseKey appLicenseKey1 =
			testGraphQLGetAppLicenseKeysPage_addAppLicenseKey();
		AppLicenseKey appLicenseKey2 =
			testGraphQLGetAppLicenseKeysPage_addAppLicenseKey();

		appLicenseKeysJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/appLicenseKeys");

		Assert.assertEquals(
			totalCount + 2, appLicenseKeysJSONObject.getLong("totalCount"));

		assertContains(
			appLicenseKey1,
			Arrays.asList(
				AppLicenseKeySerDes.toDTOs(
					appLicenseKeysJSONObject.getString("items"))));
		assertContains(
			appLicenseKey2,
			Arrays.asList(
				AppLicenseKeySerDes.toDTOs(
					appLicenseKeysJSONObject.getString("items"))));
	}

	protected AppLicenseKey testGraphQLGetAppLicenseKeysPage_addAppLicenseKey()
		throws Exception {

		return testGraphQLAppLicenseKey_addAppLicenseKey();
	}

	@Test
	public void testPostAppLicenseKey() throws Exception {
		AppLicenseKey randomAppLicenseKey = randomAppLicenseKey();

		AppLicenseKey postAppLicenseKey =
			testPostAppLicenseKey_addAppLicenseKey(randomAppLicenseKey);

		assertEquals(randomAppLicenseKey, postAppLicenseKey);
		assertValid(postAppLicenseKey);
	}

	protected AppLicenseKey testPostAppLicenseKey_addAppLicenseKey(
			AppLicenseKey appLicenseKey)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutAppLicenseKeyActivate() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		AppLicenseKey appLicenseKey =
			testPutAppLicenseKeyActivate_addAppLicenseKey();

		assertHttpResponseStatusCode(
			204,
			appLicenseKeyResource.putAppLicenseKeyActivateHttpResponse(
				null, null, null));

		assertHttpResponseStatusCode(
			404,
			appLicenseKeyResource.putAppLicenseKeyActivateHttpResponse(
				null, null, null));
	}

	protected AppLicenseKey testPutAppLicenseKeyActivate_addAppLicenseKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutAppLicenseKeyDeactivate() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		AppLicenseKey appLicenseKey =
			testPutAppLicenseKeyDeactivate_addAppLicenseKey();

		assertHttpResponseStatusCode(
			204,
			appLicenseKeyResource.putAppLicenseKeyDeactivateHttpResponse(
				null, null, null));

		assertHttpResponseStatusCode(
			404,
			appLicenseKeyResource.putAppLicenseKeyDeactivateHttpResponse(
				null, null, null));
	}

	protected AppLicenseKey testPutAppLicenseKeyDeactivate_addAppLicenseKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetAppLicenseKey() throws Exception {
		AppLicenseKey postAppLicenseKey =
			testGetAppLicenseKey_addAppLicenseKey();

		AppLicenseKey getAppLicenseKey = appLicenseKeyResource.getAppLicenseKey(
			postAppLicenseKey.getId());

		assertEquals(postAppLicenseKey, getAppLicenseKey);
		assertValid(getAppLicenseKey);
	}

	protected AppLicenseKey testGetAppLicenseKey_addAppLicenseKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetAppLicenseKey() throws Exception {
		AppLicenseKey appLicenseKey =
			testGraphQLGetAppLicenseKey_addAppLicenseKey();

		Assert.assertTrue(
			equals(
				appLicenseKey,
				AppLicenseKeySerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"appLicenseKey",
								new HashMap<String, Object>() {
									{
										put(
											"appLicenseKeyId",
											appLicenseKey.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/appLicenseKey"))));
	}

	@Test
	public void testGraphQLGetAppLicenseKeyNotFound() throws Exception {
		Long irrelevantAppLicenseKeyId = RandomTestUtil.randomLong();

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"appLicenseKey",
						new HashMap<String, Object>() {
							{
								put(
									"appLicenseKeyId",
									irrelevantAppLicenseKeyId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected AppLicenseKey testGraphQLGetAppLicenseKey_addAppLicenseKey()
		throws Exception {

		return testGraphQLAppLicenseKey_addAppLicenseKey();
	}

	@Test
	public void testGetAppLicenseKeyDownload() throws Exception {
		Assert.assertTrue(false);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected AppLicenseKey testGraphQLAppLicenseKey_addAppLicenseKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		AppLicenseKey appLicenseKey, List<AppLicenseKey> appLicenseKeys) {

		boolean contains = false;

		for (AppLicenseKey item : appLicenseKeys) {
			if (equals(appLicenseKey, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			appLicenseKeys + " does not contain " + appLicenseKey, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		AppLicenseKey appLicenseKey1, AppLicenseKey appLicenseKey2) {

		Assert.assertTrue(
			appLicenseKey1 + " does not equal " + appLicenseKey2,
			equals(appLicenseKey1, appLicenseKey2));
	}

	protected void assertEquals(
		List<AppLicenseKey> appLicenseKeys1,
		List<AppLicenseKey> appLicenseKeys2) {

		Assert.assertEquals(appLicenseKeys1.size(), appLicenseKeys2.size());

		for (int i = 0; i < appLicenseKeys1.size(); i++) {
			AppLicenseKey appLicenseKey1 = appLicenseKeys1.get(i);
			AppLicenseKey appLicenseKey2 = appLicenseKeys2.get(i);

			assertEquals(appLicenseKey1, appLicenseKey2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<AppLicenseKey> appLicenseKeys1,
		List<AppLicenseKey> appLicenseKeys2) {

		Assert.assertEquals(appLicenseKeys1.size(), appLicenseKeys2.size());

		for (AppLicenseKey appLicenseKey1 : appLicenseKeys1) {
			boolean contains = false;

			for (AppLicenseKey appLicenseKey2 : appLicenseKeys2) {
				if (equals(appLicenseKey1, appLicenseKey2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				appLicenseKeys2 + " does not contain " + appLicenseKey1,
				contains);
		}
	}

	protected void assertValid(AppLicenseKey appLicenseKey) throws Exception {
		boolean valid = true;

		if (appLicenseKey.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("active", additionalAssertFieldName)) {
				if (appLicenseKey.getActive() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("complimentary", additionalAssertFieldName)) {
				if (appLicenseKey.getComplimentary() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (appLicenseKey.getCreateDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (appLicenseKey.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("expirationDate", additionalAssertFieldName)) {
				if (appLicenseKey.getExpirationDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("hostName", additionalAssertFieldName)) {
				if (appLicenseKey.getHostName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("ipAddresses", additionalAssertFieldName)) {
				if (appLicenseKey.getIpAddresses() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (appLicenseKey.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("licenseType", additionalAssertFieldName)) {
				if (appLicenseKey.getLicenseType() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("macAddresses", additionalAssertFieldName)) {
				if (appLicenseKey.getMacAddresses() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (appLicenseKey.getModifiedDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifiedUserName", additionalAssertFieldName)) {
				if (appLicenseKey.getModifiedUserName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifiedUserUuid", additionalAssertFieldName)) {
				if (appLicenseKey.getModifiedUserUuid() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("orderId", additionalAssertFieldName)) {
				if (appLicenseKey.getOrderId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("owner", additionalAssertFieldName)) {
				if (appLicenseKey.getOwner() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productId", additionalAssertFieldName)) {
				if (appLicenseKey.getProductId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productName", additionalAssertFieldName)) {
				if (appLicenseKey.getProductName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productVersion", additionalAssertFieldName)) {
				if (appLicenseKey.getProductVersion() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("startDate", additionalAssertFieldName)) {
				if (appLicenseKey.getStartDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("userName", additionalAssertFieldName)) {
				if (appLicenseKey.getUserName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("userUuid", additionalAssertFieldName)) {
				if (appLicenseKey.getUserUuid() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<AppLicenseKey> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<AppLicenseKey> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<AppLicenseKey> appLicenseKeys = page.getItems();

		int size = appLicenseKeys.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);

		Map<String, Map<String, String>> actions = page.getActions();

		for (String key : expectedActions.keySet()) {
			Map action = actions.get(key);

			Assert.assertNotNull(key + " does not contain an action", action);

			Map expectedAction = expectedActions.get(key);

			Assert.assertEquals(
				expectedAction.get("method"), action.get("method"));
			Assert.assertEquals(expectedAction.get("href"), action.get("href"));
		}
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					com.liferay.osb.provisioning.rest.dto.v1_0.AppLicenseKey.
						class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(
			java.lang.reflect.Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(
		AppLicenseKey appLicenseKey1, AppLicenseKey appLicenseKey2) {

		if (appLicenseKey1 == appLicenseKey2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("active", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getActive(),
						appLicenseKey2.getActive())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("complimentary", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getComplimentary(),
						appLicenseKey2.getComplimentary())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getCreateDate(),
						appLicenseKey2.getCreateDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getDescription(),
						appLicenseKey2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("expirationDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getExpirationDate(),
						appLicenseKey2.getExpirationDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("hostName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getHostName(),
						appLicenseKey2.getHostName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getId(), appLicenseKey2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("ipAddresses", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getIpAddresses(),
						appLicenseKey2.getIpAddresses())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getKey(), appLicenseKey2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("licenseType", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getLicenseType(),
						appLicenseKey2.getLicenseType())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("macAddresses", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getMacAddresses(),
						appLicenseKey2.getMacAddresses())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getModifiedDate(),
						appLicenseKey2.getModifiedDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modifiedUserName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getModifiedUserName(),
						appLicenseKey2.getModifiedUserName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modifiedUserUuid", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getModifiedUserUuid(),
						appLicenseKey2.getModifiedUserUuid())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("orderId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getOrderId(),
						appLicenseKey2.getOrderId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("owner", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getOwner(), appLicenseKey2.getOwner())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getProductId(),
						appLicenseKey2.getProductId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getProductName(),
						appLicenseKey2.getProductName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productVersion", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getProductVersion(),
						appLicenseKey2.getProductVersion())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("startDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getStartDate(),
						appLicenseKey2.getStartDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getUserName(),
						appLicenseKey2.getUserName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userUuid", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						appLicenseKey1.getUserUuid(),
						appLicenseKey2.getUserUuid())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected java.lang.reflect.Field[] getDeclaredFields(Class clazz)
		throws Exception {

		return TransformUtil.transform(
			ReflectionUtil.getDeclaredFields(clazz),
			field -> {
				if (field.isSynthetic()) {
					return null;
				}

				return field;
			},
			java.lang.reflect.Field.class);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_appLicenseKeyResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_appLicenseKeyResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		if (entityModel == null) {
			return Collections.emptyList();
		}

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		return TransformUtil.transform(
			getEntityFields(),
			entityField -> {
				if (!Objects.equals(entityField.getType(), type) ||
					ArrayUtil.contains(
						getIgnoredEntityFieldNames(), entityField.getName())) {

					return null;
				}

				return entityField;
			});
	}

	protected String getFilterString(
		EntityField entityField, String operator, AppLicenseKey appLicenseKey) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("active")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("complimentary")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("createDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							appLicenseKey.getCreateDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							appLicenseKey.getCreateDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(appLicenseKey.getCreateDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("expirationDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							appLicenseKey.getExpirationDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							appLicenseKey.getExpirationDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(
					_dateFormat.format(appLicenseKey.getExpirationDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("hostName")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getHostName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("ipAddresses")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getIpAddresses()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("licenseType")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("macAddresses")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getMacAddresses()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("modifiedDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							appLicenseKey.getModifiedDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							appLicenseKey.getModifiedDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(appLicenseKey.getModifiedDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("modifiedUserName")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getModifiedUserName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("modifiedUserUuid")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getModifiedUserUuid()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("orderId")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getOrderId()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("owner")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getOwner()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("productId")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getProductId()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("productName")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getProductName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("productVersion")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getProductVersion()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("startDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							appLicenseKey.getStartDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(appLicenseKey.getStartDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(appLicenseKey.getStartDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("userName")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getUserName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("userUuid")) {
			sb.append("'");
			sb.append(String.valueOf(appLicenseKey.getUserUuid()));
			sb.append("'");

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected AppLicenseKey randomAppLicenseKey() throws Exception {
		return new AppLicenseKey() {
			{
				active = RandomTestUtil.randomBoolean();
				complimentary = RandomTestUtil.randomBoolean();
				createDate = RandomTestUtil.nextDate();
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				expirationDate = RandomTestUtil.nextDate();
				hostName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				id = RandomTestUtil.randomLong();
				ipAddresses = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				macAddresses = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				modifiedDate = RandomTestUtil.nextDate();
				modifiedUserName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				modifiedUserUuid = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				orderId = StringUtil.toLowerCase(RandomTestUtil.randomString());
				owner = StringUtil.toLowerCase(RandomTestUtil.randomString());
				productId = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				productName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				productVersion = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				startDate = RandomTestUtil.nextDate();
				userName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				userUuid = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected AppLicenseKey randomIrrelevantAppLicenseKey() throws Exception {
		AppLicenseKey randomIrrelevantAppLicenseKey = randomAppLicenseKey();

		return randomIrrelevantAppLicenseKey;
	}

	protected AppLicenseKey randomPatchAppLicenseKey() throws Exception {
		return randomAppLicenseKey();
	}

	protected AppLicenseKeyResource appLicenseKeyResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected static class BeanTestUtil {

		public static void copyProperties(Object source, Object target)
			throws Exception {

			Class<?> sourceClass = _getSuperClass(source.getClass());

			Class<?> targetClass = target.getClass();

			for (java.lang.reflect.Field field :
					sourceClass.getDeclaredFields()) {

				if (field.isSynthetic()) {
					continue;
				}

				Method getMethod = _getMethod(
					sourceClass, field.getName(), "get");

				Method setMethod = _getMethod(
					targetClass, field.getName(), "set",
					getMethod.getReturnType());

				setMethod.invoke(target, getMethod.invoke(source));
			}
		}

		public static boolean hasProperty(Object bean, String name) {
			Method setMethod = _getMethod(
				bean.getClass(), "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod != null) {
				return true;
			}

			return false;
		}

		public static void setProperty(Object bean, String name, Object value)
			throws Exception {

			Class<?> clazz = bean.getClass();

			Method setMethod = _getMethod(
				clazz, "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod == null) {
				throw new NoSuchMethodException();
			}

			Class<?>[] parameterTypes = setMethod.getParameterTypes();

			setMethod.invoke(bean, _translateValue(parameterTypes[0], value));
		}

		private static Method _getMethod(Class<?> clazz, String name) {
			for (Method method : clazz.getMethods()) {
				if (name.equals(method.getName()) &&
					(method.getParameterCount() == 1) &&
					_parameterTypes.contains(method.getParameterTypes()[0])) {

					return method;
				}
			}

			return null;
		}

		private static Method _getMethod(
				Class<?> clazz, String fieldName, String prefix,
				Class<?>... parameterTypes)
			throws Exception {

			return clazz.getMethod(
				prefix + StringUtil.upperCaseFirstLetter(fieldName),
				parameterTypes);
		}

		private static Class<?> _getSuperClass(Class<?> clazz) {
			Class<?> superClass = clazz.getSuperclass();

			if ((superClass == null) || (superClass == Object.class)) {
				return clazz;
			}

			return superClass;
		}

		private static Object _translateValue(
			Class<?> parameterType, Object value) {

			if ((value instanceof Integer) &&
				parameterType.equals(Long.class)) {

				Integer intValue = (Integer)value;

				return intValue.longValue();
			}

			return value;
		}

		private static final Set<Class<?>> _parameterTypes = new HashSet<>(
			Arrays.asList(
				Boolean.class, Date.class, Double.class, Integer.class,
				Long.class, Map.class, String.class));

	}

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseAppLicenseKeyResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private
		com.liferay.osb.provisioning.rest.resource.v1_0.AppLicenseKeyResource
			_appLicenseKeyResource;

}