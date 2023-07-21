/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.internal;

import com.liferay.registry.Filter;
import com.liferay.registry.ServiceReference;

import java.util.Map;

/**
 * @author Raymond Augé
 */
public class FilterWrapper implements Filter {

	public FilterWrapper(org.osgi.framework.Filter filter) {
		_filter = filter;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FilterWrapper)) {
			throw new IllegalArgumentException();
		}

		FilterWrapper filterWrapper = (FilterWrapper)object;

		return _filter.equals(filterWrapper.getFilter());
	}

	public org.osgi.framework.Filter getFilter() {
		return _filter;
	}

	@Override
	public int hashCode() {
		return _filter.hashCode();
	}

	@Override
	public boolean matches(Map<String, Object> properties) {
		return _filter.match(new MapWrapper(properties));
	}

	@Override
	public boolean matches(ServiceReference<?> serviceReference) {
		if (!(serviceReference instanceof ServiceReferenceWrapper)) {
			throw new IllegalArgumentException();
		}

		ServiceReferenceWrapper<?> serviceReferenceWrapper =
			(ServiceReferenceWrapper<?>)serviceReference;

		return _filter.match(serviceReferenceWrapper.getServiceReference());
	}

	@Override
	public boolean matchesCase(Map<String, Object> properties) {
		return _filter.matchCase(new MapWrapper(properties));
	}

	@Override
	public String toString() {
		return _filter.toString();
	}

	private final org.osgi.framework.Filter _filter;

}