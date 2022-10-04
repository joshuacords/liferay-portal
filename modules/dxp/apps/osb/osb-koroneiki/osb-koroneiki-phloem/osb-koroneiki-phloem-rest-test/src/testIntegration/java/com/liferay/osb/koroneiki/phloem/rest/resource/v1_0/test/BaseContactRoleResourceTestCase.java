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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactRoleSerDes;
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
public abstract class BaseContactRoleResourceTestCase {

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

		_contactRoleResource.setContextCompany(testCompany);

		ContactRoleResource.Builder builder = ContactRoleResource.builder();

		contactRoleResource = builder.authentication(
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

		ContactRole contactRole1 = randomContactRole();

		String json = objectMapper.writeValueAsString(contactRole1);

		ContactRole contactRole2 = ContactRoleSerDes.toDTO(json);

		Assert.assertTrue(equals(contactRole1, contactRole2));
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

		ContactRole contactRole = randomContactRole();

		String json1 = objectMapper.writeValueAsString(contactRole);
		String json2 = ContactRoleSerDes.toJSON(contactRole);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		ContactRole contactRole = randomContactRole();

		contactRole.setDescription(regex);
		contactRole.setKey(regex);
		contactRole.setName(regex);

		String json = ContactRoleSerDes.toJSON(contactRole);

		Assert.assertFalse(json.contains(regex));

		contactRole = ContactRoleSerDes.toDTO(json);

		Assert.assertEquals(regex, contactRole.getDescription());
		Assert.assertEquals(regex, contactRole.getKey());
		Assert.assertEquals(regex, contactRole.getName());
	}

	@Test
	public void testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantAccountKey();
		String contactEmailAddress =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_getContactEmailAddress();
		String irrelevantContactEmailAddress =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantContactEmailAddress();

