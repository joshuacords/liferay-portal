/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.asset.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.service.TasksEntryLocalServiceUtil;
import com.liferay.tasks.service.permission.TasksEntryPermission;

/**
 * @author Matthew Kong
 */
public class TasksEntryAssetRendererFactory
	extends BaseAssetRendererFactory<TasksEntry> {

	public static final String CLASS_NAME = TasksEntry.class.getName();

	public static final String TYPE = "tasks";

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException {

		return new TasksEntryAssetRenderer(
			TasksEntryLocalServiceUtil.getTasksEntry(classPK));
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getIconCssClass() {
		return "icon-tasks";
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return TasksEntryPermission.contains(
			permissionChecker, classPK, actionId);
	}

}