/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry;

import java.util.SortedMap;

/**
 * @author Raymond Augé
 */
public interface ServiceTracker<S, T> {

	public T addingService(ServiceReference<S> serviceReference);

	public void close();

	@Override
	public boolean equals(Object object);

	public T getService();

	public T getService(ServiceReference<S> serviceReference);

	public ServiceReference<S> getServiceReference();

	public ServiceReference<S>[] getServiceReferences();

	public Object[] getServices();

	public T[] getServices(T[] services);

	public SortedMap<ServiceReference<S>, T> getTrackedServiceReferences();

	public int getUpdateMarker();

	@Override
	public int hashCode();

	public boolean isEmpty();

	public void modifiedService(
		ServiceReference<S> serviceReference, T service);

	public void open();

	public void open(boolean trackAllServices);

	public void remove(ServiceReference<S> serviceReference);

	public void removedService(ServiceReference<S> serviceReference, T service);

	public int size();

	@Override
	public String toString();

	public T waitForService(long timeout) throws InterruptedException;

}