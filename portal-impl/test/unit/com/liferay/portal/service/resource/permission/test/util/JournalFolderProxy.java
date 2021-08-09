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

package com.liferay.portal.service.resource.permission.test.util;

import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.test.util.RandomTestUtil;

import java.util.ArrayList;
import java.util.List;

import jodd.util.StringUtil;

import org.mockito.Mockito;

/**
 * @author Joshua Cords
 */
public class JournalFolderProxy {

	public JournalFolderProxy(
			ResourceActionLocalService resourceActionLocalService,
			RoleProxyFactory roleProxyFactory,
			JournalFolderProxy journalFolderProxy, String[] roleNames)
		throws Exception {

		_resourceActionLocalService = resourceActionLocalService;
		_roleProxyFactory = roleProxyFactory;

		_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
		//primKey may be the same with a JournalFolder
		_roleNames = roleNames;

		_createTreePath(journalFolderProxy);
		_mockRoles(roleNames);
	}

	public JournalFolderProxy(
			ResourceActionLocalService resourceActionLocalService,
			RoleProxyFactory roleProxyFactory, String[] roleNames)
		throws Exception {

		_resourceActionLocalService = resourceActionLocalService;
		_roleProxyFactory = roleProxyFactory;

		_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
		//primKey may be the same with a JournalFolder
		_roleNames = roleNames;

		_treePath = "/" + _resourcePrimKey + "/";

		_mockRoles(roleNames);
	}

	public String getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public String[] getRoleNames() {
		return _roleNames;
	}

	//journalFolder uses different primKey

	public String getTreePath() {
		return _treePath;
	}

	private void _createTreePath(JournalFolderProxy journalFolderProxy) {
		if (journalFolderProxy != null) {
			_treePath = journalFolderProxy.getTreePath();
		}
		else {
			_treePath = "/";
		}

		_treePath = _treePath.concat(_resourcePrimKey + "/");
	}

	private void _mockRoles(String[] roleNames) throws Exception {
		_setUpMocks();

		List<Role> roles = new ArrayList<>();

		for (String roleName : roleNames) {
			roles.add(_roleProxyFactory.getRole(roleName));
		}

		_roles.addAll(roles);
		_roleProxyFactory.setResourceActionToRolesOnAsset(
			_viewFolderResourceAction, roles, _CLASS_NAME_JOURNAL_FOLDER,
			_resourcePrimKey);
	}

	private void _setUpMocks() throws Exception {
		_viewFolderResourceAction = Mockito.mock(ResourceAction.class);

		Mockito.doReturn(
			_viewFolderResourceAction
		).when(
			_resourceActionLocalService
		).getResourceAction(
			_CLASS_NAME_JOURNAL_FOLDER, _VIEW_ACTION_ID
		);
	}

	private static final String _CLASS_NAME_JOURNAL_FOLDER =
		"com.liferay.journal.model.JournalFolder";

	private static final String _VIEW_ACTION_ID = ActionKeys.VIEW;

	private final ResourceActionLocalService _resourceActionLocalService;
	private final String _resourcePrimKey;
	private final String[] _roleNames;
	private final RoleProxyFactory _roleProxyFactory;
	private final List<Role> _roles = new ArrayList<>();
	private String _treePath;
	private ResourceAction _viewFolderResourceAction;

}