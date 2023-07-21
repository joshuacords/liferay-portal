/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.exception;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Marco Leo
 */
public class DuplicateCommerceAccountGroupExternalReferenceCodeException
	extends SystemException {

	public DuplicateCommerceAccountGroupExternalReferenceCodeException() {
	}

	public DuplicateCommerceAccountGroupExternalReferenceCodeException(
		String msg) {

		super(msg);
	}

	public DuplicateCommerceAccountGroupExternalReferenceCodeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateCommerceAccountGroupExternalReferenceCodeException(
		Throwable throwable) {

		super(throwable);
	}

}