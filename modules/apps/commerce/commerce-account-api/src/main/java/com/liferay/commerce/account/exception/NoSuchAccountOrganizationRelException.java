/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Marco Leo
 */
public class NoSuchAccountOrganizationRelException
	extends NoSuchModelException {

	public NoSuchAccountOrganizationRelException() {
	}

	public NoSuchAccountOrganizationRelException(String msg) {
		super(msg);
	}

	public NoSuchAccountOrganizationRelException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchAccountOrganizationRelException(Throwable cause) {
		super(cause);
	}

}