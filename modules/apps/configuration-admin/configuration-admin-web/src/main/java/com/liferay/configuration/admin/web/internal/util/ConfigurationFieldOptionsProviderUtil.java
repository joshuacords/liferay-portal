/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.configuration.admin.web.internal.util;

import com.liferay.configuration.admin.definition.ConfigurationFieldOptionsProvider;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = {})
public class ConfigurationFieldOptionsProviderUtil {

	public static ConfigurationFieldOptionsProvider
		getConfigurationFieldOptionsProvider(
			String configurationPid, String fieldName) {

		if (_serviceTrackerMap == null) {
			return null;
		}

		return _serviceTrackerMap.getService(
			_getKey(configurationPid, fieldName));
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap =
			(ServiceTrackerMap<String, ConfigurationFieldOptionsProvider>)
				(ServiceTrackerMap)ServiceTrackerMapFactory.openSingleValueMap(
					bundleContext, ConfigurationFieldOptionsProvider.class,
					null,
					(serviceReference, emitter) -> {
						for (String configurationPid :
								_getPropertyValues(
									serviceReference, "configuration.pid")) {

							for (String fieldName :
									_getPropertyValues(
										serviceReference,
										"configuration.field.name")) {

								emitter.emit(
									_getKey(configurationPid, fieldName));
							}
						}
					});
	}

	@Deactivate
	protected synchronized void deactivate() {
		_serviceTrackerMap.close();
	}

	private static String _getKey(String configurationPid, String fieldName) {
		return StringBundler.concat(
			configurationPid, StringPool.POUND, fieldName);
	}

	private static Collection<String> _getPropertyValues(
		ServiceReference<?> serviceReference, String name) {

		Object propertyValue = serviceReference.getProperty(name);

		if (propertyValue == null) {
			return Collections.emptyList();
		}

		if (propertyValue instanceof Collection) {
			return (Collection<String>)propertyValue;
		}

		if (propertyValue instanceof Object[]) {
			return Arrays.asList((String[])propertyValue);
		}

		return Arrays.asList((String)propertyValue);
	}

	private static volatile ServiceTrackerMap
		<String, ConfigurationFieldOptionsProvider> _serviceTrackerMap;

}