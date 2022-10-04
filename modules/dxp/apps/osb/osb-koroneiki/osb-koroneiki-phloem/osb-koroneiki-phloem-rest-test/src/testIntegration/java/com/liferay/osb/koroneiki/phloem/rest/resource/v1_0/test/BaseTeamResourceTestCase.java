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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.TeamResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.TeamSerDes;
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
public abstract class BaseTeamResourceTestCase {

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

		_teamResource.setContextCompany(testCompany);

		TeamResource.Builder builder = TeamResource.builder();

		teamResource = builder.authentication(
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

		Team team1 = randomTeam();

		String json = objectMapper.writeValueAsString(team1);

		Team team2 = TeamSerDes.toDTO(json);

		Assert.assertTrue(equals(team1, team2));
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

		Team team = randomTeam();

		String json1 = objectMapper.writeValueAsString(team);
		String json2 = TeamSerDes.toJSON(team);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Team team = randomTeam();

		team.setAccountKey(regex);
		team.setKey(regex);
		team.setName(regex);

		String json = TeamSerDes.toJSON(team);

		Assert.assertFalse(json.contains(regex));

		team = TeamSerDes.toDTO(json);

		Assert.assertEquals(regex, team.getAccountKey());
		Assert.assertEquals(regex, team.getKey());
		Assert.assertEquals(regex, team.getName());
	}

	@Test
	public void testGetAccountAccountKeyAssignedTeamsPage() throws Exception {
		String accountKey =
			testGetAccountAccountKeyAssignedTeamsPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyAssignedTeamsPage_getIrrelevantAccountKey();

		Page<Team> page = teamResource.getAccountAccountKeyAssignedTeamsPage(
			accountKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantAccountKey != null) {
			Team irrelevantTeam =
				testGetAccountAccountKeyAssignedTeamsPage_addTeam(
					irrelevantAccountKey, randomIrrelevantTeam());

			page = teamResource.getAccountAccountKeyAssignedTeamsPage(
				irrelevantAccountKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantTeam), (List<Team>)page.getItems());
			assertValid(page);
		}

		Team team1 = testGetAccountAccountKeyAssignedTeamsPage_addTeam(
			accountKey, randomTeam());

		Team team2 = testGetAccountAccountKeyAssignedTeamsPage_addTeam(
			accountKey, randomTeam());

