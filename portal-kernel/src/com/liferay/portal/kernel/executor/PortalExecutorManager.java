/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.executor;

import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.petra.executor.PortalExecutorManager}
 */
@Deprecated
public interface PortalExecutorManager {

	public ThreadPoolExecutor getPortalExecutor(String name);

	public ThreadPoolExecutor getPortalExecutor(
		String name, boolean createIfAbsent);

	public ThreadPoolExecutor registerPortalExecutor(
		String name, ThreadPoolExecutor threadPoolExecutor);

	public void shutdown();

	public void shutdown(boolean interrupt);

}