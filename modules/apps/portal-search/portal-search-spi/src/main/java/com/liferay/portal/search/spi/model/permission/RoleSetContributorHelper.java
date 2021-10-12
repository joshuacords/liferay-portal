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

package com.liferay.portal.search.spi.model.permission;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Joshua Cords
 */
public class RoleSetContributorHelper {

	public RoleSetContributorHelper(
		ResourcePermissionLocalService resourcePermissionLocalService,
		RoleLocalService roleLocalService) {

		_resourcePermissionLocalService = resourcePermissionLocalService;
		_roleLocalService = roleLocalService;
	}

	public Set<String> createRoleIdSet(
			long companyId, long groupId, String className, String classPK,
			List<Role> roles)
		throws PortalException {

		Set<String> roleIdSet = new HashSet<>();

		for (Role role : roles) {
			roleIdSet.add(
				_roleToRoleId(companyId, groupId, className, classPK, role));
		}

		return roleIdSet;
	}

	public Set<String> createRoleIdSet(
			RoleSetContributorContext roleSetContributorContext,
			String className, String classPK, List<Role> roles)
		throws PortalException {

		return createRoleIdSet(
			roleSetContributorContext.getCompanyId(),
			roleSetContributorContext.getGroupId(), className, classPK, roles);
	}

	private boolean _isOwnerRoleId(long companyId, long roleId) {
		Role ownerRole = _roleLocalService.fetchRole(
			companyId, RoleConstants.OWNER);

		if ((ownerRole != null) && (roleId == ownerRole.getRoleId())) {
			return true;
		}

		return false;
	}

	private String _roleToRoleId(
			long companyId, long groupId, String className, String classPK,
			Role role)
		throws PortalException {

		Role ownerRole = _roleLocalService.getRole(
			companyId, RoleConstants.OWNER);

		if ((role.getType() == RoleConstants.TYPE_ORGANIZATION) ||
			(role.getType() == RoleConstants.TYPE_SITE)) {

			return groupId + StringPool.DASH + role.getRoleId();
		}
		else if (_isOwnerRoleId(companyId, role.getRoleId())) {
			ResourcePermission resourcePermission =
				_resourcePermissionLocalService.getResourcePermission(
					companyId, className, ResourceConstants.SCOPE_INDIVIDUAL,
					classPK, ownerRole.getRoleId());

			return resourcePermission.getOwnerId() + StringPool.DASH +
				role.getRoleId();
		}
		else {
			return String.valueOf(role.getRoleId());
		}
	}

	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;
	private final RoleLocalService _roleLocalService;

}