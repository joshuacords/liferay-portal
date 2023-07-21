/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.dependency;

import com.liferay.registry.Filter;
import com.liferay.registry.ServiceReference;

/**
 * @author Michael C. Han
 */
public class FilterServiceDependencyVerifier
	implements ServiceDependencyVerifier {

	public FilterServiceDependencyVerifier(Filter filter) {
		_filter = filter;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if ((object == null) || (getClass() != object.getClass())) {
			return false;
		}

		return _filter.equals(object);
	}

	@Override
	public int hashCode() {
		return _filter.hashCode();
	}

	@Override
	public String toString() {
		return _filter.toString();
	}

	@Override
	public boolean verify(ServiceReference<?> serviceReference) {
		return _filter.matches(serviceReference);
	}

	private final Filter _filter;

}