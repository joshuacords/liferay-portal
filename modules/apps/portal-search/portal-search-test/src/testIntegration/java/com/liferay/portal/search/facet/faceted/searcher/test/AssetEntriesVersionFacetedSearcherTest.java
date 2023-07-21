/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.facet.faceted.searcher.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.test.util.search.JournalArticleBlueprint;
import com.liferay.journal.test.util.search.JournalArticleContent;
import com.liferay.journal.test.util.search.JournalArticleTitle;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.search.facet.type.AssetEntriesFacetFactory;
import com.liferay.portal.search.test.util.DocumentsAssert;
import com.liferay.portal.search.test.util.FacetsAssert;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author André de Oliveira
 */
@RunWith(Arquillian.class)
public class AssetEntriesVersionFacetedSearcherTest
	extends BaseFacetedSearcherTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testOriginal() throws Exception {
		String keyword = RandomTestUtil.randomString();

		Group group = userSearchFixture.addGroup();

		index(keyword, group);

		SearchContext searchContext = getSearchContext(keyword);

		Facet facet = createFacet(searchContext);

		searchContext.addFacet(facet);

		Hits hits = search(searchContext);

		assertVersions(Arrays.asList("1.0"), hits, keyword);

		FacetsAssert.assertFrequencies(
			facet.getFieldName(), searchContext, hits,
			Collections.singletonMap(JournalArticle.class.getName(), 1));
	}

	@Test
	public void testVersioned() throws Exception {
		String keyword = RandomTestUtil.randomString();

		Group group = userSearchFixture.addGroup();

		JournalArticle journalArticle = index(keyword, group);

		update(journalArticle);

		SearchContext searchContext = getSearchContext(keyword);

		Facet facet = createFacet(searchContext);

		searchContext.addFacet(facet);

		Hits hits = search(searchContext);

		assertVersions(Arrays.asList("1.1"), hits, keyword);

		FacetsAssert.assertFrequencies(
			facet.getFieldName(), searchContext, hits,
			Collections.singletonMap(JournalArticle.class.getName(), 1));
	}

	protected void assertVersions(
		List<String> expectedValues, Hits hits, String keyword) {

		DocumentsAssert.assertValuesIgnoreRelevance(
			keyword, hits.getDocs(), "version", expectedValues);
	}

	protected Facet createFacet(SearchContext searchContext) {
		return _assetEntriesFacetFactory.newInstance(searchContext);
	}

	protected JournalArticle index(String keyword, Group group)
		throws Exception {

		JournalArticle journalArticle = journalArticleSearchFixture.addArticle(
			new JournalArticleBlueprint() {
				{
					setGroupId(group.getGroupId());
					setJournalArticleContent(
						new JournalArticleContent() {
							{
								put(
									LocaleUtil.US,
									RandomTestUtil.randomString());

								setDefaultLocale(LocaleUtil.US);
								setName("content");
							}
						});
					setJournalArticleTitle(
						new JournalArticleTitle() {
							{
								put(LocaleUtil.US, keyword);
							}
						});
				}
			});

		User user = UserTestUtil.getAdminUser(group.getCompanyId());

		PermissionThreadLocal.setPermissionChecker(
			_permissionCheckerFactory.create(user));

		return journalArticle;
	}

	protected void update(JournalArticle journalArticle) throws Exception {
		journalArticleSearchFixture.updateArticle(journalArticle);
	}

	@Inject
	private AssetEntriesFacetFactory _assetEntriesFacetFactory;

	@Inject
	private PermissionCheckerFactory _permissionCheckerFactory;

}