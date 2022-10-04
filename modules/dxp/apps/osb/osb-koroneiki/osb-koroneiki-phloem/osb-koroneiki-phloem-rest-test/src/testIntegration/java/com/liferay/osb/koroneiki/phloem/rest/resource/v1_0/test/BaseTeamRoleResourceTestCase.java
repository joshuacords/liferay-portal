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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.TeamRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.TeamRoleSerDes;
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
public abstract class BaseTeamRoleResourceTestCase {

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

		_teamRoleResource.setContextCompany(testCompany);

		TeamRoleResource.Builder builder = TeamRoleResource.builder();

		teamRoleResource = builder.authentication(
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

		TeamRole teamRole1 = randomTeamRole();

		String json = objectMapper.writeValueAsString(teamRole1);

		TeamRole teamRole2 = TeamRoleSerDes.toDTO(json);

		Assert.assertTrue(equals(teamRole1, teamRole2));
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

		TeamRole teamRole = randomTeamRole();

		String json1 = objectMapper.writeValueAsString(teamRole);
		String json2 = TeamRoleSerDes.toJSON(teamRole);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		TeamRole teamRole = randomTeamRole();

		teamRole.setDescription(regex);
		teamRole.setKey(regex);
		teamRole.setName(regex);

		String json = TeamRoleSerDes.toJSON(teamRole);

		Assert.assertFalse(json.contains(regex));

		teamRole = TeamRoleSerDes.toDTO(json);

		Assert.assertEquals(regex, teamRole.getDescription());
		Assert.assertEquals(regex, teamRole.getKey());
		Assert.assertEquals(regex, teamRole.getName());
	}

	@Test
	public void testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_getIrrelevantAccountKey();
		String teamKey =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_getTeamKey();
		String irrelevantTeamKey =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_getIrrelevantTeamKey();

