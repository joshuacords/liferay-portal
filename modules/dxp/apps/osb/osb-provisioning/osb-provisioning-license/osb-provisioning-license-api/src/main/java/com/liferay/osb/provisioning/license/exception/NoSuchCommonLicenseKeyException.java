/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchCommonLicenseKeyException extends NoSuchModelException {

	public NoSuchCommonLicenseKeyException() {
	}

	public NoSuchCommonLicenseKeyException(String msg) {
		super(msg);
	}

	public NoSuchCommonLicenseKeyException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchCommonLicenseKeyException(Throwable throwable) {
		super(throwable);
	}

}