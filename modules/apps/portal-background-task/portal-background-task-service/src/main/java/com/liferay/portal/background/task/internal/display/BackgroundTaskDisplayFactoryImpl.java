/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.background.task.internal.display;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutorRegistry;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManager;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplayFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrew Betts
 */
@Component(immediate = true, service = BackgroundTaskDisplayFactory.class)
public class BackgroundTaskDisplayFactoryImpl
	implements BackgroundTaskDisplayFactory {

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(
		BackgroundTask backgroundTask) {

		if (backgroundTask == null) {
			return null;
		}

		BackgroundTaskExecutor backgroundTaskExecutor =
			_backgroundTaskExecutorRegistry.getBackgroundTaskExecutor(
				backgroundTask.getTaskExecutorClassName());

		return backgroundTaskExecutor.getBackgroundTaskDisplay(backgroundTask);
	}

	@Override
	public BackgroundTaskDisplay getBackgroundTaskDisplay(
		long backgroundTaskId) {

		BackgroundTask backgroundTask =
			_backgroundTaskManager.fetchBackgroundTask(backgroundTaskId);

		return getBackgroundTaskDisplay(backgroundTask);
	}

	@Reference(unbind = "-")
	protected void setBackgroundTaskExecutorRegistry(
		BackgroundTaskExecutorRegistry backgroundTaskExecutorRegistry) {

		_backgroundTaskExecutorRegistry = backgroundTaskExecutorRegistry;
	}

	@Reference(unbind = "-")
	protected void setBackgroundTaskManager(
		BackgroundTaskManager backgroundTaskManager) {

		_backgroundTaskManager = backgroundTaskManager;
	}

	private BackgroundTaskExecutorRegistry _backgroundTaskExecutorRegistry;
	private BackgroundTaskManager _backgroundTaskManager;

}