		Page<TeamRole> page =
			teamRoleResource.getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
				accountKey, teamKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantAccountKey != null) && (irrelevantTeamKey != null)) {
			TeamRole irrelevantTeamRole =
				testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_addTeamRole(
					irrelevantAccountKey, irrelevantTeamKey,
					randomIrrelevantTeamRole());

			page =
				teamRoleResource.
					getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
						irrelevantAccountKey, irrelevantTeamKey,
						Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantTeamRole),
				(List<TeamRole>)page.getItems());
			assertValid(page);
		}

		TeamRole teamRole1 =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_addTeamRole(
				accountKey, teamKey, randomTeamRole());

		TeamRole teamRole2 =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_addTeamRole(
				accountKey, teamKey, randomTeamRole());

		page =
			teamRoleResource.getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
				accountKey, teamKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(teamRole1, teamRole2),
			(List<TeamRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyAssignedTeamTeamKeyRolesPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_getAccountKey();
		String teamKey =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_getTeamKey();

		TeamRole teamRole1 =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_addTeamRole(
				accountKey, teamKey, randomTeamRole());

		TeamRole teamRole2 =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_addTeamRole(
				accountKey, teamKey, randomTeamRole());

		TeamRole teamRole3 =
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_addTeamRole(
				accountKey, teamKey, randomTeamRole());

		Page<TeamRole> page1 =
			teamRoleResource.getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
				accountKey, teamKey, Pagination.of(1, 2));

		List<TeamRole> teamRoles1 = (List<TeamRole>)page1.getItems();

		Assert.assertEquals(teamRoles1.toString(), 2, teamRoles1.size());

		Page<TeamRole> page2 =
			teamRoleResource.getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
				accountKey, teamKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<TeamRole> teamRoles2 = (List<TeamRole>)page2.getItems();

		Assert.assertEquals(teamRoles2.toString(), 1, teamRoles2.size());

		Page<TeamRole> page3 =
			teamRoleResource.getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
				accountKey, teamKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(teamRole1, teamRole2, teamRole3),
			(List<TeamRole>)page3.getItems());
	}

	protected TeamRole
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_addTeamRole(
				String accountKey, String teamKey, TeamRole teamRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	protected String
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_getTeamKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyAssignedTeamTeamKeyRolesPage_getIrrelevantTeamKey()
		throws Exception {

		return null;
	}

	@Test
	public void testGetTeamRolesPage() throws Exception {
		Page<TeamRole> page = teamRoleResource.getTeamRolesPage(
			null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		TeamRole teamRole1 = testGetTeamRolesPage_addTeamRole(randomTeamRole());

		TeamRole teamRole2 = testGetTeamRolesPage_addTeamRole(randomTeamRole());

		page = teamRoleResource.getTeamRolesPage(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(teamRole1, (List<TeamRole>)page.getItems());
		assertContains(teamRole2, (List<TeamRole>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetTeamRolesPageWithFilterDateTimeEquals()
		throws Exception {

		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		TeamRole teamRole1 = randomTeamRole();

		teamRole1 = testGetTeamRolesPage_addTeamRole(teamRole1);

		for (EntityField entityField : entityFields) {
			Page<TeamRole> page = teamRoleResource.getTeamRolesPage(
				null, getFilterString(entityField, "between", teamRole1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(teamRole1),
				(List<TeamRole>)page.getItems());
		}
	}

	@Test
	public void testGetTeamRolesPageWithFilterDoubleEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DOUBLE);

		if (entityFields.isEmpty()) {
			return;
		}

		TeamRole teamRole1 = testGetTeamRolesPage_addTeamRole(randomTeamRole());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		TeamRole teamRole2 = testGetTeamRolesPage_addTeamRole(randomTeamRole());

		for (EntityField entityField : entityFields) {
			Page<TeamRole> page = teamRoleResource.getTeamRolesPage(
				null, getFilterString(entityField, "eq", teamRole1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(teamRole1),
				(List<TeamRole>)page.getItems());
		}
	}

	@Test
	public void testGetTeamRolesPageWithFilterStringEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		TeamRole teamRole1 = testGetTeamRolesPage_addTeamRole(randomTeamRole());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		TeamRole teamRole2 = testGetTeamRolesPage_addTeamRole(randomTeamRole());

		for (EntityField entityField : entityFields) {
			Page<TeamRole> page = teamRoleResource.getTeamRolesPage(
				null, getFilterString(entityField, "eq", teamRole1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(teamRole1),
				(List<TeamRole>)page.getItems());
		}
	}

	@Test
	public void testGetTeamRolesPageWithPagination() throws Exception {
		Page<TeamRole> totalPage = teamRoleResource.getTeamRolesPage(
			null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		TeamRole teamRole1 = testGetTeamRolesPage_addTeamRole(randomTeamRole());

		TeamRole teamRole2 = testGetTeamRolesPage_addTeamRole(randomTeamRole());

		TeamRole teamRole3 = testGetTeamRolesPage_addTeamRole(randomTeamRole());

		Page<TeamRole> page1 = teamRoleResource.getTeamRolesPage(
			null, null, Pagination.of(1, totalCount + 2), null);

		List<TeamRole> teamRoles1 = (List<TeamRole>)page1.getItems();

		Assert.assertEquals(
			teamRoles1.toString(), totalCount + 2, teamRoles1.size());

		Page<TeamRole> page2 = teamRoleResource.getTeamRolesPage(
			null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<TeamRole> teamRoles2 = (List<TeamRole>)page2.getItems();

		Assert.assertEquals(teamRoles2.toString(), 1, teamRoles2.size());

		Page<TeamRole> page3 = teamRoleResource.getTeamRolesPage(
			null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(teamRole1, (List<TeamRole>)page3.getItems());
		assertContains(teamRole2, (List<TeamRole>)page3.getItems());
		assertContains(teamRole3, (List<TeamRole>)page3.getItems());
	}

	@Test
	public void testGetTeamRolesPageWithSortDateTime() throws Exception {
		testGetTeamRolesPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, teamRole1, teamRole2) -> {
				BeanTestUtil.setProperty(
					teamRole1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetTeamRolesPageWithSortDouble() throws Exception {
		testGetTeamRolesPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, teamRole1, teamRole2) -> {
				BeanTestUtil.setProperty(teamRole1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(teamRole2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetTeamRolesPageWithSortInteger() throws Exception {
		testGetTeamRolesPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, teamRole1, teamRole2) -> {
				BeanTestUtil.setProperty(teamRole1, entityField.getName(), 0);
				BeanTestUtil.setProperty(teamRole2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetTeamRolesPageWithSortString() throws Exception {
		testGetTeamRolesPageWithSort(
			EntityField.Type.STRING,
			(entityField, teamRole1, teamRole2) -> {
				Class<?> clazz = teamRole1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						teamRole1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						teamRole2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						teamRole1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						teamRole2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						teamRole1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						teamRole2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetTeamRolesPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, TeamRole, TeamRole, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		TeamRole teamRole1 = randomTeamRole();
		TeamRole teamRole2 = randomTeamRole();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, teamRole1, teamRole2);
		}

		teamRole1 = testGetTeamRolesPage_addTeamRole(teamRole1);

		teamRole2 = testGetTeamRolesPage_addTeamRole(teamRole2);

		for (EntityField entityField : entityFields) {
			Page<TeamRole> ascPage = teamRoleResource.getTeamRolesPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(teamRole1, teamRole2),
				(List<TeamRole>)ascPage.getItems());

			Page<TeamRole> descPage = teamRoleResource.getTeamRolesPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(teamRole2, teamRole1),
				(List<TeamRole>)descPage.getItems());
		}
	}

	protected TeamRole testGetTeamRolesPage_addTeamRole(TeamRole teamRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetTeamRolesPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPostTeamRole() throws Exception {
		TeamRole randomTeamRole = randomTeamRole();

		TeamRole postTeamRole = testPostTeamRole_addTeamRole(randomTeamRole);

		assertEquals(randomTeamRole, postTeamRole);
		assertValid(postTeamRole);
	}

	protected TeamRole testPostTeamRole_addTeamRole(TeamRole teamRole)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetTeamRoleByTypeTeamRoleTypeByNameTeamRoleName()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetTeamRoleByTypeTeamRoleTypeByNameTeamRoleName()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetTeamRoleByTypeTeamRoleTypeByNameTeamRoleNameNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testDeleteTeamRole() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLDeleteTeamRole() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetTeamRole() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetTeamRole() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetTeamRoleNotFound() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPutTeamRole() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteTeamRoleTeamRolePermission() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPutTeamRoleTeamRolePermission() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		TeamRole teamRole = testPutTeamRoleTeamRolePermission_addTeamRole();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			teamRoleResource.putTeamRoleTeamRolePermissionHttpResponse(
				null, null, null, null));

		assertHttpResponseStatusCode(
			404,
			teamRoleResource.putTeamRoleTeamRolePermissionHttpResponse(
				null, null, null, null));
	}

	protected TeamRole testPutTeamRoleTeamRolePermission_addTeamRole()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetTeamRolesTeamRoleTypeTeamRoleName() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetTeamRolesTeamRoleTypeTeamRoleName()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetTeamRolesTeamRoleTypeTeamRoleNameNotFound()
		throws Exception {

		Assert.assertTrue(true);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertContains(TeamRole teamRole, List<TeamRole> teamRoles) {
		boolean contains = false;

		for (TeamRole item : teamRoles) {
			if (equals(teamRole, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			teamRoles + " does not contain " + teamRole, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(TeamRole teamRole1, TeamRole teamRole2) {
		Assert.assertTrue(
			teamRole1 + " does not equal " + teamRole2,
			equals(teamRole1, teamRole2));
	}

	protected void assertEquals(
		List<TeamRole> teamRoles1, List<TeamRole> teamRoles2) {

		Assert.assertEquals(teamRoles1.size(), teamRoles2.size());

		for (int i = 0; i < teamRoles1.size(); i++) {
			TeamRole teamRole1 = teamRoles1.get(i);
			TeamRole teamRole2 = teamRoles2.get(i);

			assertEquals(teamRole1, teamRole2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<TeamRole> teamRoles1, List<TeamRole> teamRoles2) {

		Assert.assertEquals(teamRoles1.size(), teamRoles2.size());

		for (TeamRole teamRole1 : teamRoles1) {
			boolean contains = false;

			for (TeamRole teamRole2 : teamRoles2) {
				if (equals(teamRole1, teamRole2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				teamRoles2 + " does not contain " + teamRole1, contains);
		}
	}

	protected void assertValid(TeamRole teamRole) throws Exception {
		boolean valid = true;

		if (teamRole.getDateCreated() == null) {
			valid = false;
		}

		if (teamRole.getDateModified() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (teamRole.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (teamRole.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (teamRole.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (teamRole.getType() == null) {
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

	protected void assertValid(Page<TeamRole> page) {
		boolean valid = false;

		java.util.Collection<TeamRole> teamRoles = page.getItems();

		int size = teamRoles.size();

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
					com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.
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

	protected boolean equals(TeamRole teamRole1, TeamRole teamRole2) {
		if (teamRole1 == teamRole2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						teamRole1.getDateCreated(),
						teamRole2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						teamRole1.getDateModified(),
						teamRole2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						teamRole1.getDescription(),
						teamRole2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						teamRole1.getKey(), teamRole2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						teamRole1.getName(), teamRole2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						teamRole1.getType(), teamRole2.getType())) {

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

		if (!(_teamRoleResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_teamRoleResource;

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
		EntityField entityField, String operator, TeamRole teamRole) {

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
						DateUtils.addSeconds(teamRole.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(teamRole.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(teamRole.getDateCreated()));
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
						DateUtils.addSeconds(teamRole.getDateModified(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(teamRole.getDateModified(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(teamRole.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			sb.append("'");
			sb.append(String.valueOf(teamRole.getDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(teamRole.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(teamRole.getName()));
			sb.append("'");

			return sb.toString();
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

	protected TeamRole randomTeamRole() throws Exception {
		return new TeamRole() {
			{
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected TeamRole randomIrrelevantTeamRole() throws Exception {
		TeamRole randomIrrelevantTeamRole = randomTeamRole();

		return randomIrrelevantTeamRole;
	}

	protected TeamRole randomPatchTeamRole() throws Exception {
		return randomTeamRole();
	}

	protected TeamRoleResource teamRoleResource;
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
		LogFactoryUtil.getLog(BaseTeamRoleResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamRoleResource
		_teamRoleResource;

}