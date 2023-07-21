/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.internal.util;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.sync.model.SyncDevice;

/**
 * @author Shinn Lok
 */
public class SyncDeviceThreadLocal {

	public static SyncDevice getSyncDevice() {
		return _syncDevice.get();
	}

	public static void setSyncDevice(SyncDevice syncDevice) {
		_syncDevice.set(syncDevice);
	}

	private static final ThreadLocal<SyncDevice> _syncDevice =
		new CentralizedThreadLocal<>(
			SyncDeviceThreadLocal.class + "._syncDevice");

}