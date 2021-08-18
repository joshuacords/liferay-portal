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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.ResourceAction;
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

import java.util.HashSet;
import java.util.Set;

import jodd.util.StringUtil;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

//test: ant test-class -Dtest.class=ResourcePermissionImplTest -Djvm.debug=true
/**
 * @author Joshua Cords
 */
public class ResourcePermissionImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

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

	@Test
	public void testDynamicInheritanceRolesCombinations() throws Exception {
		long creatorUserId = RandomTestUtil.randomLong();

		JournalFolderProxy journalFolderProxy1 =
			_journalProxyFactory.createJournalFolderProxy(
				creatorUserId, RoleConstants.OWNER, RoleConstants.USER);

		JournalFolderProxy journalFolderProxy2 =
			_journalProxyFactory.createJournalFolderProxy(
				journalFolderProxy1, creatorUserId, RoleConstants.OWNER,
				RoleConstants.SITE_MEMBER);

		JournalArticleProxy journalArticleProxy =
			_journalProxyFactory.createJournalArticleProxy(
				journalFolderProxy2, creatorUserId,
				RoleConstants.OWNER, RoleConstants.POWER_USER);

		Set<Set<String>> roleSets =
			_resourcePermissionLocalService.getFlattenedInheritanceRoleIds(
				_companyId, _groupId, _journalArticleClassName, _scope,
				journalArticleProxy.getResourcePrimKey(),
				journalArticleProxy.getPrimKey(), _viewActionId);

		Set<Set<Role>> expectedRoleSets = new HashSet<>();

		Set<Role> ownerRoleSet = new HashSet<>();

		ownerRoleSet.add(_roleProxyFactory.getRole(RoleConstants.OWNER));

		expectedRoleSets.add(ownerRoleSet);

		Set<Role> comboRoleSet = new HashSet<>();

		comboRoleSet.add(_roleProxyFactory.getRole(RoleConstants.POWER_USER));
		comboRoleSet.add(_roleProxyFactory.getRole(RoleConstants.SITE_MEMBER));
		comboRoleSet.add(_roleProxyFactory.getRole(RoleConstants.USER));

		expectedRoleSets.add(comboRoleSet);

//		assertContainsRoleSets(expectedRoleSets, roleSets);
	}

	@Test
	public void testDynamicInheritanceRolesGuestAsWildcard() throws Exception {
		long creatorUserId = RandomTestUtil.randomLong();

		JournalFolderProxy journalFolderProxy =
			_journalProxyFactory.createJournalFolderProxy(
				creatorUserId, RoleConstants.GUEST, RoleConstants.USER);

		JournalArticleProxy journalArticleProxy =
			_journalProxyFactory.createJournalArticleProxy(
				journalFolderProxy, creatorUserId, RoleConstants.GUEST,
				RoleConstants.OWNER);

		Set<Set<String>> roleSets =
			_resourcePermissionLocalService.getFlattenedInheritanceRoleIds(
				_companyId, _groupId, _journalArticleClassName, _scope,
				journalArticleProxy.getResourcePrimKey(),
				journalArticleProxy.getPrimKey(), _viewActionId);

		Set<Set<Role>> expectedRoleSets = _rolesToSetSet(
			_roleProxyFactory.getRole(RoleConstants.GUEST),
			_roleProxyFactory.getRole(RoleConstants.OWNER),
			_roleProxyFactory.getRole(RoleConstants.USER));

//		assertContainsRoleSets(expectedRoleSets, roleSets);
	}

	@Test
	public void testDynamicInheritanceRolesNoFolders() throws Exception {
		long creatorUserId = RandomTestUtil.randomLong();

		JournalArticleProxy journalArticleProxy =
			_journalProxyFactory.createJournalArticleProxy(
				creatorUserId, RoleConstants.GUEST, RoleConstants.OWNER);

		Set<Set<String>> roleIdSets =
			_resourcePermissionLocalService.getFlattenedInheritanceRoleIds(
				_companyId, _groupId, _journalArticleClassName, _scope,
				journalArticleProxy.getResourcePrimKey(),
				journalArticleProxy.getPrimKey(), _viewActionId);

		Set<Set<String>> expectedRoleIdSets = getExpectedRoleIdSets(
			creatorUserId, RoleConstants.GUEST, RoleConstants.OWNER);

		assertContainsRoleSets(expectedRoleIdSets, roleIdSets);
	}

	protected Set<Set<String>> getExpectedRoleIdSets(
		long userId, String ... roleNames) throws Exception {

		Set<Set<String>> roleIdSets = new HashSet<>();

		for (String roleName : roleNames) {
			Role role = _roleProxyFactory.getRole(roleName);

			Set<String> roleIdSet = new HashSet<>();

			if(roleName.equals(RoleConstants.OWNER)) {
				roleIdSet.add(Long.toString(userId) + StringPool.DASH +
					role.getRoleId());
			}
			else if(roleName.equals(RoleConstants.SITE_MEMBER)) {
				roleIdSet.add(Long.toString(_groupId) + StringPool.DASH +
			  		role.getRoleId());
			}
			else {
				roleIdSet.add(Long.toString(role.getRoleId()));
			}

			roleIdSets.add(roleIdSet);
		}

		return roleIdSets;
	}

	protected void assertContainsRoleSets(
			Set<Set<String>> expectedRoleIdSets,
			Set<Set<String>> actualRoleIdSets)
		throws Exception {

		if (expectedRoleIdSets.size() != actualRoleIdSets.size()) {
			StringBuilder sb = new StringBuilder(4);

			sb.append("expectedRoleIdSets size ");
			sb.append(expectedRoleIdSets.size());
			sb.append(" ");

			_appendRoleSets(expectedRoleIdSets, sb);

			sb.append(" is not actualRoleIdSets size ");
			sb.append(actualRoleIdSets.size());
			sb.append(" ");

			_appendRoleSets(actualRoleIdSets, sb);

			throw new Exception(sb.toString());
		}

		for (Set<String> expectedRoleIdSet : expectedRoleIdSets) {
			if (!actualRoleIdSets.contains(expectedRoleIdSet)) {
				StringBuilder sb = new StringBuilder();

				sb.append("expectedRoleIdSet [");

				_appendRoleSet(expectedRoleIdSet, sb);

				sb.append("] from expectedRoleIdSets [");

				_appendRoleSets(expectedRoleIdSets, sb);

				sb.append("] was not found in actualRoleIdSets [");

				_appendRoleSets(actualRoleIdSets, sb);

				sb.append("]");

				throw new Exception(sb.toString());
			}
		}
	}

	private StringBuilder _appendRoleSet(Set<String> roleIdSet, StringBuilder sb)
		throws Exception {

		for (String roleId : roleIdSet) {
			Role role = _roleProxyFactory.getRoleById(roleId);
			sb.append(role.getDescriptiveName());
			sb.append(", ");
		}

		return sb;
	}

	private StringBuilder _appendRoleSets(
			Set<Set<String>> roleIdSets, StringBuilder sb)
		throws Exception {

		for (Set<String> roleSet : roleIdSets) {
			sb.append("[");

			_appendRoleSet(roleSet, sb);
			sb.append("], ");
		}

		return sb;
	}

	private void _initializeBasicVariables() {
		_companyId = RandomTestUtil.randomLong();
		_groupId = RandomTestUtil.randomLong();
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

		Mockito.doReturn(
			_roleProxyFactory.getRole(RoleConstants.OWNER)
		).when(
			_roleLocalService
		).fetchRole(
			_companyId, RoleConstants.OWNER
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

	@Mock
	private ResourceAction _accessFolderResourceAction;

	private long _companyId;
	private long _groupId;
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