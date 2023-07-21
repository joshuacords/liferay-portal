/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osgi.service.tracker.collections.map;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * @author Carlos Sierra Andrés
 */
public final class ServiceReferenceMapperFactory {

	public static <K, S> ServiceReferenceMapper<K, S> create(
		final BundleContext bundleContext,
		final ServiceMapper<K, S> serviceMapper) {

		return new ServiceReferenceMapper<K, S>() {

			@Override
			public void map(
				ServiceReference<S> serviceReference, Emitter<K> emitter) {

				S service = bundleContext.getService(serviceReference);

				try {
					serviceMapper.map(service, emitter);
				}
				finally {
					bundleContext.ungetService(serviceReference);
				}
			}

		};
	}

	public static <K, S> Function<BundleContext, ServiceReferenceMapper<K, S>>
		createFromFunction(BiFunction<ServiceReference<S>, S, K> function) {

		return b -> (serviceReference, emitter) -> {
			S service = b.getService(serviceReference);

			try {
				emitter.emit(function.apply(serviceReference, service));
			}
			catch (Exception exception) {
				b.ungetService(serviceReference);
			}
		};
	}

}