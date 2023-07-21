/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.exception;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Marco Leo
 */
public class
	DuplicateCommerceAccountGroupCommerceAccountRelExternalReferenceCodeException
		extends SystemException {

	public DuplicateCommerceAccountGroupCommerceAccountRelExternalReferenceCodeException() {
	}

	public DuplicateCommerceAccountGroupCommerceAccountRelExternalReferenceCodeException(
		String msg) {

		super(msg);
	}

	public DuplicateCommerceAccountGroupCommerceAccountRelExternalReferenceCodeException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public DuplicateCommerceAccountGroupCommerceAccountRelExternalReferenceCodeException(
		Throwable throwable) {

		super(throwable);
	}

}