/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.display.context;

import com.liferay.document.library.display.context.DLDisplayContextFactory;

import java.util.Objects;

import org.osgi.framework.ServiceReference;

/**
 * @author Iván Zaera
 */
public class DLDisplayContextFactoryReference
	implements Comparable<DLDisplayContextFactoryReference> {

	public DLDisplayContextFactoryReference(
		DLDisplayContextFactory dlDisplayContextFactory,
		ServiceReference<DLDisplayContextFactory> serviceReference) {

		_dlDisplayContextFactory = dlDisplayContextFactory;
		_serviceReference = serviceReference;
	}

	@Override
	public int compareTo(DLDisplayContextFactoryReference that) {
		return _serviceReference.compareTo(that._serviceReference);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DLDisplayContextFactoryReference)) {
			return false;
		}

		DLDisplayContextFactoryReference dlDisplayContextFactoryReference =
			(DLDisplayContextFactoryReference)obj;

		if (Objects.equals(
				_serviceReference,
				dlDisplayContextFactoryReference._serviceReference)) {

			return true;
		}

		return false;
	}

	public DLDisplayContextFactory getDLDisplayContextFactory() {
		return _dlDisplayContextFactory;
	}

	public ServiceReference<DLDisplayContextFactory> getServiceReference() {
		return _serviceReference;
	}

	@Override
	public int hashCode() {
		return _serviceReference.hashCode();
	}

	private final DLDisplayContextFactory _dlDisplayContextFactory;
	private final ServiceReference<DLDisplayContextFactory> _serviceReference;

}