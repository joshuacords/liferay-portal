/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.test.util;

import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.test.randomizerbumpers.NumericStringRandomizerBumper;
import com.liferay.portal.kernel.test.randomizerbumpers.UniqueStringRandomizerBumper;

/**
 * @author Roberto Díaz
 */
public class RoleTestUtil {

	public static long addGroupRole(long groupId) throws Exception {
		Role role = addRole(RoleConstants.TYPE_SITE);

		RoleLocalServiceUtil.addGroupRole(groupId, role.getRoleId());

		return role.getRoleId();
	}

	public static long addOrganizationRole(long groupId) throws Exception {
		Role role = addRole(RoleConstants.TYPE_ORGANIZATION);

		RoleLocalServiceUtil.addGroupRole(groupId, role.getRoleId());

		return role.getRoleId();
	}

	public static long addRegularRole(long groupId) throws Exception {
		Role role = addRole(RoleConstants.TYPE_REGULAR);

		RoleLocalServiceUtil.addGroupRole(groupId, role.getRoleId());

		return role.getRoleId();
	}

	public static void addResourcePermission(
			Role role, String resourceName, int scope, String primKey,
			String actionId)
		throws Exception {

		ResourcePermissionLocalServiceUtil.addResourcePermission(
			role.getCompanyId(), resourceName, scope, primKey, role.getRoleId(),
			actionId);
	}

	public static void addResourcePermission(
			String roleName, String resourceName, int scope, String primKey,
			String actionId)
		throws Exception {

		Role role = RoleLocalServiceUtil.getRole(
			TestPropsValues.getCompanyId(), roleName);

		addResourcePermission(role, resourceName, scope, primKey, actionId);
	}

	public static Role addRole(int roleType) throws Exception {
		return addRole(
			RandomTestUtil.randomString(
				NumericStringRandomizerBumper.INSTANCE,
				UniqueStringRandomizerBumper.INSTANCE),
			roleType);
	}

	public static Role addRole(String roleName, int roleType) throws Exception {
		Role role = null;

		try {
			role = RoleLocalServiceUtil.getRole(
				TestPropsValues.getCompanyId(), roleName);
		}
		catch (NoSuchRoleException noSuchRoleException) {
			role = RoleLocalServiceUtil.addRole(
				TestPropsValues.getUserId(), null, 0, roleName, null, null,
				roleType, null, null);
		}

		return role;
	}

	public static Role addRole(
			String roleName, int roleType, String resourceName, int scope,
			String primKey, String actionId)
		throws Exception {

		Role role = addRole(roleName, roleType);

		addResourcePermission(role, resourceName, scope, primKey, actionId);

		return role;
	}

	public static void removeResourcePermission(
			String roleName, String resourceName, int scope, String primKey,
			String actionId)
		throws Exception {

		Role role = RoleLocalServiceUtil.getRole(
			TestPropsValues.getCompanyId(), roleName);

		ResourcePermissionLocalServiceUtil.removeResourcePermission(
			role.getCompanyId(), resourceName, scope, primKey, role.getRoleId(),
			actionId);
	}

}