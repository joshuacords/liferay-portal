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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactAccountView;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ContactAccountViewResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactAccountViewSerDes;
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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
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
public abstract class BaseContactAccountViewResourceTestCase {

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

		_contactAccountViewResource.setContextCompany(testCompany);

		ContactAccountViewResource.Builder builder =
			ContactAccountViewResource.builder();

		contactAccountViewResource = builder.authentication(
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

		ContactAccountView contactAccountView1 = randomContactAccountView();

		String json = objectMapper.writeValueAsString(contactAccountView1);

		ContactAccountView contactAccountView2 = ContactAccountViewSerDes.toDTO(
			json);

		Assert.assertTrue(equals(contactAccountView1, contactAccountView2));
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

		ContactAccountView contactAccountView = randomContactAccountView();

		String json1 = objectMapper.writeValueAsString(contactAccountView);
		String json2 = ContactAccountViewSerDes.toJSON(contactAccountView);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		ContactAccountView contactAccountView = randomContactAccountView();

		String json = ContactAccountViewSerDes.toJSON(contactAccountView);

		Assert.assertFalse(json.contains(regex));

		contactAccountView = ContactAccountViewSerDes.toDTO(json);
	}

	@Test
	public void testGetContactByUuidContactUuidContactAccountViewsPage()
		throws Exception {

		String contactUuid =
			testGetContactByUuidContactUuidContactAccountViewsPage_getContactUuid();
		String irrelevantContactUuid =
			testGetContactByUuidContactUuidContactAccountViewsPage_getIrrelevantContactUuid();

		Page<ContactAccountView> page =
			contactAccountViewResource.
				getContactByUuidContactUuidContactAccountViewsPage(
					contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantContactUuid != null) {
			ContactAccountView irrelevantContactAccountView =
				testGetContactByUuidContactUuidContactAccountViewsPage_addContactAccountView(
					irrelevantContactUuid,
					randomIrrelevantContactAccountView());

			page =
				contactAccountViewResource.
					getContactByUuidContactUuidContactAccountViewsPage(
						irrelevantContactUuid, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantContactAccountView),
				(List<ContactAccountView>)page.getItems());
			assertValid(page);
		}

		ContactAccountView contactAccountView1 =
			testGetContactByUuidContactUuidContactAccountViewsPage_addContactAccountView(
				contactUuid, randomContactAccountView());

		ContactAccountView contactAccountView2 =
			testGetContactByUuidContactUuidContactAccountViewsPage_addContactAccountView(
				contactUuid, randomContactAccountView());

