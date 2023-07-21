/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.internal.security.permission.resource;

import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.definition.PortletResourcePermissionDefinition;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceRegistration;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.util.Map;

/**
 * @author Preston Crary
 */
public class PortletResourcePermissionDefinitionTracker {

	public void afterPropertiesSet() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			PortletResourcePermissionDefinition.class,
			new PortletResourcePermissionDefinitionServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	public void destroy() {
		_serviceTracker.close();
	}

	private ServiceTracker
		<PortletResourcePermissionDefinition, ServiceRegistration<?>>
			_serviceTracker;

	private static class
		PortletResourcePermissionDefinitionServiceTrackerCustomizer
			implements ServiceTrackerCustomizer
				<PortletResourcePermissionDefinition, ServiceRegistration<?>> {

		@Override
		public ServiceRegistration<?> addingService(
			ServiceReference<PortletResourcePermissionDefinition>
				serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			PortletResourcePermissionDefinition
				portletResourcePermissionDefinition = registry.getService(
					serviceReference);

			PortletResourcePermission portletResourcePermission =
				new DefaultPortletResourcePermission(
					portletResourcePermissionDefinition.getResourceName(),
					portletResourcePermissionDefinition.
						getPortletResourcePermissionLogics());

			Map<String, Object> properties = HashMapBuilder.<String, Object>put(
				"resource.name",
				portletResourcePermissionDefinition.getResourceName()
			).build();

			Object serviceRanking = serviceReference.getProperty(
				"service.ranking");

			if (serviceRanking != null) {
				properties.put("service.ranking", serviceRanking);
			}

			return registry.registerService(
				PortletResourcePermission.class, portletResourcePermission,
				properties);
		}

		@Override
		public void modifiedService(
			ServiceReference<PortletResourcePermissionDefinition>
				serviceReference,
			ServiceRegistration<?> serviceRegistration) {
		}

		@Override
		public void removedService(
			ServiceReference<PortletResourcePermissionDefinition>
				serviceReference,
			ServiceRegistration<?> serviceRegistration) {

			serviceRegistration.unregister();

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);
		}

	}

}