		page = teamResource.getAccountAccountKeyAssignedTeamsPage(
			accountKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(team1, team2), (List<Team>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyAssignedTeamsPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyAssignedTeamsPage_getAccountKey();

		Team team1 = testGetAccountAccountKeyAssignedTeamsPage_addTeam(
			accountKey, randomTeam());

		Team team2 = testGetAccountAccountKeyAssignedTeamsPage_addTeam(
			accountKey, randomTeam());

		Team team3 = testGetAccountAccountKeyAssignedTeamsPage_addTeam(
			accountKey, randomTeam());

		Page<Team> page1 = teamResource.getAccountAccountKeyAssignedTeamsPage(
			accountKey, Pagination.of(1, 2));

		List<Team> teams1 = (List<Team>)page1.getItems();

		Assert.assertEquals(teams1.toString(), 2, teams1.size());

		Page<Team> page2 = teamResource.getAccountAccountKeyAssignedTeamsPage(
			accountKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Team> teams2 = (List<Team>)page2.getItems();

		Assert.assertEquals(teams2.toString(), 1, teams2.size());

		Page<Team> page3 = teamResource.getAccountAccountKeyAssignedTeamsPage(
			accountKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(team1, team2, team3), (List<Team>)page3.getItems());
	}

	protected Team testGetAccountAccountKeyAssignedTeamsPage_addTeam(
			String accountKey, Team team)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetAccountAccountKeyAssignedTeamsPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyAssignedTeamsPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testGetAccountAccountKeyTeamsPage() throws Exception {
		String accountKey = testGetAccountAccountKeyTeamsPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyTeamsPage_getIrrelevantAccountKey();

		Page<Team> page = teamResource.getAccountAccountKeyTeamsPage(
			accountKey, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantAccountKey != null) {
			Team irrelevantTeam = testGetAccountAccountKeyTeamsPage_addTeam(
				irrelevantAccountKey, randomIrrelevantTeam());

			page = teamResource.getAccountAccountKeyTeamsPage(
				irrelevantAccountKey, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantTeam), (List<Team>)page.getItems());
			assertValid(page);
		}

		Team team1 = testGetAccountAccountKeyTeamsPage_addTeam(
			accountKey, randomTeam());

		Team team2 = testGetAccountAccountKeyTeamsPage_addTeam(
			accountKey, randomTeam());

		page = teamResource.getAccountAccountKeyTeamsPage(
			accountKey, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(team1, team2), (List<Team>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyTeamsPageWithPagination()
		throws Exception {

		String accountKey = testGetAccountAccountKeyTeamsPage_getAccountKey();

		Team team1 = testGetAccountAccountKeyTeamsPage_addTeam(
			accountKey, randomTeam());

		Team team2 = testGetAccountAccountKeyTeamsPage_addTeam(
			accountKey, randomTeam());

		Team team3 = testGetAccountAccountKeyTeamsPage_addTeam(
			accountKey, randomTeam());

		Page<Team> page1 = teamResource.getAccountAccountKeyTeamsPage(
			accountKey, Pagination.of(1, 2));

		List<Team> teams1 = (List<Team>)page1.getItems();

		Assert.assertEquals(teams1.toString(), 2, teams1.size());

		Page<Team> page2 = teamResource.getAccountAccountKeyTeamsPage(
			accountKey, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Team> teams2 = (List<Team>)page2.getItems();

		Assert.assertEquals(teams2.toString(), 1, teams2.size());

		Page<Team> page3 = teamResource.getAccountAccountKeyTeamsPage(
			accountKey, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(team1, team2, team3), (List<Team>)page3.getItems());
	}

	protected Team testGetAccountAccountKeyTeamsPage_addTeam(
			String accountKey, Team team)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetAccountAccountKeyTeamsPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetAccountAccountKeyTeamsPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostAccountAccountKeyTeam() throws Exception {
		Team randomTeam = randomTeam();

		Team postTeam = testPostAccountAccountKeyTeam_addTeam(randomTeam);

		assertEquals(randomTeam, postTeam);
		assertValid(postTeam);
	}

	protected Team testPostAccountAccountKeyTeam_addTeam(Team team)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetTeamsPage() throws Exception {
		Page<Team> page = teamResource.getTeamsPage(
			null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		Team team1 = testGetTeamsPage_addTeam(randomTeam());

		Team team2 = testGetTeamsPage_addTeam(randomTeam());

		page = teamResource.getTeamsPage(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(team1, (List<Team>)page.getItems());
		assertContains(team2, (List<Team>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetTeamsPageWithFilterDateTimeEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Team team1 = randomTeam();

		team1 = testGetTeamsPage_addTeam(team1);

		for (EntityField entityField : entityFields) {
			Page<Team> page = teamResource.getTeamsPage(
				null, getFilterString(entityField, "between", team1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(team1), (List<Team>)page.getItems());
		}
	}

	@Test
	public void testGetTeamsPageWithFilterDoubleEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DOUBLE);

		if (entityFields.isEmpty()) {
			return;
		}

		Team team1 = testGetTeamsPage_addTeam(randomTeam());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Team team2 = testGetTeamsPage_addTeam(randomTeam());

		for (EntityField entityField : entityFields) {
			Page<Team> page = teamResource.getTeamsPage(
				null, getFilterString(entityField, "eq", team1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(team1), (List<Team>)page.getItems());
		}
	}

	@Test
	public void testGetTeamsPageWithFilterStringEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Team team1 = testGetTeamsPage_addTeam(randomTeam());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Team team2 = testGetTeamsPage_addTeam(randomTeam());

		for (EntityField entityField : entityFields) {
			Page<Team> page = teamResource.getTeamsPage(
				null, getFilterString(entityField, "eq", team1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(team1), (List<Team>)page.getItems());
		}
	}

	@Test
	public void testGetTeamsPageWithPagination() throws Exception {
		Page<Team> totalPage = teamResource.getTeamsPage(
			null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		Team team1 = testGetTeamsPage_addTeam(randomTeam());

		Team team2 = testGetTeamsPage_addTeam(randomTeam());

		Team team3 = testGetTeamsPage_addTeam(randomTeam());

		Page<Team> page1 = teamResource.getTeamsPage(
			null, null, Pagination.of(1, totalCount + 2), null);

		List<Team> teams1 = (List<Team>)page1.getItems();

		Assert.assertEquals(teams1.toString(), totalCount + 2, teams1.size());

		Page<Team> page2 = teamResource.getTeamsPage(
			null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<Team> teams2 = (List<Team>)page2.getItems();

		Assert.assertEquals(teams2.toString(), 1, teams2.size());

		Page<Team> page3 = teamResource.getTeamsPage(
			null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(team1, (List<Team>)page3.getItems());
		assertContains(team2, (List<Team>)page3.getItems());
		assertContains(team3, (List<Team>)page3.getItems());
	}

	@Test
	public void testGetTeamsPageWithSortDateTime() throws Exception {
		testGetTeamsPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, team1, team2) -> {
				BeanTestUtil.setProperty(
					team1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetTeamsPageWithSortDouble() throws Exception {
		testGetTeamsPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, team1, team2) -> {
				BeanTestUtil.setProperty(team1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(team2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetTeamsPageWithSortInteger() throws Exception {
		testGetTeamsPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, team1, team2) -> {
				BeanTestUtil.setProperty(team1, entityField.getName(), 0);
				BeanTestUtil.setProperty(team2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetTeamsPageWithSortString() throws Exception {
		testGetTeamsPageWithSort(
			EntityField.Type.STRING,
			(entityField, team1, team2) -> {
				Class<?> clazz = team1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						team1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						team2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						team1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						team2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						team1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						team2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetTeamsPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, Team, Team, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Team team1 = randomTeam();
		Team team2 = randomTeam();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, team1, team2);
		}

		team1 = testGetTeamsPage_addTeam(team1);

		team2 = testGetTeamsPage_addTeam(team2);

		for (EntityField entityField : entityFields) {
			Page<Team> ascPage = teamResource.getTeamsPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(team1, team2), (List<Team>)ascPage.getItems());

			Page<Team> descPage = teamResource.getTeamsPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(team2, team1), (List<Team>)descPage.getItems());
		}
	}

	protected Team testGetTeamsPage_addTeam(Team team) throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetTeamsPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetTeamByExternalLinkDomainEntityNameEntityPage()
		throws Exception {

		String domain =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getDomain();
		String irrelevantDomain =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getIrrelevantDomain();
		String entityName =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getEntityName();
		String irrelevantEntityName =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityName();
		String entityId =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getEntityId();
		String irrelevantEntityId =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityId();

		Page<Team> page =
			teamResource.getTeamByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if ((irrelevantDomain != null) && (irrelevantEntityName != null) &&
			(irrelevantEntityId != null)) {

			Team irrelevantTeam =
				testGetTeamByExternalLinkDomainEntityNameEntityPage_addTeam(
					irrelevantDomain, irrelevantEntityName, irrelevantEntityId,
					randomIrrelevantTeam());

			page = teamResource.getTeamByExternalLinkDomainEntityNameEntityPage(
				irrelevantDomain, irrelevantEntityName, irrelevantEntityId,
				Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantTeam), (List<Team>)page.getItems());
			assertValid(page);
		}

		Team team1 =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_addTeam(
				domain, entityName, entityId, randomTeam());

		Team team2 =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_addTeam(
				domain, entityName, entityId, randomTeam());

		page = teamResource.getTeamByExternalLinkDomainEntityNameEntityPage(
			domain, entityName, entityId, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(team1, team2), (List<Team>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetTeamByExternalLinkDomainEntityNameEntityPageWithPagination()
		throws Exception {

		String domain =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getDomain();
		String entityName =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getEntityName();
		String entityId =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getEntityId();

		Team team1 =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_addTeam(
				domain, entityName, entityId, randomTeam());

		Team team2 =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_addTeam(
				domain, entityName, entityId, randomTeam());

		Team team3 =
			testGetTeamByExternalLinkDomainEntityNameEntityPage_addTeam(
				domain, entityName, entityId, randomTeam());

		Page<Team> page1 =
			teamResource.getTeamByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(1, 2));

		List<Team> teams1 = (List<Team>)page1.getItems();

		Assert.assertEquals(teams1.toString(), 2, teams1.size());

		Page<Team> page2 =
			teamResource.getTeamByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Team> teams2 = (List<Team>)page2.getItems();

		Assert.assertEquals(teams2.toString(), 1, teams2.size());

		Page<Team> page3 =
			teamResource.getTeamByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(team1, team2, team3), (List<Team>)page3.getItems());
	}

	protected Team testGetTeamByExternalLinkDomainEntityNameEntityPage_addTeam(
			String domain, String entityName, String entityId, Team team)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getDomain()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getIrrelevantDomain()
		throws Exception {

		return null;
	}

	protected String
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getEntityName()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityName()
		throws Exception {

		return null;
	}

	protected String
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getEntityId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetTeamByExternalLinkDomainEntityNameEntityPage_getIrrelevantEntityId()
		throws Exception {

		return null;
	}

	@Test
	public void testDeleteTeam() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLDeleteTeam() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetTeam() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetTeam() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetTeamNotFound() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPutTeam() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteTeamContactByEmailAddres() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPutTeamContactByEmailAddres() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteTeamContactByEmailAddresEmailAddressRole()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testPutTeamContactByEmailAddresEmailAddressRole()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteTeamContactByUuid() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPutTeamContactByUuid() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteTeamContactByUuidContactUuidRole() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPutTeamContactByUuidContactUuidRole() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testDeleteTeamTeamPermission() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPutTeamTeamPermission() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		Team team = testPutTeamTeamPermission_addTeam();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			RoleConstants.TYPE_REGULAR);

		assertHttpResponseStatusCode(
			200,
			teamResource.putTeamTeamPermissionHttpResponse(
				null, null, null, null));

		assertHttpResponseStatusCode(
			404,
			teamResource.putTeamTeamPermissionHttpResponse(
				null, null, null, null));
	}

	protected Team testPutTeamTeamPermission_addTeam() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertContains(Team team, List<Team> teams) {
		boolean contains = false;

		for (Team item : teams) {
			if (equals(team, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(teams + " does not contain " + team, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Team team1, Team team2) {
		Assert.assertTrue(
			team1 + " does not equal " + team2, equals(team1, team2));
	}

	protected void assertEquals(List<Team> teams1, List<Team> teams2) {
		Assert.assertEquals(teams1.size(), teams2.size());

		for (int i = 0; i < teams1.size(); i++) {
			Team team1 = teams1.get(i);
			Team team2 = teams2.get(i);

			assertEquals(team1, team2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Team> teams1, List<Team> teams2) {

		Assert.assertEquals(teams1.size(), teams2.size());

		for (Team team1 : teams1) {
			boolean contains = false;

			for (Team team2 : teams2) {
				if (equals(team1, team2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(teams2 + " does not contain " + team1, contains);
		}
	}

	protected void assertValid(Team team) throws Exception {
		boolean valid = true;

		if (team.getDateCreated() == null) {
			valid = false;
		}

		if (team.getDateModified() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("account", additionalAssertFieldName)) {
				if (team.getAccount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("accountKey", additionalAssertFieldName)) {
				if (team.getAccountKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("contacts", additionalAssertFieldName)) {
				if (team.getContacts() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (team.getExternalLinks() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (team.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (team.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("system", additionalAssertFieldName)) {
				if (team.getSystem() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("teamRoles", additionalAssertFieldName)) {
				if (team.getTeamRoles() == null) {
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

	protected void assertValid(Page<Team> page) {
		boolean valid = false;

		java.util.Collection<Team> teams = page.getItems();

		int size = teams.size();

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
					com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team.
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

	protected boolean equals(Team team1, Team team2) {
		if (team1 == team2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("account", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						team1.getAccount(), team2.getAccount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("accountKey", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						team1.getAccountKey(), team2.getAccountKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("contacts", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						team1.getContacts(), team2.getContacts())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						team1.getDateCreated(), team2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						team1.getDateModified(), team2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						team1.getExternalLinks(), team2.getExternalLinks())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(team1.getKey(), team2.getKey())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(team1.getName(), team2.getName())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("system", additionalAssertFieldName)) {
				if (!Objects.deepEquals(team1.getSystem(), team2.getSystem())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("teamRoles", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						team1.getTeamRoles(), team2.getTeamRoles())) {

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

		if (!(_teamResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_teamResource;

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
		EntityField entityField, String operator, Team team) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("account")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("accountKey")) {
			sb.append("'");
			sb.append(String.valueOf(team.getAccountKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("contacts")) {
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
						DateUtils.addSeconds(team.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(team.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(team.getDateCreated()));
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
						DateUtils.addSeconds(team.getDateModified(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(team.getDateModified(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(team.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("externalLinks")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(team.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(team.getName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("system")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("teamRoles")) {
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

	protected Team randomTeam() throws Exception {
		return new Team() {
			{
				accountKey = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
				system = RandomTestUtil.randomBoolean();
			}
		};
	}

	protected Team randomIrrelevantTeam() throws Exception {
		Team randomIrrelevantTeam = randomTeam();

		return randomIrrelevantTeam;
	}

	protected Team randomPatchTeam() throws Exception {
		return randomTeam();
	}

	protected TeamResource teamResource;
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
		LogFactoryUtil.getLog(BaseTeamResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource
		_teamResource;

}