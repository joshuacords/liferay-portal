/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.tasks.model.TasksEntry;

/**
 * Provides the remote service utility for TasksEntry. This utility wraps
 * <code>com.liferay.tasks.service.impl.TasksEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Ryan Park
 * @see TasksEntryService
 * @generated
 */
public class TasksEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.tasks.service.impl.TasksEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static TasksEntry addTasksEntry(
			String title, int priority, long assigneeUserId, int dueDateMonth,
			int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addTasksEntry(
			title, priority, assigneeUserId, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, neverDue, serviceContext);
	}

	public static TasksEntry deleteTasksEntry(long tasksEntryId)
		throws PortalException {

		return getService().deleteTasksEntry(tasksEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static TasksEntry getTasksEntry(long tasksEntryId)
		throws PortalException {

		return getService().getTasksEntry(tasksEntryId);
	}

	public static TasksEntry updateTasksEntry(
			long tasksEntryId, String title, int priority, long assigneeUserId,
			long resolverUserId, int dueDateMonth, int dueDateDay,
			int dueDateYear, int dueDateHour, int dueDateMinute,
			boolean neverDue, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateTasksEntry(
			tasksEntryId, title, priority, assigneeUserId, resolverUserId,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			neverDue, status, serviceContext);
	}

	public static TasksEntry updateTasksEntryStatus(
			long tasksEntryId, long resolverUserId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateTasksEntryStatus(
			tasksEntryId, resolverUserId, status, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static TasksEntryService getService() {
		return _service;
	}

	public static void setService(TasksEntryService service) {
		_service = service;
	}

	private static volatile TasksEntryService _service;

}