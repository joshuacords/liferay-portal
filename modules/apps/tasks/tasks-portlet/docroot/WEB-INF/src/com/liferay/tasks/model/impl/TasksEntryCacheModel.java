/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.tasks.model.TasksEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TasksEntry in entity cache.
 *
 * @author Ryan Park
 * @generated
 */
public class TasksEntryCacheModel
	implements CacheModel<TasksEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TasksEntryCacheModel)) {
			return false;
		}

		TasksEntryCacheModel tasksEntryCacheModel =
			(TasksEntryCacheModel)object;

		if (tasksEntryId == tasksEntryCacheModel.tasksEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, tasksEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{tasksEntryId=");
		sb.append(tasksEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", assigneeUserId=");
		sb.append(assigneeUserId);
		sb.append(", resolverUserId=");
		sb.append(resolverUserId);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", finishDate=");
		sb.append(finishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TasksEntry toEntityModel() {
		TasksEntryImpl tasksEntryImpl = new TasksEntryImpl();

		tasksEntryImpl.setTasksEntryId(tasksEntryId);
		tasksEntryImpl.setGroupId(groupId);
		tasksEntryImpl.setCompanyId(companyId);
		tasksEntryImpl.setUserId(userId);

		if (userName == null) {
			tasksEntryImpl.setUserName("");
		}
		else {
			tasksEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			tasksEntryImpl.setCreateDate(null);
		}
		else {
			tasksEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			tasksEntryImpl.setModifiedDate(null);
		}
		else {
			tasksEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			tasksEntryImpl.setTitle("");
		}
		else {
			tasksEntryImpl.setTitle(title);
		}

		tasksEntryImpl.setPriority(priority);
		tasksEntryImpl.setAssigneeUserId(assigneeUserId);
		tasksEntryImpl.setResolverUserId(resolverUserId);

		if (dueDate == Long.MIN_VALUE) {
			tasksEntryImpl.setDueDate(null);
		}
		else {
			tasksEntryImpl.setDueDate(new Date(dueDate));
		}

		if (finishDate == Long.MIN_VALUE) {
			tasksEntryImpl.setFinishDate(null);
		}
		else {
			tasksEntryImpl.setFinishDate(new Date(finishDate));
		}

		tasksEntryImpl.setStatus(status);

		tasksEntryImpl.resetOriginalValues();

		return tasksEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		tasksEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();

		priority = objectInput.readInt();

		assigneeUserId = objectInput.readLong();

		resolverUserId = objectInput.readLong();
		dueDate = objectInput.readLong();
		finishDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(tasksEntryId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeInt(priority);

		objectOutput.writeLong(assigneeUserId);

		objectOutput.writeLong(resolverUserId);
		objectOutput.writeLong(dueDate);
		objectOutput.writeLong(finishDate);

		objectOutput.writeInt(status);
	}

	public long tasksEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public int priority;
	public long assigneeUserId;
	public long resolverUserId;
	public long dueDate;
	public long finishDate;
	public int status;

}