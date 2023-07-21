/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.background.task;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = BackgroundTaskExecutorConfigurator.class)
public class BackgroundTaskExecutorConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		BackgroundTaskExecutor layoutExportBackgroundTaskExecutor =
			new LayoutExportBackgroundTaskExecutor();

		registerBackgroundTaskExecutor(
			bundleContext, layoutExportBackgroundTaskExecutor);

		BackgroundTaskExecutor layoutImportBackgroundTaskExecutor =
			new LayoutImportBackgroundTaskExecutor();

		registerBackgroundTaskExecutor(
			bundleContext, layoutImportBackgroundTaskExecutor);

		BackgroundTaskExecutor layoutRemoteStagingBackgroundTaskExecutor =
			new LayoutRemoteStagingBackgroundTaskExecutor();

		registerBackgroundTaskExecutor(
			bundleContext, layoutRemoteStagingBackgroundTaskExecutor);

		BackgroundTaskExecutor layoutSetPrototypeImportBackgroundTaskExcecutor =
			new LayoutSetPrototypeImportBackgroundTaskExcecutor();

		registerBackgroundTaskExecutor(
			bundleContext, layoutSetPrototypeImportBackgroundTaskExcecutor);

		BackgroundTaskExecutor layoutStagingBackgroundTaskExecutor =
			new LayoutStagingBackgroundTaskExecutor();

		registerBackgroundTaskExecutor(
			bundleContext, layoutStagingBackgroundTaskExecutor);

		BackgroundTaskExecutor portletExportBackgroundTaskExecutor =
			new PortletExportBackgroundTaskExecutor();

		registerBackgroundTaskExecutor(
			bundleContext, portletExportBackgroundTaskExecutor);

		BackgroundTaskExecutor portletImportBackgroundTaskExecutor =
			new PortletImportBackgroundTaskExecutor();

		registerBackgroundTaskExecutor(
			bundleContext, portletImportBackgroundTaskExecutor);

		BackgroundTaskExecutor portletRemoteStagingBackgroundTaskExecutor =
			new PortletRemoteStagingBackgroundTaskExecutor();

		registerBackgroundTaskExecutor(
			bundleContext, portletRemoteStagingBackgroundTaskExecutor);

		BackgroundTaskExecutor portletStagingBackgroundTaskExecutor =
			new PortletStagingBackgroundTaskExecutor();

		registerBackgroundTaskExecutor(
			bundleContext, portletStagingBackgroundTaskExecutor);
	}

	@Deactivate
	protected void deactivate() {
		for (ServiceRegistration<BackgroundTaskExecutor> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	protected void registerBackgroundTaskExecutor(
		BundleContext bundleContext,
		BackgroundTaskExecutor backgroundTaskExecutor) {

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		Class<?> clazz = backgroundTaskExecutor.getClass();

		properties.put("background.task.executor.class.name", clazz.getName());

		ServiceRegistration<BackgroundTaskExecutor> serviceRegistration =
			bundleContext.registerService(
				BackgroundTaskExecutor.class, backgroundTaskExecutor,
				properties);

		_serviceRegistrations.add(serviceRegistration);
	}

	private final Set<ServiceRegistration<BackgroundTaskExecutor>>
		_serviceRegistrations = new HashSet<>();

}