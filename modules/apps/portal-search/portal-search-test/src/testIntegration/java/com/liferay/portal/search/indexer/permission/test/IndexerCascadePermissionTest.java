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
import com.liferay.document.library.test.util.search.DLFolderSearchFixture;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.service.ServiceContext;
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
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.users.admin.test.util.search.UserSearchFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osgi.service.component.annotations.Reference;

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
		//dlFolderSearchFixture.tearDown();
	}

	@Before
	public void setUp() throws Exception {

		//setUpUserSearchFixture();

		_user = UserTestUtil.addUser(
			RandomTestUtil.randomString(
				NumericStringRandomizerBumper.INSTANCE,
				UniqueStringRandomizerBumper.INSTANCE),
			LocaleUtil.getDefault(), RandomTestUtil.randomString(),
			"LastName", new long[] {TestPropsValues.getGroupId()});

		_group = GroupTestUtil.addGroup();

		setUpDLFolderSearchFixture();

		_indexer = indexerRegistry.getIndexer(DLFolder.class);
	}

	protected void setUpUserSearchFixture() throws Exception {
		_userSearchFixture = new UserSearchFixture();

		_userSearchFixture.setUp();

		_group = _userSearchFixture.addGroup();

		//_groups = _userSearchFixture.getGroups();

		_user = _userSearchFixture.addUser(
			RandomTestUtil.randomString(), _group);

		//_users = _userSearchFixture.getUsers();
	}

	@Test
	public void testCascadePermission() throws Exception {
		_createDLFolderTree();
	}

	private void _createDLFolderTree() throws Exception {
		DLFolder parentFolder = _createDLFolder(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		DLFolder childFolder = _createDLFolder(parentFolder.getFolderId());

		dlFolderSearchFixture.tearDown();
	}

	private DLFolder _createDLFolder(long parentFolderId) throws Exception {
		return dlFolderSearchFixture.addFolder(
			parentFolderId, "permissioned folder", StringPool.BLANK,
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

	//@DeleteAfterTestRun
	private Group _group;

//	@DeleteAfterTestRun
//	private List<User> _users;

	private User _user;

	private UserSearchFixture _userSearchFixture;

	protected DLFolderSearchFixture dlFolderSearchFixture;
}
