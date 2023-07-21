/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.collections;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;

/**
 * @author Carlos Sierra Andrés
 */
public class ServiceReferenceMapperFactory {

	public static <K, S> ServiceReferenceMapper<K, S> create(
		final ServiceMapper<K, S> serviceMapper) {

		return new ServiceReferenceMapper<K, S>() {

			@Override
			public void map(
				ServiceReference<S> serviceReference, Emitter<K> emitter) {

				Registry registry = RegistryUtil.getRegistry();

				S service = registry.getService(serviceReference);

				try {
					serviceMapper.map(service, emitter);
				}
				finally {
					registry.ungetService(serviceReference);
				}
			}

		};
	}

	public static <K, S> ServiceReferenceMapper<K, S> create(
		final String propertyKey) {

		return new ServiceReferenceMapper<K, S>() {

			@Override
			public void map(
				ServiceReference<S> serviceReference, Emitter<K> emitter) {

				Object propertyValue = serviceReference.getProperty(
					propertyKey);

				if (propertyValue == null) {
					return;
				}

				if (propertyValue instanceof Object[]) {
					for (K k : (K[])propertyValue) {
						emitter.emit(k);
					}
				}
				else {
					emitter.emit((K)propertyValue);
				}
			}

		};
	}

}