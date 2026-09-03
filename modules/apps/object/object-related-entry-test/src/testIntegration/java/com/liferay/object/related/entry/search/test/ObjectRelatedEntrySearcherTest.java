/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.field.builder.TextObjectFieldBuilder;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearchResponse;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearchResult;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearcher;
import com.liferay.object.relationship.util.ObjectRelationshipUtil;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.object.test.util.ObjectRelationshipTestUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.portal.search.test.rule.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Query and read time halves of the related entry mechanism applied to
 * Objects, exercised through the SearchRequest framework. Each test builds the
 * object definitions of one PTR-9173 case, indexes a few entries and searches
 * the host definition through ObjectRelatedEntrySearcher, which expands the
 * request to the related definitions and folds the returned hits into their
 * parent in the portal.
 *
 * @author Joshua Cords
 */
@RunWith(Arquillian.class)
public class ObjectRelatedEntrySearcherTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@After
	public void tearDown() throws Exception {
		Collections.reverse(_objectDefinitions);

		for (ObjectDefinition objectDefinition : _objectDefinitions) {
			_objectDefinitionLocalService.deleteObjectDefinition(
				objectDefinition);
		}
	}

	/**
	 * The fold runs in the portal on the page the search engine returned,
	 * after DefaultSearchResultPermissionFilter has seen every hit, so the
	 * total and the page size are counted in child documents: three matching
	 * Questions of one Exam are three hits, a page of two of them folds into a
	 * single Exam result. This is the paging caveat the PTR-9173 analysis
	 * raises against the mechanism, demonstrated rather than hidden.
	 */
	@Test
	public void testFoldRunsAfterPagination() throws Exception {
		ObjectDefinition examObjectDefinition = _publishObjectDefinition(
			"title");
		ObjectDefinition questionObjectDefinition = _publishObjectDefinition(
			"questionText");

		ObjectRelationship objectRelationship = _addObjectRelationship(
			examObjectDefinition, questionObjectDefinition);

		ObjectEntry examObjectEntry = _addObjectEntry(
			examObjectDefinition, "title", RandomTestUtil.randomString());

		String keyword = RandomTestUtil.randomString();

		for (int i = 0; i < 3; i++) {
			_addObjectEntry(
				questionObjectDefinition, "questionText", keyword,
				objectRelationship, examObjectDefinition, examObjectEntry);
		}

		ObjectRelatedEntrySearchResponse objectRelatedEntrySearchResponse =
			_objectRelatedEntrySearcher.search(
				_getSearchRequest(keyword, 2, examObjectDefinition));

		SearchResponse searchResponse =
			objectRelatedEntrySearchResponse.getSearchResponse();

		SearchHits searchHits = searchResponse.getSearchHits();

		Assert.assertEquals(3, searchHits.getTotalHits());

		List<SearchHit> searchHitsList = searchHits.getSearchHits();

		Assert.assertEquals(
			searchHitsList.toString(), 2, searchHitsList.size());

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			_assertSingleResult(
				objectRelatedEntrySearchResponse, examObjectDefinition,
				examObjectEntry);

		Assert.assertNull(objectRelatedEntrySearchResult.getSearchHit());

		List<SearchHit> relatedEntrySearchHits =
			objectRelatedEntrySearchResult.getRelatedEntrySearchHits();

		Assert.assertEquals(
			relatedEntrySearchHits.toString(), 2,
			relatedEntrySearchHits.size());
	}

	/**
	 * PTR-9173 case 1, Requestor 1:N Case, in the direction the ticket asks
	 * for: find a Case by the name of its Requestor. The Requestor is the
	 * parent, and a related entry document only ever points up, at its
	 * parent, so a Requestor hit cannot be attributed to the Cases below it.
	 * The mechanism does not serve this case; see the sibling test for the
	 * direction it does serve.
	 */
	@Test
	public void testSearchCaseByRequestorName() throws Exception {
		ObjectDefinition requestorObjectDefinition = _publishObjectDefinition(
			"name");
		ObjectDefinition caseObjectDefinition = _publishObjectDefinition(
			"subject");

		ObjectRelationship objectRelationship = _addObjectRelationship(
			requestorObjectDefinition, caseObjectDefinition);

		String requestorName = RandomTestUtil.randomString();

		ObjectEntry requestorObjectEntry = _addObjectEntry(
			requestorObjectDefinition, "name", requestorName);

		_addObjectEntry(
			caseObjectDefinition, "subject", RandomTestUtil.randomString(),
			objectRelationship, requestorObjectDefinition,
			requestorObjectEntry);

		ObjectRelatedEntrySearchResponse objectRelatedEntrySearchResponse =
			_objectRelatedEntrySearcher.search(
				_getSearchRequest(requestorName, 10, caseObjectDefinition));

		List<ObjectRelatedEntrySearchResult> objectRelatedEntrySearchResults =
			objectRelatedEntrySearchResponse.
				getObjectRelatedEntrySearchResults();

		Assert.assertTrue(
			objectRelatedEntrySearchResults.toString(),
			objectRelatedEntrySearchResults.isEmpty());
	}

	/**
	 * PTR-9173 case 5, Exam 1:N Question 1:N Answer: find an Exam by text
	 * that lives two hops down, in an Answer. The Answer document points at
	 * its Question, but its ancestor keys also name the Exam, so the fold
	 * attributes the Answer hit to the Exam in one query.
	 */
	@Test
	public void testSearchExamByAnswerText() throws Exception {
		ObjectDefinition examObjectDefinition = _publishObjectDefinition(
			"title");
		ObjectDefinition questionObjectDefinition = _publishObjectDefinition(
			"questionText");
		ObjectDefinition answerObjectDefinition = _publishObjectDefinition(
			"answerText");

		ObjectRelationship examToQuestionsObjectRelationship =
			_addObjectRelationship(
				examObjectDefinition, questionObjectDefinition);
		ObjectRelationship questionToAnswersObjectRelationship =
			_addObjectRelationship(
				questionObjectDefinition, answerObjectDefinition);

		ObjectEntry examObjectEntry = _addObjectEntry(
			examObjectDefinition, "title", RandomTestUtil.randomString());

		ObjectEntry questionObjectEntry = _addObjectEntry(
			questionObjectDefinition, "questionText",
			RandomTestUtil.randomString(), examToQuestionsObjectRelationship,
			examObjectDefinition, examObjectEntry);

		String keyword = RandomTestUtil.randomString();

		ObjectEntry answerObjectEntry = _addObjectEntry(
			answerObjectDefinition, "answerText", keyword,
			questionToAnswersObjectRelationship, questionObjectDefinition,
			questionObjectEntry);

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			_assertSingleResult(
				_objectRelatedEntrySearcher.search(
					_getSearchRequest(keyword, 10, examObjectDefinition)),
				examObjectDefinition, examObjectEntry);

		Assert.assertNull(objectRelatedEntrySearchResult.getSearchHit());

		_assertRelatedEntrySearchHits(
			objectRelatedEntrySearchResult, answerObjectEntry);
	}

	/**
	 * PTR-9173 case 4, Exam 1:N Question: find an Exam by the text of one of
	 * its Questions. The Exam document itself does not match, so the only hit
	 * is the Question document, which is folded into a result keyed on the
	 * Exam because it carries the Exam's classNameId/classPK.
	 */
	@Test
	public void testSearchExamByQuestionText() throws Exception {
		ObjectDefinition examObjectDefinition = _publishObjectDefinition(
			"title");
		ObjectDefinition questionObjectDefinition = _publishObjectDefinition(
			"questionText");

		ObjectRelationship objectRelationship = _addObjectRelationship(
			examObjectDefinition, questionObjectDefinition);

		ObjectEntry examObjectEntry = _addObjectEntry(
			examObjectDefinition, "title", RandomTestUtil.randomString());

		String keyword = RandomTestUtil.randomString();

		ObjectEntry questionObjectEntry = _addObjectEntry(
			questionObjectDefinition, "questionText", keyword,
			objectRelationship, examObjectDefinition, examObjectEntry);

		_addObjectEntry(
			questionObjectDefinition, "questionText",
			RandomTestUtil.randomString(), objectRelationship,
			examObjectDefinition, examObjectEntry);

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			_assertSingleResult(
				_objectRelatedEntrySearcher.search(
					_getSearchRequest(keyword, 10, examObjectDefinition)),
				examObjectDefinition, examObjectEntry);

		Assert.assertNull(objectRelatedEntrySearchResult.getSearchHit());

		_assertRelatedEntrySearchHits(
			objectRelatedEntrySearchResult, questionObjectEntry);
	}

	/**
	 * A Question that belongs to no Exam is indexed as a plain document, and a
	 * Question whose parent is not one of the searched definitions has nothing
	 * to fold into. Both are hits of the expanded query, neither becomes a
	 * result of an Exam search: the folded results stay keyed on the searched
	 * definitions only.
	 */
	@Test
	public void testSearchExamIgnoresUnrelatedQuestions() throws Exception {
		ObjectDefinition examObjectDefinition = _publishObjectDefinition(
			"title");
		ObjectDefinition questionObjectDefinition = _publishObjectDefinition(
			"questionText");

		_addObjectRelationship(examObjectDefinition, questionObjectDefinition);

		String keyword = RandomTestUtil.randomString();

		_addObjectEntry(questionObjectDefinition, "questionText", keyword);

		ObjectRelatedEntrySearchResponse objectRelatedEntrySearchResponse =
			_objectRelatedEntrySearcher.search(
				_getSearchRequest(keyword, 10, examObjectDefinition));

		SearchResponse searchResponse =
			objectRelatedEntrySearchResponse.getSearchResponse();

		SearchHits searchHits = searchResponse.getSearchHits();

		Assert.assertEquals(1, searchHits.getTotalHits());

		List<ObjectRelatedEntrySearchResult> objectRelatedEntrySearchResults =
			objectRelatedEntrySearchResponse.
				getObjectRelatedEntrySearchResults();

		Assert.assertTrue(
			objectRelatedEntrySearchResults.toString(),
			objectRelatedEntrySearchResults.isEmpty());
	}

	/**
	 * When the searched keyword matches both the Exam and one of its
	 * Questions, the two hits fold into one result: the Exam's own hit is the
	 * result's search hit and the Question's hit is attached to it as a
	 * related entry, mirroring how SearchResultTranslatorImpl attaches
	 * attachments to the entry they belong to.
	 */
	@Test
	public void testSearchExamMatchingItselfAndItsQuestion() throws Exception {
		ObjectDefinition examObjectDefinition = _publishObjectDefinition(
			"title");
		ObjectDefinition questionObjectDefinition = _publishObjectDefinition(
			"questionText");

		ObjectRelationship objectRelationship = _addObjectRelationship(
			examObjectDefinition, questionObjectDefinition);

		String keyword = RandomTestUtil.randomString();

		ObjectEntry examObjectEntry = _addObjectEntry(
			examObjectDefinition, "title", keyword);

		ObjectEntry questionObjectEntry = _addObjectEntry(
			questionObjectDefinition, "questionText", keyword,
			objectRelationship, examObjectDefinition, examObjectEntry);

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			_assertSingleResult(
				_objectRelatedEntrySearcher.search(
					_getSearchRequest(keyword, 10, examObjectDefinition)),
				examObjectDefinition, examObjectEntry);

		_assertSearchHit(
			examObjectEntry, objectRelatedEntrySearchResult.getSearchHit());

		_assertRelatedEntrySearchHits(
			objectRelatedEntrySearchResult, questionObjectEntry);
	}

	/**
	 * PTR-9173 case 1, Requestor 1:N Case, in the direction the mechanism
	 * serves: a Case is the many side, so its document points at its
	 * Requestor and a Requestor search matching the Case's subject folds the
	 * Case into its Requestor.
	 */
	@Test
	public void testSearchRequestorByCaseSubject() throws Exception {
		ObjectDefinition requestorObjectDefinition = _publishObjectDefinition(
			"name");
		ObjectDefinition caseObjectDefinition = _publishObjectDefinition(
			"subject");

		ObjectRelationship objectRelationship = _addObjectRelationship(
			requestorObjectDefinition, caseObjectDefinition);

		ObjectEntry requestorObjectEntry = _addObjectEntry(
			requestorObjectDefinition, "name", RandomTestUtil.randomString());

		String keyword = RandomTestUtil.randomString();

		ObjectEntry caseObjectEntry = _addObjectEntry(
			caseObjectDefinition, "subject", keyword, objectRelationship,
			requestorObjectDefinition, requestorObjectEntry);

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			_assertSingleResult(
				_objectRelatedEntrySearcher.search(
					_getSearchRequest(keyword, 10, requestorObjectDefinition)),
				requestorObjectDefinition, requestorObjectEntry);

		_assertRelatedEntrySearchHits(
			objectRelatedEntrySearchResult, caseObjectEntry);
	}

	/**
	 * PTR-9173 case 6, Training 1:N Session together with Center 1:N Session,
	 * first hop: a Session has two parents and one classNameId/classPK pair.
	 * The ancestor keys name both parents, so the same Session document folds
	 * into its Training when Trainings are searched and into its Center when
	 * Centers are searched.
	 */
	@Test
	public void testSearchTrainingAndCenterBySessionTitle() throws Exception {
		ObjectDefinition trainingObjectDefinition = _publishObjectDefinition(
			"title");
		ObjectDefinition centerObjectDefinition = _publishObjectDefinition(
			"name");
		ObjectDefinition sessionObjectDefinition = _publishObjectDefinition(
			"title");

		ObjectRelationship trainingToSessionsObjectRelationship =
			_addObjectRelationship(
				trainingObjectDefinition, sessionObjectDefinition);
		ObjectRelationship centerToSessionsObjectRelationship =
			_addObjectRelationship(
				centerObjectDefinition, sessionObjectDefinition);

		ObjectEntry trainingObjectEntry = _addObjectEntry(
			trainingObjectDefinition, "title", RandomTestUtil.randomString());
		ObjectEntry centerObjectEntry = _addObjectEntry(
			centerObjectDefinition, "name", RandomTestUtil.randomString());

		String keyword = RandomTestUtil.randomString();

		Map<String, Serializable> values = _getValues(
			"title", keyword, trainingToSessionsObjectRelationship,
			trainingObjectDefinition, trainingObjectEntry);

		values.put(
			ObjectRelationshipUtil.getObjectRelationshipFieldName(
				centerObjectDefinition,
				centerToSessionsObjectRelationship.getName()),
			centerObjectEntry.getObjectEntryId());

		ObjectEntry sessionObjectEntry = _addObjectEntry(
			sessionObjectDefinition, values);

		_assertRelatedEntrySearchHits(
			_assertSingleResult(
				_objectRelatedEntrySearcher.search(
					_getSearchRequest(keyword, 10, trainingObjectDefinition)),
				trainingObjectDefinition, trainingObjectEntry),
			sessionObjectEntry);

		_assertRelatedEntrySearchHits(
			_assertSingleResult(
				_objectRelatedEntrySearcher.search(
					_getSearchRequest(keyword, 10, centerObjectDefinition)),
				centerObjectDefinition, centerObjectEntry),
			sessionObjectEntry);
	}

	/**
	 * PTR-9173 case 6, Training 1:N Session together with Center 1:N Session,
	 * second hop: find a Training by the name of the Center of one of its
	 * Sessions. The second hop runs from the Session up to its other parent,
	 * and the Center's name is on the Center document only; the Session
	 * document carries identities, never values. The mechanism does not
	 * serve this case.
	 */
	@Test
	public void testSearchTrainingByCenterName() throws Exception {
		ObjectDefinition trainingObjectDefinition = _publishObjectDefinition(
			"title");
		ObjectDefinition centerObjectDefinition = _publishObjectDefinition(
			"name");
		ObjectDefinition sessionObjectDefinition = _publishObjectDefinition(
			"title");

		ObjectRelationship trainingToSessionsObjectRelationship =
			_addObjectRelationship(
				trainingObjectDefinition, sessionObjectDefinition);
		ObjectRelationship centerToSessionsObjectRelationship =
			_addObjectRelationship(
				centerObjectDefinition, sessionObjectDefinition);

		ObjectEntry trainingObjectEntry = _addObjectEntry(
			trainingObjectDefinition, "title", RandomTestUtil.randomString());

		String centerName = RandomTestUtil.randomString();

		ObjectEntry centerObjectEntry = _addObjectEntry(
			centerObjectDefinition, "name", centerName);

		Map<String, Serializable> values = _getValues(
			"title", RandomTestUtil.randomString(),
			trainingToSessionsObjectRelationship, trainingObjectDefinition,
			trainingObjectEntry);

		values.put(
			ObjectRelationshipUtil.getObjectRelationshipFieldName(
				centerObjectDefinition,
				centerToSessionsObjectRelationship.getName()),
			centerObjectEntry.getObjectEntryId());

		_addObjectEntry(sessionObjectDefinition, values);

		ObjectRelatedEntrySearchResponse objectRelatedEntrySearchResponse =
			_objectRelatedEntrySearcher.search(
				_getSearchRequest(centerName, 10, trainingObjectDefinition));

		List<ObjectRelatedEntrySearchResult> objectRelatedEntrySearchResults =
			objectRelatedEntrySearchResponse.
				getObjectRelatedEntrySearchResults();

		Assert.assertTrue(
			objectRelatedEntrySearchResults.toString(),
			objectRelatedEntrySearchResults.isEmpty());
	}

	/**
	 * Baseline: the same request run through Searcher without the
	 * includeObjectRelatedEntries attribute searches the Exam documents only,
	 * so a Question's text finds nothing. Related entries are strictly opt in.
	 */
	@Test
	public void testSearchWithoutRelatedEntries() throws Exception {
		ObjectDefinition examObjectDefinition = _publishObjectDefinition(
			"title");
		ObjectDefinition questionObjectDefinition = _publishObjectDefinition(
			"questionText");

		ObjectRelationship objectRelationship = _addObjectRelationship(
			examObjectDefinition, questionObjectDefinition);

		ObjectEntry examObjectEntry = _addObjectEntry(
			examObjectDefinition, "title", RandomTestUtil.randomString());

		String keyword = RandomTestUtil.randomString();

		_addObjectEntry(
			questionObjectDefinition, "questionText", keyword,
			objectRelationship, examObjectDefinition, examObjectEntry);

		SearchResponse searchResponse = _searcher.search(
			_getSearchRequest(keyword, 10, examObjectDefinition));

		Assert.assertEquals(0, searchResponse.getTotalHits());
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	private ObjectEntry _addObjectEntry(
			ObjectDefinition objectDefinition, Map<String, Serializable> values)
		throws Exception {

		return _objectEntryLocalService.addObjectEntry(
			0, TestPropsValues.getUserId(),
			objectDefinition.getObjectDefinitionId(),
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			null, values, ServiceContextTestUtil.getServiceContext());
	}

	private ObjectEntry _addObjectEntry(
			ObjectDefinition objectDefinition, String objectFieldName,
			String value)
		throws Exception {

		return _addObjectEntry(
			objectDefinition,
			HashMapBuilder.<String, Serializable>put(
				objectFieldName, value
			).build());
	}

	private ObjectEntry _addObjectEntry(
			ObjectDefinition objectDefinition, String objectFieldName,
			String value, ObjectRelationship objectRelationship,
			ObjectDefinition parentObjectDefinition,
			ObjectEntry parentObjectEntry)
		throws Exception {

		return _addObjectEntry(
			objectDefinition,
			_getValues(
				objectFieldName, value, objectRelationship,
				parentObjectDefinition, parentObjectEntry));
	}

	private ObjectRelationship _addObjectRelationship(
			ObjectDefinition parentObjectDefinition,
			ObjectDefinition childObjectDefinition)
		throws Exception {

		return ObjectRelationshipTestUtil.addObjectRelationship(
			_objectRelationshipLocalService, parentObjectDefinition,
			childObjectDefinition,
			ObjectRelationshipConstants.DELETION_TYPE_CASCADE,
			StringUtil.randomId());
	}

	private void _assertRelatedEntrySearchHits(
		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult,
		ObjectEntry... objectEntries) {

		List<SearchHit> relatedEntrySearchHits =
			objectRelatedEntrySearchResult.getRelatedEntrySearchHits();

		Assert.assertEquals(
			relatedEntrySearchHits.toString(), objectEntries.length,
			relatedEntrySearchHits.size());

		for (int i = 0; i < objectEntries.length; i++) {
			_assertSearchHit(objectEntries[i], relatedEntrySearchHits.get(i));
		}
	}

	private void _assertSearchHit(
		ObjectEntry objectEntry, SearchHit searchHit) {

		Assert.assertNotNull(searchHit);

		Document document = searchHit.getDocument();

		Assert.assertEquals(
			objectEntry.getModelClassName(),
			document.getString(Field.ENTRY_CLASS_NAME));
		Assert.assertEquals(
			objectEntry.getObjectEntryId(),
			(long)document.getLong(Field.ENTRY_CLASS_PK));
	}

	private ObjectRelatedEntrySearchResult _assertSingleResult(
		ObjectRelatedEntrySearchResponse objectRelatedEntrySearchResponse,
		ObjectDefinition objectDefinition, ObjectEntry objectEntry) {

		List<ObjectRelatedEntrySearchResult> objectRelatedEntrySearchResults =
			objectRelatedEntrySearchResponse.
				getObjectRelatedEntrySearchResults();

		Assert.assertEquals(
			objectRelatedEntrySearchResults.toString(), 1,
			objectRelatedEntrySearchResults.size());

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			objectRelatedEntrySearchResults.get(0);

		Assert.assertEquals(
			objectDefinition.getClassName(),
			objectRelatedEntrySearchResult.getClassName());
		Assert.assertEquals(
			objectEntry.getObjectEntryId(),
			objectRelatedEntrySearchResult.getClassPK());

		return objectRelatedEntrySearchResult;
	}

	private SearchRequest _getSearchRequest(
			String keywords, int size, ObjectDefinition objectDefinition)
		throws Exception {

		long userId = TestPropsValues.getUserId();

		return _searchRequestBuilderFactory.builder(
		).companyId(
			TestPropsValues.getCompanyId()
		).entryClassNames(
			objectDefinition.getClassName()
		).locale(
			LocaleUtil.US
		).queryString(
			keywords
		).size(
			size
		).withSearchContext(
			searchContext -> searchContext.setUserId(userId)
		).build();
	}

	private Map<String, Serializable> _getValues(
		String objectFieldName, String value,
		ObjectRelationship objectRelationship,
		ObjectDefinition parentObjectDefinition,
		ObjectEntry parentObjectEntry) {

		return HashMapBuilder.<String, Serializable>put(
			objectFieldName, value
		).put(
			ObjectRelationshipUtil.getObjectRelationshipFieldName(
				parentObjectDefinition, objectRelationship.getName()),
			parentObjectEntry.getObjectEntryId()
		).build();
	}

	private ObjectDefinition _publishObjectDefinition(String objectFieldName)
		throws Exception {

		ObjectDefinition objectDefinition =
			ObjectDefinitionTestUtil.publishObjectDefinition(
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

		_objectDefinitions.add(objectDefinition);

		return objectDefinition;
	}

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	private final List<ObjectDefinition> _objectDefinitions = new ArrayList<>();

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject
	private ObjectRelatedEntrySearcher _objectRelatedEntrySearcher;

	@Inject
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Inject
	private Searcher _searcher;

	@Inject
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

}