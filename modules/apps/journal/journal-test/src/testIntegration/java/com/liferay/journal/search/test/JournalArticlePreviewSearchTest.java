/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.petra.function.UnsafeSupplierValue;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.MultiValueFacet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.preview.PreviewableResolverUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Proof-of-concept for LPD-92301: prove that Search can honor a Preview Context
 * Model (swap an approved/live version for a specific draft version) for
 * JournalArticle, entirely inside the search query.
 *
 * <p>
 * The preview context is the one {@code LaunchPreviewFilter} sets: a
 * {@link PreviewableResolverUtil} previewable map keyed by the live
 * {@link JournalArticle#getId()}, whose values are the draft version's
 * primary key or an {@link UnsafeSupplierValue} that supplies the draft.
 * </p>
 *
 * <p>
 * Covers scenarios 1-9 of the Confluence "Search POC: Preview Framework Test
 * Scenarios" page, adapted to JournalArticle. Scenarios 4 (Headless) and 5
 * (FreeMarker) are documented as {@link Ignore}d: see their Javadoc.
 * </p>
 *
 * @author Rodrigo Guedes de Souza
 */
@RunWith(Arquillian.class)
public class JournalArticlePreviewSearchTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group1 = GroupTestUtil.addGroup();
		_group2 = GroupTestUtil.addGroup();

		UserTestUtil.setUser(TestPropsValues.getUser());

		// Index every version (including drafts) as its own document. This is
		// the product default, but we set it explicitly to make the test
		// independent of company configuration.

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				TestPropsValues.getUserId(), true);

		_originalPortalPreferencesXML = PortletPreferencesFactoryUtil.toXML(
			portalPreferences);

		portalPreferences.setValue(
			"", "indexAllArticleVersionsEnabled", "true");

		PortalPreferencesLocalServiceUtil.updatePreferences(
			TestPropsValues.getCompanyId(),
			PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			PortletPreferencesFactoryUtil.toXML(portalPreferences));

		_folder1 = JournalTestUtil.addFolder(
			_group1.getGroupId(), RandomTestUtil.randomString());
		_folder2 = JournalTestUtil.addFolder(
			_group2.getGroupId(), RandomTestUtil.randomString());
	}

	@After
	public void tearDown() throws Exception {
		PortalPreferencesLocalServiceUtil.updatePreferences(
			TestPropsValues.getCompanyId(),
			PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			_originalPortalPreferencesXML);
	}

	/**
	 * Scenario 1 - Baseline, no preview context. The default search returns
	 * only the approved/live version; the draft is not discoverable.
	 */
	@Test
	public void testScenario1BaselineNoPreviewContext() throws Exception {
		Article article = _addArticleWithDraft("alpaca", "zebra");

		_assertUIDs(_search("alpaca", null), _uid(article._approved));

		_assertUIDs(_search("zebra", null));
	}

	/**
	 * Scenario 2 - Single asset preview (ad-hoc). A one-entry swap map replaces
	 * the approved version with its draft version in the search results.
	 */
	@Test
	public void testScenario2SingleAssetPreview() throws Exception {
		Article article = _addArticleWithDraft("alpaca", "zebra");
		Article unmapped = _addArticleWithDraft("cobra", "ocelot");

		Map<Serializable, Object> previewValues =
			HashMapBuilder.<Serializable, Object>put(
				article._approved.getId(), article._draft.getId()
			).build();

		_assertUIDs(_search("zebra", previewValues), _uid(article._draft));
		_assertUIDs(_search("alpaca", previewValues));
		_assertUIDs(
			_search(null, previewValues), _uid(article._draft),
			_uid(unmapped._approved));
	}

	/**
	 * Scenario 3 - Launch preview (multi-asset map). Multiple entries are
	 * swapped to their draft versions; unmapped entries pass through.
	 */
	@Test
	public void testScenario3LaunchMultiAssetPreview() throws Exception {
		Article article1 = _addArticleWithDraft("alpaca", "zebra");
		Article article2 = _addArticleWithDraft("beaver", "yak");
		Article unmapped = _addArticleWithDraft("cobra", "ocelot");

		Map<Serializable, Object> previewValues = _previewValues(
			article1, article2);

		_assertUIDs(_search("zebra", previewValues), _uid(article1._draft));
		_assertUIDs(_search("yak", previewValues), _uid(article2._draft));
		_assertUIDs(
			_search(null, previewValues), _uid(article1._draft),
			_uid(article2._draft), _uid(unmapped._approved));
	}

	/**
	 * Scenario 4 - Headless APIs. OUT OF SCOPE for the search-query POC.
	 *
	 * <p>
	 * The swap is implemented inside the model pre-filter contributor, which is
	 * applied for every search execution path (headless backend, FreeMarker
	 * restClient, search bar) since they all converge on the same query
	 * building. So the swap works for headless <em>if</em> the headless
	 * request passes through {@code LaunchPreviewFilter}, which sets the
	 * {@link PreviewableResolverUtil} preview ID for requests carrying
	 * {@code previewLaunchSetId} and {@code p_l_mode=preview}. Exercising the
	 * full HTTP stack is beyond the search-query POC.
	 * </p>
	 */
	@Ignore
	@Test
	public void testScenario4HeadlessApis() {
	}

	/**
	 * Scenario 5 - FreeMarker. OUT OF SCOPE for the search-query POC, for the
	 * same reason as scenario 4: a FreeMarker template invoking search (via the
	 * search taglib or restClient) reaches the same query path, so the swap
	 * applies once the preview ID is set on the rendering thread.
	 * Rendering a template and wiring the signal is product integration, not a
	 * search-query concern.
	 */
	@Ignore
	@Test
	public void testScenario5FreeMarker() {
	}

	/**
	 * Scenario 6 - Faceting and aggregations reflect the preview version.
	 *
	 * <p>
	 * The Confluence scenario tags the live entry {@code red} and the draft
	 * {@code blue} and asserts the tag facet counts {@code blue}. Asset tags in
	 * a JournalArticle are per-asset (per {@code resourcePrimKey}), not
	 * per-version, so they cannot differ between the approved and draft docs.
	 * We therefore facet on the per-version {@link Field#STATUS} field: the
	 * previewed entry must contribute its draft's status ({@code 2}) to the
	 * facet instead of the live status ({@code 0}). This proves aggregations
	 * are computed over the previewed (swapped) result set, not the live one.
	 * </p>
	 */
	@Test
	public void testScenario6FacetsReflectPreview() throws Exception {
		Article article = _addArticleWithDraft("alpaca", "zebra");
		Article unmapped = _addArticleWithDraft("cobra", "ocelot");

		Map<String, Integer> baselineFacet = _statusFacet(null);

		Assert.assertEquals(
			"Baseline: both approved heads counted", Integer.valueOf(2),
			baselineFacet.getOrDefault(
				String.valueOf(WorkflowConstants.STATUS_APPROVED), 0));
		Assert.assertEquals(
			"Baseline: no draft status in the facet", Integer.valueOf(0),
			baselineFacet.getOrDefault(
				String.valueOf(WorkflowConstants.STATUS_DRAFT), 0));

		Map<String, Integer> previewFacet = _statusFacet(
			_previewValues(article));

		Assert.assertEquals(
			"Preview: the previewed entry contributes its draft status",
			Integer.valueOf(1),
			previewFacet.getOrDefault(
				String.valueOf(WorkflowConstants.STATUS_DRAFT), 0));
		Assert.assertEquals(
			"Preview: only the unmapped entry remains approved",
			Integer.valueOf(1),
			previewFacet.getOrDefault(
				String.valueOf(WorkflowConstants.STATUS_APPROVED), 0));

		// Unmapped entry is untouched in both cases.

		Assert.assertNotNull(unmapped);
	}

	/**
	 * Scenario 7 - Concurrent previews are isolated. Two threads search the
	 * same entry at the same time: one with a preview ID, one without. The
	 * preview ID is a thread-local, so neither thread leaks into the other.
	 */
	@Test
	public void testScenario7ConcurrentPreviewsAreIsolated() throws Exception {
		Article article = _addArticleWithDraft("alpaca", "zebra");

		Map<Serializable, Object> previewValues = _previewValues(article);

		String draftUID = _uid(article._draft);

		long companyId = TestPropsValues.getCompanyId();
		long groupId = _group1.getGroupId();

		int iterations = 25;

		AtomicInteger previewerSawDraft = new AtomicInteger();
		AtomicInteger baselinerSawDraft = new AtomicInteger();

		List<Throwable> throwables = Collections.synchronizedList(
			new ArrayList<>());

		CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

		Thread previewerThread = new Thread(
			() -> _runConcurrentSearch(
				companyId, groupId, "zebra", previewValues, iterations,
				cyclicBarrier, throwables,
				hits -> {
					if (_containsUID(hits, draftUID)) {
						previewerSawDraft.incrementAndGet();
					}
				}));
		Thread baselinerThread = new Thread(
			() -> _runConcurrentSearch(
				companyId, groupId, "zebra", null, iterations, cyclicBarrier,
				throwables,
				hits -> {
					if (hits.getLength() > 0) {
						baselinerSawDraft.incrementAndGet();
					}
				}));

		previewerThread.start();
		baselinerThread.start();

		previewerThread.join();
		baselinerThread.join();

		if (!throwables.isEmpty()) {
			throw new AssertionError(
				"Concurrent search failure", throwables.get(0));
		}

		Assert.assertEquals(
			"Previewer thread always saw the draft", iterations,
			previewerSawDraft.get());
		Assert.assertEquals(
			"Baseliner thread never saw the draft (no cross-thread leak)", 0,
			baselinerSawDraft.get());
	}

	/**
	 * Scenario 8 - Cross-group launch. A swap map spanning two groups (a site
	 * and an "asset library") swaps both entries to their drafts. The swap is
	 * keyed by {@link Field#UID} (primary key), with no {@code groupId} filter,
	 * so a cross-group preview is not broken.
	 */
	@Test
	public void testScenario8CrossGroupLaunch() throws Exception {
		Article article1 = _addArticleWithDraft("alpaca", "zebra");
		Article article2 = _addArticleWithDraft(
			_group2.getGroupId(), _folder2.getFolderId(), "beaver", "yak");

		Map<Serializable, Object> previewValues = _previewValues(
			article1, article2);

		long[] groupIds = {_group1.getGroupId(), _group2.getGroupId()};

		_assertUIDs(
			_searchGroups(groupIds, "zebra", previewValues),
			_uid(article1._draft));
		_assertUIDs(
			_searchGroups(groupIds, "yak", previewValues),
			_uid(article2._draft));

		Hits hits = _searchGroups(groupIds, null, previewValues);

		Assert.assertTrue(
			"Group 1 draft present", _containsUID(hits, _uid(article1._draft)));
		Assert.assertTrue(
			"Group 2 draft present", _containsUID(hits, _uid(article2._draft)));
		Assert.assertFalse(
			"Group 1 approved excluded",
			_containsUID(hits, _uid(article1._approved)));
		Assert.assertFalse(
			"Group 2 approved excluded",
			_containsUID(hits, _uid(article2._approved)));
	}

	/**
	 * Scenario 9 - Preview context cleared. After the preview ID is closed, a
	 * subsequent search on the same thread returns live versions only.
	 */
	@Test
	public void testScenario9PreviewContextCleared() throws Exception {
		Article article = _addArticleWithDraft("alpaca", "zebra");

		_assertUIDs(
			_search("zebra", _previewValues(article)), _uid(article._draft));

		Assert.assertNull(PreviewableResolverUtil.getPreviewId());

		_assertUIDs(_search("zebra", null));
		_assertUIDs(_search("alpaca", null), _uid(article._approved));
	}

	private Article _addArticleWithDraft(
			long groupId, long folderId, String approvedKeyword,
			String draftKeyword)
		throws Exception {

		JournalArticle approved = JournalTestUtil.addArticleWithWorkflow(
			groupId, folderId, "title", approvedKeyword, true);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		JournalArticle draft = JournalTestUtil.updateArticle(
			approved, approved.getTitleMap(),
			DDMStructureTestUtil.getSampleStructuredContent(draftKeyword),
			false, true, serviceContext);

		Assert.assertNotEquals(
			"Approved and draft must be distinct version rows",
			(Long)approved.getId(), (Long)draft.getId());

		return new Article(approved, draft);
	}

	private Article _addArticleWithDraft(
			String approvedKeyword, String draftKeyword)
		throws Exception {

		return _addArticleWithDraft(
			_group1.getGroupId(), _folder1.getFolderId(), approvedKeyword,
			draftKeyword);
	}

	private void _assertUIDs(Hits hits, String... expectedUIDs) {
		List<String> actualUIDs = new ArrayList<>();

		for (Document document : hits.getDocs()) {
			actualUIDs.add(document.get(Field.UID));
		}

		Assert.assertEquals(
			hits.toString(), new HashSet<>(Arrays.asList(expectedUIDs)),
			new HashSet<>(actualUIDs));
	}

	private boolean _containsUID(Hits hits, String uid) {
		for (Document document : hits.getDocs()) {
			if (uid.equals(document.get(Field.UID))) {
				return true;
			}
		}

		return false;
	}

	private Map<Serializable, Object> _previewValues(Article... articles) {
		Map<Serializable, Object> previewValues = new HashMap<>();

		for (Article article : articles) {
			previewValues.put(
				article._approved.getId(),
				new UnsafeSupplierValue<>(() -> article._draft));
		}

		return previewValues;
	}

	private void _runConcurrentSearch(
		long companyId, long groupId, String keywords,
		Map<Serializable, Object> previewValues, int iterations,
		CyclicBarrier cyclicBarrier, List<Throwable> throwables,
		Consumer<Hits> hitsConsumer) {

		try {
			CompanyThreadLocal.setCompanyId(companyId);

			cyclicBarrier.await(60, TimeUnit.SECONDS);

			Indexer<JournalArticle> indexer = IndexerRegistryUtil.getIndexer(
				JournalArticle.class);

			for (int i = 0; i < iterations; i++) {
				SearchContext searchContext =
					SearchContextTestUtil.getSearchContext(groupId);

				searchContext.setCompanyId(companyId);
				searchContext.setGroupIds(new long[] {groupId});
				searchContext.setKeywords(keywords);
				searchContext.setUserId(0);

				try (SafeCloseable safeCloseable =
						_setPreviewValuesWithSafeCloseable(previewValues)) {

					hitsConsumer.accept(indexer.search(searchContext));
				}
			}
		}
		catch (Throwable throwable) {
			throwables.add(throwable);
		}
	}

	private Hits _search(
			String keywords, Map<Serializable, Object> previewValues)
		throws Exception {

		return _searchGroups(
			new long[] {_group1.getGroupId()}, keywords, previewValues);
	}

	private Hits _searchGroups(
			long[] groupIds, String keywords,
			Map<Serializable, Object> previewValues)
		throws Exception {

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			groupIds[0]);

		searchContext.setGroupIds(groupIds);
		searchContext.setUserId(0);

		if (keywords != null) {
			searchContext.setKeywords(keywords);
		}

		Indexer<JournalArticle> indexer = IndexerRegistryUtil.getIndexer(
			JournalArticle.class);

		try (SafeCloseable safeCloseable = _setPreviewValuesWithSafeCloseable(
				previewValues)) {

			return indexer.search(searchContext);
		}
	}

	private SafeCloseable _setPreviewValuesWithSafeCloseable(
		Map<Serializable, Object> previewValues) {

		if (previewValues == null) {
			return SafeCloseable.EMPTY;
		}

		Long previewId = PreviewableResolverUtil.addPreviewableMap(
			HashMapBuilder.<Class<?>, Map<Serializable, Object>>put(
				JournalArticle.class, previewValues
			).build());

		SafeCloseable safeCloseable =
			PreviewableResolverUtil.setPreviewIdWithSafeCloseable(previewId);

		return () -> {
			safeCloseable.close();

			PreviewableResolverUtil.removePreviewableMap(previewId);
		};
	}

	private Map<String, Integer> _statusFacet(
			Map<Serializable, Object> previewValues)
		throws Exception {

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			_group1.getGroupId());

		searchContext.setGroupIds(new long[] {_group1.getGroupId()});
		searchContext.setUserId(0);

		MultiValueFacet multiValueFacet = new MultiValueFacet(searchContext);

		multiValueFacet.setFieldName(Field.STATUS);

		searchContext.addFacet(multiValueFacet);

		Indexer<JournalArticle> indexer = IndexerRegistryUtil.getIndexer(
			JournalArticle.class);

		try (SafeCloseable safeCloseable = _setPreviewValuesWithSafeCloseable(
				previewValues)) {

			indexer.search(searchContext);
		}

		Map<String, Integer> termFrequencies = new HashMap<>();

		FacetCollector facetCollector = multiValueFacet.getFacetCollector();

		if (facetCollector != null) {
			for (TermCollector termCollector :
					facetCollector.getTermCollectors()) {

				termFrequencies.put(
					termCollector.getTerm(), termCollector.getFrequency());
			}
		}

		return termFrequencies;
	}

	private String _uid(JournalArticle journalArticle) {

		// Mirrors UIDFactoryImpl production UID format
		// (modelClassName + "_PORTLET_" + primaryKey).

		return JournalArticle.class.getName() + "_PORTLET_" +
			journalArticle.getId();
	}

	@DeleteAfterTestRun
	private JournalFolder _folder1;

	@DeleteAfterTestRun
	private JournalFolder _folder2;

	@DeleteAfterTestRun
	private Group _group1;

	@DeleteAfterTestRun
	private Group _group2;

	private String _originalPortalPreferencesXML;

	private static class Article {

		private Article(JournalArticle approved, JournalArticle draft) {
			_approved = approved;
			_draft = draft;
		}

		private final JournalArticle _approved;
		private final JournalArticle _draft;

	}

}