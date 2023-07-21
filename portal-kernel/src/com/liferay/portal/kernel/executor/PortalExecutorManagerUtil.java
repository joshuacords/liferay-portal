/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.executor;

import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class PortalExecutorManagerUtil {

	public static ThreadPoolExecutor getPortalExecutor(String name) {
		return _portalExecutorManager.getPortalExecutor(name);
	}

	public static ThreadPoolExecutor getPortalExecutor(
		String name, boolean createIfAbsent) {

		return _portalExecutorManager.getPortalExecutor(name, createIfAbsent);
	}

	public static PortalExecutorManager getPortalExecutorManager() {
		return _portalExecutorManager;
	}

	public static ThreadPoolExecutor registerPortalExecutor(
		String name, ThreadPoolExecutor threadPoolExecutor) {

		return _portalExecutorManager.registerPortalExecutor(
			name, threadPoolExecutor);
	}

	public static void shutdown() {
		_portalExecutorManager.shutdown();
	}

	public static void shutdown(boolean interrupt) {
		_portalExecutorManager.shutdown(interrupt);
	}

	private PortalExecutorManagerUtil() {
	}

	private static volatile PortalExecutorManager _portalExecutorManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			PortalExecutorManager.class, PortalExecutorManagerUtil.class,
			"_portalExecutorManager", true);

}