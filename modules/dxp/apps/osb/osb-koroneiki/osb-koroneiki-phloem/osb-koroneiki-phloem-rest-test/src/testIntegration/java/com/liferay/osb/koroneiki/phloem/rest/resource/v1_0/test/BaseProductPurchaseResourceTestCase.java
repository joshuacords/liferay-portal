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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductPurchaseSerDes;
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
public abstract class BaseProductPurchaseResourceTestCase {

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

		_productPurchaseResource.setContextCompany(testCompany);

		ProductPurchaseResource.Builder builder =
			ProductPurchaseResource.builder();

		productPurchaseResource = builder.authentication(
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

		ProductPurchase productPurchase1 = randomProductPurchase();

		String json = objectMapper.writeValueAsString(productPurchase1);

		ProductPurchase productPurchase2 = ProductPurchaseSerDes.toDTO(json);

		Assert.assertTrue(equals(productPurchase1, productPurchase2));
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

		ProductPurchase productPurchase = randomProductPurchase();

		String json1 = objectMapper.writeValueAsString(productPurchase);
		String json2 = ProductPurchaseSerDes.toJSON(productPurchase);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		ProductPurchase productPurchase = randomProductPurchase();

		productPurchase.setAccountKey(regex);
		productPurchase.setKey(regex);
		productPurchase.setProductKey(regex);

		String json = ProductPurchaseSerDes.toJSON(productPurchase);

		Assert.assertFalse(json.contains(regex));

		productPurchase = ProductPurchaseSerDes.toDTO(json);

		Assert.assertEquals(regex, productPurchase.getAccountKey());
		Assert.assertEquals(regex, productPurchase.getKey());
		Assert.assertEquals(regex, productPurchase.getProductKey());
	}

	@Test
	public void testGetAccountAccountKeyProductPurchasesPage()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyProductPurchasesPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyProductPurchasesPage_getIrrelevantAccountKey();

