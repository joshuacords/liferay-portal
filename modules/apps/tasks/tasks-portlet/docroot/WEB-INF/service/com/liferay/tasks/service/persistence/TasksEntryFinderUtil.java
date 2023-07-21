/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

/**
 * @author Ryan Park
 * @generated
 */
public class TasksEntryFinderUtil {

	public static int countByG_U_P_A_S_T_N(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds) {

		return getFinder().countByG_U_P_A_S_T_N(
			groupId, userId, priority, assigneeUserId, status, assetTagIds,
			notAssetTagIds);
	}

	public static java.util.List<com.liferay.tasks.model.TasksEntry>
		findByG_U_P_A_S_T_N(
			long groupId, long userId, int priority, long assigneeUserId,
			int status, long[] assetTagIds, long[] notAssetTagIds, int start,
			int end) {

		return getFinder().findByG_U_P_A_S_T_N(
			groupId, userId, priority, assigneeUserId, status, assetTagIds,
			notAssetTagIds, start, end);
	}

	public static TasksEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (TasksEntryFinder)PortletBeanLocatorUtil.locate(
				com.liferay.tasks.service.ServletContextUtil.
					getServletContextName(),
				TasksEntryFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(TasksEntryFinder finder) {
		_finder = finder;
	}

	private static TasksEntryFinder _finder;

}