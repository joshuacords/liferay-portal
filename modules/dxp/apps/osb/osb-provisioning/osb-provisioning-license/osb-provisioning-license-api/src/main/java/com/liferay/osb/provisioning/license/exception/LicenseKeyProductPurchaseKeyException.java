/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class LicenseKeyProductPurchaseKeyException extends PortalException {

	public LicenseKeyProductPurchaseKeyException() {
	}

	public LicenseKeyProductPurchaseKeyException(String msg) {
		super(msg);
	}

	public LicenseKeyProductPurchaseKeyException(
		String msg, Throwable throwable) {

		super(msg, throwable);
	}

	public LicenseKeyProductPurchaseKeyException(Throwable throwable) {
		super(throwable);
	}

}