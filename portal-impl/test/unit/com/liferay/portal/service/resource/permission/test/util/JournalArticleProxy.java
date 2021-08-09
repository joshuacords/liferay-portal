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

import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * @author Joshua Cords
 */
public class JournalArticleProxy {

	JournalArticleProxy(
			RoleProxyFactory roleProxyFactory,
			PersistedModelLocalService journalArticlePersistedModelLocalService,
			ResourceActionLocalService resourceActionLocalService,
			String[] roleNames)
		throws Exception {

		new JournalArticleProxy(
			roleProxyFactory, journalArticlePersistedModelLocalService,
			resourceActionLocalService, null, roleNames);
	}

	JournalArticleProxy(
			RoleProxyFactory roleProxyFactory,
			PersistedModelLocalService journalArticlePersistedModelLocalService,
			ResourceActionLocalService resourceActionLocalService,
			JournalFolderProxy journalFolderProxy, String[] roleNames)
		throws Exception {

		_roleProxyFactory = roleProxyFactory;
		_resourceActionLocalService = resourceActionLocalService;
		_journalArticlePersistedModelLocalService =
			journalArticlePersistedModelLocalService;
		_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
		_primKey = StringUtil.toString(RandomTestUtil.randomLong());
		_roleNames = roleNames;
		_parentClassPK = journalFolderProxy.getResourcePrimKey();
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
	private String _primKey;
	private ResourceActionLocalService _resourceActionLocalService;
	private String _resourcePrimKey;
	private String[] _roleNames;
	private RoleProxyFactory _roleProxyFactory;
	private List<Role> _roles = new ArrayList<>();
	private String _treePath;

	private ResourceAction _viewArticleResourceAction;

}