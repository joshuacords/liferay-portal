/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchEntitlementException extends NoSuchModelException {

	public NoSuchEntitlementException() {
	}

	public NoSuchEntitlementException(String msg) {
		super(msg);
	}

	public NoSuchEntitlementException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchEntitlementException(Throwable cause) {
		super(cause);
	}

}