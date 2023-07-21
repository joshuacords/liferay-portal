/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dependency.manager.component.executor.factory.internal;

import com.liferay.portal.kernel.dependency.manager.DependencyManagerSync;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.NamedThreadFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.felix.dm.ComponentExecutorFactory;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Shuyang Zhou
 */
public class ComponentExecutorFactoryBundleActivator
	implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) {
		boolean threadPoolEnabled = GetterUtil.getBoolean(
			bundleContext.getProperty("dependency.manager.thread.pool.enabled"),
			true);

		if (!threadPoolEnabled) {
			return;
		}

		long syncTimeout = GetterUtil.getInteger(
			bundleContext.getProperty("dependency.manager.sync.timeout"), 60);

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			0, 1, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(),
			new NamedThreadFactory(
				"Portal Dependency Manager Component Executor-",
				Thread.NORM_PRIORITY,
				ComponentExecutorFactory.class.getClassLoader()));

		threadPoolExecutor.allowCoreThreadTimeOut(true);

		_serviceRegistration = bundleContext.registerService(
			ComponentExecutorFactory.class,
			new ComponentExecutorFactoryImpl(threadPoolExecutor), null);

		DependencyManagerSyncImpl dependencyManagerSyncImpl =
			new DependencyManagerSyncImpl(
				threadPoolExecutor, _serviceRegistration, syncTimeout);

		_dependencyManagerSyncServiceRegistration =
			bundleContext.registerService(
				DependencyManagerSync.class, dependencyManagerSyncImpl, null);

		dependencyManagerSyncImpl.setDependencyManagerSyncServiceRegistration(
			_dependencyManagerSyncServiceRegistration);
	}

	@Override
	public void stop(BundleContext bundleContext) {
		if (_serviceRegistration == null) {
			return;
		}

		try {
			_serviceRegistration.unregister();
		}
		catch (IllegalStateException illegalStateException) {

			// Concurrent unregister, no need to do anything.

		}

		try {
			_dependencyManagerSyncServiceRegistration.unregister();
		}
		catch (IllegalStateException illegalStateException) {

			// Concurrent unregister, no need to do anything.

		}
	}

	private ServiceRegistration<DependencyManagerSync>
		_dependencyManagerSyncServiceRegistration;
	private ServiceRegistration<ComponentExecutorFactory> _serviceRegistration;

}