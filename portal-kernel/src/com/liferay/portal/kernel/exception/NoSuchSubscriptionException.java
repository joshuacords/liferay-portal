/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.exception;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchSubscriptionException extends NoSuchModelException {

	public NoSuchSubscriptionException() {
	}

	public NoSuchSubscriptionException(String msg) {
		super(msg);
	}

	public NoSuchSubscriptionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchSubscriptionException(Throwable cause) {
		super(cause);
	}

}