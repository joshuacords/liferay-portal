/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Yuanyuan Huang
 * @author Kyle Bischof
 */
public class AccountParentException extends PortalException {

	public AccountParentException() {
	}

	public AccountParentException(String msg) {
		super(msg);
	}

	public AccountParentException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AccountParentException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDescendant extends AccountParentException {

		public MustNotBeDescendant(String accountId) {
			super(
				String.format(
					"The parent account must not be a descendant of %s",
					accountId));
		}

	}

	public static class MustNotBeSelf extends AccountParentException {

		public MustNotBeSelf(String accountId) {
			super(
				String.format(
					"The parent account %s cannot be itself", accountId));
		}

	}

}