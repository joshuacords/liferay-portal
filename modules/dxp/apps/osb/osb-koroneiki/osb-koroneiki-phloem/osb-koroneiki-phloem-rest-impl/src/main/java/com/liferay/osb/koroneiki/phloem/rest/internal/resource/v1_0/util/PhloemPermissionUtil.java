/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util;

import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = PhloemPermissionUtil.class)
public class PhloemPermissionUtil {

	public void persistModelPermission(
			String operation, long companyId, String resourceName,
			long resourcePrimKey, String[] roleNames, List<String> actionIds)
		throws Exception {

		if (StringUtil.equalsIgnoreCase("add", operation)) {
			_addModelPermission(
				companyId, resourceName, resourcePrimKey, roleNames, actionIds);
		}
		else {
			_deleteModelPermission(
				companyId, resourceName, resourcePrimKey, roleNames, actionIds);
		}
	}

	private void _addModelPermission(
			long companyId, String resourceName, long resourcePrimKey,
			String[] roleNames, List<String> actionIds)
		throws Exception {

		List<ResourceAction> resourceActions =
			_resourceActionLocalService.getResourceActions(resourceName);

		for (Role role : _getRoles(companyId, roleNames)) {
			ResourcePermission resourcePermission =
				_resourcePermissionLocalService.fetchResourcePermission(
					companyId, resourceName, ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(resourcePrimKey), role.getRoleId());

			List<String> roleActionIds = new ArrayList<>(actionIds);

			if (resourcePermission != null) {
				for (ResourceAction resourceAction : resourceActions) {
					if (roleActionIds.contains(resourceAction.getActionId())) {
						continue;
					}

					if (resourcePermission.hasAction(resourceAction)) {
						roleActionIds.add(resourceAction.getActionId());
					}
				}
			}

			_resourcePermissionLocalService.setResourcePermissions(
				companyId, resourceName, ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(resourcePrimKey), role.getRoleId(),
				roleActionIds.toArray(new String[0]));
		}
	}

	private void _deleteModelPermission(
			long companyId, String resourceName, long resourcePrimKey,
			String[] roleNames, List<String> actionIds)
		throws Exception {

		for (Role role : _getRoles(companyId, roleNames)) {
			for (String actionId : actionIds) {
				_resourcePermissionLocalService.removeResourcePermission(
					companyId, resourceName, ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(resourcePrimKey), role.getRoleId(),
					actionId);
			}
		}
	}

	private List<Role> _getRoles(long companyId, String[] roleNames)
		throws PortalException {

		List<String> invalidRoleNames = new ArrayList<>();
		List<Role> roles = new ArrayList<>();

		for (String roleName : roleNames) {
			try {
				roles.add(_roleLocalService.getRole(companyId, roleName));
			}
			catch (NoSuchRoleException noSuchRoleException) {
				if (_log.isDebugEnabled()) {
					_log.debug(roleName, noSuchRoleException);
				}

				invalidRoleNames.add(roleName);
			}
		}

		if (!invalidRoleNames.isEmpty()) {
			throw new ValidationException(
				"Invalid roles: " + ArrayUtil.toStringArray(invalidRoleNames));
		}

		return roles;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PhloemPermissionUtil.class);

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}