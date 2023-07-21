/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.trash;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceRegistration;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.registry.collections.ServiceRegistrationMap;
import com.liferay.registry.collections.ServiceRegistrationMapImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author Alexander Chow
 */
public class TrashHandlerRegistryUtil {

	public static TrashHandler getTrashHandler(String className) {
		return _trashHandlerRegistryUtil._getTrashHandler(className);
	}

	public static List<TrashHandler> getTrashHandlers() {
		return _trashHandlerRegistryUtil._getTrashHandlers();
	}

	public static void register(List<TrashHandler> trashHandlers) {
		for (TrashHandler trashHandler : trashHandlers) {
			register(trashHandler);
		}
	}

	public static void register(TrashHandler trashHandler) {
		_trashHandlerRegistryUtil._register(trashHandler);
	}

	public static void unregister(List<TrashHandler> trashHandlers) {
		for (TrashHandler trashHandler : trashHandlers) {
			unregister(trashHandler);
		}
	}

	public static void unregister(TrashHandler trashHandler) {
		_trashHandlerRegistryUtil._unregister(trashHandler);
	}

	private TrashHandlerRegistryUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			TrashHandler.class, new TrashHandlerServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	private TrashHandler _getTrashHandler(String className) {
		return _trashHandlers.get(className);
	}

	private List<TrashHandler> _getTrashHandlers() {
		return ListUtil.fromMapValues(_trashHandlers);
	}

	private void _register(TrashHandler trashHandler) {
		Registry registry = RegistryUtil.getRegistry();

		ServiceRegistration<TrashHandler> serviceRegistration =
			registry.registerService(TrashHandler.class, trashHandler);

		_serviceRegistrations.put(trashHandler, serviceRegistration);
	}

	private void _unregister(TrashHandler trashHandler) {
		ServiceRegistration<TrashHandler> serviceRegistration =
			_serviceRegistrations.remove(trashHandler);

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	private static final TrashHandlerRegistryUtil _trashHandlerRegistryUtil =
		new TrashHandlerRegistryUtil();

	private final ServiceRegistrationMap<TrashHandler> _serviceRegistrations =
		new ServiceRegistrationMapImpl<>();
	private final ServiceTracker<TrashHandler, TrashHandler> _serviceTracker;
	private final Map<String, TrashHandler> _trashHandlers =
		new ConcurrentSkipListMap<>();

	private class TrashHandlerServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<TrashHandler, TrashHandler> {

		@Override
		public TrashHandler addingService(
			ServiceReference<TrashHandler> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			TrashHandler trashHandler = registry.getService(serviceReference);

			_trashHandlers.put(trashHandler.getClassName(), trashHandler);

			return trashHandler;
		}

		@Override
		public void modifiedService(
			ServiceReference<TrashHandler> serviceReference,
			TrashHandler trashHandler) {
		}

		@Override
		public void removedService(
			ServiceReference<TrashHandler> serviceReference,
			TrashHandler trashHandler) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_trashHandlers.remove(trashHandler.getClassName());
		}

	}

}