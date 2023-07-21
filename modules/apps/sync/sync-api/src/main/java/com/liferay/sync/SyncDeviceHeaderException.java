/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class SyncDeviceHeaderException extends PortalException {

	public SyncDeviceHeaderException() {
	}

	public SyncDeviceHeaderException(String msg) {
		super(msg);
	}

	public SyncDeviceHeaderException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SyncDeviceHeaderException(Throwable cause) {
		super(cause);
	}

}