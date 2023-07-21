/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.config;

/**
 * @author Michael C. Han
 */
public class DefaultMessagingConfigurator extends BaseMessagingConfigurator {

	@Override
	protected ClassLoader getOperatingClassloader() {
		Thread currentThread = Thread.currentThread();

		return currentThread.getContextClassLoader();
	}

}