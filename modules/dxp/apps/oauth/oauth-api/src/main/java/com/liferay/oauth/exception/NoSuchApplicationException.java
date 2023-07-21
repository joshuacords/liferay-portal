/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Ivica Cardic
 */
public class NoSuchApplicationException extends NoSuchModelException {

	public NoSuchApplicationException() {
	}

	public NoSuchApplicationException(String msg) {
		super(msg);
	}

	public NoSuchApplicationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchApplicationException(Throwable cause) {
		super(cause);
	}

}