		Page<ProductPurchase> page =
			productPurchaseResource.getAccountAccountKeyProductPurchasesPage(
				accountKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantAccountKey != null) {
			ProductPurchase irrelevantProductPurchase =
				testGetAccountAccountKeyProductPurchasesPage_addProductPurchase(
					irrelevantAccountKey, randomIrrelevantProductPurchase());

			page =
				productPurchaseResource.
					getAccountAccountKeyProductPurchasesPage(
						irrelevantAccountKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProductPurchase),
				(List<ProductPurchase>)page.getItems());
			assertValid(page);
		}

		ProductPurchase productPurchase1 =
			testGetAccountAccountKeyProductPurchasesPage_addProductPurchase(
				accountKey, randomProductPurchase());

		ProductPurchase productPurchase2 =
			testGetAccountAccountKeyProductPurchasesPage_addProductPurchase(
				accountKey, randomProductPurchase());

		page = productPurchaseResource.getAccountAccountKeyProductPurchasesPage(
			accountKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(productPurchase1, productPurchase2),
			(List<ProductPurchase>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyProductPurchasesPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyProductPurchasesPage_getAccountKey();

		ProductPurchase productPurchase1 =
			testGetAccountAccountKeyProductPurchasesPage_addProductPurchase(
				accountKey, randomProductPurchase());

		ProductPurchase productPurchase2 =
			testGetAccountAccountKeyProductPurchasesPage_addProductPurchase(
				accountKey, randomProductPurchase());

		ProductPurchase productPurchase3 =
			testGetAccountAccountKeyProductPurchasesPage_addProductPurchase(
				accountKey, randomProductPurchase());

		Page<ProductPurchase> page1 =
			productPurchaseResource.getAccountAccountKeyProductPurchasesPage(
				accountKey, Pagination.of(1, 2));

		List<ProductPurchase> productPurchases1 =
			(List<ProductPurchase>)page1.getItems();

		Assert.assertEquals(
			productPurchases1.toString(), 2, productPurchases1.size());

		Page<ProductPurchase> page2 =
			productPurchaseResource.getAccountAccountKeyProductPurchasesPage(
				accountKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProductPurchase> productPurchases2 =
			(List<ProductPurchase>)page2.getItems();

		Assert.assertEquals(
			productPurchases2.toString(), 1, productPurchases2.size());

		Page<ProductPurchase> page3 =
			productPurchaseResource.getAccountAccountKeyProductPurchasesPage(
				accountKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(productPurchase1, productPurchase2, productPurchase3),
			(List<ProductPurchase>)page3.getItems());
	}

	protected ProductPurchase
			testGetAccountAccountKeyProductPurchasesPage_addProductPurchase(
				String accountKey, ProductPurchase productPurchase)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyProductPurchasesPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyProductPurchasesPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostAccountAccountKeyProductPurchase() throws Exception {
		ProductPurchase randomProductPurchase = randomProductPurchase();

		ProductPurchase postProductPurchase =
			testPostAccountAccountKeyProductPurchase_addProductPurchase(
				randomProductPurchase);

		assertEquals(randomProductPurchase, postProductPurchase);
		assertValid(postProductPurchase);
	}

	protected ProductPurchase
			testPostAccountAccountKeyProductPurchase_addProductPurchase(
				ProductPurchase productPurchase)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetContactByUuidContactUuidProductPurchasesPage()
		throws Exception {

		String contactUuid =
			testGetContactByUuidContactUuidProductPurchasesPage_getContactUuid();
		String irrelevantContactUuid =
			testGetContactByUuidContactUuidProductPurchasesPage_getIrrelevantContactUuid();

		Page<ProductPurchase> page =
			productPurchaseResource.
				getContactByUuidContactUuidProductPurchasesPage(
					contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantContactUuid != null) {
			ProductPurchase irrelevantProductPurchase =
				testGetContactByUuidContactUuidProductPurchasesPage_addProductPurchase(
					irrelevantContactUuid, randomIrrelevantProductPurchase());

			page =
				productPurchaseResource.
					getContactByUuidContactUuidProductPurchasesPage(
						irrelevantContactUuid, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProductPurchase),
				(List<ProductPurchase>)page.getItems());
			assertValid(page);
		}

		ProductPurchase productPurchase1 =
			testGetContactByUuidContactUuidProductPurchasesPage_addProductPurchase(
				contactUuid, randomProductPurchase());

		ProductPurchase productPurchase2 =
			testGetContactByUuidContactUuidProductPurchasesPage_addProductPurchase(
				contactUuid, randomProductPurchase());

		page =
			productPurchaseResource.
				getContactByUuidContactUuidProductPurchasesPage(
					contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(productPurchase1, productPurchase2),
			(List<ProductPurchase>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContactByUuidContactUuidProductPurchasesPageWithPagination()
		throws Exception {

		String contactUuid =
			testGetContactByUuidContactUuidProductPurchasesPage_getContactUuid();

		ProductPurchase productPurchase1 =
			testGetContactByUuidContactUuidProductPurchasesPage_addProductPurchase(
				contactUuid, randomProductPurchase());

		ProductPurchase productPurchase2 =
			testGetContactByUuidContactUuidProductPurchasesPage_addProductPurchase(
				contactUuid, randomProductPurchase());

		ProductPurchase productPurchase3 =
			testGetContactByUuidContactUuidProductPurchasesPage_addProductPurchase(
				contactUuid, randomProductPurchase());

		Page<ProductPurchase> page1 =
			productPurchaseResource.
				getContactByUuidContactUuidProductPurchasesPage(
					contactUuid, Pagination.of(1, 2));

		List<ProductPurchase> productPurchases1 =
			(List<ProductPurchase>)page1.getItems();

		Assert.assertEquals(
			productPurchases1.toString(), 2, productPurchases1.size());

		Page<ProductPurchase> page2 =
			productPurchaseResource.
				getContactByUuidContactUuidProductPurchasesPage(
					contactUuid, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProductPurchase> productPurchases2 =
			(List<ProductPurchase>)page2.getItems();

		Assert.assertEquals(
			productPurchases2.toString(), 1, productPurchases2.size());

		Page<ProductPurchase> page3 =
			productPurchaseResource.
				getContactByUuidContactUuidProductPurchasesPage(
					contactUuid, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(productPurchase1, productPurchase2, productPurchase3),
			(List<ProductPurchase>)page3.getItems());
	}

	protected ProductPurchase
			testGetContactByUuidContactUuidProductPurchasesPage_addProductPurchase(
				String contactUuid, ProductPurchase productPurchase)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactByUuidContactUuidProductPurchasesPage_getContactUuid()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactByUuidContactUuidProductPurchasesPage_getIrrelevantContactUuid()
		throws Exception {

		return null;
	}

	@Test
	public void testGetProductPurchasesPage() throws Exception {
		Page<ProductPurchase> page =
			productPurchaseResource.getProductPurchasesPage(
				null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		ProductPurchase productPurchase1 =
			testGetProductPurchasesPage_addProductPurchase(
				randomProductPurchase());

		ProductPurchase productPurchase2 =
			testGetProductPurchasesPage_addProductPurchase(
				randomProductPurchase());

		page = productPurchaseResource.getProductPurchasesPage(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			productPurchase1, (List<ProductPurchase>)page.getItems());
		assertContains(
			productPurchase2, (List<ProductPurchase>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductPurchasesPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductPurchase productPurchase1 = randomProductPurchase();

		productPurchase1 = testGetProductPurchasesPage_addProductPurchase(
			productPurchase1);

		for (EntityField entityField : entityFields) {
			Page<ProductPurchase> page =
				productPurchaseResource.getProductPurchasesPage(
					null,
					getFilterString(entityField, "between", productPurchase1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(productPurchase1),
				(List<ProductPurchase>)page.getItems());
		}
	}

	@Test
	public void testGetProductPurchasesPageWithFilterDoubleEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DOUBLE);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductPurchase productPurchase1 =
			testGetProductPurchasesPage_addProductPurchase(
				randomProductPurchase());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		ProductPurchase productPurchase2 =
			testGetProductPurchasesPage_addProductPurchase(
				randomProductPurchase());

		for (EntityField entityField : entityFields) {
			Page<ProductPurchase> page =
				productPurchaseResource.getProductPurchasesPage(
					null, getFilterString(entityField, "eq", productPurchase1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(productPurchase1),
				(List<ProductPurchase>)page.getItems());
		}
	}

	@Test
	public void testGetProductPurchasesPageWithFilterStringEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductPurchase productPurchase1 =
			testGetProductPurchasesPage_addProductPurchase(
				randomProductPurchase());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		ProductPurchase productPurchase2 =
			testGetProductPurchasesPage_addProductPurchase(
				randomProductPurchase());

		for (EntityField entityField : entityFields) {
			Page<ProductPurchase> page =
				productPurchaseResource.getProductPurchasesPage(
					null, getFilterString(entityField, "eq", productPurchase1),
					Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(productPurchase1),
				(List<ProductPurchase>)page.getItems());
		}
	}

	@Test
	public void testGetProductPurchasesPageWithPagination() throws Exception {
		Page<ProductPurchase> totalPage =
			productPurchaseResource.getProductPurchasesPage(
				null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		ProductPurchase productPurchase1 =
			testGetProductPurchasesPage_addProductPurchase(
				randomProductPurchase());

		ProductPurchase productPurchase2 =
			testGetProductPurchasesPage_addProductPurchase(
				randomProductPurchase());

		ProductPurchase productPurchase3 =
			testGetProductPurchasesPage_addProductPurchase(
				randomProductPurchase());

		Page<ProductPurchase> page1 =
			productPurchaseResource.getProductPurchasesPage(
				null, null, Pagination.of(1, totalCount + 2), null);

		List<ProductPurchase> productPurchases1 =
			(List<ProductPurchase>)page1.getItems();

		Assert.assertEquals(
			productPurchases1.toString(), totalCount + 2,
			productPurchases1.size());

		Page<ProductPurchase> page2 =
			productPurchaseResource.getProductPurchasesPage(
				null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<ProductPurchase> productPurchases2 =
			(List<ProductPurchase>)page2.getItems();

		Assert.assertEquals(
			productPurchases2.toString(), 1, productPurchases2.size());

		Page<ProductPurchase> page3 =
			productPurchaseResource.getProductPurchasesPage(
				null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(
			productPurchase1, (List<ProductPurchase>)page3.getItems());
		assertContains(
			productPurchase2, (List<ProductPurchase>)page3.getItems());
		assertContains(
			productPurchase3, (List<ProductPurchase>)page3.getItems());
	}

	@Test
	public void testGetProductPurchasesPageWithSortDateTime() throws Exception {
		testGetProductPurchasesPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, productPurchase1, productPurchase2) -> {
				BeanTestUtil.setProperty(
					productPurchase1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetProductPurchasesPageWithSortDouble() throws Exception {
		testGetProductPurchasesPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, productPurchase1, productPurchase2) -> {
				BeanTestUtil.setProperty(
					productPurchase1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					productPurchase2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetProductPurchasesPageWithSortInteger() throws Exception {
		testGetProductPurchasesPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, productPurchase1, productPurchase2) -> {
				BeanTestUtil.setProperty(
					productPurchase1, entityField.getName(), 0);
				BeanTestUtil.setProperty(
					productPurchase2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetProductPurchasesPageWithSortString() throws Exception {
		testGetProductPurchasesPageWithSort(
			EntityField.Type.STRING,
			(entityField, productPurchase1, productPurchase2) -> {
				Class<?> clazz = productPurchase1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						productPurchase1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						productPurchase2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						productPurchase1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						productPurchase2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						productPurchase1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						productPurchase2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetProductPurchasesPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer
				<EntityField, ProductPurchase, ProductPurchase, Exception>
					unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		ProductPurchase productPurchase1 = randomProductPurchase();
		ProductPurchase productPurchase2 = randomProductPurchase();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(
				entityField, productPurchase1, productPurchase2);
		}

		productPurchase1 = testGetProductPurchasesPage_addProductPurchase(
			productPurchase1);

		productPurchase2 = testGetProductPurchasesPage_addProductPurchase(
			productPurchase2);

		for (EntityField entityField : entityFields) {
			Page<ProductPurchase> ascPage =
				productPurchaseResource.getProductPurchasesPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(productPurchase1, productPurchase2),
				(List<ProductPurchase>)ascPage.getItems());

			Page<ProductPurchase> descPage =
				productPurchaseResource.getProductPurchasesPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(productPurchase2, productPurchase1),
				(List<ProductPurchase>)descPage.getItems());
		}
	}

	protected ProductPurchase testGetProductPurchasesPage_addProductPurchase(
			ProductPurchase productPurchase)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetProductPurchasesPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage()
		throws Exception {

		String domain =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getDomain();
		String irrelevantDomain =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getIrrelevantDomain();
		String entityName =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getEntityName();
		String irrelevantEntityName =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityName();
		String entityId =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getEntityId();
		String irrelevantEntityId =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityId();

		Page<ProductPurchase> page =
			productPurchaseResource.
				getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
					domain, entityName, entityId, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantDomain != null) && (irrelevantEntityName != null) &&
			(irrelevantEntityId != null)) {

			ProductPurchase irrelevantProductPurchase =
				testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_addProductPurchase(
					irrelevantDomain, irrelevantEntityName, irrelevantEntityId,
					randomIrrelevantProductPurchase());

			page =
				productPurchaseResource.
					getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
						irrelevantDomain, irrelevantEntityName,
						irrelevantEntityId, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProductPurchase),
				(List<ProductPurchase>)page.getItems());
			assertValid(page);
		}

		ProductPurchase productPurchase1 =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_addProductPurchase(
				domain, entityName, entityId, randomProductPurchase());

		ProductPurchase productPurchase2 =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_addProductPurchase(
				domain, entityName, entityId, randomProductPurchase());

		page =
			productPurchaseResource.
				getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
					domain, entityName, entityId, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(productPurchase1, productPurchase2),
			(List<ProductPurchase>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProductPurchaseByExternalLinkDomainEntityNameEntityPageWithPagination()
		throws Exception {

		String domain =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getDomain();
		String entityName =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getEntityName();
		String entityId =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getEntityId();

		ProductPurchase productPurchase1 =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_addProductPurchase(
				domain, entityName, entityId, randomProductPurchase());

		ProductPurchase productPurchase2 =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_addProductPurchase(
				domain, entityName, entityId, randomProductPurchase());

		ProductPurchase productPurchase3 =
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_addProductPurchase(
				domain, entityName, entityId, randomProductPurchase());

		Page<ProductPurchase> page1 =
			productPurchaseResource.
				getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
					domain, entityName, entityId, Pagination.of(1, 2));

		List<ProductPurchase> productPurchases1 =
			(List<ProductPurchase>)page1.getItems();

		Assert.assertEquals(
			productPurchases1.toString(), 2, productPurchases1.size());

		Page<ProductPurchase> page2 =
			productPurchaseResource.
				getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
					domain, entityName, entityId, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProductPurchase> productPurchases2 =
			(List<ProductPurchase>)page2.getItems();

		Assert.assertEquals(
			productPurchases2.toString(), 1, productPurchases2.size());

		Page<ProductPurchase> page3 =
			productPurchaseResource.
				getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
					domain, entityName, entityId, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(productPurchase1, productPurchase2, productPurchase3),
			(List<ProductPurchase>)page3.getItems());
	}

	protected ProductPurchase
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_addProductPurchase(
				String domain, String entityName, String entityId,
				ProductPurchase productPurchase)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getDomain()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getIrrelevantDomain()
		throws Exception {

		return null;
	}

	protected String
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getEntityName()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityName()
		throws Exception {

		return null;
	}

	protected String
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getEntityId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetProductPurchaseByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityId()
		throws Exception {

		return null;
	}

	@Test
	public void testDeleteProductPurchase() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLDeleteProductPurchase() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetProductPurchase() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetProductPurchase() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetProductPurchaseNotFound() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPutProductPurchase() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteProductPurchaseProductPurchasePermission()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testPutProductPurchaseProductPurchasePermission()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		ProductPurchase productPurchase =
			testPutProductPurchaseProductPurchasePermission_addProductPurchase();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			productPurchaseResource.
				putProductPurchaseProductPurchasePermissionHttpResponse(
					null, null, null, null));

		assertHttpResponseStatusCode(
			404,
			productPurchaseResource.
				putProductPurchaseProductPurchasePermissionHttpResponse(
					null, null, null, null));
	}

	protected ProductPurchase
			testPutProductPurchaseProductPurchasePermission_addProductPurchase()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertContains(
		ProductPurchase productPurchase,
		List<ProductPurchase> productPurchases) {

		boolean contains = false;

		for (ProductPurchase item : productPurchases) {
			if (equals(productPurchase, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			productPurchases + " does not contain " + productPurchase,
			contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		ProductPurchase productPurchase1, ProductPurchase productPurchase2) {

		Assert.assertTrue(
			productPurchase1 + " does not equal " + productPurchase2,
			equals(productPurchase1, productPurchase2));
	}

	protected void assertEquals(
		List<ProductPurchase> productPurchases1,
		List<ProductPurchase> productPurchases2) {

		Assert.assertEquals(productPurchases1.size(), productPurchases2.size());

		for (int i = 0; i < productPurchases1.size(); i++) {
			ProductPurchase productPurchase1 = productPurchases1.get(i);
			ProductPurchase productPurchase2 = productPurchases2.get(i);

			assertEquals(productPurchase1, productPurchase2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<ProductPurchase> productPurchases1,
		List<ProductPurchase> productPurchases2) {

		Assert.assertEquals(productPurchases1.size(), productPurchases2.size());

		for (ProductPurchase productPurchase1 : productPurchases1) {
			boolean contains = false;

			for (ProductPurchase productPurchase2 : productPurchases2) {
				if (equals(productPurchase1, productPurchase2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				productPurchases2 + " does not contain " + productPurchase1,
				contains);
		}
	}

	protected void assertValid(ProductPurchase productPurchase)
		throws Exception {

		boolean valid = true;

		if (productPurchase.getDateCreated() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accountKey", additionalAssertFieldName)) {
				if (productPurchase.getAccountKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("endDate", additionalAssertFieldName)) {
				if (productPurchase.getEndDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (productPurchase.getExternalLinks() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (productPurchase.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("originalEndDate", additionalAssertFieldName)) {
				if (productPurchase.getOriginalEndDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("perpetual", additionalAssertFieldName)) {
				if (productPurchase.getPerpetual() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("product", additionalAssertFieldName)) {
				if (productPurchase.getProduct() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"productConsumptions", additionalAssertFieldName)) {

				if (productPurchase.getProductConsumptions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productKey", additionalAssertFieldName)) {
				if (productPurchase.getProductKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("properties", additionalAssertFieldName)) {
				if (productPurchase.getProperties() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("quantity", additionalAssertFieldName)) {
				if (productPurchase.getQuantity() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("startDate", additionalAssertFieldName)) {
				if (productPurchase.getStartDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (productPurchase.getStatus() == null) {
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

	protected void assertValid(Page<ProductPurchase> page) {
		boolean valid = false;

		java.util.Collection<ProductPurchase> productPurchases =
			page.getItems();

		int size = productPurchases.size();

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
						ProductPurchase.class)) {

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
		ProductPurchase productPurchase1, ProductPurchase productPurchase2) {

		if (productPurchase1 == productPurchase2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accountKey", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getAccountKey(),
						productPurchase2.getAccountKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getDateCreated(),
						productPurchase2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("endDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getEndDate(),
						productPurchase2.getEndDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getExternalLinks(),
						productPurchase2.getExternalLinks())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getKey(), productPurchase2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("originalEndDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getOriginalEndDate(),
						productPurchase2.getOriginalEndDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("perpetual", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getPerpetual(),
						productPurchase2.getPerpetual())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("product", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getProduct(),
						productPurchase2.getProduct())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"productConsumptions", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						productPurchase1.getProductConsumptions(),
						productPurchase2.getProductConsumptions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productKey", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getProductKey(),
						productPurchase2.getProductKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("properties", additionalAssertFieldName)) {
				if (!equals(
						(Map)productPurchase1.getProperties(),
						(Map)productPurchase2.getProperties())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("quantity", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getQuantity(),
						productPurchase2.getQuantity())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("startDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getStartDate(),
						productPurchase2.getStartDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchase1.getStatus(),
						productPurchase2.getStatus())) {

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

		if (!(_productPurchaseResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_productPurchaseResource;

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
		ProductPurchase productPurchase) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("accountKey")) {
			sb.append("'");
			sb.append(String.valueOf(productPurchase.getAccountKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("dateCreated")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							productPurchase.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							productPurchase.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(productPurchase.getDateCreated()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("endDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							productPurchase.getEndDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(productPurchase.getEndDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(productPurchase.getEndDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("externalLinks")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(productPurchase.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("originalEndDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							productPurchase.getOriginalEndDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							productPurchase.getOriginalEndDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(
					_dateFormat.format(productPurchase.getOriginalEndDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("perpetual")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("product")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productConsumptions")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productKey")) {
			sb.append("'");
			sb.append(String.valueOf(productPurchase.getProductKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("properties")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("quantity")) {
			sb.append(String.valueOf(productPurchase.getQuantity()));

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
							productPurchase.getStartDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							productPurchase.getStartDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(productPurchase.getStartDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("status")) {
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

	protected ProductPurchase randomProductPurchase() throws Exception {
		return new ProductPurchase() {
			{
				accountKey = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				dateCreated = RandomTestUtil.nextDate();
				endDate = RandomTestUtil.nextDate();
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				originalEndDate = RandomTestUtil.nextDate();
				perpetual = RandomTestUtil.randomBoolean();
				productKey = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				quantity = RandomTestUtil.randomInt();
				startDate = RandomTestUtil.nextDate();
			}
		};
	}

	protected ProductPurchase randomIrrelevantProductPurchase()
		throws Exception {

		ProductPurchase randomIrrelevantProductPurchase =
			randomProductPurchase();

		return randomIrrelevantProductPurchase;
	}

	protected ProductPurchase randomPatchProductPurchase() throws Exception {
		return randomProductPurchase();
	}

	protected ProductPurchaseResource productPurchaseResource;
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
		LogFactoryUtil.getLog(BaseProductPurchaseResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private
		com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.
			ProductPurchaseResource _productPurchaseResource;

}