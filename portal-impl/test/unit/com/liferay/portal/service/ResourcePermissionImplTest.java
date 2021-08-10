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

package com.liferay.portal.service;

import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.service.impl.ResourcePermissionLocalServiceImpl;
import com.liferay.portal.service.resource.permission.test.util.JournalArticleProxy;
import com.liferay.portal.service.resource.permission.test.util.JournalFolderProxy;
import com.liferay.portal.service.resource.permission.test.util.JournalProxyFactory;
import com.liferay.portal.service.resource.permission.test.util.RoleProxyFactory;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.RegistryUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jodd.util.StringUtil;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

//test command: ant test-class -Dtest.class=ResourcePermissionImplTest -Djvm.debug=true
/**
 * @author Joshua Cords
 */
public class ResourcePermissionImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void getDynamicInheritanceRolesOnlyGuest() throws Exception {
		JournalFolderProxy journalFolderProxy =
			_journalProxyFactory.createJournalFolderProxy(
				RoleConstants.GUEST, RoleConstants.OWNER);

		JournalArticleProxy journalArticleProxy =
			_journalProxyFactory.createJournalArticleProxy(
				journalFolderProxy, RoleConstants.GUEST, RoleConstants.OWNER);

		Set<Set<Role>> roleSets =
			_resourcePermissionLocalService.getDynamicInheritanceRoles(
				_companyId, _journalArticleClassName, _scope,
				journalArticleProxy.getResourcePrimKey(),
				journalArticleProxy.getPrimKey(), _viewActionId);

		Set<Set<Role>> expectedRoleSets = _rolesToSetSet(
			_roleProxyFactory.getRole(RoleConstants.GUEST),
			_roleProxyFactory.getRole(RoleConstants.OWNER));

		assertContainsRoleSets(roleSets, expectedRoleSets);
	}

	@Before
	public void setUp() throws Exception {
		_initializeBasicVariables();

		RegistryUtil.setRegistry(new BasicRegistryImpl());

		_resourcePermissionLocalService =
			new ResourcePermissionLocalServiceImpl();

		MockitoAnnotations.initMocks(this);

		_roleProxyFactory = new RoleProxyFactory(
			_resourcePermissionLocalService, _roleLocalService, _companyId);

		_initializeReflections();
		_mockPersistedModelLocalServices();

		_journalProxyFactory = new JournalProxyFactory(
			_journalArticlePersistedModelLocalService,
			_resourceActionLocalService, _roleProxyFactory, _companyId);
	}

	protected void assertContainsRoleSets(
			Set<Set<Role>> expectedRoleSets, Set<Set<Role>> actualRoleSets)
		throws Exception {

		if (expectedRoleSets.size() != actualRoleSets.size()) {
			StringBuilder sb = new StringBuilder(4);

			sb.append("expectedRoleSets size ");
			sb.append(expectedRoleSets.size());
			sb.append(" is not actualRoleSets size ");
			sb.append(actualRoleSets.size());
			sb.append(" ");
			_appendRoleSets(actualRoleSets, sb);

			throw new Exception(sb.toString());
		}

		for (Set<Role> expectedRoleSet : expectedRoleSets) {
			if (!actualRoleSets.contains(expectedRoleSet)) {
				StringBuilder sb = new StringBuilder();

				sb.append("expectedRoleSet [");
				_appendRoleSet(expectedRoleSet, sb);
				sb.append("] from expectedRoleSets [");
				_appendRoleSets(expectedRoleSets, sb);
				sb.append("] was not found in actualRoleSets [");
				_appendRoleSets(actualRoleSets, sb);
				sb.append("]");

				throw new Exception(sb.toString());
			}
		}
	}

	private StringBuilder _appendRoleSet(Set<Role> roleSet, StringBuilder sb)
		throws Exception {
		for (Role role : roleSet) {
			sb.append(role.getDescriptiveName());
			sb.append(", ");
		}

		return sb;
	}

	private StringBuilder _appendRoleSets(
			Set<Set<Role>> roleSets, StringBuilder sb)
		throws Exception {

		for (Set<Role> roleSet : roleSets) {
			sb.append("[");
			_appendRoleSet(roleSet, sb);
			sb.append("], ");
		}

		return sb;
	}

	private void _initializeBasicVariables() {
		_companyId = RandomTestUtil.randomLong();
		_journalArticleClassName = "com.liferay.journal.model.JournalArticle";
		_journalFolderClassName = "com.liferay.journal.model.JournalFolder";
		_scope = 4;
		_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
		_primKey = StringUtil.toString(RandomTestUtil.randomLong());
		_viewActionId = ActionKeys.VIEW;
	}

	private void _initializeReflections() {
		ReflectionTestUtil.setFieldValue(
			_resourcePermissionLocalService, "resourceActionLocalService",
			_resourceActionLocalService);
		ReflectionTestUtil.setFieldValue(
			_resourcePermissionLocalService, "roleLocalService",
			_roleLocalService);
		ReflectionTestUtil.setFieldValue(
			_resourcePermissionLocalService,
			"persistedModelLocalServiceRegistry",
			_persistedModelLocalServiceRegistry);
	}

	private void _mockPersistedModelLocalServices() throws Exception {
		Mockito.doReturn(
			_journalArticlePersistedModelLocalService
		).when(
			_persistedModelLocalServiceRegistry
		).getPersistedModelLocalService(
			_journalArticleClassName
		);

		Mockito.doReturn(
			_journalFolderPersistedModelLocalService
		).when(
			_persistedModelLocalServiceRegistry
		).getPersistedModelLocalService(
			_journalFolderClassName
		);

		Mockito.doReturn(
			_roleProxyFactory.getRole(RoleConstants.GUEST)
		).when(
			_roleLocalService
		).getRole(
			_companyId, RoleConstants.GUEST
		);
	}

	private Set<Set<Role>> _rolesToSetSet(Role... roles) {
		Set<Set<Role>> roleSets = new HashSet<>();

		for (Role role : roles) {
			Set<Role> roleSet = new HashSet<>();

			roleSet.add(role);
			roleSets.add(roleSet);
		}

		return roleSets;
	}

	List<List<ResourcePermission>> resourcePermissionsList = new ArrayList<>();

	@Mock
	private ResourceAction _accessFolderResourceAction;

	private long _companyId;
	private String _journalArticleClassName;

	@Mock
	private PersistedModelLocalService
		_journalArticlePersistedModelLocalService;

	private String _journalFolderClassName;

	@Mock
	private PersistedModelLocalService _journalFolderPersistedModelLocalService;

	private JournalProxyFactory _journalProxyFactory;

	@Mock
	private PersistedModelLocalServiceRegistry
		_persistedModelLocalServiceRegistry;

	private String _primKey;

	@Mock
	private ResourceActionLocalService _resourceActionLocalService;

	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Mock
	private ResourcePermissionPersistence _resourcePermissionPersistence;

	private String _resourcePrimKey;

	@Mock
	private RoleLocalService _roleLocalService;

	private RoleProxyFactory _roleProxyFactory;
	private int _scope;
	private String _viewActionId;

	@Mock
	private ResourceAction _viewArticleResourceAction;

	@Mock
	private ResourceAction _viewFolderResourceAction;

}