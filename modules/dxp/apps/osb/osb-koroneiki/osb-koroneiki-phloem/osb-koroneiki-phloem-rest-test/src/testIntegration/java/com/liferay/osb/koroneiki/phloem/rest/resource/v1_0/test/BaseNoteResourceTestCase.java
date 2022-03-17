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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.NoteResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.NoteSerDes;
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
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

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
public abstract class BaseNoteResourceTestCase {

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

		_noteResource.setContextCompany(testCompany);

		NoteResource.Builder builder = NoteResource.builder();

		noteResource = builder.authentication(
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

		Note note1 = randomNote();

		String json = objectMapper.writeValueAsString(note1);

		Note note2 = NoteSerDes.toDTO(json);

		Assert.assertTrue(equals(note1, note2));
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

		Note note = randomNote();

		String json1 = objectMapper.writeValueAsString(note);
		String json2 = NoteSerDes.toJSON(note);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Note note = randomNote();

		note.setContent(regex);
		note.setCreatorName(regex);
		note.setCreatorUID(regex);
		note.setKey(regex);
		note.setModifierName(regex);
		note.setModifierUID(regex);

		String json = NoteSerDes.toJSON(note);

		Assert.assertFalse(json.contains(regex));

		note = NoteSerDes.toDTO(json);

		Assert.assertEquals(regex, note.getContent());
		Assert.assertEquals(regex, note.getCreatorName());
		Assert.assertEquals(regex, note.getCreatorUID());
		Assert.assertEquals(regex, note.getKey());
		Assert.assertEquals(regex, note.getModifierName());
		Assert.assertEquals(regex, note.getModifierUID());
	}

	@Test
	public void testGetAccountAccountKeyNotesPage() throws Exception {
		String accountKey = testGetAccountAccountKeyNotesPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyNotesPage_getIrrelevantAccountKey();

		Page<Note> page = noteResource.getAccountAccountKeyNotesPage(
			accountKey, null, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantAccountKey != null) {
			Note irrelevantNote = testGetAccountAccountKeyNotesPage_addNote(
				irrelevantAccountKey, randomIrrelevantNote());

			page = noteResource.getAccountAccountKeyNotesPage(
				irrelevantAccountKey, null, null, null, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantNote), (List<Note>)page.getItems());
			assertValid(page);
		}

		Note note1 = testGetAccountAccountKeyNotesPage_addNote(
			accountKey, randomNote());

		Note note2 = testGetAccountAccountKeyNotesPage_addNote(
			accountKey, randomNote());

