/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the TasksEntry service. Represents a row in the &quot;TMS_TasksEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ryan Park
 * @see TasksEntryModel
 * @generated
 */
@ImplementationClassName("com.liferay.tasks.model.impl.TasksEntryImpl")
@ProviderType
public interface TasksEntry extends PersistedModel, TasksEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.tasks.model.impl.TasksEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TasksEntry, Long> TASKS_ENTRY_ID_ACCESSOR =
		new Accessor<TasksEntry, Long>() {

			@Override
			public Long get(TasksEntry tasksEntry) {
				return tasksEntry.getTasksEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TasksEntry> getTypeClass() {
				return TasksEntry.class;
			}

		};

	public String getAssigneeFullName();

	public String getPriorityLabel();

	public String getReporterFullName();

	public String getStatusLabel();

}