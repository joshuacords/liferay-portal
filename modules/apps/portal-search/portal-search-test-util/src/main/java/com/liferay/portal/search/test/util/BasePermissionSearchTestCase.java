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

		addControlBaseModelAndParent(serviceContext);
	}

	@Test
	public void testBasicCombination() throws Exception {
		long baseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());
		long parentBaseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addBaseParentAndBaseModels(
			baseModelRoleId, parentBaseModelRoleId, false, false,
			serviceContext);

		User user = UserTestUtil.addUser(null, 0);

		assertOnlyRoleCombinationReturnsResults(
			baseModelRoleId, parentBaseModelRoleId, searchContext, user);

		UserLocalServiceUtil.deleteUser(user);
	}

	@Test
	public void testGuestBridge() throws Exception {
		serviceContext.setAddGroupPermissions(false);
		serviceContext.setAddGuestPermissions(false);

		BaseModel<?> topParentBaseModel = getParentBaseModel(
			group, serviceContext);

		long topParentBaseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(
			topParentBaseModel, group, topParentBaseModelRoleId);

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, topParentBaseModel, serviceContext);

		serviceContext.setAddGroupPermissions(false);
		serviceContext.setAddGuestPermissions(false);

		BaseModel<?> baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		long baseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(baseModel, group, baseModelRoleId);

		baseModelList.add(baseModel);

		User user = UserTestUtil.addUser(null, 0);

		assertOnlyRoleCombinationReturnsResults(
			baseModelRoleId, topParentBaseModelRoleId, searchContext, user);

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

		BaseModel<?> topParentBaseModel = getParentBaseModel(
			group, serviceContext);

		long topParentBaseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(
			topParentBaseModel, group, topParentBaseModelRoleId);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, topParentBaseModel, serviceContext);

		long parentBaseModelRoleId1 = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(parentBaseModel, group, parentBaseModelRoleId1);

		long parentBaseModelRoleId2 = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(parentBaseModel, group, parentBaseModelRoleId2);

		BaseModel<?> baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		addRoleViewPermission(baseModel, group, parentBaseModelRoleId1);

		long baseModelRoleId = RoleTestUtil.addRegularRole(
			TestPropsValues.getGroupId());

		addRoleViewPermission(baseModel, group, baseModelRoleId);

		baseModelList.add(baseModel);

		User user = UserTestUtil.addUser(null, 0);

		assertOnlyRoleCombinationReturnsResults(
			parentBaseModelRoleId1, topParentBaseModelRoleId, searchContext,
			user);

		assertOnlyRoleCombinationReturnsResults(
			baseModelRoleId, parentBaseModelRoleId2, topParentBaseModelRoleId,
			searchContext, user);

		UserLocalServiceUtil.deleteUser(user);
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

	protected void addBaseParentAndBaseModels(
			boolean addBaseModelGroupAndGuestPermission,
			boolean addParentBaseModelGroupAndGuestPermission,
			ServiceContext serviceContext)
		throws Exception {

		serviceContext.setAddGroupPermissions(
			addParentBaseModelGroupAndGuestPermission);
		serviceContext.setAddGuestPermissions(
			addParentBaseModelGroupAndGuestPermission);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		serviceContext.setAddGroupPermissions(
			addBaseModelGroupAndGuestPermission);
		serviceContext.setAddGuestPermissions(
			addBaseModelGroupAndGuestPermission);

		baseModelList.add(
			addBaseModel(
				parentBaseModel, true, getSearchKeywords(), serviceContext));
	}

	protected void addBaseParentAndBaseModels(
			long modelRoleId, long parentRoleId, boolean addBaseModelPermission,
			boolean addParentBaseModelPermission, ServiceContext serviceContext)
		throws Exception {

		serviceContext.setAddGroupPermissions(addParentBaseModelPermission);
		serviceContext.setAddGuestPermissions(addParentBaseModelPermission);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		if (parentRoleId > 0) {
			addRoleViewPermission(parentBaseModel, group, parentRoleId);
		}

		serviceContext.setAddGroupPermissions(addBaseModelPermission);
		serviceContext.setAddGuestPermissions(addBaseModelPermission);

		BaseModel<?> baseModel = addBaseModel(
			parentBaseModel, true, getSearchKeywords(), serviceContext);

		if (modelRoleId > 0) {
			addRoleViewPermission(baseModel, group, modelRoleId);
		}

		baseModelList.add(baseModel);
	}

	protected void addControlBaseModelAndParent(ServiceContext serviceContext)
		throws Exception {

		addBaseParentAndBaseModels(true, true, serviceContext);
	}

	protected void addRoleViewPermission(
			BaseModel<?> baseModel, Group group, long roleId)
		throws Exception {

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			group.getCompanyId(), baseModel.getModelClassName(),
			ResourceConstants.SCOPE_INDIVIDUAL, getPrimKey(baseModel), roleId,
			new String[] {ActionKeys.VIEW});
	}

	protected void assertOnlyRoleCombinationReturnsResults(
			long roleId1, long roleId2, long roleId3,
			SearchContext searchContext, User user)
		throws Exception {

		assertOnlyRoleCombinationReturnsResults(
			roleId1, roleId2, searchContext, user);

		assertOnlyRoleCombinationReturnsResults(
			roleId1, roleId3, searchContext, user);

		assertOnlyRoleCombinationReturnsResults(
			roleId2, roleId3, searchContext, user);

		PermissionChecker originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(user));

			searchContext.setUserId(user.getUserId());

			UserLocalServiceUtil.addRoleUser(roleId1, user);
			UserLocalServiceUtil.addRoleUser(roleId2, user);
			UserLocalServiceUtil.addRoleUser(roleId3, user);

			assertPermissionFilteringOfSearchEngine(searchContext);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(
				originalPermissionChecker);
		}
	}

	protected void assertOnlyRoleCombinationReturnsResults(
			long roleId1, long roleId2, SearchContext searchContext, User user)
		throws Exception {

		PermissionChecker originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(user));

			searchContext.setUserId(user.getUserId());

			UserLocalServiceUtil.addRoleUser(roleId1, user);

			assertPermissionFilteringOfSearchEngine(searchContext);

			UserLocalServiceUtil.addRoleUser(roleId2, user);

			assertPermissionFilteringOfSearchEngine(searchContext);

			UserLocalServiceUtil.deleteRoleUser(roleId1, user);

			assertPermissionFilteringOfSearchEngine(searchContext);

			UserLocalServiceUtil.deleteRoleUser(roleId2, user);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(
				originalPermissionChecker);
		}
	}

	protected void assertPermissionFilteringOfSearchEngine(
			SearchContext searchContext)
		throws Exception {

		Hits filteredHits = searchBaseModelsPermissionFilteredCount(
			searchContext);

		Hits unfilteredHits = searchBaseModelsSearchEngineCount(searchContext);

		Assert.assertEquals(
			"Documents have been filtered out: " + unfilteredHits,
			filteredHits.getLength(), unfilteredHits.getLength());
	}

	protected abstract Class<?> getBaseModelClass();

	protected abstract BaseModel<?> getParentBaseModel(
			Group group, BaseModel<?> baseModel, ServiceContext serviceContext)
		throws Exception;

	protected BaseModel<?> getParentBaseModel(
			Group group, Role role, ServiceContext serviceContext)
		throws Exception {

		return group;
	}

	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		return group;
	}

	protected String getPrimKey(BaseModel<?> baseModel) {
		return null;
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
			boolean addBaseModelGroupAndGuestPermission,
			boolean addParentBaseModelGroupAndGuestPermission)
		throws Exception {

		addBaseParentAndBaseModels(
			addBaseModelGroupAndGuestPermission,
			addParentBaseModelGroupAndGuestPermission, serviceContext);

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

	protected List<BaseModel<?>> baseModelList = new ArrayList<>();

	@DeleteAfterTestRun
	protected Group group;

	protected SearchContext searchContext;
	protected ServiceContext serviceContext;

}