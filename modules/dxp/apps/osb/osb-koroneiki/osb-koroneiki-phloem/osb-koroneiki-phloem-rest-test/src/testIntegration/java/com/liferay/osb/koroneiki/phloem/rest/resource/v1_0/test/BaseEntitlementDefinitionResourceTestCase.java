/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.EntitlementDefinition;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.EntitlementDefinitionResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.EntitlementDefinitionSerDes;
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
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
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
public abstract class BaseEntitlementDefinitionResourceTestCase {

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

		_entitlementDefinitionResource.setContextCompany(testCompany);

		EntitlementDefinitionResource.Builder builder =
			EntitlementDefinitionResource.builder();

		entitlementDefinitionResource = builder.authentication(
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

		EntitlementDefinition entitlementDefinition1 =
			randomEntitlementDefinition();

		String json = objectMapper.writeValueAsString(entitlementDefinition1);

		EntitlementDefinition entitlementDefinition2 =
			EntitlementDefinitionSerDes.toDTO(json);

		Assert.assertTrue(
			equals(entitlementDefinition1, entitlementDefinition2));
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

		EntitlementDefinition entitlementDefinition =
			randomEntitlementDefinition();

		String json1 = objectMapper.writeValueAsString(entitlementDefinition);
		String json2 = EntitlementDefinitionSerDes.toJSON(
			entitlementDefinition);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		EntitlementDefinition entitlementDefinition =
			randomEntitlementDefinition();

		entitlementDefinition.setDefinition(regex);
		entitlementDefinition.setDescription(regex);
		entitlementDefinition.setKey(regex);
		entitlementDefinition.setName(regex);

		String json = EntitlementDefinitionSerDes.toJSON(entitlementDefinition);

		Assert.assertFalse(json.contains(regex));

		entitlementDefinition = EntitlementDefinitionSerDes.toDTO(json);

		Assert.assertEquals(regex, entitlementDefinition.getDefinition());
		Assert.assertEquals(regex, entitlementDefinition.getDescription());
		Assert.assertEquals(regex, entitlementDefinition.getKey());
		Assert.assertEquals(regex, entitlementDefinition.getName());
	}

	@Test
	public void testGetAccountEntitlementDefinitionsPage() throws Exception {
		Page<EntitlementDefinition> page =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				null, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		EntitlementDefinition entitlementDefinition1 =
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition2 =
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		page =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				null, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			entitlementDefinition1,
			(List<EntitlementDefinition>)page.getItems());
		assertContains(
			entitlementDefinition2,
			(List<EntitlementDefinition>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountEntitlementDefinitionsPageWithPagination()
		throws Exception {

		Page<EntitlementDefinition> totalPage =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		EntitlementDefinition entitlementDefinition1 =
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition2 =
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition3 =
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		Page<EntitlementDefinition> page1 =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				null, Pagination.of(1, totalCount + 2));

		List<EntitlementDefinition> entitlementDefinitions1 =
			(List<EntitlementDefinition>)page1.getItems();

		Assert.assertEquals(
			entitlementDefinitions1.toString(), totalCount + 2,
			entitlementDefinitions1.size());

		Page<EntitlementDefinition> page2 =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				null, Pagination.of(2, totalCount + 2));

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<EntitlementDefinition> entitlementDefinitions2 =
			(List<EntitlementDefinition>)page2.getItems();

		Assert.assertEquals(
			entitlementDefinitions2.toString(), 1,
			entitlementDefinitions2.size());

		Page<EntitlementDefinition> page3 =
			entitlementDefinitionResource.getAccountEntitlementDefinitionsPage(
				null, Pagination.of(1, totalCount + 3));

		assertContains(
			entitlementDefinition1,
			(List<EntitlementDefinition>)page3.getItems());
		assertContains(
			entitlementDefinition2,
			(List<EntitlementDefinition>)page3.getItems());
		assertContains(
			entitlementDefinition3,
			(List<EntitlementDefinition>)page3.getItems());
	}

