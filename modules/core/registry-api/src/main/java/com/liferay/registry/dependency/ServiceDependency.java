/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.dependency;

import com.liferay.registry.Filter;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;

/**
 * @author Michael C. Han
 */
public class ServiceDependency {

	public ServiceDependency(Class<?> clazz) {
		_serviceDependencyVerifier = new ClassServiceDependencyVerifier(clazz);
	}

	public ServiceDependency(Filter filter) {
		_serviceDependencyVerifier = new FilterServiceDependencyVerifier(
			filter);
	}

	public void close() {
		_serviceTracker.close();

		_serviceTracker = null;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if ((object == null) || (getClass() != object.getClass())) {
			return false;
		}

		ServiceDependency serviceDependency = (ServiceDependency)object;

		return _serviceDependencyVerifier.equals(
			serviceDependency._serviceDependencyVerifier);
	}

	public boolean fulfilled(ServiceReference<?> serviceReference) {
		_fulfilled = _serviceDependencyVerifier.verify(serviceReference);

		return _fulfilled;
	}

	@Override
	public int hashCode() {
		return _serviceDependencyVerifier.hashCode();
	}

	public boolean isFulfilled() {
		return _fulfilled;
	}

	public void open() {
		_serviceTracker.open(true);
	}

	public void setServiceTracker(
		ServiceTracker<Object, Object> serviceTracker) {

		_serviceTracker = serviceTracker;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(5);

		sb.append("{_fulfilled=");
		sb.append(_fulfilled);
		sb.append(", _serviceDependencyVerifier=");
		sb.append(_serviceDependencyVerifier);
		sb.append("}");

		return sb.toString();
	}

	private boolean _fulfilled;
	private final ServiceDependencyVerifier _serviceDependencyVerifier;
	private ServiceTracker<Object, Object> _serviceTracker;

}