/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class SyncSiteUnavailableException extends PortalException {

	public SyncSiteUnavailableException() {
	}

	public SyncSiteUnavailableException(String msg) {
		super(msg);
	}

	public SyncSiteUnavailableException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SyncSiteUnavailableException(Throwable cause) {
		super(cause);
	}

}