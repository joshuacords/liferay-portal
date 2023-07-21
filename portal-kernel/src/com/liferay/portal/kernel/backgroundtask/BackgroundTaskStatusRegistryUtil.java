/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.backgroundtask;

import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskStatusRegistryUtil {

	public static BackgroundTaskStatus getBackgroundTaskStatus(
		long backgroundTaskId) {

		return _getBackgroundTaskStatusRegistry().getBackgroundTaskStatus(
			backgroundTaskId);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #_getBackgroundTaskStatusRegistry()}
	 */
	@Deprecated
	public static BackgroundTaskStatusRegistry
		getBackgroundTaskStatusRegistry() {

		return _getBackgroundTaskStatusRegistry();
	}

	public static BackgroundTaskStatus registerBackgroundTaskStatus(
		long backgroundTaskId) {

		return _getBackgroundTaskStatusRegistry().registerBackgroundTaskStatus(
			backgroundTaskId);
	}

	public static BackgroundTaskStatus unregisterBackgroundTaskStatus(
		long backgroundTaskId) {

		return _getBackgroundTaskStatusRegistry().
			unregisterBackgroundTaskStatus(backgroundTaskId);
	}

	private static BackgroundTaskStatusRegistry
		_getBackgroundTaskStatusRegistry() {

		return _backgroundTaskStatusRegistry;
	}

	private static volatile BackgroundTaskStatusRegistry
		_backgroundTaskStatusRegistry =
			ServiceProxyFactory.newServiceTrackedInstance(
				BackgroundTaskStatusRegistry.class,
				BackgroundTaskStatusRegistryUtil.class,
				"_backgroundTaskStatusRegistry", false);

}