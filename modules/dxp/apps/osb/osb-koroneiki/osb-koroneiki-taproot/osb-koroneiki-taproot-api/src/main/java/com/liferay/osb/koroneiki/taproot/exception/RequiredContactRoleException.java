/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Rebecca Dai
 */
public class RequiredContactRoleException extends PortalException {

	public RequiredContactRoleException() {
	}

	public RequiredContactRoleException(String msg) {
		super(msg);
	}

	public RequiredContactRoleException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredContactRoleException(Throwable cause) {
		super(cause);
	}

	public static class MustNotDeleteContactRoleReferencedByContact
		extends RequiredContactRoleException {

		public MustNotDeleteContactRoleReferencedByContact(long contactRoleId) {
			super(
				String.format(
					"Contact role %s cannot be deleted because it is " +
						"assigned to one or more contacts",
					contactRoleId));
		}

	}

}