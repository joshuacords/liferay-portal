/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class ProductPurchaseEndDateException extends PortalException {

	public ProductPurchaseEndDateException() {
	}

	public ProductPurchaseEndDateException(String msg) {
		super(msg);
	}

	public ProductPurchaseEndDateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ProductPurchaseEndDateException(Throwable cause) {
		super(cause);
	}

}