	protected EntitlementDefinition
			testGetAccountEntitlementDefinitionsPage_addEntitlementDefinition(
				EntitlementDefinition entitlementDefinition)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostAccountEntitlementDefinition() throws Exception {
		EntitlementDefinition randomEntitlementDefinition =
			randomEntitlementDefinition();

		EntitlementDefinition postEntitlementDefinition =
			testPostAccountEntitlementDefinition_addEntitlementDefinition(
				randomEntitlementDefinition);

		assertEquals(randomEntitlementDefinition, postEntitlementDefinition);
		assertValid(postEntitlementDefinition);
	}

	protected EntitlementDefinition
			testPostAccountEntitlementDefinition_addEntitlementDefinition(
				EntitlementDefinition entitlementDefinition)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetContactEntitlementDefinitionsPage() throws Exception {
		Page<EntitlementDefinition> page =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				null, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		EntitlementDefinition entitlementDefinition1 =
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition2 =
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		page =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				null, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			entitlementDefinition1,
			(List<EntitlementDefinition>)page.getItems());
		assertContains(
			entitlementDefinition2,
			(List<EntitlementDefinition>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContactEntitlementDefinitionsPageWithPagination()
		throws Exception {

		Page<EntitlementDefinition> totalPage =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		EntitlementDefinition entitlementDefinition1 =
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition2 =
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		EntitlementDefinition entitlementDefinition3 =
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				randomEntitlementDefinition());

		Page<EntitlementDefinition> page1 =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				null, Pagination.of(1, totalCount + 2));

		List<EntitlementDefinition> entitlementDefinitions1 =
			(List<EntitlementDefinition>)page1.getItems();

		Assert.assertEquals(
			entitlementDefinitions1.toString(), totalCount + 2,
			entitlementDefinitions1.size());

		Page<EntitlementDefinition> page2 =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				null, Pagination.of(2, totalCount + 2));

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<EntitlementDefinition> entitlementDefinitions2 =
			(List<EntitlementDefinition>)page2.getItems();

		Assert.assertEquals(
			entitlementDefinitions2.toString(), 1,
			entitlementDefinitions2.size());

		Page<EntitlementDefinition> page3 =
			entitlementDefinitionResource.getContactEntitlementDefinitionsPage(
				null, Pagination.of(1, totalCount + 3));