		page =
			contactAccountViewResource.
				getContactByUuidContactUuidContactAccountViewsPage(
					contactUuid, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(contactAccountView1, contactAccountView2),
			(List<ContactAccountView>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetContactByUuidContactUuidContactAccountViewsPageWithPagination()
		throws Exception {

		String contactUuid =
			testGetContactByUuidContactUuidContactAccountViewsPage_getContactUuid();

		ContactAccountView contactAccountView1 =
			testGetContactByUuidContactUuidContactAccountViewsPage_addContactAccountView(
				contactUuid, randomContactAccountView());

		ContactAccountView contactAccountView2 =
			testGetContactByUuidContactUuidContactAccountViewsPage_addContactAccountView(
				contactUuid, randomContactAccountView());

		ContactAccountView contactAccountView3 =
			testGetContactByUuidContactUuidContactAccountViewsPage_addContactAccountView(
				contactUuid, randomContactAccountView());

		Page<ContactAccountView> page1 =
			contactAccountViewResource.
				getContactByUuidContactUuidContactAccountViewsPage(
					contactUuid, Pagination.of(1, 2));

		List<ContactAccountView> contactAccountViews1 =
			(List<ContactAccountView>)page1.getItems();

		Assert.assertEquals(
			contactAccountViews1.toString(), 2, contactAccountViews1.size());

		Page<ContactAccountView> page2 =
			contactAccountViewResource.
				getContactByUuidContactUuidContactAccountViewsPage(
					contactUuid, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ContactAccountView> contactAccountViews2 =
			(List<ContactAccountView>)page2.getItems();

		Assert.assertEquals(
			contactAccountViews2.toString(), 1, contactAccountViews2.size());

		Page<ContactAccountView> page3 =
			contactAccountViewResource.
				getContactByUuidContactUuidContactAccountViewsPage(
					contactUuid, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(
				contactAccountView1, contactAccountView2, contactAccountView3),
			(List<ContactAccountView>)page3.getItems());
	}

	protected ContactAccountView
			testGetContactByUuidContactUuidContactAccountViewsPage_addContactAccountView(
				String contactUuid, ContactAccountView contactAccountView)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactByUuidContactUuidContactAccountViewsPage_getContactUuid()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetContactByUuidContactUuidContactAccountViewsPage_getIrrelevantContactUuid()
		throws Exception {

		return null;
	}

	protected void assertContains(
		ContactAccountView contactAccountView,
		List<ContactAccountView> contactAccountViews) {

		boolean contains = false;

		for (ContactAccountView item : contactAccountViews) {
			if (equals(contactAccountView, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			contactAccountViews + " does not contain " + contactAccountView,
			contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		ContactAccountView contactAccountView1,
		ContactAccountView contactAccountView2) {

		Assert.assertTrue(
			contactAccountView1 + " does not equal " + contactAccountView2,
			equals(contactAccountView1, contactAccountView2));
	}

	protected void assertEquals(
		List<ContactAccountView> contactAccountViews1,
		List<ContactAccountView> contactAccountViews2) {

		Assert.assertEquals(
			contactAccountViews1.size(), contactAccountViews2.size());

		for (int i = 0; i < contactAccountViews1.size(); i++) {
			ContactAccountView contactAccountView1 = contactAccountViews1.get(
				i);
			ContactAccountView contactAccountView2 = contactAccountViews2.get(
				i);

			assertEquals(contactAccountView1, contactAccountView2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<ContactAccountView> contactAccountViews1,
		List<ContactAccountView> contactAccountViews2) {

		Assert.assertEquals(
			contactAccountViews1.size(), contactAccountViews2.size());

		for (ContactAccountView contactAccountView1 : contactAccountViews1) {
			boolean contains = false;

			for (ContactAccountView contactAccountView2 :
					contactAccountViews2) {

				if (equals(contactAccountView1, contactAccountView2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				contactAccountViews2 + " does not contain " +
					contactAccountView1,
				contains);
		}
	}

	protected void assertValid(ContactAccountView contactAccountView)
		throws Exception {

		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("account", additionalAssertFieldName)) {
				if (contactAccountView.getAccount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"customerContactRoles", additionalAssertFieldName)) {

				if (contactAccountView.getCustomerContactRoles() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"workerContactRoles", additionalAssertFieldName)) {

				if (contactAccountView.getWorkerContactRoles() == null) {
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

	protected void assertValid(Page<ContactAccountView> page) {
		boolean valid = false;

		java.util.Collection<ContactAccountView> contactAccountViews =
			page.getItems();

		int size = contactAccountViews.size();

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
						ContactAccountView.class)) {

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
		ContactAccountView contactAccountView1,
		ContactAccountView contactAccountView2) {

		if (contactAccountView1 == contactAccountView2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("account", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						contactAccountView1.getAccount(),
						contactAccountView2.getAccount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"customerContactRoles", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						contactAccountView1.getCustomerContactRoles(),
						contactAccountView2.getCustomerContactRoles())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"workerContactRoles", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						contactAccountView1.getWorkerContactRoles(),
						contactAccountView2.getWorkerContactRoles())) {

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

		if (!(_contactAccountViewResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_contactAccountViewResource;

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
		ContactAccountView contactAccountView) {

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

		if (entityFieldName.equals("customerContactRoles")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("workerContactRoles")) {
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

	protected ContactAccountView randomContactAccountView() throws Exception {
		return new ContactAccountView() {
			{
			}
		};
	}

	protected ContactAccountView randomIrrelevantContactAccountView()
		throws Exception {

		ContactAccountView randomIrrelevantContactAccountView =
			randomContactAccountView();

		return randomIrrelevantContactAccountView;
	}

	protected ContactAccountView randomPatchContactAccountView()
		throws Exception {

		return randomContactAccountView();
	}

	protected ContactAccountViewResource contactAccountViewResource;
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
		LogFactoryUtil.getLog(BaseContactAccountViewResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.
		ContactAccountViewResource _contactAccountViewResource;

}