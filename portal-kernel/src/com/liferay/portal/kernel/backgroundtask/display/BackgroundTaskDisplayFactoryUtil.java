/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.backgroundtask.display;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Andrew Betts
 */
public class BackgroundTaskDisplayFactoryUtil {

	public static BackgroundTaskDisplay getBackgroundTaskDisplay(
		BackgroundTask backgroundTask) {

		return _getBackgroundTaskDisplayFactory().getBackgroundTaskDisplay(
			backgroundTask);
	}

	public static BackgroundTaskDisplay getBackgroundTaskDisplay(
		long backgroundTaskId) {

		return _getBackgroundTaskDisplayFactory().getBackgroundTaskDisplay(
			backgroundTaskId);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #_getBackgroundTaskDisplayFactory()}
	 */
	@Deprecated
	public static BackgroundTaskDisplayFactory
		getBackgroundTaskDisplayFactory() {

		return _getBackgroundTaskDisplayFactory();
	}

	private static BackgroundTaskDisplayFactory
		_getBackgroundTaskDisplayFactory() {

		return _backgroundTaskDisplayFactory;
	}

	private static volatile BackgroundTaskDisplayFactory
		_backgroundTaskDisplayFactory =
			ServiceProxyFactory.newServiceTrackedInstance(
				BackgroundTaskDisplayFactory.class,
				BackgroundTaskDisplayFactoryUtil.class,
				"_backgroundTaskDisplayFactory", false);

}