/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.internal;

import com.liferay.registry.ServiceReference;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class ServiceReferenceWrapper<T> implements ServiceReference<T> {

	public ServiceReferenceWrapper(
		org.osgi.framework.ServiceReference<T> serviceReference) {

		_serviceReference = serviceReference;
	}

	@Override
	public int compareTo(Object object) {
		if (this == object) {
			return 0;
		}

		if (!(object instanceof ServiceReferenceWrapper)) {
			throw new IllegalArgumentException();
		}

		ServiceReferenceWrapper<T> serviceReferenceWrapper =
			(ServiceReferenceWrapper<T>)object;

		return _serviceReference.compareTo(
			serviceReferenceWrapper.getServiceReference());
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ServiceReferenceWrapper)) {
			return false;
		}

		ServiceReferenceWrapper<T> serviceReferenceWrapper =
			(ServiceReferenceWrapper<T>)object;

		return _serviceReference.equals(
			serviceReferenceWrapper.getServiceReference());
	}

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();

		for (String key : getPropertyKeys()) {
			Object value = getProperty(key);

			properties.put(key, value);
		}

		return properties;
	}

	@Override
	public Object getProperty(String key) {
		return _serviceReference.getProperty(key);
	}

	@Override
	public String[] getPropertyKeys() {
		return _serviceReference.getPropertyKeys();
	}

	public org.osgi.framework.ServiceReference<T> getServiceReference() {
		return _serviceReference;
	}

	@Override
	public int hashCode() {
		return _serviceReference.hashCode();
	}

	@Override
	public String toString() {
		return _serviceReference.toString();
	}

	private final org.osgi.framework.ServiceReference<T> _serviceReference;

}