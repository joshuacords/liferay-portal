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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.comment.CommentManagerUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ClassedModel;
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
import com.liferay.portal.kernel.service.IdentityServiceContextFunction;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
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

//	@Test
//	public void testBaseModelUserPermissions() throws Exception {
//		testUserPermissions(false, true);
//	}

	@Test
	public void testParentBaseModelUserPermissions() throws Exception {
		testUserPermissions(true, false);
	}
//
//	@Test
//	public void testSearchBaseModel() throws Exception {
//		searchBaseModel();
//	}
//
//	@Test
//	public void testSearchByKeywords() throws Exception {
//		searchByKeywords();
//	}

//	@Test
//	public void testSearchBaseModelInPermissionedFolder() throws Exception {
//		searchBaseModelInPermissionedFolder();
//	}

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

	protected void addComment(
			ClassedModel classedModel, String body,
			ServiceContext serviceContext)
		throws Exception {

		User user = TestPropsValues.getUser();

		CommentManagerUtil.addComment(
			user.getUserId(), serviceContext.getScopeGroupId(),
			getBaseModelClassName(), getBaseModelClassPK(classedModel), body,
			new IdentityServiceContextFunction(serviceContext));
	}

	protected void assertBaseModelsPostPermissionFilteredCount(
			int expectedCount, SearchContext searchContext)
		throws Exception {

		Hits hits = searchBaseModelsCount(searchContext);

		Assert.assertEquals(
			searchContext.getAttribute("queryString") + "->" + hits,
			expectedCount, hits.getLength());
	}

	protected void assertBaseModelsDirectCount(
		int expectedCount, SearchContext searchContext)
		throws Exception {

		Hits hits = searchBaseModelsDirectCount(searchContext);

		Assert.assertEquals(
			searchContext.getAttribute("queryString") + "->" + hits,
			expectedCount, hits.getLength());
	}

	protected void assertPermissionFilteringOfSearchEngine(
		SearchContext searchContext)
		throws Exception {

		Hits filteredHits = searchBaseModelsCount(searchContext);

		Hits unfilteredHits = searchBaseModelsDirectCount(searchContext);

		Assert.assertEquals(
			"Documents have been filtered out.", filteredHits.getLength(),
			unfilteredHits.getLength());

	}


	protected void deleteBaseModel(BaseModel<?> baseModel) throws Exception {
		deleteBaseModel((Long)baseModel.getPrimaryKeyObj());
	}

	protected void deleteBaseModel(long primaryKey) throws Exception {
	}

	protected abstract Class<?> getBaseModelClass();

	protected String getBaseModelClassName() {
		Class<?> clazz = getBaseModelClass();

		return clazz.getName();
	}

	protected Long getBaseModelClassPK(ClassedModel classedModel) {
		return (Long)classedModel.getPrimaryKeyObj();
	}

	protected BaseModel<?> getParentBaseModel(
			BaseModel<?> parentBaseModel, ServiceContext serviceContext)
		throws Exception {

		return parentBaseModel;
	}

	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		return group;
	}

	protected BaseModel<?> getPermissionedParentBaseModel(
		Group group, ServiceContext serviceContext)
		throws Exception {

		return group;
	}

	protected String getParentBaseModelClassName() {
		return StringPool.BLANK;
	}

	protected abstract String getSearchKeywords();

	protected boolean isCheckBaseModelPermission() {
		return CHECK_BASE_MODEL_PERMISSION;
	}

	protected void moveBaseModelToTrash(long primaryKey) throws Exception {
	}

	protected void moveParentBaseModelToTrash(long primaryKey)
		throws Exception {
	}

	protected void searchBaseModel() throws Exception {
		searchBaseModel(0);
	}

	protected void searchBaseModelInPermissionedFolder() throws Exception {
		searchBaseModelInPermissionedFolder(0);
	}

	protected void searchBaseModelInPermissionedFolder(int initialBaseModelsSearchCount)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

//		BaseModel<?> parentBaseModel = getParentBaseModel(
//			group, serviceContext);

		BaseModel<?> permissionedParentBaseModel = getPermissionedParentBaseModel(
			group, serviceContext);

		assertBaseModelsPostPermissionFilteredCount(initialBaseModelsSearchCount, searchContext);

		baseModel = addBaseModel(
			permissionedParentBaseModel, true, RandomTestUtil.randomString(),
			serviceContext);

		assertBaseModelsPostPermissionFilteredCount(initialBaseModelsSearchCount + 1, searchContext);
	}

	protected void searchBaseModel(int initialBaseModelsSearchCount)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		assertBaseModelsPostPermissionFilteredCount(initialBaseModelsSearchCount, searchContext);

		baseModel = addBaseModel(
			parentBaseModel, true, RandomTestUtil.randomString(),
			serviceContext);

		assertBaseModelsPostPermissionFilteredCount(initialBaseModelsSearchCount + 1, searchContext);
	}

	protected Hits searchBaseModelsCount(
			Class<?> clazz, long groupId, SearchContext searchContext)
		throws Exception {

		Indexer<?> indexer = IndexerRegistryUtil.getIndexer(clazz);

		searchContext.setGroupIds(new long[] {groupId});

		return indexer.search(searchContext);
	}

	protected Hits searchBaseModelsDirectCount
		(Class<?> clazz, long groupId, SearchContext searchContext)
		throws Exception {

		Indexer<?> indexer = IndexerRegistryUtil.getIndexer(clazz);

		Query fullQuery = indexer.getFullQuery(searchContext);

		fullQuery.setQueryConfig(searchContext.getQueryConfig());

		return IndexSearcherHelperUtil.search(searchContext, fullQuery);
	}

	protected Hits searchBaseModelsCount(SearchContext searchContext)
		throws Exception {

		return searchBaseModelsCount(
			getBaseModelClass(), group.getGroupId(), searchContext);
	}

	protected Hits searchBaseModelsDirectCount(SearchContext searchContext)
		throws Exception {

		return searchBaseModelsDirectCount(
			getBaseModelClass(), group.getGroupId(), searchContext);
	}

	protected void searchByKeywords() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords(getSearchKeywords());

		int initialBaseModelsSearchCount = 0;

		assertBaseModelsPostPermissionFilteredCount(initialBaseModelsSearchCount, searchContext);

		baseModel = addBaseModel(
			getParentBaseModel(group, serviceContext), true,
			getSearchKeywords(), serviceContext);

		assertBaseModelsPostPermissionFilteredCount(initialBaseModelsSearchCount + 1, searchContext);
	}

	protected void testUserPermissions(
			boolean addBaseModelPermission,//false means only owner role will be given
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

	protected BaseModel<?> updateBaseModel(
			BaseModel<?> baseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		return baseModel;
	}

	protected static final boolean CHECK_BASE_MODEL_PERMISSION = true;

	protected BaseModel<?> baseModel;

	@DeleteAfterTestRun
	protected Group group;

}