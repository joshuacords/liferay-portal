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
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.kernel.test.ReflectionTestUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;

/**
 * @author Joshua Cords
 */
public class RoleProxyFactory {

	public RoleProxyFactory(
		ResourcePermissionLocalService resourcePermissionLocalService,
		RoleLocalService roleLocalService, long companyId) {

		_resourcePermissionLocalService = resourcePermissionLocalService;
		_roleLocalService = roleLocalService;
		_companyId = companyId;

		_scope = 4;

		_resourcePermissionPersistence = Mockito.mock(
			ResourcePermissionPersistence.class);

		ReflectionTestUtil.setFieldValue(
			_resourcePermissionLocalService, "resourcePermissionPersistence",
			_resourcePermissionPersistence);
	}

	public Role getRole(String roleName) throws Exception {
		if (_roleProxies.containsKey(roleName)) {
			RoleProxy roleProxy = _roleProxies.get(roleName);

			return roleProxy.getRole();
		}

		RoleProxy roleProxy = _createRoleProxy(roleName);

		return roleProxy.getRole();
	}

	public RoleProxy getRoleProxy(String roleName) throws Exception {
		if (_roleProxies.containsKey(roleName)) {
			return _roleProxies.get(roleName);
		}

		return _createRoleProxy(roleName);
	}

	public void setResourceActionToRolesOnAsset(
		ResourceAction resourceAction, List<Role> rolesWithResourceAction,
		String className, String primKey)
		throws Exception {

		_mockResourcePermissionLocalServiceGetRolesInside(
			_companyId, className, _scope, primKey, rolesWithResourceAction, resourceAction);
	}

	private void _addRolesToRoleLocalServiceInside(List<Role> roles)
		throws Exception {

		for (Role role : roles) {
			Mockito.when(
				_roleLocalService.getRole(role.getRoleId())
			).thenReturn(
				role
			);
		}
	}

	private List<ResourcePermission> _createResourcePermissionMocksInside(
		ResourceAction resourceAction, List<Role> roles) {

		List<ResourcePermission> resourcePermissions = new ArrayList<>();

		for (Role role : roles) {
			ResourcePermission resourcePermission = Mockito.mock(
				ResourcePermission.class);

			Mockito.doReturn(
				true
			).when(
				resourcePermission
			).hasAction(
				resourceAction
			);

			Mockito.doReturn(
				role.getRoleId()
			).when(
				resourcePermission
			).getRoleId();

			resourcePermissions.add(resourcePermission);
		}

		return resourcePermissions;
	}

	private RoleProxy _createRoleProxy(String roleName) throws Exception {
		RoleProxy roleProxy = new RoleProxy(
			_roleLocalService, _companyId, roleName);

		_roleProxies.put(roleName, roleProxy);

		return roleProxy;
	}

	private void _mockResourcePermissionLocalServiceGetRolesInside(
			long companyId, String className, int scope, String resourcePrimKey,
			List<Role> roles, ResourceAction resourceAction)
		throws Exception {

		List<ResourcePermission> resourcePermissions =
			_createResourcePermissionMocksInside(resourceAction, roles);

		_addRolesToRoleLocalServiceInside(roles);

		Mockito.doReturn(
			resourcePermissions
		).when(
			_resourcePermissionPersistence
		).findByC_N_S_P(
			companyId, className, scope, resourcePrimKey
		);
	}

	private final long _companyId;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;
	private final ResourcePermissionPersistence _resourcePermissionPersistence;
	private final RoleLocalService _roleLocalService;
	private final Map<String, RoleProxy> _roleProxies = new HashMap<>();
	private final int _scope;

}