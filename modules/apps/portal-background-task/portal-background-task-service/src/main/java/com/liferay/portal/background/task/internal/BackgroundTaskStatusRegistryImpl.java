/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.background.task.internal;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistry;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistryUtil;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = BackgroundTaskStatusRegistry.class)
public class BackgroundTaskStatusRegistryImpl
	implements BackgroundTaskStatusRegistry {

	@Override
	public BackgroundTaskStatus getBackgroundTaskStatus(long backgroundTaskId) {
		if (!_clusterMasterExecutor.isMaster()) {
			return getMasterBackgroundTaskStatus(backgroundTaskId);
		}

		Lock lock = _readWriteLock.readLock();

		lock.lock();

		try {
			return _backgroundTaskStatuses.get(backgroundTaskId);
		}
		finally {
			lock.unlock();
		}
	}

	@Override
	public BackgroundTaskStatus registerBackgroundTaskStatus(
		long backgroundTaskId) {

		Lock lock = _readWriteLock.writeLock();

		lock.lock();

		try {
			BackgroundTaskStatus backgroundTaskStatus =
				_backgroundTaskStatuses.get(backgroundTaskId);

			if (backgroundTaskStatus == null) {
				backgroundTaskStatus = new BackgroundTaskStatus();

				_backgroundTaskStatuses.put(
					backgroundTaskId, backgroundTaskStatus);
			}

			return backgroundTaskStatus;
		}
		finally {
			lock.unlock();
		}
	}

	@Override
	public BackgroundTaskStatus unregisterBackgroundTaskStatus(
		long backgroundTaskId) {

		Lock lock = _readWriteLock.writeLock();

		lock.lock();

		try {
			return _backgroundTaskStatuses.remove(backgroundTaskId);
		}
		finally {
			lock.unlock();
		}
	}

	protected BackgroundTaskStatus getMasterBackgroundTaskStatus(
		long backgroundTaskId) {

		try {
			MethodHandler methodHandler = new MethodHandler(
				BackgroundTaskStatusRegistryUtil.class.getDeclaredMethod(
					"getBackgroundTaskStatus", long.class),
				backgroundTaskId);

			Future<BackgroundTaskStatus> future =
				_clusterMasterExecutor.executeOnMaster(methodHandler);

			return future.get();
		}
		catch (Exception exception) {
			_log.error("Unable to retrieve status from master node", exception);
		}

		return null;
	}

	@Reference(unbind = "-")
	protected void setClusterMasterExecutor(
		ClusterMasterExecutor clusterMasterExecutor) {

		_clusterMasterExecutor = clusterMasterExecutor;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BackgroundTaskStatusRegistryImpl.class);

	private final Map<Long, BackgroundTaskStatus> _backgroundTaskStatuses =
		new HashMap<>();
	private ClusterMasterExecutor _clusterMasterExecutor;
	private final ReadWriteLock _readWriteLock = new ReentrantReadWriteLock();

}