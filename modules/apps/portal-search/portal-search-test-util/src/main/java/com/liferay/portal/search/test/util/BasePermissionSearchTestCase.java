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
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.workflow.WorkflowThreadLocal;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Joshua Cords
 */
public abstract class BasePermissionSearchTestCase {

	@Before
	public void setUp() throws Exception {
		group = GroupTestUtil.addGroup();

		serviceContext = ServiceContextTestUtil.getServiceContext(
			group.getGroupId());

		searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		searchContext.setKeywords(getSearchKeywords());

		addControlBaseModelAndParent();
	}

	@Test
	public void testBasicCombination() throws Exception {
		long baseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());
		long parentBaseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addBaseParentAndBaseModels(
			baseModelRoleId, parentBaseModelRoleId, false, false);

		User user = UserTestUtil.addUser(null, 0);

		assertOnlyRoleCombinationReturnsResults(
			baseModelRoleId, parentBaseModelRoleId, user);

		UserLocalServiceUtil.deleteUser(user);
	}

	@Test
	public void testGuestBridge() throws Exception {
		serviceContext.setAddGroupPermissions(false);
		serviceContext.setAddGuestPermissions(false);

		BaseModel<?> topParentBaseModel = getParentBaseModel();

		long topParentBaseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(topParentBaseModel, topParentBaseModelRoleId);

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		BaseModel<?> parentBaseModel = getParentBaseModel(topParentBaseModel);

		serviceContext.setAddGroupPermissions(false);
		serviceContext.setAddGuestPermissions(false);

		BaseModel<?> baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords());

		long baseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(baseModel, baseModelRoleId);

		User user = UserTestUtil.addUser(null, 0);

		assertOnlyRoleCombinationReturnsResults(
			baseModelRoleId, topParentBaseModelRoleId, user);

		UserLocalServiceUtil.deleteUser(user);
	}

	@Test
	public void testInheritedGuest() throws Exception {
		testUserPermissions(false, true);
	}

	@Test
	public void testInheritedPermission() throws Exception {
		testUserPermissions(true, false);
	}

	@Test
	public void testTripleLevelCombination() throws Exception {
		serviceContext.setAddGroupPermissions(false);
		serviceContext.setAddGuestPermissions(false);

		BaseModel<?> topParentBaseModel = getParentBaseModel();

		long topParentBaseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(topParentBaseModel, topParentBaseModelRoleId);

		BaseModel<?> parentBaseModel = getParentBaseModel(topParentBaseModel);

		long parentBaseModelRoleId1 = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(parentBaseModel, parentBaseModelRoleId1);

		long parentBaseModelRoleId2 = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(parentBaseModel, parentBaseModelRoleId2);

		BaseModel<?> baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords());

		addRoleViewPermission(baseModel, parentBaseModelRoleId1);

		long baseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(baseModel, baseModelRoleId);

		User user = UserTestUtil.addUser(null, 0);

		assertOnlyRoleCombinationReturnsResults(
			parentBaseModelRoleId1, topParentBaseModelRoleId, user);

		assertOnlyRoleCombinationReturnsResults(
			baseModelRoleId, parentBaseModelRoleId2, topParentBaseModelRoleId,
			user);

		UserLocalServiceUtil.deleteUser(user);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected BaseModel<?> addBaseModel(
			BaseModel<?> parentBaseModel, boolean approved, String keywords)
		throws Exception {

		boolean workflowEnabled = WorkflowThreadLocal.isEnabled();

		try {
			WorkflowThreadLocal.setEnabled(true);

			return addBaseModelWithWorkflow(
				parentBaseModel, approved, keywords);
		}
		finally {
			WorkflowThreadLocal.setEnabled(workflowEnabled);
		}
	}

	protected abstract BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords)
		throws Exception;

	protected void addBaseParentAndBaseModels(
			boolean addBaseModelGroupAndGuestPermission,
			boolean addParentBaseModelGroupAndGuestPermission)
		throws Exception {

		serviceContext.setAddGroupPermissions(
			addParentBaseModelGroupAndGuestPermission);
		serviceContext.setAddGuestPermissions(
			addParentBaseModelGroupAndGuestPermission);

		BaseModel<?> parentBaseModel = getParentBaseModel();

		serviceContext.setAddGroupPermissions(
			addBaseModelGroupAndGuestPermission);
		serviceContext.setAddGuestPermissions(
			addBaseModelGroupAndGuestPermission);

		addBaseModel(parentBaseModel, true, getSearchKeywords());
	}

	protected void addBaseParentAndBaseModels(
			long modelRoleId, long parentRoleId, boolean addBaseModelPermission,
			boolean addParentBaseModelPermission)
		throws Exception {

		serviceContext.setAddGroupPermissions(addParentBaseModelPermission);
		serviceContext.setAddGuestPermissions(addParentBaseModelPermission);

		BaseModel<?> parentBaseModel = getParentBaseModel();

		if (parentRoleId > 0) {
			addRoleViewPermission(parentBaseModel, parentRoleId);
		}

		serviceContext.setAddGroupPermissions(addBaseModelPermission);
		serviceContext.setAddGuestPermissions(addBaseModelPermission);

		BaseModel<?> baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords());

		if (modelRoleId > 0) {
			addRoleViewPermission(baseModel, modelRoleId);
		}
	}

	protected void addControlBaseModelAndParent() throws Exception {
		addBaseParentAndBaseModels(true, true);
	}

	protected void addRoleViewPermission(BaseModel<?> baseModel, long roleId)
		throws Exception {

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			group.getCompanyId(), baseModel.getModelClassName(),
			ResourceConstants.SCOPE_INDIVIDUAL, getPrimKey(baseModel), roleId,
			new String[] {ActionKeys.VIEW});
	}

	protected void assertOnlyRoleCombinationReturnsResults(
			long roleId1, long roleId2, long roleId3, User user)
		throws Exception {

		assertOnlyRoleCombinationReturnsResults(roleId1, roleId2, user);

		assertOnlyRoleCombinationReturnsResults(roleId1, roleId3, user);

		assertOnlyRoleCombinationReturnsResults(roleId2, roleId3, user);

		PermissionChecker originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(user));

			searchContext.setUserId(user.getUserId());

			UserLocalServiceUtil.addRoleUser(roleId1, user);
			UserLocalServiceUtil.addRoleUser(roleId2, user);
			UserLocalServiceUtil.addRoleUser(roleId3, user);

			assertPermissionFilteringOfSearchEngine();
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(
				originalPermissionChecker);
		}
	}

	protected void assertOnlyRoleCombinationReturnsResults(
			long roleId1, long roleId2, User user)
		throws Exception {

		PermissionChecker originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(user));

			searchContext.setUserId(user.getUserId());

			UserLocalServiceUtil.addRoleUser(roleId1, user);

			assertPermissionFilteringOfSearchEngine();

			UserLocalServiceUtil.addRoleUser(roleId2, user);

			assertPermissionFilteringOfSearchEngine();

			UserLocalServiceUtil.deleteRoleUser(roleId1, user);

			assertPermissionFilteringOfSearchEngine();

			UserLocalServiceUtil.deleteRoleUser(roleId2, user);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(
				originalPermissionChecker);
		}
	}

	protected void assertPermissionFilteringOfSearchEngine() throws Exception {
		Hits filteredHits = searchBaseModelsPermissionFilteredCount();

		Hits unfilteredHits = searchBaseModelsSearchEngineCount();

		Assert.assertEquals(
			"Documents have been filtered out: " + unfilteredHits,
			filteredHits.getLength(), unfilteredHits.getLength());
	}

	protected abstract Class<?> getBaseModelClass();

	protected BaseModel<?> getParentBaseModel() throws Exception {
		return group;
	}

	protected abstract BaseModel<?> getParentBaseModel(BaseModel<?> baseModel)
		throws Exception;

	protected String getPrimKey(BaseModel<?> baseModel) {
		return null;
	}

	protected abstract String getSearchKeywords();

	protected Hits searchBaseModelsPermissionFilteredCount() throws Exception {
		return searchBaseModelsPermissionFilteredCount(
			getBaseModelClass(), group.getGroupId());
	}

	protected Hits searchBaseModelsPermissionFilteredCount(
			Class<?> clazz, long groupId)
		throws Exception {

		Indexer<?> indexer = IndexerRegistryUtil.getIndexer(clazz);

		searchContext.setGroupIds(new long[] {groupId});

		return indexer.search(searchContext);
	}

	protected Hits searchBaseModelsSearchEngineCount() throws Exception {
		return searchBaseModelsSearchEngineCount(
			getBaseModelClass(), group.getGroupId());
	}

	protected Hits searchBaseModelsSearchEngineCount(
			Class<?> clazz, long groupId)
		throws Exception {

		Indexer<?> indexer = IndexerRegistryUtil.getIndexer(clazz);

		searchContext.setGroupIds(new long[] {groupId});

		Query fullQuery = indexer.getFullQuery(searchContext);

		fullQuery.setQueryConfig(searchContext.getQueryConfig());

		return IndexSearcherHelperUtil.search(searchContext, fullQuery);
	}

	protected void testUserPermissions(
			boolean addBaseModelGroupAndGuestPermission,
			boolean addParentBaseModelGroupAndGuestPermission)
		throws Exception {

		addBaseParentAndBaseModels(
			addBaseModelGroupAndGuestPermission,
			addParentBaseModelGroupAndGuestPermission);

		User user = UserTestUtil.addUser(null, 0);

		PermissionChecker originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(user));

			searchContext.setUserId(user.getUserId());

			assertPermissionFilteringOfSearchEngine();
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(
				originalPermissionChecker);
		}

		UserLocalServiceUtil.deleteUser(user);
	}

	@DeleteAfterTestRun
	protected Group group;

	protected SearchContext searchContext;
	protected ServiceContext serviceContext;

}