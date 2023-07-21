/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.dependency;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;

/**
 * @author Michael C. Han
 */
public class ClassServiceDependencyVerifier
	implements ServiceDependencyVerifier {

	public ClassServiceDependencyVerifier(Class<?> clazz) {
		_clazz = clazz;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if ((object == null) || (getClass() != object.getClass())) {
			return false;
		}

		return _clazz.equals(object);
	}

	@Override
	public int hashCode() {
		return _clazz.hashCode();
	}

	@Override
	public String toString() {
		return _clazz.getName();
	}

	@Override
	public boolean verify(ServiceReference<?> serviceReference) {
		Registry registry = RegistryUtil.getRegistry();

		Object service = registry.getService(serviceReference);

		Class<?> serviceClass = service.getClass();

		if (serviceClass.equals(_clazz)) {
			return true;
		}

		Class<?>[] interfaceClasses = serviceClass.getInterfaces();

		for (Class<?> interfaceClass : interfaceClasses) {
			if (interfaceClass.equals(_clazz)) {
				return true;
			}
		}

		return false;
	}

	private final Class<?> _clazz;

}