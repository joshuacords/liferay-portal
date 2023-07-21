/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Ryan Park
 * @generated
 */
@ProviderType
public interface TasksEntryFinder {

	public int countByG_U_P_A_S_T_N(
		long groupId, long userId, int priority, long assigneeUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds);

	public java.util.List<com.liferay.tasks.model.TasksEntry>
		findByG_U_P_A_S_T_N(
			long groupId, long userId, int priority, long assigneeUserId,
			int status, long[] assetTagIds, long[] notAssetTagIds, int start,
			int end);

}