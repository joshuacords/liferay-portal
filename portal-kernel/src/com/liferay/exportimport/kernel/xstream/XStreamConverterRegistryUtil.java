/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.kernel.xstream;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceRegistration;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.registry.collections.ServiceRegistrationMap;
import com.liferay.registry.collections.ServiceRegistrationMapImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Daniel Kocsis
 */
public class XStreamConverterRegistryUtil {

	public static Set<XStreamConverter> getXStreamConverters() {
		return _xStreamConverterRegistryUtil._getXStreamConverters();
	}

	public static void register(XStreamConverter xStreamConverter) {
		_xStreamConverterRegistryUtil._register(xStreamConverter);
	}

	public static void unregister(XStreamConverter xStreamConverter) {
		_xStreamConverterRegistryUtil._unregister(xStreamConverter);
	}

	private XStreamConverterRegistryUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			XStreamConverter.class,
			new XStreamConverterServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	private Set<XStreamConverter> _getXStreamConverters() {
		return _xStreamConverters;
	}

	private void _register(XStreamConverter xStreamConverter) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<XStreamConverter> serviceRegistration =
			registry.registerService(XStreamConverter.class, xStreamConverter);

		_serviceRegistrations.put(xStreamConverter, serviceRegistration);
	}

	private void _unregister(XStreamConverter xStreamConverter) {
		ServiceRegistration<XStreamConverter> serviceRegistration =
			_serviceRegistrations.remove(xStreamConverter);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	private static final XStreamConverterRegistryUtil
		_xStreamConverterRegistryUtil = new XStreamConverterRegistryUtil();

	private final ServiceRegistrationMap<XStreamConverter>
		_serviceRegistrations = new ServiceRegistrationMapImpl<>();
	private final ServiceTracker<XStreamConverter, XStreamConverter>
		_serviceTracker;
	private final Set<XStreamConverter> _xStreamConverters = new HashSet<>();

	private class XStreamConverterServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<XStreamConverter, XStreamConverter> {

		@Override
		public XStreamConverter addingService(
			ServiceReference<XStreamConverter> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			XStreamConverter xStreamConverter = registry.getService(
				serviceReference);

			_xStreamConverters.add(xStreamConverter);

			return xStreamConverter;
		}

		@Override
		public void modifiedService(
			ServiceReference<XStreamConverter> serviceReference,
			XStreamConverter xStreamConverter) {
		}

		@Override
		public void removedService(
			ServiceReference<XStreamConverter> serviceReference,
			XStreamConverter xStreamConverter) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_xStreamConverters.remove(xStreamConverter);
		}

	}

}