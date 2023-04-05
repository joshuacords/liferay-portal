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

package com.liferay.portal.search.test.util;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.workflow.WorkflowThreadLocal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Eudaldo Alonso
 * @author Tibor Lipusz
 */
public abstract class BasePermissionSearchTestCase {

	@Before
	public void setUp() throws Exception {
		group = GroupTestUtil.addGroup();
	}

	@Test
	public void testParentBaseModelUserPermissions() throws Exception {
		testUserPermissions(true, false);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected BaseModel<?> addBaseModel(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		boolean workflowEnabled = WorkflowThreadLocal.isEnabled();

		try {
			WorkflowThreadLocal.setEnabled(true);

			return addBaseModelWithWorkflow(
				parentBaseModel, approved, keywords, serviceContext);
		}
		finally {
			WorkflowThreadLocal.setEnabled(workflowEnabled);
		}
	}

	protected abstract BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception;

	protected void assertPermissionFilteringOfSearchEngine(
			SearchContext searchContext)
		throws Exception {

		Hits filteredHits = searchBaseModelsPermissionFilteredCount(
			searchContext);

		Hits unfilteredHits = searchBaseModelsSearchEngineCount(searchContext);

		Assert.assertEquals(
			"Documents have been filtered out.", filteredHits.getLength(),
			unfilteredHits.getLength());
	}

	protected abstract Class<?> getBaseModelClass();

	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		return group;
	}

	protected abstract String getSearchKeywords();

	protected Hits searchBaseModelsPermissionFilteredCount(
			Class<?> clazz, long groupId, SearchContext searchContext)
		throws Exception {

		Indexer<?> indexer = IndexerRegistryUtil.getIndexer(clazz);

		searchContext.setGroupIds(new long[] {groupId});

		return indexer.search(searchContext);
	}

	protected Hits searchBaseModelsPermissionFilteredCount(
			SearchContext searchContext)
		throws Exception {

		return searchBaseModelsPermissionFilteredCount(
			getBaseModelClass(), group.getGroupId(), searchContext);
	}

	protected Hits searchBaseModelsSearchEngineCount(
			Class<?> clazz, long groupId, SearchContext searchContext)
		throws Exception {

		Indexer<?> indexer = IndexerRegistryUtil.getIndexer(clazz);

		searchContext.setGroupIds(new long[] {groupId});

		Query fullQuery = indexer.getFullQuery(searchContext);

		fullQuery.setQueryConfig(searchContext.getQueryConfig());

		return IndexSearcherHelperUtil.search(searchContext, fullQuery);
	}

	protected Hits searchBaseModelsSearchEngineCount(
			SearchContext searchContext)
		throws Exception {

		return searchBaseModelsSearchEngineCount(
			getBaseModelClass(), group.getGroupId(), searchContext);
	}

	protected void testUserPermissions(
			boolean addBaseModelPermission,
			boolean addParentBaseModelPermission)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords(getSearchKeywords());

		serviceContext.setAddGroupPermissions(addParentBaseModelPermission);
		serviceContext.setAddGuestPermissions(addParentBaseModelPermission);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		serviceContext.setAddGroupPermissions(addBaseModelPermission);
		serviceContext.setAddGuestPermissions(addBaseModelPermission);

		baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		User user = UserTestUtil.addUser(null, 0);

		PermissionChecker originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(user));

			searchContext.setUserId(user.getUserId());

			assertPermissionFilteringOfSearchEngine(searchContext);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(
				originalPermissionChecker);
		}

		UserLocalServiceUtil.deleteUser(user);
	}

	protected BaseModel<?> baseModel;

	@DeleteAfterTestRun
	protected Group group;

	//	protected void assertBaseModelsPostPermissionFilteredCount(
	//			int expectedCount, SearchContext searchContext)
	//		throws Exception {
	//
	//		Hits hits = searchBaseModelsCount(searchContext);

	//
	//		Assert.assertEquals(
	//			searchContext.getAttribute("queryString") + "->" + hits,
	//			expectedCount, hits.getLength());
	//	}
	//

	// 	@Test

	//	public void testSearchBaseModelInPermissionedFolder() throws Exception {
	//		searchBaseModelInPermissionedFolder();
	//	}
	//
	//	protected BaseModel<?> getParentBaseModel(
	//			BaseModel<?> parentBaseModel, ServiceContext serviceContext)
	//		throws Exception {
	//
	//		return parentBaseModel;
	//	}
	//
	//	protected BaseModel<?> getPermissionedParentBaseModel(
	//		Group group, ServiceContext serviceContext)
	//		throws Exception {
	//
	//		return group;
	//	}
	//
	//	protected String getParentBaseModelClassName() {
	//		return StringPool.BLANK;
	//	}
	//
	//	protected void searchBaseModel() throws Exception {
	//		searchBaseModel(0);
	//	}
	//
	//	protected void searchBaseModelInPermissionedFolder() throws Exception {
	//		searchBaseModelInPermissionedFolder(0);
	//	}
	//

	// 	protected void searchBaseModelInPermissionedFolder(int initialBaseModelsSearchCount)

	//		throws Exception {
	//

	// 		ServiceContext serviceContext =

	//			ServiceContextTestUtil.getServiceContext(group.getGroupId());
	//
	//		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
	//			group.getGroupId());
	//
	////		BaseModel<?> parentBaseModel = getParentBaseModel(
	////			group, serviceContext);
	//
	//		BaseModel<?> permissionedParentBaseModel = getPermissionedParentBaseModel(
	//			group, serviceContext);
	//
	//		assertBaseModelsPostPermissionFilteredCount(initialBaseModelsSearchCount, searchContext);
	//
	//		baseModel = addBaseModel(
	//			permissionedParentBaseModel, true, RandomTestUtil.randomString(),
	//			serviceContext);
	//
	//		assertBaseModelsPostPermissionFilteredCount(initialBaseModelsSearchCount + 1, searchContext);
	//	}
	//
	//	protected BaseModel<?> updateBaseModel(
	//			BaseModel<?> baseModel, String keywords,
	//			ServiceContext serviceContext)
	//		throws Exception {
	//
	//		return baseModel;
	//	}
}