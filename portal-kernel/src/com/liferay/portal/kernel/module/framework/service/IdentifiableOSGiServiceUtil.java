/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.module.framework.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tina Tian
 */
public class IdentifiableOSGiServiceUtil {

	public static IdentifiableOSGiService getIdentifiableOSGiService(
		String osgiServiceIdentifier) {

		return _identifiableOSGiServices.get(osgiServiceIdentifier);
	}

	private IdentifiableOSGiServiceUtil() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		IdentifiableOSGiServiceUtil.class);

	private static final Map<String, IdentifiableOSGiService>
		_identifiableOSGiServices = new ConcurrentHashMap<>();
	private static final ServiceTracker
		<IdentifiableOSGiService, IdentifiableOSGiService> _serviceTracker;

	private static class IdentifiableOSGiServiceServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<IdentifiableOSGiService, IdentifiableOSGiService> {

		@Override
		public IdentifiableOSGiService addingService(
			ServiceReference<IdentifiableOSGiService> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			IdentifiableOSGiService identifiableOSGiService =
				registry.getService(serviceReference);

			try {
				_identifiableOSGiServices.put(
					identifiableOSGiService.getOSGiServiceIdentifier(),
					identifiableOSGiService);
			}
			catch (UnsupportedOperationException
						unsupportedOperationException) {

				// LPS-89569

				if (_log.isDebugEnabled()) {
					_log.debug(
						unsupportedOperationException,
						unsupportedOperationException);
				}

				return null;
			}

			return identifiableOSGiService;
		}

		@Override
		public void modifiedService(
			ServiceReference<IdentifiableOSGiService> serviceReference,
			IdentifiableOSGiService identifiableOSGiService) {

			try {
				_identifiableOSGiServices.put(
					identifiableOSGiService.getOSGiServiceIdentifier(),
					identifiableOSGiService);
			}
			catch (UnsupportedOperationException
						unsupportedOperationException) {

				// LPS-89569

				if (_log.isDebugEnabled()) {
					_log.debug(
						unsupportedOperationException,
						unsupportedOperationException);
				}
			}
		}

		@Override
		public void removedService(
			ServiceReference<IdentifiableOSGiService> serviceReference,
			IdentifiableOSGiService identifiableOSGiService) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			try {
				_identifiableOSGiServices.remove(
					identifiableOSGiService.getOSGiServiceIdentifier());
			}
			catch (UnsupportedOperationException
						unsupportedOperationException) {

				// LPS-89569

				if (_log.isDebugEnabled()) {
					_log.debug(
						unsupportedOperationException,
						unsupportedOperationException);
				}
			}
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			IdentifiableOSGiService.class,
			new IdentifiableOSGiServiceServiceTrackerCustomizer());

		_serviceTracker.open();
	}

}