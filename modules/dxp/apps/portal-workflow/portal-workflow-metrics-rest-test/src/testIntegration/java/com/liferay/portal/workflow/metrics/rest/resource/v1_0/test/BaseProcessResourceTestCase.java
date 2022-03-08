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

package com.liferay.portal.workflow.metrics.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

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
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Process;
import com.liferay.portal.workflow.metrics.rest.client.http.HttpInvoker;
import com.liferay.portal.workflow.metrics.rest.client.pagination.Page;
import com.liferay.portal.workflow.metrics.rest.client.pagination.Pagination;
import com.liferay.portal.workflow.metrics.rest.client.resource.v1_0.ProcessResource;
import com.liferay.portal.workflow.metrics.rest.client.serdes.v1_0.ProcessSerDes;

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
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public abstract class BaseProcessResourceTestCase {

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

		_processResource.setContextCompany(testCompany);

		ProcessResource.Builder builder = ProcessResource.builder();

		processResource = builder.authentication(
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

		Process process1 = randomProcess();

		String json = objectMapper.writeValueAsString(process1);

		Process process2 = ProcessSerDes.toDTO(json);

		Assert.assertTrue(equals(process1, process2));
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

		Process process = randomProcess();

		String json1 = objectMapper.writeValueAsString(process);
		String json2 = ProcessSerDes.toJSON(process);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Process process = randomProcess();

		process.setTitle(regex);

		String json = ProcessSerDes.toJSON(process);

		Assert.assertFalse(json.contains(regex));

		process = ProcessSerDes.toDTO(json);

		Assert.assertEquals(regex, process.getTitle());
	}

	@Test
	public void testGetProcessesPage() throws Exception {
		Page<Process> page = processResource.getProcessesPage(
			RandomTestUtil.randomString(), Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		Process process1 = testGetProcessesPage_addProcess(randomProcess());

		Process process2 = testGetProcessesPage_addProcess(randomProcess());

		page = processResource.getProcessesPage(
			null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(process1, (List<Process>)page.getItems());
		assertContains(process2, (List<Process>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetProcessesPageWithPagination() throws Exception {
		Page<Process> totalPage = processResource.getProcessesPage(
			null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		Process process1 = testGetProcessesPage_addProcess(randomProcess());

		Process process2 = testGetProcessesPage_addProcess(randomProcess());

		Process process3 = testGetProcessesPage_addProcess(randomProcess());

		Page<Process> page1 = processResource.getProcessesPage(
			null, Pagination.of(1, totalCount + 2), null);

		List<Process> processes1 = (List<Process>)page1.getItems();

		Assert.assertEquals(
			processes1.toString(), totalCount + 2, processes1.size());

		Page<Process> page2 = processResource.getProcessesPage(
			null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<Process> processes2 = (List<Process>)page2.getItems();

		Assert.assertEquals(processes2.toString(), 1, processes2.size());

		Page<Process> page3 = processResource.getProcessesPage(
			null, Pagination.of(1, totalCount + 3), null);

		assertContains(process1, (List<Process>)page3.getItems());
		assertContains(process2, (List<Process>)page3.getItems());
		assertContains(process3, (List<Process>)page3.getItems());
	}

	@Test
	public void testGetProcessesPageWithSortDateTime() throws Exception {
		testGetProcessesPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, process1, process2) -> {
				BeanUtils.setProperty(
					process1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetProcessesPageWithSortDouble() throws Exception {
		testGetProcessesPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, process1, process2) -> {
				BeanUtils.setProperty(process1, entityField.getName(), 0.1);
				BeanUtils.setProperty(process2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetProcessesPageWithSortInteger() throws Exception {
		testGetProcessesPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, process1, process2) -> {
				BeanUtils.setProperty(process1, entityField.getName(), 0);
				BeanUtils.setProperty(process2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetProcessesPageWithSortString() throws Exception {
		testGetProcessesPageWithSort(
			EntityField.Type.STRING,
			(entityField, process1, process2) -> {
				Class<?> clazz = process1.getClass();

				String entityFieldName = entityField.getName();

				java.lang.reflect.Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						process1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						process2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanUtils.setProperty(
						process1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanUtils.setProperty(
						process2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanUtils.setProperty(
						process1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanUtils.setProperty(
						process2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetProcessesPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, Process, Process, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Process process1 = randomProcess();
		Process process2 = randomProcess();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, process1, process2);
		}

		process1 = testGetProcessesPage_addProcess(process1);

		process2 = testGetProcessesPage_addProcess(process2);

		for (EntityField entityField : entityFields) {
			Page<Process> ascPage = processResource.getProcessesPage(
				null, Pagination.of(1, 2), entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(process1, process2),
				(List<Process>)ascPage.getItems());

			Page<Process> descPage = processResource.getProcessesPage(
				null, Pagination.of(1, 2), entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(process2, process1),
				(List<Process>)descPage.getItems());
		}
	}

	protected Process testGetProcessesPage_addProcess(Process process)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetProcessesPage() throws Exception {
		GraphQLField graphQLField = new GraphQLField(
			"processes",
			new HashMap<String, Object>() {
				{
					put("page", 1);
					put("pageSize", 10);
				}
			},
			new GraphQLField("items", getGraphQLFields()),
			new GraphQLField("page"), new GraphQLField("totalCount"));

		JSONObject processesJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/processes");

		long totalCount = processesJSONObject.getLong("totalCount");

		Process process1 = testGraphQLProcess_addProcess();
		Process process2 = testGraphQLProcess_addProcess();

		processesJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/processes");

		Assert.assertEquals(
			totalCount + 2, processesJSONObject.getLong("totalCount"));

		assertContains(
			process1,
			Arrays.asList(
				ProcessSerDes.toDTOs(processesJSONObject.getString("items"))));
		assertContains(
			process2,
			Arrays.asList(
				ProcessSerDes.toDTOs(processesJSONObject.getString("items"))));
	}

	@Test
	public void testGetProcess() throws Exception {
		Process postProcess = testGetProcess_addProcess();

		Process getProcess = processResource.getProcess(
			postProcess.getId(), null, null, null);

		assertEquals(postProcess, getProcess);
		assertValid(getProcess);
	}

	protected Process testGetProcess_addProcess() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetProcess() throws Exception {
		Process process = testGraphQLProcess_addProcess();

		Assert.assertTrue(
			equals(
				process,
				ProcessSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"process",
								new HashMap<String, Object>() {
									{
										put("processId", process.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/process"))));
	}

	@Test
	public void testGraphQLGetProcessNotFound() throws Exception {
		Long irrelevantProcessId = RandomTestUtil.randomLong();

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"process",
						new HashMap<String, Object>() {
							{
								put("processId", irrelevantProcessId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	@Test
	public void testGetProcessTitle() throws Exception {
		Assert.assertTrue(false);
	}

	protected Process testGraphQLProcess_addProcess() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(Process process, List<Process> processes) {
		boolean contains = false;

		for (Process item : processes) {
			if (equals(process, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(processes + " does not contain " + process, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Process process1, Process process2) {
		Assert.assertTrue(
			process1 + " does not equal " + process2,
			equals(process1, process2));
	}

	protected void assertEquals(
		List<Process> processes1, List<Process> processes2) {

		Assert.assertEquals(processes1.size(), processes2.size());

		for (int i = 0; i < processes1.size(); i++) {
			Process process1 = processes1.get(i);
			Process process2 = processes2.get(i);

			assertEquals(process1, process2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Process> processes1, List<Process> processes2) {

		Assert.assertEquals(processes1.size(), processes2.size());

		for (Process process1 : processes1) {
			boolean contains = false;

			for (Process process2 : processes2) {
				if (equals(process1, process2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				processes2 + " does not contain " + process1, contains);
		}
	}

	protected void assertValid(Process process) throws Exception {
		boolean valid = true;

		if (process.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("instanceCount", additionalAssertFieldName)) {
				if (process.getInstanceCount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"onTimeInstanceCount", additionalAssertFieldName)) {

				if (process.getOnTimeInstanceCount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"overdueInstanceCount", additionalAssertFieldName)) {

				if (process.getOverdueInstanceCount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (process.getTitle() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"untrackedInstanceCount", additionalAssertFieldName)) {

				if (process.getUntrackedInstanceCount() == null) {
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

	protected void assertValid(Page<Process> page) {
		boolean valid = false;

		java.util.Collection<Process> processes = page.getItems();

		int size = processes.size();

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
					com.liferay.portal.workflow.metrics.rest.dto.v1_0.Process.
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

	protected boolean equals(Process process1, Process process2) {
		if (process1 == process2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(process1.getId(), process2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("instanceCount", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						process1.getInstanceCount(),
						process2.getInstanceCount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"onTimeInstanceCount", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						process1.getOnTimeInstanceCount(),
						process2.getOnTimeInstanceCount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"overdueInstanceCount", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						process1.getOverdueInstanceCount(),
						process2.getOverdueInstanceCount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						process1.getTitle(), process2.getTitle())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"untrackedInstanceCount", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						process1.getUntrackedInstanceCount(),
						process2.getUntrackedInstanceCount())) {

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

		if (!(_processResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_processResource;

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
		EntityField entityField, String operator, Process process) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("instanceCount")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("onTimeInstanceCount")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("overdueInstanceCount")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("title")) {
			sb.append("'");
			sb.append(String.valueOf(process.getTitle()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("untrackedInstanceCount")) {
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

	protected Process randomProcess() throws Exception {
		return new Process() {
			{
				id = RandomTestUtil.randomLong();
				instanceCount = RandomTestUtil.randomLong();
				onTimeInstanceCount = RandomTestUtil.randomLong();
				overdueInstanceCount = RandomTestUtil.randomLong();
				title = StringUtil.toLowerCase(RandomTestUtil.randomString());
				untrackedInstanceCount = RandomTestUtil.randomLong();
			}
		};
	}

	protected Process randomIrrelevantProcess() throws Exception {
		Process randomIrrelevantProcess = randomProcess();

		return randomIrrelevantProcess;
	}

	protected Process randomPatchProcess() throws Exception {
		return randomProcess();
	}

	protected ProcessResource processResource;
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
		LogFactoryUtil.getLog(BaseProcessResourceTestCase.class);

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
	private
		com.liferay.portal.workflow.metrics.rest.resource.v1_0.ProcessResource
			_processResource;

}