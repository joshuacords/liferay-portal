/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.background.task.internal;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutorRegistry;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = BackgroundTaskExecutorRegistry.class)
public class BackgroundTaskExecutorRegistryImpl
	implements BackgroundTaskExecutorRegistry {

	@Override
	public synchronized BackgroundTaskExecutor getBackgroundTaskExecutor(
		String backgroundTaskExecutorClassName) {

		return _backgroundTaskExecutors.getService(
			backgroundTaskExecutorClassName);
	}

	@Override
	public synchronized void registerBackgroundTaskExecutor(
		String backgroundTaskExecutorClassName,
		BackgroundTaskExecutor backgroundTaskExecutor) {

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			"background.task.executor.class.name",
			backgroundTaskExecutorClassName);

		ServiceRegistration<BackgroundTaskExecutor> serviceRegistration =
			_bundleContext.registerService(
				BackgroundTaskExecutor.class, backgroundTaskExecutor,
				properties);

		_backgroundTaskExecutorRegistrations.put(
			backgroundTaskExecutorClassName, serviceRegistration);
	}

	@Override
	public synchronized void unregisterBackgroundTaskExecutor(
		String backgroundTaskExecutorClassName) {

		if (!_backgroundTaskExecutorRegistrations.containsKey(
				backgroundTaskExecutorClassName)) {

			return;
		}

		ServiceRegistration<BackgroundTaskExecutor> serviceRegistration =
			_backgroundTaskExecutorRegistrations.get(
				backgroundTaskExecutorClassName);

		serviceRegistration.unregister();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_backgroundTaskExecutors = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, BackgroundTaskExecutor.class,
			"background.task.executor.class.name");
	}

	@Deactivate
	protected synchronized void deactivate() {
		_backgroundTaskExecutors.close();

		_bundleContext = null;

		for (ServiceRegistration<BackgroundTaskExecutor> serviceRegistration :
				_backgroundTaskExecutorRegistrations.values()) {

			serviceRegistration.unregister();
		}

		_backgroundTaskExecutorRegistrations.clear();
	}

	private final Map<String, ServiceRegistration<BackgroundTaskExecutor>>
		_backgroundTaskExecutorRegistrations = new HashMap<>();
	private ServiceTrackerMap<String, BackgroundTaskExecutor>
		_backgroundTaskExecutors;
	private BundleContext _bundleContext;

}