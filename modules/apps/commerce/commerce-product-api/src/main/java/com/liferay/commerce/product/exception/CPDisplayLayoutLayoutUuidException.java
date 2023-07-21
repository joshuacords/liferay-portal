/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marco Leo
 */
public class CPDisplayLayoutLayoutUuidException extends PortalException {

	public CPDisplayLayoutLayoutUuidException() {
	}

	public CPDisplayLayoutLayoutUuidException(String msg) {
		super(msg);
	}

	public CPDisplayLayoutLayoutUuidException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CPDisplayLayoutLayoutUuidException(Throwable cause) {
		super(cause);
	}

}