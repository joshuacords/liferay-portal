/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael C. Han
 */
public class ServiceRegistrar<T> {

	public ServiceRegistrar(Registry registry, Class<T> clazz) {
		_registry = registry;
	}

	public void destroy() {
		destroy(null);
	}

	public void destroy(ServiceFinalizer<T> serviceFinalizer) {
		_destroyed = true;

		for (ServiceRegistration<T> serviceRegistration :
				_serviceRegistrations) {

			if (serviceFinalizer != null) {
				ServiceReference<T> serviceReference =
					serviceRegistration.getServiceReference();

				serviceFinalizer.finalize(
					serviceReference, _registry.getService(serviceReference));
			}

			serviceRegistration.unregister();
		}

		_serviceRegistrations.clear();
	}

	public Collection<ServiceRegistration<T>> getServiceRegistrations() {
		if (_destroyed) {
			return Collections.emptySet();
		}

		return Collections.unmodifiableCollection(_serviceRegistrations);
	}

	public boolean isDestroyed() {
		return _destroyed;
	}

	public ServiceRegistration<T> registerService(Class<T> clazz, T service) {
		if (_destroyed) {
			return null;
		}

		ServiceRegistration<T> serviceRegistration = _registry.registerService(
			clazz, service);

		_serviceRegistrations.add(serviceRegistration);

		return serviceRegistration;
	}

	public ServiceRegistration<T> registerService(
		Class<T> clazz, T service, Map<String, Object> properties) {

		if (_destroyed) {
			return null;
		}

		ServiceRegistration<T> serviceRegistration = _registry.registerService(
			clazz, service, properties);

		_serviceRegistrations.add(serviceRegistration);

		return serviceRegistration;
	}

	public ServiceRegistration<T> registerService(String className, T service) {
		ServiceRegistration<T> serviceRegistration = _registry.registerService(
			className, service);

		if (_destroyed) {
			return null;
		}

		_serviceRegistrations.add(serviceRegistration);

		return serviceRegistration;
	}

	public ServiceRegistration<T> registerService(
		String className, T service, Map<String, Object> properties) {

		if (_destroyed) {
			return null;
		}

		ServiceRegistration<T> serviceRegistration = _registry.registerService(
			className, service, properties);

		_serviceRegistrations.add(serviceRegistration);

		return serviceRegistration;
	}

	public ServiceRegistration<T> registerService(
		String[] classNames, T service) {

		if (_destroyed) {
			return null;
		}

		ServiceRegistration<T> serviceRegistration = _registry.registerService(
			classNames, service);

		_serviceRegistrations.add(serviceRegistration);

		return serviceRegistration;
	}

	public ServiceRegistration<T> registerService(
		String[] classNames, T service, Map<String, Object> properties) {

		if (_destroyed) {
			return null;
		}

		ServiceRegistration<T> serviceRegistration = _registry.registerService(
			classNames, service, properties);

		_serviceRegistrations.add(serviceRegistration);

		return serviceRegistration;
	}

	private volatile boolean _destroyed;
	private final Registry _registry;
	private final Set<ServiceRegistration<T>> _serviceRegistrations =
		Collections.newSetFromMap(new ConcurrentHashMap<>());

}