/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marco Leo
 */
public class InvalidCommerceChannelTypeException extends PortalException {

	public InvalidCommerceChannelTypeException() {
	}

	public InvalidCommerceChannelTypeException(String msg) {
		super(msg);
	}

	public InvalidCommerceChannelTypeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public InvalidCommerceChannelTypeException(Throwable cause) {
		super(cause);
	}

}