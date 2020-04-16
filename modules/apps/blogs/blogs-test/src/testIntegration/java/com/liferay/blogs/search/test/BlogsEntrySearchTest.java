/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.blogs.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.blogs.test.util.BlogsTestUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.RelatedEntryIndexer;
import com.liferay.portal.kernel.search.RelatedEntryIndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcher;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcherManager;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.search.constants.SearchContextAttributes;
import com.liferay.portal.search.test.util.BaseSearchTestCase;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Eudaldo Alonso
 */
@RunWith(Arquillian.class)
public class BlogsEntrySearchTest extends BaseSearchTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		UserTestUtil.setUser(TestPropsValues.getUser());

		super.setUp();
	}

	@Override
	public void testLocalizedSearch() {
	}

	@Override
	public void testParentBaseModelUserPermissions() {
	}

	@Override
	public void testSearchAttachments() {
	}

	@Override
	public void testSearchByDDMStructureField() {
	}

	@Override
	public void testSearchByKeywordsInsideParentBaseModel() {
	}

	@Override
	public void testSearchExpireAllVersions() {
	}

	@Override
	public void testSearchExpireLatestVersion() {
	}

	@Override
	public void testSearchMyEntries() {
	}

	@Override
	public void testSearchRecentEntries() {
	}

	@Override
	public void testSearchStatus() {
	}

	@Override
	public void testSearchVersions() {
	}

	@Override
	public void testSearchWithinDDMStructure() {
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		return BlogsTestUtil.addEntryWithWorkflow(
			TestPropsValues.getUserId(), keywords, approved, serviceContext);
	}

	@Override
	protected void deleteBaseModel(long primaryKey) throws Exception {
		BlogsEntryLocalServiceUtil.deleteEntry(primaryKey);
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return BlogsEntry.class;
	}

	@Override
	protected String getSearchKeywords() {
		return "Title";
	}

	@Override
	protected void moveBaseModelToTrash(long primaryKey) throws Exception {
		BlogsEntryLocalServiceUtil.moveEntryToTrash(
			TestPropsValues.getUserId(), primaryKey);
	}

	@Override
	protected Hits searchBaseModelsCount(
		Class<?> clazz, long groupId, SearchContext searchContext)
		throws Exception {
		Indexer<?> indexer = IndexerRegistryUtil.getIndexer(clazz);

		searchContext.setGroupIds(new long[] {groupId});

//		indexer.search(searchContext);

		searchContext.setGroupIds(new long[] {groupId});

		Set<String> entryClassNames = new HashSet<>();

		for (RelatedEntryIndexer relatedEntryIndexer :
			RelatedEntryIndexerRegistryUtil.getRelatedEntryIndexers()) {

			relatedEntryIndexer.updateFullQuery(searchContext);
		}

		for(String entryClassName : searchContext.getFullQueryEntryClassNames()) {
			entryClassNames.add(entryClassName);
		}

		//for(String entryClassName : DLSearcher.CLASS_NAMES) {
		entryClassNames.add(BlogsEntry.class.getName());
		//}

		String[] entryClassNamesArray = entryClassNames.toArray(new String[0]);

		searchContext.setEntryClassNames(entryClassNamesArray);

		searchContext.setAttribute(
			SearchContextAttributes.ATTRIBUTE_KEY_EMPTY_SEARCH, Boolean.TRUE);

		FacetedSearcher facetedSearcher =
			facetedSearcherManager.createFacetedSearcher();

		return facetedSearcher.search(searchContext);
	}

	@Override
	protected BaseModel<?> updateBaseModel(
			BaseModel<?> baseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		BlogsEntry entry = (BlogsEntry)baseModel;

		return BlogsEntryLocalServiceUtil.updateEntry(
			serviceContext.getUserId(), entry.getEntryId(), keywords,
			entry.getContent(), serviceContext);
	}

	@Inject
	protected static FacetedSearcherManager facetedSearcherManager;
}