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
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.test.util.RandomTestUtil;

import java.util.ArrayList;
import java.util.List;

import jodd.util.StringUtil;

/**
 * @author Joshua Cords
 */
public class JournalFolderProxy {

	public JournalFolderProxy(
			ResourceActionLocalService resourceActionLocalService,
			ResourceAction viewFolderResourceAction,
			RoleProxyFactory roleProxyFactory,
			String[] roleNamesWithViewPermission,
			JournalFolderProxy journalFolderProxy)
		throws Exception {

		_resourceActionLocalService = resourceActionLocalService;
		_viewFolderResourceAction = viewFolderResourceAction;
		_roleProxyFactory = roleProxyFactory;
		_roleNamesWithViewPermission = roleNamesWithViewPermission;

		_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());

		_createTreePath(journalFolderProxy);
		_mockRolesWithViewPermissions();
	}

	public String getResourcePrimKey() {
		return _resourcePrimKey;
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

		sb.append(_resourcePrimKey);
		sb.append("/");

		_treePath = sb.toString();
	}

	private void _mockRolesWithViewPermissions() throws Exception {
		for (String roleName : _roleNamesWithViewPermission) {
			_rolesWithViewPermission.add(_roleProxyFactory.getRole(roleName));
		}

		_roleProxyFactory.mockResourceActionWithRolesOnAsset(
			_viewFolderResourceAction, _rolesWithViewPermission,
			_CLASS_NAME_JOURNAL_FOLDER, _resourcePrimKey);
	}

	private static final String _CLASS_NAME_JOURNAL_FOLDER =
		"com.liferay.journal.model.JournalFolder";

	private final ResourceActionLocalService _resourceActionLocalService;
	private final String _resourcePrimKey;
	private final String[] _roleNamesWithViewPermission;
	private final RoleProxyFactory _roleProxyFactory;
	private final List<Role> _rolesWithViewPermission = new ArrayList<>();
	private String _treePath;
	private final ResourceAction _viewFolderResourceAction;

}