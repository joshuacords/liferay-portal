/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class ContactEmailAddressException extends PortalException {

	public ContactEmailAddressException() {
	}

	public ContactEmailAddressException(String msg) {
		super(msg);
	}

	public ContactEmailAddressException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ContactEmailAddressException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDuplicate
		extends ContactEmailAddressException {

		public MustNotBeDuplicate(String emailAddress) {
			super(
				String.format(
					"A user with email address %s is already in use",
					emailAddress));
		}

	}

}