/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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