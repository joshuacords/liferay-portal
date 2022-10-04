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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactSerDes;
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
public abstract class BaseContactResourceTestCase {

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

		_contactResource.setContextCompany(testCompany);

		ContactResource.Builder builder = ContactResource.builder();

		contactResource = builder.authentication(
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

		Contact contact1 = randomContact();

		String json = objectMapper.writeValueAsString(contact1);

		Contact contact2 = ContactSerDes.toDTO(json);

		Assert.assertTrue(equals(contact1, contact2));
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

		Contact contact = randomContact();

		String json1 = objectMapper.writeValueAsString(contact);
		String json2 = ContactSerDes.toJSON(contact);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Contact contact = randomContact();

		contact.setEmailAddress(regex);
		contact.setFirstName(regex);
		contact.setKey(regex);
		contact.setLanguageId(regex);
		contact.setLastName(regex);
		contact.setMiddleName(regex);
		contact.setUuid(regex);

		String json = ContactSerDes.toJSON(contact);

		Assert.assertFalse(json.contains(regex));

		contact = ContactSerDes.toDTO(json);

		Assert.assertEquals(regex, contact.getEmailAddress());
		Assert.assertEquals(regex, contact.getFirstName());
		Assert.assertEquals(regex, contact.getKey());
		Assert.assertEquals(regex, contact.getLanguageId());
		Assert.assertEquals(regex, contact.getLastName());
		Assert.assertEquals(regex, contact.getMiddleName());
		Assert.assertEquals(regex, contact.getUuid());
	}

	@Test
	public void testGetAccountAccountKeyContactsPage() throws Exception {
		String accountKey =
			testGetAccountAccountKeyContactsPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyContactsPage_getIrrelevantAccountKey();

		Page<Contact> page = contactResource.getAccountAccountKeyContactsPage(
			accountKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantAccountKey != null) {
			Contact irrelevantContact =
				testGetAccountAccountKeyContactsPage_addContact(
					irrelevantAccountKey, randomIrrelevantContact());

			page = contactResource.getAccountAccountKeyContactsPage(
				irrelevantAccountKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContact),
				(List<Contact>)page.getItems());
			assertValid(page);
		}

		Contact contact1 = testGetAccountAccountKeyContactsPage_addContact(
			accountKey, randomContact());

		Contact contact2 = testGetAccountAccountKeyContactsPage_addContact(
			accountKey, randomContact());

		page = contactResource.getAccountAccountKeyContactsPage(
			accountKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contact1, contact2), (List<Contact>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyContactsPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyContactsPage_getAccountKey();

		Contact contact1 = testGetAccountAccountKeyContactsPage_addContact(
			accountKey, randomContact());

		Contact contact2 = testGetAccountAccountKeyContactsPage_addContact(
			accountKey, randomContact());

		Contact contact3 = testGetAccountAccountKeyContactsPage_addContact(
			accountKey, randomContact());

		Page<Contact> page1 = contactResource.getAccountAccountKeyContactsPage(
			accountKey, Pagination.of(1, 2));

		List<Contact> contacts1 = (List<Contact>)page1.getItems();

		Assert.assertEquals(contacts1.toString(), 2, contacts1.size());

		Page<Contact> page2 = contactResource.getAccountAccountKeyContactsPage(
			accountKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Contact> contacts2 = (List<Contact>)page2.getItems();

		Assert.assertEquals(contacts2.toString(), 1, contacts2.size());

		Page<Contact> page3 = contactResource.getAccountAccountKeyContactsPage(
			accountKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contact1, contact2, contact3),
			(List<Contact>)page3.getItems());
	}

	protected Contact testGetAccountAccountKeyContactsPage_addContact(
			String accountKey, Contact contact)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetAccountAccountKeyContactsPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyContactsPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testGetAccountAccountKeyCustomerContactsPage()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyCustomerContactsPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyCustomerContactsPage_getIrrelevantAccountKey();

