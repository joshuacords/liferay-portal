/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.background.task.internal.security.permission.resource;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.service.BackgroundTaskLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(immediate = true, service = {})
public class BackgroundTaskModelResourcePermissionRegistrar {

	@Activate
	public void activate(BundleContext bundleContext) {
		_backgroundTaskModelResourcePermissionLogics =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, ModelResourcePermissionLogic.class,
				"background.task.executor.class.name");

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("model.class.name", BackgroundTask.class.getName());

		_serviceRegistration = bundleContext.registerService(
			ModelResourcePermission.class,
			ModelResourcePermissionFactory.create(
				BackgroundTask.class, BackgroundTask::getBackgroundTaskId,
				_backgroundTaskLocalService::getBackgroundTask, null,
				(modelResourcePermission, consumer) -> consumer.accept(
					new BackgroundTaskModelResourcePermissionLogic())),
			properties);
	}

	@Deactivate
	public void deactivate() {
		_serviceRegistration.unregister();

		_backgroundTaskModelResourcePermissionLogics.close();
	}

	@Reference
	private BackgroundTaskLocalService _backgroundTaskLocalService;

	private ServiceTrackerMap<String, ModelResourcePermissionLogic>
		_backgroundTaskModelResourcePermissionLogics;
	private ServiceRegistration<ModelResourcePermission> _serviceRegistration;

	private class BackgroundTaskModelResourcePermissionLogic
		implements ModelResourcePermissionLogic<BackgroundTask> {

		@Override
		public Boolean contains(
				PermissionChecker permissionChecker, String name,
				BackgroundTask backgroundTask, String actionId)
			throws PortalException {

			ModelResourcePermissionLogic<BackgroundTask>
				backgroundTaskModelResourcePermissionLogic =
					_backgroundTaskModelResourcePermissionLogics.getService(
						backgroundTask.getTaskExecutorClassName());

			if (backgroundTaskModelResourcePermissionLogic == null) {
				return null;
			}

			return backgroundTaskModelResourcePermissionLogic.contains(
				permissionChecker, name, backgroundTask, actionId);
		}

	}

}