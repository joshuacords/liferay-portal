package com.liferay.portal.service.resource.permission.test.util;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import org.mockito.Mockito;

public class RoleProxy{

	RoleProxy(
		RoleLocalService roleLocalService, long companyId, String roleName)
		throws Exception {
		_companyId = companyId;
		_role = Mockito.mock(Role.class);
		_roleLocalService = roleLocalService;
		_roleId = RandomTestUtil.randomLong();
		_roleName = roleName;
		_mockRole();
	}

	private void _mockRole() throws Exception {
//		try {
		Mockito.doReturn(
			_roleId
		).when(
			_role
		).getRoleId();

		Mockito.doReturn(
			_role
		).when(
			_roleLocalService
		).getRole(_roleId);

		Mockito.doReturn(
			_role
		).when(
			_roleLocalService
		).getRole(_companyId, _roleName);

		Mockito.doReturn(
			_roleName
		).when(
			_role
		).getDescriptiveName();

//		} catch (Exception exception) {
//			System.out.println("Failed to mock role " + _roleName);
//		}
	}

	public Role getRole() {
		return _role;
	}

	private long _companyId;
	private RoleLocalService _roleLocalService;
	private long _roleId;
	private String _roleName;
	private Role _role;
}