/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.exception;

/**
 * The base class for all exceptions caused by system problems. Examples include
 * database connection errors and file not found errors.
 *
 * <p>
 * System exceptions are always unexpected, and generally indicate that the
 * portal is misconfigured or that a critical service is unavailable.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see    PortalException
 */
public class SystemException extends NestableRuntimeException {

	public SystemException() {
	}

	public SystemException(String msg) {
		super(msg);
	}

	public SystemException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

}