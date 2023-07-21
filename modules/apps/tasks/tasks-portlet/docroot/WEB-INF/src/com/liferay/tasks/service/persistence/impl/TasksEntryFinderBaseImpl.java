/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.persistence.TasksEntryPersistence;

/**
 * @author Ryan Park
 * @generated
 */
public class TasksEntryFinderBaseImpl extends BasePersistenceImpl<TasksEntry> {

	public TasksEntryFinderBaseImpl() {
		setModelClass(TasksEntry.class);
	}

	/**
	 * Returns the tasks entry persistence.
	 *
	 * @return the tasks entry persistence
	 */
	public TasksEntryPersistence getTasksEntryPersistence() {
		return tasksEntryPersistence;
	}

	/**
	 * Sets the tasks entry persistence.
	 *
	 * @param tasksEntryPersistence the tasks entry persistence
	 */
	public void setTasksEntryPersistence(
		TasksEntryPersistence tasksEntryPersistence) {

		this.tasksEntryPersistence = tasksEntryPersistence;
	}

	@BeanReference(type = TasksEntryPersistence.class)
	protected TasksEntryPersistence tasksEntryPersistence;

}