/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.backgroundtask;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.lock.LockManagerUtil;

/**
 * @author     Daniel Kocsis
 * @deprecated As of Judson (7.1.x), moved to {@link
 *             com.liferay.portal.background.task.internal.lock.BackgroundTaskLockHelper}
 */
@Deprecated
public class BackgroundTaskLockHelperUtil {

	public static boolean isLockedBackgroundTask(
		BackgroundTask backgroundTask) {

		return LockManagerUtil.isLocked(
			BackgroundTaskExecutor.class.getName(), getLockKey(backgroundTask));
	}

	public static Lock lockBackgroundTask(BackgroundTask backgroundTask) {
		String owner =
			backgroundTask.getName() + StringPool.POUND +
				backgroundTask.getBackgroundTaskId();

		return LockManagerUtil.lock(
			BackgroundTaskExecutor.class.getName(), getLockKey(backgroundTask),
			owner);
	}

	public static void unlockBackgroundTask(BackgroundTask backgroundTask) {
		String owner =
			backgroundTask.getName() + StringPool.POUND +
				backgroundTask.getBackgroundTaskId();

		LockManagerUtil.unlock(
			BackgroundTaskExecutor.class.getName(), getLockKey(backgroundTask),
			owner);
	}

	protected static String getLockKey(BackgroundTask backgroundTask) {
		BackgroundTaskExecutor backgroundTaskExecutor =
			BackgroundTaskExecutorRegistryUtil.getBackgroundTaskExecutor(
				backgroundTask.getTaskExecutorClassName());

		String lockKey = StringPool.BLANK;

		if (backgroundTaskExecutor.getIsolationLevel() ==
				BackgroundTaskConstants.ISOLATION_LEVEL_CLASS) {

			lockKey = backgroundTask.getTaskExecutorClassName();
		}
		else if (backgroundTaskExecutor.getIsolationLevel() ==
					BackgroundTaskConstants.ISOLATION_LEVEL_COMPANY) {

			lockKey =
				backgroundTask.getTaskExecutorClassName() + StringPool.POUND +
					backgroundTask.getCompanyId();
		}
		else if (backgroundTaskExecutor.getIsolationLevel() ==
					BackgroundTaskConstants.ISOLATION_LEVEL_CUSTOM) {

			lockKey = backgroundTaskExecutor.generateLockKey(backgroundTask);
		}
		else if (backgroundTaskExecutor.getIsolationLevel() ==
					BackgroundTaskConstants.ISOLATION_LEVEL_GROUP) {

			lockKey =
				backgroundTask.getTaskExecutorClassName() + StringPool.POUND +
					backgroundTask.getGroupId();
		}
		else if (backgroundTaskExecutor.getIsolationLevel() ==
					BackgroundTaskConstants.ISOLATION_LEVEL_TASK_NAME) {

			lockKey =
				backgroundTask.getTaskExecutorClassName() + StringPool.POUND +
					backgroundTask.getName();
		}
		else {
			lockKey =
				backgroundTask.getTaskExecutorClassName() + StringPool.POUND +
					backgroundTaskExecutor.getIsolationLevel();
		}

		return lockKey;
	}

}