/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.display.context;

import com.liferay.registry.ServiceReference;

import java.util.Objects;

/**
 * @author Iván Zaera
 */
public class DisplayContextFactoryReference<T extends DisplayContextFactory>
	implements Comparable<DisplayContextFactoryReference<?>> {

	public DisplayContextFactoryReference(
		T displayContextFactory, ServiceReference<T> serviceReference) {

		_displayContextFactory = displayContextFactory;
		_serviceReference = serviceReference;
	}

	@Override
	public int compareTo(
		DisplayContextFactoryReference<?> displayContextFactoryReference) {

		return _serviceReference.compareTo(
			displayContextFactoryReference._serviceReference);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DisplayContextFactoryReference)) {
			return false;
		}

		DisplayContextFactoryReference<?> displayContextFactoryReference =
			(DisplayContextFactoryReference<?>)obj;

		if (Objects.equals(
				_serviceReference,
				displayContextFactoryReference._serviceReference)) {

			return true;
		}

		return false;
	}

	public T getDisplayContextFactory() {
		return _displayContextFactory;
	}

	public ServiceReference<T> getServiceReference() {
		return _serviceReference;
	}

	@Override
	public int hashCode() {
		return _serviceReference.hashCode();
	}

	private final T _displayContextFactory;
	private final ServiceReference<T> _serviceReference;

}