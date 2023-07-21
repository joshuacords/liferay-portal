/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marco Leo
 */
public class DuplicateCommerceAccountException extends PortalException {

	public DuplicateCommerceAccountException() {
	}

	public DuplicateCommerceAccountException(String msg) {
		super(msg);
	}

	public DuplicateCommerceAccountException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DuplicateCommerceAccountException(Throwable cause) {
		super(cause);
	}

}