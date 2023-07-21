/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.petra.lang.CentralizedThreadLocal;

/**
 * @author Shuyang Zhou
 */
public class ProxyModeThreadLocal {

	public static boolean isForceSync() {
		return _forceSync.get();
	}

	public static void setForceSync(boolean forceSync) {
		_forceSync.set(forceSync);
	}

	private static final ThreadLocal<Boolean> _forceSync =
		new CentralizedThreadLocal<>(
			ProxyModeThreadLocal.class + "_forceSync", () -> Boolean.FALSE);

}