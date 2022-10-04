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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductSerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
 * @author Amos Fong
 * @generated
 */
@Generated("")
public abstract class BaseProductResourceTestCase {

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

		_productResource.setContextCompany(testCompany);

		ProductResource.Builder builder = ProductResource.builder();

		productResource = builder.authentication(
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

		Product product1 = randomProduct();

		String json = objectMapper.writeValueAsString(product1);

		Product product2 = ProductSerDes.toDTO(json);

		Assert.assertTrue(equals(product1, product2));
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

		Product product = randomProduct();

		String json1 = objectMapper.writeValueAsString(product);
		String json2 = ProductSerDes.toJSON(product);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Product product = randomProduct();

		product.setKey(regex);
		product.setName(regex);

		String json = ProductSerDes.toJSON(product);

		Assert.assertFalse(json.contains(regex));

		product = ProductSerDes.toDTO(json);

		Assert.assertEquals(regex, product.getKey());
		Assert.assertEquals(regex, product.getName());
	}

	@Test
	public void testGetProductsPage() throws Exception {
		Page<Product> page = productResource.getProductsPage(
			null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		Product product1 = testGetProductsPage_addProduct(randomProduct());

		Product product2 = testGetProductsPage_addProduct(randomProduct());

		page = productResource.getProductsPage(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(product1, (List<Product>)page.getItems());
		assertContains(product2, (List<Product>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductsPageWithFilterDateTimeEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Product product1 = randomProduct();

		product1 = testGetProductsPage_addProduct(product1);

		for (EntityField entityField : entityFields) {
			Page<Product> page = productResource.getProductsPage(
				null, getFilterString(entityField, "between", product1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(product1),
				(List<Product>)page.getItems());
		}
	}

	@Test
	public void testGetProductsPageWithFilterDoubleEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DOUBLE);

		if (entityFields.isEmpty()) {
			return;
		}

		Product product1 = testGetProductsPage_addProduct(randomProduct());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Product product2 = testGetProductsPage_addProduct(randomProduct());

		for (EntityField entityField : entityFields) {
			Page<Product> page = productResource.getProductsPage(
				null, getFilterString(entityField, "eq", product1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(product1),
				(List<Product>)page.getItems());
		}
	}

	@Test
	public void testGetProductsPageWithFilterStringEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Product product1 = testGetProductsPage_addProduct(randomProduct());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Product product2 = testGetProductsPage_addProduct(randomProduct());

		for (EntityField entityField : entityFields) {
			Page<Product> page = productResource.getProductsPage(
				null, getFilterString(entityField, "eq", product1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(product1),
				(List<Product>)page.getItems());
		}
	}

	@Test
	public void testGetProductsPageWithPagination() throws Exception {
		Page<Product> totalPage = productResource.getProductsPage(
			null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		Product product1 = testGetProductsPage_addProduct(randomProduct());

		Product product2 = testGetProductsPage_addProduct(randomProduct());

		Product product3 = testGetProductsPage_addProduct(randomProduct());

		Page<Product> page1 = productResource.getProductsPage(
			null, null, Pagination.of(1, totalCount + 2), null);

		List<Product> products1 = (List<Product>)page1.getItems();

		Assert.assertEquals(
			products1.toString(), totalCount + 2, products1.size());

		Page<Product> page2 = productResource.getProductsPage(
			null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<Product> products2 = (List<Product>)page2.getItems();

		Assert.assertEquals(products2.toString(), 1, products2.size());

		Page<Product> page3 = productResource.getProductsPage(
			null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(product1, (List<Product>)page3.getItems());
		assertContains(product2, (List<Product>)page3.getItems());
		assertContains(product3, (List<Product>)page3.getItems());
	}

	@Test
	public void testGetProductsPageWithSortDateTime() throws Exception {
		testGetProductsPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, product1, product2) -> {
				BeanTestUtil.setProperty(
					product1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetProductsPageWithSortDouble() throws Exception {
		testGetProductsPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, product1, product2) -> {
				BeanTestUtil.setProperty(product1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(product2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetProductsPageWithSortInteger() throws Exception {
		testGetProductsPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, product1, product2) -> {
				BeanTestUtil.setProperty(product1, entityField.getName(), 0);
				BeanTestUtil.setProperty(product2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetProductsPageWithSortString() throws Exception {
		testGetProductsPageWithSort(
			EntityField.Type.STRING,
			(entityField, product1, product2) -> {
				Class<?> clazz = product1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						product1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						product2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						product1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						product2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						product1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						product2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetProductsPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, Product, Product, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Product product1 = randomProduct();
		Product product2 = randomProduct();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, product1, product2);
		}

		product1 = testGetProductsPage_addProduct(product1);

		product2 = testGetProductsPage_addProduct(product2);

		for (EntityField entityField : entityFields) {
			Page<Product> ascPage = productResource.getProductsPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(product1, product2),
				(List<Product>)ascPage.getItems());

			Page<Product> descPage = productResource.getProductsPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(product2, product1),
				(List<Product>)descPage.getItems());
		}
	}

	protected Product testGetProductsPage_addProduct(Product product)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetProductsPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPostProduct() throws Exception {
		Product randomProduct = randomProduct();

		Product postProduct = testPostProduct_addProduct(randomProduct);

		assertEquals(randomProduct, postProduct);
		assertValid(postProduct);
	}

	protected Product testPostProduct_addProduct(Product product)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetProductByExternalLinkDomainEntityNameEntityPage()
		throws Exception {

		String domain =
			testGetProductByExternalLinkDomainEntityNameEntityPage_getDomain();
		String irrelevantDomain =
			testGetProductByExternalLinkDomainEntityNameEntityPage_getIrrelevantDomain();
		String entityName =
			testGetProductByExternalLinkDomainEntityNameEntityPage_getEntityName();
		String irrelevantEntityName =
			testGetProductByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityName();
		String entityId =
			testGetProductByExternalLinkDomainEntityNameEntityPage_getEntityId();
		String irrelevantEntityId =
			testGetProductByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityId();

		Page<Product> page =
			productResource.getProductByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantDomain != null) && (irrelevantEntityName != null) &&
			(irrelevantEntityId != null)) {

			Product irrelevantProduct =
				testGetProductByExternalLinkDomainEntityNameEntityPage_addProduct(
					irrelevantDomain, irrelevantEntityName, irrelevantEntityId,
					randomIrrelevantProduct());

			page =
				productResource.
					getProductByExternalLinkDomainEntityNameEntityPage(
						irrelevantDomain, irrelevantEntityName,
						irrelevantEntityId, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProduct),
				(List<Product>)page.getItems());
			assertValid(page);
		}

		Product product1 =
			testGetProductByExternalLinkDomainEntityNameEntityPage_addProduct(
				domain, entityName, entityId, randomProduct());

		Product product2 =
			testGetProductByExternalLinkDomainEntityNameEntityPage_addProduct(
				domain, entityName, entityId, randomProduct());

		page =
			productResource.getProductByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(product1, product2), (List<Product>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductByExternalLinkDomainEntityNameEntityPageWithPagination()
		throws Exception {

		String domain =
			testGetProductByExternalLinkDomainEntityNameEntityPage_getDomain();
		String entityName =
			testGetProductByExternalLinkDomainEntityNameEntityPage_getEntityName();
		String entityId =
			testGetProductByExternalLinkDomainEntityNameEntityPage_getEntityId();

		Product product1 =
			testGetProductByExternalLinkDomainEntityNameEntityPage_addProduct(
				domain, entityName, entityId, randomProduct());

		Product product2 =
			testGetProductByExternalLinkDomainEntityNameEntityPage_addProduct(
				domain, entityName, entityId, randomProduct());

		Product product3 =
			testGetProductByExternalLinkDomainEntityNameEntityPage_addProduct(
				domain, entityName, entityId, randomProduct());

		Page<Product> page1 =
			productResource.getProductByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(1, 2));

		List<Product> products1 = (List<Product>)page1.getItems();

		Assert.assertEquals(products1.toString(), 2, products1.size());

		Page<Product> page2 =
			productResource.getProductByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Product> products2 = (List<Product>)page2.getItems();

		Assert.assertEquals(products2.toString(), 1, products2.size());

		Page<Product> page3 =
			productResource.getProductByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(product1, product2, product3),
			(List<Product>)page3.getItems());
	}

	protected Product
			testGetProductByExternalLinkDomainEntityNameEntityPage_addProduct(
				String domain, String entityName, String entityId,
				Product product)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductByExternalLinkDomainEntityNameEntityPage_getDomain()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductByExternalLinkDomainEntityNameEntityPage_getIrrelevantDomain()
		throws Exception {

		return null;
	}

	protected String
			testGetProductByExternalLinkDomainEntityNameEntityPage_getEntityName()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityName()
		throws Exception {

		return null;
	}

	protected String
			testGetProductByExternalLinkDomainEntityNameEntityPage_getEntityId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityId()
		throws Exception {

		return null;
	}

	@Test
	public void testGetProductByNameProductName() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetProductByNameProductName() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetProductByNameProductNameNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testDeleteProduct() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLDeleteProduct() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetProduct() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetProduct() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetProductNotFound() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPutProduct() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteProductProductPermission() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPutProductProductPermission() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		Product product = testPutProductProductPermission_addProduct();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			productResource.putProductProductPermissionHttpResponse(
				null, null, null, null));

		assertHttpResponseStatusCode(
			404,
			productResource.putProductProductPermissionHttpResponse(
				null, null, null, null));
	}

	protected Product testPutProductProductPermission_addProduct()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertContains(Product product, List<Product> products) {
		boolean contains = false;

		for (Product item : products) {
			if (equals(product, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(products + " does not contain " + product, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Product product1, Product product2) {
		Assert.assertTrue(
			product1 + " does not equal " + product2,
			equals(product1, product2));
	}

	protected void assertEquals(
		List<Product> products1, List<Product> products2) {

		Assert.assertEquals(products1.size(), products2.size());

		for (int i = 0; i < products1.size(); i++) {
			Product product1 = products1.get(i);
			Product product2 = products2.get(i);

			assertEquals(product1, product2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Product> products1, List<Product> products2) {

		Assert.assertEquals(products1.size(), products2.size());

		for (Product product1 : products1) {
			boolean contains = false;

			for (Product product2 : products2) {
				if (equals(product1, product2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				products2 + " does not contain " + product1, contains);
		}
	}

	protected void assertValid(Product product) throws Exception {
		boolean valid = true;

		if (product.getDateCreated() == null) {
			valid = false;
		}

		if (product.getDateModified() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (product.getExternalLinks() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (product.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (product.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("properties", additionalAssertFieldName)) {
				if (product.getProperties() == null) {
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

	protected void assertValid(Page<Product> page) {
		boolean valid = false;

		java.util.Collection<Product> products = page.getItems();

		int size = products.size();

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
					com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Product.
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

	protected boolean equals(Product product1, Product product2) {
		if (product1 == product2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getDateCreated(), product2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getDateModified(),
						product2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getExternalLinks(),
						product2.getExternalLinks())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(product1.getKey(), product2.getKey())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						product1.getName(), product2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("properties", additionalAssertFieldName)) {
				if (!equals(
						(Map)product1.getProperties(),
						(Map)product2.getProperties())) {

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

		if (!(_productResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_productResource;

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
		EntityField entityField, String operator, Product product) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("dateCreated")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(product.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(product.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(product.getDateCreated()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("dateModified")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(product.getDateModified(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(product.getDateModified(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(product.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("externalLinks")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(product.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(product.getName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("properties")) {
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

	protected Product randomProduct() throws Exception {
		return new Product() {
			{
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected Product randomIrrelevantProduct() throws Exception {
		Product randomIrrelevantProduct = randomProduct();

		return randomIrrelevantProduct;
	}

	protected Product randomPatchProduct() throws Exception {
		return randomProduct();
	}

	protected ProductResource productResource;
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
		LogFactoryUtil.getLog(BaseProductResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource
		_productResource;

}