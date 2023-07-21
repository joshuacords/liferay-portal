/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.resource.v1_0.util;

import com.liferay.data.engine.rest.internal.constants.DataActionKeys;
import com.liferay.data.engine.rest.internal.constants.DataEngineConstants;
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

/**
 * @author Brian Wing Shun Chan
 */
public class DataEnginePermissionUtil {

	public static void checkOperationPermission(
			GroupLocalService groupLocalService, String operation, long siteId)
		throws Exception {

		if (!StringUtil.equalsIgnoreCase(
				DataEngineConstants.OPERATION_DELETE_PERMISSION, operation) &&
			!StringUtil.equalsIgnoreCase(
				DataEngineConstants.OPERATION_SAVE_PERMISSION, operation)) {

			throw new ValidationException(
				"Operation must be 'delete' or 'save'");
		}

		checkPermission(
			DataActionKeys.DEFINE_PERMISSIONS, groupLocalService, siteId);
	}

	public static void checkPermission(
			String actionId, GroupLocalService groupLocalService, Long siteId)
		throws PortalException {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		Group group = groupLocalService.fetchGroup(siteId);

		if ((group != null) && group.isStagingGroup()) {
			group = group.getLiveGroup();
		}

		if (!permissionChecker.hasPermission(
				group, DataEngineConstants.RESOURCE_NAME, siteId, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, DataEngineConstants.RESOURCE_NAME, siteId,
				actionId);
		}
	}

	public static List<Role> getRoles(
			Company company, RoleLocalService roleLocalService,
			String[] roleNames)
		throws PortalException {

		List<String> invalidRoleNames = new ArrayList<>();
		List<Role> roles = new ArrayList<>();

		for (String roleName : roleNames) {
			try {
				Role role = roleLocalService.getRole(
					company.getCompanyId(), roleName);

				roles.add(role);
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

	public static void persistModelPermission(
			List<String> actionIds, Company company, long modelId,
			String operation, String resourceName,
			ResourcePermissionLocalService resourcePermissionLocalService,
			RoleLocalService roleLocalService, String[] roleNames, long siteId)
		throws Exception {

		if (StringUtil.equalsIgnoreCase(
				DataEngineConstants.OPERATION_SAVE_PERMISSION, operation)) {

			ModelPermissions modelPermissions = new ModelPermissions();

			for (String roleName : roleNames) {
				modelPermissions.addRolePermissions(
					roleName, ArrayUtil.toStringArray(actionIds));
			}

			resourcePermissionLocalService.addModelResourcePermissions(
				company.getCompanyId(), siteId,
				PrincipalThreadLocal.getUserId(), resourceName,
				String.valueOf(modelId), modelPermissions);
		}
		else {
			for (Role role : getRoles(company, roleLocalService, roleNames)) {
				for (String actionId : actionIds) {
					resourcePermissionLocalService.removeResourcePermission(
						company.getCompanyId(), resourceName,
						ResourceConstants.SCOPE_INDIVIDUAL,
						String.valueOf(modelId), role.getRoleId(), actionId);
				}
			}
		}
	}

	public static void persistPermission(
			List<String> actionIds, Company company, String operation,
			ResourcePermissionLocalService resourcePermissionLocalService,
			RoleLocalService roleLocalService, String[] roleNames)
		throws Exception {

		List<Role> roles = getRoles(company, roleLocalService, roleNames);

		if (StringUtil.equalsIgnoreCase(
				DataEngineConstants.OPERATION_SAVE_PERMISSION, operation)) {

			for (Role role : roles) {
				resourcePermissionLocalService.setResourcePermissions(
					company.getCompanyId(), DataEngineConstants.RESOURCE_NAME,
					ResourceConstants.SCOPE_COMPANY,
					String.valueOf(company.getCompanyId()), role.getRoleId(),
					ArrayUtil.toStringArray(actionIds));
			}
		}
		else {
			for (Role role : roles) {
				ResourcePermission resourcePermission =
					resourcePermissionLocalService.fetchResourcePermission(
						company.getCompanyId(),
						DataEngineConstants.RESOURCE_NAME,
						ResourceConstants.SCOPE_COMPANY,
						String.valueOf(company.getCompanyId()),
						role.getRoleId());

				if (resourcePermission != null) {
					resourcePermissionLocalService.deleteResourcePermission(
						resourcePermission);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DataEnginePermissionUtil.class);

}