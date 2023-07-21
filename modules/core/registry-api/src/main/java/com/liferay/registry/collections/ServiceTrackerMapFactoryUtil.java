/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.collections;

/**
 * @author Carlos Sierra Andrés
 */
public class ServiceTrackerMapFactoryUtil {

	public static ServiceTrackerMapFactory getServiceTrackerMapFactory() {
		return _serviceTrackerMapFactory;
	}

	public static void setServiceTrackerMapFactory(
		ServiceTrackerMapFactory serviceTrackerMapFactory) {

		_serviceTrackerMapFactory = serviceTrackerMapFactory;
	}

	private static ServiceTrackerMapFactory _serviceTrackerMapFactory;

}