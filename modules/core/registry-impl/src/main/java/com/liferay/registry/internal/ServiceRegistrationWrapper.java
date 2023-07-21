/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.internal;

import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceRegistration;

import java.util.Map;

/**
 * @author Raymond Augé
 */
public class ServiceRegistrationWrapper<T> implements ServiceRegistration<T> {

	public ServiceRegistrationWrapper(
		org.osgi.framework.ServiceRegistration<T> serviceRegistration) {

		_serviceRegistration = serviceRegistration;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ServiceRegistrationWrapper)) {
			return false;
		}

		ServiceRegistrationWrapper<T> serviceReferenceWrapper =
			(ServiceRegistrationWrapper<T>)object;

		return _serviceRegistration.equals(
			serviceReferenceWrapper.getServiceRegistration());
	}

	@Override
	public ServiceReference<T> getServiceReference() {
		return new ServiceReferenceWrapper<>(
			_serviceRegistration.getReference());
	}

	public org.osgi.framework.ServiceRegistration<T> getServiceRegistration() {
		return _serviceRegistration;
	}

	@Override
	public int hashCode() {
		return _serviceRegistration.hashCode();
	}

	@Override
	public void setProperties(Map<String, Object> properties) {
		_serviceRegistration.setProperties(new MapWrapper(properties));
	}

	@Override
	public String toString() {
		return _serviceRegistration.toString();
	}

	@Override
	public void unregister() {
		_serviceRegistration.unregister();
	}

	private final org.osgi.framework.ServiceRegistration<T>
		_serviceRegistration;

}