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
		String[] roleNamesWithViewPermission,
		JournalFolderProxy journalFolderProxy)
		throws Exception {

		_resourceActionLocalService = resourceActionLocalService;
		_roleProxyFactory = roleProxyFactory;
		_roleNamesWithViewPermission = roleNamesWithViewPermission;

		_createTreePath(journalFolderProxy);
		_mockRoleViewPermissions();
	}

	public String getResourcePrimKey() {
		return _RESOURCE_PRIM_KEY;
	}

	public String getTreePath() {
		return _treePath;
	}

	private void _createTreePath(JournalFolderProxy journalFolderProxy) {
		StringBuilder sb = new StringBuilder(3);

		if (journalFolderProxy != null) {
			sb.append(journalFolderProxy.getTreePath());
		}
		else {
			sb.append("/");
		}

		sb.append(_RESOURCE_PRIM_KEY);
		sb.append("/");

		_treePath = sb.toString();
	}

	private void _mockRoleViewPermissions() throws Exception {
		_mockViewFolderResourceAction();

		for (String roleName : _roleNamesWithViewPermission) {
			_rolesWithViewPermission.add(_roleProxyFactory.getRole(roleName));
		}

		_roleProxyFactory.mockResourceActionWithRolesOnAsset(
			_viewFolderResourceAction, _rolesWithViewPermission,
			_CLASS_NAME_JOURNAL_FOLDER, _RESOURCE_PRIM_KEY);
	}

	private void _mockViewFolderResourceAction() throws Exception {
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

	private static final String _RESOURCE_PRIM_KEY = StringUtil.toString(
		RandomTestUtil.randomLong());

	private static final String _VIEW_ACTION_ID = ActionKeys.VIEW;

	private final ResourceActionLocalService _resourceActionLocalService;
	private final String[] _roleNamesWithViewPermission;
	private final RoleProxyFactory _roleProxyFactory;
	private final List<Role> _rolesWithViewPermission = new ArrayList<>();
	private String _treePath;
	private ResourceAction _viewFolderResourceAction;

}