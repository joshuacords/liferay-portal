/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.poller.comet;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class CometException extends PortalException {

	public CometException() {
	}

	public CometException(String msg) {
		super(msg);
	}

	public CometException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CometException(Throwable cause) {
		super(cause);
	}

}