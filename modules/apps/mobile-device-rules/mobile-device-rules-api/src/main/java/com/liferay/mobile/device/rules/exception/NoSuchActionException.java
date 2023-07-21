/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Edward C. Han
 */
public class NoSuchActionException extends NoSuchModelException {

	public NoSuchActionException() {
	}

	public NoSuchActionException(String msg) {
		super(msg);
	}

	public NoSuchActionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchActionException(Throwable cause) {
		super(cause);
	}

}