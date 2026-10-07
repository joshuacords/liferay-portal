/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.site.initializer.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearchResponse;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearchResult;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearcher;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.test.rule.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.site.initializer.SiteInitializer;
import com.liferay.site.initializer.SiteInitializerRegistry;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Runs the Object Related Entry site initializer, the fixture meant for
 * manual testing of PTR-9173, and checks that the sample data it creates
 * behaves the way the manual test plan expects: the object definitions and
 * relationships exist, the child entries are indexed as related entries and
 * the roll-up answers the searches described in the cases.
 *
 * @author Joshua Cords
 */
@RunWith(Arquillian.class)
public class ObjectRelatedEntrySiteInitializerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		ServiceContextThreadLocal.pushServiceContext(
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		try (SafeCloseable safeCloseable =
				CompanyThreadLocal.setCompanyIdWithSafeCloseable(
					_group.getCompanyId())) {

			SiteInitializer siteInitializer =
				_siteInitializerRegistry.getSiteInitializer(
					_BUNDLE_SYMBOLIC_NAME);

			siteInitializer.initialize(_group.getGroupId());
		}
	}

	@After
	public void tearDown() throws Exception {
		ServiceContextThreadLocal.popServiceContext();

		for (String externalReferenceCode : _EXTERNAL_REFERENCE_CODES) {
			ObjectDefinition objectDefinition =
				_objectDefinitionLocalService.
					fetchObjectDefinitionByExternalReferenceCode(
						externalReferenceCode, _group.getCompanyId());

			if (objectDefinition != null) {
				_objectDefinitionLocalService.deleteObjectDefinition(
					objectDefinition);
			}
		}
	}

	/**
	 * The definitions and relationships of the manual test plan are in place,
	 * index search is enabled on every definition and the sample Question
	 * entries were indexed as related entries of their Exam.
	 */
	@Test
	public void testInitialize() throws Exception {
		for (String externalReferenceCode : _EXTERNAL_REFERENCE_CODES) {
			ObjectDefinition objectDefinition = _getObjectDefinition(
				externalReferenceCode);

			Assert.assertTrue(
				externalReferenceCode, objectDefinition.isEnableIndexSearch());
		}

		ObjectDefinition examObjectDefinition = _getObjectDefinition(
			"PTR_9173_EXAM");

		ObjectRelationship objectRelationship =
			_objectRelationshipLocalService.
				fetchObjectRelationshipByExternalReferenceCode(
					"PTR_9173_EXAM_TO_QUESTIONS", _group.getCompanyId(),
					examObjectDefinition.getObjectDefinitionId());

		Assert.assertNotNull(objectRelationship);

		ObjectEntry examObjectEntry = _objectEntryLocalService.getObjectEntry(
			"EXAM_JAVA", _group.getGroupId(),
			examObjectDefinition.getObjectDefinitionId());

		ObjectDefinition questionObjectDefinition = _getObjectDefinition(
			"PTR_9173_QUESTION");

		ObjectEntry questionObjectEntry =
			_objectEntryLocalService.getObjectEntry(
				"QUESTION_RECORDS", _group.getGroupId(),
				questionObjectDefinition.getObjectDefinitionId());

		Indexer<ObjectEntry> indexer = IndexerRegistryUtil.getIndexer(
			questionObjectDefinition.getClassName());

		Document document = indexer.getDocument(questionObjectEntry);

		Assert.assertEquals(
			String.valueOf(
				_portal.getClassNameId(examObjectDefinition.getClassName())),
			document.get(Field.CLASS_NAME_ID));
		Assert.assertEquals(
			String.valueOf(examObjectEntry.getObjectEntryId()),
			document.get(Field.CLASS_PK));
		Assert.assertEquals(StringPool.TRUE, document.get(Field.RELATED_ENTRY));
	}

	/**
	 * PTR-9173 case 5 on the sample data: "collector" appears in one Question
	 * and in two of its Answers of the Java exam. Searching Exams returns the
	 * Java exam once, with the three child hits folded into it.
	 */
	@Test
	public void testSearchExamByQuestionAndAnswerText() throws Exception {
		ObjectDefinition examObjectDefinition = _getObjectDefinition(
			"PTR_9173_EXAM");

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			_assertSingleResult(
				_search(examObjectDefinition, "collector"),
				examObjectDefinition,
				_objectEntryLocalService.getObjectEntry(
					"EXAM_JAVA", _group.getGroupId(),
					examObjectDefinition.getObjectDefinitionId()));

		Assert.assertNull(objectRelatedEntrySearchResult.getSearchHit());

		List<SearchHit> relatedEntrySearchHits =
			objectRelatedEntrySearchResult.getRelatedEntrySearchHits();

		Assert.assertEquals(
			relatedEntrySearchHits.toString(), 3,
			relatedEntrySearchHits.size());
	}

	/**
	 * PTR-9173 case 1 on the sample data, in the direction the mechanism
	 * serves: the Case about the printer folds into its Requestor.
	 */
	@Test
	public void testSearchRequestorByCaseSubject() throws Exception {
		ObjectDefinition requestorObjectDefinition = _getObjectDefinition(
			"PTR_9173_REQUESTOR");

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			_assertSingleResult(
				_search(requestorObjectDefinition, "printer"),
				requestorObjectDefinition,
				_objectEntryLocalService.getObjectEntry(
					"REQUESTOR_ADA_LOVELACE", _group.getGroupId(),
					requestorObjectDefinition.getObjectDefinitionId()));

		List<SearchHit> relatedEntrySearchHits =
			objectRelatedEntrySearchResult.getRelatedEntrySearchHits();

		Assert.assertEquals(
			relatedEntrySearchHits.toString(), 1,
			relatedEntrySearchHits.size());
	}

	/**
	 * PTR-9173 case 6 on the sample data, first hop: two Sessions take place
	 * in Porto, one per Training, so searching Trainings for "Porto" returns
	 * both Trainings, each with its own Session folded into it. The Center
	 * named Porto is not part of a Training search.
	 */
	@Test
	public void testSearchTrainingBySessionTitle() throws Exception {
		ObjectDefinition trainingObjectDefinition = _getObjectDefinition(
			"PTR_9173_TRAINING");

		ObjectRelatedEntrySearchResponse objectRelatedEntrySearchResponse =
			_search(trainingObjectDefinition, "Porto");

		List<ObjectRelatedEntrySearchResult> objectRelatedEntrySearchResults =
			objectRelatedEntrySearchResponse.
				getObjectRelatedEntrySearchResults();

		Assert.assertEquals(
			objectRelatedEntrySearchResults.toString(), 2,
			objectRelatedEntrySearchResults.size());

		for (ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult :
				objectRelatedEntrySearchResults) {

			Assert.assertEquals(
				trainingObjectDefinition.getClassName(),
				objectRelatedEntrySearchResult.getClassName());
			Assert.assertNull(objectRelatedEntrySearchResult.getSearchHit());

			List<SearchHit> relatedEntrySearchHits =
				objectRelatedEntrySearchResult.getRelatedEntrySearchHits();

			Assert.assertEquals(
				relatedEntrySearchHits.toString(), 1,
				relatedEntrySearchHits.size());
		}
	}

	/**
	 * PTR-9173 case 2 on the sample data: the Transportation Request to Porto
	 * is found through the name of one of its Engine Types.
	 */
	@Test
	public void testSearchTransportationRequestByEngineTypeName()
		throws Exception {

		ObjectDefinition transportationRequestObjectDefinition =
			_getObjectDefinition("PTR_9173_TRANSPORTATION_REQUEST");

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			_assertSingleResult(
				_search(transportationRequestObjectDefinition, "electric"),
				transportationRequestObjectDefinition,
				_objectEntryLocalService.getObjectEntry(
					"TRANSPORTATION_REQUEST_PORTO", _group.getGroupId(),
					transportationRequestObjectDefinition.
						getObjectDefinitionId()));

		List<SearchHit> relatedEntrySearchHits =
			objectRelatedEntrySearchResult.getRelatedEntrySearchHits();

		Assert.assertEquals(
			relatedEntrySearchHits.toString(), 1,
			relatedEntrySearchHits.size());
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

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

	private ObjectDefinition _getObjectDefinition(String externalReferenceCode)
		throws Exception {

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.
				fetchObjectDefinitionByExternalReferenceCode(
					externalReferenceCode, _group.getCompanyId());

		Assert.assertNotNull(externalReferenceCode, objectDefinition);

		return objectDefinition;
	}

	private ObjectRelatedEntrySearchResponse _search(
			ObjectDefinition objectDefinition, String keywords)
		throws Exception {

		long userId = TestPropsValues.getUserId();

		return _objectRelatedEntrySearcher.search(
			_searchRequestBuilderFactory.builder(
			).companyId(
				_group.getCompanyId()
			).entryClassNames(
				objectDefinition.getClassName()
			).groupIds(
				_group.getGroupId()
			).locale(
				LocaleUtil.US
			).queryString(
				keywords
			).withSearchContext(
				searchContext -> searchContext.setUserId(userId)
			).build());
	}

	private static final String _BUNDLE_SYMBOLIC_NAME =
		"com.liferay.site.initializer.object.related.entry";

	private static final String[] _EXTERNAL_REFERENCE_CODES = {
		"PTR_9173_ANSWER", "PTR_9173_QUESTION", "PTR_9173_EXAM",
		"PTR_9173_CASE", "PTR_9173_REQUESTOR", "PTR_9173_ENGINE_TYPE",
		"PTR_9173_TRANSPORTATION_REQUEST", "PTR_9173_SESSION",
		"PTR_9173_CENTER", "PTR_9173_TRAINING"
	};

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject
	private ObjectRelatedEntrySearcher _objectRelatedEntrySearcher;

	@Inject
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Inject
	private Portal _portal;

	@Inject
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

	@Inject
	private SiteInitializerRegistry _siteInitializerRegistry;

}