/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class LicenseKeyServerInfoException extends PortalException {

	public LicenseKeyServerInfoException() {
	}

	public LicenseKeyServerInfoException(String msg) {
		super(msg);
	}

	public LicenseKeyServerInfoException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public LicenseKeyServerInfoException(Throwable throwable) {
		super(throwable);
	}

}