		Page<ContactRole> page =
			contactRoleResource.
				getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantAccountKey != null) &&
			(irrelevantContactEmailAddress != null)) {

			ContactRole irrelevantContactRole =
				testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
					irrelevantAccountKey, irrelevantContactEmailAddress,
					randomIrrelevantContactRole());

			page =
				contactRoleResource.
					getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
						irrelevantAccountKey, irrelevantContactEmailAddress,
						Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContactRole),
				(List<ContactRole>)page.getItems());
			assertValid(page);
		}

		ContactRole contactRole1 =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		page =
			contactRoleResource.
				getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2),
			(List<ContactRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_getAccountKey();
		String contactEmailAddress =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_getContactEmailAddress();

		ContactRole contactRole1 =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		ContactRole contactRole3 =
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		Page<ContactRole> page1 =
			contactRoleResource.
				getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 2));

		List<ContactRole> contactRoles1 = (List<ContactRole>)page1.getItems();

		Assert.assertEquals(contactRoles1.toString(), 2, contactRoles1.size());

		Page<ContactRole> page2 =
			contactRoleResource.
				getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ContactRole> contactRoles2 = (List<ContactRole>)page2.getItems();

		Assert.assertEquals(contactRoles2.toString(), 1, contactRoles2.size());

		Page<ContactRole> page3 =
			contactRoleResource.
				getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2, contactRole3),
			(List<ContactRole>)page3.getItems());
	}

	protected ContactRole
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				String accountKey, String contactEmailAddress,
				ContactRole contactRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	protected String
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_getContactEmailAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantContactEmailAddress()
		throws Exception {

		return null;
	}

	@Test
	public void testGetAccountAccountKeyContactByUuidContactUuidRolesPage()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_getIrrelevantAccountKey();
		String contactUuid =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_getContactUuid();
		String irrelevantContactUuid =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_getIrrelevantContactUuid();

		Page<ContactRole> page =
			contactRoleResource.
				getAccountAccountKeyContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantAccountKey != null) && (irrelevantContactUuid != null)) {
			ContactRole irrelevantContactRole =
				testGetAccountAccountKeyContactByUuidContactUuidRolesPage_addContactRole(
					irrelevantAccountKey, irrelevantContactUuid,
					randomIrrelevantContactRole());

			page =
				contactRoleResource.
					getAccountAccountKeyContactByUuidContactUuidRolesPage(
						irrelevantAccountKey, irrelevantContactUuid,
						Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContactRole),
				(List<ContactRole>)page.getItems());
			assertValid(page);
		}

		ContactRole contactRole1 =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		page =
			contactRoleResource.
				getAccountAccountKeyContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2),
			(List<ContactRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyContactByUuidContactUuidRolesPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_getAccountKey();
		String contactUuid =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_getContactUuid();

		ContactRole contactRole1 =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		ContactRole contactRole3 =
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		Page<ContactRole> page1 =
			contactRoleResource.
				getAccountAccountKeyContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 2));

		List<ContactRole> contactRoles1 = (List<ContactRole>)page1.getItems();

		Assert.assertEquals(contactRoles1.toString(), 2, contactRoles1.size());

		Page<ContactRole> page2 =
			contactRoleResource.
				getAccountAccountKeyContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ContactRole> contactRoles2 = (List<ContactRole>)page2.getItems();

		Assert.assertEquals(contactRoles2.toString(), 1, contactRoles2.size());

		Page<ContactRole> page3 =
			contactRoleResource.
				getAccountAccountKeyContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2, contactRole3),
			(List<ContactRole>)page3.getItems());
	}

	protected ContactRole
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_addContactRole(
				String accountKey, String contactUuid, ContactRole contactRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	protected String
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_getContactUuid()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyContactByUuidContactUuidRolesPage_getIrrelevantContactUuid()
		throws Exception {

		return null;
	}

	@Test
	public void testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantAccountKey();
		String contactEmailAddress =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_getContactEmailAddress();
		String irrelevantContactEmailAddress =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantContactEmailAddress();

		Page<ContactRole> page =
			contactRoleResource.
				getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantAccountKey != null) &&
			(irrelevantContactEmailAddress != null)) {

			ContactRole irrelevantContactRole =
				testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
					irrelevantAccountKey, irrelevantContactEmailAddress,
					randomIrrelevantContactRole());

			page =
				contactRoleResource.
					getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
						irrelevantAccountKey, irrelevantContactEmailAddress,
						Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContactRole),
				(List<ContactRole>)page.getItems());
			assertValid(page);
		}

		ContactRole contactRole1 =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		page =
			contactRoleResource.
				getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2),
			(List<ContactRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_getAccountKey();
		String contactEmailAddress =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_getContactEmailAddress();

		ContactRole contactRole1 =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		ContactRole contactRole3 =
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		Page<ContactRole> page1 =
			contactRoleResource.
				getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 2));

		List<ContactRole> contactRoles1 = (List<ContactRole>)page1.getItems();

		Assert.assertEquals(contactRoles1.toString(), 2, contactRoles1.size());

		Page<ContactRole> page2 =
			contactRoleResource.
				getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ContactRole> contactRoles2 = (List<ContactRole>)page2.getItems();

		Assert.assertEquals(contactRoles2.toString(), 1, contactRoles2.size());

		Page<ContactRole> page3 =
			contactRoleResource.
				getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2, contactRole3),
			(List<ContactRole>)page3.getItems());
	}

	protected ContactRole
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				String accountKey, String contactEmailAddress,
				ContactRole contactRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	protected String
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_getContactEmailAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantContactEmailAddress()
		throws Exception {

		return null;
	}

	@Test
	public void testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_getIrrelevantAccountKey();
		String contactUuid =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_getContactUuid();
		String irrelevantContactUuid =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_getIrrelevantContactUuid();

		Page<ContactRole> page =
			contactRoleResource.
				getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantAccountKey != null) && (irrelevantContactUuid != null)) {
			ContactRole irrelevantContactRole =
				testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_addContactRole(
					irrelevantAccountKey, irrelevantContactUuid,
					randomIrrelevantContactRole());

			page =
				contactRoleResource.
					getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
						irrelevantAccountKey, irrelevantContactUuid,
						Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContactRole),
				(List<ContactRole>)page.getItems());
			assertValid(page);
		}

		ContactRole contactRole1 =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		page =
			contactRoleResource.
				getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2),
			(List<ContactRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_getAccountKey();
		String contactUuid =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_getContactUuid();

		ContactRole contactRole1 =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		ContactRole contactRole3 =
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		Page<ContactRole> page1 =
			contactRoleResource.
				getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 2));

		List<ContactRole> contactRoles1 = (List<ContactRole>)page1.getItems();

		Assert.assertEquals(contactRoles1.toString(), 2, contactRoles1.size());

		Page<ContactRole> page2 =
			contactRoleResource.
				getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ContactRole> contactRoles2 = (List<ContactRole>)page2.getItems();

		Assert.assertEquals(contactRoles2.toString(), 1, contactRoles2.size());

		Page<ContactRole> page3 =
			contactRoleResource.
				getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2, contactRole3),
			(List<ContactRole>)page3.getItems());
	}

	protected ContactRole
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_addContactRole(
				String accountKey, String contactUuid, ContactRole contactRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	protected String
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_getContactUuid()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyCustomerContactByUuidContactUuidRolesPage_getIrrelevantContactUuid()
		throws Exception {

		return null;
	}

	@Test
	public void testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantAccountKey();
		String contactEmailAddress =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_getContactEmailAddress();
		String irrelevantContactEmailAddress =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantContactEmailAddress();

		Page<ContactRole> page =
			contactRoleResource.
				getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantAccountKey != null) &&
			(irrelevantContactEmailAddress != null)) {

			ContactRole irrelevantContactRole =
				testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
					irrelevantAccountKey, irrelevantContactEmailAddress,
					randomIrrelevantContactRole());

			page =
				contactRoleResource.
					getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
						irrelevantAccountKey, irrelevantContactEmailAddress,
						Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContactRole),
				(List<ContactRole>)page.getItems());
			assertValid(page);
		}

		ContactRole contactRole1 =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		page =
			contactRoleResource.
				getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2),
			(List<ContactRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_getAccountKey();
		String contactEmailAddress =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_getContactEmailAddress();

		ContactRole contactRole1 =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		ContactRole contactRole3 =
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				accountKey, contactEmailAddress, randomContactRole());

		Page<ContactRole> page1 =
			contactRoleResource.
				getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 2));

		List<ContactRole> contactRoles1 = (List<ContactRole>)page1.getItems();

		Assert.assertEquals(contactRoles1.toString(), 2, contactRoles1.size());

		Page<ContactRole> page2 =
			contactRoleResource.
				getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ContactRole> contactRoles2 = (List<ContactRole>)page2.getItems();

		Assert.assertEquals(contactRoles2.toString(), 1, contactRoles2.size());

		Page<ContactRole> page3 =
			contactRoleResource.
				getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, contactEmailAddress, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2, contactRole3),
			(List<ContactRole>)page3.getItems());
	}

	protected ContactRole
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_addContactRole(
				String accountKey, String contactEmailAddress,
				ContactRole contactRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	protected String
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_getContactEmailAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage_getIrrelevantContactEmailAddress()
		throws Exception {

		return null;
	}

	@Test
	public void testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_getIrrelevantAccountKey();
		String contactUuid =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_getContactUuid();
		String irrelevantContactUuid =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_getIrrelevantContactUuid();

		Page<ContactRole> page =
			contactRoleResource.
				getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantAccountKey != null) && (irrelevantContactUuid != null)) {
			ContactRole irrelevantContactRole =
				testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_addContactRole(
					irrelevantAccountKey, irrelevantContactUuid,
					randomIrrelevantContactRole());

			page =
				contactRoleResource.
					getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
						irrelevantAccountKey, irrelevantContactUuid,
						Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContactRole),
				(List<ContactRole>)page.getItems());
			assertValid(page);
		}

		ContactRole contactRole1 =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		page =
			contactRoleResource.
				getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2),
			(List<ContactRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_getAccountKey();
		String contactUuid =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_getContactUuid();

		ContactRole contactRole1 =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		ContactRole contactRole2 =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		ContactRole contactRole3 =
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_addContactRole(
				accountKey, contactUuid, randomContactRole());

		Page<ContactRole> page1 =
			contactRoleResource.
				getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 2));

		List<ContactRole> contactRoles1 = (List<ContactRole>)page1.getItems();

		Assert.assertEquals(contactRoles1.toString(), 2, contactRoles1.size());

		Page<ContactRole> page2 =
			contactRoleResource.
				getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ContactRole> contactRoles2 = (List<ContactRole>)page2.getItems();

		Assert.assertEquals(contactRoles2.toString(), 1, contactRoles2.size());

		Page<ContactRole> page3 =
			contactRoleResource.
				getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
					accountKey, contactUuid, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2, contactRole3),
			(List<ContactRole>)page3.getItems());
	}

	protected ContactRole
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_addContactRole(
				String accountKey, String contactUuid, ContactRole contactRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	protected String
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_getContactUuid()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyWorkerContactByUuidContactUuidRolesPage_getIrrelevantContactUuid()
		throws Exception {

		return null;
	}

	@Test
	public void testGetContactRolesPage() throws Exception {
		Page<ContactRole> page = contactRoleResource.getContactRolesPage(
			null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		ContactRole contactRole1 = testGetContactRolesPage_addContactRole(
			randomContactRole());

		ContactRole contactRole2 = testGetContactRolesPage_addContactRole(
			randomContactRole());

		page = contactRoleResource.getContactRolesPage(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(contactRole1, (List<ContactRole>)page.getItems());
		assertContains(contactRole2, (List<ContactRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContactRolesPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		ContactRole contactRole1 = randomContactRole();

		contactRole1 = testGetContactRolesPage_addContactRole(contactRole1);

		for (EntityField entityField : entityFields) {
			Page<ContactRole> page = contactRoleResource.getContactRolesPage(
				null, getFilterString(entityField, "between", contactRole1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(contactRole1),
				(List<ContactRole>)page.getItems());
		}
	}

	@Test
	public void testGetContactRolesPageWithFilterDoubleEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DOUBLE);

		if (entityFields.isEmpty()) {
			return;
		}

		ContactRole contactRole1 = testGetContactRolesPage_addContactRole(
			randomContactRole());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		ContactRole contactRole2 = testGetContactRolesPage_addContactRole(
			randomContactRole());

		for (EntityField entityField : entityFields) {
			Page<ContactRole> page = contactRoleResource.getContactRolesPage(
				null, getFilterString(entityField, "eq", contactRole1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(contactRole1),
				(List<ContactRole>)page.getItems());
		}
	}

	@Test
	public void testGetContactRolesPageWithFilterStringEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		ContactRole contactRole1 = testGetContactRolesPage_addContactRole(
			randomContactRole());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		ContactRole contactRole2 = testGetContactRolesPage_addContactRole(
			randomContactRole());

		for (EntityField entityField : entityFields) {
			Page<ContactRole> page = contactRoleResource.getContactRolesPage(
				null, getFilterString(entityField, "eq", contactRole1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(contactRole1),
				(List<ContactRole>)page.getItems());
		}
	}

	@Test
	public void testGetContactRolesPageWithPagination() throws Exception {
		Page<ContactRole> totalPage = contactRoleResource.getContactRolesPage(
			null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		ContactRole contactRole1 = testGetContactRolesPage_addContactRole(
			randomContactRole());

		ContactRole contactRole2 = testGetContactRolesPage_addContactRole(
			randomContactRole());

		ContactRole contactRole3 = testGetContactRolesPage_addContactRole(
			randomContactRole());

		Page<ContactRole> page1 = contactRoleResource.getContactRolesPage(
			null, null, Pagination.of(1, totalCount + 2), null);

		List<ContactRole> contactRoles1 = (List<ContactRole>)page1.getItems();

		Assert.assertEquals(
			contactRoles1.toString(), totalCount + 2, contactRoles1.size());

		Page<ContactRole> page2 = contactRoleResource.getContactRolesPage(
			null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<ContactRole> contactRoles2 = (List<ContactRole>)page2.getItems();

		Assert.assertEquals(contactRoles2.toString(), 1, contactRoles2.size());

		Page<ContactRole> page3 = contactRoleResource.getContactRolesPage(
			null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(contactRole1, (List<ContactRole>)page3.getItems());
		assertContains(contactRole2, (List<ContactRole>)page3.getItems());
		assertContains(contactRole3, (List<ContactRole>)page3.getItems());
	}

	@Test
	public void testGetContactRolesPageWithSortDateTime() throws Exception {
		testGetContactRolesPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, contactRole1, contactRole2) -> {
				BeanTestUtil.setProperty(
					contactRole1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetContactRolesPageWithSortDouble() throws Exception {
		testGetContactRolesPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, contactRole1, contactRole2) -> {
				BeanTestUtil.setProperty(
					contactRole1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(
					contactRole2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetContactRolesPageWithSortInteger() throws Exception {
		testGetContactRolesPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, contactRole1, contactRole2) -> {
				BeanTestUtil.setProperty(
					contactRole1, entityField.getName(), 0);
				BeanTestUtil.setProperty(
					contactRole2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetContactRolesPageWithSortString() throws Exception {
		testGetContactRolesPageWithSort(
			EntityField.Type.STRING,
			(entityField, contactRole1, contactRole2) -> {
				Class<?> clazz = contactRole1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						contactRole1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						contactRole2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						contactRole1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						contactRole2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						contactRole1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						contactRole2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetContactRolesPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, ContactRole, ContactRole, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		ContactRole contactRole1 = randomContactRole();
		ContactRole contactRole2 = randomContactRole();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, contactRole1, contactRole2);
		}

		contactRole1 = testGetContactRolesPage_addContactRole(contactRole1);

		contactRole2 = testGetContactRolesPage_addContactRole(contactRole2);

		for (EntityField entityField : entityFields) {
			Page<ContactRole> ascPage = contactRoleResource.getContactRolesPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(contactRole1, contactRole2),
				(List<ContactRole>)ascPage.getItems());

			Page<ContactRole> descPage =
				contactRoleResource.getContactRolesPage(
					null, null, Pagination.of(1, 2),
					entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(contactRole2, contactRole1),
				(List<ContactRole>)descPage.getItems());
		}
	}

	protected ContactRole testGetContactRolesPage_addContactRole(
			ContactRole contactRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetContactRolesPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPostContactRole() throws Exception {
		ContactRole randomContactRole = randomContactRole();

		ContactRole postContactRole = testPostContactRole_addContactRole(
			randomContactRole);

		assertEquals(randomContactRole, postContactRole);
		assertValid(postContactRole);
	}

	protected ContactRole testPostContactRole_addContactRole(
			ContactRole contactRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetContactRoleByTypeContactRoleTypeByNameContactRoleName()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetContactRoleByTypeContactRoleTypeByNameContactRoleName()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetContactRoleByTypeContactRoleTypeByNameContactRoleNameNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testDeleteContactRole() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLDeleteContactRole() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetContactRole() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetContactRole() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetContactRoleNotFound() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPutContactRole() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteContactRoleContactRolePermission() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPutContactRoleContactRolePermission() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		ContactRole contactRole =
			testPutContactRoleContactRolePermission_addContactRole();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			contactRoleResource.putContactRoleContactRolePermissionHttpResponse(
				null, null, null, null));

		assertHttpResponseStatusCode(
			404,
			contactRoleResource.putContactRoleContactRolePermissionHttpResponse(
				null, null, null, null));
	}

	protected ContactRole
			testPutContactRoleContactRolePermission_addContactRole()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetContactRolesContactRoleTypeContactRoleName()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetContactRolesContactRoleTypeContactRoleName()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetContactRolesContactRoleTypeContactRoleNameNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage()
		throws Exception {

		String teamKey =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_getTeamKey();
		String irrelevantTeamKey =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_getIrrelevantTeamKey();
		String emailAddress =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_getEmailAddress();
		String irrelevantEmailAddress =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_getIrrelevantEmailAddress();

		Page<ContactRole> page =
			contactRoleResource.
				getTeamTeamKeyContactByEmailAddresEmailAddressRolesPage(
					teamKey, emailAddress, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantTeamKey != null) && (irrelevantEmailAddress != null)) {
			ContactRole irrelevantContactRole =
				testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_addContactRole(
					irrelevantTeamKey, irrelevantEmailAddress,
					randomIrrelevantContactRole());

			page =
				contactRoleResource.
					getTeamTeamKeyContactByEmailAddresEmailAddressRolesPage(
						irrelevantTeamKey, irrelevantEmailAddress,
						Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContactRole),
				(List<ContactRole>)page.getItems());
			assertValid(page);
		}

		ContactRole contactRole1 =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_addContactRole(
				teamKey, emailAddress, randomContactRole());

		ContactRole contactRole2 =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_addContactRole(
				teamKey, emailAddress, randomContactRole());

		page =
			contactRoleResource.
				getTeamTeamKeyContactByEmailAddresEmailAddressRolesPage(
					teamKey, emailAddress, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2),
			(List<ContactRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPageWithPagination()
		throws Exception {

		String teamKey =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_getTeamKey();
		String emailAddress =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_getEmailAddress();

		ContactRole contactRole1 =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_addContactRole(
				teamKey, emailAddress, randomContactRole());

		ContactRole contactRole2 =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_addContactRole(
				teamKey, emailAddress, randomContactRole());

		ContactRole contactRole3 =
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_addContactRole(
				teamKey, emailAddress, randomContactRole());

		Page<ContactRole> page1 =
			contactRoleResource.
				getTeamTeamKeyContactByEmailAddresEmailAddressRolesPage(
					teamKey, emailAddress, Pagination.of(1, 2));

		List<ContactRole> contactRoles1 = (List<ContactRole>)page1.getItems();

		Assert.assertEquals(contactRoles1.toString(), 2, contactRoles1.size());

		Page<ContactRole> page2 =
			contactRoleResource.
				getTeamTeamKeyContactByEmailAddresEmailAddressRolesPage(
					teamKey, emailAddress, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ContactRole> contactRoles2 = (List<ContactRole>)page2.getItems();

		Assert.assertEquals(contactRoles2.toString(), 1, contactRoles2.size());

		Page<ContactRole> page3 =
			contactRoleResource.
				getTeamTeamKeyContactByEmailAddresEmailAddressRolesPage(
					teamKey, emailAddress, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2, contactRole3),
			(List<ContactRole>)page3.getItems());
	}

	protected ContactRole
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_addContactRole(
				String teamKey, String emailAddress, ContactRole contactRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_getTeamKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_getIrrelevantTeamKey()
		throws Exception {

		return null;
	}

	protected String
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_getEmailAddress()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetTeamTeamKeyContactByEmailAddresEmailAddressRolesPage_getIrrelevantEmailAddress()
		throws Exception {

		return null;
	}

	@Test
	public void testGetTeamTeamKeyContactByUuidContactUuidRolesPage()
		throws Exception {

		String teamKey =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_getTeamKey();
		String irrelevantTeamKey =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_getIrrelevantTeamKey();
		String contactUuid =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_getContactUuid();
		String irrelevantContactUuid =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_getIrrelevantContactUuid();

		Page<ContactRole> page =
			contactRoleResource.getTeamTeamKeyContactByUuidContactUuidRolesPage(
				teamKey, contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantTeamKey != null) && (irrelevantContactUuid != null)) {
			ContactRole irrelevantContactRole =
				testGetTeamTeamKeyContactByUuidContactUuidRolesPage_addContactRole(
					irrelevantTeamKey, irrelevantContactUuid,
					randomIrrelevantContactRole());

			page =
				contactRoleResource.
					getTeamTeamKeyContactByUuidContactUuidRolesPage(
						irrelevantTeamKey, irrelevantContactUuid,
						Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContactRole),
				(List<ContactRole>)page.getItems());
			assertValid(page);
		}

		ContactRole contactRole1 =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_addContactRole(
				teamKey, contactUuid, randomContactRole());

		ContactRole contactRole2 =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_addContactRole(
				teamKey, contactUuid, randomContactRole());

		page =
			contactRoleResource.getTeamTeamKeyContactByUuidContactUuidRolesPage(
				teamKey, contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2),
			(List<ContactRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetTeamTeamKeyContactByUuidContactUuidRolesPageWithPagination()
		throws Exception {

		String teamKey =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_getTeamKey();
		String contactUuid =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_getContactUuid();

		ContactRole contactRole1 =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_addContactRole(
				teamKey, contactUuid, randomContactRole());

		ContactRole contactRole2 =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_addContactRole(
				teamKey, contactUuid, randomContactRole());

		ContactRole contactRole3 =
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_addContactRole(
				teamKey, contactUuid, randomContactRole());

		Page<ContactRole> page1 =
			contactRoleResource.getTeamTeamKeyContactByUuidContactUuidRolesPage(
				teamKey, contactUuid, Pagination.of(1, 2));

		List<ContactRole> contactRoles1 = (List<ContactRole>)page1.getItems();

		Assert.assertEquals(contactRoles1.toString(), 2, contactRoles1.size());

		Page<ContactRole> page2 =
			contactRoleResource.getTeamTeamKeyContactByUuidContactUuidRolesPage(
				teamKey, contactUuid, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ContactRole> contactRoles2 = (List<ContactRole>)page2.getItems();

		Assert.assertEquals(contactRoles2.toString(), 1, contactRoles2.size());

		Page<ContactRole> page3 =
			contactRoleResource.getTeamTeamKeyContactByUuidContactUuidRolesPage(
				teamKey, contactUuid, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(contactRole1, contactRole2, contactRole3),
			(List<ContactRole>)page3.getItems());
	}

	protected ContactRole
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_addContactRole(
				String teamKey, String contactUuid, ContactRole contactRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_getTeamKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_getIrrelevantTeamKey()
		throws Exception {

		return null;
	}

	protected String
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_getContactUuid()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetTeamTeamKeyContactByUuidContactUuidRolesPage_getIrrelevantContactUuid()
		throws Exception {

		return null;
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertContains(
		ContactRole contactRole, List<ContactRole> contactRoles) {

		boolean contains = false;

		for (ContactRole item : contactRoles) {
			if (equals(contactRole, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			contactRoles + " does not contain " + contactRole, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		ContactRole contactRole1, ContactRole contactRole2) {

		Assert.assertTrue(
			contactRole1 + " does not equal " + contactRole2,
			equals(contactRole1, contactRole2));
	}

	protected void assertEquals(
		List<ContactRole> contactRoles1, List<ContactRole> contactRoles2) {

		Assert.assertEquals(contactRoles1.size(), contactRoles2.size());

		for (int i = 0; i < contactRoles1.size(); i++) {
			ContactRole contactRole1 = contactRoles1.get(i);
			ContactRole contactRole2 = contactRoles2.get(i);

			assertEquals(contactRole1, contactRole2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<ContactRole> contactRoles1, List<ContactRole> contactRoles2) {

		Assert.assertEquals(contactRoles1.size(), contactRoles2.size());

		for (ContactRole contactRole1 : contactRoles1) {
			boolean contains = false;

			for (ContactRole contactRole2 : contactRoles2) {
				if (equals(contactRole1, contactRole2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				contactRoles2 + " does not contain " + contactRole1, contains);
		}
	}

	protected void assertValid(ContactRole contactRole) throws Exception {
		boolean valid = true;

		if (contactRole.getDateCreated() == null) {
			valid = false;
		}

		if (contactRole.getDateModified() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (contactRole.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (contactRole.getExternalLinks() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (contactRole.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (contactRole.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("system", additionalAssertFieldName)) {
				if (contactRole.getSystem() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (contactRole.getType() == null) {
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

	protected void assertValid(Page<ContactRole> page) {
		boolean valid = false;

		java.util.Collection<ContactRole> contactRoles = page.getItems();

		int size = contactRoles.size();

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
					com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.
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
		ContactRole contactRole1, ContactRole contactRole2) {

		if (contactRole1 == contactRole2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contactRole1.getDateCreated(),
						contactRole2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contactRole1.getDateModified(),
						contactRole2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contactRole1.getDescription(),
						contactRole2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contactRole1.getExternalLinks(),
						contactRole2.getExternalLinks())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contactRole1.getKey(), contactRole2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contactRole1.getName(), contactRole2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("system", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contactRole1.getSystem(), contactRole2.getSystem())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contactRole1.getType(), contactRole2.getType())) {

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

		if (!(_contactRoleResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_contactRoleResource;

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
		EntityField entityField, String operator, ContactRole contactRole) {

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
						DateUtils.addSeconds(
							contactRole.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(contactRole.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(contactRole.getDateCreated()));
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
						DateUtils.addSeconds(
							contactRole.getDateModified(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							contactRole.getDateModified(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(contactRole.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			sb.append("'");
			sb.append(String.valueOf(contactRole.getDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("externalLinks")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(contactRole.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(contactRole.getName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("system")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("type")) {
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

	protected ContactRole randomContactRole() throws Exception {
		return new ContactRole() {
			{
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
				system = RandomTestUtil.randomBoolean();
			}
		};
	}

	protected ContactRole randomIrrelevantContactRole() throws Exception {
		ContactRole randomIrrelevantContactRole = randomContactRole();

		return randomIrrelevantContactRole;
	}

	protected ContactRole randomPatchContactRole() throws Exception {
		return randomContactRole();
	}

	protected ContactRoleResource contactRoleResource;
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
		LogFactoryUtil.getLog(BaseContactRoleResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private
		com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource
			_contactRoleResource;

}