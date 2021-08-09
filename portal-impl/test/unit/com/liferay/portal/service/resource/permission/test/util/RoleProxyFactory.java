package com.liferay.portal.service.resource.permission.test.util;

import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleProxyFactory {

	public RoleProxyFactory(RoleLocalService roleLocalService,
		 ResourcePermissionLocalService resourcePermissionLocalService,
		 long companyId) {

		_resourcePermissionLocalService = resourcePermissionLocalService;

		MockitoAnnotations.initMocks(this);

		ReflectionTestUtil.setFieldValue(
			_resourcePermissionLocalService, "resourcePermissionPersistence",
			_resourcePermissionPersistence);

		_roleLocalService = roleLocalService;
		_companyId = companyId;
		_scope = 4;
	}

	public Role getRole(String roleName) throws Exception {
		if (_roleProxies.containsKey(roleName)) {
			return _roleProxies.get(roleName).getRole();
		}

		RoleProxy roleProxy = new RoleProxy(_roleLocalService, _companyId, roleName);
		_roleProxies.put(roleName, roleProxy);
		return roleProxy.getRole();
	}

	public RoleProxy getRoleProxy(String roleName) throws Exception {
		if (_roleProxies.containsKey(roleName)) {
			return _roleProxies.get(roleName);
		}

		RoleProxy roleProxy = new RoleProxy(_roleLocalService, _companyId, roleName);
		_roleProxies.put(roleName, roleProxy);
		return roleProxy;
	}

	public void setRoleToAsset(String className, String primKey, ResourceAction resourceAction, String ... roleNames) throws Exception {
		List<Role> classRoles = new ArrayList<>();

		for(String roleName : roleNames) {
			classRoles.add(getRole(roleName));
		}

		//try {
			_mockResourcePermissionLocalServiceGetRolesInside(
				_companyId, className, _scope, primKey, classRoles, resourceAction);
//		} catch (Exception exception) {
//			System.out.println("Failed to mock ResourcePermissionLocalService roles");
//		}
	}

	private void _mockResourcePermissionLocalServiceGetRolesInside(
		long companyId, String className, int scope, String resourcePrimKey,
		List<Role> roles, ResourceAction resourceAction) throws Exception {

		List<ResourcePermission> resourcePermissions =
			_createResourcePermissionMocksInside(resourceAction, roles);

		_addRolesToRoleLocalServiceInside(roles);

		Mockito.doReturn(
			resourcePermissions
		).when(
			_resourcePermissionPersistence
		).findByC_N_S_P(companyId, className, scope, resourcePrimKey);

	}

	private List<ResourcePermission> _createResourcePermissionMocksInside(
		ResourceAction resourceAction, List<Role> roles) {

		List<ResourcePermission> resourcePermissions = new ArrayList<>();

		for(Role role : roles) {
			ResourcePermission resourcePermission =
				Mockito.mock(ResourcePermission.class);

			Mockito.doReturn(
				true
			).when(
				resourcePermission
			).hasAction(resourceAction);

			Mockito.doReturn(
				role.getRoleId()
			).when(
				resourcePermission
			).getRoleId();

			resourcePermissions.add(resourcePermission);
		}

		return resourcePermissions;
	}

	private void _addRolesToRoleLocalServiceInside(List<Role> roles) throws Exception {
		for(Role role : roles) {

			Mockito.when(
				_roleLocalService.getRole(role.getRoleId())
			).thenReturn(
				role
			);
		}
	}

	@Mock
	private ResourcePermissionPersistence _resourcePermissionPersistence;

	private ResourcePermissionLocalService _resourcePermissionLocalService;
	private long _companyId;
	private int _scope;
	private Map<String, RoleProxy> _roleProxies = new HashMap<>();
	private RoleLocalService _roleLocalService;

}