		Page<Contact> page =
			contactResource.getAccountAccountKeyCustomerContactsPage(
				accountKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantAccountKey != null) {
			Contact irrelevantContact =
				testGetAccountAccountKeyCustomerContactsPage_addContact(
					irrelevantAccountKey, randomIrrelevantContact());

			page = contactResource.getAccountAccountKeyCustomerContactsPage(
				irrelevantAccountKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContact),
				(List<Contact>)page.getItems());
			assertValid(page);
		}

		Contact contact1 =
			testGetAccountAccountKeyCustomerContactsPage_addContact(
				accountKey, randomContact());

		Contact contact2 =
			testGetAccountAccountKeyCustomerContactsPage_addContact(
				accountKey, randomContact());

		page = contactResource.getAccountAccountKeyCustomerContactsPage(
			accountKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contact1, contact2), (List<Contact>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyCustomerContactsPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyCustomerContactsPage_getAccountKey();

		Contact contact1 =
			testGetAccountAccountKeyCustomerContactsPage_addContact(
				accountKey, randomContact());

		Contact contact2 =
			testGetAccountAccountKeyCustomerContactsPage_addContact(
				accountKey, randomContact());

		Contact contact3 =
			testGetAccountAccountKeyCustomerContactsPage_addContact(
				accountKey, randomContact());

		Page<Contact> page1 =
			contactResource.getAccountAccountKeyCustomerContactsPage(
				accountKey, Pagination.of(1, 2));

		List<Contact> contacts1 = (List<Contact>)page1.getItems();

		Assert.assertEquals(contacts1.toString(), 2, contacts1.size());

		Page<Contact> page2 =
			contactResource.getAccountAccountKeyCustomerContactsPage(
				accountKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Contact> contacts2 = (List<Contact>)page2.getItems();

		Assert.assertEquals(contacts2.toString(), 1, contacts2.size());

		Page<Contact> page3 =
			contactResource.getAccountAccountKeyCustomerContactsPage(
				accountKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contact1, contact2, contact3),
			(List<Contact>)page3.getItems());
	}

	protected Contact testGetAccountAccountKeyCustomerContactsPage_addContact(
			String accountKey, Contact contact)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyCustomerContactsPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyCustomerContactsPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testGetAccountAccountKeyWorkerContactsPage() throws Exception {
		String accountKey =
			testGetAccountAccountKeyWorkerContactsPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyWorkerContactsPage_getIrrelevantAccountKey();

		Page<Contact> page =
			contactResource.getAccountAccountKeyWorkerContactsPage(
				accountKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantAccountKey != null) {
			Contact irrelevantContact =
				testGetAccountAccountKeyWorkerContactsPage_addContact(
					irrelevantAccountKey, randomIrrelevantContact());

			page = contactResource.getAccountAccountKeyWorkerContactsPage(
				irrelevantAccountKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContact),
				(List<Contact>)page.getItems());
			assertValid(page);
		}

		Contact contact1 =
			testGetAccountAccountKeyWorkerContactsPage_addContact(
				accountKey, randomContact());

		Contact contact2 =
			testGetAccountAccountKeyWorkerContactsPage_addContact(
				accountKey, randomContact());

		page = contactResource.getAccountAccountKeyWorkerContactsPage(
			accountKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contact1, contact2), (List<Contact>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyWorkerContactsPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyWorkerContactsPage_getAccountKey();

		Contact contact1 =
			testGetAccountAccountKeyWorkerContactsPage_addContact(
				accountKey, randomContact());

		Contact contact2 =
			testGetAccountAccountKeyWorkerContactsPage_addContact(
				accountKey, randomContact());

		Contact contact3 =
			testGetAccountAccountKeyWorkerContactsPage_addContact(
				accountKey, randomContact());

		Page<Contact> page1 =
			contactResource.getAccountAccountKeyWorkerContactsPage(
				accountKey, Pagination.of(1, 2));

		List<Contact> contacts1 = (List<Contact>)page1.getItems();

		Assert.assertEquals(contacts1.toString(), 2, contacts1.size());

		Page<Contact> page2 =
			contactResource.getAccountAccountKeyWorkerContactsPage(
				accountKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Contact> contacts2 = (List<Contact>)page2.getItems();

		Assert.assertEquals(contacts2.toString(), 1, contacts2.size());

		Page<Contact> page3 =
			contactResource.getAccountAccountKeyWorkerContactsPage(
				accountKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contact1, contact2, contact3),
			(List<Contact>)page3.getItems());
	}

	protected Contact testGetAccountAccountKeyWorkerContactsPage_addContact(
			String accountKey, Contact contact)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetAccountAccountKeyWorkerContactsPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyWorkerContactsPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testGetContactsPage() throws Exception {
		Page<Contact> page = contactResource.getContactsPage(
			null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		Contact contact1 = testGetContactsPage_addContact(randomContact());

		Contact contact2 = testGetContactsPage_addContact(randomContact());

		page = contactResource.getContactsPage(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(contact1, (List<Contact>)page.getItems());
		assertContains(contact2, (List<Contact>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContactsPageWithFilterDateTimeEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Contact contact1 = randomContact();

		contact1 = testGetContactsPage_addContact(contact1);

		for (EntityField entityField : entityFields) {
			Page<Contact> page = contactResource.getContactsPage(
				null, getFilterString(entityField, "between", contact1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(contact1),
				(List<Contact>)page.getItems());
		}
	}

	@Test
	public void testGetContactsPageWithFilterDoubleEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DOUBLE);

		if (entityFields.isEmpty()) {
			return;
		}

		Contact contact1 = testGetContactsPage_addContact(randomContact());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Contact contact2 = testGetContactsPage_addContact(randomContact());

		for (EntityField entityField : entityFields) {
			Page<Contact> page = contactResource.getContactsPage(
				null, getFilterString(entityField, "eq", contact1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(contact1),
				(List<Contact>)page.getItems());
		}
	}

	@Test
	public void testGetContactsPageWithFilterStringEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Contact contact1 = testGetContactsPage_addContact(randomContact());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Contact contact2 = testGetContactsPage_addContact(randomContact());

		for (EntityField entityField : entityFields) {
			Page<Contact> page = contactResource.getContactsPage(
				null, getFilterString(entityField, "eq", contact1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(contact1),
				(List<Contact>)page.getItems());
		}
	}

	@Test
	public void testGetContactsPageWithPagination() throws Exception {
		Page<Contact> totalPage = contactResource.getContactsPage(
			null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		Contact contact1 = testGetContactsPage_addContact(randomContact());

		Contact contact2 = testGetContactsPage_addContact(randomContact());

		Contact contact3 = testGetContactsPage_addContact(randomContact());

		Page<Contact> page1 = contactResource.getContactsPage(
			null, null, Pagination.of(1, totalCount + 2), null);

		List<Contact> contacts1 = (List<Contact>)page1.getItems();

		Assert.assertEquals(
			contacts1.toString(), totalCount + 2, contacts1.size());

		Page<Contact> page2 = contactResource.getContactsPage(
			null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<Contact> contacts2 = (List<Contact>)page2.getItems();

		Assert.assertEquals(contacts2.toString(), 1, contacts2.size());

		Page<Contact> page3 = contactResource.getContactsPage(
			null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(contact1, (List<Contact>)page3.getItems());
		assertContains(contact2, (List<Contact>)page3.getItems());
		assertContains(contact3, (List<Contact>)page3.getItems());
	}

	@Test
	public void testGetContactsPageWithSortDateTime() throws Exception {
		testGetContactsPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, contact1, contact2) -> {
				BeanTestUtil.setProperty(
					contact1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetContactsPageWithSortDouble() throws Exception {
		testGetContactsPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, contact1, contact2) -> {
				BeanTestUtil.setProperty(contact1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(contact2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetContactsPageWithSortInteger() throws Exception {
		testGetContactsPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, contact1, contact2) -> {
				BeanTestUtil.setProperty(contact1, entityField.getName(), 0);
				BeanTestUtil.setProperty(contact2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetContactsPageWithSortString() throws Exception {
		testGetContactsPageWithSort(
			EntityField.Type.STRING,
			(entityField, contact1, contact2) -> {
				Class<?> clazz = contact1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						contact1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						contact2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						contact1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						contact2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						contact1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						contact2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetContactsPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, Contact, Contact, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Contact contact1 = randomContact();
		Contact contact2 = randomContact();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, contact1, contact2);
		}

		contact1 = testGetContactsPage_addContact(contact1);

		contact2 = testGetContactsPage_addContact(contact2);

		for (EntityField entityField : entityFields) {
			Page<Contact> ascPage = contactResource.getContactsPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(contact1, contact2),
				(List<Contact>)ascPage.getItems());

			Page<Contact> descPage = contactResource.getContactsPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(contact2, contact1),
				(List<Contact>)descPage.getItems());
		}
	}

	protected Contact testGetContactsPage_addContact(Contact contact)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetContactsPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPostContact() throws Exception {
		Contact randomContact = randomContact();

		Contact postContact = testPostContact_addContact(randomContact);

		assertEquals(randomContact, postContact);
		assertValid(postContact);
	}

	protected Contact testPostContact_addContact(Contact contact)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteContactByEmailAddresEmailAddress() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetContactByEmailAddresEmailAddress() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetContactByEmailAddresEmailAddress()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetContactByEmailAddresEmailAddressNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testPutContactByEmailAddresEmailAddress() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteContactByUuidContactUuid() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetContactByUuidContactUuid() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetContactByUuidContactUuid() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetContactByUuidContactUuidNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testPutContactByUuidContactUuid() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteContactByUuidContactUuidContactPermission()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testPutContactByUuidContactUuidContactPermission()
		throws Exception {

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Contact contact =
			testPutContactByUuidContactUuidContactPermission_addContact();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			contactResource.
				putContactByUuidContactUuidContactPermissionHttpResponse(
					null, null, null, null));

		assertHttpResponseStatusCode(
			404,
			contactResource.
				putContactByUuidContactUuidContactPermissionHttpResponse(
					null, null, null, null));
	}

	protected Contact
			testPutContactByUuidContactUuidContactPermission_addContact()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetTeamTeamKeyContactsPage() throws Exception {
		String teamKey = testGetTeamTeamKeyContactsPage_getTeamKey();
		String irrelevantTeamKey =
			testGetTeamTeamKeyContactsPage_getIrrelevantTeamKey();

		Page<Contact> page = contactResource.getTeamTeamKeyContactsPage(
			teamKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantTeamKey != null) {
			Contact irrelevantContact =
				testGetTeamTeamKeyContactsPage_addContact(
					irrelevantTeamKey, randomIrrelevantContact());

			page = contactResource.getTeamTeamKeyContactsPage(
				irrelevantTeamKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContact),
				(List<Contact>)page.getItems());
			assertValid(page);
		}

		Contact contact1 = testGetTeamTeamKeyContactsPage_addContact(
			teamKey, randomContact());

		Contact contact2 = testGetTeamTeamKeyContactsPage_addContact(
			teamKey, randomContact());

		page = contactResource.getTeamTeamKeyContactsPage(
			teamKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contact1, contact2), (List<Contact>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetTeamTeamKeyContactsPageWithPagination()
		throws Exception {

		String teamKey = testGetTeamTeamKeyContactsPage_getTeamKey();

		Contact contact1 = testGetTeamTeamKeyContactsPage_addContact(
			teamKey, randomContact());

		Contact contact2 = testGetTeamTeamKeyContactsPage_addContact(
			teamKey, randomContact());

		Contact contact3 = testGetTeamTeamKeyContactsPage_addContact(
			teamKey, randomContact());

		Page<Contact> page1 = contactResource.getTeamTeamKeyContactsPage(
			teamKey, Pagination.of(1, 2));

		List<Contact> contacts1 = (List<Contact>)page1.getItems();

		Assert.assertEquals(contacts1.toString(), 2, contacts1.size());

		Page<Contact> page2 = contactResource.getTeamTeamKeyContactsPage(
			teamKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Contact> contacts2 = (List<Contact>)page2.getItems();

		Assert.assertEquals(contacts2.toString(), 1, contacts2.size());

		Page<Contact> page3 = contactResource.getTeamTeamKeyContactsPage(
			teamKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contact1, contact2, contact3),
			(List<Contact>)page3.getItems());
	}

	protected Contact testGetTeamTeamKeyContactsPage_addContact(
			String teamKey, Contact contact)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetTeamTeamKeyContactsPage_getTeamKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetTeamTeamKeyContactsPage_getIrrelevantTeamKey()
		throws Exception {

		return null;
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertContains(Contact contact, List<Contact> contacts) {
		boolean contains = false;

		for (Contact item : contacts) {
			if (equals(contact, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(contacts + " does not contain " + contact, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Contact contact1, Contact contact2) {
		Assert.assertTrue(
			contact1 + " does not equal " + contact2,
			equals(contact1, contact2));
	}

	protected void assertEquals(
		List<Contact> contacts1, List<Contact> contacts2) {

		Assert.assertEquals(contacts1.size(), contacts2.size());

		for (int i = 0; i < contacts1.size(); i++) {
			Contact contact1 = contacts1.get(i);
			Contact contact2 = contacts2.get(i);

			assertEquals(contact1, contact2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Contact> contacts1, List<Contact> contacts2) {

		Assert.assertEquals(contacts1.size(), contacts2.size());

		for (Contact contact1 : contacts1) {
			boolean contains = false;

			for (Contact contact2 : contacts2) {
				if (equals(contact1, contact2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				contacts2 + " does not contain " + contact1, contains);
		}
	}

	protected void assertValid(Contact contact) throws Exception {
		boolean valid = true;

		if (contact.getDateCreated() == null) {
			valid = false;
		}

		if (contact.getDateModified() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accounts", additionalAssertFieldName)) {
				if (contact.getAccounts() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("contactRoles", additionalAssertFieldName)) {
				if (contact.getContactRoles() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("emailAddress", additionalAssertFieldName)) {
				if (contact.getEmailAddress() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"emailAddressVerified", additionalAssertFieldName)) {

				if (contact.getEmailAddressVerified() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("entitlements", additionalAssertFieldName)) {
				if (contact.getEntitlements() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (contact.getExternalLinks() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("firstName", additionalAssertFieldName)) {
				if (contact.getFirstName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (contact.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("languageId", additionalAssertFieldName)) {
				if (contact.getLanguageId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("lastName", additionalAssertFieldName)) {
				if (contact.getLastName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("middleName", additionalAssertFieldName)) {
				if (contact.getMiddleName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("teams", additionalAssertFieldName)) {
				if (contact.getTeams() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("uuid", additionalAssertFieldName)) {
				if (contact.getUuid() == null) {
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

	protected void assertValid(Page<Contact> page) {
		boolean valid = false;

		java.util.Collection<Contact> contacts = page.getItems();

		int size = contacts.size();

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
					com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact.
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

	protected boolean equals(Contact contact1, Contact contact2) {
		if (contact1 == contact2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accounts", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getAccounts(), contact2.getAccounts())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("contactRoles", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getContactRoles(),
						contact2.getContactRoles())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getDateCreated(), contact2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getDateModified(),
						contact2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("emailAddress", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getEmailAddress(),
						contact2.getEmailAddress())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"emailAddressVerified", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						contact1.getEmailAddressVerified(),
						contact2.getEmailAddressVerified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("entitlements", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getEntitlements(),
						contact2.getEntitlements())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getExternalLinks(),
						contact2.getExternalLinks())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("firstName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getFirstName(), contact2.getFirstName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(contact1.getKey(), contact2.getKey())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("languageId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getLanguageId(), contact2.getLanguageId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("lastName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getLastName(), contact2.getLastName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("middleName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getMiddleName(), contact2.getMiddleName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("teams", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getTeams(), contact2.getTeams())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("uuid", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contact1.getUuid(), contact2.getUuid())) {

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

		if (!(_contactResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_contactResource;

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
		EntityField entityField, String operator, Contact contact) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("accounts")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("contactRoles")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("dateCreated")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(contact.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(contact.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(contact.getDateCreated()));
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
						DateUtils.addSeconds(contact.getDateModified(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(contact.getDateModified(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(contact.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("emailAddress")) {
			sb.append("'");
			sb.append(String.valueOf(contact.getEmailAddress()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("emailAddressVerified")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("entitlements")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("externalLinks")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("firstName")) {
			sb.append("'");
			sb.append(String.valueOf(contact.getFirstName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(contact.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("languageId")) {
			sb.append("'");
			sb.append(String.valueOf(contact.getLanguageId()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("lastName")) {
			sb.append("'");
			sb.append(String.valueOf(contact.getLastName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("middleName")) {
			sb.append("'");
			sb.append(String.valueOf(contact.getMiddleName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("teams")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("uuid")) {
			sb.append("'");
			sb.append(String.valueOf(contact.getUuid()));
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

	protected Contact randomContact() throws Exception {
		return new Contact() {
			{
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				emailAddress =
					StringUtil.toLowerCase(RandomTestUtil.randomString()) +
						"@liferay.com";
				emailAddressVerified = RandomTestUtil.randomBoolean();
				firstName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				languageId = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				lastName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				middleName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				uuid = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected Contact randomIrrelevantContact() throws Exception {
		Contact randomIrrelevantContact = randomContact();

		return randomIrrelevantContact;
	}

	protected Contact randomPatchContact() throws Exception {
		return randomContact();
	}

	protected ContactResource contactResource;
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
		LogFactoryUtil.getLog(BaseContactResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource
		_contactResource;

}