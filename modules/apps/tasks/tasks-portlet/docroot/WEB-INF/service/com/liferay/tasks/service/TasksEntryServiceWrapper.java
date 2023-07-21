/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TasksEntryService}.
 *
 * @author Ryan Park
 * @see TasksEntryService
 * @generated
 */
public class TasksEntryServiceWrapper
	implements ServiceWrapper<TasksEntryService>, TasksEntryService {

	public TasksEntryServiceWrapper(TasksEntryService tasksEntryService) {
		_tasksEntryService = tasksEntryService;
	}

	@Override
	public com.liferay.tasks.model.TasksEntry addTasksEntry(
			String title, int priority, long assigneeUserId, int dueDateMonth,
			int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tasksEntryService.addTasksEntry(
			title, priority, assigneeUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, neverDue, serviceContext);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry deleteTasksEntry(
			long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tasksEntryService.deleteTasksEntry(tasksEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _tasksEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.tasks.model.TasksEntry getTasksEntry(long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tasksEntryService.getTasksEntry(tasksEntryId);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry updateTasksEntry(
			long tasksEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tasksEntryService.updateTasksEntry(
			tasksEntryId, title, priority, assigneeUserId, resolverUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			neverDue, status, serviceContext);
	}

	@Override
	public com.liferay.tasks.model.TasksEntry updateTasksEntryStatus(
			long tasksEntryId, long resolverUserId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tasksEntryService.updateTasksEntryStatus(
			tasksEntryId, resolverUserId, status, serviceContext);
	}

	@Override
	public TasksEntryService getWrappedService() {
		return _tasksEntryService;
	}

	@Override
	public void setWrappedService(TasksEntryService tasksEntryService) {
		_tasksEntryService = tasksEntryService;
	}

	private TasksEntryService _tasksEntryService;

}