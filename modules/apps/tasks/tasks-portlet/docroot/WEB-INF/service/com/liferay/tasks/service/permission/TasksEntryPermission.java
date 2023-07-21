/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;

/**
 * @author Ryan Park
 */
public class TasksEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long tasksEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, tasksEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, TasksEntry tasksEntry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, tasksEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long tasksEntryId,
			String actionId)
		throws PortalException {

		return contains(
			permissionChecker,
			TasksEntryLocalServiceUtil.getTasksEntry(tasksEntryId), actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, TasksEntry tasksEntry,
		String actionId) {

		if (permissionChecker.getUserId() == tasksEntry.getAssigneeUserId()) {
			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				tasksEntry.getCompanyId(), TasksEntry.class.getName(),
				tasksEntry.getTasksEntryId(), tasksEntry.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			tasksEntry.getGroupId(), TasksEntry.class.getName(),
			tasksEntry.getTasksEntryId(), actionId);
	}

}