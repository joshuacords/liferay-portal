/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.exception;

/**
 * @author Connor McKay
 */
public class NoSuchResourceTypePermissionException
	extends NoSuchModelException {

	public NoSuchResourceTypePermissionException() {
	}

	public NoSuchResourceTypePermissionException(String msg) {
		super(msg);
	}

	public NoSuchResourceTypePermissionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchResourceTypePermissionException(Throwable cause) {
		super(cause);
	}

}