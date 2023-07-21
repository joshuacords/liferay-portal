/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Rebecca Dai
 */
public class RequiredProductPurchaseException extends PortalException {

	public RequiredProductPurchaseException() {
	}

	public RequiredProductPurchaseException(String msg) {
		super(msg);
	}

	public RequiredProductPurchaseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredProductPurchaseException(Throwable cause) {
		super(cause);
	}

	public static class
		MustNotDeleteProductPurchaseReferencedByProductConsumption
			extends RequiredProductPurchaseException {

		public MustNotDeleteProductPurchaseReferencedByProductConsumption(
			long productPurchaseId) {

			super(
				String.format(
					"Purchase %s cannot be deleted because it is referenced " +
						"by one or more product consumptions",
					productPurchaseId));
		}

	}

}