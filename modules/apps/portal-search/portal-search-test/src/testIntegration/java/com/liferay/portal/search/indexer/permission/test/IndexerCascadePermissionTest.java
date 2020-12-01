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

package com.liferay.portal.search.indexer.permission.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.document.library.test.util.search.DLFolderSearchFixture;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcher;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcherManager;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.randomizerbumpers.NumericStringRandomizerBumper;
import com.liferay.portal.kernel.test.randomizerbumpers.UniqueStringRandomizerBumper;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.users.admin.test.util.search.UserSearchFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * @author Joshua Cords
 */
@RunWith(Arquillian.class)
@Sync
public class IndexerCascadePermissionTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@After
	public void tearDown() throws Exception {
		dlFolderSearchFixture.tearDown();
	}

	@Before
	public void setUp() throws Exception {

		_user = UserTestUtil.addUser(
			RandomTestUtil.randomString(
				NumericStringRandomizerBumper.INSTANCE,
				UniqueStringRandomizerBumper.INSTANCE),
			LocaleUtil.getDefault(), RandomTestUtil.randomString(),
			"LastName", new long[] {TestPropsValues.getGroupId()});

		_group = GroupTestUtil.addGroup();

		//Need to be able to set a role on the user
		_guestRole = RoleLocalServiceUtil.getRole(
			_group.getCompanyId(), RoleConstants.GUEST);

		_guestUser = getUser(RoleConstants.GUEST);


//		UserLocalServiceUtil.setRoleUsers(
//			_guestRole.getRoleId(), new long[] {_guestUser.getUserId()});

		setUpDLFolderSearchFixture();

		_indexer = indexerRegistry.getIndexer(DLFolder.class);

//		PermissionChecker permissionChecker =
//			PermissionCheckerFactoryUtil.create(TestPropsValues.getUser());

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(_guestUser);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		//setUpUserSearchFixture();
	}

	protected void setUpUserSearchFixture() throws Exception {
		_userSearchFixture = new UserSearchFixture();

		_userSearchFixture.setUp();

		//_group = _userSearchFixture.addGroup();

		//_groups = _userSearchFixture.getGroups();

		//_guestUser = _userSearchFixture.addUser(
		//	RandomTestUtil.randomString(), _group);

		//_users = _userSearchFixture.getUsers();
	}

	@Test
	public void testCascadePermission() throws Exception {
		_createDLFolderTree();
	}

	private void _createDLFolderTree() throws Exception {

		int originalCount = searchCount(_guestUser);

		DLFolder parentFolder = _createDLFolder("permissioned folder", DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		DLFolder childFolder = _createDLFolder("permissioned subfolder", parentFolder.getFolderId());

		Assert.assertEquals(
			"Expected " + originalCount , originalCount+2, searchCount(_user));

		//Need to set permissions on the DLFolder new String[] {ActionKeys.VIEW}
		_resourcePermissionLocalService.setResourcePermissions(
			TestPropsValues.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(parentFolder.getFolderId()), _guestRole.getRoleId(), new String[] {});

		Assert.assertEquals(
			"Expected " + originalCount , originalCount, searchCount(_guestUser));

		//Need to be able to select a facet, see FolderFacetTest


	}

	protected User getUser(String roleName) throws Exception {
		Role role = RoleLocalServiceUtil.getRole(
			TestPropsValues.getCompanyId(), roleName);

		List<User> users = UserLocalServiceUtil.getRoleUsers(
			role.getRoleId(), 0, 1);

		return users.get(0);
	}

	protected int searchCount(User user) {
		SearchContext searchContext = getSearchContext( user, "permissioned");
		Hits hits = search(searchContext);

		return hits.getLength();
	}

	protected SearchContext getSearchContext(User user, String searchTerm) {
		SearchContext searchContext = getSearchContext(user);

		searchContext.setKeywords(searchTerm);

		return searchContext;
	}

	protected SearchContext getSearchContext(User user) {
		SearchContext searchContext = new SearchContext();

		try {
			searchContext.setCompanyId(TestPropsValues.getCompanyId());
			searchContext.setGroupIds(new long[] {_group.getGroupId()});
			searchContext.setUserId(user.getUserId());
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}

		return searchContext;
	}

	protected Hits search(SearchContext searchContext) {
		FacetedSearcher facetedSearcher =
			facetedSearcherManager.createFacetedSearcher();

		try {
			return facetedSearcher.search(searchContext);
		}
		catch (SearchException searchException) {
			throw new RuntimeException(searchException);
		}
	}

	private DLFolder _createDLFolder(String name, long parentFolderId) throws Exception {
		return dlFolderSearchFixture.addFolder(
			parentFolderId, name, StringPool.BLANK,
			getServiceContext());
	}

	protected ServiceContext getServiceContext() {
		try {
			return ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), _user.getUserId());
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	protected void setUpDLFolderSearchFixture() {
		dlFolderSearchFixture = new DLFolderSearchFixture(
			dlAppLocalService, dlFileEntryLocalService, dlFolderLocalService);

		dlFolderSearchFixture.setUp();

		_dlFolders = dlFolderSearchFixture.getDLFolders();
	}

	private Indexer<DLFolder> _indexer;

	private List<DLFolder> _dlFolders;

	@Inject
	protected IndexerRegistry indexerRegistry;

	@Inject
	protected DLFolderLocalService dlFolderLocalService;

	@Inject
	protected DLAppLocalService dlAppLocalService;

	@Inject
	protected DLFileEntryLocalService dlFileEntryLocalService;

	@Inject
	protected static FacetedSearcherManager facetedSearcherManager;

	@Inject
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@DeleteAfterTestRun
	private Group _group;

	//@DeleteAfterTestRun
	private Role _guestRole;

	//@DeleteAfterTestRun
	private User _guestUser;


	private User _user;

	private UserSearchFixture _userSearchFixture;

	protected DLFolderSearchFixture dlFolderSearchFixture;
}
