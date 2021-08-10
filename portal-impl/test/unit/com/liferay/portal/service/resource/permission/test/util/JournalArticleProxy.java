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

import com.liferay.portal.kernel.model.BaseChildModel;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.test.util.RandomTestUtil;

import java.util.ArrayList;
import java.util.List;

import jodd.util.StringUtil;

import org.mockito.Mockito;

/**
 * @author Joshua Cords
 */
public class JournalArticleProxy {

	public JournalArticleProxy(
		PersistedModelLocalService journalArticlePersistedModelLocalService,
		ResourceActionLocalService resourceActionLocalService,
		RoleProxyFactory roleProxyFactory, String[] roleNames,
		JournalFolderProxy journalFolderProxy)
		throws Exception {

		_journalArticlePersistedModelLocalService =
			journalArticlePersistedModelLocalService;
		_resourceActionLocalService = resourceActionLocalService;
		_roleProxyFactory = roleProxyFactory;
		_roleNames = roleNames;


		if(journalFolderProxy != null) {
			_parentClassPK = journalFolderProxy.getResourcePrimKey();
		} else {
			_parentClassPK = "";
		}

		_createTreePath(journalFolderProxy);
		_setUpMocks();
		_mockRoles(roleNames);
		_mockPersistedModel();
	}

	public String getPrimKey() {
		return _primKey;
	}

	public String getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public String[] getRoleNames() {
		return _roleNames;
	}

	public String getTreePath() {
		return _treePath;
	}

	public abstract class PersistenceBaseChild
		implements BaseChildModel, PersistedModel {
	}

	private void _createTreePath(JournalFolderProxy journalFolderProxy) {
		_treePath =
			journalFolderProxy != null ? journalFolderProxy.getTreePath() : "/";
	}

	private void _mockPersistedModel() throws Exception {
		_journalArticlePersistedBaseChildModel = Mockito.mock(
			PersistenceBaseChild.class);

		Mockito.doReturn(
			_parentClassPK
		).when(
			_journalArticlePersistedBaseChildModel
		).getParentClassPK();

		Mockito.doReturn(
			_journalFolderClassName
		).when(
			_journalArticlePersistedBaseChildModel
		).getParentClassName();

		_mockTreePath();

		Mockito.doReturn(
			_journalArticlePersistedBaseChildModel
		).when(
			_journalArticlePersistedModelLocalService
		).getPersistedModel(
			Long.valueOf(_primKey)
		);
	}

	private void _mockRoles(String[] roleNames) throws Exception {
		List<Role> roles = new ArrayList<>();

		for (String roleName : roleNames) {
			roles.add(_roleProxyFactory.getRole(roleName));
		}

		_roles.addAll(roles);
		_roleProxyFactory.mockResourceActionWithRolesOnAsset(
			_viewArticleResourceAction, roles, _journalArticleClassName,
			_resourcePrimKey);
	}

	private void _mockTreePath() {
		Mockito.doReturn(
			_treePath
		).when(
			_journalArticlePersistedBaseChildModel
		).getTreePath();
	}

	private void _setUpMocks() throws Exception {
		_viewArticleResourceAction = Mockito.mock(ResourceAction.class);

		Mockito.doReturn(
			_viewArticleResourceAction
		).when(
			_resourceActionLocalService
		).getResourceAction(
			_journalArticleClassName, _viewActionId
		);

	}

	private static final String _journalArticleClassName =
		"com.liferay.journal.model.JournalArticle";
	private static final String _journalFolderClassName =
		"com.liferay.journal.model.JournalFolder";
	private static final String _viewActionId = ActionKeys.VIEW;

	private PersistenceBaseChild _journalArticlePersistedBaseChildModel;
	private PersistedModelLocalService
		_journalArticlePersistedModelLocalService;
	private String _parentClassPK;
	private String _primKey = StringUtil.toString(RandomTestUtil.randomLong());
	private ResourceActionLocalService _resourceActionLocalService;
	private String _resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
	private String[] _roleNames;
	private RoleProxyFactory _roleProxyFactory;
	private List<Role> _roles = new ArrayList<>();
	private String _treePath;

	private ResourceAction _viewArticleResourceAction;

}