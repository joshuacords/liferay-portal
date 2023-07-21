/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.upgrade.v1_0_0;

import com.liferay.document.library.kernel.store.Store;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Sergio González
 */
@Component(immediate = true, service = {})
public class StoreProvider {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_serviceTracker = ServiceTrackerFactory.open(
			bundleContext, Store.class, new StoreServiceTrackerCustomizer());
	}

	@Deactivate
	protected void deactivate() {
		_serviceTracker.close();
	}

	private BundleContext _bundleContext;

	@Reference
	private Props _props;

	private ServiceRegistration<Store> _serviceRegistration;
	private ServiceTracker<Store, Store> _serviceTracker;

	private class StoreServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<Store, Store> {

		@Override
		public Store addingService(ServiceReference<Store> serviceReference) {
			String storeType = (String)serviceReference.getProperty(
				"store.type");

			if ((storeType == null) ||
				!storeType.equals(_props.get(PropsKeys.DL_STORE_IMPL))) {

				return null;
			}

			Store store = _bundleContext.getService(serviceReference);

			Dictionary<String, Object> properties = new HashMapDictionary<>();

			properties.put("dl.store.upgrade", "true");

			_serviceRegistration = _bundleContext.registerService(
				Store.class, store, properties);

			return store;
		}

		@Override
		public void modifiedService(
			ServiceReference<Store> serviceReference, Store store) {

			removedService(serviceReference, store);

			addingService(serviceReference);
		}

		@Override
		public void removedService(
			ServiceReference<Store> serviceReference, Store store) {

			_bundleContext.ungetService(serviceReference);

			try {
				_serviceRegistration.unregister();
			}
			catch (IllegalStateException illegalStateException) {
			}
		}

	}

}