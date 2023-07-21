/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dependency.manager.component.executor.factory.internal;

import com.liferay.portal.kernel.dependency.manager.DependencyManagerSync;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.osgi.framework.ServiceRegistration;

/**
 * @author Shuyang Zhou
 */
public class DependencyManagerSyncImpl implements DependencyManagerSync {

	public DependencyManagerSyncImpl(
		ExecutorService executorService,
		ServiceRegistration<?> componentExecutorFactoryRegistration,
		long syncTimeout) {

		_executorService = executorService;
		_componentExecutorFactoryRegistration =
			componentExecutorFactoryRegistration;
		_syncTimeout = syncTimeout;
	}

	public void setDependencyManagerSyncServiceRegistration(
		ServiceRegistration<?> dependencyManagerSyncServiceRegistration) {

		_dependencyManagerSyncServiceRegistration =
			dependencyManagerSyncServiceRegistration;
	}

	@Override
	public void sync() {
		ServiceRegistration<?> dependencyManagerSyncServiceRegistration =
			_dependencyManagerSyncServiceRegistration;

		if (dependencyManagerSyncServiceRegistration != null) {
			try {
				dependencyManagerSyncServiceRegistration.unregister();
			}
			catch (IllegalStateException illegalStateException) {

				// Concurrent unregister, no need to do anything

			}

			_dependencyManagerSyncServiceRegistration = null;
		}

		try {
			_componentExecutorFactoryRegistration.unregister();
		}
		catch (IllegalStateException illegalStateException) {

			// Concurrent unregister, no need to do anything

		}

		_executorService.shutdown();

		try {
			if (!_executorService.awaitTermination(
					_syncTimeout, TimeUnit.SECONDS)) {

				_executorService.shutdownNow();

				if (_log.isWarnEnabled()) {
					_log.warn(
						"Dependency manager sync timeout after waiting " +
							_syncTimeout + "s");
				}
			}
		}
		catch (InterruptedException interruptedException) {
			_log.error(
				"Dependency manager sync interrupted", interruptedException);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DependencyManagerSyncImpl.class);

	private final ServiceRegistration<?> _componentExecutorFactoryRegistration;
	private volatile ServiceRegistration<?>
		_dependencyManagerSyncServiceRegistration;
	private final ExecutorService _executorService;
	private final long _syncTimeout;

}