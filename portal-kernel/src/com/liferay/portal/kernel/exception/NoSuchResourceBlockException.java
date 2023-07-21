/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.exception;

/**
 * @author Connor McKay
 */
public class NoSuchResourceBlockException extends NoSuchModelException {

	public NoSuchResourceBlockException() {
	}

	public NoSuchResourceBlockException(String msg) {
		super(msg);
	}

	public NoSuchResourceBlockException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchResourceBlockException(Throwable cause) {
		super(cause);
	}

}