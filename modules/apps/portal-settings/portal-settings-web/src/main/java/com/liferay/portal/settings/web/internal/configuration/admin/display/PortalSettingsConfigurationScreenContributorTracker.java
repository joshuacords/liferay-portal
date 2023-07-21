/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.settings.web.internal.configuration.admin.display;

import com.liferay.configuration.admin.display.ConfigurationScreen;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.settings.configuration.admin.display.PortalSettingsConfigurationScreenContributor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Drew Brokke
 */
@Component(service = {})
public class PortalSettingsConfigurationScreenContributorTracker {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_serviceTracker = ServiceTrackerFactory.open(
			bundleContext, PortalSettingsConfigurationScreenContributor.class,
			new ServiceTrackerCustomizer
				<PortalSettingsConfigurationScreenContributor,
				 ConfigurationScreen>() {

				@Override
				public ConfigurationScreen addingService(
					ServiceReference
						<PortalSettingsConfigurationScreenContributor>
							serviceReference) {

					return _registerConfigurationScreen(
						_bundleContext.getService(serviceReference));
				}

				@Override
				public void modifiedService(
					ServiceReference
						<PortalSettingsConfigurationScreenContributor>
							serviceReference,
					ConfigurationScreen configurationScreen) {
				}

				@Override
				public void removedService(
					ServiceReference
						<PortalSettingsConfigurationScreenContributor>
							serviceReference,
					ConfigurationScreen configurationScreen) {

					_unregisterConfigurationScreen(
						_bundleContext.getService(serviceReference));
				}

			});
	}

	@Deactivate
	protected void deactivate() {
		_serviceTracker.close();
	}

	private ConfigurationScreen _registerConfigurationScreen(
		PortalSettingsConfigurationScreenContributor
			portalSettingsConfigurationScreenContributor) {

		PortalSettingsConfigurationScreen configurationScreen =
			new PortalSettingsConfigurationScreen(
				portalSettingsConfigurationScreenContributor, _servletContext);

		_serviceRegistrationMap.put(
			portalSettingsConfigurationScreenContributor.getKey(),
			_bundleContext.registerService(
				ConfigurationScreen.class, configurationScreen,
				new HashMapDictionary<>()));

		return configurationScreen;
	}

	private void _unregisterConfigurationScreen(
		PortalSettingsConfigurationScreenContributor
			portalSettingsConfigurationScreenContributor) {

		ServiceRegistration<ConfigurationScreen> serviceRegistration =
			_serviceRegistrationMap.remove(
				portalSettingsConfigurationScreenContributor.getKey());

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	private BundleContext _bundleContext;
	private final Map<String, ServiceRegistration<ConfigurationScreen>>
		_serviceRegistrationMap = new ConcurrentHashMap<>();
	private ServiceTracker
		<PortalSettingsConfigurationScreenContributor, ConfigurationScreen>
			_serviceTracker;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.portal.settings.web)"
	)
	private ServletContext _servletContext;

}