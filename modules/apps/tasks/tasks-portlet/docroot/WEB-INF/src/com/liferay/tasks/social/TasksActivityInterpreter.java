/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.social;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.social.kernel.model.BaseSocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;
import com.liferay.tasks.service.permission.TasksEntryPermission;

/**
 * @author Ryan Park
 */
public class TasksActivityInterpreter extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(
			activity.getClassPK());

		return getJSONValue(
			activity.getExtraData(), "title", tasksEntry.getTitle());
	}

	@Override
	protected String getLink(
		SocialActivity activity, ServiceContext serviceContext) {

		return StringPool.BLANK;
	}

	@Override
	protected ResourceBundleLoader getResourceBundleLoader() {
		return _resourceBundleLoader;
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		long userId = activity.getUserId();
		long receiverUserId = activity.getReceiverUserId();

		int activityType = activity.getType();

		if (activityType == TasksActivityKeys.RESOLVE_ENTRY) {
			TasksEntry tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(
				activity.getClassPK());

			userId = tasksEntry.getResolverUserId();
			receiverUserId = tasksEntry.getUserId();
		}

		String creatorUserName = getUserName(userId, serviceContext);
		String receiverUserName = getUserName(receiverUserId, serviceContext);

		return new Object[] {creatorUserName, receiverUserName};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		long userId = activity.getUserId();
		long receiverUserId = activity.getReceiverUserId();

		int activityType = activity.getType();

		if (activityType == TasksActivityKeys.ADD_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-tasks-add-entry-for";
			}

			return "activity-tasks-add-entry";
		}
		else if (activityType == TasksActivityKeys.REOPEN_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-tasks-reopen-entry-for";
			}

			return "activity-tasks-reopen-entry";
		}
		else if (activityType == TasksActivityKeys.RESOLVE_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-tasks-resolve-entry-for";
			}

			return "activity-tasks-resolve-entry";
		}
		else if (activityType == TasksActivityKeys.UPDATE_ENTRY) {
			if ((userId != receiverUserId) && (receiverUserId != 0)) {
				return "activity-tasks-update-entry-for";
			}

			return "activity-tasks-update-entry";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.fetchTasksEntry(
			activity.getClassPK());

		if (tasksEntry == null) {
			return false;
		}

		return TasksEntryPermission.contains(
			permissionChecker, tasksEntry, ActionKeys.VIEW);
	}

	private static final String[] _CLASS_NAMES = {TasksEntry.class.getName()};

	private final ResourceBundleLoader _resourceBundleLoader =
		new ClassResourceBundleLoader(
			"content.Language", TasksActivityInterpreter.class);

}