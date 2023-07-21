/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.backgroundtask;

import java.io.Serializable;

import java.util.Map;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), moved to {@link
 *             com.liferay.portal.background.task.internal.ThreadLocalAwareBackgroundTaskExecutor}
 */
@Deprecated
public class ThreadLocalAwareBackgroundTaskExecutor
	extends DelegatingBackgroundTaskExecutor {

	public ThreadLocalAwareBackgroundTaskExecutor(
		BackgroundTaskExecutor backgroundTaskExecutor,
		BackgroundTaskThreadLocalManager backgroundTaskThreadLocalManager) {

		super(backgroundTaskExecutor);

		_backgroundTaskThreadLocalManager = backgroundTaskThreadLocalManager;
	}

	@Override
	public BackgroundTaskExecutor clone() {
		return new ThreadLocalAwareBackgroundTaskExecutor(
			getBackgroundTaskExecutor(), _backgroundTaskThreadLocalManager);
	}

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		Map<String, Serializable> threadLocalValues =
			_backgroundTaskThreadLocalManager.getThreadLocalValues();

		try {
			_backgroundTaskThreadLocalManager.deserializeThreadLocals(
				backgroundTask.getTaskContextMap());

			return super.execute(backgroundTask);
		}
		finally {
			_backgroundTaskThreadLocalManager.setThreadLocalValues(
				threadLocalValues);
		}
	}

	private final BackgroundTaskThreadLocalManager
		_backgroundTaskThreadLocalManager;

}