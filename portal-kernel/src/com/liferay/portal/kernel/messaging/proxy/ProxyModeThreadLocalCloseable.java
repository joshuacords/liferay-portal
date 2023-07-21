/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.proxy;

import java.io.Closeable;

/**
 * @author Michael C. Han
 */
public class ProxyModeThreadLocalCloseable implements Closeable {

	public ProxyModeThreadLocalCloseable() {
		_forceSync = ProxyModeThreadLocal.isForceSync();
	}

	@Override
	public void close() {
		ProxyModeThreadLocal.setForceSync(_forceSync);
	}

	private final boolean _forceSync;

}