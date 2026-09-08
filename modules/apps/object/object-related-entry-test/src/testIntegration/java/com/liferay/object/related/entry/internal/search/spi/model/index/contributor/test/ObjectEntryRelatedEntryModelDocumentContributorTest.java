/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.internal.search.spi.model.index.contributor.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.field.builder.TextObjectFieldBuilder;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.entry.constants.ObjectRelatedEntryConstants;
import com.liferay.object.relationship.util.ObjectRelationshipUtil;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.object.test.util.ObjectRelationshipTestUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Index time half of the related entry mechanism applied to Objects. Every
 * test builds the document of a child object entry through the indexer of its
 * object definition, exactly as a reindex would, and inspects the fields the
 * contributor stamped on it. No search engine round trip is involved.
 *
 * @author Joshua Cords
 */
@RunWith(Arquillian.class)
public class ObjectEntryRelatedEntryModelDocumentContributorTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_examObjectDefinition = _publishObjectDefinition("title");
		_questionObjectDefinition = _publishObjectDefinition("questionText");
		_answerObjectDefinition = _publishObjectDefinition("answerText");
		_centerObjectDefinition = _publishObjectDefinition("name");

		_examToQuestionsObjectRelationship = _addObjectRelationship(
			_examObjectDefinition, _questionObjectDefinition,
			"examToQuestions");
		_questionToAnswersObjectRelationship = _addObjectRelationship(
			_questionObjectDefinition, _answerObjectDefinition,
			"questionToAnswers");
		_centerToQuestionsObjectRelationship = _addObjectRelationship(
			_centerObjectDefinition, _questionObjectDefinition,
			"centerToQuestions");
	}

	@After
	public void tearDown() throws Exception {
		_objectDefinitionLocalService.deleteObjectDefinition(
			_answerObjectDefinition);
		_objectDefinitionLocalService.deleteObjectDefinition(
			_questionObjectDefinition);
		_objectDefinitionLocalService.deleteObjectDefinition(
			_centerObjectDefinition);
		_objectDefinitionLocalService.deleteObjectDefinition(
			_examObjectDefinition);
	}

	/**
	 * PTR-9173 case 4, Exam 1:N Question, at index time. Like a comment
	 * carries the identity of the entry it comments on, the Question document
	 * carries classNameId/classPK of its Exam and relatedEntry=true. Nothing
	 * from the Exam is copied as searchable text.
	 */
	@Test
	public void testContributeStampsParentIdentity() throws Exception {
		ObjectEntry examObjectEntry = _addObjectEntry(
			_examObjectDefinition, Collections.emptyMap());

		ObjectEntry questionObjectEntry = _addObjectEntry(
			_questionObjectDefinition,
			_getValues(
				_examToQuestionsObjectRelationship, _examObjectDefinition,
				examObjectEntry));

		Document document = _getDocument(questionObjectEntry);

		Assert.assertEquals(
			String.valueOf(
				_portal.getClassNameId(_examObjectDefinition.getClassName())),
			document.get(Field.CLASS_NAME_ID));
		Assert.assertEquals(
			String.valueOf(examObjectEntry.getObjectEntryId()),
			document.get(Field.CLASS_PK));
		Assert.assertEquals(StringPool.TRUE, document.get(Field.RELATED_ENTRY));
		Assert.assertArrayEquals(
			new String[] {_getRelatedEntryKey(examObjectEntry)},
			document.getValues(
				ObjectRelatedEntryConstants.FIELD_RELATED_ENTRY_ANCESTOR_KEYS));
	}

	/**
	 * A parent object entry is never a related entry itself, and a child
	 * object entry that is not related to any parent is indexed as a plain
	 * document. Both keep behaving like every other object entry.
	 */
	@Test
	public void testContributeWithoutParent() throws Exception {
		ObjectEntry examObjectEntry = _addObjectEntry(
			_examObjectDefinition, Collections.emptyMap());

		_assertNotRelatedEntry(_getDocument(examObjectEntry));

		ObjectEntry questionObjectEntry = _addObjectEntry(
			_questionObjectDefinition, Collections.emptyMap());

		_assertNotRelatedEntry(_getDocument(questionObjectEntry));
	}

	/**
	 * PTR-9173 case 5, Exam 1:N Question 1:N Answer, at index time. The
	 * Answer document points at its Question through classNameId/classPK, and
	 * the ancestor keys also name the Exam two hops up, so the Answer can be
	 * folded into the Exam without a second query.
	 */
	@Test
	public void testContributeWithTwoLevels() throws Exception {
		ObjectEntry examObjectEntry = _addObjectEntry(
			_examObjectDefinition, Collections.emptyMap());

		ObjectEntry questionObjectEntry = _addObjectEntry(
			_questionObjectDefinition,
			_getValues(
				_examToQuestionsObjectRelationship, _examObjectDefinition,
				examObjectEntry));

		ObjectEntry answerObjectEntry = _addObjectEntry(
			_answerObjectDefinition,
			_getValues(
				_questionToAnswersObjectRelationship, _questionObjectDefinition,
				questionObjectEntry));

		Document document = _getDocument(answerObjectEntry);

		Assert.assertEquals(
			String.valueOf(
				_portal.getClassNameId(
					_questionObjectDefinition.getClassName())),
			document.get(Field.CLASS_NAME_ID));
		Assert.assertEquals(
			String.valueOf(questionObjectEntry.getObjectEntryId()),
			document.get(Field.CLASS_PK));
		Assert.assertEquals(
			Arrays.asList(
				_getRelatedEntryKey(questionObjectEntry),
				_getRelatedEntryKey(examObjectEntry)),
			Arrays.asList(
				document.getValues(
					ObjectRelatedEntryConstants.
						FIELD_RELATED_ENTRY_ANCESTOR_KEYS)));
	}

	/**
	 * PTR-9173 case 6, Training 1:N Session together with Center 1:N Session,
	 * at index time. A child with two parents has only one
	 * classNameId/classPK pair, so the primary parent, the relationship that
	 * comes first by name, lends the child its identity and therefore its
	 * ACL. The ancestor keys name both parents so that the fold can pick the
	 * one being searched.
	 */
	@Test
	public void testContributeWithTwoParents() throws Exception {
		ObjectEntry centerObjectEntry = _addObjectEntry(
			_centerObjectDefinition, Collections.emptyMap());

		ObjectEntry examObjectEntry = _addObjectEntry(
			_examObjectDefinition, Collections.emptyMap());

		Map<String, Serializable> values = _getValues(
			_examToQuestionsObjectRelationship, _examObjectDefinition,
			examObjectEntry);

		values.putAll(
			_getValues(
				_centerToQuestionsObjectRelationship, _centerObjectDefinition,
				centerObjectEntry));

		ObjectEntry questionObjectEntry = _addObjectEntry(
			_questionObjectDefinition, values);

		Document document = _getDocument(questionObjectEntry);

		Assert.assertEquals(
			String.valueOf(
				_portal.getClassNameId(_centerObjectDefinition.getClassName())),
			document.get(Field.CLASS_NAME_ID));
		Assert.assertEquals(
			String.valueOf(centerObjectEntry.getObjectEntryId()),
			document.get(Field.CLASS_PK));
		Assert.assertEquals(
			Arrays.asList(
				_getRelatedEntryKey(centerObjectEntry),
				_getRelatedEntryKey(examObjectEntry)),
			Arrays.asList(
				document.getValues(
					ObjectRelatedEntryConstants.
						FIELD_RELATED_ENTRY_ANCESTOR_KEYS)));
	}

	private ObjectEntry _addObjectEntry(
			ObjectDefinition objectDefinition, Map<String, Serializable> values)
		throws Exception {

		return _objectEntryLocalService.addObjectEntry(
			0, TestPropsValues.getUserId(),
			objectDefinition.getObjectDefinitionId(),
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			null, values, ServiceContextTestUtil.getServiceContext());
	}

	private ObjectRelationship _addObjectRelationship(
			ObjectDefinition parentObjectDefinition,
			ObjectDefinition childObjectDefinition, String name)
		throws Exception {

		return ObjectRelationshipTestUtil.addObjectRelationship(
			_objectRelationshipLocalService, parentObjectDefinition,
			childObjectDefinition,
			ObjectRelationshipConstants.DELETION_TYPE_CASCADE, name);
	}

	private void _assertNotRelatedEntry(Document document) {
		Assert.assertNull(document.get(Field.CLASS_NAME_ID));
		Assert.assertNull(document.get(Field.CLASS_PK));
		Assert.assertNull(document.get(Field.RELATED_ENTRY));
		Assert.assertNull(
			document.get(
				ObjectRelatedEntryConstants.FIELD_RELATED_ENTRY_ANCESTOR_KEYS));
	}

	private Document _getDocument(ObjectEntry objectEntry) throws Exception {
		Indexer<ObjectEntry> indexer = IndexerRegistryUtil.getIndexer(
			objectEntry.getModelClassName());

		return indexer.getDocument(objectEntry);
	}

	private String _getRelatedEntryKey(ObjectEntry objectEntry) {
		return StringBundler.concat(
			_portal.getClassNameId(objectEntry.getModelClassName()),
			StringPool.DASH, objectEntry.getObjectEntryId());
	}

	private Map<String, Serializable> _getValues(
		ObjectRelationship objectRelationship,
		ObjectDefinition parentObjectDefinition,
		ObjectEntry parentObjectEntry) {

		return HashMapBuilder.<String, Serializable>put(
			ObjectRelationshipUtil.getObjectRelationshipFieldName(
				parentObjectDefinition, objectRelationship.getName()),
			parentObjectEntry.getObjectEntryId()
		).build();
	}

	private ObjectDefinition _publishObjectDefinition(String objectFieldName)
		throws Exception {

		return ObjectDefinitionTestUtil.publishObjectDefinition(
			Collections.singletonList(
				new TextObjectFieldBuilder(
				).indexed(
					true
				).indexedAsKeyword(
					false
				).labelMap(
					RandomTestUtil.randomLocaleStringMap()
				).name(
					objectFieldName
				).build()),
			ObjectDefinitionConstants.SCOPE_COMPANY);
	}

	private ObjectDefinition _answerObjectDefinition;
	private ObjectDefinition _centerObjectDefinition;
	private ObjectRelationship _centerToQuestionsObjectRelationship;
	private ObjectDefinition _examObjectDefinition;
	private ObjectRelationship _examToQuestionsObjectRelationship;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Inject
	private Portal _portal;

	private ObjectDefinition _questionObjectDefinition;
	private ObjectRelationship _questionToAnswersObjectRelationship;

}