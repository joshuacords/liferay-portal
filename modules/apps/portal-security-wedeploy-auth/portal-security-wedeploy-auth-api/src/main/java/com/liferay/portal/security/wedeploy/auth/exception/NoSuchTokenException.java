/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Supritha Sundaram
 */
public class NoSuchTokenException extends NoSuchModelException {

	public NoSuchTokenException() {
	}

	public NoSuchTokenException(String msg) {
		super(msg);
	}

	public NoSuchTokenException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchTokenException(Throwable cause) {
		super(cause);
	}

}