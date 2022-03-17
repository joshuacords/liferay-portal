/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductPurchaseViewSerDes;
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

import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public abstract class BaseProductPurchaseViewResourceTestCase {

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

		_productPurchaseViewResource.setContextCompany(testCompany);

		ProductPurchaseViewResource.Builder builder =
			ProductPurchaseViewResource.builder();

		productPurchaseViewResource = builder.authentication(
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

		ProductPurchaseView productPurchaseView1 = randomProductPurchaseView();

		String json = objectMapper.writeValueAsString(productPurchaseView1);

		ProductPurchaseView productPurchaseView2 =
			ProductPurchaseViewSerDes.toDTO(json);

		Assert.assertTrue(equals(productPurchaseView1, productPurchaseView2));
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

		ProductPurchaseView productPurchaseView = randomProductPurchaseView();

		String json1 = objectMapper.writeValueAsString(productPurchaseView);
		String json2 = ProductPurchaseViewSerDes.toJSON(productPurchaseView);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		ProductPurchaseView productPurchaseView = randomProductPurchaseView();

		String json = ProductPurchaseViewSerDes.toJSON(productPurchaseView);

		Assert.assertFalse(json.contains(regex));

		productPurchaseView = ProductPurchaseViewSerDes.toDTO(json);
	}

	@Test
	public void testGetAccountAccountKeyProductProductKeyProductPurchaseView()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetAccountAccountKeyProductProductKeyProductPurchaseView()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetAccountAccountKeyProductProductKeyProductPurchaseViewNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGetProductPurchaseViewsPage() throws Exception {
		Page<ProductPurchaseView> page =
			productPurchaseViewResource.getProductPurchaseViewsPage(
				null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		ProductPurchaseView productPurchaseView1 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				randomProductPurchaseView());

		ProductPurchaseView productPurchaseView2 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				randomProductPurchaseView());

		page = productPurchaseViewResource.getProductPurchaseViewsPage(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			productPurchaseView1, (List<ProductPurchaseView>)page.getItems());
		assertContains(
			productPurchaseView2, (List<ProductPurchaseView>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductPurchaseViewsPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductPurchaseView productPurchaseView1 = randomProductPurchaseView();

		productPurchaseView1 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				productPurchaseView1);

		for (EntityField entityField : entityFields) {
			Page<ProductPurchaseView> page =
				productPurchaseViewResource.getProductPurchaseViewsPage(
					null,
					getFilterString(
						entityField, "between", productPurchaseView1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(productPurchaseView1),
				(List<ProductPurchaseView>)page.getItems());
		}
	}

	@Test
	public void testGetProductPurchaseViewsPageWithFilterDoubleEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DOUBLE);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductPurchaseView productPurchaseView1 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				randomProductPurchaseView());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		ProductPurchaseView productPurchaseView2 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				randomProductPurchaseView());

		for (EntityField entityField : entityFields) {
			Page<ProductPurchaseView> page =
				productPurchaseViewResource.getProductPurchaseViewsPage(
					null,
					getFilterString(entityField, "eq", productPurchaseView1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(productPurchaseView1),
				(List<ProductPurchaseView>)page.getItems());
		}
	}

	@Test
	public void testGetProductPurchaseViewsPageWithFilterStringEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductPurchaseView productPurchaseView1 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				randomProductPurchaseView());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		ProductPurchaseView productPurchaseView2 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				randomProductPurchaseView());

		for (EntityField entityField : entityFields) {
			Page<ProductPurchaseView> page =
				productPurchaseViewResource.getProductPurchaseViewsPage(
					null,
					getFilterString(entityField, "eq", productPurchaseView1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(productPurchaseView1),
				(List<ProductPurchaseView>)page.getItems());
		}
	}

	@Test
	public void testGetProductPurchaseViewsPageWithPagination()
		throws Exception {

		Page<ProductPurchaseView> totalPage =
			productPurchaseViewResource.getProductPurchaseViewsPage(
				null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		ProductPurchaseView productPurchaseView1 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				randomProductPurchaseView());

		ProductPurchaseView productPurchaseView2 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				randomProductPurchaseView());

		ProductPurchaseView productPurchaseView3 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				randomProductPurchaseView());

		Page<ProductPurchaseView> page1 =
			productPurchaseViewResource.getProductPurchaseViewsPage(
				null, null, Pagination.of(1, totalCount + 2), null);

		List<ProductPurchaseView> productPurchaseViews1 =
			(List<ProductPurchaseView>)page1.getItems();

		Assert.assertEquals(
			productPurchaseViews1.toString(), totalCount + 2,
			productPurchaseViews1.size());

		Page<ProductPurchaseView> page2 =
			productPurchaseViewResource.getProductPurchaseViewsPage(
				null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<ProductPurchaseView> productPurchaseViews2 =
			(List<ProductPurchaseView>)page2.getItems();

		Assert.assertEquals(
			productPurchaseViews2.toString(), 1, productPurchaseViews2.size());

		Page<ProductPurchaseView> page3 =
			productPurchaseViewResource.getProductPurchaseViewsPage(
				null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(
			productPurchaseView1, (List<ProductPurchaseView>)page3.getItems());
		assertContains(
			productPurchaseView2, (List<ProductPurchaseView>)page3.getItems());
		assertContains(
			productPurchaseView3, (List<ProductPurchaseView>)page3.getItems());
	}

	@Test
	public void testGetProductPurchaseViewsPageWithSortDateTime()
		throws Exception {

		testGetProductPurchaseViewsPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, productPurchaseView1, productPurchaseView2) -> {
				BeanUtils.setProperty(
					productPurchaseView1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetProductPurchaseViewsPageWithSortDouble()
		throws Exception {

		testGetProductPurchaseViewsPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, productPurchaseView1, productPurchaseView2) -> {
				BeanUtils.setProperty(
					productPurchaseView1, entityField.getName(), 0.1);
				BeanUtils.setProperty(
					productPurchaseView2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetProductPurchaseViewsPageWithSortInteger()
		throws Exception {

		testGetProductPurchaseViewsPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, productPurchaseView1, productPurchaseView2) -> {
				BeanUtils.setProperty(
					productPurchaseView1, entityField.getName(), 0);
				BeanUtils.setProperty(
					productPurchaseView2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetProductPurchaseViewsPageWithSortString()
		throws Exception {

		testGetProductPurchaseViewsPageWithSort(
			EntityField.Type.STRING,
			(entityField, productPurchaseView1, productPurchaseView2) -> {
				Class<?> clazz = productPurchaseView1.getClass();

				String entityFieldName = entityField.getName();

				java.lang.reflect.Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						productPurchaseView1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						productPurchaseView2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanUtils.setProperty(
						productPurchaseView1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanUtils.setProperty(
						productPurchaseView2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanUtils.setProperty(
						productPurchaseView1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanUtils.setProperty(
						productPurchaseView2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetProductPurchaseViewsPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, ProductPurchaseView, ProductPurchaseView,
				 Exception> unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductPurchaseView productPurchaseView1 = randomProductPurchaseView();
		ProductPurchaseView productPurchaseView2 = randomProductPurchaseView();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, productPurchaseView1, productPurchaseView2);
		}

		productPurchaseView1 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				productPurchaseView1);

		productPurchaseView2 =
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				productPurchaseView2);

		for (EntityField entityField : entityFields) {
			Page<ProductPurchaseView> ascPage =
				productPurchaseViewResource.getProductPurchaseViewsPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(productPurchaseView1, productPurchaseView2),
				(List<ProductPurchaseView>)ascPage.getItems());

			Page<ProductPurchaseView> descPage =
				productPurchaseViewResource.getProductPurchaseViewsPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(productPurchaseView2, productPurchaseView1),
				(List<ProductPurchaseView>)descPage.getItems());
		}
	}

	protected ProductPurchaseView
			testGetProductPurchaseViewsPage_addProductPurchaseView(
				ProductPurchaseView productPurchaseView)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetProductPurchaseViewsPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertContains(
		ProductPurchaseView productPurchaseView,
		List<ProductPurchaseView> productPurchaseViews) {

		boolean contains = false;

		for (ProductPurchaseView item : productPurchaseViews) {
			if (equals(productPurchaseView, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			productPurchaseViews + " does not contain " + productPurchaseView,
			contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		ProductPurchaseView productPurchaseView1,
		ProductPurchaseView productPurchaseView2) {

		Assert.assertTrue(
			productPurchaseView1 + " does not equal " + productPurchaseView2,
			equals(productPurchaseView1, productPurchaseView2));
	}

	protected void assertEquals(
		List<ProductPurchaseView> productPurchaseViews1,
		List<ProductPurchaseView> productPurchaseViews2) {

		Assert.assertEquals(
			productPurchaseViews1.size(), productPurchaseViews2.size());

		for (int i = 0; i < productPurchaseViews1.size(); i++) {
			ProductPurchaseView productPurchaseView1 =
				productPurchaseViews1.get(i);
			ProductPurchaseView productPurchaseView2 =
				productPurchaseViews2.get(i);

			assertEquals(productPurchaseView1, productPurchaseView2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<ProductPurchaseView> productPurchaseViews1,
		List<ProductPurchaseView> productPurchaseViews2) {

		Assert.assertEquals(
			productPurchaseViews1.size(), productPurchaseViews2.size());

		for (ProductPurchaseView productPurchaseView1 : productPurchaseViews1) {
			boolean contains = false;

			for (ProductPurchaseView productPurchaseView2 :
					productPurchaseViews2) {

				if (equals(productPurchaseView1, productPurchaseView2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				productPurchaseViews2 + " does not contain " +
					productPurchaseView1,
				contains);
		}
	}

	protected void assertValid(ProductPurchaseView productPurchaseView)
		throws Exception {

		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("product", additionalAssertFieldName)) {
				if (productPurchaseView.getProduct() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"productConsumptions", additionalAssertFieldName)) {

				if (productPurchaseView.getProductConsumptions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productPurchases", additionalAssertFieldName)) {
				if (productPurchaseView.getProductPurchases() == null) {
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

	protected void assertValid(Page<ProductPurchaseView> page) {
		boolean valid = false;

		java.util.Collection<ProductPurchaseView> productPurchaseViews =
			page.getItems();

		int size = productPurchaseViews.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.
						ProductPurchaseView.class)) {

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
		ProductPurchaseView productPurchaseView1,
		ProductPurchaseView productPurchaseView2) {

		if (productPurchaseView1 == productPurchaseView2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("product", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchaseView1.getProduct(),
						productPurchaseView2.getProduct())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"productConsumptions", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						productPurchaseView1.getProductConsumptions(),
						productPurchaseView2.getProductConsumptions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productPurchases", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchaseView1.getProductPurchases(),
						productPurchaseView2.getProductPurchases())) {

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

		Stream<java.lang.reflect.Field> stream = Stream.of(
			ReflectionUtil.getDeclaredFields(clazz));

		return stream.filter(
			field -> !field.isSynthetic()
		).toArray(
			java.lang.reflect.Field[]::new
		);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_productPurchaseViewResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_productPurchaseViewResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator,
		ProductPurchaseView productPurchaseView) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("product")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productConsumptions")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productPurchases")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
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

	protected ProductPurchaseView randomProductPurchaseView() throws Exception {
		return new ProductPurchaseView() {
			{
			}
		};
	}

	protected ProductPurchaseView randomIrrelevantProductPurchaseView()
		throws Exception {

		ProductPurchaseView randomIrrelevantProductPurchaseView =
			randomProductPurchaseView();

		return randomIrrelevantProductPurchaseView;
	}

	protected ProductPurchaseView randomPatchProductPurchaseView()
		throws Exception {

		return randomProductPurchaseView();
	}

	protected ProductPurchaseViewResource productPurchaseViewResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

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
		LogFactoryUtil.getLog(BaseProductPurchaseViewResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.
		ProductPurchaseViewResource _productPurchaseViewResource;

}