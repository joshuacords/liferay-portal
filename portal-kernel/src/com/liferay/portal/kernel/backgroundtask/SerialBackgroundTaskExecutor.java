/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.backgroundtask;

import com.liferay.portal.kernel.lock.DuplicateLockException;
import com.liferay.portal.kernel.lock.Lock;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), moved to {@link
 *             com.liferay.portal.background.task.internal.SerialBackgroundTaskExecutor}
 */
@Deprecated
public class SerialBackgroundTaskExecutor
	extends DelegatingBackgroundTaskExecutor {

	public SerialBackgroundTaskExecutor(
		BackgroundTaskExecutor backgroundTaskExecutor) {

		super(backgroundTaskExecutor);
	}

	@Override
	public BackgroundTaskExecutor clone() {
		return new SerialBackgroundTaskExecutor(getBackgroundTaskExecutor());
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		Lock lock = null;

		try {
			if (isSerial()) {
				lock = acquireLock(backgroundTask);
			}

			BackgroundTaskExecutor backgroundTaskExecutor =
				getBackgroundTaskExecutor();

			return backgroundTaskExecutor.execute(backgroundTask);
		}
		finally {
			if (lock != null) {
				BackgroundTaskLockHelperUtil.unlockBackgroundTask(
					backgroundTask);
			}
		}
	}

	protected Lock acquireLock(BackgroundTask backgroundTask)
		throws DuplicateLockException {

		Lock lock = BackgroundTaskLockHelperUtil.lockBackgroundTask(
			backgroundTask);

		if (!lock.isNew()) {
			throw new DuplicateLockException(lock);
		}

		return lock;
	}

}