		page = noteResource.getAccountAccountKeyNotesPage(
			accountKey, null, null, null, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(note1, note2), (List<Note>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyNotesPageWithPagination()
		throws Exception {

		String accountKey = testGetAccountAccountKeyNotesPage_getAccountKey();

		Note note1 = testGetAccountAccountKeyNotesPage_addNote(
			accountKey, randomNote());

		Note note2 = testGetAccountAccountKeyNotesPage_addNote(
			accountKey, randomNote());

		Note note3 = testGetAccountAccountKeyNotesPage_addNote(
			accountKey, randomNote());

		Page<Note> page1 = noteResource.getAccountAccountKeyNotesPage(
			accountKey, null, null, null, Pagination.of(1, 2));

		List<Note> notes1 = (List<Note>)page1.getItems();

		Assert.assertEquals(notes1.toString(), 2, notes1.size());

		Page<Note> page2 = noteResource.getAccountAccountKeyNotesPage(
			accountKey, null, null, null, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Note> notes2 = (List<Note>)page2.getItems();

		Assert.assertEquals(notes2.toString(), 1, notes2.size());

		Page<Note> page3 = noteResource.getAccountAccountKeyNotesPage(
			accountKey, null, null, null, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(note1, note2, note3), (List<Note>)page3.getItems());
	}

	protected Note testGetAccountAccountKeyNotesPage_addNote(
			String accountKey, Note note)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetAccountAccountKeyNotesPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String testGetAccountAccountKeyNotesPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testPostAccountAccountKeyNote() throws Exception {
		Note randomNote = randomNote();

		Note postNote = testPostAccountAccountKeyNote_addNote(randomNote);

		assertEquals(randomNote, postNote);
		assertValid(postNote);
	}

	protected Note testPostAccountAccountKeyNote_addNote(Note note)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteNote() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLDeleteNote() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetNote() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetNote() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetNoteNotFound() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPutNote() throws Exception {
		Assert.assertTrue(false);
	}

	protected void assertContains(Note note, List<Note> notes) {
		boolean contains = false;

		for (Note item : notes) {
			if (equals(note, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(notes + " does not contain " + note, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Note note1, Note note2) {
		Assert.assertTrue(
			note1 + " does not equal " + note2, equals(note1, note2));
	}

	protected void assertEquals(List<Note> notes1, List<Note> notes2) {
		Assert.assertEquals(notes1.size(), notes2.size());

		for (int i = 0; i < notes1.size(); i++) {
			Note note1 = notes1.get(i);
			Note note2 = notes2.get(i);

			assertEquals(note1, note2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Note> notes1, List<Note> notes2) {

		Assert.assertEquals(notes1.size(), notes2.size());

		for (Note note1 : notes1) {
			boolean contains = false;

			for (Note note2 : notes2) {
				if (equals(note1, note2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(notes2 + " does not contain " + note1, contains);
		}
	}

	protected void assertValid(Note note) throws Exception {
		boolean valid = true;

		if (note.getDateCreated() == null) {
			valid = false;
		}

		if (note.getDateModified() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("content", additionalAssertFieldName)) {
				if (note.getContent() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("creatorName", additionalAssertFieldName)) {
				if (note.getCreatorName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("creatorUID", additionalAssertFieldName)) {
				if (note.getCreatorUID() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("format", additionalAssertFieldName)) {
				if (note.getFormat() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (note.getKey() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifierName", additionalAssertFieldName)) {
				if (note.getModifierName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifierUID", additionalAssertFieldName)) {
				if (note.getModifierUID() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("priority", additionalAssertFieldName)) {
				if (note.getPriority() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (note.getStatus() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (note.getType() == null) {
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

	protected void assertValid(Page<Note> page) {
		boolean valid = false;

		java.util.Collection<Note> notes = page.getItems();

		int size = notes.size();

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
					com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note.
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

	protected boolean equals(Note note1, Note note2) {
		if (note1 == note2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("content", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						note1.getContent(), note2.getContent())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("creatorName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						note1.getCreatorName(), note2.getCreatorName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("creatorUID", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						note1.getCreatorUID(), note2.getCreatorUID())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateCreated", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						note1.getDateCreated(), note2.getDateCreated())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("dateModified", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						note1.getDateModified(), note2.getDateModified())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("format", additionalAssertFieldName)) {
				if (!Objects.deepEquals(note1.getFormat(), note2.getFormat())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("key", additionalAssertFieldName)) {
				if (!Objects.deepEquals(note1.getKey(), note2.getKey())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("modifierName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						note1.getModifierName(), note2.getModifierName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modifierUID", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						note1.getModifierUID(), note2.getModifierUID())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("priority", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						note1.getPriority(), note2.getPriority())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (!Objects.deepEquals(note1.getStatus(), note2.getStatus())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (!Objects.deepEquals(note1.getType(), note2.getType())) {
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

		if (!(_noteResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_noteResource;

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
		EntityField entityField, String operator, Note note) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("content")) {
			sb.append("'");
			sb.append(String.valueOf(note.getContent()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("creatorName")) {
			sb.append("'");
			sb.append(String.valueOf(note.getCreatorName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("creatorUID")) {
			sb.append("'");
			sb.append(String.valueOf(note.getCreatorUID()));
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
						DateUtils.addSeconds(note.getDateCreated(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(note.getDateCreated(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(note.getDateCreated()));
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
						DateUtils.addSeconds(note.getDateModified(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(note.getDateModified(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(note.getDateModified()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("format")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("key")) {
			sb.append("'");
			sb.append(String.valueOf(note.getKey()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("modifierName")) {
			sb.append("'");
			sb.append(String.valueOf(note.getModifierName()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("modifierUID")) {
			sb.append("'");
			sb.append(String.valueOf(note.getModifierUID()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("priority")) {
			sb.append(String.valueOf(note.getPriority()));

			return sb.toString();
		}

		if (entityFieldName.equals("status")) {
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

	protected Note randomNote() throws Exception {
		return new Note() {
			{
				content = StringUtil.toLowerCase(RandomTestUtil.randomString());
				creatorName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				creatorUID = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				key = StringUtil.toLowerCase(RandomTestUtil.randomString());
				modifierName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				modifierUID = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				priority = RandomTestUtil.randomInt();
			}
		};
	}

	protected Note randomIrrelevantNote() throws Exception {
		Note randomIrrelevantNote = randomNote();

		return randomIrrelevantNote;
	}

	protected Note randomPatchNote() throws Exception {
		return randomNote();
	}

	protected NoteResource noteResource;
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
		LogFactoryUtil.getLog(BaseNoteResourceTestCase.class);

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
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.NoteResource
		_noteResource;

}