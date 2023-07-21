/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Kyle Bischof
 */
public class RequiredProductEntryException extends PortalException {

	public RequiredProductEntryException() {
	}

	public RequiredProductEntryException(String msg) {
		super(msg);
	}

	public RequiredProductEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredProductEntryException(Throwable cause) {
		super(cause);
	}

	public static class MustNotDeleteProductEntryReferencedByProductConsumption
		extends RequiredProductEntryException {

		public MustNotDeleteProductEntryReferencedByProductConsumption(
			long productEntryId) {

			super(
				String.format(
					"Product %s cannot be deleted because it is referenced " +
						"by one or more product consumptions",
					productEntryId));
		}

	}

	public static class MustNotDeleteProductEntryReferencedByProductPurchase
		extends RequiredProductEntryException {

		public MustNotDeleteProductEntryReferencedByProductPurchase(
			long productEntryId) {

			super(
				String.format(
					"Product %s cannot be deleted because it is referenced " +
						"by one or more product purchases",
					productEntryId));
		}

	}

}