		assertContains(
			entitlementDefinition1,
			(List<EntitlementDefinition>)page3.getItems());
		assertContains(
			entitlementDefinition2,
			(List<EntitlementDefinition>)page3.getItems());
		assertContains(
			entitlementDefinition3,
			(List<EntitlementDefinition>)page3.getItems());
	}

	protected EntitlementDefinition
			testGetContactEntitlementDefinitionsPage_addEntitlementDefinition(
				EntitlementDefinition entitlementDefinition)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostContactEntitlementDefinition() throws Exception {
		EntitlementDefinition randomEntitlementDefinition =
			randomEntitlementDefinition();

		EntitlementDefinition postEntitlementDefinition =
			testPostContactEntitlementDefinition_addEntitlementDefinition(
				randomEntitlementDefinition);

		assertEquals(randomEntitlementDefinition, postEntitlementDefinition);
		assertValid(postEntitlementDefinition);
	}

	protected EntitlementDefinition
			testPostContactEntitlementDefinition_addEntitlementDefinition(
				EntitlementDefinition entitlementDefinition)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteEntitlementDefinition() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLDeleteEntitlementDefinition() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetEntitlementDefinition() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetEntitlementDefinition() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetEntitlementDefinitionNotFound() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPostEntitlementDefinitionSynchronize() throws Exception {
		Assert.assertTrue(false);
	}

	protected void assertContains(
		EntitlementDefinition entitlementDefinition,
		List<EntitlementDefinition> entitlementDefinitions) {

		boolean contains = false;

		for (EntitlementDefinition item : entitlementDefinitions) {
			if (equals(entitlementDefinition, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			entitlementDefinitions + " does not contain " +
				entitlementDefinition,
			contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		EntitlementDefinition entitlementDefinition1,
		EntitlementDefinition entitlementDefinition2) {

		Assert.assertTrue(
			entitlementDefinition1 + " does not equal " +
				entitlementDefinition2,
			equals(entitlementDefinition1, entitlementDefinition2));
	}

	protected void assertEquals(
		List<EntitlementDefinition> entitlementDefinitions1,
		List<EntitlementDefinition> entitlementDefinitions2) {

		Assert.assertEquals(
			entitlementDefinitions1.size(), entitlementDefinitions2.size());

		for (int i = 0; i < entitlementDefinitions1.size(); i++) {
			EntitlementDefinition entitlementDefinition1 =
				entitlementDefinitions1.get(i);
			EntitlementDefinition entitlementDefinition2 =
				entitlementDefinitions2.get(i);

			assertEquals(entitlementDefinition1, entitlementDefinition2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<EntitlementDefinition> entitlementDefinitions1,
		List<EntitlementDefinition> entitlementDefinitions2) {

		Assert.assertEquals(
			entitlementDefinitions1.size(), entitlementDefinitions2.size());

		for (EntitlementDefinition entitlementDefinition1 :
				entitlementDefinitions1) {

			boolean contains = false;

			for (EntitlementDefinition entitlementDefinition2 :
					entitlementDefinitions2) {

				if (equals(entitlementDefinition1, entitlementDefinition2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				entitlementDefinitions2 + " does not contain " +
					entitlementDefinition1,
				contains);
		}
	}

	protected void assertValid(EntitlementDefinition entitlementDefinition)
		throws Exception {

		boolean valid = true;

		if (entitlementDefinition.getDateCreated() == null) {
			valid = false;
		}

		if (entitlementDefinition.getDateModified() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("definition", additionalAssertFieldName)) {
				if (entitlementDefinition.getDefinition() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (entitlementDefinition.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (entitlementDefinition.getExternalLinks() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (entitlementDefinition.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (entitlementDefinition.getName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (entitlementDefinition.getStatus() == null) {
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

	protected void assertValid(Page<EntitlementDefinition> page) {
		boolean valid = false;

		java.util.Collection<EntitlementDefinition> entitlementDefinitions =
			page.getItems();

		int size = entitlementDefinitions.size();

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
						EntitlementDefinition.class)) {

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
		EntitlementDefinition entitlementDefinition1,
		EntitlementDefinition entitlementDefinition2) {

		if (entitlementDefinition1 == entitlementDefinition2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getDateCreated(),
						entitlementDefinition2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getDateModified(),
						entitlementDefinition2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("definition", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getDefinition(),
						entitlementDefinition2.getDefinition())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getDescription(),
						entitlementDefinition2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("externalLinks", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getExternalLinks(),
						entitlementDefinition2.getExternalLinks())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getKey(),
						entitlementDefinition2.getKey())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("name", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getName(),
						entitlementDefinition2.getName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						entitlementDefinition1.getStatus(),
						entitlementDefinition2.getStatus())) {

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

		if (!(_entitlementDefinitionResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_entitlementDefinitionResource;

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
		EntitlementDefinition entitlementDefinition) {

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
							entitlementDefinition.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							entitlementDefinition.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(
					_dateFormat.format(entitlementDefinition.getDateCreated()));
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
							entitlementDefinition.getDateModified(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							entitlementDefinition.getDateModified(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(
					_dateFormat.format(
						entitlementDefinition.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("definition")) {
			sb.append("'");
			sb.append(String.valueOf(entitlementDefinition.getDefinition()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			sb.append("'");
			sb.append(String.valueOf(entitlementDefinition.getDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("externalLinks")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(entitlementDefinition.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("name")) {
			sb.append("'");
			sb.append(String.valueOf(entitlementDefinition.getName()));
			sb.append("'");

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

	protected EntitlementDefinition randomEntitlementDefinition()
		throws Exception {

		return new EntitlementDefinition() {
			{
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				definition = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				name = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected EntitlementDefinition randomIrrelevantEntitlementDefinition()
		throws Exception {

		EntitlementDefinition randomIrrelevantEntitlementDefinition =
			randomEntitlementDefinition();

		return randomIrrelevantEntitlementDefinition;
	}

	protected EntitlementDefinition randomPatchEntitlementDefinition()
		throws Exception {

		return randomEntitlementDefinition();
	}

	protected EntitlementDefinitionResource entitlementDefinitionResource;
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
		LogFactoryUtil.getLog(BaseEntitlementDefinitionResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.
		EntitlementDefinitionResource _entitlementDefinitionResource;

}