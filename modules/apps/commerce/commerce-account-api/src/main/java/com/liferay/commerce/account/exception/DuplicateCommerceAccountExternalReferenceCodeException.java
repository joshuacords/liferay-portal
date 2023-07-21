/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.exception;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Marco Leo
 */
public class DuplicateCommerceAccountExternalReferenceCodeException
	extends SystemException {

	public DuplicateCommerceAccountExternalReferenceCodeException() {
	}

	public DuplicateCommerceAccountExternalReferenceCodeException(String msg) {
		super(msg);
	}

	public DuplicateCommerceAccountExternalReferenceCodeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateCommerceAccountExternalReferenceCodeException(
		Throwable throwable) {

		super(throwable);
	}

}