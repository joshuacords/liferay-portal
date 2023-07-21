/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.base.TasksEntryServiceBaseImpl;
import com.liferay.tasks.service.permission.TasksEntryPermission;
import com.liferay.tasks.service.permission.TasksPermission;

/**
 * @author Ryan Park
 */
public class TasksEntryServiceImpl extends TasksEntryServiceBaseImpl {

	@Override
	public TasksEntry addTasksEntry(
			String title, int priority, long assigneeUserId, int dueDateMonth,
			int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, ServiceContext serviceContext)
		throws PortalException {

		TasksPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ENTRY);

		return tasksEntryLocalService.addTasksEntry(
			getUserId(), title, priority, assigneeUserId, dueDateMonth,
			dueDateDay, dueDateYear, dueDateHour, dueDateMinute, neverDue,
			serviceContext);
	}

	@Override
	public TasksEntry deleteTasksEntry(long tasksEntryId)
		throws PortalException {

		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.UPDATE);

		return tasksEntryLocalService.deleteTasksEntry(tasksEntryId);
	}

	@Override
	public TasksEntry getTasksEntry(long tasksEntryId) throws PortalException {
		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.VIEW);

		return tasksEntryLocalService.getTasksEntry(tasksEntryId);
	}

	@Override
	public TasksEntry updateTasksEntry(
			long tasksEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, int status, ServiceContext serviceContext)
		throws PortalException {

		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.UPDATE);

		return tasksEntryLocalService.updateTasksEntry(
			tasksEntryId, title, priority, assigneeUserId, resolverUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			neverDue, status, serviceContext);
	}

	@Override
	public TasksEntry updateTasksEntryStatus(
			long tasksEntryId, long resolverUserId, int status,
			ServiceContext serviceContext)
		throws PortalException {

		TasksEntryPermission.check(
			getPermissionChecker(), tasksEntryId, ActionKeys.UPDATE);

		return tasksEntryLocalService.updateTasksEntryStatus(
			tasksEntryId, resolverUserId, status, serviceContext);
	}

}